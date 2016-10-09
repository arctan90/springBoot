package com.irisking.configuration;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 7200)
public class RedisHttpSessionConfig {

}
