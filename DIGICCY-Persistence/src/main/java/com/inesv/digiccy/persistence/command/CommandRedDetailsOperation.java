package com.inesv.digiccy.persistence.command;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.CommandRedDetailDto;

import java.sql.SQLException;

/**
 * Created by JimJim on 2016/12/9 0009.
 */
@Component
public class CommandRedDetailsOperation {
	
	private static Logger logger = LoggerFactory.getLogger(CommandRedDetailsOperation.class);

    @Autowired
    QueryRunner queryRunner;
    
    /*
     * 增加口令红包信息
     */
    public void insertDetails(CommandRedDetailDto commandRedDetailsDto) throws Exception{
        String sql = "INSERT INTO t_command_red_detail (user_id,command_id,command_number,command_name_price,state,date,attr1) VALUES (?,?,?,?,?,?,?)";
        Object params[] = {commandRedDetailsDto.getUser_id(),
        		commandRedDetailsDto.getCommand_id(),
        		commandRedDetailsDto.getCommand_number(),
        		commandRedDetailsDto.getCommand_name_price(),
        		commandRedDetailsDto.getState(),
        		commandRedDetailsDto.getDate(),
        		commandRedDetailsDto.getAttr1()};
        queryRunner.update(sql,params);
    }
    
    /*
     * 修改口令红包信息
     */
    public void updateDetails(CommandRedDetailDto commandRedDetailsDto) throws Exception{
        String sql = "UPDATE t_command_red_detail set state=? WHERE id=?";
        Object params[] = {commandRedDetailsDto.getState(),
        		commandRedDetailsDto.getId()};
        queryRunner.update(sql,params);
    }

}
