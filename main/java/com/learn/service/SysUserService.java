package com.learn.service;

import com.learn.entity.SysUserEntity;

import java.util.List;
import java.util.Map;


/**
 * sys user
 * 
 *
 *
 * @date #9##1118  :43:39
 */
public interface SysUserService {
	
	/**
	 * Query all permissions of a user
	 * @param userId userId
	 */
	List<String> queryAllPerms(Long userId);
	
	/**
	 * Example Query all menu ids of a user
	 */
	List<Long> queryAllMenuId(Long userId);
	
	/**
	 * * Query system users by user name
	 */
	SysUserEntity queryByUserName(String username);
	
	/**
	 * Query users by user ID
	 * @param userId
	 * @return
	 */
	SysUserEntity queryObject(Long userId);
	
	/**
	 * Query the user list
	 */
	List<SysUserEntity> queryList(Map<String, Object> map);
	
	/**
	 *
	 */
	int queryTotal(Map<String, Object> map);
	
	/**
	 *
	 */
	void save(SysUserEntity user);
	
	/**
	 *
	 */
	void update(SysUserEntity user);
	
	/**
	 *
	 */
	void deleteBatch(Long[] userIds);
	
	/**
	 * Change password
	 * @param userId userId
	 * @param password Old password
	 * @param newPassword newPassword
	 */
	int updatePassword(Long userId, String password, String newPassword);
}
