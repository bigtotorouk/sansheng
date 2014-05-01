package com.sansheng.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.sansheng.dao.interfaze.LocalInfoDao;
import com.sansheng.model.LocalInfo;
import com.sansheng.model.LocalInfo.InfoType;

public class LocalInfoDaoImpl extends BaseDaoImpl<LocalInfo, String> implements
		LocalInfoDao {
	public LocalInfoDaoImpl(ConnectionSource connectionSource)
			throws SQLException {
		super(connectionSource, LocalInfo.class);
	}

	@Override
	public List<LocalInfo> getLoclInfosByType(InfoType infoType) {
		List<LocalInfo> localInfos = null;
		int type = LocalInfo.getInfoType(infoType);
		try {
			QueryBuilder<LocalInfo, String> qb = queryBuilder();
			qb.where().eq("type", type);
			PreparedQuery<LocalInfo> pq = qb.prepare();
			localInfos = query(pq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return localInfos;
	}

	@Override
	public void deleteByType(InfoType infoType) {
		int type = LocalInfo.getInfoType(infoType);
		try {
			DeleteBuilder<LocalInfo, String> qb = deleteBuilder();
			qb.where().eq("type", type);
			PreparedDelete<LocalInfo> pd = qb.prepare();
			qb.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void batchInsert(List<LocalInfo> localInfos) {
		for (int i = 0; i < localInfos.size(); i++) {
			try {
				create(localInfos.get(i));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
