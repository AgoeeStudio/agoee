package com.agoee.ua.service;

/**
 * Created with IntelliJ IDEA.
 * User: nasya
 * Date: 13-12-6
 * Time: 上午12:11
 * To change this template use File | Settings | File Templates.
 */
public class UniqueIdentityException extends Exception {

    public UniqueIdentityException(String message) {
        super(message);
    }

    public UniqueIdentityException(String message,Throwable e) {
        super(message,e);
    }
}
