package com.oval.grabweb.vo;

import java.util.ArrayList;
import java.util.List;

import com.oval.grabweb.bean.Customer;

public class CustomerVo {
	private Customer customer;
	
	private List<Boolean> status;
	
	public CustomerVo() {
		this.status = new ArrayList<Boolean>();
		status.add(0, false);
		status.add(1, false);
		status.add(2, false);
	}
	

	public List<Boolean> getStatus() {
		return status;
	}

	public void setStatus(List<Boolean> status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
