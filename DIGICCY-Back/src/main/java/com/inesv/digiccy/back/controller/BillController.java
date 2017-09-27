package com.inesv.digiccy.back.controller;

import com.inesv.digiccy.api.command.BillCommand;
import com.inesv.digiccy.validata.BillValidata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Created by JimJim on 2016/12/16 0016.
 */
@Controller
@RequestMapping("/bill")
public class BillController {
     private static Logger logger = LoggerFactory.getLogger(WalletLinkController.class);

    @Autowired
    BillValidata billValidata;

    @RequestMapping(value = "gotoBill",method = RequestMethod.GET)
    public String gotoCoin(){
        return "/rmb/bill";
    }

    @RequestMapping(value = "getAllBill",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getAllBill(){
        Map<String,Object> billMap = billValidata.getAllBill();
        return billMap;
    }

    @RequestMapping(value = "updateBillRechargeRecord",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateBillRechargeRecord(@RequestParam String recharge_phone,@RequestParam Integer id){
        Map<String,Object> coinMap = billValidata.updateBillRechargeRecord(recharge_phone,id);
        return coinMap;
    }

}
