package com.github.jccode.springcloud.integrateddemo.account.service;

import com.github.jccode.springbootsample.core.repo.CrudMapper;
import com.github.jccode.springbootsample.core.service.CrudServiceImpl;
import com.github.jccode.springcloud.integrateddemo.account.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService extends CrudServiceImpl<Account> {

    @Autowired
    public AccountService(CrudMapper<Account> mapper) {
        super(mapper);
    }
}
