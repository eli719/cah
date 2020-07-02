package com.oval.grabweb.job;

import java.io.File;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cn.hutool.core.io.FileUtil;
/**
 * 	定时拷贝任务
 * @author eli
 *
 */
public class CopyFileJob implements Job {

	private static Logger logger = Logger.getLogger(CopyFileJob.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		String rootSrc = dataMap.getString("rootSrc");
		String rootDest = dataMap.getString("rootDest");
		doCopy(rootSrc, rootDest);
		doDelete(rootSrc);
	}

	private static void doCopy(String rootSrc, String rootDest) {
		FileUtil.copyContent(new File(rootSrc), new File(rootDest), true);
	}

	private static void doDelete(String rootSrc) {
		boolean b = FileUtil.del(rootSrc);
		if (b) {
			logger.info("删除" + rootSrc + "成功");
		} else {
			logger.info("删除" + rootSrc + "失败");
		}
	}

	public static void main(String[] args) throws IOException {
		// auto->bak/auto
//		FileUtils.copyDirectory(new File("d:/XJPFile/auto/"),new File("d:/XJPFile/bak"),true);
		// auto/..->bak/..
		FileUtil.copyContent(new File("d:/XJPFile/auto/"), new File("d:/XJPFile/bak"), true);
		// auto/../...xls->/bak/...xls
//		FileUtil.copyFilesFromDir(new File("d:/XJPFile/auto/"),new File("d:/XJPFile/bak"),true);
	}
}