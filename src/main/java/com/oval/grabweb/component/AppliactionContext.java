package com.oval.grabweb.component;

import java.util.ArrayList;
import java.util.List;

import com.oval.grabweb.config.Config;
import com.oval.grabweb.impl.Code1234ONEImpl;
import com.oval.grabweb.impl.Code1234TWOImpl;
import com.oval.grabweb.impl.Code2345Impl;

/**
 * AppliactionContext类 1.全局配置信息
 * 
 * @author eli
 *
 */
public class AppliactionContext {
	
	public AppliactionContext() {
		init();
	}
	
	private void init() {
		
	}

	public static void main(String[] args) {
		Config config = new Config(); 
		List<Paw> paws = new ArrayList<Paw>();
		paws.add(new Code1234ONEImpl());
		paws.add(new Code1234TWOImpl());
		paws.add(new Code2345Impl());
		for (Paw paw : paws) {
			Crawler.create(paw).run();
		}
		
	}
}
