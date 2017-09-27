package com.inesv.digiccy.back.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Iterator;

public class MyAccessDecisionManager implements AccessDecisionManager {

	/**检查用户是否够权限访问资源。
	 * 如果该页面不需要权限访问，则直接结束
	 * authentication : 从spring的全局缓存SecurityContextHolder中拿到的，里面是用户的权限信息
	 * object : 是URL
	 * configAttributes :访问该资源所需要的权限
	 */
	@Override
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		System.out.println("---1" + authentication);
		System.out.println("---2" + configAttributes);
		if(configAttributes == null) {
			throw new AccessDeniedException("access deny");
		}
		//访问该uri所需要的角色 列表
		 Iterator<ConfigAttribute> cons = configAttributes.iterator();
	        while(cons.hasNext()){
	            ConfigAttribute ca = cons.next();
	            String needRole=((SecurityConfig)ca).getAttribute(); //访问该资源所需要的权限
	            for(GrantedAuthority ga:authentication.getAuthorities()){ //gra:该用户拥有的权限


	            	System.out.println("<<<<<<<<<<<<<<<< ga "+ga);
	                if(needRole.equals(ga.getAuthority())){  
	                	//放行
	                	return;
	                }
	            }
	        }
	        throw new AccessDeniedException("access deny");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
