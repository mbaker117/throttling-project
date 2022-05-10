package com.atypon.throttlingproject.bean;

import java.sql.Timestamp;

/**
 * The Class ExceptionResponse.
 */
public class ExceptionResponse {

	/** The code. */
	private String code;

	/** The msg. */
	private String msg;

	/** The timestamp. */
	private Timestamp timestamp;

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the msg.
	 *
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * Sets the msg.
	 *
	 * @param msg the new msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	public Timestamp getTimestamp() {
		return timestamp;
	}

	/**
	 * Sets the timestamp.
	 *
	 * @param timestamp the new timestamp
	 */
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

}
