package com.github.jccode.springcloud.integrateddemo.account.service;

import com.github.jccode.springbootsample.core.repo.CrudMapper;
import com.github.jccode.springbootsample.core.service.CrudServiceImpl;
import com.github.jccode.springcloud.integrateddemo.account.model.Account;
import com.github.jccode.springcloud.integrateddemo.account.model.AccountCriteria;
import com.github.jccode.springcloud.integrateddemo.account.repo.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService extends CrudServiceImpl<Account> {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    public AccountService(CrudMapper<Account> mapper) {
        super(mapper);
    }

    public Optional<Integer> getBalance(int userId) {
        AccountCriteria example = new AccountCriteria();
        example.createCriteria().andUserIdEqualTo(userId);
        List<Account> accounts = accountMapper.selectByExample(example);
        return accounts.stream().findFirst().map(Account::getBalance);
    }
}
