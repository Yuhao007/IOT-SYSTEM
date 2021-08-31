package com.learn.service;

import com.learn.entity.SysConfigEntity;

import java.util.List;
import java.util.Map;

/**
 * System Configuration information
 *
 *
 * @date 114  6:49:01
 */
//Service wraps the Impl implementation interface back up
// The controller can use the method in the interface after importing the service directly

public interface SysConfigService {
	
	/**
	 *
	 */
	public void save(SysConfigEntity config);
	
	/**
	 *
	 */
	public void update(SysConfigEntity config);
	
	/**
	 *
	 */
	public void updateValueByKey(String key, String value);
	
	/**
	 *
	 */
	public void deleteBatch(Long[] ids);
	
	/**
	 *
	 */
	public List<SysConfigEntity> queryList(Map<String, Object> map);
	/**
	 *
	 */
	public int queryTotal(Map<String, Object> map);
	
	public SysConfigEntity queryObject(Long id);
	
	/**
	 *
	 * 
	 * @param key   key
	 * @param defaultValue   Object
	 */
	public String getValue(String key, String defaultValue);
	
	/**
	 *
	 * @param key    key
	 * @param clazz  Object
	 */
	public <T> T getConfigObject(String key, Class<T> clazz);
	
}
