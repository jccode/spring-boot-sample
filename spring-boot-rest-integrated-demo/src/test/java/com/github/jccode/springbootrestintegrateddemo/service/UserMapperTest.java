package com.github.jccode.springbootrestintegrateddemo.service;

import com.github.jccode.springbootrestintegrateddemo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.*;
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

    @Test
    public void count() {
        int count = userService.count();
        assertThat(count, greaterThan(0));
    }

    @Test
    @Rollback(false)
    public void save() {
        User user = randomUser();
        int c = userService.count();

        // Insert
        int ret1 = userService.save(user);
        assertThat(ret1, greaterThan(0));
        int id = user.getId();
        assertThat(id, greaterThan(0));

        // Assert equals
        User user2 = userService.find(id);
        assertThat(user2.getName(), is(user.getName()));
        assertThat(user2.getAge(), is(user.getAge()));

        int c2 = userService.count();
        assertThat(c2, equalTo(c + 1));

        // Delete
        int ret = userService.delete(id);
        assertThat(ret, greaterThan(0));

        int c3 = userService.count();
        assertThat(c3, equalTo(c));
    }

    @Test
    @Rollback(false)
    public void updateSelective() {
        User user = randomUser();

        // Insert
        int ret1 = userService.save(user);
        assertThat(ret1, greaterThan(0));
        int id = user.getId();
        assertThat(id, greaterThan(0));

        // Update user name
        String name = String.valueOf(Math.random());
        User cond = new User();
        cond.setId(id);
        cond.setName(name);
        int ret2 = userService.updateSelective(cond);
        assertThat(ret2, greaterThan(0));

        User user2 = userService.find(id);
        assertThat(user2.getName(), is(name));
        assertThat(user2.getName(), not(is(user.getName())));
        assertThat(user2.getAge(), is(user.getAge()));

        // Delete
        userService.delete(id);
    }

    @Test
    public void findByName() {
        List<User> hello = userService.findByName("hello");
        assertThat(hello.size(), greaterThan(0));
        assertThat(hello.get(0).getName(), is("hello"));
    }

    private User randomUser() {
        String randomName = String.valueOf(Math.random());
        int age = 22;
        User user = new User();
        user.setName(randomName);
        user.setPassword("pass");
        user.setAge(age);
        return user;
    }

}
