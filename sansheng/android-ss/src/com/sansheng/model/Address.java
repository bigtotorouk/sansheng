package com.sansheng.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-8 上午10:58:54 declare:
 */
public class Address implements Serializable {

	private int type;
	private int id;
	private String infrom;
	private String name;
	private String call;
	private String adds;
	@SerializedName(value = "default")
	private String defaults;
	public String userid;
	private String code;
	

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInfrom() {
		return infrom;
	}

	public void setInfrom(String infrom) {
		this.infrom = infrom;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCall() {
		return call;
	}

	public void setCall(String call) {
		this.call = call;
	}

	public String getAdds() {
		return adds;
	}

	public void setAdds(String adds) {
		this.adds = adds;
	}

	public String getDefaults() {
		return defaults;
	}

	public void setDefaults(String defaults) {
		this.defaults = defaults;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
