package com.inesv.digiccy.query.auth;

import com.inesv.digiccy.dto.auth.ResourceDto;
import com.inesv.digiccy.dto.auth.TreeItemDto;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by JimJim on 2016/11/7 0007.
 */
@Component
public class QueryResources {

    @Autowired
    QueryRunner queryRunner;

    public List<ResourceDto> queryResources(){
        String sql = "select id,type,value,parent from t_resource where parent is null";
        List<ResourceDto> res = new ArrayList<>();
        try {
            res = (List<ResourceDto>) queryRunner.query(sql,new BeanListHandler(ResourceDto.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 根据上级菜单id查询下级菜单
     * @param id
     * @return
     */
    public List<ResourceDto> querySubResources(Integer id){
        String sql = "select id,type,value,parent from t_resource where parent = ?";
        List<ResourceDto> res = new ArrayList<>();
        Object params[] = {id};
        try {
            res = (List<ResourceDto>) queryRunner.query(sql,new BeanListHandler(ResourceDto.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }


    /**
     * 生成树形菜单
     * @param roleId
     * @return
     */
    public List<TreeItemDto> queryRoleResource(Integer roleId){
        List<TreeItemDto> treeItems = new ArrayList<>();
        try{
            //查询所有权限
            String queryAllRes = "select id id,type AS name,parent AS pid from t_resource";
            List<TreeItemDto> allItems = (List<TreeItemDto>) queryRunner.query(queryAllRes,new BeanListHandler(TreeItemDto.class));
            Map<Integer,TreeItemDto> treeItemMap = new HashMap<>();
            for (TreeItemDto tree:allItems) {
                if(tree.getpId() == null ){
                    tree.setpId(0);
                }
                treeItemMap.put(tree.getId(),tree);
            }
            //查询角色拥有的权限
            String queryResourcesByRoleIdSql = "SELECT re.id as id,re.type as type,re.value as value,re.parent as parent " +
                    "FROM t_role_resource rr " +
                    "JOIN t_role ro ON ro.id = rr.role_id  " +
                    "JOIN t_resource re ON re.id =rr.resource_id " +
                    "WHERE ro.id = ?";
            Object params[] = {roleId};
            List<ResourceDto> roleRes = (List<ResourceDto>) queryRunner.query(queryResourcesByRoleIdSql,new BeanListHandler(ResourceDto.class),params);
            for (ResourceDto res:roleRes) {
                treeItemMap.get(res.getId()).setChecked(true);
            }
            Iterator it = treeItemMap.keySet().iterator();
            while (it.hasNext()) {
                Integer key = Integer.valueOf(it.next().toString());
                treeItems.add(treeItemMap.get(key));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return treeItems;
    }



}
