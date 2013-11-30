package com.agoee.ua.service;

import com.agoee.ua.persistence.dao.IAccountDao;
import com.agoee.ua.persistence.pojo.AccountPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.UUID;

/**
 * Db Access Test Servlet
 */
@Service
public class DbAccessTestService {

    @Autowired
    private IAccountDao accountDao;

    @Transactional
    public void insert(String username) {
        AccountPojo accountPojo = new AccountPojo();
        accountPojo.setUuid(UUID.randomUUID().toString());
        accountPojo.setUsername(username);
        accountPojo.setPassword("000000");
        accountPojo.setEmail("test@agoee.com");
        accountPojo.setCreateTime(System.currentTimeMillis());
        accountPojo.setVerifyCode(Calendar.getInstance().getTime().toString());
        accountDao.insert(accountPojo);
    }

    public AccountPojo query(String username) {
        return accountDao.selectByUsername(username);
    }

}
