package com.sansheng.model;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-8 下午6:12:43 declare:系列接口
 */
public class pr {

	private String prclass_id;
	private String prclass_name;

	public String getPrclass_id() {
		return prclass_id;
	}

	public void setPrclass_id(String prclass_id) {
		this.prclass_id = prclass_id;
	}

	public String getPrclass_name() {
		return prclass_name;
	}

	public void setPrclass_name(String prclass_name) {
		this.prclass_name = prclass_name;
	}

	@Override
	public String toString() {
		return "pr [prclass_id=" + prclass_id + ", prclass_name="
				+ prclass_name + "]";
	}

}
