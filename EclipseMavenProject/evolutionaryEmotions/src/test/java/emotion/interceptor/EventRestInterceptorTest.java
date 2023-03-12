package emotion.interceptor;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import emotion.controller.EmotionExceptionHandler;

public class EventRestInterceptorTest {

	private EventRestInterceptor eventRestInterceptor = new EventRestInterceptor();
	private final String testBearerToken = "Bearer b4424893-2695-4f71-b1c0-e6378484e897";

	@Before
	public void initialize()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field bearerToken = EventRestInterceptor.class.getDeclaredField("bearerToken");
		bearerToken.setAccessible(true);
		bearerToken.set(eventRestInterceptor, testBearerToken);
	}

	@Test(expected = AuthenticationException.class)
	public void testAuthenticationException() throws Exception {
		HttpServletRequest request = new MockHttpServletRequest();
		HttpServletResponse response = new MockHttpServletResponse();
		Object handler = new EmotionExceptionHandler();
		eventRestInterceptor.preHandle(request, response, handler);
	}

	@Test
	public void testPreController() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader(HttpHeaders.AUTHORIZATION, testBearerToken);
		HttpServletResponse response = new MockHttpServletResponse();
		Object handler = new EmotionExceptionHandler();
		boolean passed = eventRestInterceptor.preHandle(request, response, handler);
		assertTrue(passed);
	}

}
