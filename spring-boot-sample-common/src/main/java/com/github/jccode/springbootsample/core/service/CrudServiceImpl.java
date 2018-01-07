package com.github.jccode.springbootsample.core.service;

import com.github.jccode.springbootsample.core.repo.CrudMapper;
import com.github.jccode.springbootsample.core.model.BaseEntity;

import java.time.chrono.ChronoLocalDateTime;
import java.util.Date;

public class CrudServiceImpl<T extends BaseEntity> implements CrudService<T> {

    private final CrudMapper<T> mapper;

    public CrudServiceImpl(CrudMapper<T> mapper) {
        this.mapper = mapper;
    }

    public T find(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public int save(T t) {
        Integer id = t.getId();
        int ret;
        if (id == null || id == 0) {
            Date now = new Date();
            t.setCreateTime(now);
            t.setUpdateTime(now);
            ret = mapper.insert(t);
        } else {
            if (t.getUpdateTime() == null) {
                t.setUpdateTime(new Date());
            }
            ret = mapper.updateByPrimaryKey(t);
        }
        return ret;
    }

    public int updateSelective(T t) {
        return mapper.updateByPrimaryKeySelective(t);
    }

    public int delete(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }
}
