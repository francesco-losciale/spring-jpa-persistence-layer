package com.main.test;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class AppSpringSecurityAuditorAware implements AuditorAware<String> {

	@Override
	public String getCurrentAuditor() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		User springSecurityUser = (User) securityContext.getAuthentication().getPrincipal();
		return springSecurityUser.getUsername();
	}

}