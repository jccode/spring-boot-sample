package com.github.jccode.springcloud.integrateddemo.user.service;

import com.github.jccode.springbootsample.core.repo.CrudMapper;
import com.github.jccode.springbootsample.core.service.CrudServiceImpl;
import com.github.jccode.springcloud.integrateddemo.user.model.User;
import com.github.jccode.springcloud.integrateddemo.user.model.UserCriteria;
import com.github.jccode.springcloud.integrateddemo.user.repo.UserMapper;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends CrudServiceImpl<User> {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    public UserService(CrudMapper<User> mapper) {
        super(mapper);
    }

    public List<User> findByName(String name) {
        Preconditions.checkNotNull(name);
        UserCriteria criteria = new UserCriteria();
        criteria.createCriteria().andNameEqualTo(name);
        return userMapper.selectByExample(criteria);
    }
}
