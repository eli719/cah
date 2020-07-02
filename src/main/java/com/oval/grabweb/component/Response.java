package com.oval.grabweb.component;

import java.util.HashMap;
import java.util.Map;

/**
 * Response类
 * 1.保存一个响应的信息
 * @author eli
 *
 */
public class Response {
	
	private int statusCode;
	
	private Map<String,String> header = new HashMap<String, String>();
	
	private String body;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Map<String,String> getHeader() {
		return header;
	}

	public void setHeader(Map<String,String> header) {
		this.header = header;
	}
	
	
}
