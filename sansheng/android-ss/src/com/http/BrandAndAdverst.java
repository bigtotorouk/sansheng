package com.http;

import java.util.List;

import com.sansheng.model.Adverst;
import com.sansheng.model.Brand;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-8 下午7:51:48 declare:
 */
public class BrandAndAdverst {

	private List<Adverst> adversts;
	private List<Brand> brands;
	public List<Adverst> getAdversts() {
		return adversts;
	}
	public void setAdversts(List<Adverst> adversts) {
		this.adversts = adversts;
	}
	public List<Brand> getBrands() {
		return brands;
	}
	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}
	 
	
}
