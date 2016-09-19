package com.risking.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Login {
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request){	
		return "zzz";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		return session.getId();
	}
	
	@RequestMapping("/regist")
	public String regist(HttpServletRequest request) {
		return request.getRequestedSessionId();
	}
}
