package com.atypon.throttlingproject.service.impl;

import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atypon.throttlingproject.dao.ThrottlingRecordDAO;
import com.atypon.throttlingproject.exception.ThrottlingRecordException;
import com.atypon.throttlingproject.exception.type.ThrottlingRecordExceptionType;
import com.atypon.throttlingproject.model.ThrottlingRecord;
import com.atypon.throttlingproject.service.ThrottlingRecordService;
import com.google.common.base.Preconditions;

/**
 * The Class DefaultThrottlingRecordService.
 */
@Service
public class DefaultThrottlingRecordService implements ThrottlingRecordService {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DefaultThrottlingRecordService.class);

	/** The throttling record DAO. */
	@Autowired
	private ThrottlingRecordDAO throttlingRecordDAO;

	/**
	 * Adds the record.
	 *
	 * @param methodId the method id
	 * @param ipAddress the ip address
	 * @throws ThrottlingRecordException the throttling record exception
	 */
	@Override
	public void addRecord(String methodId, String ipAddress) throws ThrottlingRecordException {
		validateMethodIdAndIpAddress(methodId, ipAddress);
		Optional<ThrottlingRecord> throttlingRecord = getByMethodIdAndIpAddress(methodId, ipAddress);
		if (throttlingRecord.isPresent()) {
			LOG.error("record with methodName {} and ipAddress {} is already exists", methodId, ipAddress);
			throw new ThrottlingRecordException(ThrottlingRecordExceptionType.RECORD_ALREADY_EXISTS,
					ThrottlingRecordExceptionType.RECORD_ALREADY_EXISTS.getMsg());
		}

		getThrottlingRecordDAO().save(getRecord(methodId, ipAddress));
		LOG.info("record with methodId {} and ipAddress {} is added!", methodId, ipAddress);
	}

	/**
	 * Gets the by method id and ip address.
	 *
	 * @param methodId the method id
	 * @param ipAddress the ip address
	 * @return the by method id and ip address
	 */
	@Override
	public Optional<ThrottlingRecord> getByMethodIdAndIpAddress(String methodId, String ipAddress) {
		validateMethodIdAndIpAddress(methodId, ipAddress);
		return getThrottlingRecordDAO().findByMethodIdAndIpAddress(methodId, ipAddress);
	}

	/**
	 * Adds the attempt.
	 *
	 * @param id the id
	 * @param numberOfAttempts the number of attempts
	 * @throws ThrottlingRecordException the throttling record exception
	 */
	@Override
	public void addAttempt(long id, long numberOfAttempts) throws ThrottlingRecordException {
		Preconditions.checkArgument(numberOfAttempts > 0, "invalid number of attempts");
		Optional<ThrottlingRecord> throttlingRecord = getThrottlingRecordDAO().findById(id);
		if (throttlingRecord.isEmpty()) {
			LOG.error("record with id {}  doesn't exists", id);
			throw new ThrottlingRecordException(ThrottlingRecordExceptionType.RECORD_NOT_EXISTS,
					ThrottlingRecordExceptionType.RECORD_NOT_EXISTS.getMsg());
		}

		ThrottlingRecord entity = throttlingRecord.get();
		entity.setNumberOfAttempts(numberOfAttempts);
		getThrottlingRecordDAO().save(entity);
		LOG.info("record with id {}  is updated with {} number Of Attempts!", id, numberOfAttempts);

	}

	/**
	 * Gets the record.
	 *
	 * @param methodId the method id
	 * @param ipAddress the ip address
	 * @return the record
	 */
	protected ThrottlingRecord getRecord(String methodId, String ipAddress) {
		ThrottlingRecord throttlingRecord = new ThrottlingRecord();
		throttlingRecord.setIpAddress(ipAddress);
		throttlingRecord.setMethodId(methodId);
		throttlingRecord.setNumberOfAttempts(1);
		return throttlingRecord;
	}

	/**
	 * Validate method id and ip address.
	 *
	 * @param methodId the method id
	 * @param ipAddress the ip address
	 */
	protected void validateMethodIdAndIpAddress(String methodId, String ipAddress) {
		Preconditions.checkArgument(Strings.isNotBlank(methodId), "methodId is empty.");
		Preconditions.checkArgument(Strings.isNotBlank(ipAddress), "ipAddress is empty.");
	}

	/**
	 * Gets the throttling record DAO.
	 *
	 * @return the throttling record DAO
	 */
	protected ThrottlingRecordDAO getThrottlingRecordDAO() {
		return throttlingRecordDAO;
	}

}
