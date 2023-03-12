package emotion.neural.network;

import java.util.concurrent.ThreadLocalRandom;

final class ThresholdGenerator {

	private static int feelingLowerLimit = -11;
	private static int feelingUpperLimit = 31;

	public static final int getFeelingThreshold() {
		return ThreadLocalRandom.current().nextInt(feelingLowerLimit, feelingUpperLimit);
	}

	public static final void changeFeelingThresholdLimits(int _lowerLimit, int _upperLimit) {
		feelingLowerLimit = _lowerLimit;
		feelingUpperLimit = _upperLimit;
	}

	public static final int getFeelingLowerLimit() {
		return feelingLowerLimit;
	}

	public static final int getFeelingUpperLimit() {
		return feelingUpperLimit;
	}
}
