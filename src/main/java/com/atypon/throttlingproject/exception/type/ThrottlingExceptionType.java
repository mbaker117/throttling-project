package com.atypon.throttlingproject.exception.type;

/**
 * The Enum ThrottlingExceptionType.
 */
public enum ThrottlingExceptionType {

	/** The exceed number of attempts. */
	EXCEED_NUMBER_OF_ATTEMPTS("exceed number of attempts"), /** The cannot get method. */
 CANNOT_GET_METHOD("cannot get method"),
	
	/** The invalid exceed limit. */
	INVALID_EXCEED_LIMIT("invalid exceed Limit"),
/** The throttling record exception. */
THROTTLING_RECORD_EXCEPTION("throttling record exception");

	/** The msg. */
	private final String msg;

	/**
	 * Instantiates a new throttling exception type.
	 *
	 * @param msg the msg
	 */
	private ThrottlingExceptionType(String msg) {
		this.msg = msg;
	}

	/**
	 * Gets the msg.
	 *
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

}
