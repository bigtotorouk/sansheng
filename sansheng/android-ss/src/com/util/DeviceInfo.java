package com.util;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-25 下午8:29:16 declare:
 */
public class DeviceInfo {

	public static String version = "1";

	public static String getMacAddress(Context context) {
		WifiManager wifi = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		return info.getMacAddress();
	}

	public static String osVersion(Context context) {
		String v = android.os.Build.VERSION.SDK;
		return v;
	}

	public static String getInfo(Activity context) {

		String mac=MacUtil.getMac(context);
		String info = DeviceInfo.version + "|"
				+ mac + "|"
				+ "android";
		System.out.println("info:" + info);
		return info;
	}
}
