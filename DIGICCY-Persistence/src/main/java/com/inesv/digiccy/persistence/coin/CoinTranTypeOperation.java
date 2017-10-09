package com.inesv.digiccy.persistence.coin;

import com.inesv.digiccy.dto.CoinTranTypeDto;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * 虚拟货币持久层，事务处理层
 * Created by JimJim on 2016/11/17 0017.
 */
@Component
public class CoinTranTypeOperation {

    private static Logger logger = LoggerFactory.getLogger(CoinTranTypeOperation.class);

    @Autowired
    QueryRunner queryRunner;

    /**
     * 新增货币
     * @param coinDto
     */
    public void addCoinTradeType(CoinTranTypeDto coinDto){
    	String sql = " INSERT INTO t_coin_tran_type "
                + " ( coin_no ,tran_coin_no ,state ,date ) VALUES (?,?,?,?)";
        Object params[] = {coinDto.getCoin_no(), coinDto.getTran_coin_no(), coinDto.getState(), coinDto.getDate()};
        try {
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            logger.error("新增货币交易类型错误");
            e.printStackTrace();
        }
    }

   /**
     * 修改货币
     * @param coinDto
     */
    public void updateCoinTradeType(CoinTranTypeDto coinDto){
        String sql = " UPDATE t_coin_tran_type "
                + " SET coin_no = ? ,tran_coin_no = ?,state = ? ,date = ? WHERE id = ? ";
        Object params[] = {coinDto.getCoin_no(), coinDto.getTran_coin_no(), coinDto.getState(),coinDto.getDate(),coinDto.getId()};
        try {
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            logger.error("修改货币交易类型错误");
            e.printStackTrace();
        }
    }

    /**
     * 删除货币
     * @param coin_no
     */
    public void deleteCoinTradeType(CoinTranTypeDto coinDto){
        String sql = "UPDATE t_coin_tran_type SET state = 2 WHERE id = ?";
        Object params[] = {coinDto.getId()};
        try {
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            logger.error("删除货币交易类型错误");
            e.printStackTrace();
        }
    }

}
