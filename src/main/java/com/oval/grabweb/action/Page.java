package com.oval.grabweb.action;
/**
 * Page类
 * 1.保存登录成功的客户端信息
 * 2.处理完成后的数据信息
 * @author eli
 *
 */

import java.util.ArrayList;
import java.util.List;

import org.apache.http.impl.client.CloseableHttpClient;

public class Page {
	
	private CloseableHttpClient client;
	
	private Customer customer;
	
	private Request request;
	
	private Response response;
	
	private boolean isLogin;
	
	private List<Boolean> status = new ArrayList<Boolean>();
	
	
	public List<Boolean> getStatus() {
		return status;
	}

	public void setStatus(List<Boolean> status) {
		this.status = status;
	}

	private Object stock;
	
	private Object sale;
	
	private Object purchase;

	public Page() {
		
	}
	
	public Page(Customer customer) {
		this.customer=customer;
		client = ClientFactory.createDefault();
		status.add(0, false);
		status.add(1, false);
		status.add(2, false);
	}

	public CloseableHttpClient getClient() {
		return client;
	}

	public void setClient(CloseableHttpClient client) {
		this.client = client;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	public Object getStock() {
		return stock;
	}

	public void setStock(Object stock) {
		this.stock = stock;
	}

	public Object getSale() {
		return sale;
	}

	public void setSale(Object sale) {
		this.sale = sale;
	}

	public Object getPurchase() {
		return purchase;
	}

	public void setPurchase(Object purchase) {
		this.purchase = purchase;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}


	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
