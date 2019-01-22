package spring.jsample.spring;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.web.filter.OncePerRequestFilter;

public class UniqueIdFilter extends OncePerRequestFilter {

	private static final String REQ_KEY = "REQ_KEY";

	private static final String RES_HEADER = "id-header";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
			String token = UUID.randomUUID().toString();
			ThreadContext.put(REQ_KEY, token);
			response.addHeader(RES_HEADER, token);
			filterChain.doFilter(request, response);
		} finally {
			ThreadContext.clearAll();
		}
	}

}
