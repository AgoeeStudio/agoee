package com.agoee.ua.controller;

import java.security.Principal;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agoee.ua.persistence.pojo.TestObj;

/**
 * Handles requests for test.
 */
@Controller
public class TestController {
	
	private static final Logger sLog = Logger.getLogger(TestController.class);
	
	@RequestMapping(value = "/test/get_json_response", method = RequestMethod.GET)
	@ResponseBody
	public TestObj testResponse() {
		return new TestObj();
	}

	/**
	 * 使用Firefox的HttpRquester插件测试
	 * 
	 * POST http://localhost:8080/agoee-ua/test/post_json_request
	 * Content-Type: application/json
	 * {"name":"name","value":"value"}
	 * 
	 *  -- response --
	 *  200 OK
	 *  Server:  Apache-Coyote/1.1
	 *  Content-Type:  text/plain;charset=UTF-8
	 *  Content-Length:  15
	 *  Date:  Fri, 13 Dec 2013 05:54:05 GMT
	 *  {"result":"ok"}
	 * 
	 * @param body
	 * @return
	 */
	@RequestMapping(value = {"/test/post_json_request"}, method = RequestMethod.POST)
	public ResponseEntity<String> test(@RequestBody TestObj  body) {
		sLog.info("name:" + body.getName() + "," + "value:" + body.getValue());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "text/plain; charset=UTF-8");
		StringBuilder out = new StringBuilder("{\"result\":\"ok\"}");
		return new ResponseEntity<String>(out.toString(), headers, HttpStatus.OK);
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
