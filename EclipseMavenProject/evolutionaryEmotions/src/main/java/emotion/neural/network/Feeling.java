package emotion.neural.network;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * an expiring neuron that traverses within the neural network boundary
 *
 */

@Getter
@AllArgsConstructor
class Feeling {

	private int magnitude;

	private int coefficient;

	private int threshold;

	private long expireOn;
}
