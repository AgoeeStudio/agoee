package com.agoee.ua.persistence.dao;

import com.agoee.ua.persistence.pojo.PojoAccount;

public interface IAccountDao {

    public void insert(PojoAccount account);

    public PojoAccount selectById(int id);

    public PojoAccount selectByUuid(String uuid);

    public PojoAccount selectByUsername(String username);

}
