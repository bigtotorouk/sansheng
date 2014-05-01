package com.sansheng.dao.impl;

import java.sql.SQLException;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.sansheng.dao.interfaze.LogisticsDao;
import com.sansheng.model.Logistics;
import com.sansheng.model.Schedule;

public class LogisticsDaoImpl extends BaseDaoImpl<Logistics, String> implements
		LogisticsDao {
	public LogisticsDaoImpl(ConnectionSource connectionSource)
			throws SQLException {
		super(connectionSource, Logistics.class);
	}
}
