package com.github.jccode.springbootrestintegrateddemo.service;

import com.github.jccode.springbootrestintegrateddemo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@MybatisTest
@Import(UserService.class)
public class UserMapperTest {

    @Autowired
    private UserService userService;

    @Test
    public void findUser() {
        User user = userService.find(1);
        assertThat(user.getName(), is("hello"));
        assertThat(user.getAge(), is(18));
    }
}
