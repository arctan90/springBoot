package com.irisking.util;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

import com.irisking.configuration.RedisHttpSessionConfig;


public class SpringSessionInitializer extends AbstractHttpSessionApplicationInitializer{
    public SpringSessionInitializer() {
        super(RedisHttpSessionConfig.class);
    }
}
