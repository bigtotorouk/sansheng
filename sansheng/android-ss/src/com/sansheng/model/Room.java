package com.sansheng.model;

import java.io.Serializable;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-20 上午10:49:27 declare:
 */
public class Room implements Serializable {

	private String shopid;
	private String shopname;
	private String shopcall;
	private String shopadds;
	private String shopuserid;

	public String getShopid() {
		return shopid;
	}

	public void setShopid(String shopid) {
		this.shopid = shopid;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public String getShopcall() {
		return shopcall;
	}

	public void setShopcall(String shopcall) {
		this.shopcall = shopcall;
	}

	public String getShopadds() {
		return shopadds;
	}

	public String getShopuserid() {
		return shopuserid;
	}

	public void setShopuserid(String shopuserid) {
		this.shopuserid = shopuserid;
	}

	public void setShopadds(String shopadds) {
		this.shopadds = shopadds;
	}

	@Override
	public String toString() {
		return "Room [shopid=" + shopid + ", shopname=" + shopname
				+ ", shopcall=" + shopcall + ", shopadds=" + shopadds + "]";
	}

}
