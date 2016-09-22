package com.irisking.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.irisking.bean.Response;
import com.irisking.requestbody.RegistRequest;


@RestController
@RequestMapping(value = "/api")
public class Login {
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map<String, Object> login(HttpServletRequest request) {
	    Map<String, Object> resultMap = new HashMap<>();
	    
	    resultMap.put("code", 0);
	    resultMap.put("msg", "ok");
	    
		return resultMap;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		return session.getId();
	}
	
	@RequestMapping(value="/regist", method = RequestMethod.POST)
	public @ResponseBody Response<Object> regist(@RequestBody RegistRequest input, HttpServletRequest request) {
	    Response<Object> response = new Response<>();
	    
	    
	    
	    response.setPayload(input);
	    return response;
//		return request.getRequestedSessionId();
	}

}
