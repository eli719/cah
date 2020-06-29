/**   
* @Company: LuxonData 
* @Title: StockInfo.java 
* @Package com.oval.grabweb.bean 
* @Description: TODO
* @author yaokaichang  
* @date 2015-1-28 下午03:30:39 
* @version V1.0   
*/ 
package com.oval.grabweb.bean;

/** 
 * @ClassName: StockInfo 
 * @Description: TODO 
 * @author yaokaichang 
 * @date 2015-1-28 下午03:30:39  
 */
public class StockInfo {
	protected static String column_speractor = "!!";
	String inventoryDate =""; //库存日期
	String productCode =""; //产品编码
	String productName =""; //产品名称
	String productSpec =""; //产品规格
	String quantity =""; //数量
	String productNumber =""; //产品批号
	String unit=""; //计量单位
	String effectiveDate=""; //有效日期
	String rechargeDate=""; //补量日期

	/** 
	 * 
	 * @return inventoryDate  库存日期
	 */
	public String getInventoryDate() {
		return inventoryDate;
	}
	/** 
	 * 
	 * @param inventoryDate 库存日期
	 */
	public void setInventoryDate(String inventoryDate) {
		this.inventoryDate = inventoryDate.trim().replace("&nbsp;", "");
	}
	/** 
	 * @return productCode 产品编码
	 */
	public String getProductCode() {
		return productCode;
	}
	/** 
	 * @param productCode 产品编码
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
	 * @param productName 产品名称
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
	 * @param productSpec 产品规格
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
	 * @param quantity 数量
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity.trim().replace("&nbsp;", "");
	}
	/** 
	 * @return productNumber 产品批号
	 */
	public String getProductNumber() {
		return productNumber;
	}
	/** 
	 * @param productNumber 产品批号
	 */
	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber.trim().replace("&nbsp;", "");
	}
	/** 
	 * @return unit 计量单位
	 */
	public String getUnit() {
		return unit;
	}
	/** 
	 * @param unit 计量单位
	 */
	public void setUnit(String unit) {
		this.unit = unit.trim().replace("&nbsp;", "");
	}
	/** 
	 * @return effectiveDate 有效日期
	 */
	public String getEffectiveDate() {
		return effectiveDate;
	}
	/** 
	 * @param effectiveDate 有效日期
	 */
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate.trim().replace("&nbsp;", "");
	}
	/** 
	 * @return rechargeDate 补量日期
	 */
	public String getRechargeDate() {
		return rechargeDate;
	}
	/** 
	 * @param rechargeDate 补量日期
	 */
	public void setRechargeDate(String rechargeDate) {
		this.rechargeDate = rechargeDate.trim().replace("&nbsp;", "");
	}
	
	public String joinStr(){
		StringBuffer str = new StringBuffer();
		str.append(inventoryDate).append(column_speractor);//库存日期
		str.append(productCode).append(column_speractor);//产品编码
		str.append(productName).append(column_speractor);//产品名称
		str.append(productSpec).append(column_speractor);//产品规格
		str.append(quantity).append(column_speractor);//数量
		str.append(productNumber).append(column_speractor);//产品批号
		str.append(unit).append(column_speractor);//计量单位
		str.append(effectiveDate).append(column_speractor);//有效日期
		str.append(rechargeDate).append(column_speractor);//补量日期

		
		return str.toString();
	}
}
