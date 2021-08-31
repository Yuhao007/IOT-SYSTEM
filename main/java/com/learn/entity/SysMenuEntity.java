package com.learn.entity;


import java.io.Serializable;
import java.util.List;

/**
 * menu
 * 
 *
 *
 * @date #9##1118 9:26:39
 */
public class SysMenuEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 *  menuID
	 */
	private Long menuId;

	/**
	 * ID of the parent menu. The first-level menu is 0
	 */
	private Long parentId;
	
	/**
	 * Parent menu name
	 */
	private String parentName;

	/**
	 *  Menu name
	 */
	private String name;

	/**
	 *    Menu  URL
	 */
	private String url;

	/**
	 * Authorization (multiple users are separated by commas, for example,user: list,user:create)
	 */
	private String perms;

	/**
	 * Type 0: directory 1: menu 2: button
	 */
	private Integer type;

	/**
	 *
	 */
	private String icon;

	/**
	 * order
	 */
	private Integer orderNum;
	
	/**
	 * ztree
	 */
	private Boolean open;
	
	private List<?> list;

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Long getMenuId() {
		return menuId;
	}
	
	/**
	 *Setting: parent menu ID, level 1 menu is 0
	 * * @param parentId Indicates the ID of the parent menu. The level-1 menu is 0
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 *Get: parent menu ID, level 1 menu is 0
	 * * @return Long
	 */
	public Long getParentId() {
		return parentId;
	}
	
	/**
	 * Settings: Menu name
	 * * @param name Menu name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get: Menu name
	 * * @return String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Settings: menu URL
	 * * @param URL Menu URL
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 *Get: menu URL
	 * * @return String
	 */
	public String getUrl() {
		return url;
	}
	
	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * Settings: Menu icon
	 * * @param icon Menu icon
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * Get: menu icon
	 * * @return String
	 */
	public String getIcon() {
		return icon;
	}
	
	/**
	 * Settings: Sort
	 * * @param orderNum orderNum
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * Get: sort
	 * * @return Integer
	 */
	public Integer getOrderNum() {
		return orderNum;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}
}
