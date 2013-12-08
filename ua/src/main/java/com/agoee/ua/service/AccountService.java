package com.agoee.ua.service;

import com.agoee.ua.persistence.dao.IAccountDao;
import com.agoee.ua.persistence.pojo.AccountPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * AccountService
 * 
 * @author lxp
 *
 */
@Service
public class AccountService {

	private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

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

        throw new UniqueIdentityException("test");
    }

    public AccountPojo getAccountByName(String username) {
        return accountDao.selectByUsername(username);
    }
}
