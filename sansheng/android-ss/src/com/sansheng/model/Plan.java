package com.sansheng.model;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.sansheng.dao.impl.PlanDapImpl;

@DatabaseTable(daoClass = PlanDapImpl.class)
public class Plan implements Serializable {

	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField
	private String plan_id;

	@DatabaseField
	private String plan_data;

	@DatabaseField
	private String sum;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlan_id() {
		return plan_id;
	}

	public void setPlan_id(String plan_id) {
		this.plan_id = plan_id;
	}

	public String getPlan_data() {
		return plan_data;
	}

	public void setPlan_data(String plan_data) {
		this.plan_data = plan_data;
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

}
