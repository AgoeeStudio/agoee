package com.agoee.ua.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import com.agoee.ua.persistence.dao.IOAuthClientDetailsDao;

/**
 * CustomerUserDetailsService
 * 
 * @author lxp
 *
 */
@Service
public class CustomerClientDetailsService implements ClientDetailsService {

    @Autowired
    private IOAuthClientDetailsDao authClientDetailsDao;

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		return null;
	}
}
