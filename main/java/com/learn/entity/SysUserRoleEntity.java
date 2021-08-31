package com.learn.entity;


import java.io.Serializable;

/**
 * Mapping between users and roles
 * 
 *
 *
 * @date #9##1118  9:28:39
 */
public class SysUserRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;

	/**
	 *
	 */
	private Long userId;

	/**
	 *
	 */
	private Long roleId;

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
	 * set
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * get
	 * @return Long
	 */
	public Long getUserId() {
		return userId;
	}
	
	/**
	 * set
	 * @param roleId
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * ge5
	 * @return Long
	 */
	public Long getRoleId() {
		return roleId;
	}
	
}
