package com.learn.dao;

import com.learn.entity.SysRoleMenuEntity;

import java.util.List;

/**
 * Mapping between roles and menus
 * 
 *
 *
 * @date #9##11189:33:46
 */
public interface SysRoleMenuDao extends BaseDao<SysRoleMenuEntity> {
	
	/**
	 * Obtain the menu ID list based on the role ID
	 */
	List<Long> queryMenuIdList(Long roleId);
}
