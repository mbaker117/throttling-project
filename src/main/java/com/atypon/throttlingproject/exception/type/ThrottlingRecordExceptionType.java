package com.atypon.throttlingproject.exception.type;

/**
 * The Enum ThrottlingRecordExceptionType.
 */
public enum ThrottlingRecordExceptionType {

	/** The record not exists. */
	RECORD_NOT_EXISTS("record not exists"),/** The record already exists. */
RECORD_ALREADY_EXISTS("record already exists");

	/** The msg. */
	private final String msg;

	/**
	 * Instantiates a new throttling record exception type.
	 *
	 * @param msg the msg
	 */
	private ThrottlingRecordExceptionType(String msg) {
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
