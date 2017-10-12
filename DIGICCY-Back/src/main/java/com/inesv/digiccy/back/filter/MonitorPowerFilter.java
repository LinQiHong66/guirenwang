package com.inesv.digiccy.back.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.inesv.digiccy.dto.PowerInfoDto;
import com.inesv.digiccy.dto.ProwerParamDto;
import com.inesv.digiccy.validata.PowerRecordValidata;

@Component
public class MonitorPowerFilter implements Filter {
	private CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	// 用来储存要记录的权限url
	private final static String[] powerUrls = { "user/updateUserPass.do", "user/updateUserInfo.do",
			"userOrganization/updateOrganization.do", "user/updateUserState.do", "rmb/doRecharge.do",
			"rmb/doWithdraw.do", "param/addStaticParam.do", "param/updateStaticParam.do", "file/executeExcel.do" };
	// 权限url参数的说明
	private final static HashMap<String, String> paramInfo = new HashMap<>();
	// 权限url的说明
	private final static HashMap<String, String> urlInfo = new HashMap<>();
	static {
		// 存储 user/updateUserPass.do 权限地址的说明和参数说明
		String key1 = "user/updateUserPass.do" + "/" + "no";
		String key2 = "user/updateUserPass.do" + "/" + "pass";
		String key3 = "user/updateUserPass.do" + "/" + "deal";
		urlInfo.put("user/updateUserPass.do", "修改用户密码");
		paramInfo.put(key1, "用户编号");
		paramInfo.put(key2, "重置的密码");
		paramInfo.put(key3, "重复输入密码");

		// 存储 user/updateUserInfo.do 权限地址的说明和参数说明
		String key4 = "user/updateUserInfo.do" + "/" + "no";
		String key5 = "user/updateUserInfo.do" + "/" + "mail";
		urlInfo.put("user/updateUserInfo.do", "修改用户信息");
		paramInfo.put(key4, "用户编号");
		paramInfo.put(key5, "用户邮箱");

		// 存储 userOrganization/updateOrganization.do 权限地址的说明和参数说明
		String key6 = "userOrganization/updateOrganization.do" + "/" + "id";
		String key7 = "userOrganization/updateOrganization.do" + "/" + "userNo";
		String key8 = "userOrganization/updateOrganization.do" + "/" + "org_type";
		urlInfo.put("userOrganization/updateOrganization.do", "机构升级");
		paramInfo.put(key6, "id主键");
		paramInfo.put(key7, "用户编号");
		paramInfo.put(key8, "申请等级");

		// 存储 user/updateUserState.do 权限地址的说明和参数说明
		String key9 = "user/updateUserState.do" + "/" + "no";
		String key10 = "user/updateUserState.do" + "/" + "state";
		urlInfo.put("user/updateUserState.do", "冻结/解封用户");
		paramInfo.put(key9, "用户编号");
		paramInfo.put(key10, "状态");

		// 存储 rmb/doRecharge.do 权限地址的说明和参数说明
		String key11 = "rmb/doRecharge.do" + "/" + "ordId";
		urlInfo.put("rmb/doRecharge.do", "人民币充值到账");
		paramInfo.put(key11, "订单编号");

		// 存储rmb/doWithdraw.do 权限地址的说明和参数说明
		String key12 = "rmb/doWithdraw.do" + "/" + "recId";
		String key13 = "rmb/doWithdraw.do" + "/" + "name";
		String key14 = "rmb/doWithdraw.do" + "/" + "price";
		urlInfo.put("rmb/doWithdraw.do", "人民币提现到账");
		paramInfo.put(key12, "提现记录id");
		paramInfo.put(key13, "用户编号");
		paramInfo.put(key14, "提现金额");

		// 存储param/addStaticParam.do 权限地址的说明和参数说明
		String key15 = "param/addStaticParam.do" + "/" + "param";
		String key16 = "param/addStaticParam.do" + "/" + "value";
		urlInfo.put("param/addStaticParam.do", "新增后台参数");
		paramInfo.put(key15, "后台参数名称");
		paramInfo.put(key16, "后台参数值");

		// 存储param/updateStaticParam.do 权限地址的说明和参数说明
		String key17 = "param/updateStaticParam.do" + "/" + "id";
		String key18 = "param/updateStaticParam.do" + "/" + "param";
		String key19 = "param/updateStaticParam.do" + "/" + "value";
		urlInfo.put("param/updateStaticParam.do", "修改后台参数");
		paramInfo.put(key17, "后台参数id");
		paramInfo.put(key18, "后台参名称");
		paramInfo.put(key19, "后台参数值");

		// 存储file/executeExcel.do 权限地址的说明和参数说明
		String key20 = "file/executeExcel.do" + "/" + "excelFile";
		String key21 = "file/executeExcel.do" + "/" + "fileType";
		String key22 = "file/executeExcel.do" + "/" + "addAddress";
		urlInfo.put("file/executeExcel.do", "批量处理");
		paramInfo.put(key20, "处理的文件");
		paramInfo.put(key21, "文件类型(regTemplate是批量注册，balanceTemplate是批量入金)");
		paramInfo.put(key22, "是否添加地址（批量注册用的）");
	}
	@Autowired
	PowerRecordValidata powerRecordValidata;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		String url = req.getRequestURI();
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) req.getSession()
				.getAttribute("SPRING_SECURITY_CONTEXT");
		String userName = "--";
		try {
			userName = securityContextImpl.getAuthentication().getName();
		} catch (Exception e) {

		}
		// 如果过滤的url为空则让他直接通过
		if (url == null) {
			// 让请求通过
			chain.doFilter(request, response);
			return;
		}
		// 监控特定权限，并记录

		if (hasAdd(url)) {

			PowerInfoDto infoDto = new PowerInfoDto();
			// 判断是不是文件上传的类型
			String type = req.getContentType();
			if (type.contains("multipart/form-data")) {
				MultipartHttpServletRequest multipartReq = multipartResolver.resolveMultipart(req);
				Enumeration<String> enumParam = multipartReq.getParameterNames();
				ArrayList<ProwerParamDto> params = new ArrayList<>();
				
				while (enumParam.hasMoreElements()) {
					String paramName = enumParam.nextElement();
					String paramInfo = getParamInfo(url, paramName);
					String paramValue = multipartReq.getParameter(paramName);

					ProwerParamDto paramDto = new ProwerParamDto();
					paramDto.setParamInfo(paramInfo);
					paramDto.setParamName(paramName);
					paramDto.setParamValue(paramValue);
					params.add(paramDto);
				}
				infoDto.setParams(params);
				//重新给request赋值
				request = multipartReq;
			} else {

				Enumeration<String> enumParam = req.getParameterNames();
				ArrayList<ProwerParamDto> params = new ArrayList<>();
				while (enumParam.hasMoreElements()) {
					String paramName = enumParam.nextElement();
					String paramInfo = getParamInfo(url, paramName);
					ProwerParamDto paramDto = new ProwerParamDto();
					String paramValue = req.getParameter(paramName);
					paramDto.setParamInfo(paramInfo);
					paramDto.setParamName(paramName);
					paramDto.setParamValue(paramValue);
					params.add(paramDto);
				}
				infoDto.setParams(params);
			}

			infoDto.setUrl(url);
			infoDto.setInfo(getUrlInfo(url));
			infoDto.setTime(new Date(System.currentTimeMillis()));
			infoDto.setUserName(userName);

			// 添加记录
			powerRecordValidata.addPowerInfo(infoDto);
		}
		
		// 让请求通过
		chain.doFilter(request, response);
	}

	// 用来判断是否记录某条url的操作
	private boolean hasAdd(String url) {
		boolean has = false;
		for (String power : powerUrls) {
			if (url != null && (url.equals(power) || url.contains(power))) {
				has = true;
				break;
			}
		}
		return has;
	}

	// 根据url和参数获取该参数的说明
	private String getParamInfo(String url, String paramName) {
		String info = "未知";

		String url_power = "";
		for (String power : powerUrls) {
			if (url != null && (url.equals(power) || url.contains(power))) {
				url_power = power;
				break;
			}
		}
		info = paramInfo.get(url_power + "/" + paramName);
		if (info == null) {
			info = "未知";
		}
		return info;
	}

	// 获取url的权限说明
	private String getUrlInfo(String url) {
		String info = "未知";
		String url_power = "";
		for (String power : powerUrls) {
			if (url != null && (url.equals(power) || url.contains(power))) {
				url_power = power;
				break;
			}
		}

		info = urlInfo.get(url_power);
		if (info == null) {
			info = "未知";
		}
		return info;
	}

	@Override
	public void destroy() {

	}
}
