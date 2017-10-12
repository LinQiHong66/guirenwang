package com.inesv.digiccy.persistence.bonus;

import com.inesv.digiccy.dto.BonusDetailDto;
import com.inesv.digiccy.dto.CoinCountDto;
import com.inesv.digiccy.dto.UserBalanceDto;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by JimJim on 2016/12/7 0007.
 */
@Component
public class BonusOperation {

    @Autowired
    QueryRunner queryRunner;


    public void insertBonusDetailed(BonusDetailDto bonusDetail) throws SQLException {
        String insert = "INSERT into t_inesv_bonus_detail(bonus_name,coin_type,num,date) VALUES(?,?,?,?)";
        Object params[] = {bonusDetail.getBonus_name(),bonusDetail.getCoin_type(),bonusDetail.getNum(),bonusDetail.getDate()};
        queryRunner.update(insert,params);
    }

    public void updateBonusDetailed(Integer id,String bonusName, Integer coin, BigDecimal num) throws SQLException {
        String update = "UPDATE t_inesv_bonus_detail SET bonus_name=?,coin_type=?,num=? WHERE id=?";
        Object params[] = {bonusName,coin,num,id};
        queryRunner.update(update,params);
    }
    
    public void updateBalance(BigDecimal enable_coin,BigDecimal total_price,Integer user_no,Integer coin_type) throws SQLException {
        String update = "UPDATE t_inesv_user_balance SET enable_coin = enable_coin + ?,total_price = total_price + ? WHERE user_no=? and coin_type = ?";
        Object params[] = {enable_coin,total_price,user_no,coin_type};
        queryRunner.update(update,params);
    }

    public void deteleBonusDetailed(Integer id) throws SQLException {
        String delete = "DELETE FROM t_inesv_bonus_detail WHERE id=?";
        Object params[] = {id};
        queryRunner.update(delete,params);
    }

    /**
     * 分红业务
     * @param bonusId
     * @param bonusName
     * @param coinId
     * @param num
     */
    @Transactional(rollbackFor={Exception.class, RuntimeException.class})
    public void doBonus(String bonusName,Integer coinId,BigDecimal num) throws Exception{
        try {
            //查询分红货币的全站持币量
            String queryCoin = "SELECT c.coin_no AS coinId,c.coin_name AS coinname,IFNULL(SUM(total_price),0)  AS total " +
                    "FROM t_inesv_user_balance b " +
                    "RIGHT JOIN t_inesv_coin_type c ON c.coin_no = b.coin_type " +
                    "WHERE c.coin_no = ? ";
            Object coinParam[] = {coinId};
            CoinCountDto coinCount = queryRunner.query(queryCoin,new BeanHandler<CoinCountDto>(CoinCountDto.class),coinParam);
            //计算每个用户的分红比例和分红后获得的数量
            String queryUserBalance = "SELECT * FROM t_inesv_user_balance WHERE coin_type = ?";
            List<UserBalanceDto> userBalanceList = queryRunner.query(queryUserBalance,new BeanListHandler<>(UserBalanceDto.class),coinParam);
            for (UserBalanceDto ub:userBalanceList) {
                BigDecimal scale = ub.getTotal_price().divide(coinCount.getTotal(),2,BigDecimal.ROUND_HALF_EVEN);
                BigDecimal coinNum = num.multiply(scale);
                //修改用户资产表
                String updateBalance = "UPDATE t_inesv_user_balance SET enable_coin = ? WHERE user_no = ? and coin_type = ?";
                Object balanceParam[] = {coinNum.add(ub.getEnable_coin()),ub.getUser_no(),coinId};
                queryRunner.update(updateBalance,balanceParam);
                //插入Bonus表
                String insertBonus = "INSERT INTO t_inesv_bonus(user_no,coin_type,total,assets,percent,date) " +
                        "VALUES(?,?,?,?,?,?)";
                Object bonusParam[] = {ub.getUser_no(),coinId,coinCount.getTotal(),coinNum,scale,new Date()};
                queryRunner.update(insertBonus,bonusParam);
                //插入BonusRecord表
                String insertRecord = "INSERT INTO t_inesv_bonus_record(user_no,bonus_type,bonus_name,reward_type," +
                        "bonus_sum,each_bonus,coin_num,bonus,date) " +
                        "VALUES(?,?,?,?,?,?,?,?,?)";
                Object recordParam[] = {ub.getUser_no(),coinId,bonusName,coinId,num,num,ub.getTotal_price(),coinNum,new Date()};
                queryRunner.update(insertRecord,recordParam);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 分红业务
     * @param bonusId
     * @param bonusName
     * @param coinId
     * @param num
     */
    @Transactional(rollbackFor={Exception.class, RuntimeException.class})
    public void doLevelBonus(Long entrustNo,BigDecimal level_price,BigDecimal sum_price, Integer coin_type,Integer rel_user_no, String rel_code, Integer user_no, String user_code, Integer entrustType) throws Exception{
    	/*
    	 * 插入分红记录
    	 */
    	String insertBonus = "INSERT INTO t_inesv_bonus_level(bonus_source,bonus_coin,bonus_rel,bonus_user,bonus_type,bonus,remark) " +
                "VALUES (?,?,?,?,?,?,?)";
    	if(entrustType == 0 || entrustType == 1) {
    		Object bonusParam[] = {entrustNo,coin_type,rel_user_no,user_no,entrustType,level_price,"交易-产生分红"};
            queryRunner.update(insertBonus,bonusParam);
    	}else {
    		Object bonusParam[] = {entrustNo,coin_type,rel_user_no,user_no,entrustType,level_price,"其他-产生分红"};
            queryRunner.update(insertBonus,bonusParam);
    	}
    	/*
    	 * 修改货币金额
    	 */
    	 String update = "UPDATE t_inesv_user_balance SET enable_coin = enable_coin + ?,total_price = total_price + ? WHERE user_no=? and coin_type = ?";
         Object params[] = {level_price,level_price,rel_user_no,coin_type};
         queryRunner.update(update,params);
            
    }


}
