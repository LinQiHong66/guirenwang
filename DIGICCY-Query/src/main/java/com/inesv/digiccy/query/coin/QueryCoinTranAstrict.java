package com.inesv.digiccy.query.coin;

import com.inesv.digiccy.dto.CoinTranAstrictDto;

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
 * 币种查询
 * Created by JimJim on 2016/11/17 0017.
 */
@Component
public class QueryCoinTranAstrict {

    private static Logger logger = LoggerFactory.getLogger(QueryCoinTranAstrict.class);

    @Autowired
    QueryRunner queryRunner;

    /**
     * 查询所有货币交易条件
     * @return
     */
    public List<CoinTranAstrictDto> queryAllCoinTranAstrict(){
        List<CoinTranAstrictDto> coinList = new ArrayList<>();
        try {
            String sql = "SELECT * from t_coin_tran_astrict";
            coinList = queryRunner.query(sql,new BeanListHandler<>(CoinTranAstrictDto.class));
        } catch (SQLException e) {
            logger.error("查询虚拟货币失败");
            e.printStackTrace();
        }
        return coinList;
    }
    /**
     * 查询所有货币交易条件
     * @return
     */
    public CoinTranAstrictDto queryAllCoinTranAstrictById(String id){
        CoinTranAstrictDto coin = new CoinTranAstrictDto();
        try {
            String sql = "SELECT * from t_coin_tran_astrict where id = ? ";
            Object params[] = {id};
            coin = queryRunner.query(sql,new BeanHandler<>(CoinTranAstrictDto.class),params);
        } catch (SQLException e) {
            logger.error("查询虚拟货币失败");
            e.printStackTrace();
        }
        return coin;
    }
    
    /**
     * 查询所有货币交易条件
     * @return
     */
    public CoinTranAstrictDto queryAllCoinTranAstrictByCoinNo(String coin_no){
        CoinTranAstrictDto coin = new CoinTranAstrictDto();
        try {
            String sql = "SELECT * from t_coin_tran_astrict where coin_no = ? ";
            Object params[] = {coin_no};
            coin = queryRunner.query(sql,new BeanHandler<>(CoinTranAstrictDto.class),params);
        } catch (SQLException e) {
            logger.error("查询虚拟货币失败");
            e.printStackTrace();
        }
        return coin;
    }
}
