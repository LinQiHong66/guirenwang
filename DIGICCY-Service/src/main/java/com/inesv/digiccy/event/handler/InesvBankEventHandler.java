package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.dto.InesvBankInfo;
import com.inesv.digiccy.event.InesvBankInfoEvent;
import com.inesv.digiccy.persistence.bankinfo.OperationBankInfo;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;


import java.sql.SQLException;


/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class InesvBankEventHandler {

    @Autowired
    OperationBankInfo operationBankInfo;

   @EventHandler
    public void bankInfoHandle(InesvBankInfoEvent bankInfoEvent) throws SQLException {
       InesvBankInfo inesvBankInfo = new InesvBankInfo();
       inesvBankInfo.setId(bankInfoEvent.getId());
       inesvBankInfo.setUser_no(bankInfoEvent.getUser_no());
       inesvBankInfo.setRemark_name(bankInfoEvent.getRemark_name());
       inesvBankInfo.setBank(bankInfoEvent.getBank());
       inesvBankInfo.setProvince(bankInfoEvent.getProvince());
       inesvBankInfo.setCity(bankInfoEvent.getCity());
       inesvBankInfo.setBranch(bankInfoEvent.getBranch());
       inesvBankInfo.setName(bankInfoEvent.getName());
       inesvBankInfo.setBank_num(bankInfoEvent.getBank_num());
       inesvBankInfo.setDate(bankInfoEvent.getDate());
       inesvBankInfo.setState(1);
       String operation = bankInfoEvent.getOperation();
       System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
       switch (operation){
           case "insert":
               operationBankInfo.insertBankInfo(inesvBankInfo);
               break;
           case "update":
               operationBankInfo.updateBankinfo(inesvBankInfo);
               break;
           case "delete":
               operationBankInfo.deleteBankInfo(inesvBankInfo);
               break;

           default:
               break;
       }
   }
}
