package com.inesv.digiccy.filter;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.axonframework.contextsupport.spring.SpringContextParameterResolverFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jca.context.SpringContextResourceAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.inesv.digiccy.common.Constants;


public class PublicFilter extends HandlerInterceptorAdapter {
    
  @Autowired
  private RedisTemplate<String, Object> redisTemplate;
	
	/**
	 * 拦截器---每做一步操作都会经过这拦截器
	 * 
	 * 
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception{
		/**
		 * 请求令牌
		 */
	       try {
	          String token=request.getParameter("token");
              String user_token = (String) redisTemplate.opsForValue().get(token);
              if(user_token==null){
                PrintWriter writer = response.getWriter();
                writer.append("{\"code\":\""+Constants.SYS_FAILURE+"\",\"msg\":\"令牌已失效请重新登陆\"}");
                writer.flush();
                writer.close();
                return false;
               }
      		  if(!token.equals(user_token)){
      			response.setCharacterEncoding("utf-8");
      			response.setContentType("application/json;charset=UTF-8");
      			PrintWriter writer = response.getWriter();
      			writer.append("{\"code\":\""+Constants.SYS_FAILURE+"\",\"msg\":\"令牌不对\"}");
      			writer.flush();
      			writer.close();
      			return false;
      		  }
      		  /**
      		   *  保存最后操作时间
      		   */
      		redisTemplate.opsForValue().set(token+":lastTime", new Date(System.currentTimeMillis()));
	       }
           catch (Exception e) {
             response.setCharacterEncoding("utf-8");
             response.setContentType("application/json;charset=UTF-8");
             PrintWriter writer = response.getWriter();
             writer.append("{\"code\":\""+Constants.SYS_FAILURE+"\",\"msg\":\"请求令牌未加入\"}");
             writer.flush();
             writer.close();
             return false;
           }
    	  return true;
  }	
}
