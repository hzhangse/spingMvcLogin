package com.codingyun.core.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.codingyun.core.dao.GeneralDao;
import com.codingyun.core.dao.UserDao;
import com.codingyun.core.entity.bo.SysUserBo;
import com.codingyun.core.entity.vo.SysUserVo;
import com.codingyun.core.util.StringUtil;

@Repository
public class UserDaoImpl implements UserDao {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private GeneralDao generalDao;


	@Override
	public SysUserBo getSysUserByEmail(String userEmail) throws Exception {
		if(StringUtil.isEmpty(userEmail)){
			return null;
		}
		return generalDao.getEntity(SysUserBo.class, " select * from sys_user where email = ? ", new Object[]{userEmail});
	}

	@Override
	public boolean updateSysUser(SysUserBo user, String... fileds)
			throws Exception {
		if(user == null){
			return false;
		}
		try {
			return generalDao.updateEntity(user, fileds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public SysUserVo getSysUserByUserName(String userName) throws Exception {
		if(StringUtil.isEmpty(userName)){
			return null;
		}
		return generalDao.getEntity(SysUserVo.class, " select * from sys_user where userName = ? ", new Object[]{userName});
	}

}
