package com.github.jccode.springbootsample.core.service;

public interface CrudService<T> {

    T find(Integer id);

    int save(T t);

    int updateSelective(T t);

    int delete(Integer id);
}
