package com.inesv.digiccy.persistence.param;

import com.inesv.digiccy.dto.StaticParamsDto;
import com.inesv.digiccy.util.ObjectChangeUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * Created by JimJim on 2016/12/14 0014.
 */
@Component
public class StaticParamOperation {

    private static Logger logger = LoggerFactory.getLogger(StaticParamOperation.class);

    @Autowired
    QueryRunner queryRunner;

    /**
     * 新增后台参数
     * @param staticParams
     */
    public void addStaticParam(StaticParamsDto staticParams){
        String sql = new ObjectChangeUtil<StaticParamsDto>().objectToSql(staticParams,"t_inesv_static_param");
        Object params[] = new ObjectChangeUtil<StaticParamsDto>().objectToArray(staticParams);
        try {
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            logger.error("新增后台参数错误");
            e.printStackTrace();
        }
    }

    /**
     * 修改后台参数
     * @param staticParams
     */
    public void updateStaticParam(StaticParamsDto staticParams){
        String sql = "UPDATE t_inesv_static_param SET param = ?,value= ?, code=? where id =?";
        Object params[] = {staticParams.getParam(),staticParams.getValue(),staticParams.getCode(), staticParams.getId()};
        try {
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            logger.error("修改后台参数错误");
            e.printStackTrace();
        }
    }

    /**
     * 删除后台参数
     */
    public void deteleStaticParam(Integer id){
        String sql = "DELETE FROM t_inesv_static_param WHERE id = ?";
        Object params[] = {id};
        try {
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            logger.error("删除后台参数错误");
            e.printStackTrace();
        }
    }

}
