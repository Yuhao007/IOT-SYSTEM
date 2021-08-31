package com.learn.dao;

import com.learn.entity.SysMenuEntity;

import java.util.List;

/**
 * 菜menu
 * 
 *
 *
 * @date #9##1118 9:33:01
 */
public interface SysMenuDao extends BaseDao<SysMenuEntity> {
	
	/**
	 *Query submenus according to the parent menu
	 * * @param parentId ID of the parent menu
	 */
	List<SysMenuEntity> queryListParentId(Long parentId);
	
	/**
	 * Gets a list of menus that do not contain buttons
	 */
	List<SysMenuEntity> queryNotButtonList();
	
	/**
	 * Example Query the permission list of a user
	 */
	List<SysMenuEntity> queryUserList(Long userId);
}
