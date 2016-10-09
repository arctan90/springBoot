package com.irisking.util;

public class Token {
	public static String getToken(String sessionId, String name) {
		StringBuilder sb = new StringBuilder();
		sb.append(sessionId).append(name);
		return MD5.md5(sb.toString());
	}
}
