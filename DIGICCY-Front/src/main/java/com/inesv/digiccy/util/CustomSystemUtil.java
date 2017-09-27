package com.inesv.digiccy.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UrlPathHelper;

/**
 * 系统工具类，用于获取系统相关信息
 * Created by kagome.
 */
public class CustomSystemUtil {
    public static String INTRANET_IP = getIntranetIp(); // 内网IP
    public static String INTERNET_IP = getInternetIp(); // 外网IP

    private CustomSystemUtil(){}

    /**
     * 获得内网IP
     * @return 内网IP
     */
    private static String getIntranetIp(){
        try{
            return InetAddress.getLocalHost().getHostAddress();
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 获得外网IP
     * @return 外网IP
     */
    private static String getInternetIp(){
        try{
            Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            Enumeration<InetAddress> addrs;
            while (networks.hasMoreElements())
            {
                addrs = networks.nextElement().getInetAddresses();
                while (addrs.hasMoreElements())
                {
                    ip = addrs.nextElement();
                    if (ip != null
                            && ip instanceof Inet4Address
                            && ip.isSiteLocalAddress()
                            && !ip.getHostAddress().equals(INTRANET_IP))
                    {
                        return ip.getHostAddress();
                    }
                }
            }

            // 如果没有外网IP，就返回内网IP
            return INTRANET_IP;
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    
    
    /** 
     * 获得当前访问路径 
     *  
     * HttpServletRequest.getRequestURL+"?"+HttpServletRequest.getQueryString 
     *  
     * @param request 
     * @return 
     */  
    public static String  getLocation(HttpServletRequest request)  
    {  
    	Map<String,Object> resultMap=new HashMap<>();
        UrlPathHelper helper = new UrlPathHelper();  
        StringBuffer buff = request.getRequestURL();  
        String uri = request.getRequestURI();  
        String origUri = helper.getOriginatingRequestUri(request);  
        buff.replace(buff.length() - uri.length(), buff.length(), origUri);  
        String queryString = helper.getOriginatingQueryString(request);  
        if (queryString != null)  
        {  
            buff.append("?").append(queryString);  
        }  
           
        return buff.toString();
    }  
 
}