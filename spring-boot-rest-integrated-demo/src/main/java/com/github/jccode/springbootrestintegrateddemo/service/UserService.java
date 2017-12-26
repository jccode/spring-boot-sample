package com.github.jccode.springbootrestintegrateddemo.service;

import com.github.jccode.springbootrestintegrateddemo.model.UserCriteria;
import com.github.jccode.springbootrestintegrateddemo.repo.UserMapper;
import com.github.jccode.springbootrestintegrateddemo.model.User;
import com.github.jccode.springbootsample.core.repo.CrudMapper;
import com.github.jccode.springbootsample.core.service.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends CrudServiceImpl<User> {

    @Autowired
    private UserMapper mapper;

    @Autowired
    public UserService(CrudMapper<User> mapper) {
        super(mapper);
    }

    public int count() {
        return (int) mapper.countByExample(new UserCriteria());
    }

    public List<User> findByName(String name) {
        UserCriteria criteria = new UserCriteria();
        criteria.createCriteria().andNameEqualTo(name);
        return mapper.selectByExample(criteria);
    }
}
