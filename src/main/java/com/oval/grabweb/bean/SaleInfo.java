/**   
* @Company: LuxonData 
* @Title: SaleInfo.java 
* @Package com.oval.grabweb.bean 
* @Description: TODO
* @author yaokaichang  
* @date 2015-1-28 下午03:30:54 
* @version V1.0   
*/ 
package com.oval.grabweb.bean;

/** 
 * @ClassName: SaleInfo 
 * @Description: TODO 
 * @author yaokaichang 
 * @date 2015-1-28 下午03:30:54  
 */
public class SaleInfo {
	
	protected static String column_speractor = "!!";
	String customerCode =""; //客户编码
	String customerName =""; //客户名称
	String productCode ="";//产品编码
	String productName ="";//产品名称
	String productSpec =""; //产品规格
	String quantity =""; //数量
	String saleDate ="";//销售日期
	String salePrice ="";//销售单价
	String productNumber ="";//批号
	String effectiveDate=""; //有效日期
	String manufacturers="";//生产厂商
	String unit=""; 	//单位
	String dosageForms =""; //剂型
	String productionDate="";//生产日期
	String deliveryAddress="";//收货地址
	String deliveryTel="";//收货方电话
	String deliveryPerson="";//收货方联系人
	String billNumber="";//单据代码
	String billType="";//单据类型
	String isRecharge="";//是否补量
	String rechargeReason="";//补量原因
	String rechargeDate=""; //补量日期
	String lilAccount;//子结算账户名称	
	
	/** 
	 * @return lilAccount 子结算账户名称
	 */
	public String getlilAccount() {
		return lilAccount;
	}
	
	public void setlilAccount(String lilAccount) {
		 this.lilAccount= lilAccount.trim().replace("&nbsp;", "");
	}
	/** 
	 * @return customerCode 客户编码
	 */
	public String getCustomerCode() {
		return customerCode;
	}
	/** 
	 * @param customerCode 客户编码
	 */
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode.trim().replace("&nbsp;", "");
	}
	/** 
	 * @return customerName 客户名称
	 */
	public String getCustomerName() {
		return customerName;
	}
	/** 
	 * @param customerName 客户名称
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName.trim().replace("&nbsp;", "");
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
	 * @return saleDate 销售日期
	 */
	public String getSaleDate() {
		return saleDate;
	}
	/** 
	 * @param saleDate 销售日期
	 */
	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate.trim().replace("&nbsp;", "");
	}
	/** 
	 * @return salePrice 销售单价
	 */
	public String getSalePrice() {
		return salePrice;
	}
	/** 
	 * @param salePrice 销售单价
	 */
	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice.trim().replace("&nbsp;", "");
	}
	/** 
	 * @return productNumber 批号
	 */
	public String getProductNumber() {
		return productNumber;
	}
	/** 
	 * @param productNumber 批号
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
	 * @param effectiveDate 有效日期
	 */
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate.trim().replace("&nbsp;", "");
	}
	/** 
	 * @return manufacturers 生产厂商
	 */
	public String getManufacturers() {
		return manufacturers;
	}
	/** 
	 * @param manufacturers 生产厂商
	 */
	public void setManufacturers(String manufacturers) {
		this.manufacturers = manufacturers.trim().replace("&nbsp;", "");
	}
	/** 
	 * @return unit 单位
	 */
	public String getUnit() {
		return unit;
	}
	/** 
	 * @param unit 单位
	 */
	public void setUnit(String unit) {
		this.unit = unit.trim().replace("&nbsp;", "");
	}
	/** 
	 * @return dosageForms 剂型
	 */
	public String getDosageForms() {
		return dosageForms;
	}
	/** 
	 * @param dosageForms 剂型
	 */
	public void setDosageForms(String dosageForms) {
		this.dosageForms = dosageForms.trim().replace("&nbsp;", "");
	}
	/** 
	 * @return productionDate 生产日期
	 */
	public String getProductionDate() {
		return productionDate;
	}
	/** 
	 * @param productionDate 生产日期
	 */
	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate.trim().replace("&nbsp;", "");
	}
	/** 
	 * @return deliveryAddress 收货地址
	 */
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	/** 
	 * @param deliveryAddress 收货地址
	 */
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress.trim().replace("&nbsp;", "");
	}
	/** 
	 * @return deliveryTel 收货方电话
	 */
	public String getDeliveryTel() {
		return deliveryTel;
	}
	/** 
	 * @param deliveryTel 收货方电话
	 */
	public void setDeliveryTel(String deliveryTel) {
		this.deliveryTel = deliveryTel.trim().replace("&nbsp;", "");
	}
	/** 
	 * @return deliveryPerson 收货方联系人
	 */
	public String getDeliveryPerson() {
		return deliveryPerson;
	}
	/** 
	 * @param deliveryPerson 收货方联系人
	 */
	public void setDeliveryPerson(String deliveryPerson) {
		this.deliveryPerson = deliveryPerson.trim().replace("&nbsp;", "");
	}
	/** 
	 * @return billNumber 单据代码
	 */
	public String getBillNumber() {
		return billNumber;
	}
	/** 
	 * @param billNumber 单据代码
	 */
	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber.trim().replace("&nbsp;", "");
	}
	/** 
	 * @return billType 单据类型
	 */
	public String getBillType() {
		return billType;
	}
	/** 
	 * @param billType 单据类型
	 */
	public void setBillType(String billType) {
		this.billType = billType.trim().replace("&nbsp;", "");
	}
	/** 
	 * @return isRecharge 是否补量
	 */
	public String getIsRecharge() {
		return isRecharge;
	}
	/** 
	 * @param isRecharge 是否补量
	 */
	public void setIsRecharge(String isRecharge) {
		this.isRecharge = isRecharge.trim().replace("&nbsp;", "");
	}
	/** 
	 * @return rechargeReason 补量原因
	 */
	public String getRechargeReason() {
		return rechargeReason;
	}
	/** 
	 * @param rechargeReason 补量原因
	 */
	public void setRechargeReason(String rechargeReason) {
		this.rechargeReason = rechargeReason.trim().replace("&nbsp;", "");
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
		str.append(customerCode).append(column_speractor); //客户编码
		str.append(customerName).append(column_speractor); //客户名称
		str.append(productCode).append(column_speractor);//产品编码
		str.append(productName).append(column_speractor);//产品名称
		str.append(productSpec).append(column_speractor); //产品规格
		str.append(quantity).append(column_speractor); //数量
		str.append(saleDate).append(column_speractor);//销售日期
		str.append(salePrice).append(column_speractor);//销售单价
		str.append(productNumber).append(column_speractor);//批号
		str.append(effectiveDate).append(column_speractor); //有效日期
		str.append(manufacturers).append(column_speractor);//生产厂商
		str.append(unit).append(column_speractor); //单位
		str.append(dosageForms).append(column_speractor); //剂型
		str.append(productionDate).append(column_speractor);//生产日期
		str.append(deliveryAddress).append(column_speractor);//收货地址
		str.append(deliveryTel).append(column_speractor);//收货方电话
		str.append(deliveryPerson).append(column_speractor);//收货方联系人
		str.append(billNumber).append(column_speractor);//单据代码
		str.append(billType).append(column_speractor);//单据类型
		str.append(isRecharge).append(column_speractor);//是否补量
		str.append(rechargeReason).append(column_speractor);//补量原因
		str.append(rechargeDate).append(column_speractor); //补量日期
		str.append(lilAccount).append(column_speractor); //补量日期
		return str.toString();
	}
}
