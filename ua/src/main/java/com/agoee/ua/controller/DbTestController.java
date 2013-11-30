package com.agoee.ua.controller;

import com.agoee.ua.persistence.pojo.AccountPojo;
import com.agoee.ua.service.DbAccessTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Handles requests for the DB access test.
 */
@Controller
public class DbTestController {

    @Autowired
    private DbAccessTestService dbAccessTestService;

    @RequestMapping(value = "/db_test")
	public ResponseEntity<String> testDB(String opt, String username) throws IOException, ServletException {

        StringBuilder sb = new StringBuilder();
        if( "insert".equals(opt) ) {
            sb.append("insert record username=" + username + "\n");
            dbAccessTestService.insert(username);
            sb.append("insert sucess\n");
        }
        else if( "query".equals(opt) ) {
            sb.append("query record username=" + username + "\n");
            AccountPojo account = dbAccessTestService.query(username);
            if( account == null ) {
                sb.append("query no result\n");
            } else {
                sb.append("query result record:\n" + account.toString() + "\n");
            }
        }
        else {
            sb.append("args format error, MUST likes ${url}?opt=insert|query&useername=${username}\n");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "text/plain; charset=UTF-8");
        return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
	}

}
