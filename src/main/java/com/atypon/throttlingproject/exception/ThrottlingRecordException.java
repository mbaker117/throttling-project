package com.atypon.throttlingproject.exception;

import com.atypon.throttlingproject.exception.type.ThrottlingRecordExceptionType;

/**
 * The Class ThrottlingRecordException.
 */
public class ThrottlingRecordException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2139178755366560392L;
	
	/** The type. */
	private final ThrottlingRecordExceptionType type;

	/**
	 * Instantiates a new throttling record exception.
	 *
	 * @param type the type
	 * @param message the message
	 */
	public ThrottlingRecordException(ThrottlingRecordExceptionType type, String message) {
		super(message);
		this.type = type;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public ThrottlingRecordExceptionType getType() {
		return type;
	}

}
