package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.CommandRedDetailDto;
import com.inesv.digiccy.dto.CommandRedDto;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 众筹项目查询
 * Created by JimJim on 2017/06/05 0017.
 */
@Component
public class QueryCommandRedInfo {

    private static Logger logger = LoggerFactory.getLogger(QueryCommandRedInfo.class);

    @Autowired
    QueryRunner queryRunner;
    
    /**
     * 查询所有口令红包信息
     * @return
     */
    public List<CommandRedDto> queryAllCommandRed(){
        List<CommandRedDto> commandRedList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM t_command_red WHERE state != 2 Group By command_no";
            commandRedList = queryRunner.query(sql,new BeanListHandler<>(CommandRedDto.class));
        } catch (SQLException e) {
            logger.error("查询口令红包失败");
            e.printStackTrace();
        }
        return commandRedList;
    }
    
    /**
     * 查询所有用户获取口令红包信息
     * @return
     */
    public List<CommandRedDetailDto> queryAllCommandRedDetail(){
        List<CommandRedDetailDto> commandRedList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM t_command_red_detail";
            commandRedList = queryRunner.query(sql,new BeanListHandler<>(CommandRedDetailDto.class));
        } catch (SQLException e) {
            logger.error("查询口令红包失败");
            e.printStackTrace();
        }
        return commandRedList;
    }
    
    /**
     * 查询所有口令红包信息
     * @return
     */
    public CommandRedDto queryCommandRed(String command_no){
        CommandRedDto commandRed = new CommandRedDto();
        String sql = "SELECT * FROM t_command_red WHERE command_no = ? Group By command_no";
        Object params[] = {command_no};
        try {
            commandRed = queryRunner.query(sql,new BeanHandler<>(CommandRedDto.class),params);
        } catch (SQLException e) {
            logger.error("查询口令红包失败");
            e.printStackTrace();
        }
        return commandRed;
    }
    
    /**
     * 查询指定口令红包包含的信息
     * @return
     */
    public List<CommandRedDto> queryAllCommandRedInfo(String command_no){
        List<CommandRedDto> commandRedList = new ArrayList<>();
        String sql = "SELECT * FROM t_command_red WHERE command_no = ?";
        Object params[] = {command_no};
        try {
            commandRedList = queryRunner.query(sql,new BeanListHandler<>(CommandRedDto.class),params);
        } catch (SQLException e) {
            logger.error("查询口令红包失败");
            e.printStackTrace();
        }
        return commandRedList;
    }
    
    /**
     * 查询指定口令红包信息
     * @return
     */
    public CommandRedDto queryCrowdFundingInfoNumber(String command_number){
    	CommandRedDto commandRedDto = new CommandRedDto();
        String sql = "SELECT * FROM t_command_red WHERE command_number=?";
        Object params[] = {command_number};
        try {
        	commandRedDto = queryRunner.query(sql,new BeanHandler<>(CommandRedDto.class),params);
        } catch (SQLException e) {
            logger.error("查询口令红包失败");
            e.printStackTrace();
        }
        return commandRedDto;
    }

}
