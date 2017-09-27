package com.inesv.digiccy.persistence.coin;

import com.inesv.digiccy.dto.CoinDto;
import com.inesv.digiccy.dto.CoinTranAstrictDto;
import com.inesv.digiccy.util.ObjectChangeUtil;
import com.inesv.digiccy.util.TableName;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * 虚拟货币持久层，事务处理层
 * Created by JimJim on 2016/11/17 0017.
 */
@Component
public class CoinTranAstrictOperation {

    private static Logger logger = LoggerFactory.getLogger(CoinTranAstrictOperation.class);

    @Autowired
    QueryRunner queryRunner;

    /**
     * 新增货币
     * @param coinDto
     */
    public void addCoinTranAstrict(CoinTranAstrictDto coinDto){
    	String sql = " INSERT INTO t_coin_tran_astrict "
                + " ( coin_no ,buy_min_price ,buy_max_price ,sell_min_price ,sell_max_price ,single_min_price ,single_max_price, "
                + " rose_astrict ,drop_astrict ,begin_date ,end_date ,state) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        Object params[] = {coinDto.getCoin_no(),coinDto.getBuy_min_price(),coinDto.getBuy_max_price(),
        		coinDto.getSell_min_price(),coinDto.getSell_max_price(),coinDto.getSingle_min_price(),coinDto.getSingle_max_price(),
        		coinDto.getRose_astrict(),coinDto.getDrop_astrict(),coinDto.getBegin_date(),coinDto.getEnd_date(),coinDto.getState()};
        try {
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            logger.error("新增货币交易条件错误");
            e.printStackTrace();
        }
    }

    /**
     * 修改货币
     * @param coinDto
     */
    public void updateCoinTranAstrict(CoinTranAstrictDto coinDto){
        String sql = " UPDATE t_coin_tran_astrict "
                + " SET coin_no = ? ,buy_min_price = ?,buy_max_price = ? ,sell_min_price = ?,sell_max_price = ?,single_min_price = ?,single_max_price=?, "
                + " rose_astrict = ? ,drop_astrict=? ,begin_date=? ,end_date=? ,state=? "
                + " WHERE id = ? ";

        Object params[] = {coinDto.getCoin_no(),coinDto.getBuy_min_price(),coinDto.getBuy_max_price(),
        		coinDto.getSell_min_price(),coinDto.getSell_max_price(),coinDto.getSingle_min_price(),coinDto.getSingle_max_price(),
        		coinDto.getRose_astrict(),coinDto.getDrop_astrict(),coinDto.getBegin_date(),coinDto.getEnd_date(),coinDto.getState(),coinDto.getId()};
        try {
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            logger.error("修改货币交易条件错误");
            e.printStackTrace();
        }
    }

    /**
     * 删除货币
     * @param coin_no
     */
    public void deleteCoinTranAstrict(CoinTranAstrictDto coinDto){
        String sql = "DELETE FROM t_coin_tran_astrict WHERE id = ?";
        Object params[] = {coinDto.getId()};
        try {
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            logger.error("删除货币交易条件错误");
            e.printStackTrace();
        }
    }

}
