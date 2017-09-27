package com.inesv.digiccy.validata;

import com.inesv.digiccy.api.command.CommandRedCommand;
import com.inesv.digiccy.api.command.CommandRedDetailCommand;
import com.inesv.digiccy.api.command.CrowdFundingCommand;
import com.inesv.digiccy.api.command.RegUserCommand;
import com.inesv.digiccy.api.command.UserBalanceCommand;
import com.inesv.digiccy.api.command.UserBalanceDetailCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.CommandRedDetailDto;
import com.inesv.digiccy.dto.CommandRedDto;
import com.inesv.digiccy.dto.CrowdFundingDto;
import com.inesv.digiccy.dto.NoticeDto;
import com.inesv.digiccy.dto.UserBalanceDetailDto;
import com.inesv.digiccy.dto.UserBalanceDto;
import com.inesv.digiccy.persistence.command.CommandRedOperation;
import com.inesv.digiccy.persistence.reg.RegUserPersistence;
import com.inesv.digiccy.query.QueryCommandRedInfo;
import com.inesv.digiccy.query.QueryCrowdFundingInfo;
import com.inesv.digiccy.query.QueryUserBalanceInfo;
import com.inesv.digiccy.util.MD5;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.dbutils.QueryRunner;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2016/11/9 0009.
 */
@Component
public class CommandRedValidata {
	
	@Autowired
	QueryRunner queryRunner;
	
	@Autowired 
	QueryCommandRedInfo queryCommandRedInfo;
	
	@Autowired 
	QueryUserBalanceInfo queryUserBalanceInfo;
	
	@Autowired
	CommandRedOperation commandRedOperation;
	
	@Autowired
    private CommandGateway commandGateway;
	
	
	/**
	 * 增加口令红包
	 * @return
	 */
	@Transactional(rollbackFor={Exception.class, RuntimeException.class})
	public Map<String, Object> validataAddCommandRed(String command_no, String command_name, Integer command_prize_type, String command_name_price, String command_number, String command_remark, Integer state, Date date){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			CommandRedCommand command = new CommandRedCommand(0L,command_no,command_name,command_prize_type,command_name_price,command_number,command_remark,state,date,"insertCommandRed");
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
	 * 修改口令红包
	 * @return
	 */
	@Transactional(rollbackFor={Exception.class, RuntimeException.class})
	public Map<String, Object> validataEditCommandRed(String command_no, String command_name, Integer command_prize_type, String command_name_price, String command_remark){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			CommandRedCommand command = new CommandRedCommand(0L,command_no,command_name,command_prize_type,command_name_price,"",command_remark,0,new Date(),"updateCommandRed");
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
	 * 修改指定口令红包状态
	 * @return
	 */
	@Transactional(rollbackFor={Exception.class, RuntimeException.class})
	public Map<String, Object> validataEditCrowdFundingInfo(String id,String state){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			CommandRedCommand command = new CommandRedCommand(Long.valueOf(id),"","",0,"","","",1,new Date(),"updateCommandRedInfo");
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
	 * 删除口令红包
	 * @return
	 */
	@Transactional(rollbackFor={Exception.class, RuntimeException.class})
	public Map<String, Object> validataDeleteCommandRed(String command_no){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			CommandRedCommand command = new CommandRedCommand(0L,command_no,"",0,"","","",2,new Date(),"deleteCommandRed");
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
	 * 删除指定口令红包
	 * @return
	 */
	@Transactional(rollbackFor={Exception.class, RuntimeException.class})
	public Map<String, Object> validataDeleteCommandRedInfo(String id){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			CommandRedCommand command = new CommandRedCommand(Long.valueOf(id),"","",0,"","","",2,new Date(),"deleteCommandRedInfo");
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
	 * 取得所有口令红包
	 * @return
	 */
	public Map<String, Object> validataAllCommandRed() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<CommandRedDto> commandRedList = queryCommandRedInfo.queryAllCommandRed();
		if (commandRedList != null) {
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("total", commandRedList.size());
			map.put("data", commandRedList);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
	/**
	 * 取得所有用户获取口令红包情况
	 * @return
	 */
	public Map<String, Object> validataAllCommandRedDetail() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<CommandRedDetailDto> commandRedList = queryCommandRedInfo.queryAllCommandRedDetail();
		if (commandRedList != null) {
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("total", commandRedList.size());
			map.put("data", commandRedList);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
	/**
	 * 取得口令红包
	 * @return
	 */
	public Map<String, Object> validataCommandRed(String command_no) {
		Map<String, Object> map = new HashMap<String, Object>();
		CommandRedDto commandRed = queryCommandRedInfo.queryCommandRed(command_no);
		if (commandRed != null) {
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("data", commandRed);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
	/**
	 * 取得指定口令紅包信息
	 * @return
	 */
	@Transactional(rollbackFor={Exception.class, RuntimeException.class})
	public Map<String, Object> validataCommandRedInfo(String command_no) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<CommandRedDto> commandRedDtoList = queryCommandRedInfo.queryAllCommandRedInfo(command_no);
		if(commandRedDtoList.size()!=0){
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("data", commandRedDtoList);
		}else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
	/**
	 * 输入口令取得红包
	 * @return
	 */
	@Transactional(rollbackFor={Exception.class, RuntimeException.class})
	public Map<String, Object> validataCommandRedInfoNumber(String user_id,String command_number) {
		Map<String, Object> map = new HashMap<String, Object>();
		CommandRedDto commandRedDto = queryCommandRedInfo.queryCrowdFundingInfoNumber(command_number);
		if(user_id == null){
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		if(commandRedDto==null){
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "该口令红包为null，请检查！");
			return map;
		}
		if(commandRedDto.getState()!=0){
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "该口令红包已被领取！");
			return map;
		}
		try {
			commandRedOperation.updateCommandRedStateAndDetail(commandRedDto,command_number,user_id);
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
	 * 修改指定用户获取口令红包状态
	 * @return
	 */
	@Transactional(rollbackFor={Exception.class, RuntimeException.class})
	public Map<String, Object> validataEditCommandRedInfoState(String id,String State,Integer user_no, String admin_no,Integer coin_type, Integer coin_price, String remark) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
			String detailStateSql = "UPDATE t_command_red_detail set state=? WHERE id=?";
	        	Object detailStateParams[] = {Integer.valueOf(State),Long.valueOf(id)};
	        	queryRunner.update(detailStateSql,detailStateParams);
	        String balanceDetailSql = "INSERT INTO t_inesv_user_balance_detail (user_no,admin_no,coin_type,coin_price,remark,date) VALUES (?,?,?,?,?,?)";
	        	Object balanceDetailParmas[] = {user_no,admin_no,0,new BigDecimal(coin_price),remark,new Date()};
	        	queryRunner.update(balanceDetailSql,balanceDetailParmas);
	        UserBalanceDto balanceDto = queryUserBalanceInfo.queryUserBalanceInfoByUserNoAndCoinType(String.valueOf(user_no),"0");
	        String editBalanceSql = "UPDATE t_inesv_user_balance SET enable_coin = ?,total_price = ? WHERE user_no = ? and coin_type = ?";
	            Object editBalanceParmas[] = {balanceDto.getEnable_coin().add(new BigDecimal(coin_price)),balanceDto.getTotal_price().add(new BigDecimal(coin_price)),user_no,0};
	            queryRunner.update(editBalanceSql,editBalanceParmas);
	        map.put("code", ResponseCode.SUCCESS);
	        map.put("desc",ResponseCode.SUCCESS_DESC);
		return map;
	}

}
