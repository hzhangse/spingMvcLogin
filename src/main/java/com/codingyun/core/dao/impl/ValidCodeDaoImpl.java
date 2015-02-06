package com.codingyun.core.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.codingyun.core.dao.GeneralDao;
import com.codingyun.core.dao.ValidCodeDao;
import com.codingyun.core.entity.ValidCode;

@Repository
public class ValidCodeDaoImpl implements ValidCodeDao {
	
	@Autowired
	private GeneralDao generalDao;

	@Override
	public boolean remove(ValidCode code) {
		return false;
	}

	@Override
	public ValidCode getValidCode(String codeKey) {
		try {
			return generalDao.getEntity(
					ValidCode.class, 
					" select * from validCode where codeKey = ? ", new Object[]{codeKey});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void saveValidCode(ValidCode code) {
		try {
			generalDao.saveEntity(
					" insert into validCode (codeKey, code, createTime) values(?, ?, ?)", 
					new Object[]{code.getCodeKey(), code.getCode(), code.getCreateTime()});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
