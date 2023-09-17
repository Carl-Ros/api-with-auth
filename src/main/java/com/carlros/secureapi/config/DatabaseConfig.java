package com.carlros.secureapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:_database.yml", factory = YamlPropertySourceFactory.class)
public class DatabaseConfig {
}