package com.inesv.digiccy.persistence.user;

import com.inesv.digiccy.dto.InesvUserOrganizationDto;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * Created by JimJim on 2016/12/9 0009.
 */
@Component
public class UserOrganizationOperation {

    @Autowired
    QueryRunner queryRunner;
    
    /*
     * 升级机构
     */
    public void updateUserOrganization(Integer id,Integer state,Integer org_type,String org_code,Integer userNo,String recCode) throws Exception{
        String sqlOrganization = "UPDATE t_inesv_user_organization SET state = ? WHERE id = ?";
        Object params1[] = {state,id};
        	queryRunner.update(sqlOrganization,params1);
        String sqlUser = "UPDATE t_inesv_user SET org_type = ?,org_code = ?,invite_num = ? WHERE user_no = ?";
        Object params2[] = {org_type,org_code,recCode,userNo};
        	queryRunner.update(sqlUser,params2);
        String sqlSequence = "insert into t_sequence(seq_name,current_val,step) values(?,?,?)";
    	Object params3[] = {recCode + "-" + org_type,0,1};
    		queryRunner.update(sqlSequence,params3);
    }

    /**
     * 新增用户机构申请
     */
    public void addUserOrganization(InesvUserOrganizationDto inesvUserOrganizationDto) throws Exception{
        String sql = "INSERT INTO t_inesv_user_organization (user_no,state,org_type,date) " + 
        		" VALUES (?,?,?,?) ";
        Object params[] = {inesvUserOrganizationDto.getUser_no(),inesvUserOrganizationDto.getState(),inesvUserOrganizationDto.getOrg_type(),new Date()};
            queryRunner.update(sql,params);
    }

}
