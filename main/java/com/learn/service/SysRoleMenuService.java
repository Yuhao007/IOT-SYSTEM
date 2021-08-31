package com.learn.service;

import java.util.List;



/**
 * Mapping between roles and menus
 * 
 *
 *
 * @date #9##1118 9:42:30
 */
public interface SysRoleMenuService {
	
	void saveOrUpdate(Long roleId, List<Long> menuIdList);
	
	/**
	 * Obtain the menu ID list based on the role ID
	 */
	List<Long> queryMenuIdList(Long roleId);
	
}
