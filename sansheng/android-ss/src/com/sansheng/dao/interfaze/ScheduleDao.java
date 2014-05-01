package com.sansheng.dao.interfaze;

import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.sansheng.model.Schedule;
import com.sansheng.model.Schedule.Type;

public interface ScheduleDao extends Dao<Schedule, String> {

	public List<Schedule> getScheduleByType(Type type);

}
