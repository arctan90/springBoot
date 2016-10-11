package com.irisking.util;

import org.hibernate.cfg.DefaultNamingStrategy;
import org.springframework.stereotype.Component;


public class UserNamingStrategy extends DefaultNamingStrategy {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2820121473406593608L;
	public static final UserNamingStrategy instance = new UserNamingStrategy();
	
	/**
	 * 根据uid
	 * @param uid
	 * @return
	 */
	public String classToTableName(Long uid) {
		Long tableNum = uid % 100;
		return "biz_" + tableNum.toString();
	}
}
