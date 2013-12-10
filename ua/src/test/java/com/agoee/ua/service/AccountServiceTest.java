package com.agoee.ua.service;

import java.util.Date;
import java.util.UUID;

import junit.framework.Assert;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.agoee.ua.persistence.pojo.AccountPojo;

/**
 * AccountService Test Class
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/applicationContext.xml",
		"file:src/main/webapp/WEB-INF/spring/spring-security.xml" })
@Transactional
public class AccountServiceTest {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private SqlSessionFactoryBean sqlSessionFactoryBean;
	
	@Test
	@Rollback
	public void testGetAccountByName() throws Exception {
		AccountBuilder builder = new AccountBuilder();
		AccountPojo expected = null;
		AccountPojo actual = null;
		
		// insert record
		builder.setUuid(UUID.randomUUID().toString())
				.setUsername("userA")
				.setPassword("1234")
				.setEmail("userA@gmail.com")
				.setVerifyCode("3561")
				.setCreateTime(new Date());
		expected = builder.build();
		SqlSession sqlSession = SqlSessionUtils.getSqlSession(sqlSessionFactoryBean.getObject());
		sqlSession.insert("com.agoee.ua.persistence.dao.IAccountDao.insert", expected);

		// assert inserted fields
		actual = accountService.getAccountByName(expected.getUsername());
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getUsername(), actual.getUsername());
		Assert.assertEquals(expected.getPassword(), actual.getPassword());
		Assert.assertEquals(expected.getEmail(), actual.getEmail());
		Assert.assertEquals(expected.getVerifyCode(), actual.getVerifyCode());
		Assert.assertEquals(expected.getUuid(), actual.getUuid());
		// mysql将毫秒省略
		Assert.assertEquals(expected.getCreateTime().getTime()/1000, actual.getCreateTime().getTime()/1000);
		
		// update record
		builder.setUsername("userB")
				.setPassword("4567")
				.setEmail("userB@gmail.com")
				.setVerifyCode("");
		expected = builder.build();
		sqlSession.update("com.agoee.ua.persistence.dao.IAccountDao.update", expected);
		
		// assert updated fields
		actual = accountService.getAccountByName(expected.getUsername());
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getUsername(), actual.getUsername());
		Assert.assertEquals(expected.getPassword(), actual.getPassword());
		Assert.assertEquals(expected.getEmail(), actual.getEmail());
		Assert.assertEquals(expected.getVerifyCode(), actual.getVerifyCode());
		Assert.assertEquals(expected.getUuid(), actual.getUuid());
		Assert.assertEquals(expected.getCreateTime().getTime()/1000, actual.getCreateTime().getTime()/1000);
		
		// delete record
		sqlSession.update("com.agoee.ua.persistence.dao.IAccountDao.delete", expected.getUuid());
		
		actual = accountService.getAccountByName(expected.getUsername());
		Assert.assertNull(actual);
	}

}
