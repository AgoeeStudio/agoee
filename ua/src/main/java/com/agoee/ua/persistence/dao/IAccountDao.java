package com.agoee.ua.persistence.dao;

import com.agoee.ua.persistence.pojo.AccountPojo;

public interface IAccountDao {

    public void insert(AccountPojo account);

    public AccountPojo selectById(int id);

    public AccountPojo selectByUuid(String uuid);

    public AccountPojo selectByUsername(String username);

}
