package com.atypon.throttlingproject.handler;

import java.sql.Timestamp;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.atypon.throttlingproject.bean.ExceptionResponse;
import com.atypon.throttlingproject.exception.ThrottlingException;

/**
 * The Class ThrottlingExceptionHandler.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ThrottlingExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Handle throttling exception.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ResponseBody
	@ExceptionHandler({ ThrottlingException.class })
	public ResponseEntity<ExceptionResponse> handleThrottlingException(final ThrottlingException ex) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setCode("403");
		exceptionResponse.setMsg(ex.getMessage());
		exceptionResponse.setTimestamp(new Timestamp(System.currentTimeMillis()));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
	}
}
