package com.main.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan(basePackages = {"com.manager"})
@ImportResource({"classpath:context_sviluppo.xml", "classpath:hibernate.xml"})
public class AppConfig {

}
