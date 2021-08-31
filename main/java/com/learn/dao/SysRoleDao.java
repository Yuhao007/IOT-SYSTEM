package com.learn.dao;

import com.learn.entity.SysRoleEntity;

import java.util.List;

/**
 * role
 * 
 *
 *
 * @date #9##1118 9:33:33
 */
public interface SysRoleDao extends BaseDao<SysRoleEntity> {
	
	/**
	 * Example Query the role ids created by the user
	 */
	List<Long> queryRoleIdList(Long createUserId);
}
