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
import com.inesv.digiccy.dto.PoundatgeDto;
import com.inesv.digiccy.query.QueryBonusLevel;
import com.inesv.digiccy.query.QueryPoundatge;
import com.inesv.digiccy.util.excel.ExcelUtils;

@Component
public class PoundatgeValidata {

	@Autowired
	QueryPoundatge queryPoundatge;

	public Map<String, Object> queryAll(int curPage, int pageItem, String userOrgCode, String phone, String username,
			String startDate, String endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<PoundatgeDto> list = new ArrayList<PoundatgeDto>();
		list = queryPoundatge.queryAll(curPage, pageItem, userOrgCode, phone, username, startDate, endDate);
		if (list != null) {
			map.put("rows", list);
			map.put("total", queryPoundatge.getSize(userOrgCode, phone, username, startDate, endDate));
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("code", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	public void getExcel(HttpServletResponse response, String userOrgCode, String phone, String username,
			String startDate, String endDate) {
		Map<String, List<String>> contact = new HashMap<String, List<String>>();
		int size = 0;
		try {
			long lsize = queryPoundatge.getSize(userOrgCode, phone, username, startDate, endDate);
			size = Integer.parseInt(lsize + "");
		} catch (Exception e) {

		}
		List<PoundatgeDto> list = queryPoundatge.queryAll(1, size, userOrgCode, phone, username, startDate, endDate);
		String title1 = "用户ID";
		String title2 = "用户账号";
		String title3 = "用户机构编码";
		String title4 = "手续费来源类型";
		String title5 = "订单货币类型";
		String title6 = "手续费货币类型";
		String title7 = "手续费金额";
		String title8 = "订单金额";
		String title9 = "时间";
		List<String> col1 = new ArrayList<String>();
		List<String> col2 = new ArrayList<String>();
		List<String> col3 = new ArrayList<String>();
		List<String> col4 = new ArrayList<String>();
		List<String> col5 = new ArrayList<String>();
		List<String> col6 = new ArrayList<String>();
		List<String> col7 = new ArrayList<String>();
		List<String> col8 = new ArrayList<String>();
		List<String> col9 = new ArrayList<String>();
		for (PoundatgeDto dto : list) {
			col1.add(dto.getUser_no().toString());
			col2.add(dto.getUser_name().toString());
			col3.add(dto.getUser_code().toString());
			if (dto.getOptype() == 0) {
				col4.add("买");
			} else if (dto.getOptype() == 1) {
				col4.add("卖");
			} else if (dto.getOptype() == 2) {
				col4.add("充值");
			} else if (dto.getOptype() == 3) {
				col4.add("提现");
			}
			col5.add(dto.getType().toString());
			col6.add(dto.getAttr1().toString());
			col7.add(dto.getMoney().toString());
			col8.add(dto.getSum_money().toString());
			col9.add(dto.getDate().toString());
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

		ExcelUtils.export(response, contact);
	}
}
