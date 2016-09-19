package com.risking.util;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
import com.risking.configuration.RedisHttpSessionConfig;

public class SpringSessionInitializer extends AbstractHttpSessionApplicationInitializer{
    public SpringSessionInitializer() {
        super(RedisHttpSessionConfig.class);
    }
}
