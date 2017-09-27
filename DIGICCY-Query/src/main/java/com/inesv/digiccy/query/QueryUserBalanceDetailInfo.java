package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.CoinCountDto;
import com.inesv.digiccy.dto.UserBalanceDetailDto;
import com.inesv.digiccy.dto.UserBalanceDto;
import com.inesv.digiccy.dto.UserInfoDto;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 查询用户拥有的资源
 * Created by JimJim on 2016/11/4 0004.
 */
@Component
public class QueryUserBalanceDetailInfo {

	private static Logger logger = LoggerFactory.getLogger(QueryUserBalanceDetailInfo.class);
	
    @Autowired
    QueryRunner queryRunner;

    /**
     * 根据用户查询用户的财务信息
     * @paramuserAuthoritys
     * @return
     * @throws SQLException
     */
    public List<UserBalanceDetailDto> queryAllUserBalanceDetail() throws Exception{
    	List<UserBalanceDetailDto> userBalanceDetailList= new ArrayList<>();
        String querySql="select * from t_inesv_user_balance_detail ";
        userBalanceDetailList=queryRunner.query(querySql,new BeanListHandler<UserBalanceDetailDto>(UserBalanceDetailDto.class));
        return userBalanceDetailList;
    }

}
