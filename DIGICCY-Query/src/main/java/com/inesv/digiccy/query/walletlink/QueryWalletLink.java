package com.inesv.digiccy.query.walletlink;

import com.inesv.digiccy.dto.WalletLinkDto;
import com.inesv.digiccy.query.coin.QueryCoin;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JimJim on 2016/12/20 0020.
 */
@Component
public class QueryWalletLink {
    private static Logger logger = LoggerFactory.getLogger(QueryCoin.class);

    @Autowired
    QueryRunner queryRunner;

    /**
     * 查询所有货币
     * @return
     */
    public List<WalletLinkDto> queryAllWalletLink(){
        List<WalletLinkDto> walletLinkList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM t_inesv_wallet_link";
            walletLinkList = queryRunner.query(sql,new BeanListHandler<>(WalletLinkDto.class));
        } catch (SQLException e) {
            logger.error("查询钱包接口失败");
            e.printStackTrace();
        }
        return walletLinkList;
    }
}
