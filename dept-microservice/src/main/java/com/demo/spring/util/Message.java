package com.demo.spring.util;

public class Message {
	private String status;
	private String body;

	public Message() {
		// TODO Auto-generated constructor stub
	}

	public Message(String status, String body) {
		this.status = status;
		this.body = body;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
