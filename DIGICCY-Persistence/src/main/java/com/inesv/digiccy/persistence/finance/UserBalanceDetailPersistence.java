package com.inesv.digiccy.persistence.finance;

import com.inesv.digiccy.dto.RecSubProfitDto;
import com.inesv.digiccy.dto.UserBalanceDetailDto;
import com.inesv.digiccy.dto.UserBalanceDto;
import com.inesv.digiccy.util.ObjectChangeUtil;
import com.inesv.digiccy.util.TableName;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/12/1 0001.
 */
@Component
public class UserBalanceDetailPersistence {

	private static Logger logger = LoggerFactory.getLogger(UserBalanceDetailPersistence.class); 
	
    @Autowired
    QueryRunner queryRunner;

    /**
     *新增币种交易记录
     */
    public void insertDetail(UserBalanceDetailDto userBalanceDetailDto) throws Exception{
        String sql = "INSERT INTO t_inesv_user_balance_detail (user_no,admin_no,coin_type,coin_price,remark,date) VALUES (?,?,?,?,?,?)";
        Object parmas[] = {userBalanceDetailDto.getUser_no(),userBalanceDetailDto.getAdmin_no(),
        		userBalanceDetailDto.getCoin_type(),userBalanceDetailDto.getCoin_price(),
        		userBalanceDetailDto.getRemark(),userBalanceDetailDto.getDate()};
        queryRunner.update(sql,parmas);
    }
    
}
