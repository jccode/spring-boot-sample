package com.github.jccode.springbootsample.core.service;

import com.github.jccode.springbootsample.core.repo.CrudMapper;
import com.github.jccode.springbootsample.core.model.BaseEntity;

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
        if (id != null && id != 0) {
            mapper.insert(t);
        } else {
            mapper.updateByPrimaryKey(t);
        }
        return 0;
    }

    public int updateSelective(T t) {
        return mapper.updateByPrimaryKeySelective(t);
    }

    public int delete(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }
}
