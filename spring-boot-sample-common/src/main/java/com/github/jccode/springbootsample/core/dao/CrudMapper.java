package com.github.jccode.springbootsample.core.dao;

@SuppressWarnings({"InterfaceNeverImplemented", "MybatisMapperMethodInspection"})
public interface CrudMapper<T> {

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    int deleteByPrimaryKey(Integer id);
}
