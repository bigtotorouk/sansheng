package com.sansheng.dao.impl;

import java.sql.SQLException;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.sansheng.dao.interfaze.UserDao;
import com.sansheng.model.LocalInfo;
import com.sansheng.model.User;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-25 下午8:49:50 declare:
 */
public class UserDaoImple extends BaseDaoImpl<User, String> implements UserDao {
	public UserDaoImple(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, User.class);
	}

}
