package com.atypon.throttlingproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atypon.throttlingproject.annotation.Throttling;

/**
 * The Class TestController.
 */
@RestController()
@RequestMapping("/tests")
public class TestController {

	/**
	 * Gets the t1.
	 *
	 * @return the t1
	 */
	@GetMapping("/t1")
	@Throttling
	public String getT1() {
		return "throttled method";
	}

	/**
	 * Gets the t2.
	 *
	 * @return the t2
	 */
	@GetMapping("/t2")
	public String getT2() {
		return "non throttled method";
	}
	
	/**
	 * Gets the t3.
	 *
	 * @return the t3
	 */
	@GetMapping("/t3")
	@Throttling
	public String getT3() {
		return "throttled method";
	}

	/**
	 * Gets the t4.
	 *
	 * @return the t4
	 */
	@GetMapping("/t4")
	public String getT4() {
		return "non throttled method";
	}
}
