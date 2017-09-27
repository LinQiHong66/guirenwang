package com.inesv.digiccy.controller;

import java.io.DataInput;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisClusterNode.LinkState;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.inesv.digiccy.aggregate.HomeImgAggregate;
import com.inesv.digiccy.query.QueryHomeImg;
import com.inesv.digiccy.validata.ContactValidata;
import com.inesv.digiccy.validata.HelpCenterValidata;
import com.inesv.digiccy.validata.HomeImgValidate;
import com.inesv.digiccy.validata.LinkValidate;
import com.inesv.digiccy.validata.NoticeValidate;
import com.inesv.digiccy.validata.VedioValidata;
import com.inesv.digiccy.validata.VoteValidata;

/**
 * 
 * @author LiuKeling
 *
 */
@Controller
@RequestMapping("/other")
public class OtherController {
	@Autowired
	HomeImgValidate homeImgValidate;
	
	@Autowired
	LinkValidate linkValidate;
	
	@Autowired
	HelpCenterValidata helpCenterValidata;
	
	@Autowired
	VedioValidata vedioValidata;
	
	@Autowired
	ContactValidata contactValidata; 
	//首页轮播图
	@RequestMapping(value="getHomeImg", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getHomeImg(){
		return homeImgValidate.getAllHomeImg();
	}
	//联系我们
	@RequestMapping(value="getAllContact", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAllContact(){
		return contactValidata.getAllContact("","");
	}
	//友情链接
	@RequestMapping(value="getAllLink", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAllLink(){
		return linkValidate.getGroupLink();
	}
	//获取首页视频
	@RequestMapping(value="getHomeVedio", method=RequestMethod.POST)
	@ResponseBody
	public Map<String , Object> getHomeVedio(){
		return vedioValidata.getHomeVedio();
	}
	//获取充值步骤
	@RequestMapping(value="getpayrecovery",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getPayRecovery(String name){
		return helpCenterValidata.getIdByName(name);
	}
	
}
