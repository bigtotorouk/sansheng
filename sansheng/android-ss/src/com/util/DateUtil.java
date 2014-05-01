package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * create time：2013-3-19 下午7:46:58
 * 
 * @author retryu
 * @since JDK 1.6
 * @version 1.0 description：
 */
public class DateUtil {
	public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	static SimpleDateFormat shortFormat = new SimpleDateFormat("MM/dd");
	static SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy年MM月");
	public static Date now = new Date();
	
	
	 

	/**
	 * 
	 * 获得周的计数值，用来做周查询的时候用到，
	 * @param date
	 * @return
	 */
	public static int getWeekIndex(Date date) {
		int week = 0;
		try {
			String stand = "2005-01-03";
			Date standDate = format.parse(stand);
			long time = date.getTime() - standDate.getTime();
			week = (int) (time / 1000 / 60 / 60 / 24) / 7;
			System.out.print(week);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return week;
	}

	/**
	 * 
	 * 同上，只不过作月查询时候用到
	 * @param date
	 * @return
	 */
	public static int getMonthIndex(Date date) {
		int monthday;
		try {
			Date startDate = format.parse("2005-01-03");
			// 开始时间与今天相比较
			Date endDate = date;

			Calendar starCal = Calendar.getInstance();
			starCal.setTime(startDate);

			int sYear = starCal.get(Calendar.YEAR);
			int sMonth = starCal.get(Calendar.MONTH);
			int sDay = starCal.get(Calendar.DATE);

			Calendar endCal = Calendar.getInstance();
			endCal.setTime(endDate);
			int eYear = endCal.get(Calendar.YEAR);
			int eMonth = endCal.get(Calendar.MONTH);
			int eDay = endCal.get(Calendar.DATE);

			monthday = ((eYear - sYear) * 12 + (eMonth - sMonth));

			if (sDay < eDay) {
				monthday = monthday + 1;
			}
		} catch (ParseException e) {
			monthday = 0;
		}
		return monthday;

	}

	public static Date parse(String dateStr) {
		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	public static String Format(Date date) {
		String dateStr = format.format(date);
		return dateStr;
	}

	/**
	 * 简短的日期格式，界面当中需要
	 * @param date
	 * @return
	 */
	public static String shortFormat(Date date) {
		String dateStr = shortFormat.format(date);
		return dateStr;
	}

	
	/**
	 * 
	 * 获得某天是一周当中的第几天
	 * 
	 * @param date
	 * @return
	 */
	public static String getDayofWeek(Date date) {
		int dayOfWeek = 0;
		String mydate;
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);
		dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek == 0)
			dayOfWeek = 7;
		switch (dayOfWeek) {
		case 1:
			mydate = "周一";
			break;
		case 2:
			mydate = "周二";
			break;

		case 3:
			mydate = "周三";
			break;

		case 4:
			mydate = "周四";
			break;

		case 5:
			mydate = "周五";
			break;

		case 6:
			mydate = "周六";
			break;

		case 7:
			mydate = "周日";
			break;

		default:
			mydate = "周一";
			break;
		}
		return mydate;
	}

	/**
	 * 判断是否是同一周
	 * 
	 * @param dateA
	 * @param dateB
	 * @return
	 */
	public static boolean isSameDay(Date dateA, Date dateB) {
		if (dateA == null || dateB == null) {
			return false;
		}
		Calendar calDateA = Calendar.getInstance();
		calDateA.setTime(dateA);

		Calendar calDateB = Calendar.getInstance();
		calDateB.setTime(dateB);

		return calDateA.get(Calendar.YEAR) == calDateB.get(Calendar.YEAR)
				&& calDateA.get(Calendar.MONTH) == calDateB.get(Calendar.MONTH)
				&& calDateA.get(Calendar.DAY_OF_MONTH) == calDateB
						.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 判断是否是同一个月
	 * @param date
	 * @return
	 */
	public static boolean isSameMonth(Date date) {
		Calendar calDateA = Calendar.getInstance();
		calDateA.setTime(date);

		Calendar calDateB = Calendar.getInstance();
		calDateB.setTime(now);
		if (calDateA.get(Calendar.YEAR) == calDateB.get(Calendar.YEAR)
				&& calDateA.get(Calendar.MONTH) == calDateB.get(Calendar.MONTH)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * 比较
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isBefor(Date date) {
		if (date == null)
			return false;

		if (isSameDay(date, now)) {
			return false;
		}
		if (date.before(now)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取当前一周的起始日期和结束日期
	 * 
	 * @param date
	 * @return
	 */

	public static String getDateRegion(Date date) {
		String region = "";
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		region += shortFormat(c.getTime()) + " ";
		c.add(Calendar.DATE, 6);
		region += shortFormat(c.getTime());
		System.out.print(region);
		return region;
	}

	// public static String getDateRegion(Date date) {
	// String region;
	// Calendar cal = Calendar.getInstance();
	// cal.setTime(date);
	// System.out.println("今天的日期: " + cal.getTime());
	//
	// int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 2;
	// cal.add(Calendar.DATE, -day_of_week);
	// System.out.println("本周第一天: " + shortFormat.format(cal.getTime()));
	// region = shortFormat.format(cal.getTime()) + "  ";
	// cal.add(Calendar.DATE, 6);
	// System.out.println("本周末: " + shortFormat.format(cal.getTime()));
	// region += shortFormat.format(cal.getTime());
	// return region;
	// }

	public static int getWeekOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);
		int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
		return weekOfYear;
	}

	public static int getDayOfMonth(Date date) {
		int month = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		month = calendar.get(Calendar.DAY_OF_MONTH);
		return month;
	}

	public static String getMonthStr(Date date) {
		String dateStr = monthFormat.format(date);
		return dateStr;
	}

	/**
	 * 
	 * 获得两个日期的天数差
	 * @param start
	 * @param end
	 * @return
	 */
	public static long getDateDiff(Date start, Date end) {
		long diff=0;
		try {
			  diff = (end.getTime() - start.getTime()) / (3600 * 24 * 1000);
			  diff=Math.abs(diff);
			System.out.println(diff);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  diff;
	}

}
