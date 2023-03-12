package emotion.neural.network;

import static emotion.neural.network.ThresholdGenerator.getFeelingThreshold;
import static emotion.neural.network.TimerGenerator.getFeelingExpiry;

import org.springframework.stereotype.Component;

import emotion.data.model.Event;

/**
 * An input layer neural node that can accept an {@link Event} and transform it
 * into mathematical representation of a {@link Feeling}
 *
 */

@Component
class Perception implements NeuralNode<Event, Feeling> {

	@Override
	public Feeling process(Event input) {
		switch (input.getEventType()) {
		case NEGATIVE_MENTAL:
			return new Feeling(2, -1, getFeelingThreshold(), getFeelingExpiry());
		case NEGATIVE_PHYSICAL:
			return new Feeling(3, -1, getFeelingThreshold(), getFeelingExpiry());
		case NEGATIVE_AUDITORY:
			return new Feeling(5, -1, getFeelingThreshold(), getFeelingExpiry());
		case NEGATIVE_VISUAL:
			return new Feeling(7, -1, getFeelingThreshold(), getFeelingExpiry());
		case NEGATIVE_OLFACTORY:
			return new Feeling(11, -1, getFeelingThreshold(), getFeelingExpiry());
		case POSITIVE_MENTAL:
			return new Feeling(13, 1, getFeelingThreshold(), getFeelingExpiry());
		case POSITIVE_PHYSICAL:
			return new Feeling(17, 1, getFeelingThreshold(), getFeelingExpiry());
		case POSITIVE_AUDITORY:
			return new Feeling(19, 1, getFeelingThreshold(), getFeelingExpiry());
		case POSITIVE_VISUAL:
			return new Feeling(23, 1, getFeelingThreshold(), getFeelingExpiry());
		case POSITIVE_OLFACTORY:
			return new Feeling(29, 1, getFeelingThreshold(), getFeelingExpiry());
		default:
			return new Feeling(0, 1, getFeelingThreshold(), getFeelingExpiry());
		}

	}

}
