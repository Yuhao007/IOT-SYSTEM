package com.learn.dao;

import java.util.List;
import java.util.Map;

/**Base Dao(also in XML file, with corresponding SQL statements)基础Dao(还需在XML文件里，有对应的SQL语句)
 * 
 *
 *
 * @date #9##1118日 上午9:31:36
 */
public interface BaseDao<T> {
	
	void save(T t);
	
	void save(Map<String, Object> map);
	
	void saveBatch(List<T> list);
	
	int update(T t);
	
	int update(Map<String, Object> map);
	
	int delete(Object id);
	
	int delete(Map<String, Object> map);
	
	int deleteBatch(Object[] id);

	T queryObject(Object id);


	List<T> queryList(Map<String, Object> map);
	
	List<T> queryList(Object id);
	
	int queryTotal(Map<String, Object> map);

	int queryTotal();
}
