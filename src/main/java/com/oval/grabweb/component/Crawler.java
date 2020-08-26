package com.oval.grabweb.component;

import com.oval.grabweb.config.Config;
import com.oval.grabweb.vo.CustomerVo;

/**
 * Crawler类 1.核心类
 * 
 * @author eli
 *
 */
public class Crawler {
	
	private Hand hand;
	
	private Messager messager;

	public Crawler() {
		
	}

	public Crawler(Paw paw) {
		String name = paw.getClass().getSimpleName().replace("Impl", "").replace("Code", "");
		CustomerVo customerVo = new CustomerVo();
		customerVo.setCustomer(Config.getCustomers().get(name));
		this.hand = new Hand(customerVo);
		this.hand.setPaw(paw);
	}

	public static Crawler create(String url) {
		return new Crawler();
	}

	public static Crawler create(Paw paw) {
		return new Crawler(paw);
	}

	public void run() {
		this.hand.exec();
	}
	
}
