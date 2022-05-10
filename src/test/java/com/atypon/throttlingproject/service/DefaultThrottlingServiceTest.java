package com.atypon.throttlingproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import com.atypon.throttlingproject.service.impl.DefaultThrottlingService;

@SpringBootTest
public class DefaultThrottlingServiceTest {

	@InjectMocks
	private ThrottlingService throttlingService = new DefaultThrottlingService();

	@Mock
	private ThrottlingRecordService throttlingRecordService;

	@Mock
	private Environment environment;

	@Test
	public void checkThrottlingWithInvalidRequest() {

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			getThrottlingService().checkThrottling(null);
		});
		assertEquals("request is null", exception.getMessage());

	}

	protected ThrottlingService getThrottlingService() {
		return throttlingService;
	}

	protected void setThrottlingService(ThrottlingService throttlingService) {
		this.throttlingService = throttlingService;
	}

	protected ThrottlingRecordService getThrottlingRecordService() {
		return throttlingRecordService;
	}

	protected void setThrottlingRecordService(ThrottlingRecordService throttlingRecordService) {
		this.throttlingRecordService = throttlingRecordService;
	}

	protected Environment getEnvironment() {
		return environment;
	}

	protected void setEnvironment(Environment environment) {
		this.environment = environment;
	}

}
