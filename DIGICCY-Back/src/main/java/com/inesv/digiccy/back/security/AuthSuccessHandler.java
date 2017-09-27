package com.inesv.digiccy.back.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆成功时的handler
 * @author pxw
 *
 */
public class AuthSuccessHandler implements AuthenticationSuccessHandler{
	
	private String defaultTargetUrl;  //默认url
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	/**
	 * 成功时重定向到defaultTargetUrl
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException{
		//记录登陆成功的日志
		this.redirectStrategy.sendRedirect(request, response, this.defaultTargetUrl);  
	}
	
	public void setDefaultTargetUrl(String defaultTargetUrl) {
		
		        this.defaultTargetUrl = defaultTargetUrl;  
	}
	
	
}
