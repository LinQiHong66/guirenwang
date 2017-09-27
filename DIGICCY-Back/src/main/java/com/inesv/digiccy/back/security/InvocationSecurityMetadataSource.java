package com.inesv.digiccy.back.security;

import com.inesv.digiccy.dto.auth.AuthRoleDto;
import com.inesv.digiccy.dto.auth.ResourceDto;
import com.inesv.digiccy.query.auth.QueryUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntUrlPathMatcher;
import org.springframework.security.web.util.UrlMatcher;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * 在服务器启动时加载资源权限
 * @author hwh
 *
 */
public class InvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource{
	
	private Logger logger = LoggerFactory.getLogger(InvocationSecurityMetadataSource.class);
	
	@Autowired
	QueryUser queryUserInfo;
	
	UrlMatcher urlMatcher = new AntUrlPathMatcher();
	/**
	 * key:url,value:权限
	 */
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	
	/**
	 * 这个类放到spring容器后，指定init为初始化方法,从数据库中读取资源
	 */

	public InvocationSecurityMetadataSource() {
//		loadResourceDefine();
	}
	/**
	 * 加载数据库中角色和资源的对应关系
	 */
	private void loadResourceDefine() {
		logger.info("begin加载数据库中的角色和权限对应关系");
		//在web服务器启动时,提取系统中所有权限
		List<AuthRoleDto> roleList = queryUserInfo.queryRole();
	
		resourceMap = new LinkedHashMap<String, Collection<ConfigAttribute>>();
		
		for(AuthRoleDto role : roleList) {         //遍历数据库中存在的角色
			ConfigAttribute ca = new SecurityConfig((String) role.getName());
			Number roleId = (Number) role.getId();
			//获得所拥有的权限(uri)
			List<ResourceDto> resources = queryUserInfo.queryRoleResource(roleId.intValue());
			
			for(ResourceDto resource : resources) {
				String url = (String) resource.getValue();
				
				if(resourceMap.containsKey(url)) {
					//获得原来的集合
					Collection<ConfigAttribute> value = resourceMap.get(url);
					value.add(ca);
					resourceMap.put(url, value);
				} else {  //首次添加的key
					Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
					atts.add(ca);
					resourceMap.put(url, atts);
				}
			}
		}
		logger.info("finish加载数据库中的角色和权限对应关系");
//		logger.info(resourceMap.toString());
	}
	
	/*
	 * 获取当前请求所需角色 列表
	 * object :用于获取请求url
	 * 
	 * **/
	@Override 
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
	    
	    loadResourceDefine();
		
	    String requestUrl = ((FilterInvocation)object).getRequestUrl();
		URI uri = null;
			
		try{
			uri = new URI(requestUrl);
		} catch (URISyntaxException e){
			e.printStackTrace();
		}
		String requestRawPath = uri.getRawPath();	
			
		
        Iterator<String> ite = resourceMap.keySet().iterator();
        Collection<ConfigAttribute> authority = new ArrayList<ConfigAttribute>();
        while (ite.hasNext()) {
         
        	String resourceURL = ite.next();
        	
        	boolean isMatch = urlMatcher.pathMatchesUrl(resourceURL, requestRawPath);
        	if(isMatch) {
        		authority.addAll(resourceMap.get(resourceURL));
        	}
        	
        }
        
		return authority;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		
		return true;
	}
}
