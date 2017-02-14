package com.main.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan(basePackages = {"com.manager","commons"})
@ImportResource({"classpath:persistence-beans.xml", "classpath:hibernate-beans.xml"})
public class AppConfig {

}
