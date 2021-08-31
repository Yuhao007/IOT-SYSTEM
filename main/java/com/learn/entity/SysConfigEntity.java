package com.learn.entity;


import org.hibernate.validator.constraints.NotBlank;

/**
 * sys info
 * 
 *
 *
 * @date 114 6:43:36
 */
public class SysConfigEntity {
	private Long id;
	@NotBlank(message="The parameter name cannot be empty")
	private String key;
	@NotBlank(message="The parameter name cannot be empty")
	private String value; 
	private String remark;
	private String min;

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
