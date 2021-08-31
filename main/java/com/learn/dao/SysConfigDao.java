package com.learn.dao;

import com.learn.entity.SysConfigEntity;

import org.apache.ibatis.annotations.Param;

/**
 * System Configuration information
 *
 *
 * @date 114 6:46:16
 */
public interface SysConfigDao extends BaseDao<SysConfigEntity> {
	
	/**
	 * Query value based on key
	 */
	String queryByKey(String paramKey);
	
	/**
	 * Update value based on key
	 */
	int updateValueByKey(@Param("key") String key, @Param("value") String value);
	
}
