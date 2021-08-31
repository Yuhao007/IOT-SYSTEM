package com.learn.dao;

import com.learn.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

/**
 * sys user
 * 
 *
 *
 * @date #9##1118 9:34:11
 */
public interface SysUserDao extends BaseDao<SysUserEntity> {
	
	/**
	 * Example Query all permissions of a user
	 * * @param userId userId
	 */
	List<String> queryAllPerms(Long userId);
	
	/**
	 * Example Query all menu ids of a user
	 */
	List<Long> queryAllMenuId(Long userId);
	
	/**
	 * Query system users by user name
	 */
	SysUserEntity queryByUserName(String username);
	
	/**
	 * modify password
	 */
	int updatePassword(Map<String, Object> map);
}
