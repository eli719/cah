package com.oval.grabweb.config;

import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import com.oval.grabweb.bean.Customer;
import com.oval.grabweb.util.DateUtils;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
/**
 * 	配置类
 * @author eli
 *
 */
public class Config {

	public static String DIR_PRIFIX;


	public static String BAK_PRIFIX;

	public static String FILENAME_REGEX;

	public static String FILE_NAME;

	public static String BAKFILE_NAME;

	public final static Map<String, Customer> customers = new HashMap<String, Customer>();

	public static final List<List<String>> stockHead = new ArrayList<>();

	public static final List<List<String>> saleHead = new ArrayList<>();

	public static final List<List<String>> purchaseHead = new ArrayList<>();


	public Config() {
		init();
	}

	private void init() {
		getTitle();
		getCustomer();
	}

	/**
	 * 	读取配置文件获取所有商业信息并保存
	 */
	private void getCustomer() {
		URL u = this.getClass().getResource("/task.json");
		JSON j = cn.hutool.json.JSONUtil.readJSON(new File(u.getFile()), Charset.defaultCharset());
		JSONObject o = (JSONObject) j.getByPath("templete");
		Config.FILENAME_REGEX=o.getStr("filePattern");
		Config.DIR_PRIFIX = o.getStr("directory") + DateUtils.yesterday() + "/";
		Config.BAK_PRIFIX = o.getStr("bakDir") + DateUtils.yesterday() + "/";
		Config.FILE_NAME=(DIR_PRIFIX + FILENAME_REGEX + ".xls");
		Config.BAKFILE_NAME=(BAK_PRIFIX + FILENAME_REGEX + ".xls");
		JSONArray array = (JSONArray) j.getByPath("customers");
		for (int i = 0; i < array.size(); i++) {
			JSONObject cu = (JSONObject) array.get(i);
			Customer c = JSONUtil.toBean(cu, Customer.class);
			if (c.isMerge()) {
				c.setFileName(mergeDir(DIR_PRIFIX + FILENAME_REGEX, c.getOrgCode(), c.getOrgName()));
			} else {
				c.setFileName(getFileName(FILE_NAME, c.getOrgCode(), c.getOrgName()));
			}
			customers.put(c.getOrgCode(), c);
		}
	}
	
	/**
	 * 	生成合并文件夹名称
	 * @param fileName 文件夹名
	 * @param orgCode
	 * @param orgName
	 * @return
	 */
	public static String mergeDir(String fileName, String orgCode, String orgName) {
		if (!StringUtils.isEmpty(fileName)) {
			fileName = fileName.replace("{orgCode}", orgCode);
			fileName = fileName.replace("{orgName}", orgName);
			fileName = fileName.replace("_{yyyyMMdd}", "");
			fileName = fileName.replace("{type}", "");
		}
		return fileName;
	}
	
	/**
	 * 	生成目标文件名
	 * @param fileName
	 * @param orgCode
	 * @param orgName
	 * @return
	 */
	public static String getFileName(String fileName, String orgCode, String orgName) {
		if (!StringUtils.isEmpty(fileName)) {
			fileName = fileName.replace("{orgCode}", orgCode);
			fileName = fileName.replace("{orgName}", orgName);
			fileName = fileName.replace("{yyyyMMdd}", DateUtils.changeFormat(DateUtils.yesterday(), "yyyyMMdd"));
		}
		return fileName;
	}

	/**
	 * 	获取表头
	 */
	@SuppressWarnings("unchecked")
	private void getTitle() {
		URL u = this.getClass().getResource("/title.json");
		JSON j = cn.hutool.json.JSONUtil.readJSON(new File(u.getFile()), Charset.defaultCharset());
		JSONArray s = (JSONArray) j.getByPath("stock");
		for (Object o : s) {
			stockHead.add((List<String>) o);
		}
		s = (JSONArray) j.getByPath("sale");
		for (Object o : s) {
			saleHead.add((List<String>) o);
		}

		s = (JSONArray) j.getByPath("purchase");
		for (Object o : s) {
			purchaseHead.add((List<String>) o);
		}
	}

	public static Map<String, Customer> getCustomers() {
		return customers;
	}


}
