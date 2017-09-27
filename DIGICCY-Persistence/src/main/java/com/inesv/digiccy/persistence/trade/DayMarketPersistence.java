package com.inesv.digiccy.persistence.trade;

import java.sql.SQLException;

import com.inesv.digiccy.dto.CoinDto;
import com.inesv.digiccy.dto.InesvDayMarket;
import com.inesv.digiccy.util.ObjectChangeUtil;
import com.inesv.digiccy.util.TableName;

import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 角色信息增删改
 * Created by JimJim on 2016/11/8 0008.
 */
@Component
public class DayMarketPersistence {

    @Autowired
    QueryRunner queryRunner;

    /**
     * 新增
     * @return
     * @throws SQLException 
     */
    public void addDayMarket(InesvDayMarket dayMarket) throws SQLException{
        String sql = new ObjectChangeUtil<InesvDayMarket>().objectToSql(dayMarket, TableName.T_INESV_DAY_MARKET);
        Object params[] = new ObjectChangeUtil<InesvDayMarket>().objectToArray(dayMarket);
        queryRunner.update(sql,params);
    }
}
