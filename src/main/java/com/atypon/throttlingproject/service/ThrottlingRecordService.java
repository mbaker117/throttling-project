package com.atypon.throttlingproject.service;

import java.util.Optional;

import com.atypon.throttlingproject.exception.ThrottlingRecordException;
import com.atypon.throttlingproject.model.ThrottlingRecord;

/**
 * The Interface ThrottlingRecordService.
 */
public interface ThrottlingRecordService {
	
	/**
	 * Adds the record.
	 *
	 * @param methodId the method id
	 * @param ipAddress the ip address
	 * @throws ThrottlingRecordException the throttling record exception
	 */
	public void addRecord(String methodId,String ipAddress) throws ThrottlingRecordException;
	
	/**
	 * Gets the by method id and ip address.
	 *
	 * @param methodId the method id
	 * @param ipAddress the ip address
	 * @return the by method id and ip address
	 */
	public Optional<ThrottlingRecord> getByMethodIdAndIpAddress(String methodId,String ipAddress);
	
	/**
	 * Adds the attempt.
	 *
	 * @param id the id
	 * @param numberOfAttempts the number of attempts
	 * @throws ThrottlingRecordException the throttling record exception
	 */
	public void addAttempt(long id, long numberOfAttempts) throws ThrottlingRecordException;


}
