package com.inesv.digiccy.back.controller;

import com.inesv.digiccy.dto.NoticeDto;
import com.inesv.digiccy.dto.NoticeTypeDto;
import com.inesv.digiccy.validata.NoticeValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JimJim on 2016/12/13 0013.
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    NoticeValidate noticeValidate;

    @RequestMapping(value = "gotoNotice",method = RequestMethod.GET)
    public String gotoNotice(){
        return "/notice/notice";
    }

    @RequestMapping(value = "gotoAdd",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView gotoAdd(){
    	List<NoticeTypeDto> types = noticeValidate.queryNoticeType();
    	Map<String,Object> map =new HashMap<String, Object>(); 
    	map.put("types", types);
        return new ModelAndView("/notice/add", map);
    }
    @RequestMapping(value="gotoNoticeType", method=RequestMethod.GET)
    public String gotoNoticeType(){
    	return "/notice/nametype";
    }
    
    @RequestMapping(value = "gotoEdit",method = RequestMethod.GET)
    public ModelAndView gotoEdit(String noticeId){
        Map<String,Object> map = noticeValidate.queryNoticeInfoById(Integer.valueOf(noticeId));
        return new ModelAndView("/notice/edit",map);
    }

    @RequestMapping(value = "getNotice",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getNotice(){
        Map<String,Object> map = noticeValidate.queryNoticeInfo();
        return map;
    }


    @RequestMapping(value = "addNotice",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addNotice(String title,String type,String context, String noticeNameType){
        Map<String,Object> map = noticeValidate.addNotice(title,type,context,noticeNameType);
        return map;
    }

    @RequestMapping(value = "updateNotice",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateNotice(String id,String title,String type,String context, String noticeNameType){
    	System.out.println("++++++++++++"+noticeNameType);
        Map<String,Object> map = noticeValidate.updateNotice(id,title,type,context, noticeNameType);
        return map;
    }

    @RequestMapping(value = "deleteNotice",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteNotice(String id){
        Map<String,Object> map = noticeValidate.deleteNotice(id);
        return map;
    }
    @RequestMapping(value="findTypeById", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> findTypeById(int id){
    	return noticeValidate.findTypeById(id);
    }
    @RequestMapping(value="getNameType", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getNameType(){
    	return noticeValidate.getNameType(0);
    }
    @RequestMapping(value="addNameType", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addNameType(String name){
    	return noticeValidate.addNameType(name);
    }
    @RequestMapping(value="modifyNameType", method=RequestMethod.POST)
    @ResponseBody
    public ModelAndView modifyNameType(int id, String name){
    	return new ModelAndView("/notice/nametype",noticeValidate.modifyNameType(id, name));
    }
    @RequestMapping(value="deleteNameType", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delNameType(int id){
    	return noticeValidate.delNameType(id);
    }
}
