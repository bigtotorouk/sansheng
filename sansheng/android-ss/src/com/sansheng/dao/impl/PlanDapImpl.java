package com.sansheng.dao.impl;

import java.sql.SQLException;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.sansheng.dao.interfaze.PlanDao;
import com.sansheng.model.Plan;

public class PlanDapImpl extends BaseDaoImpl<Plan, String> implements PlanDao {

	public PlanDapImpl(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Plan.class);
	}

}
