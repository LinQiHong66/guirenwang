package com.inesv.digiccy.query.auth;

import com.inesv.digiccy.dto.auth.AuthRoleDto;
import com.inesv.digiccy.dto.auth.UserDto;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JimJim on 2016/11/7 0007.
 */
@Component
public class QueryRoles {

    @Autowired
    QueryRunner queryRunner;

    public List<AuthRoleDto> queryRole(){
        String sql = "select id,name,description from t_role";
        List<AuthRoleDto> role = new ArrayList<>();
        try {
            role = (List<AuthRoleDto>) queryRunner.query(sql,new BeanListHandler(AuthRoleDto.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    /**
     * 查询后台用户
     * @return
     */
    public List<UserDto> queryAuthUser(){
        String sql = "SELECT u.id AS id,u.name AS name,u.password AS password,r.name AS disabled,r.id AS roleId " +
                "FROM t_user_role ur " +
                "JOIN t_user u ON u.id = ur.user_id " +
                "JOIN t_role r ON r.id = ur.role_id ";
        List<UserDto> userList = null;
        try {
            userList = (List<UserDto>) queryRunner.query(sql,new BeanListHandler(UserDto.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

}
