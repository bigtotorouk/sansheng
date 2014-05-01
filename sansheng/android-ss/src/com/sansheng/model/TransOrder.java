package com.sansheng.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-10-5 下午3:34:37 declare:
 */
public class TransOrder implements Serializable {

	private List<Product> productlist;
	private String storeid;
	private String bianhao;
	private String ubianhao;
	private String username;
	private String fhtype;
	private String hometel;
	private String mobiletel;
	private String address;
	private String receiver;
	private String totalamt;
	private String totalpv;
	private String sysflag;
	private String ordertype;
	private String paytype;

	private String balanceid;
	private String balanceno;
	private String balanqishu;
	private String userid;
	private String shopno;
	private String allmoney;
	private String allpv;
	private String isfahuo;

	private Address homeAddres;
	private Address roomAddres;

	private String addType;

	public Address getHomeAddres() {
		return homeAddres;
	}

	public void setHomeAddres(Address homeAddres) {
		this.homeAddres = homeAddres;
	}

	public Address getRoomAddres() {
		return roomAddres;
	}

	public void setRoomAddres(Address roomAddres) {
		this.roomAddres = roomAddres;
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

	public String getBalanqishu() {
		return balanqishu;
	}

	public void setBalanqishu(String balanqishu) {
		this.balanqishu = balanqishu;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
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

	public String getIsfahuo() {
		return isfahuo;
	}

	public void setIsfahuo(String isfahuo) {
		this.isfahuo = isfahuo;
	}

	public List<Product> getProductlist() {
		return productlist;
	}

	public void setProductlist(List<Product> productlist) {
		this.productlist = productlist;
	}

	public String getStoreid() {
		return storeid;
	}

	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}

	public String getBianhao() {
		return bianhao;
	}

	public void setBianhao(String bianhao) {
		this.bianhao = bianhao;
	}

	public String getUbianhao() {
		return ubianhao;
	}

	public void setUbianhao(String ubianhao) {
		this.ubianhao = ubianhao;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFhtype() {
		return fhtype;
	}

	public void setFhtype(String fhtype) {
		this.fhtype = fhtype;
	}

	public String getHometel() {
		return hometel;
	}

	public void setHometel(String hometel) {
		this.hometel = hometel;
	}

	public String getMobiletel() {
		return mobiletel;
	}

	public void setMobiletel(String mobiletel) {
		this.mobiletel = mobiletel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
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

	public String getSysflag() {
		return sysflag;
	}

	public void setSysflag(String sysflag) {
		this.sysflag = sysflag;
	}

	public String getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	
	
	

	public String getAddType() {
		return addType;
	}

	public void setAddType(String addType) {
		this.addType = addType;
	}

	@Override
	public String toString() {
		return "TransOrder [productlist=" + productlist + ", storeid="
				+ storeid + ", bianhao=" + bianhao + ", ubianhao=" + ubianhao
				+ ", username=" + username + ", fhtype=" + fhtype
				+ ", hometel=" + hometel + ", mobiletel=" + mobiletel
				+ ", address=" + address + ", receiver=" + receiver
				+ ", totalamt=" + totalamt + ", totalpv=" + totalpv
				+ ", sysflag=" + sysflag + ", ordertype=" + ordertype
				+ ", paytype=" + paytype + "]";
	}

}
