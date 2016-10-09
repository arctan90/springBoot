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
import com.irisking.util.Token;


@RestController
@RequestMapping(value = "/api")
public class Api {
	@Autowired
	private UserDao dao;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> login(@RequestBody LoginRequest login, HttpServletRequest request) {
	    Map<String, Object> resultMap = new HashMap<>();
	    String mString = "ok";
	    int code = 0;
	    String name = login.getName();
	    String pwd = login.getPwd();
	    
	    int nameHash = login.getName().hashCode(); //用hash分表
	    
	    //生成token：name+sessionId
	    HttpSession session = request.getSession();
    	String token = Token.getToken(session.getId(), name);
	    
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
			    } else { //成功登录
			    	
			    	userOrm.setToken(token);
			    	userOrm.setTimeStamp(System.currentTimeMillis()/1000);
			    	try {
			    		dao.save(userOrm);
			    	} catch (Exception e) {
						// TODO: handle exception
			    		e.printStackTrace();
					}
			    	resultMap.put("token", token);
			    	
			    	//token写入redis
			    	session.setAttribute("token", token);
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
	
	/**
	 * 
	 * @param input
	 * @param request
	 * @return Response code 0正常；1用户已存在; 2参数错误；3数据库异常
	 */
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
	    }
	    
	    UserOrm userOrm = new UserOrm();
	    userOrm.setName(email);
	    userOrm.setPwd(pwd);
	    
		UserOrm dbResult = null;

	    try {
	    	dbResult = dao.save(userOrm);
	    	if (dbResult == null) {
	    		response.setMsg("数据库异常");
	    		response.setCode(3);
	    	}
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
