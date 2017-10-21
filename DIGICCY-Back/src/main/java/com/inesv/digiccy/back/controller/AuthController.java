package com.inesv.digiccy.back.controller;

import com.inesv.digiccy.api.command.AuthCommand;
import com.inesv.digiccy.api.command.ResourceCommand;
import com.inesv.digiccy.api.command.RoleCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.auth.AuthRoleDto;
import com.inesv.digiccy.dto.auth.ResourceDto;
import com.inesv.digiccy.dto.auth.TreeItemDto;
import com.inesv.digiccy.dto.auth.UserDto;
import com.inesv.digiccy.query.auth.QueryResources;
import com.inesv.digiccy.query.auth.QueryRoles;
import com.inesv.digiccy.validata.RmbRechargeValidate;
import com.inesv.digiccy.validata.TradeValidata;
import com.inesv.digiccy.validata.user.InesvUserValidata;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JimJim on 2016/11/7 0007.
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    QueryRoles queryRoles;

    @Autowired
    QueryResources queryResources;

    @Autowired
    CommandGateway commandGateway;
    
    @Autowired
    InesvUserValidata inesvUserValidata;
    
    @Autowired
    RmbRechargeValidate rmbRechargeValidate;
    
    @Autowired
    TradeValidata tradeValidata;

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public ModelAndView login(){
    	Map<String,Object> resultMap = inesvUserValidata.homeCount();
        return new ModelAndView("index",resultMap);
    }
    
	@RequestMapping(value = "getUserPicture", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getUserPicture() throws Exception {
		Map<String, Object> map = new HashMap<String,Object>();
		map = inesvUserValidata.getUserPicture();
		return map;
	}
	
	@RequestMapping(value = "getTradePicture", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getTradePicture() throws Exception {
		Map<String, Object> map = new HashMap<String,Object>();
		map = tradeValidata.getTradePicture();
		return map;
	}
	
	@RequestMapping(value = "getRmbRechargePicture", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getRmbRechargePicture() throws Exception {
		Map<String, Object> map = new HashMap<String,Object>();
		map = rmbRechargeValidate.getRechargePicture();
		return map;
	}
	
	@RequestMapping(value = "getRmbWithdrawPicture", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getRmbWithdrawPicture() throws Exception {
		Map<String, Object> map = new HashMap<String,Object>();
		map = rmbRechargeValidate.getWithdrawPicture();
		return map;
	}

    @RequestMapping(value = "gotoRole",method = RequestMethod.GET)
    public String gotoRole(){
        return "/auth/role";
    }

    @RequestMapping(value = "gotoRes",method = RequestMethod.GET)
    public String gotoRes(){
        return "/auth/resource";
    }

    @RequestMapping(value = "gotoUser",method = RequestMethod.GET)
    public String gotoUser(){
        return "/auth/authUser";
    }

    /**
     * 获取所有角色信息
     */
    @RequestMapping(value = "getRoles",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getRoles(){
        Map<String,Object> allRoles = new HashMap<>();
        List<AuthRoleDto> authRoleDtos = queryRoles.queryRole();
        if (authRoleDtos.size() != 0){
            allRoles.put("total",authRoleDtos.size());
            allRoles.put("data",authRoleDtos);
        }
        return allRoles;
    }

    /**
     *新增角色
     */
    @RequestMapping(value = "addRole",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addRole(@RequestParam String name,@RequestParam String desc){
        Map<String,Object> result = new HashMap<>();
        try {
//            AddRoleCommand command = new AddRoleCommand(name, desc);
//            commandGateway.sendAndWait(command);
            RoleCommand command = new RoleCommand(0,name,desc,"insert");
            commandGateway.sendAndWait(command);
            result.put("code",ResponseCode.SUCCESS);
            result.put("desc",ResponseCode.SUCCESS_DESC);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",ResponseCode.FAIL);
            result.put("desc",ResponseCode.FAIL_DESC);
        }
        return result;
    }

    /**
     *修改角色
     */
    @RequestMapping(value = "updateRole",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateRole(@RequestParam Integer id,@RequestParam String name,@RequestParam String desc){
        Map<String,Object> result = new HashMap<>();
        try {
//            UpdateRoleCommand command = new UpdateRoleCommand(id,name,desc);
//            commandGateway.sendAndWait(command);
            RoleCommand command = new RoleCommand(id,name,desc,"update");
            commandGateway.sendAndWait(command);
            result.put("code",ResponseCode.SUCCESS);
            result.put("desc",ResponseCode.SUCCESS_DESC);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",ResponseCode.FAIL);
            result.put("desc",ResponseCode.FAIL_DESC);
        }
        return result;
    }

    /**
     *删除角色
     */
    @RequestMapping(value = "deleteRole",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteRole(@RequestParam Integer id){
        Map<String,Object> result = new HashMap<>();
        try {
            RoleCommand command = new RoleCommand(id,null,null,"delete");
            commandGateway.sendAndWait(command);
            result.put("code",ResponseCode.SUCCESS);
            result.put("desc",ResponseCode.SUCCESS_DESC);
        }catch (Exception e){
            result.put("code",ResponseCode.FAIL);
            result.put("desc",ResponseCode.FAIL_DESC);
        }
        return result;
    }

    /**
     * 获取所有权限
     */
    @RequestMapping(value = "getRes",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getRes(){
        Map<String,Object> allRes = new HashMap<>();
        List<ResourceDto> resourceDtos = queryResources.queryResources();
        if (resourceDtos.size() != 0){
            allRes.put("total",resourceDtos.size());
            allRes.put("data",resourceDtos);
        }
        return allRes;
    }

    /**
     * 获取子权限
     */
    @RequestMapping(value = "getSubRes",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getRes(@RequestParam Integer id){
        Map<String,Object> allRes = new HashMap<>();
        List<ResourceDto> resourceDtos = queryResources.querySubResources(id);
        if (resourceDtos.size() != 0){
            allRes.put("total",resourceDtos.size());
            allRes.put("data",resourceDtos);
        }
        return allRes;
    }

    /**
     * 新增权限
     */
    @RequestMapping(value = "addRes",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addRes(@RequestParam String type,@RequestParam String value,
                                     @RequestParam Integer parent,@RequestParam Integer common){
        Map<String,Object> result = new HashMap<>();
        try {
            ResourceCommand command = new ResourceCommand(0,type,value,parent,"insert",common);
            commandGateway.sendAndWait(command);
            result.put("code",ResponseCode.SUCCESS);
            result.put("desc",ResponseCode.SUCCESS_DESC);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",ResponseCode.FAIL);
            result.put("desc",ResponseCode.FAIL_DESC);
        }
        return result;
    }

    /**
     *修改角色
     */
    @RequestMapping(value = "updateRes",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateRes(@RequestParam Integer id,@RequestParam String type,@RequestParam String value,
                                        @RequestParam Integer parent,@RequestParam Integer common){
        Map<String,Object> result = new HashMap<>();
        try {
            ResourceCommand command = new ResourceCommand(id,type,value,parent,"update",common);
            commandGateway.sendAndWait(command);
            result.put("code",ResponseCode.SUCCESS);
            result.put("desc",ResponseCode.SUCCESS_DESC);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",ResponseCode.FAIL);
            result.put("desc",ResponseCode.FAIL_DESC);
        }
        return result;
    }

    /**
     *删除角色
     */
    @RequestMapping(value = "deleteRes",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteRes(@RequestParam Integer id){
        Map<String,Object> result = new HashMap<>();
        try {
            ResourceCommand command = new ResourceCommand(id,null,null,null,"delete",0);
            commandGateway.sendAndWait(command);
            result.put("code",ResponseCode.SUCCESS);
            result.put("desc",ResponseCode.SUCCESS_DESC);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",ResponseCode.FAIL);
            result.put("desc",ResponseCode.FAIL_DESC);
        }
        return result;
    }

    /**
     * 生成树形菜单
     * @param id
     * @return
     */
    @RequestMapping(value = "getTreeItem" , method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getTreeItem(@RequestParam Integer roleId){
        Map<String,Object> map = new HashMap<>();
        List<TreeItemDto> treeItem = queryResources.queryRoleResource(roleId);
        if (treeItem.size() != 0){
            map.put("data",treeItem);
        }
        return map;
    }

    /**
     * 修改角色权限
     * @param id
     * @return
     */
    @RequestMapping(value = "updateRoleRes" , method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateRoleRes(@RequestParam Integer roleId,@RequestParam String resources){
        Map<String,Object> result = new HashMap<>();
        try {
            AuthCommand command = new AuthCommand(0,0,"","",roleId,resources,"roleRes");
            commandGateway.sendAndWait(command);
            result.put("code",ResponseCode.SUCCESS);
            result.put("desc",ResponseCode.SUCCESS_DESC);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",ResponseCode.FAIL);
            result.put("desc",ResponseCode.FAIL_DESC);
        }
        return result;
    }

    /**
     * 查询后台角色
     * @param id
     * @return
     */
    @RequestMapping(value = "getUser" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getUser(){
        Map<String,Object> allUser = new HashMap<>();
        List<UserDto> userDtos = queryRoles.queryAuthUser();
        if (userDtos.size() != 0){
            allUser.put("total",userDtos.size());
            allUser.put("data",userDtos);
        }
        return allUser;
    }


    /**
     * 新增用户
     * @param id
     * @return
     */
    @RequestMapping(value = "addUser" , method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addUser(@RequestParam String name,@RequestParam String pass,@RequestParam Integer roleId){
        Map<String,Object> result = new HashMap<>();
        try {
            AuthCommand command = new AuthCommand(0,0,name,pass,roleId,"","addUser");
            commandGateway.sendAndWait(command);
            result.put("code",ResponseCode.SUCCESS);
            result.put("desc",ResponseCode.SUCCESS_DESC);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",ResponseCode.FAIL);
            result.put("desc",ResponseCode.FAIL_DESC);
        }
        return result;
    }

    /**
     * 修改用户
     * @param id
     * @return
     */
    @RequestMapping(value = "updateUser" , method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateUser(@RequestParam Integer userId,@RequestParam String name,
                                      @RequestParam String pass,@RequestParam Integer roleId) {
        Map<String,Object> result = new HashMap<>();
        try {
            AuthCommand command = new AuthCommand(0,userId,name,pass,roleId,"","updateUser");
            commandGateway.sendAndWait(command);
            result.put("code",ResponseCode.SUCCESS);
            result.put("desc",ResponseCode.SUCCESS_DESC);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",ResponseCode.FAIL);
            result.put("desc",ResponseCode.FAIL_DESC);
        }
        return result;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteUser" , method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteUser(@RequestParam Integer userId){
        Map<String,Object> result = new HashMap<>();
        try {
            AuthCommand command = new AuthCommand(0,userId,"","",0,"","deleteUser");
            commandGateway.sendAndWait(command);
            result.put("code",ResponseCode.SUCCESS);
            result.put("desc",ResponseCode.SUCCESS_DESC);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",ResponseCode.FAIL);
            result.put("desc",ResponseCode.FAIL_DESC);
        }
        return result;
    }


}
