package com.inesv.digiccy.query.auth;

import com.inesv.digiccy.dto.auth.MenuDto;
import com.inesv.digiccy.dto.auth.ResourceDto;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.*;

/**
 * 查询用户拥有的资源
 * Created by JimJim on 2016/11/4 0004.
 */
@Component
public class QueryUserResources {

    @Autowired
    QueryRunner queryRunner;

    /**
     * 根据用户角色查询用户资源
     * @param userAuthoritys
     * @return
     */
    public List<MenuDto> queryResourcesByRoleName(Set<String> userAuthoritys){
        List<MenuDto> menuDtoList = new ArrayList<>();
        try{
            List<ResourceDto> resourceList = new ArrayList<>();
            String querySql = "SELECT re.id as id,re.type as type,re.value as value,re.parent as parent " +
                    "FROM t_role_resource rr " +
                    "JOIN t_role ro ON ro.id = rr.role_id  " +
                    "JOIN t_resource re ON re.id =rr.resource_id " +
                    "WHERE ro.name = ? " +
                    "AND re.common != 0";
            for (String authority:userAuthoritys) {
                Object params[] = {authority};
                resourceList = (List<ResourceDto>) queryRunner.query(querySql,new BeanListHandler(ResourceDto.class),params);
            }
            menuDtoList = getMenuTree(resourceList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuDtoList;
    }



    /** 生成菜单树 */
    private List<MenuDto> getMenuTree(List<ResourceDto> resources){
        List<MenuDto> menuDtoList = new ArrayList<>();
        Map<Integer,MenuDto> menuMap = new HashMap<>();
        for (ResourceDto res : resources) {
            MenuDto menu = new MenuDto(res.getType(),res.getValue(),new ArrayList<MenuDto>());
            menuMap.put(res.getId(),menu);
        }
        for (ResourceDto res : resources) {
            if(res.getParent()!=null){
                List<MenuDto> subMenu = menuMap.get(res.getParent()).getSubMenu();
                MenuDto menu = menuMap.get(res.getId());
                subMenu.add(menu);
                menuMap.get(res.getParent()).setSubMenu(subMenu);
            }else{
                menuDtoList.add(menuMap.get(res.getId()));
            }
        }
        return menuDtoList;
    }

}
