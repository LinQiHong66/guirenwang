package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.DealDetailDto;
import com.inesv.digiccy.dto.EntrustDto;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
@Component
public class QueryEntrustDealInfo {
    private static Logger log = LoggerFactory.getLogger(QueryEntrustDealInfo.class);

    @Autowired
    QueryRunner queryRunner;

    /**
     * 委托成交查询
     */
    public List<DealDetailDto> queryEntrustDealInfoByEntrustTypeEntrustCoin(Integer userNo, Integer dealType, Integer coinNo){
        List<DealDetailDto> list = null;
        String querySql = "select * FROM t_inesv_deal_detail  WHERE user_no=?";
        if(!dealType.equals(2)){
        	querySql+=" and deal_type ="+ dealType;
        }
        querySql+=" and coin_no=? order by date desc";
        Object params[] = {userNo,coinNo};
        try {
            list=queryRunner.query(querySql,new BeanListHandler<DealDetailDto>(DealDetailDto.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("根据交易类型、货币类型查询失败");
        }
        return list;
    }
    
    /**
     * 委托管理记录
     */
	public List<EntrustDto> queryEntrustManageInfoByUserNo(Integer userNo,
			Integer entrustType, Integer entrustCoin, Integer state) {
		List<EntrustDto> EntrustList = null;
		// 当entrustType=2，state=3时，查询某个用户某种货币的全部委托类型 entrustCoin货币类型
		String querySql="select * from t_inesv_entrust where user_no=? and entrust_coin=? ";
		if (!entrustType.equals(2)) {
			querySql+=" and entrust_type="+entrustType;
		}
		if (!state.equals(3)) {
			querySql+=" and state="+state;
		}
		querySql+=" order by date desc limit 0,20";
		Object params[] = { userNo,entrustCoin};
		try {
			EntrustList = queryRunner.query(querySql,new BeanListHandler<EntrustDto>(EntrustDto.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("根据用户查询委托记录失败");
		}
		return EntrustList;
	}

    /**
     * 根据委托id和用户编号查询委托记录
     */
    public EntrustDto queryEntrustInfoByID(Long id,Integer userNo){
        String querySql = "select * FROM t_inesv_entrust  WHERE id=? and user_no=? for update";
        Object params[] = {id,userNo};
    	EntrustDto ddd=null;
		try {
			ddd = queryRunner.query(querySql,new BeanHandler<EntrustDto>(EntrustDto.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("根据委托id和用户编号查询委托记录失败");
		}
        return ddd;
    }
    
    



}
