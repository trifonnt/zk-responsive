package be.chillworld.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author chillworld
 */
public class PageDispatcherFilter implements Filter {

	private static final String INDEX_PAGE = "/index.zul";
	private static final String PAGE = "page";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String path = req.getRequestURI().substring(req.getContextPath().length());
		if (path.equals("/")
			|| path.startsWith("/img")
			|| path.startsWith("/css")
			|| path.startsWith("/js")
			|| path.startsWith("/zkau")
			|| path.startsWith("/zkwm")
			|| path.startsWith("/index")
			|| path.contains(".zul")
			|| path.contains("html")
			|| path.contains("favicon.ico")
			|| path.contains("j_spring_security_check")
			) 
		{
			chain.doFilter(request, response); // Goes to default servlet.
		} else {
			String newPath = getPath(path, req);
			request
				.getRequestDispatcher(newPath)
				.forward(request, response);
		}
	}

	@Override
	public void destroy() {
	}

	private String getPath(String path, HttpServletRequest req) {
		req.setAttribute(PAGE, path.replaceFirst("/", ""));
		return INDEX_PAGE;
	}
}
