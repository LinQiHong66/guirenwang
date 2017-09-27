package com.inesv.digiccy.back.controller;

import com.inesv.digiccy.dto.auth.MenuDto;
import com.inesv.digiccy.query.auth.QueryUserResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 左侧菜单查询
 * Created by JimJim on 2016/11/4 0004.
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    QueryUserResources queryUserResources;

    @RequestMapping(value = "getMenu" , method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getMenu(){
        Map<String,Object> menu = new HashMap<>();
        //获取登录用户的信息
        Object principal =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<String> userAuthoritys = new HashSet();
        //获取登录用户的角色
        if(principal instanceof UserDetails){
            Iterator it = ((UserDetails)principal).getAuthorities().iterator();
            while(it.hasNext()){
                String authority = ((GrantedAuthority)it.next()).getAuthority();
                System.out.println("--------角色："+authority);
                userAuthoritys.add(authority);
            }
        }
        //根据角色查询资源
        if (userAuthoritys.size() != 0){
            List<MenuDto> menuList = queryUserResources.queryResourcesByRoleName(userAuthoritys);
            menu.put("menu",menuList);
        }
        return menu;
    }

}
