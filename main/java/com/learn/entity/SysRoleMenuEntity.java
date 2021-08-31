package com.learn.entity;


import java.io.Serializable;

/**
 * Mapping between roles and menus
 * 
 *
 *
 * @date #9##1118 9:28:13
 */
public class SysRoleMenuEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;

	/**
	 * ID
	 */
	private Long roleId;

	/**
	 *
	 */
	private Long menuId;

	/**
	 * set：
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * get：
	 * @return Long
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * set：
	 * @param roleId 角色ID
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * get：
	 * @return Long
	 */
	public Long getRoleId() {
		return roleId;
	}
	
	/**
	 * set：
	 * @param menuId
	 */
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	/**
	 * get：
	 * @return Long
	 */
	public Long getMenuId() {
		return menuId;
	}
	
}
