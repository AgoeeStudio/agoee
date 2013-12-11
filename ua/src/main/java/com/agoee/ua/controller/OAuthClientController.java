package com.agoee.ua.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.BaseClientDetails;
import org.springframework.security.oauth2.provider.ClientAlreadyExistsException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.JdbcClientDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.agoee.ua.persistence.pojo.OAuthClientDetailsPojo;

@Controller
@RequestMapping(value="/oauth_client")
public class OAuthClientController {

	@Autowired
	private JdbcClientDetailsService clientDetailsService;
	
    @RequestMapping(method = RequestMethod.GET)
    public String getRootView(Model model) {
        model.addAttribute("oauthclient",new OAuthClientDetailsPojo());
        return "oauth_client";
    }
    
    @RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(Model model, @Valid @ModelAttribute("oauthclient") OAuthClientDetailsPojo oAuthClientDetails, BindingResult result) {
		if (result.hasErrors()) {
			return "oauth_client";
		}
		BaseClientDetails clientDetails = new BaseClientDetails();
		try {
			clientDetails.setAccessTokenValiditySeconds(oAuthClientDetails.getAccess_token_validity());
			clientDetails.setRefreshTokenValiditySeconds(oAuthClientDetails.getRefresh_token_validity());
			
			Set<String> authorities = new HashSet<String>(Arrays.asList(oAuthClientDetails.getAuthorities()));
			List<GrantedAuthority> gas = AuthorityUtils.createAuthorityList(authorities.toArray(new String[authorities.size()]));
			
			clientDetails.setAuthorities(gas);
			clientDetails.setAuthorizedGrantTypes(Collections.singleton(oAuthClientDetails.getAuthorized_grant_types()));
			clientDetails.setClientId(oAuthClientDetails.getClient_id());
			clientDetails.setClientSecret(oAuthClientDetails.getClient_secret());
			Set<String> redirectUris = new HashSet<String>(Arrays.asList(oAuthClientDetails.getWeb_server_redirect_uri()));
			clientDetails.setRegisteredRedirectUri(redirectUris);
			clientDetails.setScope(Collections.singleton(oAuthClientDetails.getScope()));
			clientDetails.setResourceIds(Collections.singleton(oAuthClientDetails.getResource_ids()));
			
			clientDetailsService.addClientDetails(clientDetails);
		} catch (ClientAlreadyExistsException e) {
			e.printStackTrace();
            model.addAttribute("error",e.getMessage());
            return "oauth_client";
		}
		return "redirect:/oauth_client/" + clientDetails.getClientId();
	}
    
    @RequestMapping(value = "{clientid}", method = RequestMethod.GET)
	public String getClientDetailsView(@PathVariable	String clientid, Model model) {
		ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientid);
		if (clientDetails == null) {
			model.addAttribute("error", "no client was found.");
			return "oauth_client_detail_view";
		}
		model.addAttribute("clientDetails", clientDetails);
		return "oauth_client_detail_view";
	}
	
}
