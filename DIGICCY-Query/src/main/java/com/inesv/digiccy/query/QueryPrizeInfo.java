package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.InesvPrizeDto;

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
 * Created by Administrator on 2016/11/18 0018.
 */
@Component
public class QueryPrizeInfo {
    public static Logger logger = LoggerFactory.getLogger(QueryPrizeInfo.class);
    @Autowired
    QueryRunner queryRunner;

    public List<InesvPrizeDto> getPrizeInfo(Integer userNo){
        String sql = "SELECT * FROM t_inesv_prize where user_no=? ";
        Object params[] = {userNo};
        List<InesvPrizeDto> list = null;
        try {
            list = queryRunner.query(sql,new BeanListHandler<InesvPrizeDto>(InesvPrizeDto.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("查询我的奖品失败");
        }
        return list;

    }

}
