package com.agoee.ua.service;

import java.util.Date;

import com.agoee.ua.persistence.pojo.AccountPojo;

public class AccountBuilder {

	private String uuid;

	private String username;

	private String password;

	private String email;

	private String verifyCode;

	private Date createTime;

	public AccountBuilder setUsername(String username) {
		this.username = username;
		return this;
	}

	public AccountBuilder setUuid(String uuid) {
		this.uuid = uuid;
		return this;
	}

	public AccountBuilder setPassword(String password) {
		this.password = password;
		return this;
	}

	public AccountBuilder setEmail(String email) {
		this.email = email;
		return this;
	}

	public AccountBuilder setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
		return this;
	}

	public AccountBuilder setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public AccountPojo build() {
		AccountPojo account = new AccountPojo();
		account.setUuid(uuid);
		account.setUsername(username);
		account.setPassword(password);
		account.setEmail(email);
		account.setVerifyCode(verifyCode);
		account.setCreateTime(createTime);
		return account;
	}

}
