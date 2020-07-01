package com.oval.grabweb.constant;

import com.oval.grabweb.util.DateUtils;
/**
 *	全局常量
 * @author eli
 *
 */
public interface Constant {

	String STOCK = "V";

	String SALE = "S";

	String PURCHASE = "P";

	String START_DATE = DateUtils.offsetToday(-60);

	String END_DATE = DateUtils.now();

	String DIR_PRIFIX = "D:/XJPFile/auto17/" + DateUtils.yesterday();

	String BAK_PRIFIX="";

	String FILENAME_REGEX="";

	String FILE_NAME = DIR_PRIFIX + FILENAME_REGEX;

	String BAKFILE_NAME = BAK_PRIFIX + FILENAME_REGEX;
}
