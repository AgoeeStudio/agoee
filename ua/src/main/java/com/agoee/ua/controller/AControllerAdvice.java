package com.agoee.ua.ua.controller;

import org.springframework.http.HttpStatus;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ע��ʹ��ע��@ControllerAdvice��������ȫ��Controller��Χ
 * ��Ӧ�õ�����@RequestMapping��򷽷��ϵ�@ExceptionHandler��@InitBinder��@ModelAttribute����������@ExceptionHandler
 * 
 * @author lxp
 *
 */
@ControllerAdvice
public class AControllerAdvice {
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public String handleException(Exception ex) {
      return ClassUtils.getShortName(ex.getClass()) + " " + ex.getMessage();
  }
}
