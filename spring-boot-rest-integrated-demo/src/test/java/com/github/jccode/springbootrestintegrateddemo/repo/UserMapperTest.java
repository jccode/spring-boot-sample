package com.github.jccode.springbootrestintegrateddemo.repo;

import com.github.jccode.springbootrestintegrateddemo.dao.UserMapper;
import com.github.jccode.springbootrestintegrateddemo.model.UserCriteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@MybatisTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void findUser() {
        long c = userMapper.countByExample(new UserCriteria());
        System.out.println(c);
    }
}
