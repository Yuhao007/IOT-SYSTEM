package com.learn.dao;

import com.learn.entity.DataEntity;

import java.util.Map;

/**
 *
 *
 *
 *
 *
 */
public interface DataDao extends BaseDao<DataEntity> {
    DataEntity last(Map<String, Object> params);
}
