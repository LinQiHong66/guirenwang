package com.inesv.digiccy.back.controller;

import com.inesv.digiccy.validata.SubCoreValidata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by JimJim on 2016/12/1 0001.
 */
@Controller
@RequestMapping("/subCore")
public class SubCoreController {

    private static Logger logger = LoggerFactory.getLogger(SubCoreController.class);

    @Autowired
    SubCoreValidata subCoreValidata;

    @RequestMapping(value = "/gotoSubCore" , method = RequestMethod.GET)
    public String gotoSubCore(){
        return "/subCore/subCore";
    }

    @RequestMapping(value = "/gotoSubRecord" , method = RequestMethod.GET)
    public String gotoSubRecord(){
        return "/subCore/subRecord";
    }

    @RequestMapping(value = "/gotoAdd" , method = RequestMethod.GET)
    public String gotoAdd(){
        return "/subCore/add";
    }

    @RequestMapping(value = "/gotoUpdate" , method = RequestMethod.GET)
    public String gotoUpdate(){
        return "/subCore/update";
    }

    @RequestMapping(value = "/getSubCore" , method = RequestMethod.GET)
    public @ResponseBody Map<String , Object> getAllSubCoreInfo(){
        Map<String , Object> map = subCoreValidata.getAllSubInfo();
        return map;
    }

    @RequestMapping(value = "/getSubCoreById" , method = RequestMethod.GET)
    public @ResponseBody Map<String , Object> getSubCoreById(@RequestParam  int id){
        Map<String , Object> map = subCoreValidata.validataRecordInfoById(id);
        return map;
    }

    @RequestMapping(value = "/getSubRecord" , method = RequestMethod.GET)
    public @ResponseBody Map<String , Object> getSubRecord(){
        Map<String , Object> map = subCoreValidata.getAllSubRecord();
        return map;
    }

    @RequestMapping(value = "/addSubCore" , method = RequestMethod.POST)
    public ModelAndView addSubCore(@RequestParam String name,@RequestParam Integer coin,@RequestParam BigDecimal price,@RequestParam BigDecimal num,
                                                         @RequestParam BigDecimal limit,@RequestParam Integer thaw,@RequestParam Integer cycle,@RequestParam MultipartFile myfiles){
        Map<String , Object> map = subCoreValidata.addSubCore(name,coin,price,num,limit,thaw,cycle,myfiles);
        return new ModelAndView("/subCore/subCore",map);
    }

    @RequestMapping(value = "/updateSubCore" , method = RequestMethod.POST)
    public @ResponseBody Map<String , Object> updateSubCore(Integer id,String sub_name,int coin_type, BigDecimal price, BigDecimal num,
                                                         BigDecimal limit_buy, Integer thaw_num, Integer cycle,Integer status){
        Map<String , Object> map = subCoreValidata.updateSubCore(id,sub_name,coin_type,price,num,limit_buy,thaw_num,cycle,status);
        return map;
    }

    @RequestMapping(value = "/deleteSubCore" , method = RequestMethod.POST)
    public @ResponseBody Map<String , Object> deleteSubCore(Integer id){
        Map<String , Object> map = subCoreValidata.deleteSubCode(id);
        return map;
    }

}
