package com.learn.service.impl;

import com.learn.dao.DataDao;
import com.learn.entity.DataEntity;
import com.learn.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

//The Impl file is the implementation of the API
@Service("dataService")
//This class implements this interface and the methods in it have logic
public class DataServiceImpl implements DataService {
    @Autowired
    private DataDao dataDao;

    @Override
    public DataEntity queryObject(Long id) {
        // The DAO base library functions are called to manipulate the database

        DataEntity entity = dataDao.queryObject(id);
        return entity;
    }

    // This is where the dao is called
    @Override
    public List<DataEntity> queryList(Map<String, Object> map) {
        List<DataEntity> list = dataDao.queryList(map);
        for (DataEntity entity : list) {
        }
        return list;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return dataDao.queryTotal(map);
    }

    @Override
    public void save(DataEntity data) {
        dataDao.save(data);
    }

    @Override
    public void update(DataEntity data) {
        dataDao.update(data);
    }

    @Override
    public void delete(Long id) {
        dataDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        dataDao.deleteBatch(ids);
    }

}
