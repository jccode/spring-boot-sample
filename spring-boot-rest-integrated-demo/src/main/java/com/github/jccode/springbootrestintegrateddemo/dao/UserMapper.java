package com.github.jccode.springbootrestintegrateddemo.dao;

import com.github.jccode.springbootrestintegrateddemo.model.User;
import com.github.jccode.springbootrestintegrateddemo.model.UserCriteria;
import com.github.jccode.springbootsample.core.dao.CrudMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends com.github.jccode.springbootsample.core.dao.CrudMapper<User> {
    long countByExample(UserCriteria example);

    int deleteByExample(UserCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserCriteria example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserCriteria example);

    int updateByExample(@Param("record") User record, @Param("example") UserCriteria example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}