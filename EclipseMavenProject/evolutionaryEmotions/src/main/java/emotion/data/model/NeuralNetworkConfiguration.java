package emotion.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * A configuration to experiment a different emotion expressions at runtime
 *
 */

@Getter
@AllArgsConstructor
public class NeuralNetworkConfiguration {

	private int feelingLowerLimit;
	private int feelingUpperLimit;
	private long feelingExpiryPeriod;
}
