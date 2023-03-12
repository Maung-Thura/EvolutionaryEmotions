package emotion.controller;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import emotion.data.model.ErrorMessage;

@ControllerAdvice
public class EmotionExceptionHandler {

	@ResponseBody
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ErrorMessage> handleServiceException(HttpServletResponse response,
			AuthenticationException e) {
		return new ResponseEntity<ErrorMessage>(new ErrorMessage(HttpStatus.UNAUTHORIZED.getReasonPhrase()),
				HttpStatus.UNAUTHORIZED);
	}
}