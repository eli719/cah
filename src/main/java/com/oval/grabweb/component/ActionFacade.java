package com.oval.grabweb.component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.log4j.Logger;
import com.oval.grabweb.job.JobInfo;

/**
 * 	任务管理类
 * @author eli
 *
 */
public class ActionFacade {
	private static Logger logger = Logger.getLogger(ActionFacade.class);
	
	public static List<Thread> runningThreads = new ArrayList<Thread>(); 
	public static int dealerlength=0;
	public static int runnsumlength=0;
	
	/**
	 * 
	 * @param jobInfoList 任务列表
	 * @throws Exception
	 */
	public static void doActions(List<JobInfo> jobInfoList) throws Exception {
		
		dealerlength=jobInfoList.size()+1;
		
		//根据任务数量构建线程并分配给线程池
		TaskThread[] threads = new TaskThread[jobInfoList.size()];
		ExecutorService executor = Executors.newFixedThreadPool(20);
		runnsumlength=0;
		Paw paw = null ;
		for (int i = 0; i < jobInfoList.size(); i++) {
			JobInfo info = jobInfoList.get(i);
        	String className = "com.oval.grabweb.action."+"Code"+info.getOrgCode()+"Impl";
        	paw= (Paw) Class.forName(className).newInstance();
        	threads[i] = new TaskThread(paw);
		}
		
		for (int j = 0; j < threads.length; j++) {
			executor.execute(threads[j]);
			Thread.sleep(35000);
		}
		logger.info("关闭");
		executor.shutdown();
	}
	
	static class TaskThread extends Thread {
		private final Paw paw;

		public TaskThread(Paw paw) {
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
				logger.info(runningThreads.size());
				logger.info("执行完成的经销商:"+runnsumlength);
				logger.info("总的经销商:"+(dealerlength-1));
				if((runningThreads.size()==0) && (runnsumlength==dealerlength-1)){				
					logger.info("任务执行完毕");
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
