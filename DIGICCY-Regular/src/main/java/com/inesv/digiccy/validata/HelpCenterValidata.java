package com.inesv.digiccy.validata;

import com.inesv.digiccy.api.command.HelpCenterCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.HelpCenterDto;
import com.inesv.digiccy.query.QueryHelpCenterInfo;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/9 0009.
 */
@Component
public class HelpCenterValidata {

	@Autowired
	QueryHelpCenterInfo queryHelpCenterInfo;

	@Autowired
	private CommandGateway commandGateway;

	/**
	 * 增加
	 * 
	 * @return
	 */
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public Map<String, Object> validataAddHelpCenter(String help_name, String help_grade, String help_parent,
			String help_remark) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			HelpCenterCommand helpCenter = new HelpCenterCommand(0L, help_name, Integer.valueOf(help_grade),
					Long.valueOf(help_parent), help_remark, "insertHelpCenter");
			commandGateway.send(helpCenter);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	/**
	 * 修改
	 * 
	 * @return
	 */
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public Map<String, Object> validataEditHelpCenter(String id, String help_name, String help_grade,
			String help_parent, String help_remark) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			HelpCenterCommand helpCenter = new HelpCenterCommand(Long.valueOf(id), help_name,
					Integer.valueOf(help_grade), Long.valueOf(help_parent), help_remark, "updateHelpCenter");
			commandGateway.send(helpCenter);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public Map<String, Object> validataDeleteHelpCenter(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			HelpCenterCommand helpCenter = new HelpCenterCommand(Long.valueOf(id), "", 0, 0L, "", "deleteHelpCenter");
			commandGateway.send(helpCenter);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	/**
	 * 取得所有
	 * 
	 * @return
	 */
	public Map<String, Object> validataAllHelpCenter() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<HelpCenterDto> helpCenterList = queryHelpCenterInfo.queryAllHelpCenter();
		if (helpCenterList != null) {
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("total", helpCenterList.size());
			map.put("data", helpCenterList);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	/**
	 * 取得一级所有
	 * 
	 * @return
	 */
	public Map<String, Object> validataAllHelpCenterByOne() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<HelpCenterDto> helpCenterList = queryHelpCenterInfo.queryAllHelpCenterByOne();
		List<HelpCenterDto> helpCenterTwoList = new ArrayList<>();
		for (int i = 0; i < helpCenterList.size(); i++) {
			helpCenterTwoList = queryHelpCenterInfo
					.queryAllHelpCenterByTwo(String.valueOf(helpCenterList.get(i).getId()));
			helpCenterList.get(i).setAttr1(helpCenterTwoList);
		}
		if (helpCenterList != null) {
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("total", helpCenterList.size());
			map.put("data", helpCenterList);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	/**
	 * 取得指定父类的二级所有
	 * 
	 * @return
	 */
	public Map<String, Object> validataAllHelpCenterByTwo(String parentId) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<HelpCenterDto> helpCenterList = queryHelpCenterInfo.queryAllHelpCenterByTwo(parentId);
		if (helpCenterList.size() != 0) {
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("total", helpCenterList.size());
			map.put("data", helpCenterList);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	/**
	 * 取得指定
	 * 
	 * @return
	 */
	public Map<String, Object> validataHelpCenter(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		HelpCenterDto helpCenterDto = queryHelpCenterInfo.queryHelpCenterInfo(id);
		if (helpCenterDto != null) {
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("data", helpCenterDto);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	/**
	 * 根据二级名称获取id
	 */
	public Map<String, Object> getIdByName(String name) {
		Integer ids = queryHelpCenterInfo.getIdsByTwoName(name);
		HashMap<String, Object> map = new HashMap<>();
		if (ids != null && ids != 0) {
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("data", ids);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
}
