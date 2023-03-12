package emotion.neural.network;

import java.util.Collection;

import emotion.data.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * a neuron that traverses within the neural network boundary
 *
 */
@Getter
@AllArgsConstructor
class Memory {
	private Subject subject;
	private Collection<Emotion> emotions;
}
