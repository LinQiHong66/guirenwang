package controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inesv.digiccy.dto.InesvAddressDto;
import com.inesv.digiccy.dto.UserLevelDto;
import com.inesv.digiccy.query.QueryInesvAddress;
import com.inesv.digiccy.query.QueryUserLevel;
import com.inesv.digiccy.validata.BuyEntrustDepthValidata;
import com.inesv.digiccy.validata.TradeValidata;
import com.inesv.digiccy.validata.UserBalanceValidate;

@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	BuyEntrustDepthValidata validata;
	
	@Autowired
	UserBalanceValidate balanceValidate;
	
	@Autowired
	TradeValidata tradeValidata;
	
	@Autowired
	QueryUserLevel QueryUserLevel;
	
	@Autowired
	QueryInesvAddress queryInesvAddress;
	
	/**
	 * 市场深度图
	 * */
	@ResponseBody
	@RequestMapping(value = "/depth",method = RequestMethod.POST)
	public Map<String,Object> Depth(String user_no,String entrust_coin){
		Map<String,Object> map = new HashMap<String, Object>();
		InesvAddressDto inesvAddressDto = new InesvAddressDto();
		inesvAddressDto = queryInesvAddress.queryByAddressName("Depth");
		if (inesvAddressDto.getAddress_status() == 1) {
		Integer count = QueryUserLevel.queryByUserId1(Long.valueOf(user_no));
		if(count == 1){
				map = validata.queryEntrustByEntrustCoinOrderBy(Integer.valueOf(entrust_coin));
				//break;
			}else{
				map.put("msg", 100);
			}
		
		}else{
			map.put("msg", 100);
		}
		return map;
	}
	//validateTradeCoin
	@ResponseBody
	@RequestMapping(value = "/assets",method = RequestMethod.POST)
	public Map<String,Object> Assets(String userNo){
		Map<String,Object> map = new HashMap<String, Object>();
		InesvAddressDto inesvAddressDto = new InesvAddressDto();
		inesvAddressDto = queryInesvAddress.queryByAddressName("Assets");
		if (inesvAddressDto.getAddress_status() == 1) {
		Integer count = QueryUserLevel.queryByUserId2(Long.valueOf(userNo));
		if(count == 1){
				map = balanceValidate.validateQureyByUserNo(userNo);
				//break;
			}else{
				map.put("msg", 100);
			}
		
		}else{
			map.put("msg", 100);
		}
		return map;
	}
	
	/**
	 * 挂单操作
	 * @param userNo 用户编号
	 * @param tradeNum 交易数量
	 * @param tradePrice 交易价格
	 * @param poundatge 手续费
	 * @param tradePassword 交易密码
	 * @param coinType 币种类型
	 * @param tradeType 交易类型
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/submit",method = RequestMethod.POST)
	public Map<String,Object> Submit(String userNo,String tradeNum,String tradePrice,String poundatge,String tradePassword, String coinType,String tradeType,String poundageRate){
		Map<String,Object> map = new HashMap<String, Object>();
		InesvAddressDto inesvAddressDto = new InesvAddressDto();
		inesvAddressDto = queryInesvAddress.queryByAddressName("Submit");
		if (inesvAddressDto.getAddress_status() == 1) {
		Integer count = QueryUserLevel.queryByUserId3(Long.valueOf(userNo));
			if(count == 1){
				map = tradeValidata.validateTradeCoin(userNo, new BigDecimal(tradeNum),new BigDecimal(tradePrice), new BigDecimal(poundatge), tradePassword, coinType, tradeType);
				//break;
			}else{
				map.put("msg", 100);
			}
		}else{
			map.put("msg", 100);
		}
		return map;
	}
	
	
	/**
	 * 撤销委托记录
	 * @param id
	 * @param userNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/cancel",method = RequestMethod.POST)
	public Map<String,Object> Cancel(String id, String userNo) throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		InesvAddressDto inesvAddressDto = new InesvAddressDto();
		inesvAddressDto = queryInesvAddress.queryByAddressName("Cancel");
		if (inesvAddressDto.getAddress_status() == 1) {
		UserLevelDto userLevelDto = new UserLevelDto();
		Integer count = QueryUserLevel.queryByUserId4(Long.valueOf(userNo));
			if(count == 1){
				map = tradeValidata.validateDelEntrust(Long.valueOf(id), Integer.valueOf(userNo));
			}else {
				map.put("msg", 100);
			}
		}else{
			map.put("msg", 100);
		}
		return map;
	}
	
}
