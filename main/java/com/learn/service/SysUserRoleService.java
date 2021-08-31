package com.learn.service;

import java.util.List;



/**
 * Mapping between users and roles
 * 
 *
 *
 * @date #9##1118   9:43:24
 */
public interface SysUserRoleService {
	
	void saveOrUpdate(Long userId, List<Long> roleIdList);
	
	/**
	 * Obtain a list of role ids based on user ids
	 */
	List<Long> queryRoleIdList(Long userId);
	
	void delete(Long userId);
}
