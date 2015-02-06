package com.codingyun.core.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingyun.core.dao.UserDao;
import com.codingyun.core.entity.vo.SysUserVo;
import com.codingyun.core.exception.ServiceException;
import com.codingyun.core.service.UserService;
import com.codingyun.core.service.ValidCodeService;
import com.codingyun.core.util.MD5Util;

@Service
public class UserServiceImpl implements UserService {
	
	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ValidCodeService validCodeService;

	@Override
	public SysUserVo sysAdminLogin(SysUserVo user, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(!validCodeService.checkValidCode(user.getRandomString(), user.getType(), user.getAuthCode())){
			throw new ServiceException("验证码错误！");
		}
		SysUserVo existUser = null;
		existUser = userDao.getSysUserByUserName(user.getUserName());
		if(existUser == null){
			throw new ServiceException("用户不存在！");
		}
		logger.info(MD5Util.GetMD5Code(user.getUserPass()));
		if(!existUser.getUserPass().
				equalsIgnoreCase(MD5Util.GetMD5Code(user.getUserPass()))){
			throw new ServiceException("用户密码错误！");
		}
		request.getSession().setAttribute("sysUser", existUser);
		return existUser;
	}
}
