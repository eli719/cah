package com.oval.grabweb.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
/**
 *	 日期工具类
 * @author eli
 */
public class DateUtils {
	
	/**
	 * 返回与输入日期像相差几天的日期
	 * @param date 日期
	 * @param offset 第几天
	 * @return 日期转String
	 */
	public static String getDayAt(String date, int offset) {
		LocalDate d= LocalDate.parse(date,checkDateFormat(date));
		d=d.plus(offset, ChronoUnit.DAYS);
		return d.format(checkDateFormat(date));
	}
	/**
	 * 转换日期格式
	 * @param date 输入日期
	 * @param pattern 日期格式
	 * @return String类型
	 */
	public static String changeFormat(String date,String pattern) {
		return LocalDate.parse(date).format(DateTimeFormatter.ofPattern(pattern));
	}
	/**
	 * 当前日期
	 * @return String类型
	 */
	public static String now() {
		return LocalDate.now().toString();
	}
	/**
	 * 昨天
	 * @return String类型
	 */
	public static String yesterday() {
		return getDayAt(now(),-1);
	}
	/**
	 * 与今天相差一定天数的日期
	 * @param offset 相差天数，正数向后推，负数向前推
	 * @return String类型
	 */
	public static String offsetToday(int offset) {
		return getDayAt(now(),offset);
	}
	
	/**
	 * 检查日期的格式并返回
	 * @param date String类型的日期,支持yyyyMMdd、yyyy-MM-dd、yyyy/MM/dd、yyyy.MM.dd
	 * @return 返回日期格式
	 */
	public static DateTimeFormatter checkDateFormat(String date) {
		if(StringUtils.isEmpty(date)) {
			return null;
		}
		String regex = "^(?:(?!0000)[0-9]{4}([-/.]?)(?:(?:0?[1-9]|1[0-2])\\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\\1(?:29|30)|(?:0?[13578]|1[02])\\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-/.]?)0?2\\2(?:29))$";
		Pattern pattern = Pattern.compile(regex);
		if(!pattern.matcher(date).matches()) {
			return null;
		}
		if(date.contains("-")) {
			return DateTimeFormatter.ISO_DATE;
		}else if(date.contains("/")) {
			return DateTimeFormatter.ofPattern("yyyy/MM/dd");
		}else if(date.contains(".")) {
			return DateTimeFormatter.ofPattern("yyyy.MM.dd");
		}else {
			return DateTimeFormatter.BASIC_ISO_DATE;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(now());
		System.out.println(yesterday());
		System.out.println(offsetToday(1));
		System.out.println(changeFormat(offsetToday(1),"yyyyMMdd"));
	}
}
