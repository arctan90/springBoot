package com.irisking.requestbody;

public class LoginRequest {

    private String name;
    private String pwd;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
    @Override
    public String toString() {
    	StringBuilder stringBuilder = new StringBuilder();
    	stringBuilder.append("用户名:")
    		.append(name).append(" 密码:").append(pwd);
    	return stringBuilder.toString();
    }
}
