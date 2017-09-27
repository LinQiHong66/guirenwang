package com.inesv.digiccy.back.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AdminAuthenticationFailureHandler implements AuthenticationFailureHandler{

	/**
	 * 抛出异常时运行
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException{
		/*if(exception instanceof BadCredentialsException) {
			credentialError(request, response, "用户名密码错误");
		}else{*/
		  credentialError(request, response, "用户名密码错误");
//		}
	}
	
	/**
	 * 账号或者密码错误
	 */
	private static void credentialError(HttpServletRequest request, HttpServletResponse response, String message){
		request.setAttribute("ErrorMessage", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		try{
			dispatcher.forward(request, response);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
}
