package com.inesv.digiccy.validata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.DealDetailDto;
import com.inesv.digiccy.dto.EntrustDto;
import com.inesv.digiccy.persistence.reg.RegUserPersistence;
import com.inesv.digiccy.query.QueryEntrustDealInfo;
import com.inesv.digiccy.query.QueryEntrustInfo;
import com.inesv.digiccy.validata.util.ExcelUtils;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
@Component
public class EntrustDealValidate {

	@Autowired
	QueryEntrustDealInfo queryEntrustDealInfo;

	@Autowired
	QueryEntrustInfo queryEntrustInfo;

	@Autowired
	CommandGateway commandGateway;

	@Autowired
	RegUserPersistence regUserPersistence;

	/**
	 * 根据用户编号查询委托记录
	 * 
	 * @return
	 */
	public Map<String, Object> validataEntrustRecordByUserNo(String userNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<EntrustDto> list = queryEntrustInfo.queryEntrustInfoByUserNo(userNo);
		if (list == null) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		} else {
			map.put("data", list);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}
		return map;
	}

	/**
	 * 获取分页委托记录
	 */
	public Map<String, Object> getPagingentrList(int pageNum, int itemCount, int userNo, int coinType) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<EntrustDto> dtos = queryEntrustInfo.queryPagingEntrust(pageNum, itemCount, userNo, coinType);
		Long size = queryEntrustInfo.getEntrustSize(userNo, coinType);
		if (dtos != null) {
			map.put("data", dtos);
			map.put("count", size);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	/**
	 * 委托记录
	 * 
	 * @return
	 */
	public Map<String, Object> validataEntrustListByUserNo(String userNo, String dealType, String coinNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<DealDetailDto> list = queryEntrustDealInfo.queryEntrustDealInfoByEntrustTypeEntrustCoin(
				Integer.parseInt(userNo), Integer.parseInt(dealType), Integer.parseInt(coinNo));
		if (list == null) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		} else {
			map.put("data", list);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}
		return map;
	}

	/**
	 * 委托管理记录
	 * 
	 * @return
	 */
	public Map<String, Object> validataEntrustManageListByUserNo(String userNo, String entrustType, String entrustCoin,
			String state) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<EntrustDto> EntrustList = queryEntrustDealInfo.queryEntrustManageInfoByUserNo(Integer.parseInt(userNo),
				Integer.parseInt(entrustType), Integer.parseInt(entrustCoin), Integer.parseInt(state));
		if (EntrustList == null) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		} else {
			map.put("EntrustList", EntrustList);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);

		}
		return map;
	}

	/**
	 * 修改委托管理状态
	 */
	public Map<String, Object> validataUpdateEntrustManage(Long id) {
		Map<String, Object> map = new HashMap<>();
		try {

			regUserPersistence.updateEntrustState2(id);
			EntrustDto entrust = queryEntrustInfo.queryEntrustInfoByID(id);
			regUserPersistence.updateBalance(entrust.getUser_no(), entrust.getEntrust_coin(),
					entrust.getEntrust_num().doubleValue());
			map.put("msg", 100);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", 200);
		}
		return map;
	}

	public Map<String, Object> validateQueryEntrustAll(String userName, String state, String startDate, String endDate,
			Long pageItem, Integer pageNum) {
		Map<String, Object> map = new HashMap<>();
		List<EntrustDto> list = queryEntrustInfo.queryEntrustInfoAll(userName, state, startDate, endDate, pageItem,
				pageNum);
		Long count = queryEntrustInfo.getEntrustSize(userName, state, startDate, endDate);
		if (list == null) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		} else {
			map.put("data", list);
			map.put("count", count);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}
		return map;
	}

	public Map<String, Object> validataUpdateEntrustStateByAttr1(Long attr1) {
		Map<String, Object> map = new HashMap<>();
		try {
			// EntrustCommand command = new
			// EntrustCommand(id,null,null,null,null,null,null,null,2,new
			// Date(),"updateState2");
			// commandGateway.sendAndWait(command);
			regUserPersistence.updateEntrustStateByAttr1(attr1);
			map.put("msg", 100);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", 200);
		}
		return map;
	}

	public Map<String, Object> validateQueryEntrustAllByDay() {
		Map<String, Object> map = new HashMap<>();
		List<EntrustDto> list = queryEntrustInfo.queryEntrustInfoAllByDay();
		if (list == null) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		} else {
			map.put("data", list);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}
		return map;
	}

	public Map<String, Object> validateQueryEntrustAllPaging(Integer pageSize, Integer lineSize) {
		Map<String, Object> map = new HashMap<>();
		List<EntrustDto> list = queryEntrustInfo.queryEntrustInfoAllPaging(pageSize, lineSize);
		if (list == null) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		} else {
			map.put("data", list);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}
		return map;
	}

	public void getExcel(HttpServletResponse response, String userName, String state, String startDate,
			String endDate) {
		long size = queryEntrustInfo.getEntrustSize(userName, state, startDate, endDate);
		List<EntrustDto> list = queryEntrustInfo.queryEntrustInfoAll(userName, state, startDate, endDate, size, 1);
		Map<String, List<String>> contact = new HashMap<String, List<String>>();
		String title1 = "用户编号";
		String title2 = "委托币种";
		String title3 = "委托类型";
		String title4 = "委托价格";
		String title5 = "委托数量(个)";
		String title6 = "成交数量(个)";
		String title7 = "手续费";
		String title8 = "状态";
		String title9 = "委托时间";
		List<String> value1 = new ArrayList<String>();
		List<String> value2 = new ArrayList<String>();
		List<String> value3 = new ArrayList<String>();
		List<String> value4 = new ArrayList<String>();
		List<String> value5 = new ArrayList<String>();
		List<String> value6 = new ArrayList<String>();
		List<String> value7 = new ArrayList<String>();
		List<String> value8 = new ArrayList<String>();
		List<String> value9 = new ArrayList<String>();
		for (EntrustDto dto : list) {
			value1.add(dto.getUser_no().toString());
			value2.add(dto.getEntrust_coin().toString());
			value3.add(dto.getEntrust_type().toString());
			value4.add(dto.getEntrust_price().toString());
			value5.add(dto.getEntrust_num().toString());
			value6.add(dto.getDeal_num().toString());
			value7.add(dto.getPiundatge().toString());
			value8.add(dto.getState().toString());
			value9.add(dto.getDate().toString());
		}
		contact.put(title9, value9);
		contact.put(title8, value8);
		contact.put(title7, value7);
		contact.put(title6, value6);
		contact.put(title5, value5);
		contact.put(title4, value4);
		contact.put(title3, value3);
		contact.put(title2, value2);
		contact.put(title1, value1);
		ExcelUtils.export(response, contact);
	}
}
