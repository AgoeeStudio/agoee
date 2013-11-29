package com.agoee.ua.controller;

import java.io.Serializable;
import java.security.Principal;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class TestController {
	private class Obj implements Serializable {
		private static final long serialVersionUID = -8806785348790167393L;
		@SuppressWarnings("unused")
		public String name = "key";
		@SuppressWarnings("unused")
		public String value = "value";
	}
	
	@RequestMapping(value = "/test")
	@ResponseBody
	public Obj home() {
		return new Obj();
	}
	
	/**
	 * api controller.
	 * 
	 * @param principal
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = {"/apis","/apis.json"})
	public ResponseEntity<String> getApis(Principal principal) throws Exception {
		StringBuilder out = new StringBuilder();
		out.append("{api1:1,api2:2,api3:3}").toString();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "text/plain; charset=UTF-8");
		return new ResponseEntity<String>(out.toString(), headers, HttpStatus.OK);
	}
}
