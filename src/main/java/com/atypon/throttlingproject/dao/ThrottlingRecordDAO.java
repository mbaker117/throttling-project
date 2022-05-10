package com.atypon.throttlingproject.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atypon.throttlingproject.model.ThrottlingRecord;

/**
 * The Interface ThrottlingRecordDAO.
 */
@Repository
public interface ThrottlingRecordDAO extends JpaRepository<ThrottlingRecord, Long> {
	
	/**
	 * Find by method id and ip address.
	 *
	 * @param methodId the method id
	 * @param ipAddress the ip address
	 * @return the optional
	 */
	public Optional<ThrottlingRecord> findByMethodIdAndIpAddress(String methodId,String ipAddress);

}
