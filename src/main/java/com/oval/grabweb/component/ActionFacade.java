package com.oval.grabweb.component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.log4j.Logger;

import com.alibaba.excel.EasyExcelFactory;
import com.oval.grabweb.config.Config;
import com.oval.grabweb.constant.Constant;
import com.oval.grabweb.job.JobInfo;
import com.oval.grabweb.util.DateUtils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelUtil;

public class ActionFacade {
	private static Logger logger = Logger.getLogger(ActionFacade.class);
	public static List<Thread> runningThreads = new ArrayList<Thread>(); 
	public static int dealerlength=0;
	public static int runnsumlength=0;
	
	public static void doActions(List<JobInfo> jobInfoList) throws Exception {
		dealerlength=jobInfoList.size()+1;
		GetThread[] threads = new GetThread[jobInfoList.size()];
		ExecutorService executor = Executors.newFixedThreadPool(3);
		runnsumlength=0;
		Paw paw = null ;
		for (int i = 0; i < jobInfoList.size(); i++) {
			JobInfo info = jobInfoList.get(i);
        	String className = "com.oval.grabweb.action."+"Code"+info.getOrgCode()+"Impl";
        	paw= (Paw) Class.forName(className).newInstance();
        	threads[i] = new GetThread(paw);
		}
		
		for (int j = 0; j < threads.length; j++) {
			executor.execute(threads[j]);
			Thread.sleep(35000);
		}
		System.out.println("关闭");
		executor.shutdown();
	}
	

	
	



	static class GetThread extends Thread {
		private final Paw paw;

		public GetThread(Paw paw) {
			this.paw = paw;
		}
		public void run() {
				regist(this);				
					try {
						Crawler.create(paw).run();
					} catch (Exception e) {
						logger.error(e.getMessage());
					}					
				unRegist(this);
				System.out.println(runningThreads.size());
				System.out.println("执行完成的经销商:"+runnsumlength);
				System.out.println("总的经销商:"+(dealerlength-1));
				if((runningThreads.size()==0) && (runnsumlength==dealerlength-1)){				
					System.out.println("任务执行完毕");
				}				
		}
		
		public void regist(Thread t){   
			    synchronized(runningThreads){ 
			    	runnsumlength=runnsumlength+1;
			        runningThreads.add(t);   
			   }   
			}   
		public void unRegist(Thread t){   
			    synchronized(runningThreads){    
			       runningThreads.remove(t);   
		    }   
		} 		
	}	
	

}
