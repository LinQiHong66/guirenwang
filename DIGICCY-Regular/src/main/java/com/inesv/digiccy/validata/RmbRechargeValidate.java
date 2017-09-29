package com.inesv.digiccy.validata;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.api.command.RmbRechargeCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.dto.RmbRechargeDto;
import com.inesv.digiccy.dto.UserBalanceDto;
import com.inesv.digiccy.persistence.finance.RmbRechargePersistence;
import com.inesv.digiccy.query.QueryRmbRechargeInfo;
import com.inesv.digiccy.query.QueryRmbWithdrawInfo;
import com.inesv.digiccy.query.QueryUserBalanceInfo;
import com.inesv.digiccy.query.QueryUserInfo;
import com.inesv.digiccy.util.MD5;
import com.inesv.digiccy.validata.util.EasyPayUtil;
import com.inesv.digiccy.validata.util.ExcelUtils;

/**
 * Created by yc on 2016/12/9 0009.
 */
@Component
public class RmbRechargeValidate {

    @Autowired
    QueryRunner            queryRunner;

    @Autowired
    QueryRmbRechargeInfo   queryRmbRechargeInfo;

    @Autowired
    RmbRechargePersistence rmbRechargePersistence;

    @Autowired
    QueryUserBalanceInfo   queryUserBalanceInfo;

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private QueryUserInfo  queryUserInfo;

    @Autowired
    QueryRmbWithdrawInfo   queryRmbWithdrawInfo;


    /**
     * 校验确认充值信息
     */
    public Map<String, Object> validateGoRmbRecharge(int userNo, int recType, String recharge_price, String pwd, String order) {
        Map<String, Object> map = new HashMap();
        double rechargeCoin = Double.parseDouble(recharge_price); // 充值金额，转成double方便比较....
        BigDecimal recharge = BigDecimal.valueOf(rechargeCoin);
        //判断交易密码、可用金额
        InesvUserDto userinfo = queryUserInfo.getUserInfoById(Long.parseLong(userNo + ""));
        if(userinfo == null) {
        	map.put("code", "200");
            map.put("desc", "充值异常！");
            return map;
        }
        if (rechargeCoin < 1) {
            map.put("code", "200");
            map.put("desc", "充值金额不能低于1元");
            return map;
        }
        if(userinfo.getDeal_pwd() == null) {
        	map.put("code", "200");
            map.put("desc", "交易密码不能为空，请前往安全中心填写交易密码！");
            return map;
        }
        if (!userinfo.getDeal_pwd().equals(new MD5().getMD5(pwd))) {
            map.put("code", "200");
            map.put("desc", "交易密码错误");
            return map;
        }
        //添加订单
        RmbRechargeCommand command = new RmbRechargeCommand(4646L, userNo, recType, recharge, order, BigDecimal.ZERO, 0, new Date(), "insert");
        commandGateway.sendAndWait(command);// 发送命令
        String url = "";
        try {
            url = EasyPayUtil.pay(order, recharge.multiply(new BigDecimal(100)).longValue());
            map.put("url", url);
            map.put("desc", ResponseCode.SUCCESS_DESC);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "200");
            map.put("desc", "充值异常！");
        }
        return map;
    }

    /**
     * 获取用户信息，银行卡信息
     */
    public Map<String, Object> validateUserInfo(Long userNo) {
        Map<String, Object> map = new HashMap();
        // 用户信息
        InesvUserDto currentUser = queryUserInfo.getUserInfoById(userNo);
        currentUser.setDeal_pwd("");
        currentUser.setPassword("");

        if (currentUser != null) {
            map.put("currentUser", currentUser);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
        } else {
            map.put("code", ResponseCode.FAIL);

        }
        return map;
    }

    /**
     * 校验可用人民币及人民充值记录
     */
    public Map<String, Object> validateRmbRechargeInfo(int userNo, int coinType) {
        Map<String, Object> map = new HashMap();
        List<RmbRechargeDto> list = queryRmbRechargeInfo.qureyRechargeInfo(userNo);
        UserBalanceDto userBalanceDto = queryUserBalanceInfo.queryEnableCoin(userNo, coinType);
        BigDecimal enableCoin = userBalanceDto.getEnable_coin();
        if (!list.isEmpty() && enableCoin != null) {
            map.put("data", enableCoin);
            map.put("list", list);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
        } else {
            map.put("code", ResponseCode.FAIL);
            map.put("desc", ResponseCode.FAIL_DESC);
        }
        return map;
    }

    /**
     * 校验可用人民币及人民充值记录
     */
    public Map<String, Object> validateRmbRechargeBack() {
        Map<String, Object> map = new HashMap<String, Object>();
        RmbRechargeDto dto = queryRmbRechargeInfo.qureyRechargeBack();
        if (dto != null) {
            map.put("sumPrice", dto.getRecharge_price());
            map.put("dayPrice", dto.getActual_price());
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
        } else {
            map.put("code", ResponseCode.FAIL);
            map.put("desc", ResponseCode.FAIL_DESC);
        }
        return map;
    }

    /*
    	*//**
           * 校验确认充值信息
           *//* public Map<String, Object> validateGoRmbRecharge(int userNo, int recType, String recharge_price) {
             * Map<String, Object> map = new HashMap();
             * 
             * double rechargeCoin = Double.parseDouble(recharge_price); // 充值金额，转成double方便比较....
             * 
             * BigDecimal recharge = BigDecimal.valueOf(rechargeCoin);
             * String order = generateOrderNo();
             * RmbRechargeCommand command = new RmbRechargeCommand(4646L, userNo, recType, recharge, order, BigDecimal.ZERO, 0,
             * new Date(), "insert");
             * commandGateway.sendAndWait(command);// 发送命令
             * RmbRechargeDto rmbRechargeDto = queryRmbRechargeInfo.qureyRechargeInfo(userNo).get(0);
             * map.put("data", rmbRechargeDto);
             * map.put("code", ResponseCode.SUCCESS);
             * map.put("order", order);
             * map.put("desc", ResponseCode.SUCCESS_DESC);
             * 
             * return map;
             * } */

    /**
     * 校验修改用户充值状态
     */
    public Map<String, Object> validateUpdateStatu(int status, String orderNo, BigDecimal recharge_price) {
        Map<String, Object> map = new HashMap();
        if (orderNo != null) {

            RmbRechargeCommand command = new RmbRechargeCommand(6565L, 0, 0, BigDecimal.ZERO, orderNo, recharge_price, status, new Date(), "updateStatus");
            commandGateway.sendAndWait(command);// 发送命令
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
        } else {
            map.put("code", ResponseCode.FAIL);
            map.put("desc", ResponseCode.FAIL_DESC);
        }
        return map;
    }

    /* 查询人民币充值总条数 **/
    private long getRechargeSize(String userName, String state, String startDate, String endDate) throws SQLException {
        return queryRmbRechargeInfo.getRechargeSize(userName, state, startDate, endDate);
    }

    /**
     * 校验查询充值信息
     */
    public Map<String, Object> validateQueryRecord(String userName, String state, String startDate, String endDate, int curPage, int pageItem) {
        Map<String, Object> map = new HashMap();
        List<RmbRechargeDto> list = queryRmbRechargeInfo.qureyRechargeInfo(userName, state, startDate, endDate, curPage, pageItem);
        long size = 0;
        try {
            size = getRechargeSize(userName, state, startDate, endDate);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (list != null) {
            map.put("data", list);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
            map.put("count", size);
        } else {
            map.put("code", ResponseCode.FAIL);
            map.put("desc", ResponseCode.FAIL_DESC);
        }
        return map;
    }

    /** 获取表格 
     * @throws SQLException */
    public void getExcel(HttpServletResponse response, String userName, String state, String startDate, String endDate) throws SQLException {
    	long size = queryRmbRechargeInfo.getRechargeSize(userName, state, startDate, endDate);
    	List<RmbRechargeDto> dtos = queryRmbRechargeInfo.qureyRechargeInfo(userName, state, startDate, endDate, 1, Integer.parseInt(size+""));
        HashMap<String, List<String>> contact = new HashMap<String, List<String>>();
        String title1 = "用户名称";
        String title2 = "充值方式";
        String title3 = "充值金额";
        String title4 = "订单编号";
        String title5 = "日期";
        String title6 = "状态";
        List<String> value1 = new ArrayList<>();
        List<String> value2 = new ArrayList<>();
        List<String> value3 = new ArrayList<>();
        List<String> value4 = new ArrayList<>();
        List<String> value5 = new ArrayList<>();
        List<String> value6 = new ArrayList<>();

        for (RmbRechargeDto dto : dtos) {
            value1.add(dto.getAttr1());
            value2.add("易付通");
            value3.add(dto.getActual_price().toString());
            value4.add(dto.getRecharge_order());
            value5.add(dto.getDate().toString());
            value6.add(dto.getState() == 1 ? "已到账" : "未到账");
        }

        contact.put(title1, value1);
        contact.put(title2, value2);
        contact.put(title3, value3);
        contact.put(title4, value4);
        contact.put(title5, value5);
        contact.put(title6, value6);

        ExcelUtils.export(response, contact);
    }

    /**
     * 校验充值到账
     */
    public Map<String, Object> confirmToAccount(String ordId) {
        Map<String, Object> map = new HashMap();
        try {
            RmbRechargeCommand command = new RmbRechargeCommand(0,ordId, 0, "confirm");
            commandGateway.send(command);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
        } catch (Exception e) {
            map.put("code", ResponseCode.FAIL);
            map.put("desc", ResponseCode.FAIL_DESC);
        }
        return map;
    }

    /**
     * 生成订单号
     */
    public static String generateOrderNo() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Integer num = (int) (Math.random() * 9000 + 1000);
        String str = sdf.format(date) + num;
        return str;
    }

    /**
     * 回调银行接口是否充值成功
     */
    public void validateRechargeInfo() {
        Map<String, Object> map = new HashMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        System.out.println("*********************************");
        List<RmbRechargeDto> dtos = queryRmbRechargeInfo.qureyRechargeInfoByNo();
        for (int i = 0; i < dtos.size(); i++) {
            map = EasyPayUtil.query(dtos.get(i).getRecharge_order(), sdf.format(dtos.get(i).getDate()));
            if (map == null) {
                continue;
            } else {
                if (String.valueOf(map.get("respCode")).equals("0000")) {
                    try {
                        rmbRechargePersistence.confirmToOrder(dtos.get(i).getRecharge_order().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                        continue;
                    }
                }
            }
        }
    }

}
