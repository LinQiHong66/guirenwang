package com.inesv.digiccy.validata;

import com.inesv.digiccy.api.command.BillCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.BillDto;
import com.inesv.digiccy.dto.UserBalanceDto;
import com.inesv.digiccy.dto.UserInfoDto;
import com.inesv.digiccy.persistence.operation.BillPer;
import com.inesv.digiccy.query.QueryBill;
import com.inesv.digiccy.query.QuerySubCore;
import org.apache.commons.collections.map.HashedMap;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
@Component
public class BillValidata {

    private static Logger logger = LoggerFactory.getLogger(BillValidata.class);

    @Autowired
    private QueryBill queryBill;
    @Autowired
    private QuerySubCore querySubCore;
    @Autowired
    private CommandGateway commandGateway;
    @Autowired
    private BillPer billPer;

    /**
     * create by huguokai date:2016年11月16日15:16:04
     * 校验话费充值记录数据
     * @param userNo
     * @return map
     */
    public Map<String ,Object> getBillInfo(Integer userNo){
        Map<String ,Object>  map = new HashMap<>();
        List<BillDto> list = queryBill.getBillInfo(userNo);
        if(list == null || list.size() == 0){
            map.put("code", ResponseCode.FAIL_BILL_INFO);
            map.put("desc",ResponseCode.FAIL_BILL_INFO_DESC);
        }else {
            map.put("data", list);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
        }
        return map;
    }

    /**
     * 校验所有钱包接口
     * */
    public Map<String,Object> getAllBill(){
        Map<String,Object> map = new HashedMap();
        List<BillDto> billList = queryBill.getAllBill();
        if(billList == null){
            map.put("code", ResponseCode.FAIL);
            map.put("desc", ResponseCode.FAIL_DESC);
        }else{
            map.put("total",billList.size());
            map.put("data",billList.subList(0,billList.size()));
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);
        }
        return map;
    }

    /**
     * create by huguokai date:2016年11月16日15:16:04
     * 校验话费充值记录数据
     * @return map
     */
    public Map<String ,Object> getBillInfo(){
        Map<String ,Object>  map = new HashMap<>();
        List<BillDto> list = queryBill.getBillInfo();
        if(list == null || list.size() == 0){
            map.put("code", ResponseCode.FAIL_BILL_INFO);
            map.put("desc",ResponseCode.FAIL_BILL_INFO_DESC);
        }else {
            map.put("data", list);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
        }
        return map;
    }

    /**
     * 校验钱包充值记录
     * @param recharge_phone
     * @return map
     * */
    public Map<String,Object> updateBillRechargeRecord(String recharge_phone,Integer id){
        Map<String,Object> map = new HashMap<>();
        BillDto billDto = queryBill.getBillinfoByIdAndPhone(id,recharge_phone);
        if(billDto == null){
            map.put("msg",100);
            return map;
        }
        if(billDto.getState() == 1){
            map.put("msg",200);
            return map;
        }

        int count = billPer.updateBillRechargeRecord(recharge_phone, id);

        if(count>0){
            map.put("msg",200);
        }else{
            map.put("msg",100);
        }
        return map;
    }


    /**
     * create by huguokai date:2016年11月16日15:15:35
     * 校验话费充值数据
     * @param userNo
     * @param phone
     * @param price
     * @param payType
     * @param dealPwd
     * @return map
     */
    public Map<String ,Object> billRechargeValidata(Integer userNo,String phone,String price,Integer payType,String dealPwd){
        Map<String ,Object> map = new HashMap<>();
        if(Integer.parseInt(price) < 0){
            map.put("code",ResponseCode.FAIL_PRICE);
            map.put("desc",ResponseCode.FAIL_PRICE_DESC);
        }else{
            UserInfoDto uid = querySubCore.getUserInfo(userNo);
            if(uid == null){
                map.put("code",ResponseCode.FAIL_USER_INFO);
                map.put("desc",ResponseCode.FAIL_USER_INFO_DESC);
            }else{
                if(dealPwd.equals(uid.getDeal_pwd())){
                    UserBalanceDto ubd =  querySubCore.getUserBalance(userNo,0);
                    if(ubd == null){
                        map.put("code",ResponseCode.FAIL_USER_BALANCE);
                        map.put("desc",ResponseCode.FAIL_USER_BALANCE_DESC);
                    }else{
                        if(ubd.getEnable_coin().compareTo(new BigDecimal(price)) == 1 || ubd.getEnable_coin().compareTo(new BigDecimal(price)) == 0){
                            try {
                                BillCommand billCommand = new BillCommand(userNo,phone,new BigDecimal(Integer.parseInt(price)),payType,new BigDecimal(Integer.parseInt(price)),null,0,new Date(),"update");
                                commandGateway.sendAndWait(billCommand);
                                map.put("code",ResponseCode.SUCCESS);
                                map.put("desc",ResponseCode.SUCCESS_DESC);
                            }catch (Exception e){
                                logger.error("话费充值失败");
                                e.printStackTrace();
                                map.put("code",ResponseCode.FAIL);
                                map.put("desc",ResponseCode.FAIL_DESC);
                            }
                        }else{
                            map.put("code",ResponseCode.FAIL_ENABLE_COIN);
                            map.put("desc",ResponseCode.FAIL_ENABLE_COIN_DESC);
                        }
                    }
                }else{
                    map.put("code",ResponseCode.FAIL_TRADE_PASSWORD);
                    map.put("desc",ResponseCode.FAIL_TRADE_PASSWORD_DESC);
                }
            }
        }
        return map;
    }
    
    
    //校验查询所有订单
    public Map<String,Object> getBillAllValidata()
    {
    	Map<String,Object> map=new HashMap<String, Object>();
    	List<BillDto> bills =  queryBill.getBillAll();
    	if(bills==null)
    	{
    		map.put("code","100");
    		map.put("desc","订单没有数据");
    	}
    	else{
    		map.put("code","200");
    		map.put("desc","查询成功");
    		map.put("total",bills.size());
    		map.put("data",bills);
    		
    	}
    	return map;
    }
    
    
    
    //添加
    public Map<String,Object> addBillValidata(Integer user_no, String recharge_phone, BigDecimal recharge_price, Integer pay_type, BigDecimal pay_price, Date handle_date, Integer state, Date date)
    {
    	Map<String,Object> map=new HashMap<String, Object>();
    	try{
    	BillCommand bc=new BillCommand(user_no, recharge_phone, recharge_price, pay_type, pay_price, handle_date, state, date, "insert");
    	commandGateway.sendAndWait(bc);
    	map.put("code","100");
		map.put("desc","添加成功");
		
    	}catch(Exception e){
    		map.put("code","200");
    		map.put("desc","添加失败");
    	}
    	return map;
    }
    

}
