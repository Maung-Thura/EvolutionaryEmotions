package emotion.neural.network;

import static emotion.type.EmotionType.ANGER;
import static emotion.type.EmotionType.CONTEMPT;
import static emotion.type.EmotionType.DISGUST;
import static emotion.type.EmotionType.ENJOYMENT;
import static emotion.type.EmotionType.FEAR;
import static emotion.type.EmotionType.SADNESS;
import static emotion.type.EmotionType.SURPRISE;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import emotion.type.EmotionType;
import emotion.type.Pair;

/**
 * A hidden layer neural node that can accept a pair of a {@link Feeling} and a
 * {@link Memory}, and transform a pair into an {@link Emotion}
 *
 */

@Component
class Cognition implements NeuralNode<Pair<Feeling, Memory>, Emotion> {

	@Override
	public Emotion process(Pair<Feeling, Memory> input) {
		Feeling feeling = input.getFirst();
		Memory memory = input.getSecond();

		int processedMagnitude = feeling.getCoefficient() * feeling.getMagnitude();
		int threshold = feeling.getThreshold();

		List<EmotionType> emotionTypes = memory.getEmotions().stream().map(emotion -> emotion.getEmotionType())
				.collect(Collectors.toList());

		if (feeling.getExpireOn() > Instant.now().toEpochMilli()) {
			emotionTypes.add(determineEmotionType(processedMagnitude, threshold));
		}

		EmotionType emotionType = emotionTypes.get(ThreadLocalRandom.current().nextInt(0, emotionTypes.size()));
		return new Emotion(null, emotionType, processedMagnitude, threshold, null);
	}

	// TODO: move the logic into runtime configuration file
	private EmotionType determineEmotionType(int processedMagnitude, int threshold) {
		switch (processedMagnitude) {
		case -11:
			return compare(processedMagnitude, threshold) ? SURPRISE : DISGUST;
		case -7:
			return compare(processedMagnitude, threshold) ? CONTEMPT : SURPRISE;
		case -5:
			return compare(processedMagnitude, threshold) ? SURPRISE : FEAR;
		case -3:
			return compare(processedMagnitude, threshold) ? SURPRISE : ANGER;
		case -2:
			return compare(processedMagnitude, threshold) ? FEAR : SADNESS;
		case 13:
			return ENJOYMENT;
		case 17:
		case 19:
		case 23:
		case 29:
			return compare(processedMagnitude, threshold) ? SURPRISE : ENJOYMENT;
		default:
			return null;
		}
	}

	private boolean compare(int processedMagnitude, int threshold) {
		return processedMagnitude < threshold;
	}

}
