package com.inesv.digiccy.validata;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.CoinDto;
import com.inesv.digiccy.dto.UserInfoDto;
import com.inesv.digiccy.query.QuerySubCore;
import com.inesv.digiccy.query.coin.QueryCoin;
import com.inesv.digiccy.util.MD5;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2016/11/9 0009.
 */
@Component
public class CommonValidata {
    @Autowired
    private QuerySubCore querySubCore;

    @Autowired
    private CommandGateway commandGateway;
	
	@Autowired 
	QueryCoin queryCoin;

	/**
	 * 得到基本的货币类型
	 * @return
	 */
	public Map<Object, Object> validateBasicCoinType() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		List<CoinDto> coinTypeList = queryCoin.queryCoinNoAndCoinNameOfCoin();
		if (coinTypeList != null) {
			map.put("coinTypeList", coinTypeList);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
			map.put("coinTypeList", "none");
		}
		return map;
	}

	/**
	 * 判断用户交易密码是否正确
	 * @param userNo
	 * @param dealpWd
	 * @return
	 */
	public Map<Object, Object> validateTradePassword(Integer userNo,
			String dealpWd) {
		Map<Object , Object> map = new HashMap<>();
		//判断用户是否存在
        UserInfoDto uid = querySubCore.getUserInfo(userNo);
        if(uid == null){
            map.put("code",ResponseCode.FAIL);
            map.put("desc",ResponseCode.FAIL_DESC);
            return map;
        }else{
        	//判断交易密码
            if(!new MD5().getMD5(dealpWd).equals(uid.getDeal_pwd())){
                map.put("code",ResponseCode.FAIL_TRADE_PASSWORD);
                map.put("desc",ResponseCode.FAIL_TRADE_PASSWORD_DESC);
                return map;
            }else{
            	map.put("code",ResponseCode.SUCCESS);
                map.put("desc",ResponseCode.SUCCESS_DESC);
                return map;
            }
        }
	}

	/**
	 * 得到除人民币外所有的委托货币种类信息
	 * @return
	 */
	public Map<Object, Object> validateBasicCoinTypeExceptRMB() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		List<CoinDto> coinTypeList = queryCoin.queryCoinNoAndCoinNameOfCoinExceptRMB();
		if (coinTypeList != null) {
			map.put("coinTypeList", coinTypeList);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
			map.put("coinTypeList", "none");
		}
		return map;
	}

}
