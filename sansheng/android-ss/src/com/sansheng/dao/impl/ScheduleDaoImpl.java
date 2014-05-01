package com.sansheng.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.sansheng.dao.interfaze.ScheduleDao;
import com.sansheng.model.Schedule;
import com.sansheng.model.Schedule.Type;

public class ScheduleDaoImpl extends BaseDaoImpl<Schedule, String> implements
		ScheduleDao {
	public ScheduleDaoImpl(ConnectionSource connectionSource)
			throws SQLException {
		super(connectionSource, Schedule.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Schedule> getScheduleByType(Type type) {
		List<Schedule> schedules = null;
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
		}
		try {
			QueryBuilder<Schedule, String> qb = queryBuilder();
			qb.where().eq("type", typeValue);
			PreparedQuery<Schedule> pq = qb.prepare();
			schedules = query(pq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return schedules;
	}

}
