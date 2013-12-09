package com.agoee.ua.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agoee.ua.persistence.dao.IAccountDao;
import com.agoee.ua.persistence.pojo.AccountPojo;

/**
 * AccountService
 * 
 * @author lxp
 *
 */
@Service
public class AccountService {

    @Autowired
    private IAccountDao accountDao;

	@Transactional
    public void register(AccountPojo account) throws UniqueIdentityException {
        int count = accountDao.countByUsername(account.getUsername());
        if(count > 0) {
            throw new UniqueIdentityException("user[" + account.getUsername() + "] was already existed.");
        }
        count = accountDao.countByEmail(account.getEmail());
        if(count > 0) {
            throw new UniqueIdentityException("email[" + account.getEmail() + "] was already used.");
        }
        account.setUuid(UUID.randomUUID().toString());
        accountDao.insert(account);

    }

    public AccountPojo getAccountByName(String username) {
        return accountDao.selectByUsername(username);
    }
}
