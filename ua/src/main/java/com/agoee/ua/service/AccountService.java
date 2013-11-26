package com.agoee.ua.service;

import java.util.ArrayList;

import com.agoee.ua.persistence.dao.IAccountDao;
import com.agoee.ua.persistence.pojo.AccountPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 
 * 
 * AccountService
 * @author lxp
 *
 */
@SuppressWarnings("deprecation")
@Service
public class AccountService implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private IAccountDao accountDao;

	/**
	 * <p>
	 * 在OAuth2提供方，授权第三方使用其用户进行登录认证之前，需要用户进行登录认证方可继续授权 <br />
	 * 此方法为用户登录认证时，确认用户信息是否正确。
	 * 必须实现自{@code org.springframework.security.core.userdetails.UserDetailsService.loadUserByUsername}方法
	 * </p>
	 * 
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // test database access
        AccountPojo account = accountDao.selectByUsername(username);
        if( logger.isDebugEnabled() ) {
            logger.debug("DAO.selectByUsername: " + username + "account=" + account.toString());
        }

		// test
		logger.info("loadUserByUsername: username=" + username);
		ArrayList<GrantedAuthority> gas = new ArrayList<GrantedAuthority>();
		GrantedAuthority ga = new GrantedAuthorityImpl("ROLE_USER");
		gas.add(ga);
		// User user = new User("marissa", "c18b8a4d7ecd3df6", gas);
		UserDetails user = new User(username, "koala", true, true, true, true, gas);
		return user;
	}

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }
}
