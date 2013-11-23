package org.agoee.ua.controller;

import org.springframework.http.HttpStatus;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 注意使用注解@ControllerAdvice作用域是全局Controller范围
 * 可应用到所有@RequestMapping类或方法上的@ExceptionHandler、@InitBinder、@ModelAttribute，在这里是@ExceptionHandler
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
