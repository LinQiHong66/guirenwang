package com.inesv.digiccy.validata;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.inesv.digiccy.api.command.CrowdFundingCommand;
import com.inesv.digiccy.api.command.CrowdFundingDetailsCommand;
import com.inesv.digiccy.api.command.UserBalanceCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.AddressDto;
import com.inesv.digiccy.dto.CrowdFundingDetailsDto;
import com.inesv.digiccy.dto.CrowdFundingDto;
import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.dto.UserBalanceDto;
import com.inesv.digiccy.dto.UserVoucherDto;
import com.inesv.digiccy.persistence.crowd.CrowdFundingDetailsOperation;
import com.inesv.digiccy.query.QueryCrowdFundingInfo;
import com.inesv.digiccy.query.QueryUserBalanceInfo;
import com.inesv.digiccy.query.QueryUserInfo;
import com.inesv.digiccy.util.MD5;

/**
 * Created by Administrator on 2017/06/05 0009.
 */
@Component

public class CrowdFundingDetailsValidata {

	@Autowired
	private QueryUserInfo queryUserInfo;

	@Autowired
	QueryCrowdFundingInfo queryCrowdFundingInfo;

	@Autowired
	QueryUserBalanceInfo queryUserBalanceInfo;
	
	@Autowired
	CrowdFundingDetailsOperation crowdFundingDetailsOperation;

	@Transactional(rollbackFor={Exception.class, RuntimeException.class})
	public Map<String, Object> validataAddCrowdFunding(String icoNo,
			Integer userNo, Integer icoNumber, String payPassword) throws Exception{
		Map<String, Object> map = new HashMap<>();
		MD5 md5 = new MD5();
		InesvUserDto userDto = queryUserInfo.queryInviteNum(userNo);
		if(userDto == null){
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);// 众筹失败
			return map;
		}
		UserVoucherDto voucherDto = queryUserInfo.queryVoucher(userNo);
		AddressDto addressDto = queryUserInfo.queryAddress(userNo);
		if(voucherDto == null || addressDto == null){
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "该用户暂不能众筹，请前往安全中心实名认证并填写收货地址");// 众筹失败
			return map;
		}
		CrowdFundingDto icoDto = queryCrowdFundingInfo
				.queryCrowdFundingInfo(icoNo);
		if(icoDto == null){
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);// 众筹失败
			return map;
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(df.parse(df.format(icoDto.getBegin_date())).getTime() >= new Date().getTime()){
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "众筹暂未开始，请等待！");// 众筹失败
			return map;
		}
		if(df.parse(df.format(icoDto.getEnd_date())).getTime() <= new Date().getTime()){
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "众筹已结束，不好意思！");// 众筹失败
			return map;
		}
		if(Double.valueOf(icoDto.getAttr2()) > icoNumber.doubleValue() || Double.valueOf(icoDto.getAttr3()) < icoNumber.doubleValue()){
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "大于或小于限购数量，众筹失败！");// 众筹失败
			return map;
		}
		if(Double.valueOf(icoDto.getAttr4()) == 1) {//判断是否开启最小倍数的整数
			double yushu = icoNumber.doubleValue() % Double.valueOf(icoDto.getAttr2()) ;
			if(yushu != 0){
				map.put("code", ResponseCode.FAIL);
				map.put("desc", "众筹数量必须是最小限购数量的整数倍，众筹失败");// 众筹失败
				return map;
			}
		}
		if(icoDto.getIco_target() < (icoDto.getIco_current() + icoNumber)){
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "超过发行总量，众筹失败！");// 众筹失败
			return map;
		}
		if(icoDto.getIco_state()!=0){//状态是否为进行中
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "众筹不在进行中，谢谢！");// 众筹失败
			return map;
		}
		if (!md5.getMD5(payPassword).equals(userDto.getDeal_pwd())) {// 交易密码是否相同
			map.put("code", ResponseCode.FAIL_DEALPWD);
			map.put("desc", ResponseCode.FAIL_DEALPWD_DESC);// 交易密码错误
			return map;
		}
		/*List<CrowdFundingDetailsDto> crowdFundingList = queryCrowdFundingInfo.queryAllCrowdFundingDetailById(userNo.toString(),icoNo);
		if(crowdFundingList.size() != 0) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "该用户已参与过此众筹");// 众筹失败
			return map;
		}*/
		BigDecimal enableCoin = BigDecimal.valueOf(0);// 获取用户可用金额
		UserBalanceDto balanceDto = queryUserBalanceInfo
				.queryUserBalanceInfoByUserNoAndCoinType(
						Integer.toString(userNo), icoDto.getIco_price_type());// 0:人民币类型
		if (balanceDto != null) {
			enableCoin = balanceDto.getEnable_coin();
			if (enableCoin == null) {
				map.put("code", ResponseCode.FAIL);
				map.put("desc", ResponseCode.FAIL_DESC);// 众筹失败
				return map;
			}
		}
		BigDecimal sumPrice = icoDto.getIco_price().multiply(new BigDecimal(Integer.toString(icoNumber)));//单价*数量
		BigDecimal sumPrices = sumPrice.add(sumPrice.multiply(new BigDecimal(0.002))); //需支付金额  + 手续费
		if (sumPrices.doubleValue() > enableCoin.doubleValue()) {// 余额是否大于支付金额
			map.put("code", ResponseCode.FAIL_BALANCE_NUM);
			map.put("desc", ResponseCode.FAIL_BALANCE_NUM_DESC);// 余额不足
			return map;
		}
		Double icoNumberConvert = 0d;
		if(Double.valueOf(icoDto.getAttr6()) == 1) {
			icoNumberConvert = icoNumber * Double.valueOf(icoDto.getAttr5());
		}else {
			icoNumberConvert = Double.valueOf(icoNumber);
		}
		try {
			Integer icoCurrent = icoDto.getIco_current() + icoNumber;// 当前份数
			Double icoStatus = (double) icoCurrent/ icoDto.getIco_target();// 进度
			double total_price = balanceDto.getTotal_price().doubleValue()-sumPrices.doubleValue();//总资产
			double enable_coin = balanceDto.getEnable_coin().doubleValue()-sumPrices.doubleValue();//可用货币
			crowdFundingDetailsOperation.updateCrowdDetailAndBalance(icoNo, userNo.toString(), icoNumber, icoNumberConvert, sumPrice.toString()
					, icoCurrent.toString(),icoStatus.toString(), String.valueOf(total_price), String.valueOf(enable_coin), icoDto.getIco_price_type());
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
		
			/*CrowdFundingDetailsCommand detailsCommand = new CrowdFundingDetailsCommand(
					0L, userNo, icoNo, icoNumber, sumPrice, new Date(),
					"", "", "insertDetails");
			commandGateway.sendAndWait(detailsCommand);//同步执行
			Integer icoCurrent = icoDto.getIco_current() + icoNumber;// 当前份数
			Double icoStatus = (double) icoCurrent/ icoDto.getIco_target();// 进度
			CrowdFundingCommand crowdCommand = new CrowdFundingCommand(
					0L, icoNo, "", "", 0, icoCurrent, icoStatus,icoDto.getIco_price_type(),
					icoDto.getIco_price(), icoDto.getIco_sum_price(),
					"","", 0, new Date(), new Date(), "","", "updateCrowdFundingFront");
			commandGateway.sendAndWait(crowdCommand);
			BigDecimal total_price = balanceDto.getTotal_price().subtract(sumPrice);//总资产
			BigDecimal enable_coin = balanceDto.getEnable_coin().subtract(sumPrice);//可用货币
			UserBalanceCommand userBalanceCommand = new UserBalanceCommand(0,userNo,Integer.valueOf(icoDto.getIco_price_type()),enable_coin,total_price,new Date(),"updateTranEnbles");
			commandGateway.sendAndWait(userBalanceCommand);//同步执行
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);// 众筹成功 */		
	}
}
