package com.inesv.digiccy.controller;

import com.inesv.digiccy.validata.AddressValidata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by JimJim on 2016/12/16 0016.
 */
@Controller
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressValidata addressValidata;

    /*@AutoDocMethod(author = DeveloperType.DEFAULT, createTime = "2016-12-19",cver = VersionType.V100, name = "查询联系人地址", description = "测试根据用户编号查询联系人地址接口", model = ModelType.TEST, dtoClazz = BaseRes.class, reqParams = {
		"userNo"}, progress = ProgressType.TESTING,requestMode=RequestModeType.POST)
    @AutoDocMethodParam(note = "用户编号", name = "userNo")
    @RequestMapping(value = "/address/getUserAddress",method = RequestMethod.POST)*/
    @RequestMapping(value = "getUserAddress",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getUserAddress(@RequestParam String userNo){
        return addressValidata.queryAddress(userNo);
    }
    //插入或修改
    /*@AutoDocMethod(author = DeveloperType.DEFAULT, createTime = "2016-12-19",cver = VersionType.V100, name = "添加联系人地址", description = "测试添加联系人地址接口", model = ModelType.TEST, dtoClazz = BaseRes.class, reqParams = {
	"userNo","remarkAddress","name","card","phone","address"}, progress = ProgressType.TESTING,requestMode=RequestModeType.POST)
    @AutoDocMethodParam(note = "用户编号@@备注名@@联系姓名@@身份证号@@联系电话@@送货地址", name = "userNo@@remarkAddress@@name@@card@@phone@@address")
    @RequestMapping(value = "/address/insertAddress",method = RequestMethod.POST)*/
    @RequestMapping(value = "insertAddress",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> insertAddress(@RequestParam String userNo,@RequestParam String remarkAddress,@RequestParam String name,
                                            @RequestParam String card,@RequestParam String phone,@RequestParam String address){
        return addressValidata.insertAddress(userNo,remarkAddress,name,card,phone,address);
    }

    /*@AutoDocMethod(author = DeveloperType.DEFAULT, createTime = "2016-12-19",cver = VersionType.V100, name = "修改联系人地址", description = "测试修改联系人地址接口", model = ModelType.TEST, dtoClazz = BaseRes.class, 
    		reqParams = {"id","userNo","name","phone","address"}, progress = ProgressType.TESTING,requestMode=RequestModeType.POST)
        @AutoDocMethodParam(note = "联系地址id@@用户编号@@联系姓名@@联系电话@@送货地址", name = "id@@userNo@@name@@phone@@address")
    @RequestMapping(value = "/address/updateAddress",method = RequestMethod.POST)*/
    @RequestMapping(value = "updateAddress",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateAddress(@RequestParam String id,@RequestParam String userNo,@RequestParam String name,
                                            @RequestParam String phone,@RequestParam String address){
        return addressValidata.updateAddress(id,userNo,name,phone,address);
    }

    /*@AutoDocMethod(author = DeveloperType.DEFAULT, createTime = "2016-12-19",cver = VersionType.V100, name = "删除联系人地址", description = "测试删除联系人地址接口", model = ModelType.TEST, dtoClazz = BaseRes.class, reqParams = {
    	"id"}, progress = ProgressType.TESTING,requestMode=RequestModeType.POST)
        @AutoDocMethodParam(note = "联系地址id", name = "id")
    @RequestMapping(value = "/address/deleteAddress",method = RequestMethod.POST)*/
    @RequestMapping(value = "deleteAddress",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteAddress(@RequestParam String id){
        return addressValidata.deleteAddress(id);
    }

}
