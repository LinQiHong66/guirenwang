package com.inesv.digiccy.persistence.param;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.PaymentBankDto;
/**
 * 
 * @author Liukeling
 *
 */
@Component
public class PaymentBankOperation {
	private static Logger logger = LoggerFactory.getLogger(StaticParamOperation.class);

    @Autowired
    QueryRunner queryRunner;
    
    public void modifyPaymentBank(PaymentBankDto dto){
    	String sql = "update t_inesv_platform_payment set bank_name=?, bank_account=?, bank_people=?, remark=? where id=?";
    	Object[] params = {
    			dto.getBankName(),
    			dto.getBankCardId(),
    			dto.getBankUserName(),
    			dto.getRemark(),
    			dto.getId()
    	};
    	try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("更新平台银行信息失败");
		}
    }
    
}
