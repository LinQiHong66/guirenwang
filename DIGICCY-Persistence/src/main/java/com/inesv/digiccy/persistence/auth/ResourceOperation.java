package com.inesv.digiccy.persistence.auth;


import com.inesv.digiccy.dto.auth.ResourceDto;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by JimJim on 2016/11/10 0010.
 */
@Component
public class ResourceOperation {

    @Autowired
    QueryRunner queryRunner;

    /**
     * 新增权限
     * @return
     */
    public void addResource(ResourceDto res) throws Exception {

        String insertRole = "INSERT INTO t_resource(type,value,parent,common) values (?,?,?,?)";
        Object params[] ={res.getType(),res.getValue(),res.getParent(),res.getCommon()};
        queryRunner.update(insertRole,params);

    }

    /**
     * 修改资源
     * @return
     */
    public void updateResource(ResourceDto res) throws Exception {
        String updateRole = "UPDATE t_resource SET type = ?,value = ?,parent = ?,common = ? WHERE id = ?";
        Object params[] ={res.getType(),res.getValue(),res.getParent(),res.getCommon(),res.getId()};
        queryRunner.update(updateRole,params);

    }

    /**
     * 删除资源
     * @return
     */
    public void deleteResource(ResourceDto res) throws Exception{
//        String deleteRoleRes = "DELETE FROM t_role_resource WHERE role_id in " +
//                "(SELECT id FROM t_resource WHERE id = ? OR parent = ?)";
        String deleteRes = "DELETE FROM t_resource WHERE id = ? OR parent = ?";
        Object params[] ={res.getId(),res.getId()};
//        queryRunner.update(deleteRoleRes,params);
        queryRunner.update(deleteRes,params);
    }

    /**
     * 更新角色资源
     * @return
     */
    @Transactional(rollbackFor={Exception.class, RuntimeException.class})
    public void updateRoleResource(Integer roleId,String resources) throws Exception{
        String deleteRoleRes = "DELETE FROM t_role_resource WHERE role_id = ?";
        Object delParams[] ={roleId};
        queryRunner.update(deleteRoleRes,delParams);
        String[] resource = resources.split(",");
        String insertRoleRes = "INSERT INTO t_role_resource VALUES(?,?)";
        for (String res:resource) {
            Object insertParams[] ={roleId,res};
            queryRunner.update(insertRoleRes,insertParams);
        }
    }

}
