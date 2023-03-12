package emotion.data.model;

public class ErrorMessage {

	private final String response;

	public ErrorMessage(String response) {
		this.response = response;
	}

	public String getMessage() {
		return this.response;
	}
}