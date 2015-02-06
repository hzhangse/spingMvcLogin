package com.codingyun.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingyun.core.dao.ValidCodeDao;
import com.codingyun.core.entity.ValidCode;
import com.codingyun.core.service.ValidCodeService;

@Service
public class ValidCodeServiceImpl implements ValidCodeService {
	
	@Autowired
	private ValidCodeDao validCodeDao;

	@Override
	public boolean checkValidCode(String random, String type, String input) {
		String codeKey = genCodeKey(type, random);
		ValidCode code = validCodeDao.getValidCode(codeKey);
		if (code == null) {
			return false;
		}
		if (!code.getCode().equalsIgnoreCase(input)) {
			return false;
		}
		this.remove(code);
		return true;
	}

	@Override
	public void recordValidCode(String random, String type, String code) {
		String codeKey = this.genCodeKey(type, random);
		//create new code
		ValidCode validCode = new ValidCode(codeKey, code);
		validCodeDao.saveValidCode(validCode);
	}

	@Override
	public void remove(ValidCode code) {
		validCodeDao.remove(code);
	}

	private String genCodeKey(String type, String random) {
		return type + "." + random;
	}

}
