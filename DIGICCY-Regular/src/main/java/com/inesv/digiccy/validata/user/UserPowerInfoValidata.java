package com.inesv.digiccy.validata.user;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.PowerInfoDto;
import com.inesv.digiccy.query.QueryPowerInfo;

@Component
public class UserPowerInfoValidata {
	private static Logger log = LoggerFactory.getLogger(UserPowerInfoValidata.class);

	@Autowired
	QueryPowerInfo queryPowerInfo;
	
	//获取访问详情
	public Map<String, Object> getPowerBasicInfo(int powerInfoId){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PowerInfoDto dto = queryPowerInfo.getBasicInfo(powerInfoId);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("code", ResponseCode.SUCCESS);
			map.put("result", dto);
		} catch (SQLException e) {
			e.printStackTrace();
			log.debug("-----------查询用户log详细信息失败-------");
			log.debug(e.getMessage());

			map.put("desc", ResponseCode.FAIL_DESC);
			map.put("code", ResponseCode.FAIL);
		}
		return map;
	}
	
	// 获取权限访问记录
	public Map<String, Object> getUserPowerInfo(String url, String info, String userName, String startDate,
			String endDate, int curPage, int pageItem, String orderName, String orderType) {
		Map<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat forMat = new SimpleDateFormat("yyyy-MM-dd");
		Date startD = null;
		Date endD = null;
		try {
			startD = forMat.parse(startDate);
			endD = forMat.parse(endDate);
		} catch (Exception e) {
			log.debug("-------日期转换异常--------");
			log.debug(e.getMessage());
			startD = null;
			endD = null;
		}
		try {
			List<PowerInfoDto> infos = queryPowerInfo.getPowerInfo(url, info, userName, startD, endD, curPage,
					pageItem, orderName, orderType);
			Long size = queryPowerInfo.getSize(url, info, userName, startD, endD);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("code", ResponseCode.SUCCESS);
			map.put("result", infos);
			map.put("size", size);
		} catch (SQLException e) {
			log.debug("-------查询权限信息日志异常--------");
			log.debug(e.getMessage());
			map.put("desc", ResponseCode.FAIL_DESC);
			map.put("code", ResponseCode.FAIL);
		}
		return map;
	}
}
