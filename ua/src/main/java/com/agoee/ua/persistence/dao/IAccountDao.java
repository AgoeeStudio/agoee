package com.agoee.ua.persistence.dao;

import com.agoee.ua.persistence.pojo.AccountPojo;

public interface IAccountDao {

    public void insert(AccountPojo account);
    
    public void update(AccountPojo account);
    
    public void delete(String uuid);

    public AccountPojo selectById(int id);

    public AccountPojo selectByUuid(String uuid);

    public AccountPojo selectByUsername(String username);

    public int countByUsername(String username);

    public int countByEmail(String email);
}
