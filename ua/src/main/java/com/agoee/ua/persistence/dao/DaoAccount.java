package com.agoee.ua.persistence.dao;

import com.agoee.ua.persistence.pojo.PojoAccount;

public interface DaoAccount {

    void insert(PojoAccount account);

    PojoAccount selectById(int id);

    PojoAccount selectByUuid(String uuid);

    PojoAccount selectByUsername(String username);

}
