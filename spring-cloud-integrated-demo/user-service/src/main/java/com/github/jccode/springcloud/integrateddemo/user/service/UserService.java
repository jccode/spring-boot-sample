package com.github.jccode.springcloud.integrateddemo.user.service;

import com.github.jccode.springbootsample.core.repo.CrudMapper;
import com.github.jccode.springbootsample.core.service.CrudServiceImpl;
import com.github.jccode.springcloud.integrateddemo.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends CrudServiceImpl<User> {

    @Autowired
    public UserService(CrudMapper<User> mapper) {
        super(mapper);
    }

}
