package com.sansheng.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-26 下午2:38:15 declare:
 */
public class Brand implements Serializable {
	private int brandid;
	private String id;
	private String simg;
	private String title;
	private String pv;
	private String brandimg;
	private String brandname;
	private  String  price;
	private List<Product> productlist;

	 

	public String getId() {
		return id;
	}

	public String getBrandImg() {
		return brandimg;
	}

	public void setBrandImg(String brandImg) {
		this.brandimg = brandImg;
	}

	public String getBrandName() {
		return brandname;
	}

	public void setBrandName(String brandName) {
		this.brandname = brandName;
	}

	public List<Product> getProducets() {
		return productlist;
	}

	public void setProducets(List<Product> producets) {
		this.productlist = producets;
	}

	public int getBrandid() {
		return brandid;
	}

	public void setBrandid(int brandid) {
		this.brandid = brandid;
	}

	public String getSimg() {
		return simg;
	}

	public void setSimg(String simg) {
		this.simg = simg;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPv() {
		return pv;
	}

	public void setPv(String pv) {
		this.pv = pv;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBrandimg() {
		return brandimg;
	}

	public void setBrandimg(String brandimg) {
		this.brandimg = brandimg;
	}

	public String getBrandname() {
		return brandname;
	}

	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}

	public List<Product> getProductlist() {
		return productlist;
	}

	public void setProductlist(List<Product> productlist) {
		this.productlist = productlist;
	}
	

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Brand [brandid=" + brandid + ", id=" + id + ", simg=" + simg
				+ ", title=" + title + ", pv=" + pv + ", brandimg=" + brandimg
				+ ", brandname=" + brandname + ", price=" + price
				+ ", productlist=" + productlist + "]";
	}

 

	 

}
