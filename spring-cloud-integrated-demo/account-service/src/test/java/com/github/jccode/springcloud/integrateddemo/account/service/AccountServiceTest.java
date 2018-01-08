package com.github.jccode.springcloud.integrateddemo.account.service;

import com.github.jccode.springcloud.integrateddemo.account.model.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

@SpringBootTest
@RunWith(SpringRunner.class)
@MybatisTest
@Import(AccountService.class)
@ActiveProfiles("test")
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void mybatisWorks() {
        Account account = new Account();
        account.setName("Hello");
        account.setUserId(1);
        account.setBalance(10);
        assertThat(account.getId(), is(nullValue()));

        int save = accountService.save(account);

        assertThat(save, greaterThan(0));
        assertThat(account.getId(), greaterThan(0));
    }
}
