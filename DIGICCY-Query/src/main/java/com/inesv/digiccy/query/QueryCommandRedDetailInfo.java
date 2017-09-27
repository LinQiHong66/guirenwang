package com.inesv.digiccy.query;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.CommandRedDetailDto;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 众筹项目查询
 * Created by JimJim on 2017/06/05 0017.
 */
@Component
public class QueryCommandRedDetailInfo {

    private static Logger logger = LoggerFactory.getLogger(QueryCommandRedDetailInfo.class);

    @Autowired
    QueryRunner queryRunner;
    
    /**
     * 查询该用户所有口令红包信息
     * @return
     * @throws SQLException 
     */
    public List<CommandRedDetailDto> queryAllCommandRedDetail(String user_id) throws Exception{
        String sql = "SELECT * FROM t_command_red_detail WHERE user_id = ?";
        Object parmas[] = {user_id};
        List<CommandRedDetailDto> CommandRedDetailList = queryRunner.query(sql,new BeanListHandler<>(CommandRedDetailDto.class),parmas);
        return CommandRedDetailList;
    }
    
    /**
     * 查询该用户所有口令红包信息
     * @return
     * @throws SQLException 
     */
    public CommandRedDetailDto queryAllCommandRedDetailById(String id) throws Exception{
        String sql = "SELECT * FROM t_command_red_detail WHERE id = ?";
        Object parmas[] = {id};
        CommandRedDetailDto commandRedDetailDto = queryRunner.query(sql,new BeanHandler<>(CommandRedDetailDto.class),parmas);
        return commandRedDetailDto;
    }

}
