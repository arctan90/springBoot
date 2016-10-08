package com.irisking.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.irisking.bean.Response;
import com.irisking.dao.UserDao;
import com.irisking.orm.UserOrm;
import com.irisking.requestbody.LoginRequest;
import com.irisking.requestbody.RegistRequest;


@RestController
@RequestMapping(value = "/api")
public class Api {
	@Autowired
	private UserDao dao;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> login(@RequestBody LoginRequest login, HttpServletRequest request) {
	    Map<String, Object> resultMap = new HashMap<>();
	    int nameHash = login.getName().hashCode(); //用hash分表
	    
	    String mString = "ok";
	    int code = 0;
	    String name = login.getName();
	    String pwd = login.getPwd();
	    //后端数据校验
	    if (name == null || pwd == null) {
	    	code = 2;
	    	mString = "参数错误";

	    } else {
	    	int len = pwd.length();
	    	int eLen = name.length();
	    	if (len < 6 || len > 30 || eLen < 6 || eLen > 30 
	    			|| name.indexOf("@") < 0) {
		    	code = 2;
		    	mString = "参数错误";
	    	} else {
			    UserOrm userOrm = dao.findByName(name);
			    if (userOrm == null) {
			    	code = 3;
			    	mString = "用户不存在";
			    } else if (!userOrm.getPwd().equals(pwd)){
			    	code = 4;
			    	mString = "密码错误";
			    } else {
			    	HttpSession session = request.getSession();
			    	session.getId();
			    	resultMap.put("sid", session.getId());
			    }
	    	}
	    }
	    
	    resultMap.put("code", code);
	    resultMap.put("msg", mString);
	    
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
	    //后端数据校验
	    String email = input.getEmail();
	    String pwd = input.getPwd();
	    if (email == null || pwd == null) {
	    	response.setCode(2);
	    	response.setMsg("参数错误");
	    	return response;
	    } else {
	    	int len = pwd.length();
	    	int eLen = email.length();
	    	if (len < 6 || len > 30 || eLen < 6 || eLen > 30 
	    			|| email.indexOf("@") < 0) {
		    	response.setCode(2);
		    	response.setMsg("参数错误");
		    	return response;
	    	}
	    	//sql注入攻击
//	    	if (email.indexOf(";")>=0 || email.indexOf("--")>=0
//	    			|| pwd.indexOf(";")>=0 || pwd.indexOf("--")>=0){
//	    		return response;
//	    	}
	    }
	    
	    UserOrm userOrm = new UserOrm();
	    userOrm.setName(email);
	    userOrm.setPwd(pwd);
	    UserOrm dbResult = null;

	    try {
	    	dbResult = dao.save(userOrm);
	    }catch (Exception e) {
			// TODO: handle exception
	    	String errorMsg = showDBInfo(e);
	    	System.out.println(errorMsg);
	    	if (errorMsg.indexOf("Duplicate entry") >= 0) {
	    		response.setMsg("用户已存在");
	    		response.setCode(1);
	    	}
		}
	    
	    return response;
	}

	private String showDBInfo(Throwable e) {
		Throwable next = e.getCause();
		String msg = "";
		if (next == null) {
			return e.getMessage();  
        } else {  
        	msg = showDBInfo(next);
        }
		return msg;
	}
}
