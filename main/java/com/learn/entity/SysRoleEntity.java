package com.learn.entity;


import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * role
 * 
 *
 *
 * @date #9##1118  9:27:38
 */
public class SysRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * ID
	 */
	private Long roleId;

	/**
	 * name
	 */
	@NotBlank(message="The role name cannot be empty")
	private String roleName;

	/**
	 *
	 */
	private String remark;
	
	/**
	 *
	 */
	private Long createUserId;
	
	private List<Long> menuIdList;
	
	/**
	 *
	 */
	private Date createTime;

	/**
	 * set
	 * @param roleId 
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * get
	 * @return Long
	 */
	public Long getRoleId() {
		return roleId;
	}
	
	/**
	 * set
	 * @param roleName
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * get
	 * @return String
	 */
	public String getRoleName() {
		return roleName;
	}
	
	/**
	 * set
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * get
	 * @return String
	 */
	public String getRemark() {
		return remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<Long> getMenuIdList() {
		return menuIdList;
	}

	public void setMenuIdList(List<Long> menuIdList) {
		this.menuIdList = menuIdList;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	
}
