package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.StaticParamsDto;

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
 * Created by JimJim on 2016/12/14 0014.
 */
@Component
public class QueryStaticParam {

    public static Logger logger = LoggerFactory.getLogger(QueryStaticParam.class);

    @Autowired
    QueryRunner queryRunner;

    public List<StaticParamsDto> getStaticParam(){
        String sql = "select * from t_inesv_static_param";
        List<StaticParamsDto> list = null;
        try {
            list = queryRunner.query(sql,new BeanListHandler<StaticParamsDto>(StaticParamsDto.class));
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("查询后台参数失败");
        }
        return list;
    }

    /**
     * 根据参数查询该参数对应的静态参数
     * @return
     */
    public StaticParamsDto getStaticParamByParam(String param){
        String sql = "select * from t_inesv_static_param where param=?";
        StaticParamsDto staticParam=null;
        try {
        	staticParam = queryRunner.query(sql,new BeanHandler<StaticParamsDto>(StaticParamsDto.class),param);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("查询后台静态参数失败");
        }
        return staticParam;
    }
}
