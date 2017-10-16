package com.inesv.digiccy.query.coin;

import com.inesv.digiccy.dto.CoinTranTypeDto;
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
public class QueryCoinTranType {

    private static Logger logger = LoggerFactory.getLogger(QueryCoinTranType.class);

    @Autowired
    QueryRunner queryRunner;
    
    /**
     * 查询所有货币交易条件
     * @return
     */
    public List<CoinTranTypeDto> queryAllCoinTradeType(String coinNo){
        List<CoinTranTypeDto> coinList = new ArrayList<>();
        try {
            String sql = "SELECT tran_coin_no from t_coin_tran_type WHERE coin_no = ? AND state != 2";
            Object params[] = {coinNo};
            coinList = queryRunner.query(sql,new BeanListHandler<>(CoinTranTypeDto.class),params);
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
    public CoinTranTypeDto queryAllTradeTypeByTranCoin(String coinNo,String tranNo){
        CoinTranTypeDto coinDto = new CoinTranTypeDto();
        try {
            String sql = "SELECT coin_no , tran_coin_no , state from t_coin_tran_type WHERE coin_no = ? AND tran_coin_no = ? AND state != 2";
            Object params[] = {coinNo,tranNo};
            coinDto = queryRunner.query(sql,new BeanHandler<>(CoinTranTypeDto.class),params);
        } catch (SQLException e) {
            logger.error("查询虚拟货币失败");
            e.printStackTrace();
        }
        return coinDto;
    }

    /**
     * 查询所有货币交易条件
     * @return
     */
    public List<CoinTranTypeDto> queryAllCoinTradeType(){
        List<CoinTranTypeDto> coinList = new ArrayList<>();
        try {
        	String sql = "SELECT t2.coin_name AS attr1, t3.coin_name AS attr2, t1.id AS id, t1.state AS state,t1.date AS date "
            		+ " FROM t_coin_tran_type t1 , t_inesv_coin_type t2 , t_inesv_coin_type t3 "
            		+ " WHERE t1.state != 2 AND t1.coin_no = t2.coin_no AND t1.tran_coin_no = t3.coin_no";
            coinList = queryRunner.query(sql,new BeanListHandler<>(CoinTranTypeDto.class));
        } catch (SQLException e) {
            logger.error("查询虚拟货币失败");
            e.printStackTrace();
        }
        return coinList;
    }
    
    /**
     * 查询货币交易类型
     * @return
     */
    public CoinTranTypeDto queryAllCoinTradeTypeById(String id){
    	CoinTranTypeDto coin = new CoinTranTypeDto();
        try {
            String sql = "SELECT * from t_coin_tran_type WHERE id = ? AND state != 2 ";
            Object params[] = {id};
            coin = queryRunner.query(sql,new BeanHandler<>(CoinTranTypeDto.class),params);
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
    public List<CoinTranTypeDto> queryAllCoinTradeTypeByCoinNo(String coin_no){
    	List<CoinTranTypeDto> coinList = new ArrayList<>();
        try {
            String sql = "SELECT * from t_coin_tran_type where coin_no = ? AND state != 2 ";
            Object params[] = {coin_no};
            coinList = queryRunner.query(sql,new BeanListHandler<>(CoinTranTypeDto.class),params);
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
    public List<CoinTranTypeDto> queryAllCoinTradeTypeByCoinNo(String coin_no,String tran_coin_no){
    	List<CoinTranTypeDto> coinList = new ArrayList<>();
        try {
            String sql = "SELECT * from t_coin_tran_type where coin_no = ? AND tran_coin_no = ? AND state != 2 ";
            Object params[] = {coin_no,tran_coin_no};
            coinList = queryRunner.query(sql,new BeanListHandler<>(CoinTranTypeDto.class),params);
        } catch (SQLException e) {
            logger.error("查询虚拟货币失败");
            e.printStackTrace();
        }
        return coinList;
    }
}
