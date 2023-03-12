package emotion.neural.network;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import emotion.data.model.Subject;

/**
 * An input layer neural node that can accept a {@link Subject} and retrieve a
 * list of past {@link Emotion}s
 *
 */

@Component
class Recognition implements NeuralNode<Subject, Memory> {

	@Autowired
	private EmotionRepository emotionRepository;

	@Override
	public Memory process(Subject subject) {
		List<Emotion> pastEmotions = emotionRepository.findAll(EmotionRepository.retrievePastEmotions(subject));

		if (pastEmotions != null && !pastEmotions.isEmpty()) {
			return new Memory(subject, Collections.unmodifiableList(pastEmotions));
		} else {
			Memory noMemory = new Memory(subject, Collections.emptyList());
			return noMemory;
		}
	}

}
