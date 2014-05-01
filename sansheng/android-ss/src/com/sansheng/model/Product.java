package com.sansheng.model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-26 下午2:32:15 declare:
 */
public class Product implements Serializable {

	private String id;
	private String product_id;
	private String name;
	private String simg;
	private String bimg;
	private String pid;
	private String number;
	private String title;
	private String pv;
	private int mun;
	private String price;
	private String format;
	private String summary;
	private String[] imgs;
	private String cartid;
	private int qualityint;
	private int cartcount;

	private String comment;

	public String getComment() {
		return comment;
	}

	public int getCartcount() {
		return cartcount;
	}

	public void setCartcount(int cartcount) {
		this.cartcount = cartcount;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	private String img;

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSimg() {
		return simg;
	}

	public void setSimg(String simg) {
		this.simg = simg;
	}

	public String getBimg() {
		return bimg;
	}

	public void setBimg(String bimg) {
		this.bimg = bimg;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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

	public int getMun() {
		return mun;
	}

	public void setMun(int mun) {
		this.mun = mun;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getImgs() {
		return imgs;
	}

	public void setImgs(String[] imgs) {
		this.imgs = imgs;
	}

	public String getCartid() {
		return cartid;
	}

	public void setCartid(String cartid) {
		this.cartid = cartid;
	}

	public int getQualityint() {
		return qualityint;
	}

	public void setQualityint(int qualityint) {
		this.qualityint = qualityint;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", product_id=" + product_id + ", name="
				+ name + ", simg=" + simg + ", bimg=" + bimg + ", pid=" + pid
				+ ", number=" + number + ", title=" + title + ", pv=" + pv
				+ ", mun=" + mun + ", price=" + price + ", format=" + format
				+ ", summary=" + summary + ", imgs=" + Arrays.toString(imgs)
				+ ", cartid=" + cartid + ", qualityint=" + qualityint
				+ ", comment=" + comment + ", img=" + img + "]";
	}

}
