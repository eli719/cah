
package com.oval.grabweb.component;

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

import com.oval.grabweb.bean.Customer;
import com.oval.grabweb.vo.CustomerVo;

public class Page {
	
	private CloseableHttpClient client;
	
	private CustomerVo customerVo;
	
	private Request request;
	
	private Response response;
	
	
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
	
	public Page(CustomerVo customer) {
		this.customerVo=customer;
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


	public CustomerVo getCustomerVo() {
		return customerVo;
	}

	public void setCustomerVo(CustomerVo customerVo) {
		this.customerVo = customerVo;
	}
}
