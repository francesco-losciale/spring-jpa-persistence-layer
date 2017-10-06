package main;


import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableJpaAuditing(auditorAwareRef="appSpringSecurityAuditorAware")
@EnableGlobalMethodSecurity(securedEnabled=true)
@ComponentScan(basePackages = {"com.repository","com.persistence.base","com.persistence.operation"})
public class AppConfig extends GlobalMethodSecurityConfiguration {
		
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		basicDataSource.setUrl("jdbc:mysql://address=(protocol=tcp)(host=localhost)(port=3306)/manager_db?useLegacyDatetimeCode=false&amp&serverTimezone=UTC");
		basicDataSource.setUsername("root");
		basicDataSource.setPassword("root");
		return basicDataSource;
	}
		
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setPersistenceUnitName("jpaUnit");
		entityManagerFactory.setDataSource(dataSource);
		entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactory.setJpaDialect(new HibernateJpaDialect());
		entityManagerFactory.setPackagesToScan("com.repository","com.entity");
		entityManagerFactory.setJpaPropertyMap(hibernateJpaProperties());
		return entityManagerFactory;
	}
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(emf);
		return jpaTransactionManager;
	}	
	
	@Bean
	public AppSpringSecurityAuditorAware appSpringSecurityAuditorAware() {
		return new AppSpringSecurityAuditorAware();
	}
	
	private Map<String, ?> hibernateJpaProperties() {
		HashMap<String, String> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "create"); 
		properties.put("hibernate.default_schema", "PUBLIC");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		//properties.put("hibernate.show_sql", "false");
		//properties.put("hibernate.format_sql", "false");
		//properties.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");

		//properties.put("hibernate.c3p0.min_size", "2");
		//properties.put("hibernate.c3p0.max_size", "5");
		//properties.put("hibernate.c3p0.timeout", "300"); // 5mins

		return properties;
	}
}
