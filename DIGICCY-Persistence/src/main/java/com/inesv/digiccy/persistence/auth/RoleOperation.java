package com.inesv.digiccy.persistence.auth;

import com.inesv.digiccy.dto.auth.AuthRoleDto;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 角色信息增删改
 * Created by JimJim on 2016/11/8 0008.
 */
@Component
public class RoleOperation {

    @Autowired
    QueryRunner queryRunner;

    /**
     * 新增角色
     * @return
     */
    public void addRole(AuthRoleDto role) throws Exception {

        String insertRole = "INSERT INTO t_role(name,description) values (?,?)";
        Object params[] ={role.getName(),role.getDescription()};
        queryRunner.update(insertRole,params);

    }

    /**
     * 修改角色
     * @return
     */
    public void updateRole(AuthRoleDto role) throws Exception {
        String updateRole = "UPDATE t_role SET name = ?,description = ? WHERE id = ?";
        Object params[] ={role.getName(),role.getDescription(),role.getId()};
        queryRunner.update(updateRole,params);

    }

    /**
     * 删除角色
     * @return
     */
    public void deleteRole(AuthRoleDto role) throws Exception{
        String deleteRole = "DELETE FROM t_role WHERE id = ? ";
        Object params[] ={role.getId()};
        queryRunner.update(deleteRole,params);
    }


}
