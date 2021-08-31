package com.learn.service;

import com.learn.entity.DataEntity;

import java.util.List;
import java.util.Map;

/**
 * monitoring data
 *
 *
 *
 */
public interface DataService {
    /**
    * query
	* @return
	*/
	DataEntity queryObject(Long id);

    /**
    * query list
    * @return
    */
	List<DataEntity> queryList(Map<String, Object> map);

    /**
    * query total
    * @return
    */
	int queryTotal(Map<String, Object> map);

    /**
    * save
    * @return
    */
	void save(DataEntity data);

    /**
    * update
    * @return
    */
	void update(DataEntity data);

    /**
    * delete
    * @return
    */
	void delete(Long id);

    /**
    * delete batch
    * @return
    */
	void deleteBatch(Long[] ids);
}
