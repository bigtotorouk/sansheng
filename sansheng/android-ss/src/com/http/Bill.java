package com.http;

import java.util.List;

import com.sansheng.model.AchList;
import com.sansheng.model.Achivement;
import com.sansheng.model.FuxiaoPool;
import com.sansheng.model.SalePool;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-10-21 下午9:26:43 declare:
 */
public class Bill {
	private List<AchList> achLists;
	private Achivement achivement;
	private SalePool salePool;
	private FuxiaoPool fuxiaoPool;
	public List<AchList> getAchLists() {
		return achLists;
	}
	public void setAchLists(List<AchList> achLists) {
		this.achLists = achLists;
	}
	public Achivement getAchivement() {
		return achivement;
	}
	public void setAchivement(Achivement achivement) {
		this.achivement = achivement;
	}
	public SalePool getSalePool() {
		return salePool;
	}
	public void setSalePool(SalePool salePool) {
		this.salePool = salePool;
	}
	public FuxiaoPool getFuxiaoPool() {
		return fuxiaoPool;
	}
	public void setFuxiaoPool(FuxiaoPool fuxiaoPool) {
		this.fuxiaoPool = fuxiaoPool;
	}
	@Override
	public String toString() {
		return "Bill [achLists=" + achLists + ", achivement=" + achivement
				+ ", salePool=" + salePool + ", fuxiaoPool=" + fuxiaoPool + "]";
	}
	
	

}
