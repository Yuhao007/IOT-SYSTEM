package com.learn.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * sys user
 * 
 *
 *
 * @date #9##1118  9:28:55
 */
public class SysUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * ID
	 */
	private Long userId;

	/**
	 *
	 */
	private String username;

	/**
	 *
	 */
	private transient String password;

	/**
	 * 邮箱
	 */
//	@NotBlank(message="error", groups = {AddGroup.class, UpdateGroup.class})
//	@Email(message="error", groups = {AddGroup.class, UpdateGroup.class})
	private String email;

	/**
	 * mobile number
	 */
	private String mobile;

	/**
	 * Status 0: Disabled 1: Normal
	 */
	private Integer status;
	
	/**
	 *
	 */
	private List<Long> roleIdList;
	
	/**
	 *
	 */
	private Long createUserId;

	/**
	 *
	 */
	private Date createTime;

	/**
	 * set：
	 * @param userId 
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * get：
	 * @return Long
	 */
	public Long getUserId() {
		return userId;
	}
	
	/**
	 * set
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * get
	 * @return String
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * set
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * get
	 * @return String
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * set
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * get
	 * @return String
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * set
	 * @param mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * get
	 * @return String
	 */
	public String getMobile() {
		return mobile;
	}
	
	/**
	 * Status 0: disabled 1: Normal
	 * * @param status Status 0: disabled 1: Normal
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * Obtain: Status 0: disabled 1: Normal
	 * @return Integer
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * set
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * get
	 * @return Date
	 */
	public Date getCreateTime() {
		return createTime;
	}

	public List<Long> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<Long> roleIdList) {
		this.roleIdList = roleIdList;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	
}
