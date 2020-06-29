package com.oval.grabweb.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.ehcache.impl.internal.executor.ExecutorUtil;

import com.oval.grabweb.config.Config;
import com.oval.grabweb.constant.Constant;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;

/**
 * Hand类 1.控制所有的Paw
 * 
 * @author user
 *
 */
public class Hand {

	private Paw paw;

	private Page page;

	public Hand() {

	}

	public Hand(Customer customer) {
		this.page = new Page(customer);
	}

	public Paw getPaw() {
		return paw;
	}

	public void setPaw(Paw paw) {
		this.paw = paw;
	}

	public void exec() {
		// 1.登录
		paw.login(page);
		Customer customer = page.getCustomer();
		System.out.println("-----------" + page.isLogin());
		ExecutorService e = Executors.newFixedThreadPool(3);
		// 2.登录成功后
		if (page.isLogin()) {
			try {
				CompletableFuture.runAsync(() -> {
					paw.stock(page);
					String fileName = customer.getFileName().replace("{type}", Constant.STOCK);
					List<Integer> l = paw.titleNo();
					List<List<String>> data = new ArrayList<>();
					data.add(Config.stockHead.get(l.get(0)));
					data.addAll((List<List<String>>) page.getStock());
					ExcelWriter writer = ExcelUtil.getWriter(fileName);
					writer.write(data, true);
					writer.close();
					page.getStatus().set(0, true);
				}, e);

				CompletableFuture.runAsync(() -> {
					paw.sale(page);
					String fileName = customer.getFileName().replace("{type}", Constant.SALE);
					List<Integer> l = paw.titleNo();
					List<List<String>> data = new ArrayList<>();
					data.add(Config.saleHead.get(l.get(0)));
					data.addAll((List<List<String>>) page.getSale());
					ExcelWriter writer = ExcelUtil.getWriter(fileName);
					writer.write(data, true);
					writer.close();
					page.getStatus().set(1, true);
				}, e);

				CompletableFuture.runAsync(() -> {
					paw.purchase(page);
					String fileName = customer.getFileName().replace("{type}", Constant.PURCHASE);
					List<Integer> l = paw.titleNo();
					List<List<String>> data = new ArrayList<>();
					data.add(Config.purchaseHead.get(l.get(0)));
					data.addAll((List<List<String>>) page.getPurchase());
					ExcelWriter writer = ExcelUtil.getWriter(fileName);
					writer.write(data, true);
					writer.close();
					page.getStatus().set(2, true);
				}, e);
				e.shutdown();
			} catch (Exception e1) {
				e1.printStackTrace();
			}finally {
				try {
					page.getClient().close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

}
