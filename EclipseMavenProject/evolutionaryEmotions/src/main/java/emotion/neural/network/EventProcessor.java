package emotion.neural.network;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emotion.data.model.Event;
import emotion.data.model.Expression;
import emotion.data.model.NeuralNetworkConfiguration;
import emotion.data.model.Subject;
import emotion.repository.EventRepository;
import emotion.repository.SubjectRepository;
import emotion.type.Pair;

/**
 * a service that acts as a facade between REST API and a neural network
 */

@Service
public class EventProcessor {

	@Autowired
	private Perception perception;

	@Autowired
	private Recognition recognition;

	@Autowired
	private Cognition cognition;

	@Autowired
	private Communication communication;

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private EmotionRepository emotionRepository;

	public Expression processEvent(Event event) {
		event = persistEvent(event);

		return processEventInNeuralNetwork(event);
	}

	// for experiment purpose only
	public void setFeelingConfiguration(NeuralNetworkConfiguration neuralNetworkConfiguration) {
		ThresholdGenerator.changeFeelingThresholdLimits(neuralNetworkConfiguration.getFeelingLowerLimit(),
				neuralNetworkConfiguration.getFeelingUpperLimit());
		TimerGenerator.setFeelingExpiryPeriod(neuralNetworkConfiguration.getFeelingExpiryPeriod());
	}

	// for experiment purpose only
	public NeuralNetworkConfiguration getFeelingConfiguration() {
		return new NeuralNetworkConfiguration(ThresholdGenerator.getFeelingLowerLimit(),
				ThresholdGenerator.getFeelingUpperLimit(), TimerGenerator.getFeelingExpiryPeriod());
	}

	private Event persistEvent(Event newEvent) {
		long currentTimeStamp = Instant.now().toEpochMilli();
		newEvent.setCausedOn(currentTimeStamp);

		Optional<Subject> existingSubject = subjectRepository.findOne(SubjectRepository
				.isKnownSubject(newEvent.getCausedBy().getName(), newEvent.getCausedBy().getIdentifiedAs()));
		if (existingSubject.isPresent()) {
			newEvent.setCausedBy(existingSubject.get());
		} else {
			newEvent.getCausedBy().setKnownOn(currentTimeStamp);
		}
		return eventRepository.save(newEvent);
	}

	private Expression processEventInNeuralNetwork(Event event) {
		// input layer nodes
		Feeling feeling = perception.process(event);
		Memory memory = recognition.process(event.getCausedBy());

		// hidden layer node
		Emotion newEmotion = cognition.process(new Pair<Feeling, Memory>(feeling, memory));
		newEmotion.setEvent(event);
		newEmotion = emotionRepository.save(newEmotion);

		// output layer node
		return communication.process(newEmotion);
	}
}