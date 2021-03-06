package com.oval.grabweb.job;

import java.util.List;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.oval.grabweb.component.ActionFacade;
import com.oval.grabweb.config.Config;
import com.oval.grabweb.util.DateUtils;
import cn.hutool.core.io.FileUtil;
/**
 * 	定时任务类
 * @author eli
 *
 */
public  class AbstractJob implements Job {
	private static Logger logger = Logger.getLogger(AbstractJob.class);
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
 	    @SuppressWarnings("unchecked")
		List<JobInfo>  jobInfoList = (List<JobInfo>) dataMap.get("jobInfoList"); 
 	    FileUtil.mkParentDirs(Config.DIR_PRIFIX);
		try {
			ActionFacade.doActions(jobInfoList);
		} catch (Exception e) {
			logger.error(DateUtils.now()+"日报执行过程中出错");
			e.printStackTrace();
		}
	}
}
