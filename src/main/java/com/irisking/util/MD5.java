package com.irisking.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static String md5(String input) {
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(input.getBytes());
            
            for (int index = 0 ; index < md5Bytes.length; index++) {
            	int val = ((int) md5Bytes[index]) & 0xff;  
                if (val < 16) {
                    sb.append("0"); 
                }
                sb.append(Integer.toHexString(val));
            } 
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
        	MyLogger.logException(e);
        } 
        return sb.toString();
    }
}
