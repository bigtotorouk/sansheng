package com.sansheng.model;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-20 下午12:09:03 declare:
 */
public class Remind {
	private String userid;
	private String type;
	private String title;
	private String clientid;
	private String time;
	private String info;

	private String remindid;
	private String remindtime;
	private String remindtitle;
	private String remindinfo;
	private String phoneNumber;
	private String remindcode;
	private String remindstatus;
	private String remindjiage;
	private String remindpv;

	private String mobilephone;

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	private String call;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getCall() {
		return call;
	}

	public void setCall(String call) {
		this.call = call;
	}

	public String getRemindid() {
		return remindid;
	}

	public void setRemindid(String remindid) {
		this.remindid = remindid;
	}

	public String getRemindtime() {
		return remindtime;
	}

	public void setRemindtime(String remindtime) {
		this.remindtime = remindtime;
	}

	public String getRemindtitle() {
		return remindtitle;
	}

	public void setRemindtitle(String remindtitle) {
		this.remindtitle = remindtitle;
	}

	public String getRemindinfo() {
		return remindinfo;
	}

	public void setRemindinfo(String remindinfo) {
		this.remindinfo = remindinfo;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRemindcode() {
		return remindcode;
	}

	public void setRemindcode(String remindcode) {
		this.remindcode = remindcode;
	}

	public String getRemindstatus() {
		return remindstatus;
	}

	public void setRemindstatus(String remindstatus) {
		this.remindstatus = remindstatus;
	}

	public String getRemindjiage() {
		return remindjiage;
	}

	public void setRemindjiage(String remindjiage) {
		this.remindjiage = remindjiage;
	}

	public String getRemindpv() {
		return remindpv;
	}

	public void setRemindpv(String remindpv) {
		this.remindpv = remindpv;
	}

	@Override
	public String toString() {
		return "Remind [userid=" + userid + ", type=" + type + ", title="
				+ title + ", clientid=" + clientid + ", time=" + time
				+ ", info=" + info + ", remindid=" + remindid + ", remindtime="
				+ remindtime + ", remindtitle=" + remindtitle + ", remindinfo="
				+ remindinfo + "]";
	}

}
