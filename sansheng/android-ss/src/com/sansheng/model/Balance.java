package com.sansheng.model;

import java.util.List;

public class Balance {
	private String balanceid;
	private String balanceno;
	private String userno;
	private String username;
	private String userphone;
	private String address;
	private String Produc;
	private String productname;
	private String productlist;
	private String productinfo;
	private List<Product> product;
	
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
	public String getUserno() {
		return userno;
	}
	public void setUserno(String userno) {
		this.userno = userno;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserphone() {
		return userphone;
	}
	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getProduc() {
		return Produc;
	}
	public void setProduc(String produc) {
		Produc = produc;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getProductlist() {
		return productlist;
	}
	public void setProductlist(String productlist) {
		this.productlist = productlist;
	}
	public String getProductinfo() {
		return productinfo;
	}
	public void setProductinfo(String productinfo) {
		this.productinfo = productinfo;
	}
	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> product) {
		this.product = product;
	}
}
