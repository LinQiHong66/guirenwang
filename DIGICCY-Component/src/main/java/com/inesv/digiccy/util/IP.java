package com.inesv.digiccy.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;


/**
 * 获取用户ip
 *
 */
public class IP {
	
	/**
	 * 获取ip
	 * @param request
	 * @return
	 */
	public static String getIpAdress(HttpServletRequest request){
		 String ips = request.getHeader("x-forwarded-for"); 
	         if(ips == null || ips.length() == 0 || "unknown".equalsIgnoreCase(ips)) { 
	             ips = request.getHeader("Proxy-Client-IP"); 
	         } 
	         if(ips == null || ips.length() == 0 || "unknown".equalsIgnoreCase(ips)) { 
	             ips = request.getHeader("WL-Proxy-Client-IP"); 
	         } 
	         if(ips == null || ips.length() == 0 || "unknown".equalsIgnoreCase(ips)) { 
		             ips = request.getRemoteAddr(); 
	
		             if(ips.equals("127.0.0.1") || ips.equals("0:0:0:0:0:0:0:1")){
		        	       //根据网卡取本机配置的IP
		        	       InetAddress inet=null;
		        	    try {
		        		    inet = InetAddress.getLocalHost();
		        	    } catch (UnknownHostException e) {
		        		    e.printStackTrace();
		        	    }
		        	    ips= inet.getHostAddress();
		             }
	         } 
	         
	       //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if(ips!=null && ips.length()>15){ //"***.***.***.***".length() = 15
			if(ips.indexOf(",")>0){
				ips = ips.substring(0,ips.indexOf(","));
			}
		}
		return ips;
	}
}
