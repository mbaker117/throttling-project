package com.atypon.throttlingproject.service;

import javax.servlet.http.HttpServletRequest;

import com.atypon.throttlingproject.exception.ThrottlingException;

/**
 * The Interface ThrottlingService.
 */
public interface ThrottlingService {
	
	/**
	 * Check throttling.
	 *
	 * @param request the request
	 * @throws ThrottlingException the throttling exception
	 */
	public void checkThrottling(HttpServletRequest request) throws ThrottlingException;

}
