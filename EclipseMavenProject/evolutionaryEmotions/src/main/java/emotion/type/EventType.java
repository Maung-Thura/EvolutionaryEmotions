package emotion.type;

/**
 * Abstract event types, which evokes one or more emotions. Assuming to be
 * provided by different kind of sensors and internal thoughts
 *
 */
public enum EventType {

	NEGATIVE_MENTAL, NEGATIVE_PHYSICAL, NEGATIVE_AUDITORY, NEGATIVE_VISUAL, NEGATIVE_OLFACTORY,

	POSITIVE_MENTAL, POSITIVE_PHYSICAL, POSITIVE_AUDITORY, POSITIVE_VISUAL, POSITIVE_OLFACTORY;

}
