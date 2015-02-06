package com.codingyun.core.dao;

import com.codingyun.core.entity.bo.SysUserBo;
import com.codingyun.core.entity.vo.SysUserVo;

public interface UserDao {
	/**
	 * 根据邮箱获取系统管理员信息
	 * @user coding云
	 * 2014年6月24日
	 */
	public SysUserBo getSysUserByEmail(String userEmail) throws Exception;
	
	public SysUserVo getSysUserByUserName(String userName) throws Exception;
	
	public boolean updateSysUser(SysUserBo user, String... fileds) throws Exception;

}
