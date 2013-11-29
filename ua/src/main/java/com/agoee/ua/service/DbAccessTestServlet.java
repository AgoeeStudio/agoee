package com.agoee.ua.service;

import com.agoee.ua.persistence.dao.IAccountDao;
import com.agoee.ua.persistence.pojo.AccountPojo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

/**
 * Db Access Test Servlet
 */
public class DbAccessTestServlet extends HttpServlet {

    @Autowired
    protected IAccountDao accountDao;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String arg = request.getParameter("opt");
        String username = request.getParameter("username");
        if( "insert".equals(arg) ) {
            response.getWriter().write("insert record username=" + username + "\n");
            AccountPojo accountPojo = new AccountPojo();
            accountPojo.setUuid(UUID.randomUUID().toString());
            accountPojo.setUsername(username);
            accountPojo.setPassword("000000");
            accountPojo.setEmail("test@agoee.com");
            accountPojo.setCreateTime(System.currentTimeMillis());
            accountPojo.setVerifyCode(Calendar.getInstance().getTime().toString());
            accountDao.insert(accountPojo);
            response.getWriter().write("insert sucess\n");
        }
        else if( "query".equals(arg) ) {
            response.getWriter().write("query record username=" + username + "\n");
            AccountPojo account = accountDao.selectByUsername("username");
            if( account == null ) {
                response.getWriter().write("query no result\n");
            } else {
                response.getWriter().write("query result record:\n" + account.toString() + "\n");
            }
        }
        else {
            response.getWriter().write("args format error, MUST likes ${url}?opt=insert|query&useername=${username}\n");
        }
    }

}
