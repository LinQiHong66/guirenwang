package com.inesv.digiccy.persistence.finance;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.NoticeDto;
import com.inesv.digiccy.dto.RecSubProfitDto;
import com.inesv.digiccy.persistence.notice.NoticeOperation;
import com.inesv.digiccy.util.ObjectChangeUtil;
import com.inesv.digiccy.util.TableName;

import java.sql.SQLException;

/**
 * Created by yc on 2016/12/20 0020.
 */
@Component
public class RecSubProfitPersistence {

	private static Logger logger = LoggerFactory.getLogger(RecSubProfitPersistence.class); 
	
    @Autowired
    QueryRunner queryRunner;


    /**
     *新增用户返佣记录
     */
    public void addRecProfit(RecSubProfitDto recSubProfitDto){
        String sql = new ObjectChangeUtil<RecSubProfitDto>().objectToSql(recSubProfitDto, TableName.T_INESV_REC_SUB_PROFIT);
        Object params[] = new ObjectChangeUtil<RecSubProfitDto>().objectToArray(recSubProfitDto);
        try {
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            logger.error("新增返佣记录错误");
            e.printStackTrace();
        }
    }
}
