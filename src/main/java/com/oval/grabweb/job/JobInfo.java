package com.oval.grabweb.job;

import java.util.HashMap;
import java.util.Map;

/**
 * 	任务实体类
 * @author eli
 *
 */
public class JobInfo {
	
	private String orgCode;
	
	private String orgName;
	
	private boolean merge;
	
	private Map<String, String> params = new HashMap<String, String>();
	
	private String execTime;
	
	private String fileName;

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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
