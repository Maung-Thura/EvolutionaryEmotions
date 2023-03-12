package emotion.neural.network;

import org.springframework.stereotype.Component;

import emotion.data.model.Expression;

/**
 * An output layer neural node that can accept an {@link Emotion} and transform
 * it into an {@link Expression}
 *
 */

@Component
class Communication implements NeuralNode<Emotion, Expression> {

	@Override
	public Expression process(Emotion emotion) {
		if (emotion != null && emotion.getEmotionType() != null) {
			switch (emotion.getEmotionType()) {
			case ANGER:
				return new Expression("I'm furious!", "Hold fists.");
			case CONTEMPT:
				return new Expression("I don't care what you say...", "Display a boring face emoji.");
			case DISGUST:
				return new Expression("That's disgusting!", "Make a vomiting sound.");
			case ENJOYMENT:
				return new Expression("I am so happy!", "Display a smiling face emoji.");
			case FEAR:
				return new Expression("I'm scared!", "Display a worrying face emoji.");
			case SADNESS:
				return new Expression("My heart is broken...", "Display a crying face emoji.");
			case SURPRISE:
				return new Expression("Oh my!", "Display a shocking face emoji.");
			}
		}

		return new Expression("I don't know how I feel at the moment!", "Display a neutral face emoji.");
	}

}
