package com.sansheng.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-20 上午11:15:59 declare:
 */
public class CustomForm implements Serializable {

	private String balanceid;
	private String balanceno;

	private String balanqishu;
	private String userid;
	private String username;
	private String shopno;
	private String allmoney;
	private String allpv;
	private String logistics;
	private String logiscode;
	private String orderid;
	private String receiptusername;
	private String receiptusercall;
	private String receiptuseradds;
	private String totalamt;
	private String totalpv;
	private List<Product> product;

	private String isfahuo;
	
	
	

	public String getIsfahuo() {
		return isfahuo;
	}

	public void setIsfahuo(String isfahuo) {
		this.isfahuo = isfahuo;
	}

	private String isshow;

	public String getIsshow() {
		return isshow;
	}

	public String getBalanqishu() {
		return balanqishu;
	}

	public void setBalanqishu(String balanqishu) {
		this.balanqishu = balanqishu;
	}

	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}

	public String getBalanceid() {
		return balanceid;
	}

	public void setBalanceid(String balanceid) {
		this.balanceid = balanceid;
	}

	public String getBalanceno() {
		return balanceno;
	}

	public void setBalanceno(String balanceno) {
		this.balanceno = balanceno;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getShopno() {
		return shopno;
	}

	public void setShopno(String shopno) {
		this.shopno = shopno;
	}

	public String getAllmoney() {
		return allmoney;
	}

	public void setAllmoney(String allmoney) {
		this.allmoney = allmoney;
	}

	public String getAllpv() {
		return allpv;
	}

	public void setAllpv(String allpv) {
		this.allpv = allpv;
	}

	public String getLogistics() {
		return logistics;
	}

	public void setLogistics(String logistics) {
		this.logistics = logistics;
	}

	public String getLogiscode() {
		return logiscode;
	}

	public void setLogiscode(String logiscode) {
		this.logiscode = logiscode;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getReceiptusername() {
		return receiptusername;
	}

	public void setReceiptusername(String receiptusername) {
		this.receiptusername = receiptusername;
	}

	public String getReceiptusercall() {
		return receiptusercall;
	}

	public void setReceiptusercall(String receiptusercall) {
		this.receiptusercall = receiptusercall;
	}

	public String getReceiptuseradds() {
		return receiptuseradds;
	}

	public void setReceiptuseradds(String receiptuseradds) {
		this.receiptuseradds = receiptuseradds;
	}

	public String getTotalamt() {
		return totalamt;
	}

	public void setTotalamt(String totalamt) {
		this.totalamt = totalamt;
	}

	public String getTotalpv() {
		return totalpv;
	}

	public void setTotalpv(String totalpv) {
		this.totalpv = totalpv;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "CustomForm [balanceid=" + balanceid + ", balanceno="
				+ balanceno + ", balanqishu=" + balanqishu + ", userid="
				+ userid + ", username=" + username + ", shopno=" + shopno
				+ ", allmoney=" + allmoney + ", allpv=" + allpv
				+ ", logistics=" + logistics + ", logiscode=" + logiscode
				+ ", orderid=" + orderid + ", receiptusername="
				+ receiptusername + ", receiptusercall=" + receiptusercall
				+ ", receiptuseradds=" + receiptuseradds + ", totalamt="
				+ totalamt + ", totalpv=" + totalpv + ", product=" + product
				+ ", isshow=" + isshow + "]";
	}

}
