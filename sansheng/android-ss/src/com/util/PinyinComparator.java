package com.util;

import java.util.Comparator;

import com.sansheng.model.Contact;

public class PinyinComparator implements Comparator {

	// @Override
	// public int compare(Object o1, Object o2) {
	// Contact friendItem1 = (Contact) o1;
	// Contact friendItem2 = (Contact) o2;
	//
	// String str1 = friendItem1.getNickName();
	// String str2 = friendItem2.getNickName();
	//
	// str1 = str1.toLowerCase();
	// str2 = str2.toLowerCase();
	// str1 = str1.replaceAll("(?i)[^a-zA-Z0-9\u4E00-\u9FA5]", "");
	// str2 = str2.replaceAll("(?i)[^a-zA-Z0-9\u4E00-\u9FA5]", "");
	// return str1.compareTo(str2);
	// }

	@Override
	public int compare(Object o1, Object o2) {
		Contact friendItem1 = (Contact) o1;
		Contact friendItem2 = (Contact) o2;

		String str1 = friendItem1.getPingying();
		String str2 = friendItem2.getPingying();

		str1 = str1.toLowerCase();
		str2 = str2.toLowerCase();
		str1 = str1.replaceAll("(?i)[^a-zA-Z0-9\u4E00-\u9FA5]", "");
		str2 = str2.replaceAll("(?i)[^a-zA-Z0-9\u4E00-\u9FA5]", "");
		return str1.compareTo(str2);
	}

}
