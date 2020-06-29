package com.oval.grabweb.bean;

public class PurchasInfo {

	protected static String column_speractor = "!!";
	String purchasDate = ""; // 入库时间
	String supplierName = ""; // 供应商名称
	String productCode = ""; // 产品编码
	String productName = ""; // 产品名称
	String productSpec = ""; // 产品规格
	String quantity = ""; // 数量
	String productNumber = ""; // 产品批号
	String effectiveDate = ""; // 有效日期

	/**
	 * @return purchasDate 入库时间
	 */
	public String getPurchasDate() {
		return purchasDate;
	}

	/**
	 * @param purchasDate
	 *            入库时间
	 */
	public void setPurchasDate(String purchasDate) {
		this.purchasDate = purchasDate.trim().replace("&nbsp;", "");
	}

	/**
	 * @return supplierName 供应商名称
	 */
	public String getSupplierName() {
		return supplierName;
	}

	/**
	 * @param supplierName
	 *            供应商名称
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName.trim().replace("&nbsp;", "");
	}

	/**
	 * @return productCode 产品编码
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * @param productCode
	 *            产品编码
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode.trim().replace("&nbsp;", "");
	}

	/**
	 * @return productName 产品名称
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName
	 *            产品名称
	 */
	public void setProductName(String productName) {
		this.productName = productName.trim().replace("&nbsp;", "");
	}

	/**
	 * @return productSpec 产品规格
	 */
	public String getProductSpec() {
		return productSpec;
	}

	/**
	 * @param productSpec
	 *            产品规格
	 */
	public void setProductSpec(String productSpec) {
		this.productSpec = productSpec.trim().replace("&nbsp;", "");
	}

	/**
	 * @return quantity 数量
	 */
	public String getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            数量
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity.trim().replace("&nbsp;", "");
	}

	/**
	 * @return productNumber 批号
	 */
	public String getProductNumber() {
		return productNumber;
	}

	/**
	 * @param productNumber
	 *            批号
	 */
	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber.trim().replace("&nbsp;", "");
	}

	/**
	 * @return effectiveDate 有效日期
	 */
	public String getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * @param effectiveDate
	 *            有效日期
	 */
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate.trim().replace("&nbsp;", "");
	}

	public String joinStr() {
		StringBuffer str = new StringBuffer();
		str.append(purchasDate).append(column_speractor); // 入库时间
		str.append(supplierName).append(column_speractor);// 供应商
		str.append(productName).append(column_speractor);// 产品名称
		str.append(productSpec).append(column_speractor); // 产品规格
		str.append(quantity).append(column_speractor); // 数量
		str.append(productNumber).append(column_speractor);// 批号
		str.append(effectiveDate).append(column_speractor); // 有效日期
		str.append(productCode); // 产品编码

		return str.toString();
	}
}
