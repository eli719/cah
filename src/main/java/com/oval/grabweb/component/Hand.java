package com.oval.grabweb.component;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.io.filefilter.FileFilterUtils;
import com.alibaba.excel.EasyExcelFactory;
import com.oval.grabweb.bean.Customer;
import com.oval.grabweb.config.Config;
import com.oval.grabweb.constant.Constant;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;

/**
 * Hand类 1.控制所有的Paw
 * 
 * @author eli
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

				CompletableFuture<Boolean> c1 = CompletableFuture.supplyAsync(() -> {
					paw.stock(page);

					if (customer.isMerge()) {
						String childDir = customer.getFileName().replace("{type}", "");
						String childFileName = childDir.replaceAll("ONE", "").replaceAll("TWO", "") + "/" + Config
								.getFileName(Config.getFILENAME_REGEX(), customer.getOrgCode(), customer.getOrgName())
								.replace("{type}", Constant.STOCK) + ".xls";
						normalCreateFiles(childFileName, customer, Constant.STOCK);
					} else {
						String fileName = customer.getFileName().replace("{type}", Constant.STOCK);

						normalCreateFiles(fileName, customer, Constant.STOCK);
					}
					return true;
				}, e);

				CompletableFuture<Boolean> c2 = CompletableFuture.supplyAsync(() -> {
					paw.sale(page);
					if (customer.isMerge()) {
						String childDir = customer.getFileName().replace("{type}", "");
						String childFileName = childDir.replaceAll("ONE", "").replaceAll("TWO", "") + "/" + Config
								.getFileName(Config.getFILENAME_REGEX(), customer.getOrgCode(), customer.getOrgName())
								.replace("{type}", Constant.SALE) + ".xls";

						normalCreateFiles(childFileName, customer, Constant.SALE);
					} else {
						String fileName = customer.getFileName().replace("{type}", Constant.SALE);

						normalCreateFiles(fileName, customer, Constant.SALE);
					}
					return true;
				}, e);

				CompletableFuture<Boolean> c3 = CompletableFuture.supplyAsync(() -> {
					paw.purchase(page);

					if (customer.isMerge()) {
						String childDir = customer.getFileName().replace("{type}", "");
						String childFileName = childDir.replaceAll("ONE", "").replaceAll("TWO", "") + "/" + Config
								.getFileName(Config.getFILENAME_REGEX(), customer.getOrgCode(), customer.getOrgName())
								.replace("{type}", Constant.PURCHASE) + ".xls";

						normalCreateFiles(childFileName, customer, Constant.PURCHASE);
					} else {
						String fileName = customer.getFileName().replace("{type}", Constant.PURCHASE);

						normalCreateFiles(fileName, customer, Constant.PURCHASE);
					}
					return true;
				}, e);

				CompletableFuture.allOf(c1, c2, c3).thenRunAsync(() -> {
					System.out.println("the last");
					if (customer.isMerge()) {
						String mergeDir = customer.getFileName().replaceAll("ONE", "").replaceAll("TWO", "");
						boolean b = FileUtil.exist(mergeDir);
						if (!b) {
							return;
						}
						if (FileUtil.isDirEmpty(new File(mergeDir))) {
							return;
						}

						createFiles(mergeDir, Constant.STOCK, customer);
						createFiles(mergeDir, Constant.SALE, customer);
						createFiles(mergeDir, Constant.PURCHASE, customer);
					}
				}, e).get();
				e.shutdown();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void normalCreateFiles(String fileName, Customer customer, String type) {
		
		if(FileUtil.exist(fileName)) {
			System.out.println(fileName+"---已存在！！");
			return;
		}
		
		List<Integer> l = paw.titleNo();
		List<List<String>> data = new ArrayList<>();
		switch (type) {
		case Constant.STOCK:
			data.add(Config.stockHead.get(l.get(0)));
			data.addAll((List<List<String>>) page.getStock());
			break;
		case Constant.SALE:
			data.add(Config.saleHead.get(l.get(0)));
			data.addAll((List<List<String>>) page.getSale());
			break;
		default:
			data.add(Config.purchaseHead.get(l.get(0)));
			data.addAll((List<List<String>>) page.getPurchase());
			break;
		}
		try {
			FileUtil.touch(fileName);
//			FileUtils.touch(new File(fileName));
//			EasyExcelFactory.write(fos).sheet(0).doWrite(data);//有红色警告 ：Cleaning up unclosed ZipFile for archive
			ExcelWriter writer=ExcelUtil.getWriter().setDestFile(new File(fileName));
			writer.write(data).flush();
		} catch (Exception eo) {
			eo.printStackTrace();
		}
		page.getStatus().set(0, true);
	}

	private static void createFiles(String mergeDir, String type, Customer customer) {
		List<File> files = FileUtil.loopFiles(new File(mergeDir), FileFilterUtils.prefixFileFilter(type));
		if (!(files.size() >= 2)) {
			return;
		}
		List<List<Object>> data = new ArrayList<List<Object>>();
		for (int i = 0; i < files.size(); i++) {
			if (i == 0) {
				List<List<Object>> s = ExcelUtil.getReader(files.get(i)).read(0);
				data.addAll(s);
			} else {
				List<List<Object>> s = ExcelUtil.getReader(files.get(i)).read(1);
				data.addAll(s);
			}
		}

		String sumFile = Config
				.getFileName(Config.getDIR_PRIFIX() + Config.getFILENAME_REGEX(),
						customer.getOrgCode().replaceAll("ONE", "").replaceAll("TWO", ""), customer.getOrgName())
				.replace("{type}", type) + ".xls";
		try (FileOutputStream fos = new FileOutputStream(sumFile)) {
			EasyExcelFactory.write(fos).sheet(0).doWrite(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
