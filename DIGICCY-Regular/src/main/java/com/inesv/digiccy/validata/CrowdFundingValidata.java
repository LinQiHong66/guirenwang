package com.inesv.digiccy.validata;

import com.inesv.digiccy.api.command.CrowdFundingCommand;
import com.inesv.digiccy.api.command.RegUserCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.CrowdFundingDetailsDto;
import com.inesv.digiccy.dto.CrowdFundingDto;
import com.inesv.digiccy.dto.FicWithdrawDto;
import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.dto.NoticeDto;
import com.inesv.digiccy.query.QueryCrowdFundingInfo;
import com.inesv.digiccy.query.QuerySubCore;
import com.inesv.digiccy.util.MD5;
import com.inesv.digiccy.util.excel.ExcelUtils;

import org.apache.commons.collections.map.HashedMap;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;


/**
 * Created by Administrator on 2016/11/9 0009.
 */
@Component
public class CrowdFundingValidata {
	
	@Autowired 
	QueryCrowdFundingInfo queryCrowdFundingInfo;
	
	@Autowired 
	QuerySubCore querySubCore;
	
	@Autowired
    private CommandGateway commandGateway;
	
	/**
	 * 增加众筹项目
	 * @return
	 */
	@Transactional(rollbackFor={Exception.class, RuntimeException.class})
	public Map<String, Object> validataAddCrowdFunding(String icoName, String photoName, String photoRemarkName, Integer icoTarget, String icoPriceType, BigDecimal icoPrice, 
			String icoRemark, String icoExplain, String minNumber,String maxNumber, String numberInteger,String convert, String convertFunction, Date beginDate, Date endDate){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
			String icoNo=df.format(new Date()) + ((int)(Math.random()*(8999))+1000);
			BigDecimal icoSumPrice = icoPrice.multiply(new BigDecimal(icoTarget));
			CrowdFundingCommand command = new CrowdFundingCommand(0L,icoNo,icoName,photoName,icoTarget,0,0d,icoPriceType,icoPrice,icoSumPrice,icoRemark,icoExplain,0,beginDate,endDate,photoRemarkName,minNumber,maxNumber,numberInteger,convert,convertFunction,"insertCrowdFunding");
	        commandGateway.send(command);
	        map.put("code", ResponseCode.SUCCESS);
	        map.put("desc",ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
            map.put("code",ResponseCode.FAIL);
            map.put("desc",ResponseCode.FAIL_DESC);
		}
		return map;
	}
	/**
	 * 修改众筹项目
	 * @return
	 */
	@Transactional(rollbackFor={Exception.class, RuntimeException.class})
	public Map<String, Object> validataEditCrowdFunding(String icoNo, String icoName, String photoName, String photoRemarkName, Integer icoTarget, String icoPriceType, BigDecimal icoPrice, 
			String icoRemark, String icoExplain, String minNumber,String maxNumber, String numberInteger, String convert, String convertFunction, Date beginDate, Date endDate){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BigDecimal icoSumPrice = icoPrice.multiply(new BigDecimal(icoTarget));
			CrowdFundingCommand command = new CrowdFundingCommand(0L,icoNo,icoName,photoName,icoTarget,0,0d,icoPriceType,icoPrice,icoSumPrice,icoRemark,icoExplain,0,beginDate,endDate,photoRemarkName,minNumber,maxNumber,numberInteger,convert,convertFunction,"updateCrowdFundingBack");
	        commandGateway.sendAndWait(command);
	        map.put("code", ResponseCode.SUCCESS);
	        map.put("desc",ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
            map.put("code",ResponseCode.FAIL);
            map.put("desc",ResponseCode.FAIL_DESC);
		}
		return map;
	}
	/**
	 * 删除众筹项目
	 * @return
	 */
	@Transactional(rollbackFor={Exception.class, RuntimeException.class})
	public Map<String, Object> validataDeleteCrowdFunding(String icoNo){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			CrowdFundingCommand command = new CrowdFundingCommand(0L,icoNo,"","",0,0,0d,"0",new BigDecimal("0"),new BigDecimal("0"),"","",0,new Date(),new Date(),"","","","","","","deleteCrowdFunding");
	        commandGateway.sendAndWait(command);
	        map.put("code", ResponseCode.SUCCESS);
	        map.put("desc",ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
            map.put("code",ResponseCode.FAIL);
            map.put("desc",ResponseCode.FAIL_DESC);
		}
		return map;
	}
	/**
	 * 取得所有众筹项目
	 * @return
	 */
	public Map<String, Object> validataAllCrowdFunding(String pageSize,String lineSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrowdFundingDto> crowdFundingList = queryCrowdFundingInfo.queryAllCrowdFunding(pageSize,lineSize);
		Long crowdFundingSize = queryCrowdFundingInfo.getCrowdFundingSize();
		if (crowdFundingList != null) {
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("total", crowdFundingSize);
			map.put("data", crowdFundingList);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
	/**
	 * 取得所有众筹项目
	 * @return
	 */
	public Map<String, Object> validataAllCrowdFundingBack() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrowdFundingDto> crowdFundingList = queryCrowdFundingInfo.queryAllCrowdFundingBack();
		if (crowdFundingList != null) {
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("total", crowdFundingList.size());
			map.put("data", crowdFundingList);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
	/**
	 * 取得指定众筹项目
	 * @return
	 * @throws ParseException 
	 */
	public Map<String, Object> validataCrowdFundingInfo(String icoNo) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		CrowdFundingDto crowdFunding = queryCrowdFundingInfo.queryCrowdFundingInfo(icoNo);
		/*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date date = format.parse(format.format(crowdFunding.getEnd_date())); 
		crowdFunding.setAttr5(crowdFunding.getAttr1());
		crowdFunding.setAttr1(String.valueOf(date.getTime()));*/
		if (crowdFunding != null) {
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("data", crowdFunding);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
	
	/**
	 * 取得指定众筹项目
	 * @return
	 * @throws ParseException 
	 */
	public Map<String, Object> validataCrowdFundingInfoBack(String icoNo){
		Map<String, Object> map = new HashMap<String, Object>();
		CrowdFundingDto crowdFunding = queryCrowdFundingInfo.queryCrowdFundingInfo(icoNo);
		if (crowdFunding != null) {
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("data", crowdFunding);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
	
	/**
	 * 取得所有众筹项目
	 * @return
	 */
	public Map<String, Object> validataAllCrowdFundingDetailBack() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrowdFundingDetailsDto> crowdFundingList = queryCrowdFundingInfo.queryAllCrowdFundingDetailBack();
		if (crowdFundingList != null) {
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("total", crowdFundingList.size());
			map.put("data", crowdFundingList);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
	
	/**
	 * 取得所有众筹项目
	 * @return
	 */
	public Map<String, Object> validataAllCrowdFundingDetailBack_Jigou() {
		Map<String, Object> map = new HashMap<String, Object>();
		CrowdFundingDetailsDto dto = new CrowdFundingDetailsDto();
		List<CrowdFundingDetailsDto> crowdFundingList = queryCrowdFundingInfo.queryAllCrowdFundingDetailBack_Jigou();
		if (crowdFundingList != null) {
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("total", crowdFundingList.size());
			map.put("data", crowdFundingList);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
	
	/**
	 * 取得所有众筹项目
	 * @return
	 */
	public Map<String, Object> validataAllCrowdFundingDetailBack_User() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrowdFundingDetailsDto> crowdFundingList = queryCrowdFundingInfo.queryAllCrowdFundingDetailBack_User();
		if (crowdFundingList != null) {
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("total", crowdFundingList.size());
			map.put("data", crowdFundingList);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
	
	/**
	 * 取得机构下所有众筹项目
	 * @return
	 */
	public Map<String, Object> validataAllCrowdFundingDetailBack_Jigou_user(String orgCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrowdFundingDetailsDto> crowdFundingList = queryCrowdFundingInfo.queryAllCrowdFundingDetailBack_Jigou_detail(orgCode);
		if (crowdFundingList != null) {
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("total", crowdFundingList.size());
			map.put("data", crowdFundingList);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
	
	/**
	 * 定时器修改众筹项目状态
	 * @return
	 */
	public void validataEditCrowdState(){
		CrowdFundingCommand command = new CrowdFundingCommand(0L,"","","",0,0,0d,"",new BigDecimal(0),new BigDecimal(0),"","",0,new Date(),new Date(),"","","","","","","updateCrowdFundingState");
	    commandGateway.sendAndWait(command);
	}
	
	/*
	 * 众筹总结
	 */
	public Map<String, Object> validateCrowdDetail(){
		Map<String, Object> map = new HashMap<String, Object>();
		CrowdFundingDetailsDto dto1 = queryCrowdFundingInfo.queryCrowdFunding1();//总贵人通数量
		CrowdFundingDetailsDto dto2 = queryCrowdFundingInfo.queryCrowdFunding2();//今日贵人通数量
		CrowdFundingDetailsDto dto3 = queryCrowdFundingInfo.queryCrowdFunding3();//总购酒数量
		CrowdFundingDetailsDto dto4 = queryCrowdFundingInfo.queryCrowdFunding4();//今日购酒数量
		List<InesvUserDto> userList = querySubCore.getInesvUserByOrgType(0);
		map.put("crowd1",dto1.getIco_user_number());
		map.put("crowd2",dto2.getIco_user_number());
		map.put("crowd3",dto3.getIco_user_number());
		map.put("crowd4",dto4.getIco_user_number());
		map.put("userOrgCode",userList);
		return map;
	}
	
	/*
	 * 导出excel
	 */
	public void getDetailExcel(HttpServletResponse response){
    	ArrayList<CrowdFundingDetailsDto> details = (ArrayList<CrowdFundingDetailsDto>) queryCrowdFundingInfo.queryAllCrowdFundingDetailBack();
    	Map<String, List<String>> contact = new HashMap<String, List<String>>();
    	String title1 = "用户ID";
    	String title2 = "众筹ID";
    	String title3 = "众筹贵人通数量";
    	String title4 = "众筹茅台酒数量";
    	String title5 = "众筹总金额";
    	String title6 = "时间";
    	List<String> value1 = new ArrayList<>();
    	List<String> value2 = new ArrayList<>();
    	List<String> value3 = new ArrayList<>();
    	List<String> value4 = new ArrayList<>();
    	List<String> value5 = new ArrayList<>();
    	List<String> value6 = new ArrayList<>();
    	for(CrowdFundingDetailsDto dto : details){
    		value1.add(dto.getAttr1());
    		value2.add(dto.getIco_id());
    		value3.add(dto.getIco_user_number().toString());
    		value4.add(dto.getAttr2());
    		value5.add(dto.getIco_user_sumprice().toString());
    		value6.add(dto.getDate().toString());
    	}
		contact.put(title1, value1);
		contact.put(title2, value2);
		contact.put(title3, value3);
		contact.put(title4, value4);
		contact.put(title5, value5);
		contact.put(title6, value6);
		ExcelUtils.export(response, contact);
    }
}
