package emotion.interceptor;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class EventRestInterceptor implements HandlerInterceptor {

	// non-expiring bearer token for demonstration purpose only
	@Value("${bearer.token}")
	private String bearerToken;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (request.getHeader(HttpHeaders.AUTHORIZATION) == null
				|| !request.getHeader(HttpHeaders.AUTHORIZATION).equals(bearerToken)) {
			throw new AuthenticationException();
		}

		return true;
	}

}
