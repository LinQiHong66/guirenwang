package com.inesv.digiccy.query;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.PaymentBankDto;

/**
 * 
 * @author Liukeling
 *
 */
@Component
public class QueryPaymentBank {

    @Autowired
    QueryRunner queryRunner;
    
    public PaymentBankDto getBankInfo(){
    	String sql = "select id as id, bank_name as bankName, bank_account as bankCardId, bank_people as bankUserName, remark as remark from t_inesv_platform_payment";
    	try {
			List<PaymentBankDto> list = queryRunner.query(sql, new BeanListHandler<PaymentBankDto>(PaymentBankDto.class));
			if(list != null && !list.isEmpty()){
				return list.get(0);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
}
