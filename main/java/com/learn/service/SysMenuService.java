package com.learn.service;

import com.learn.entity.SysMenuEntity;

import java.util.List;
import java.util.Map;


/**
 * menu
 * 
 *
 *
 * @date #9##1118 9:42:16
 */
public interface SysMenuService {
	
	/**
	 * * According to the parent menu, query the child menu
	 * * @param parentId ID of the parent menu
	 * * @param menuIdList User menu ID
	 */
	List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList);
	
	/**
	 * Gets a list of menus that do not contain buttons
	 */
	List<SysMenuEntity> queryNotButtonList();
	
	/**
	 * Gets the user menu list
	 */
	List<SysMenuEntity> getUserMenuList(Long userId);
	
	
	/**
	 *Query menu
	 */
	SysMenuEntity queryObject(Long menuId);
	
	/**
	 *The list number of queries
	 */
	List<SysMenuEntity> queryList(Map<String, Object> map);
	
	/**
	 * The total number of queries
	 */
	int queryTotal(Map<String, Object> map);
	
	/**
	 *
	 */
	void save(SysMenuEntity menu);
	
	/**
	 *
	 */
	void update(SysMenuEntity menu);
	
	/**
	 *
	 */
	void deleteBatch(Long[] menuIds);
	
	/**
	 * Example Query the permission list of a user
	 */
	List<SysMenuEntity> queryUserList(Long userId);
}
