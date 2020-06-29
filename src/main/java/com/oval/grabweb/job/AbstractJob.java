package com.oval.grabweb.job;

import java.util.List;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.oval.grabweb.action.ActionFacade;
import com.oval.grabweb.action.ClientFactory;
import com.oval.grabweb.constant.Constant;

import cn.hutool.core.io.FileUtil;

public  class AbstractJob implements Job {
	private static Logger logger = Logger.getLogger(AbstractJob.class);
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
 	    List<JobInfo>  jobInfoList = (List<JobInfo>) dataMap.get("jobInfoList"); 
 	    FileUtil.mkParentDirs(Constant.DIR_PRIFIX);
		try {
			ActionFacade.doActions(jobInfoList);
		} catch (Exception e) {
			logger.error(Constant.END_DATE+"日报执行过程中出错");
			e.printStackTrace();
		}
	}
}
