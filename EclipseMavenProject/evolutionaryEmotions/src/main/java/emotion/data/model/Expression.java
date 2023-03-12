package emotion.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * a visible output of this system that reveals the current emotions of this
 * system
 * 
 */
@Getter
@AllArgsConstructor
public class Expression {

	private String verbalWords;
	private String physicalReaction;

}
