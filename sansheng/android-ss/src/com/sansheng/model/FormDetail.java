package com.sansheng.model;

import java.io.Serializable;
import java.util.List;

import com.sansheng.model.FormDetail.Product.shipment;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-10-24 下午9:20:37 declare:
 */
public class FormDetail implements Serializable {

	private String logistics;
	private String logiscode;
	private String orderid;
	private String ordercode;
	private String userid;
	private String username;
	private String receiptusername;
	private String receiptusercall;
	private String receiptuseradds;
	private String totalamt;
	private String totalpv;
	private List<Product> product;
	private List<shipment> ships;
	private List<shipment> shipmentslist;

	public List<shipment> getShipmentslist() {
		return shipmentslist;
	}

	public void setShipmentslist(List<shipment> shipmentslist) {
		this.shipmentslist = shipmentslist;
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

	public String getOrdercode() {
		return ordercode;
	}

	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
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

	public List<shipment> getShips() {
		return ships;
	}

	public void setShips(List<shipment> ships) {
		this.ships = ships;
	}

	public java.util.List<com.sansheng.model.Product> getOderprlist() {
		return oderprlist;
	}

	public void setOderprlist(
			java.util.List<com.sansheng.model.Product> oderprlist) {
		this.oderprlist = oderprlist;
	}

	private java.util.List<com.sansheng.model.Product> oderprlist;

	public class Product {
		private String pid;
		private String name;
		private String number;
		private String mun;
		private String price;
		private String pv;

		public String getPid() {
			return pid;
		}

		public void setPid(String pid) {
			this.pid = pid;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		public String getMun() {
			return mun;
		}

		public void setMun(String mun) {
			this.mun = mun;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}

		public String getPv() {
			return pv;
		}

		public void setPv(String pv) {
			this.pv = pv;
		}

		@Override
		public String toString() {
			return "Product [pid=" + pid + ", name=" + name + ", number="
					+ number + ", mun=" + mun + ", price=" + price + ", pv="
					+ pv + "]";
		}

		public class shipment {
			private String pid;
			private String name;
			private String number;
			private String mun;
			private String price;
			private String pv;

			public String getPid() {
				return pid;
			}

			public void setPid(String pid) {
				this.pid = pid;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getNumber() {
				return number;
			}

			public void setNumber(String number) {
				this.number = number;
			}

			public String getMun() {
				return mun;
			}

			public void setMun(String mun) {
				this.mun = mun;
			}

			public String getPrice() {
				return price;
			}

			public void setPrice(String price) {
				this.price = price;
			}

			public String getPv() {
				return pv;
			}

			public void setPv(String pv) {
				this.pv = pv;
			}

		}

	}

	@Override
	public String toString() {
		return "FormDetail [logistics=" + logistics + ", logiscode="
				+ logiscode + ", orderid=" + orderid + ", ordercode="
				+ ordercode + ", userid=" + userid + ", username=" + username
				+ ", receiptusername=" + receiptusername + ", receiptusercall="
				+ receiptusercall + ", receiptuseradds=" + receiptuseradds
				+ ", totalamt=" + totalamt + ", totalpv=" + totalpv
				+ ", oderprlist=" + oderprlist + "]";
	}

}
