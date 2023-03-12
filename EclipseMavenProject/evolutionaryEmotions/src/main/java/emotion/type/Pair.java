package emotion.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Pair<F, S> {

	private F first;
	private S second;

}
