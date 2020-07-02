package com.oval.grabweb.component;

import java.util.List;

import com.oval.grabweb.config.Config;

/**
 * Paw接口
 * 1.抓取所需数据
 * @author eli
 *
 */
public interface Paw {

	public List<Integer> titleNo();
	
	public boolean login(Page page);
	
	public Page stock(Page page);
	
	public Page sale(Page page);
	
	public Page purchase(Page page);
}
