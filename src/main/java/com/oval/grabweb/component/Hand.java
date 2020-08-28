package com.oval.grabweb.component;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.log4j.Logger;
import com.alibaba.excel.EasyExcelFactory;
import com.oval.grabweb.bean.Customer;
import com.oval.grabweb.config.Config;
import com.oval.grabweb.constant.Constant;
import com.oval.grabweb.vo.CustomerVo;

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
	
	private static Logger logger = Logger.getLogger(Hand.class);


	private Paw paw;

	private Page page;

	public Hand() {

	}

	public Hand(CustomerVo customerVo) {
		this.page = new Page(customerVo);
	}

	public Paw getPaw() {
		return paw;
	}

	public void setPaw(Paw paw) {
		this.paw = paw;
	}

	public void exec() {
		// 1.登录
		boolean isLogin = paw.login(page);
		Customer customer = page.getCustomerVo().getCustomer();
		logger.info("-----------" + isLogin);
		ExecutorService e = Executors.newFixedThreadPool(4);
		// 2.登录成功后，可以异步获取进销存数据
		if (isLogin) {
			try {
				//1.库存
				CompletableFuture<Boolean> c1 = CompletableFuture.supplyAsync(() -> {
					paw.stock(page);

					if (customer.isMerge()) {
						String childDir = customer.getFileName().replace("{type}", "");
						String childFileName = childDir.replaceAll("ONE", "").replaceAll("TWO", "") + "/" + Config
								.getFileName(Config.FILENAME_REGEX, customer.getOrgCode(), customer.getOrgName())
								.replace("{type}", Constant.STOCK) + ".xls";
						normalCreateFiles(childFileName, customer, Constant.STOCK);
					} else {
						String fileName = customer.getFileName().replace("{type}", Constant.STOCK);

						normalCreateFiles(fileName, customer, Constant.STOCK);
					}
					page.getCustomerVo().getStatus().set(0, true);
					return true;
				}, e);
				
				//2.销售
				CompletableFuture<Boolean> c2 = CompletableFuture.supplyAsync(() -> {
					paw.sale(page);
					if (customer.isMerge()) {
						String childDir = customer.getFileName().replace("{type}", "");
						String childFileName = childDir.replaceAll("ONE", "").replaceAll("TWO", "") + "/" + Config
								.getFileName(Config.FILENAME_REGEX, customer.getOrgCode(), customer.getOrgName())
								.replace("{type}", Constant.SALE) + ".xls";

						normalCreateFiles(childFileName, customer, Constant.SALE);
					} else {
						String fileName = customer.getFileName().replace("{type}", Constant.SALE);

						normalCreateFiles(fileName, customer, Constant.SALE);
					}
					page.getCustomerVo().getStatus().set(1, true);

					return true;
				}, e);

				//3.采购
				CompletableFuture<Boolean> c3 = CompletableFuture.supplyAsync(() -> {
					paw.purchase(page);

					if (customer.isMerge()) {
						String childDir = customer.getFileName().replace("{type}", "");
						String childFileName = childDir.replaceAll("ONE", "").replaceAll("TWO", "") + "/" + Config
								.getFileName(Config.FILENAME_REGEX, customer.getOrgCode(), customer.getOrgName())
								.replace("{type}", Constant.PURCHASE) + ".xls";

						normalCreateFiles(childFileName, customer, Constant.PURCHASE);
					} else {
						String fileName = customer.getFileName().replace("{type}", Constant.PURCHASE);

						normalCreateFiles(fileName, customer, Constant.PURCHASE);
					}
					page.getCustomerVo().getStatus().set(2, true);
					return true;
				}, e);
				
				//4.3个任务完成后，处理需要合并的多账号文件
				CompletableFuture.allOf(c1, c2, c3).thenRunAsync(() -> {
					logger.info("the last");
					//判断是否合并
					if (customer.isMerge()) {
						
						//获取合并文件夹名称
						String mergeDir = customer.getFileName().replaceAll("ONE", "").replaceAll("TWO", "");
						
						boolean b = FileUtil.exist(mergeDir);
						//合并文件夹要存在
						if (!b) {
							return;
						}
						//合并文件夹不能为空
						if (FileUtil.isDirEmpty(new File(mergeDir))) {
							return;
						}
						//创建子文件
						createFiles(mergeDir, Constant.STOCK, customer);
						createFiles(mergeDir, Constant.SALE, customer);
						createFiles(mergeDir, Constant.PURCHASE, customer);
					}
				}, e).get();
				
				//关闭线程池
				e.shutdown();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * 	生成目标文件
	 * @param fileName  目标文件名
	 * @param customer  商业
	 * @param type	 	文件进销存类型
	 */
	@SuppressWarnings("unchecked")
	private void normalCreateFiles(String fileName, Customer customer, String type) {
		//1.检查目标文件是否存在
		if(FileUtil.exist(fileName)) {
			logger.info(fileName+"---已存在！！");
			return;
		}
		
		//2.获取表头
		List<Integer> l = paw.titleNo();
		List<List<String>> data = new ArrayList<>();
		
		//3.根据类型获取数据
		switch (type) {
		case Constant.STOCK:
			data.add(Config.stockHead.get(l.get(0)));
			//List<List<String>>-->对应excel一行每个单元格的值
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
		
		//4.向目标文件写入数据
		
		try {
			FileUtil.touch(fileName);
//			FileUtils.touch(new File(fileName));
//			EasyExcelFactory.write(fos).sheet(0).doWrite(data);//有红色警告 ：Cleaning up unclosed ZipFile for archive
			ExcelWriter writer=ExcelUtil.getWriter().setDestFile(new File(fileName));
			writer.write(data).flush();
		} catch (Exception eo) {
			eo.printStackTrace();
		}
	}
	
	/**
	 * 	创建合并后的文件
	 * @param mergeDir 合并文件夹
	 * @param type	类型
	 * @param customer	商业
	 */
	private static void createFiles(String mergeDir, String type, Customer customer) {
		//1.先从合并的文件夹中获取所需类型的子文件
		
		List<File> files = FileUtil.loopFiles(new File(mergeDir), FileFilterUtils.prefixFileFilter(type));
		
		//2.单个类型子文件数量从2个起步代表起码2个账号合并
		if (!(files.size() >= 2)) {
			return;
		}
		//3.获取子文件数据
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
		//{type}{orgCode}_{yyyyMMdd}_{orgName}.xls
		String sumFile = Config
				.getFileName(Config.DIR_PRIFIX + Config.FILENAME_REGEX,
						customer.getOrgCode().replaceAll("ONE", "").replaceAll("TWO", ""), customer.getOrgName())
				.replace("{type}", type) + ".xls";
		try (FileOutputStream fos = new FileOutputStream(sumFile)) {
			EasyExcelFactory.write(fos).sheet(0).doWrite(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
