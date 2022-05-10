package com.atypon.throttlingproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class ThrottlingRecord.
 */
@Entity
@Table(name = "THROTLINGR_ECORD")
public class ThrottlingRecord {

	/** The id. */
	@Id
	@GeneratedValue
	private long id;

	/** The method id. */
	@Column(name = "MethodId")
	private String methodId;

	/** The ip address. */
	@Column(name = "ipAddress")
	private String ipAddress;

	/** The number of attempts. */
	@Column(name = "numberOfAttempts")
	private long numberOfAttempts;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the method id.
	 *
	 * @return the method id
	 */
	public String getMethodId() {
		return methodId;
	}

	/**
	 * Sets the method id.
	 *
	 * @param methodId the new method id
	 */
	public void setMethodId(String methodId) {
		this.methodId = methodId;
	}

	/**
	 * Gets the ip address.
	 *
	 * @return the ip address
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * Sets the ip address.
	 *
	 * @param ipAddress the new ip address
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * Gets the number of attempts.
	 *
	 * @return the number of attempts
	 */
	public long getNumberOfAttempts() {
		return numberOfAttempts;
	}

	/**
	 * Sets the number of attempts.
	 *
	 * @param numberOfAttempts the new number of attempts
	 */
	public void setNumberOfAttempts(long numberOfAttempts) {
		this.numberOfAttempts = numberOfAttempts;
	}

}
