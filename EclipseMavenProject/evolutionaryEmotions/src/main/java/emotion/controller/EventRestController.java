package emotion.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import emotion.data.model.Event;
import emotion.data.model.Expression;
import emotion.data.model.NeuralNetworkConfiguration;
import emotion.data.model.Subject;
import emotion.neural.network.EventProcessor;
import emotion.repository.EventRepository;
import emotion.repository.SubjectRepository;

/**
 * 
 * REST API interface to interact with physical devices like sensors and cameras
 *
 */

@RestController
@RequestMapping("/events")
public class EventRestController {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private EventProcessor eventProcessor;

	@PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = "application/json")
	public ResponseEntity<Expression> addNewEvent(@RequestBody Event newEvent) {
		Expression expression = eventProcessor.processEvent(newEvent);

		return new ResponseEntity<Expression>(expression, HttpStatus.CREATED);
	}

	@GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Event>> getAllEvents() {
		Collection<Event> allEvents = eventRepository.findAll();
		return new ResponseEntity<Collection<Event>>(allEvents, HttpStatus.OK);
	}

	@GetMapping(path = "/subjects", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Subject>> getAllSubjects() {
		Collection<Subject> allSubjects = subjectRepository.findAll();
		return new ResponseEntity<Collection<Subject>>(allSubjects, HttpStatus.OK);
	}

	@GetMapping(path = "/configurations", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<NeuralNetworkConfiguration> getNeuralNetworkConfiguration() {
		NeuralNetworkConfiguration neuralNetworkConfiguration = eventProcessor.getFeelingConfiguration();
		return new ResponseEntity<NeuralNetworkConfiguration>(neuralNetworkConfiguration, HttpStatus.OK);
	}

	@PatchMapping(path = "/configurations", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<NeuralNetworkConfiguration> changeNeuralNetworkConfiguration(
			@RequestBody NeuralNetworkConfiguration neuralNetworkConfiguration) {
		eventProcessor.setFeelingConfiguration(neuralNetworkConfiguration);
		return new ResponseEntity<NeuralNetworkConfiguration>(neuralNetworkConfiguration, HttpStatus.OK);
	}

}