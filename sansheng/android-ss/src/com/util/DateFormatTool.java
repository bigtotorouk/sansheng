package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.provider.ContactsContract.Contacts.Data;

public class DateFormatTool {

	// yyyy-mm-dd hh:mm:ss 2 yyyy-mm-dd
	static SimpleDateFormat dateTimeSDF = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	static SimpleDateFormat dateSDF = new SimpleDateFormat("yyyy-MM-dd");

	public static String dateTime2Date(String dateStr) {
		try {
			Date date = dateTimeSDF.parse(dateStr);
			return dateSDF.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateStr;
	}

	public static String getFormtTime() {
		String date = null;
		Date d = new Date();
		date = dateSDF.format(d);
		return date;
	}
}
