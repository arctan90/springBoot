package com.irisking.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLogger {
    private  static final Logger log = LoggerFactory.getLogger(MyLogger.class);
    public static void log(String msg) {
        log.info(msg);
    }
}
