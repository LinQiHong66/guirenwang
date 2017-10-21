package com.inesv.digiccy.validata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.BonusLevelDto;
import com.inesv.digiccy.query.QueryBonusLevel;
import com.inesv.digiccy.util.excel.ExcelUtils;

@Component
public class BonusLevelValidata {

	@Autowired
	QueryBonusLevel queryBonusLevel;

	public Map<String, Object> queryAll(int curPage, int pageItem, String userName, String userPhone, String userCode,
			String startDate, String endDate) {

		Map<String, Object> map = new HashMap<String, Object>();
		List<BonusLevelDto> list = new ArrayList<BonusLevelDto>();
		list = queryBonusLevel.queryAll(curPage, pageItem, userName, userPhone, userCode, startDate, endDate);
		if (list != null) {
			map.put("rows", list);
			map.put("total", queryBonusLevel.getSize(userName, userPhone, userCode, startDate, endDate));
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("code", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	public void getExcel(HttpServletResponse response, String userName, String userPhone, String userCode,
			String startDate, String endDate) {
		Map<String, List<String>> contact = new HashMap<String, List<String>>();
		long size = queryBonusLevel.getSize(userName, userPhone, userCode, startDate, endDate);
		int sizeInt = 10;
		try {
			sizeInt = Integer.parseInt(size + "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<BonusLevelDto> list = queryBonusLevel.queryAll(1, sizeInt, userName, userPhone, userCode, startDate,
				endDate);
		String title1 = "来源订单";
		String title2 = "货币种类";
		String title3 = "获取分润用户";
		String title4 = "获取分润用户编号";
		String title5 = "产生分润用户";
		String title6 = "产生分润用户账号";
		String title7 = "产生分润用户编号";
		String title8 = "交易类型";
		String title9 = "分润比例";
		String title10 = "所得分润";
		String title11 = "分润总金额";
		String title12 = "详情";
		List<String> col1 = new ArrayList<String>();
		List<String> col2 = new ArrayList<String>();
		List<String> col3 = new ArrayList<String>();
		List<String> col4 = new ArrayList<String>();
		List<String> col5 = new ArrayList<String>();
		List<String> col6 = new ArrayList<String>();
		List<String> col7 = new ArrayList<String>();
		List<String> col8 = new ArrayList<String>();
		List<String> col9 = new ArrayList<String>();
		List<String> col10 = new ArrayList<String>();
		List<String> col11 = new ArrayList<String>();
		List<String> col12 = new ArrayList<String>();
		for (BonusLevelDto dto : list) {
			col1.add(dto.getBonus_source().toString());
			col2.add(dto.getBonus_coin().toString());
			col3.add(dto.getAttr2());
			col4.add(dto.getBonus_rel_code().toString());
			col5.add(dto.getAttr3());
			col6.add(dto.getBonus_user_name().toString());
			col7.add(dto.getBonus_user_code().toString());
			if (dto.getBonus_type() == 0) {
				col8.add("买");
			} else if (dto.getBonus_type() == 1) {
				col8.add("卖");
			} else {
				col8.add("其他");
			}
			col9.add(dto.getLevel_scale().toString());
			col10.add(dto.getBonus().toString());
			col11.add(dto.getSum_bonus().toString());
			col12.add(dto.getRemark().toString());
		}
		contact.put(title1, col1);
		contact.put(title2, col2);
		contact.put(title3, col3);
		contact.put(title4, col4);
		contact.put(title5, col5);
		contact.put(title6, col6);
		contact.put(title7, col7);
		contact.put(title8, col8);
		contact.put(title9, col9);
		contact.put(title10, col10);
		contact.put(title11, col11);
		contact.put(title12, col12);

		ExcelUtils.export(response, contact);
	}
}
