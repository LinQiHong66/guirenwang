package com.inesv.digiccy.controller;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.common.autocreate.annotation.AutoDocMethod;
import com.inesv.digiccy.common.autocreate.annotation.AutoDocMethodParam;
import com.inesv.digiccy.common.autocreate.bean.DeveloperType;
import com.inesv.digiccy.common.autocreate.bean.ModelType;
import com.inesv.digiccy.common.autocreate.bean.ProgressType;
import com.inesv.digiccy.common.autocreate.bean.VersionType;
import com.inesv.digiccy.common.dto.BaseRes;
import com.inesv.digiccy.dto.NoticeDto;
import com.inesv.digiccy.dto.NoticeTypeDto;
import com.inesv.digiccy.query.QueryNoticeInfo;
import com.inesv.digiccy.validata.CommonValidata;
import com.inesv.digiccy.validata.NoticeValidate;
import com.inesv.digiccy.validata.coin.CoinValidata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/9 0009.
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {
	@Autowired
	private QueryNoticeInfo queryNoticeInfo;

	@Autowired
	NoticeValidate noticeValidate;

	@Autowired
	CommonValidata commonValidata;

	/*
	 * 查询指定类型的公告列表(1:公告 -2：新闻 3：常见问题4其他（-1-2-3-4-5-6.。。。。。）) lqh 2017-7-14
	 */
	@RequestMapping(value = "getNoticeByType", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getNoticeByType(@RequestParam String type) {
		Map<String, Object> map = noticeValidate.queryNoticeInfoByType(type);
		return map;
	}

	/*
	 * 首页公告列表(1:公告 2：新闻 3：常见问题) lqh 2017-7-14
	 */
	@RequestMapping(value = "getNoticeByTypeShouYe", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getNoticeByTypeShouYe(@RequestParam String type) {
		Map<String, Object> map = noticeValidate.queryNoticeShouYe(type);
		return map;
	}

	/*
	 * 根据id获取公告 lqh 2017-7-14
	 */
	@RequestMapping(value = "getNoticeById", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getNoticeById(@RequestParam Integer id) {
		Map<String, Object> map = noticeValidate.queryNoticeInfoById(id);
		return map;
	}

	/*
	 * 获取最新公告 lqh 2017-7-14
	 */
	@RequestMapping(value = "getNoticeOne", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getNoticeOne() {
		Map<String, Object> map = noticeValidate.queryNoticeOneInfo();
		return map;
	}

	@RequestMapping(value = "/selectNotice", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectNotice(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<NoticeDto> list = queryNoticeInfo.queryNoticeInfo();
		if (!list.isEmpty()) {
			map.put("code", ResponseCode.SUCCESS);
			map.put("msg", ResponseCode.SUCCESS_DESC);
			map.put("noticeList", list);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("msg", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	/**
	 * 首页获取币种类型（除人民币外），调用不需token值
	 * 
	 * @param request
	 * @return
	 */
	/*
	 * @AutoDocMethod(author = DeveloperType.CHENWAIQING, createTime =
	 * "2016-12-8",cver = VersionType.V100, name = "首页获取币种类型", description =
	 * "首页获取币种类型（除人民币外），调用不需token值接口", model = ModelType.OTHER, dtoClazz =
	 * BaseRes.class,progress = ProgressType.TESTING)
	 * 
	 * @AutoDocMethodParam(note = "", name = "")
	 */
	@RequestMapping(value = "getBasicCoinTypeExceptRMB", method = RequestMethod.POST)
	public @ResponseBody Map<Object, Object> getBasicCoinTypeExceptRMB(HttpServletRequest request) {
		return commonValidata.validateBasicCoinTypeExceptRMB();
	}

	/**
	 * 获取所有公告分类
	 */
	@RequestMapping(value = "getAllType", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAllType() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<NoticeTypeDto> list = new ArrayList<NoticeTypeDto>();
		NoticeTypeDto d1 = new NoticeTypeDto();
		d1.setId(1);
		d1.setName("公告");
		NoticeTypeDto d2 = new NoticeTypeDto();
		d2.setId(2);
		d2.setName("新闻");
		NoticeTypeDto d3 = new NoticeTypeDto();
		d3.setId(3);
		d3.setName("常见问题");

		list.add(d1);
		list.add(d2);
		list.add(d3);
		list.addAll(queryNoticeInfo.getNameType(1));
		
		if (!list.isEmpty()) {
			map.put("code", ResponseCode.SUCCESS);
			map.put("msg", ResponseCode.SUCCESS_DESC);
			map.put("typelist", list);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("msg", ResponseCode.FAIL_DESC);
		}
		return map;
	}
}
