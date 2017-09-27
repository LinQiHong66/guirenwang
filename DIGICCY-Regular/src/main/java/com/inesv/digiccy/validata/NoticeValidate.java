package com.inesv.digiccy.validata;

import com.inesv.digiccy.api.command.NoticeCommand;
import com.inesv.digiccy.api.command.NoticeTypeCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.NoticeDto;
import com.inesv.digiccy.dto.NoticeTypeDto;
import com.inesv.digiccy.query.QueryNoticeInfo;
import org.apache.commons.collections.map.HashedMap;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JimJim on 2016/12/13 0013.
 */
@Component
public class NoticeValidate {

    @Autowired
    CommandGateway commandGateway;

    @Autowired
    QueryNoticeInfo queryNoticeInfo;

    /**
     * 校验查询公告
     * @return
     */
    public Map<String,Object> queryNoticeInfo(){
        Map<String,Object> map = new HashedMap();
        List<NoticeDto> notices = queryNoticeInfo.queryNoticeInfo();
        if(notices == null){
            map.put("code", ResponseCode.FAIL);
            map.put("desc", ResponseCode.FAIL_DESC);
        }else{
            map.put("total",notices.size());
            map.put("data",notices);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);
        }
        return map;
    }

    
   //首页公告展示
    public Map<String,Object> queryNoticeShouYe(String type){
        Map<String,Object> map = new HashedMap();
        List<NoticeDto> notices = queryNoticeInfo.queryNoticeInfoByType(type);
        if(notices != null && !notices.isEmpty()){
			for(NoticeDto dto : notices){
				String context = dto.getContext();
				String regex = "<[^>]*>";
				context = context.replaceAll(regex, "");
			    dto.setContext(context.length()>70?context.substring(0, 70):context);
			    System.out.println(context);
			}
		}
        System.out.println(notices.size());
        if(notices.size()>6){
        	notices=notices.subList(0,5);
        }
        if(notices == null){
            map.put("code", ResponseCode.FAIL);
            map.put("desc", ResponseCode.FAIL_DESC);
        }else{
            map.put("data",notices);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);
        }
        return map;
    }
    //根据type查询相关内容
    public Map<String,Object> queryNoticeInfoByType(String type){
        Map<String,Object> map = new HashedMap();
        List<NoticeDto> notices = queryNoticeInfo.queryNoticeInfoByType(type);
        if(notices == null){
            map.put("code", ResponseCode.FAIL);
            map.put("desc", ResponseCode.FAIL_DESC);
        }else{
            map.put("data",notices);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);
        }
        return map;
    }


    public Map<String,Object> queryNoticeOneInfo(){
        Map<String,Object> map = new HashedMap();
        NoticeDto notice = queryNoticeInfo.queryNoticeOne("1"); //1为公告
        if(notice == null){
            map.put("code", ResponseCode.FAIL);
            map.put("desc", ResponseCode.FAIL_DESC);
        }else{
            map.put("data",notice);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);
        }
        return map;
    }
    /**根据id获取公告*/
    public Map<String,Object> queryNoticeInfoById(Integer id){
        Map<String,Object> map = new HashedMap();
        NoticeDto notices = queryNoticeInfo.queryNoticeInfoById(id);
        if(notices == null){
            map.put("code", ResponseCode.FAIL);
            map.put("desc", ResponseCode.FAIL_DESC);
        }else{
            map.put("data",notices);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);
        }
        return map;
    }

    /**
     * 校验添加公告
     * @return
     */
    public Map<String,Object> addNotice(String title, String type ,String context, String noticeNameType){
        Map<String,Object> result = new HashedMap();
        try {
            NoticeCommand command = new NoticeCommand(0L,Integer.valueOf(type),"","管理员",new Date(),title,context,noticeNameType,"insert");
            commandGateway.send(command);
            result.put("code", ResponseCode.SUCCESS);
            result.put("desc",ResponseCode.SUCCESS_DESC);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",ResponseCode.FAIL);
            result.put("desc",ResponseCode.FAIL_DESC);
        }
        return result;
    }

    /**
     * 校验修改公告
     * @return
     */
    public Map<String,Object> updateNotice(String id,String title, String type ,String context, String noticeNameType){
        Map<String,Object> result = new HashedMap();
        try {
            NoticeCommand command = new NoticeCommand(Long.valueOf(id),Integer.valueOf(type),"","管理员",new Date(),title,context,noticeNameType,"update");
            commandGateway.send(command);
            result.put("code", ResponseCode.SUCCESS);
            result.put("desc",ResponseCode.SUCCESS_DESC);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",ResponseCode.FAIL);
            result.put("desc",ResponseCode.FAIL_DESC);
        }
        return result;
    }

    /**
     * 校验删除公告
     * @return
     */
    public Map<String,Object> deleteNotice(String id){
        Map<String,Object> result = new HashedMap();
        try {
            NoticeCommand command = new NoticeCommand(Long.valueOf(id),1,"","管理员",new Date(),"","","","delete");
            commandGateway.send(command);
            result.put("code", ResponseCode.SUCCESS);
            result.put("desc",ResponseCode.SUCCESS_DESC);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",ResponseCode.FAIL);
            result.put("desc",ResponseCode.FAIL_DESC);
        }
        return result;
    }
    /**
     * 查询所有自定义类型
     */
    public Map<String, Object> getNameType(int type){
    	Map<String, Object> map = new HashMap<String, Object>();
    	ArrayList<NoticeTypeDto> list = (ArrayList<NoticeTypeDto>) queryNoticeInfo.getNameType(type);
    	if(list != null){
    		map.put("data", list);
    		map.put("code", ResponseCode.SUCCESS);
    		map.put("desc",ResponseCode.SUCCESS_DESC);
    	}else{
    		map.put("data", "none");
    		map.put("code",ResponseCode.FAIL);
    		map.put("desc",ResponseCode.FAIL_DESC);
    	}
    	return map;
    }
    /**
     * 根据id查询类型
     */
    public Map<String, Object> findTypeById(int id){
    	NoticeTypeDto dto = queryNoticeInfo.findTypeById(id);
    	Map<String, Object> map = new HashMap<String, Object>();
    	if(dto != null){
    		map.put("data", dto);
    		map.put("code", ResponseCode.SUCCESS);
    		map.put("desc",ResponseCode.SUCCESS_DESC);
    	}else{
    		map.put("data", "none");
    		map.put("code",ResponseCode.FAIL);
    		map.put("desc",ResponseCode.FAIL_DESC);
    	}
    	return map;
    }
    /**
     * 新增自定义类型
     */
    public Map<String, Object> addNameType(String name){
    	NoticeTypeCommand command = new NoticeTypeCommand(0, name, "insert");
    	Map<String, Object> map = new HashMap<String, Object>();
    	try{
    		commandGateway.sendAndWait(command);
    		map.put("code", ResponseCode.SUCCESS);
    		map.put("desc",ResponseCode.SUCCESS_DESC);
    	}catch(Exception e){
    		map.put("code",ResponseCode.FAIL);
    		map.put("desc",ResponseCode.FAIL_DESC);
    	}
    	return map;
    }
    public Map<String, Object> modifyNameType(int id, String name){
    	NoticeTypeCommand command = new NoticeTypeCommand(id, name, "update");
    	Map<String, Object> map = new HashMap<String, Object>();
    	try{
    		commandGateway.sendAndWait(command);
    		map.put("code", ResponseCode.SUCCESS);
    		map.put("desc",ResponseCode.SUCCESS_DESC);
    	}catch(Exception e){
    		map.put("code",ResponseCode.FAIL);
    		map.put("desc",ResponseCode.FAIL_DESC);
    	}
    	return map;
    }
    //删除类型
    public Map<String, Object> delNameType(int id){
    	NoticeTypeCommand command = new NoticeTypeCommand(id, "", "delete");
    	Map<String,Object> map = new HashMap<String, Object>();
    	try{
    		commandGateway.sendAndWait(command);
    		map.put("code", ResponseCode.SUCCESS);
    		map.put("desc",ResponseCode.SUCCESS_DESC);
    	}catch(Exception e){
    		map.put("code",ResponseCode.FAIL);
    		map.put("desc",ResponseCode.FAIL_DESC);
    	}
    	return map;
    }
}
