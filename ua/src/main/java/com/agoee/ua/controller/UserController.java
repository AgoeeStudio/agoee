package com.agoee.ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agoee.ua.persistence.pojo.AccountPojo;
import com.agoee.ua.service.AccountService;
import com.agoee.ua.service.UniqueIdentityException;

/**
 * Handles requests for user
 */
@Controller
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET)
    public String toRegister(Model model) {
        model.addAttribute("user",new AccountPojo());
        return "register";
    }

//    @RequestMapping(value = "register", method = RequestMethod.POST)
//    public String register(Model model, @Valid @ModelAttribute( "user" )AccountPojo user, BindingResult result) {
//        if (result.hasErrors()) {
//            return "register";
//        }
//
//        try {
//            accountService.register(user);
//            return "redirect:/user/" + user.getUsername();
//        } catch (UniqueIdentityException e) {
//            e.printStackTrace();
//            model.addAttribute("error",e.getMessage());
//            return "register";
//        }
//    }

    @RequestMapping(value = "verify_username", method = RequestMethod.GET)
    @ResponseBody
	public int verifyUserName(@RequestParam(required = true) String username) {
		AccountPojo user = accountService.getAccountByName(username);
		if (user == null) {
			return 0;
		}
		return -1;
	}
    
    @RequestMapping(value = "register", method = RequestMethod.POST,produces = {"application/json"})
    @ResponseBody
    public String register(@RequestParam(required = true) String username,@RequestParam(required = true) String password,@RequestParam(required = true) String email) {
        try {
            AccountPojo user = new AccountPojo();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);

            accountService.register(user);
            return "{\"result\":\"success\"}";
        } catch (UniqueIdentityException e) {
            e.printStackTrace();
            return "{\"result\":\"error\"}";
        }
    }

    @RequestMapping(value = "{username}", method = RequestMethod.GET)
    public String getView(@PathVariable String username, Model model) {
        AccountPojo user = accountService.getAccountByName(username);
        if (user == null) {
            model.addAttribute("error", "no user was found.");
            return "user_view";
        }
        model.addAttribute("user",user);
        return "user_view";
    }
}