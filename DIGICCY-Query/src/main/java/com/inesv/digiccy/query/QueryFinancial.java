package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.FinancialDto;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
@Component
public class QueryFinancial {

    private static Logger logger = LoggerFactory.getLogger(QueryFinancial.class);

    @Autowired
    private QueryRunner queryRunner;

    /**
     * create by huguokai date:2016年11月16日12:53:36
     * 查询理财记录
     * @param userNo
     * @return list
     */
    public List<FinancialDto> getFinancialInfo(Integer userNo){
        String sql = "select * from t_inesv_financial where user_no = ?";
        Object params[] = {userNo};
        List<FinancialDto> list = null;
        try {
           list = (List<FinancialDto>) queryRunner.query(sql,new BeanListHandler<FinancialDto>(FinancialDto.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("查询理财记录");
        }
        return list;
    }

}
