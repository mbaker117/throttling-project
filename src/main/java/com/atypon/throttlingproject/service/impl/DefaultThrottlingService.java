package com.atypon.throttlingproject.service.impl;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.atypon.throttlingproject.annotation.Throttling;
import com.atypon.throttlingproject.exception.ThrottlingException;
import com.atypon.throttlingproject.exception.ThrottlingRecordException;
import com.atypon.throttlingproject.exception.type.ThrottlingExceptionType;
import com.atypon.throttlingproject.model.ThrottlingRecord;
import com.atypon.throttlingproject.service.ThrottlingRecordService;
import com.atypon.throttlingproject.service.ThrottlingService;
import com.google.common.base.Preconditions;

/**
 * The Class DefaultThrottlingService.
 */
@Service
public class DefaultThrottlingService implements ThrottlingService {

	/** The Constant EXCEED_MSG. */
	private static final String EXCEED_MSG = "ipAddress {} is exceed number of attempts for method {}";

	/** The Constant THROTTLING_EXCEED_LIMIT. */
	private static final String THROTTLING_EXCEED_LIMIT = "throttling.exceedLimit";

	/** The Constant HANDLER_IS_NULL. */
	private static final String HANDLER_IS_NULL = "handler is null";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DefaultThrottlingService.class);

	/** The throttling record service. */
	@Autowired
	private ThrottlingRecordService throttlingRecordService;

	/** The environment. */
	@Autowired
	private Environment environment;

	/** The application context. */
	@Autowired
	private ApplicationContext applicationContext;

	/**
	 * Check throttling.
	 *
	 * @param request the request
	 * @throws ThrottlingException the throttling exception
	 */
	@Override
	public void checkThrottling(HttpServletRequest request) throws ThrottlingException {
		Preconditions.checkArgument(Objects.nonNull(request), "request is null");
		String ipAddress = request.getRemoteAddr();
		Method method = getMethod(request);
		if (!checkThrottlingEnabled(method)) {
			LOG.info("throttling is not enabled for method {}", method.hashCode());
			return;
		}
		long exceedLimit = getExceedLimit();
		String methodId = String.valueOf(method.hashCode());
		Optional<ThrottlingRecord> throttlingRecordOptional = getThrottlingRecordService()
				.getByMethodIdAndIpAddress(methodId, ipAddress);

		if ((throttlingRecordOptional.isEmpty() && exceedLimit == 0l)
				|| (throttlingRecordOptional.isPresent() && throttlingRecordOptional.get().getNumberOfAttempts() >= exceedLimit)) {
			LOG.error(EXCEED_MSG, ipAddress, method.hashCode());
			throw new ThrottlingException(ThrottlingExceptionType.EXCEED_NUMBER_OF_ATTEMPTS,
					ThrottlingExceptionType.EXCEED_NUMBER_OF_ATTEMPTS.getMsg());
		}

		try {
			saveRecord(throttlingRecordOptional, methodId, ipAddress);
			LOG.info("record saved");
		} catch (ThrottlingRecordException e) {
			LOG.error(e.getMessage());
			throw new ThrottlingException(ThrottlingExceptionType.THROTTLING_RECORD_EXCEPTION, e.getMessage());
		}

	}

	/**
	 * Save record.
	 *
	 * @param throttlingRecordOptional the throttling record optional
	 * @param methodId the method id
	 * @param ipAddress the ip address
	 * @throws ThrottlingRecordException the throttling record exception
	 */
	private void saveRecord(Optional<ThrottlingRecord> throttlingRecordOptional, String methodId, String ipAddress)
			throws ThrottlingRecordException {
		if (throttlingRecordOptional.isEmpty()) {
			getThrottlingRecordService().addRecord(methodId, ipAddress);
			return;
		}

		ThrottlingRecord throttlingRecord = throttlingRecordOptional.get();
		long numberOfAttempts = throttlingRecord.getNumberOfAttempts() + 1;
		getThrottlingRecordService().addAttempt(throttlingRecord.getId(), numberOfAttempts);
	}

	/**
	 * Gets the exceed limit.
	 *
	 * @return the exceed limit
	 */
	private long getExceedLimit() {
		String property = getEnvironment().getProperty(THROTTLING_EXCEED_LIMIT);

		return Strings.isBlank(property) ? 0 : Long.valueOf(property);
	}

	/**
	 * Check throttling enabled.
	 *
	 * @param method the method
	 * @return true, if successful
	 */
	private boolean checkThrottlingEnabled(Method method) {

		return method.isAnnotationPresent(Throttling.class);
	}

	/**
	 * Gets the method.
	 *
	 * @param request the request
	 * @return the method
	 * @throws ThrottlingException the throttling exception
	 */
	private Method getMethod(HttpServletRequest request) throws ThrottlingException {
		HandlerExecutionChain handler = null;
		try {
			handler = getRequestMappingHandlerMapping().getHandler(request);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		if (Objects.isNull(handler)) {
			LOG.error(HANDLER_IS_NULL);
			throw new ThrottlingException(ThrottlingExceptionType.CANNOT_GET_METHOD, HANDLER_IS_NULL);
		}
		return ((HandlerMethod) handler.getHandler()).getMethod();
	}

	/**
	 * Gets the throttling record service.
	 *
	 * @return the throttling record service
	 */
	protected ThrottlingRecordService getThrottlingRecordService() {
		return throttlingRecordService;
	}

	/**
	 * Gets the request mapping handler mapping.
	 *
	 * @return the request mapping handler mapping
	 */
	protected RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
		return (RequestMappingHandlerMapping) getApplicationContext().getBean("requestMappingHandlerMapping");
	}

	/**
	 * Gets the environment.
	 *
	 * @return the environment
	 */
	protected Environment getEnvironment() {
		return environment;
	}

	/**
	 * Gets the application context.
	 *
	 * @return the application context
	 */
	protected ApplicationContext getApplicationContext() {
		return applicationContext;
	}

}
