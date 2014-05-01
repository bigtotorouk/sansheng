package com.sansheng.model;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-10-20 下午9:09:42 declare:
 */
public class AchList {

	private String periodsid;
	private String title;
	private String times;
	public String getPeriodsid() {
		return periodsid;
	}
	public void setPeriodsid(String periodsid) {
		this.periodsid = periodsid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	@Override
	public String toString() {
		return "AchList [periodsid=" + periodsid + ", title=" + title
				+ ", times=" + times + "]";
	}
	
}
