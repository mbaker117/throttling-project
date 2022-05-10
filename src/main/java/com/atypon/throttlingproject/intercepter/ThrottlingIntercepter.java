package com.atypon.throttlingproject.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.atypon.throttlingproject.service.ThrottlingService;

/**
 * The Class ThrottlingIntercepter.
 */
@Component
public class ThrottlingIntercepter implements HandlerInterceptor {

	/** The throttling service. */
	@Autowired
	private ThrottlingService throttlingService;

	/**
	 * Pre handle.
	 *
	 * @param request the request
	 * @param response the response
	 * @param handler the handler
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		getThrottlingService().checkThrottling(request);
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	/**
	 * Gets the throttling service.
	 *
	 * @return the throttling service
	 */
	protected ThrottlingService getThrottlingService() {
		return throttlingService;
	}

}
