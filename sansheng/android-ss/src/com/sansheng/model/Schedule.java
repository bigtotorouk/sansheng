package com.sansheng.model;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.sansheng.dao.impl.ScheduleDaoImpl;

import android.R.integer;

@DatabaseTable(daoClass = ScheduleDaoImpl.class)
public class Schedule implements Serializable {

	public enum Type {
		visit, birthday, other, wuliu, fuxiao
	}

	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField
	private int type;
	@DatabaseField
	private int custome_id;
	@DatabaseField
	private String content;
	@DatabaseField
	private String data;
	@DatabaseField
	private String custome_name;

	@DatabaseField
	private String phoneNumber;

	private String mobilephone;

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
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

	public int getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = getTypeValue(type);
	}

	public int getCustome_id() {
		return custome_id;
	}

	public void setCustome_id(int custome_id) {
		this.custome_id = custome_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getCustome_name() {
		return custome_name;
	}

	public void setCustome_name(String custome_name) {
		this.custome_name = custome_name;
	}

	public static int getTypeValue(Type type) {

		int typeValue = 0;
		switch (type) {
		case visit:
			typeValue = 1;
			break;
		case birthday:
			typeValue = 2;
			break;
		case other:
			typeValue = 3;
			break;
		case wuliu:
			typeValue = 4;
			break;
		case fuxiao:
			typeValue = 5;
			break;
		}
		return typeValue;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
