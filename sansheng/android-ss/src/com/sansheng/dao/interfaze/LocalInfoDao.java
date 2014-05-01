package com.sansheng.dao.interfaze;

import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.sansheng.model.LocalInfo;
import com.sansheng.model.LocalInfo.InfoType;

public interface LocalInfoDao extends Dao<LocalInfo, String> {

	public List<LocalInfo> getLoclInfosByType(InfoType infoType);

	public void deleteByType(InfoType infoType);

	public void batchInsert(List<LocalInfo> localInfos);

}
