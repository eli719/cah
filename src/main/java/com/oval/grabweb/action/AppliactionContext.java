package com.oval.grabweb.action;

import java.util.ArrayList;
import java.util.List;

import com.oval.grabweb.config.Config;

/**
 * AppliactionContext类 1.全局配置信息
 * 
 * @author user
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
		paws.add(new Code1234Impl());
		paws.add(new Code2345Impl());
		for (Paw paw : paws) {
			Crawler.create(paw).run();
		}
		
	}
}
