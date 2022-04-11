package com.epam.architecture.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.epam.architecture.aspects")
@EnableAspectJAutoProxy
public class AOPConfig {
}
