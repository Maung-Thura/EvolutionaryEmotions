package emotion.neural.network;

import java.time.Instant;

final class TimerGenerator {

	// default to 90 seconds as per Dr. Jill Bolte Taylor
	private static long expiryPeriod = 90000l; // in milliseconds

	public static final long getFeelingExpiry() {
		return Instant.now().toEpochMilli() + expiryPeriod;
	}

	public static final void setFeelingExpiryPeriod(long _expiryPeriod) {
		expiryPeriod = _expiryPeriod;
	}

	public static final long getFeelingExpiryPeriod() {
		return expiryPeriod;
	}

}
