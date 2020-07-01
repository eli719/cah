package com.oval.grabweb.component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.oval.grabweb.constant.Constant;
import com.oval.grabweb.util.DateUtils;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;

public abstract class AbstractAction {

	protected Logger logger = Logger.getLogger(AbstractAction.class);

	protected boolean ismerge = false;

	public boolean isIsmerge() {
		return ismerge;
	}

	public void setIsmerge(boolean ismerge) {
		this.ismerge = ismerge;
	}

	protected abstract String[] createPurchas(int paramInt);

	protected abstract String[] createStock(int paramInt);

	protected abstract String[] createSale(int paramInt);

	protected abstract void login(HttpClient paramHttpClient, Map<String, String> paramMap) throws Exception;

	protected abstract String[] getStock(HttpClient paramHttpClient, String paramString) throws Exception;

	protected abstract String[] getPurchase(HttpClient paramHttpClient, String paramString) throws Exception;

	protected abstract String[] getSale(HttpClient paramHttpClient, String paramString) throws Exception;

	public boolean HasExistsFile(String orgCode, String orgName) {

		boolean IsExistStockFile = checkFile(orgCode, orgName, Constant.STOCK);

		boolean IsExistSaleFile = checkFile(orgCode, orgName, Constant.SALE);

		boolean IsExistPurchasFile = checkFile(orgCode, orgName, Constant.PURCHASE);

		boolean IsWebFile;
		if ((IsExistStockFile) && (IsExistSaleFile) && (IsExistPurchasFile))
			IsWebFile = true;
		else {
			IsWebFile = false;
		}
		return IsWebFile;
	}

	/**
	 * @param orgCode
	 * @param orgName
	 * @return
	 */
	private boolean checkFile(String orgCode, String orgName, String type) {

		String file = Constant.DIR_PRIFIX + "/" + type + orgCode + "_" + DateUtil.yesterday() + "_" + orgName + ".xls";
		String bakFile = Constant.BAK_PRIFIX + "/" + type + orgCode + "_" + DateUtil.yesterday() + "_" + orgName
				+ ".xls";
		boolean IsExistFile;
		if ((FileUtil.exist(file)) || (FileUtil.exist(bakFile)))
			IsExistFile = true;
		else {
			IsExistFile = false;
		}
		return IsExistFile;
	}

	public List<Boolean> HasExistsFileList(String orgCode, String orgName) {
		List<Boolean> list = new ArrayList<Boolean>();

		boolean IsExistStockFile = checkFile(orgCode, orgName, Constant.STOCK);

		boolean IsExistSaleFile = checkFile(orgCode, orgName, Constant.SALE);

		boolean IsExistPurchasFile = checkFile(orgCode, orgName, Constant.PURCHASE);

		list.add(IsExistStockFile);
		list.add(IsExistSaleFile);
		list.add(IsExistPurchasFile);

		return list;
	}

	private String getFileName(String fileName, String type, String orgCode, String orgName, String account) {
		return StringUtils.isEmpty(account) ? getFileName(fileName.replace("{account}", ""), type, orgCode, orgName)
				: "";
	}

	private String getFileName(String fileName, String type, String orgCode, String orgName) {
		if (!StringUtils.isEmpty(fileName)) {
			fileName = fileName.replace("{orgCode}", orgCode);
			fileName = fileName.replace("{orgName}", orgName);
			fileName = fileName.replace("{yyyyMMdd}", DateUtils.changeFormat(DateUtils.yesterday(), "yyyyMMdd"));
		}

		switch (type) {

		case Constant.STOCK:
			fileName = fileName.replace("{type}", Constant.STOCK);
			break;
		case Constant.SALE:
			fileName = fileName.replace("{type}", Constant.SALE);
			break;
		default:
			fileName = fileName.replace("{type}", Constant.PURCHASE);
			break;
		}
		return fileName;
	}

	public void exec(HttpClient client, Map<String, String> loginParams, String orgCode, String orgName,
			StringBuffer sb) throws Exception {

		String account = sb.toString();

		if (HasExistsFile(orgCode, orgName)) {
			return;
		}
		logger.info(orgName + "  " + DateUtil.yesterday() + "  日报");

		try {
			login(client, loginParams);
		} catch (Exception e) {
			logger.error(orgName + "的网站不可访问！");
			throw new Exception(e.getMessage());
		}

		createFile(client, orgCode, orgName, Constant.STOCK, account);

		createFile(client, orgCode, orgName, Constant.SALE, account);

		createFile(client, orgCode, orgName, Constant.PURCHASE, account);

		try {
			logger.info("记录抓取日志");
		} catch (Exception e) {
			logger.error("记录" + orgName + "抓取日志过程中发生错误！");
			throw new Exception(e.getMessage());
		}
		logger.info(orgName + "  " + DateUtil.yesterday() + "  日报完成");
		logger.info(
				"------------------------------------------------------------------------------------------------------");
	}

	/**
	 * @param client
	 * @param orgCode
	 * @param orgName
	 * @param type
	 * @param account
	 */
	private void createFile(HttpClient client, String orgCode, String orgName, String type, String account) {

		String fileName = null;
		String bakFileName = null;

		switch (type) {
		case Constant.STOCK:
			fileName = getFileName(Constant.FILE_NAME, Constant.STOCK, orgCode, orgName);
			bakFileName = getFileName(Constant.BAKFILE_NAME, Constant.STOCK, orgCode, orgName);
			break;
		case Constant.SALE:
			fileName = getFileName(Constant.FILE_NAME, Constant.SALE, orgCode, orgName);
			bakFileName = getFileName(Constant.BAKFILE_NAME, Constant.SALE, orgCode, orgName);
			break;
		default:
			fileName = getFileName(Constant.FILE_NAME, Constant.PURCHASE, orgCode, orgName);
			bakFileName = getFileName(Constant.BAKFILE_NAME, Constant.PURCHASE, orgCode, orgName);
			break;
		}
		try {

			if (!ismerge) {
				if ((FileUtil.exist(fileName)) || (FileUtil.exist(bakFileName))) {
					logger.info("文件已生成 ");
					return;
				}

				String[] data = null;

				switch (type) {
				case Constant.STOCK:
					data = getStock(client, orgName);
					break;
				case Constant.SALE:
					data = getSale(client, orgName);
					break;
				default:
					data = getPurchase(client, orgName);
					break;
				}

				if (data != null) {
					if (data.length == 1) {
						logger.info(orgName + DateUtil.yesterday() + "日的库存数据为空！");
					}
					FileUtil.appendLines(Arrays.asList(data), fileName, "utf-8");
					logger.info("生成库存 " + fileName);
				}

			} else {

				String childDir = null;
				String childFile = null;

				childDir = Constant.DIR_PRIFIX + "/" + orgCode.concat("_").concat(orgName);
				switch (type) {
				case Constant.STOCK:
					childFile = childDir + "/"
							+ getFileName(Constant.FILENAME_REGEX, Constant.STOCK, orgCode, orgName, account);
					break;
				case Constant.SALE:
					childFile = childDir + "/"
							+ getFileName(Constant.FILENAME_REGEX, Constant.SALE, orgCode, orgName, account);
					break;
				default:
					childFile = childDir + "/"
							+ getFileName(Constant.FILENAME_REGEX, Constant.PURCHASE, orgCode, orgName, account);
					break;
				}

				if ((FileUtil.exist(childFile))) {
					logger.info("文件已生成 ");
				}

				String[] data = null;

				switch (type) {
				case Constant.STOCK:
					data = getStock(client, orgName);
					break;
				case Constant.SALE:
					data = getSale(client, orgName);
					break;
				default:
					data = getPurchase(client, orgName);
					break;
				}

				if (data != null) {
					if (data.length != 1) {
						FileUtil.touch(childFile);
						FileUtil.appendLines(Arrays.asList(data), childFile, "utf-8");
						logger.info("生成 " + childFile);
						FileUtil.del(fileName);
						String[] sumInfo = null;
						switch (type) {
						case Constant.STOCK:
							sumInfo = getSumInfo(childDir, Constant.STOCK);
							break;
						case Constant.SALE:
							sumInfo = getSumInfo(childDir, Constant.SALE);
							break;
						default:
							sumInfo = getSumInfo(childDir, Constant.PURCHASE);
							break;
						}
						FileUtil.appendLines(Arrays.asList(sumInfo), fileName, "utf-8");
						logger.info("生成合并文件 " + sumInfo);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected String[] getSumInfo(String filepath, String character) throws Exception {
		List<String> list = new ArrayList<String>(); // 定义数据存储集合
		File file = new File(filepath); // 获取文件路下的所有文件
		String[] filelist = file.list();
		HSSFWorkbook wb = null;
		// 循环下列文件
		for (int i = 0; i < filelist.length; i++) {
			File readfile = new File(filepath + "\\" + filelist[i]);

			// 找出已V开头的excel文件character： V代表库存文件 S代表销售文件P伪采购
			if (readfile.getName().startsWith(character)) {
				FileInputStream fileInputStream;
				try {
					// 获取excel第一个页签的内容
					fileInputStream = new FileInputStream(readfile);
					wb = new HSSFWorkbook(fileInputStream);
					HSSFSheet sheet = wb.getSheetAt(0);
					// 第一个excel的表头需要获取， 其他的去掉不需要
					for (int j = list.size() > 0 ? 1 : 0; j <= sheet.getLastRowNum(); j++) {
						StringBuffer o = new StringBuffer();
						for (int p = 0; p < sheet.getRow(0).getLastCellNum(); p++) {// 以表头的列为准
							o.append(sheet.getRow(j).getCell(p)).append("!!");
						}

						list.add(o.toString().replace("null", ""));
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (wb != null) {
						wb.close();
					}
				}
			}
		}
		return list.toArray(new String[1]);
	}
}