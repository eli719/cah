package com.oval.grabweb.listener;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.oval.grabweb.bean.Customer;
import com.oval.grabweb.config.Config;
import com.oval.grabweb.job.AbstractJob;
import com.oval.grabweb.job.CopyFileJob;
import com.oval.grabweb.job.JobInfo;

import cn.hutool.core.io.FileUtil;

public class StartupListener implements ServletContextListener {
	
	private static Logger logger = Logger.getLogger(StartupListener.class);

    public StartupListener() {
    	Config config = new Config(); 
    }
    
    @Override
    public void contextInitialized(ServletContextEvent event) {
    	ServletContext context = event.getServletContext();
    	String rootPath = context.getRealPath("/");
    	String verifyStorePath = rootPath + "verifycode";
    	try {
    		FileUtils.forceMkdir(new File(verifyStorePath));
		} catch (Exception e) {
			logger.error("create verify code store path error!");
			e.printStackTrace();
		}
    	//获取定时器信息
		Map<String,List<JobInfo>> mapActions= null;
		try {
			mapActions = getActions(); 
		} catch (Exception e) {
			logger.error("action.properties error!");
			e.printStackTrace();
		} 
    	String rootSrc = context.getInitParameter("FileDir");
    	String copyExpr = context.getInitParameter("copyExpr");
    	rootSrc = StringUtils.isEmpty(rootSrc) ? "d:" : rootSrc;
    	FileUtil.touch(rootSrc);
		try {
			Iterator<String> it = mapActions.keySet().iterator();
			Scheduler sche = StdSchedulerFactory.getDefaultScheduler();
			while (it.hasNext()) {
				String dailyExpr =  it.next();	
				List<JobInfo> jobInfoList = mapActions.get(dailyExpr);	
		        event.getServletContext().setAttribute("JobScheduler", sche);
		        createJob(sche, rootSrc,jobInfoList,dailyExpr); 
			}
			createCopyJob(sche, rootSrc, rootSrc, copyExpr);
		} catch (SchedulerException e) {
			logger.error("生成定时任务出错：" + e.getMessage());
		}
    }

    public void contextDestroyed(ServletContextEvent event) {
		try {
			Scheduler sche = (Scheduler)event.getServletContext().getAttribute("JobScheduler");
			sche.shutdown();
			event.getServletContext().removeAttribute("JobScheduler");
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

    }
    
	private static void createJob(Scheduler sche,String dir,List<JobInfo> jobInfoList,String dailyExpr){
		JobDetail jobDetail = JobBuilder.newJob(AbstractJob.class).build();
		JobDataMap dataMap = jobDetail.getJobDataMap();
		dataMap.put("jobInfoList", jobInfoList);		
		CronTrigger cronTrigger= TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(dailyExpr)).build();
		try {
			sche.scheduleJob(jobDetail, cronTrigger);
			sche.start();
		} catch (Exception e) {
			logger.error("生成日报定时任务出错：" + e.getMessage());
		}
	}
	
	private static void createCopyJob(Scheduler sche,String rootSrc,String rootDest, String expr){
		JobDetail jobDetail = JobBuilder.newJob(CopyFileJob.class).build();
		JobDataMap dataMap = jobDetail.getJobDataMap();
		dataMap.put("rootSrc", rootSrc);		
		dataMap.put("rootDest", rootDest);
		CronTrigger cronTrigger= TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(expr)).build();
		try {
			sche.scheduleJob(jobDetail, cronTrigger);
			sche.start();
		} catch (Exception e) {
			logger.error("生成文件复制定时任务出错：" + e.getMessage());
		}
	}
	
	
	
	private  Map<String,List<JobInfo>> getActions(){
		Map<String,Customer> customers = Config.getCustomers();
		Map<String,List<JobInfo>> map = new HashMap<String,List<JobInfo>>();
        List<JobInfo> list = null;
        for(Customer customer:customers.values()) {
        	JobInfo jobInfo = new JobInfo(); 
        	jobInfo.setOrgCode(customer.getOrgCode());
        	jobInfo.setOrgName(customer.getOrgName());
        	jobInfo.setExecTime(customer.getExecTime());
        	jobInfo.setParams(customer.getParams());
        	jobInfo.setFileName(customer.getFileName());
        	
        	// 同一时间启动的事务放在一起
    		if(map.containsKey(customer.getExecTime())){
    			list = map.get(customer.getExecTime());
    			list.add(jobInfo);
    			map.put(customer.getExecTime(),list);
    		} else {
    			list = new ArrayList<JobInfo>();
    			list.add(jobInfo);
    			map.put(customer.getExecTime(),list);
    		}
        }
		return map;
	}
}
