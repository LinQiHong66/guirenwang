package com.inesv.digiccy.validata;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.FicRechargeDto;
import com.inesv.digiccy.dto.FicWithdrawDto;
import com.inesv.digiccy.dto.pageDto;
import com.inesv.digiccy.query.QueryFicRechargeInfo;
import com.inesv.digiccy.query.QueryFinWithdrawInfo;
import com.inesv.digiccy.util.excel.ExcelUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by JimJim on 2016/12/6 0006.
 */
@Component
public class FicTradeValidata {

    @Autowired
    QueryFicRechargeInfo queryFicRechargeInfo;

    @Autowired
    QueryFinWithdrawInfo queryFinWithdrawInfo;

    /**
     * 查询所有虚拟币充值记录
     * @return
     */
    public Map<String ,Object> queryAllFicRechargeInfo(String userCode, String phone, String realName,String state,String coinType,String startData,String endData,pageDto page ){
        Map<String ,Object> map = new HashMap<>();
        List<FicRechargeDto> list = queryFicRechargeInfo.queryAllFicRechargeInfo(userCode, phone, realName, state, coinType, startData, endData, page);
       
        map.put("total", queryFicRechargeInfo.queryAllFicRechargeInfoSize(userCode, phone, realName, state, coinType, startData, endData));
        map.put("rows", list);
        return map;
    }

    /**
     * 查询所有虚拟币提现记录
     * @return
     */
    public Map<String ,Object> queryAllFicWithdrawInfo(String userName, String coinTypeSearch, String startData,String endData){
        Map<String ,Object> map = new HashMap<>();
        List<FicWithdrawDto> list = queryFinWithdrawInfo.queryAllFicWithdrawInfo(userName, coinTypeSearch, startData, endData);
        if(list == null){
            map.put("code", ResponseCode.FAIL_BILL_INFO);
            map.put("desc",ResponseCode.FAIL_BILL_INFO_DESC);
        }else {
            map.put("data", list);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
        }
        return map;
    }
    
    public void getRechargeExcel(HttpServletResponse response, String userCode, String phone, String realName,String state,String coinType,String startData,String endData){
    	pageDto page =new pageDto();
    	page.setPageNumber(1);
    	page.setPageSize(queryFicRechargeInfo.queryAllFicRechargeInfoSize(userCode, phone, realName, state, coinType, startData, endData));
    	System.out.println("PageSize:"+page.getPageSize());
    	List<FicRechargeDto> list = queryFicRechargeInfo.queryAllFicRechargeInfo(userCode, phone, realName, state, coinType, startData, endData, page);
    	Map<String, List<String>> contact = new HashMap<String, List<String>>();
 
    	String title1 = "用户账号";
    	String title2 = "真实姓名";
    	String title3 = "用户编号";
    	String title4 = "转入币种";
    	String title5 = "转入地址";
    	String title6 = "转入数量";
    	String title7 = "到账数量";
    	String title8 = "转入时间";
    	String title9 = "转入状态";
		List<String> value1 = new ArrayList<>();
		List<String> value2 = new ArrayList<>();
		List<String> value3 = new ArrayList<>();
		List<String> value4 = new ArrayList<>();
		List<String> value5 = new ArrayList<>();
		List<String> value6 = new ArrayList<>();
		List<String> value7 = new ArrayList<>();
		List<String> value8 = new ArrayList<>();
		List<String> value9 = new ArrayList<>();
    	for(FicRechargeDto dto : list){
    		value1.add(dto.getUserName());
    		value2.add(dto.getRealName());
    		value3.add(dto.getUserCode());
    		value4.add(dto.getCoinName());
    		value5.add(dto.getAddressFrom());
    		value6.add(dto.getNumber());
    		value7.add(dto.getRealNumber());
    		value8.add(dto.getDate());
    		value9.add(dto.getState()==1?"未到账":"已到账");
    	}
		contact.put(title1, value1);
		contact.put(title2, value2);
		contact.put(title3, value3);
		contact.put(title4, value4);
		contact.put(title5, value5);
		contact.put(title6, value6);
		contact.put(title7, value7);
		contact.put(title8, value8);
		contact.put(title9, value9);
		ExcelUtils.export(response, contact);
    } 
    public void getWithdrawExcel(HttpServletResponse response, String userName, String coinTypeSearch, String startData,String endData){
    	ArrayList<FicWithdrawDto> withdraws = (ArrayList<FicWithdrawDto>) queryFinWithdrawInfo.queryAllFicWithdrawInfo(userName, coinTypeSearch, startData, endData);
    	Map<String, List<String>> contact = new HashMap<String, List<String>>();
    	String title1 = "用户名称";
    	String title2 = "货币种类";
    	String title3 = "提现金额";
    	String title4 = "手续费";
    	String title5 = "实际到账";
    	List<String> value1 = new ArrayList<>();
    	List<String> value2 = new ArrayList<>();
    	List<String> value3 = new ArrayList<>();
    	List<String> value4 = new ArrayList<>();
    	List<String> value5 = new ArrayList<>();
    	for(FicWithdrawDto dto : withdraws){
    		value1.add(dto.getAttr1());
    		value2.add(dto.getAttr2());
    		value3.add(dto.getCoin_sum().toString());
    		value4.add(dto.getPoundage().toString());
    		value5.add(dto.getActual_price().toString());
    	}
		contact.put(title1, value1);
		contact.put(title2, value2);
		contact.put(title3, value3);
		contact.put(title4, value4);
		contact.put(title5, value5);
		ExcelUtils.export(response, contact);
    }
}
