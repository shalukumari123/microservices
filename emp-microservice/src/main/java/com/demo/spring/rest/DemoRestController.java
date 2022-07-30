package com.demo.spring.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRestController {

	@GetMapping(value = "/greet",produces = MediaType.TEXT_PLAIN_VALUE)
	public String greet() {
		return "Welcome to REST Services";
	}
}
