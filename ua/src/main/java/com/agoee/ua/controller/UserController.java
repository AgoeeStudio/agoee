package com.agoee.ua.controller;

import com.agoee.ua.persistence.pojo.AccountPojo;
import com.agoee.ua.service.AccountService;
import com.agoee.ua.service.UniqueIdentityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

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

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(Model model, @Valid @ModelAttribute( "user" )AccountPojo user, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }

        try {
            accountService.register(user);
            user.setUsername("");
            user.setEmail("");
            user.setPassword("");
        } catch (UniqueIdentityException e) {
            e.printStackTrace();
            model.addAttribute("error",e.getMessage());
            return "register";
        }

        return "redirect:/user/" + user.getUsername();
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