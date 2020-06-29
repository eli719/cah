/**   
* @Company: LuxonData 
* @Title: jobInfo.java 
* @Package com.oval.grabweb.job 
* @Description: TODO
* @author yaokaichang  
* @date 2015-1-27 下午03:15:28 
* @version V1.0   
*/ 
package com.oval.grabweb.job;

import java.util.HashMap;
import java.util.Map;

public class JobInfo {
	
	private String orgCode;
	
	private String orgName;
	
	private boolean merge;
	
	private Map<String, String> params = new HashMap<String, String>();
	
	private String execTime;

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public boolean isMerge() {
		return merge;
	}

	public void setMerge(boolean merge) {
		this.merge = merge;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public String getExecTime() {
		return execTime;
	}

	public void setExecTime(String execTime) {
		this.execTime = execTime;
	}
	
	
}
