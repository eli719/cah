package com.oval.grabweb.action;

import java.util.Map;

import org.apache.http.Header;

/**
 * Request类
 * 1.保存一个请求的信息
 * @author user
 *
 */
public class Request {

	private String url;

	private Integer statusCode;

	private String method;

	private String body;

	private Map<String,String> header;

	public Request() {

	}

	public Request(String url) {
		this.url=url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
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
