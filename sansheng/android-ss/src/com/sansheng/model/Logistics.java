package com.sansheng.model;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.sansheng.dao.impl.LogisticsDaoImpl;
import com.sansheng.dao.impl.ScheduleDaoImpl;

@DatabaseTable(daoClass = LogisticsDaoImpl.class)
public class Logistics implements Serializable {

	@DatabaseField(generatedId = true)
	private int id;

	// 物流编号
	@DatabaseField
	private String logistics_id;
	// 报单金额
	@DatabaseField
	private float bill_sum;
	// 物流状态
	@DatabaseField
	private String logistics_status;

	// 发货时间
	@DatabaseField
	private String delivery_time;

	// 收货时间
	@DatabaseField
	private String recive_time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogistics_id() {
		return logistics_id;
	}

	public void setLogistics_id(String logistics_id) {
		this.logistics_id = logistics_id;
	}

	public float getBill_sum() {
		return bill_sum;
	}

	public void setBill_sum(float bill_sum) {
		this.bill_sum = bill_sum;
	}

	public String getLogistics_status() {
		return logistics_status;
	}

	public void setLogistics_status(String logistics_status) {
		this.logistics_status = logistics_status;
	}

	public String getDelivery_time() {
		return delivery_time;
	}

	public void setDelivery_time(String delivery_time) {
		this.delivery_time = delivery_time;
	}

	public String getRecive_time() {
		return recive_time;
	}

	public void setRecive_time(String recive_time) {
		this.recive_time = recive_time;
	}

}
