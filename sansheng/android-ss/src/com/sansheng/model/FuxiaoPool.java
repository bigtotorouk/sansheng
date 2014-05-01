package com.sansheng.model;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-10-21 下午8:59:22 declare:
 */
public class FuxiaoPool {
	private String repeat40PVevaluation;
	private String usedevaluation;
	private String surplusevaluation;
	private String allqualifiedmonths;
	private String usedqualifiedmonths;
	private String surplusqualifiedmonths;
	public String getRepeat40PVevaluation() {
		return repeat40PVevaluation;
	}
	public void setRepeat40PVevaluation(String repeat40pVevaluation) {
		repeat40PVevaluation = repeat40pVevaluation;
	}
	public String getUsedevaluation() {
		return usedevaluation;
	}
	public void setUsedevaluation(String usedevaluation) {
		this.usedevaluation = usedevaluation;
	}
	public String getSurplusevaluation() {
		return surplusevaluation;
	}
	public void setSurplusevaluation(String surplusevaluation) {
		this.surplusevaluation = surplusevaluation;
	}
	public String getAllqualifiedmonths() {
		return allqualifiedmonths;
	}
	public void setAllqualifiedmonths(String allqualifiedmonths) {
		this.allqualifiedmonths = allqualifiedmonths;
	}
	public String getUsedqualifiedmonths() {
		return usedqualifiedmonths;
	}
	public void setUsedqualifiedmonths(String usedqualifiedmonths) {
		this.usedqualifiedmonths = usedqualifiedmonths;
	}
	public String getSurplusqualifiedmonths() {
		return surplusqualifiedmonths;
	}
	public void setSurplusqualifiedmonths(String surplusqualifiedmonths) {
		this.surplusqualifiedmonths = surplusqualifiedmonths;
	}
	@Override
	public String toString() {
		return "FuxiaoPool [repeat40PVevaluation=" + repeat40PVevaluation
				+ ", usedevaluation=" + usedevaluation + ", surplusevaluation="
				+ surplusevaluation + ", allqualifiedmonths="
				+ allqualifiedmonths + ", usedqualifiedmonths="
				+ usedqualifiedmonths + ", surplusqualifiedmonths="
				+ surplusqualifiedmonths + "]";
	}

}
