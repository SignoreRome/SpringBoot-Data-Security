package com.signore.SpringBootEx.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.signore.SpringBootEx")
//@EnableTransactionManagement
public class SpringConfig {
}
