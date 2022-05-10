package com.atypon.throttlingproject.exception;

import com.atypon.throttlingproject.exception.type.ThrottlingExceptionType;

/**
 * The Class ThrottlingException.
 */
public class ThrottlingException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2139178755366560392L;

	/** The type. */
	private final ThrottlingExceptionType type;

	/**
	 * Instantiates a new throttling exception.
	 *
	 * @param type the type
	 * @param message the message
	 */
	public ThrottlingException(ThrottlingExceptionType type, String message) {
		super(message);
		this.type = type;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public ThrottlingExceptionType getType() {
		return type;
	}

}
