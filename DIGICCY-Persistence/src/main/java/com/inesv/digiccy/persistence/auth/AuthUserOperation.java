package com.inesv.digiccy.persistence.auth;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by JimJim on 2016/11/16 0016.
 */
@Component
public class AuthUserOperation {

    @Autowired
    QueryRunner queryRunner;

    /**
     * 新增用户
     * @return
     */
    @Transactional(rollbackFor={Exception.class, RuntimeException.class})
    public void addAuthUser(String name,String pass,Integer roleId) throws SQLException {

        String insertUser = "INSERT INTO t_user(name,password) values (?,?)";
        Object userParams[] ={name,pass};
        Map<Long, Map<String, Object>> key = queryRunner.insert(insertUser,new KeyedHandler<Long>(),userParams);
        Long userId = (Long) key.keySet().toArray()[0];
        String insertUserRole = "INSERT INTO t_user_role(user_id,role_id) values (?,?)";
        Object roleParams[] ={userId,roleId};
        queryRunner.update(insertUserRole,roleParams);

    }

    /**
     * 修改用户
     * @return
     */
    @Transactional(rollbackFor={Exception.class, RuntimeException.class})
    public void updateAuthUser(Integer userId,String name,String pass,Integer roleId) throws SQLException {

        String updateUser = "UPDATE t_user SET name = ? , password = ? WHERE id = ?";
        Object userParams[] ={name,pass,userId};
        queryRunner.update(updateUser,userParams);
        String updateUserRole = "UPDATE t_user_role ur SET role_id = ? WHERE user_id = ?";
        Object roleParams[] ={roleId,userId};
        queryRunner.update(updateUserRole,roleParams);

    }

    /**
     * 删除用户
     * @return
     */
    public void deleteAuthUser(Integer userId) throws SQLException {

//        String deleteUserRole = "DELETE FROM t_user_role WHERE id = ?";
//        Object roleParams[] ={userId};
//        queryRunner.update(deleteUserRole,roleParams);
        String deleteUser = "DELETE FROM t_user WHERE id = ?";
        Object userParams[] ={userId};
        queryRunner.update(deleteUser,userParams);

    }


}
