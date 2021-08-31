package com.learn.dao;

import com.learn.entity.SysUserRoleEntity;

import java.util.List;

/**
 * Mapping between users and roles
 * 
 *
 *
 * @date #9##1118 9:34:46
 */
public interface SysUserRoleDao extends BaseDao<SysUserRoleEntity> {
	
	/**
	 * Obtain a list of role ids based on user ids
	 */
	List<Long> queryRoleIdList(Long userId);
}
