package com.atypon.throttlingproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.atypon.throttlingproject.dao.ThrottlingRecordDAO;
import com.atypon.throttlingproject.exception.ThrottlingRecordException;
import com.atypon.throttlingproject.exception.type.ThrottlingRecordExceptionType;
import com.atypon.throttlingproject.model.ThrottlingRecord;
import com.atypon.throttlingproject.service.impl.DefaultThrottlingRecordService;

@SpringBootTest
public class DefaultThrottlingRecordServiceTest {

	private static final long RECORD_ID = 100l;

	private static final String IP_ADDRESS_IS_EMPTY = "ipAddress is empty.";

	private static final String METHOD_ID_IS_EMPTY = "methodId is empty.";

	private static final String METHOD_ID = "123";
	private static final String IP_ADDRESS = "127.0.0.1";

	@InjectMocks
	private ThrottlingRecordService throttlRecordService = new DefaultThrottlingRecordService();

	@Mock
	private ThrottlingRecordDAO throttlingRecordDAO;

	@Test
	public void testAddRecordWithInvalidMethodId() {

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			getThrottlRecordService().addRecord(null, IP_ADDRESS);
		});
		assertEquals(METHOD_ID_IS_EMPTY, exception.getMessage());

	}

	@Test
	public void testAddRecordWithInvalidIpAddress() {

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			getThrottlRecordService().addRecord(METHOD_ID, null);
		});
		assertEquals(IP_ADDRESS_IS_EMPTY, exception.getMessage());

	}

	@Test
	public void testAddRecordWithExistingRecord() {

		when(getThrottlingRecordDAO().findByMethodIdAndIpAddress(METHOD_ID, IP_ADDRESS))
				.thenReturn(Optional.of(new ThrottlingRecord()));
		ThrottlingRecordException exception = assertThrows(ThrottlingRecordException.class, () -> {
			getThrottlRecordService().addRecord(METHOD_ID, IP_ADDRESS);
		});
		assertEquals(ThrottlingRecordExceptionType.RECORD_ALREADY_EXISTS, exception.getType());

	}

	@Test
	public void testGetRecordByMethodIdAndIpAddressWithInvalidMethodId() {

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			getThrottlRecordService().getByMethodIdAndIpAddress(null, IP_ADDRESS);
		});
		assertEquals(METHOD_ID_IS_EMPTY, exception.getMessage());

	}

	@Test
	public void testGetRecordByMethodIdAndIpAddressWithInvalidIpAddress() {

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			getThrottlRecordService().getByMethodIdAndIpAddress(METHOD_ID, null);
		});
		assertEquals(IP_ADDRESS_IS_EMPTY, exception.getMessage());

	}

	@Test
	public void testAddAttemptsWithNotExistingRecord() {

		when(getThrottlingRecordDAO().findById(RECORD_ID)).thenReturn(Optional.empty());
		ThrottlingRecordException exception = assertThrows(ThrottlingRecordException.class, () -> {
			getThrottlRecordService().addAttempt(RECORD_ID, 1);
		});
		assertEquals(ThrottlingRecordExceptionType.RECORD_NOT_EXISTS, exception.getType());

	}

	@Test
	public void testAddAttemptsWithInvalidNumberOfAttempts() {

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			getThrottlRecordService().addAttempt(RECORD_ID, 0);
		});
		assertEquals("invalid number of attempts", exception.getMessage());

	}

	protected ThrottlingRecordService getThrottlRecordService() {
		return throttlRecordService;
	}

	protected ThrottlingRecordDAO getThrottlingRecordDAO() {
		return throttlingRecordDAO;
	}

}
