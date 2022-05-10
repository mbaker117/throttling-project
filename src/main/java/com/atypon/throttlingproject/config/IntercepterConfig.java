package com.atypon.throttlingproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.atypon.throttlingproject.intercepter.ThrottlingIntercepter;

/**
 * The Class IntercepterConfig.
 */
@Configuration
public class IntercepterConfig implements WebMvcConfigurer {

	/** The throttling intercepter. */
	@Autowired
	private ThrottlingIntercepter throttlingIntercepter;

	/**
	 * Adds the interceptors.
	 *
	 * @param registry the registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(getThrottlingIntercepter());
	}

	/**
	 * Gets the throttling intercepter.
	 *
	 * @return the throttling intercepter
	 */
	protected ThrottlingIntercepter getThrottlingIntercepter() {
		return throttlingIntercepter;
	}

}
