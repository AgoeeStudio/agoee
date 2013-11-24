package com.agoee.ua.service;

import java.util.ArrayList;

import com.agoee.ua.persistence.dao.DaoAccount;
import com.agoee.ua.persistence.pojo.PojoAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 
 * 
 * AccountService
 * @author lxp
 *
 */
@SuppressWarnings("deprecation")
public class AccountService implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    private DaoAccount daoAccount;

	/**
	 * <p>
	 * ��OAuth2�ṩ������Ȩ����ʹ�����û����е�¼��֤֮ǰ����Ҫ�û����е�¼��֤���ɼ�����Ȩ <br />
	 * �˷���Ϊ�û���¼��֤ʱ��ȷ���û���Ϣ�Ƿ���ȷ��
	 * ����ʵ����{@code org.springframework.security.core.userdetails.UserDetailsService.loadUserByUsername}����
	 * </p>
	 * 
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        PojoAccount account = daoAccount.selectByUsername(username);
        System.out.println(account.toString());


		// test
		logger.info("loadUserByUsername: username=" + username);
		ArrayList<GrantedAuthority> gas = new ArrayList<GrantedAuthority>();
		GrantedAuthority ga = new GrantedAuthorityImpl("ROLE_USER");
		gas.add(ga);
		// User user = new User("marissa", "c18b8a4d7ecd3df6", gas);
		UserDetails user = new User(username, "koala", true, true, true, true, gas);
		return user;
	}

    public void setDaoAccount(DaoAccount daoAccount) {
        this.daoAccount = daoAccount;
    }
}
