package com.oval.grabweb.servlet;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.oval.grabweb.component.Crawler;
import com.oval.grabweb.component.Paw;

public class PageActionManager {

	private static final int MAX_NUM = 5;

	private static ExecutorService executor = Executors.newFixedThreadPool(MAX_NUM);;

	public static Set<String> currentTask = new HashSet<String>();

	private static PageActionManager taskManager = null;

	private PageActionManager() {
		
	}

	public static PageActionManager getInstance() {
		if (taskManager == null) {
			synchronized (PageActionManager.class) {
				if (taskManager == null) {
					return new PageActionManager();
				}
			}
		}
		return taskManager;
	}

	public static void execTask() throws Exception {
		TaskThread[] threads = new TaskThread[currentTask.size()];
		Paw paw = null;
		int i = 0;
		for (String orgCode : currentTask) {
//			CustomerVo customerVo = Config.getCustomerVos().get(orgCode);
			String className = "com.oval.grabweb.impl." + "Code" + orgCode + "Impl";
			paw = (Paw) Class.forName(className).newInstance();
			threads[i] = new TaskThread(paw);
			i++;
		}
		
		for (int j = 0; j < threads.length; j++) {
			executor.execute(threads[j]);
			Thread.sleep(30000);
		}
	}

	static class TaskThread extends Thread {
		private final Paw paw;

		public TaskThread(Paw paw) {
			this.paw = paw;
		}

		public void run() {
			try {
				System.out.println(Thread.currentThread().getName()+"-------------run------------");
				Crawler.create(paw).run();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void clear() {
		if(currentTask!=null&&currentTask.size()>0) {
			currentTask.clear();
		}
	}
}
