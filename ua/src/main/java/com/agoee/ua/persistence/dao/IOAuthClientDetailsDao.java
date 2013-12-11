package com.agoee.ua.persistence.dao;

import com.agoee.ua.persistence.pojo.OAuthClientDetailsPojo;

public interface IOAuthClientDetailsDao {

	public void insert(OAuthClientDetailsPojo clientDetail);

	public void update(OAuthClientDetailsPojo clientDetail);

	public void delete(String clientId);

	public OAuthClientDetailsPojo selectByClientId(String clientId);

}
