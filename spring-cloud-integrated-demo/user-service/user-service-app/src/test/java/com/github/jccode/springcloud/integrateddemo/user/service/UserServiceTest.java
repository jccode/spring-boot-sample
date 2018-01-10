package com.github.jccode.springcloud.integrateddemo.user.service;


import com.github.jccode.springcloud.integrateddemo.user.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.OrderingComparison.greaterThan;

@SpringBootTest
@RunWith(SpringRunner.class)
@MybatisTest
@Import(UserService.class)
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    private User createTestUser() {
        User user = new User();
        user.setName("junit_test_user_name");
        user.setMobile("18100001111");
        user.setPassword("junit_test_user_pass");
        user.setSalt("junit_test_user_pwd_salt");
        return user;
    }

    @Test
    @Rollback(false)
    public void mybatisWorks() {
        User testUser = createTestUser();
        assertThat(testUser.getId(), is(nullValue()));

        int insertResult = userService.save(testUser);
        assertThat(insertResult, greaterThan(0));
        Integer id = testUser.getId();
        assertThat(id, greaterThan(0));

        User user = userService.find(id);
        assertThat(user.getName(), is(testUser.getName()));
        assertThat(user.getMobile(), is(testUser.getMobile()));

        int deleteResult = userService.delete(id);
        assertThat(deleteResult, greaterThan(0));

        User user2 = userService.find(id);
        assertThat(user2, is(nullValue()));
    }
}
