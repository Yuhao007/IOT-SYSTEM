package com.learn.service;

import com.learn.entity.SysRoleEntity;

import java.util.List;
import java.util.Map;


/**
 * role
 * 
 *
 *
 * @date #9##1118  9:42:52
 */
public interface SysRoleService {
	
	SysRoleEntity queryObject(Long roleId);
	
	List<SysRoleEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysRoleEntity role);
	
	void update(SysRoleEntity role);
	
	void deleteBatch(Long[] roleIds);
	
	/**
	 * Example Query the role ids created by the user
	 */
	List<Long> queryRoleIdList(Long createUserId);
}
