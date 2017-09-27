package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.InesvUserDto;

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
public class QueryUserNamePhoneInfo {
    private static Logger logger = LoggerFactory.getLogger(QueryUserNamePhoneInfo.class);

    @Autowired
    QueryRunner queryRunner;

    /**
     *根据用户名和手机号查询
     */
    public List<InesvUserDto> getUserNamePhoneInfo(String username){
        /*String sql = "SELECT * FROM t_inesv_user WHERE username = ? and phone=? ";*/
    	String sql = "SELECT * FROM t_inesv_user WHERE username = ?";
        Object params[] = {username};
        List<InesvUserDto> list = null;
        try {
            list = queryRunner.query(sql,new BeanListHandler<InesvUserDto>(InesvUserDto.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("根据用户名和手机号查询失败");
        }
        return list;
    }

}
