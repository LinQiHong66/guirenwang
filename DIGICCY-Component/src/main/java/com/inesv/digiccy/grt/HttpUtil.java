package com.inesv.digiccy.grt;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.web3j.protocol.core.methods.response.Log;

import com.alibaba.fastjson.JSON;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

/**
 * @author lin
 *
 */
public class HttpUtil {

	
	/**
	 * 发送Get请求
	 * @param url : 请求的连接
	 * @param params ： 请求参数，无参时传null
	 * @return
	 */
	public static String sendGet(String url,Map<String,String> params){
		HttpRequest request = HttpRequest.get(url);  
		if(params!=null) {
			request.query(params);
		} 
		HttpResponse response = request.send();
		String respJson = response.bodyText();
		return respJson;
	}
	
	
	/**
	 * 发送Post请求
	 * @param url : 请求的连接
	 * @param params ：  请求参数，无参时传null
	 * @param paramsDatails : 参数详情，没有时传null 
	 * @return
	 */
	public static String sendPost(String url,Map<String,Object> params ){
		HttpRequest request = HttpRequest.post(url);  
	 
		request.charset("utf-8");
  		 //参数详情
		 if(params!=null) {
			request.body(JSON.toJSONString(params));
		 }
    	  
		HttpResponse response = request.send();
		String respJson = response.bodyText();
		return respJson;
	}
	
	
	/**
	 * 发送Delete请求
	 * @param url : 请求的连接
	 * @param params ：  请求参数，无参时传null
	 * @return
	 */
	public static String sendDelete(String url,Map<String,Object> params){
		HttpRequest request = HttpRequest.delete(url);  
 
		 if(params!=null) {
			 request.form(params);
		  } 
		HttpResponse response = request.send();
		String respJson = response.bodyText();
		return respJson;
	}
	
 

	// 测试
	public static void main(String[] args) {
        //Get
		String responbody = HttpUtil.sendGet("https://www.baidu.com", null);
		System.out.println(responbody);
	}

}
