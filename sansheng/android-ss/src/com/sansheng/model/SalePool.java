package com.sansheng.model;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-10-21 下午8:57:07 declare:
 */
public class SalePool {

	private String lastresaleevaluation;
	private String resaleevaluation;
	private String useresaleevaluation;
	private String resalesurplusevaluation;
	public String getLastresaleevaluation() {
		return lastresaleevaluation;
	}
	public void setLastresaleevaluation(String lastresaleevaluation) {
		this.lastresaleevaluation = lastresaleevaluation;
	}
	public String getResaleevaluation() {
		return resaleevaluation;
	}
	public void setResaleevaluation(String resaleevaluation) {
		this.resaleevaluation = resaleevaluation;
	}
	public String getUseresaleevaluation() {
		return useresaleevaluation;
	}
	public void setUseresaleevaluation(String useresaleevaluation) {
		this.useresaleevaluation = useresaleevaluation;
	}
	public String getResalesurplusevaluation() {
		return resalesurplusevaluation;
	}
	public void setResalesurplusevaluation(String resalesurplusevaluation) {
		this.resalesurplusevaluation = resalesurplusevaluation;
	}
	@Override
	public String toString() {
		return "SalePool [lastresaleevaluation=" + lastresaleevaluation
				+ ", resaleevaluation=" + resaleevaluation
				+ ", useresaleevaluation=" + useresaleevaluation
				+ ", resalesurplusevaluation=" + resalesurplusevaluation + "]";
	}
 
}
