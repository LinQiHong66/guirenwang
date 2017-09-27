package com.inesv.digiccy.common.controller;

import java.util.HashMap;
import java.util.Map;

import com.inesv.digiccy.common.dto.BaseRes;


//需登陆的接口务必继承此类（另作为生成文档使用）
public class QuickLoginedBaseController
{
    
    
    protected Object printJSONResult(BaseRes mobResponse)
    {
        return this.printJSONResult(mobResponse, null, null, null, null, null, null);
    }
    
    private Object printJSONResult(BaseRes mobResponse, Object object, Object object2, Object object3, Object object4,
            Object object5, Object object6)
    {   
        Map<String, BaseRes> map = new HashMap<String, BaseRes>();
        map.put("BaseRes", mobResponse);
        return map;
        
    }
    
    
}
