package com.inesv.digiccy.controller;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.common.autocreate.annotation.AutoDocMethod;
import com.inesv.digiccy.common.autocreate.annotation.AutoDocMethodParam;
import com.inesv.digiccy.common.autocreate.bean.DeveloperType;
import com.inesv.digiccy.common.autocreate.bean.ModelType;
import com.inesv.digiccy.common.autocreate.bean.ProgressType;
import com.inesv.digiccy.common.autocreate.bean.VersionType;
import com.inesv.digiccy.common.dto.BaseRes;
import com.inesv.digiccy.dto.InesvDayMarket;
import com.inesv.digiccy.query.QueryDayMarketInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 每日行情
 * @author Administrator
 *
 */
@Controller
public class DayMarketController {
    @Autowired
    private QueryDayMarketInfo queryDayMarketInfo;

    /**
     * 获取某种货币的每日行情
     * @param request
     * @return
     */
    /*@AutoDocMethod(author = DeveloperType.DEFAULT, createTime = "2016-12-2",
    cver = VersionType.V100, name = "每
    日行情", description = "获取某种货币的每日行情详细信息接口",
    model = ModelType.DAYMARKET, dtoClazz = BaseRes.class,
    reqParams = {"coinType"},//有参才需要加的
    progress = ProgressType.FINISHED)
    @AutoDocMethodParam(note = "货币类型", name = "coinType")*/
    @RequestMapping(value ="/dayMarket/selectDayMarket",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> selectDayMarket(Integer coinType){
        Map<String,Object> map = new HashMap<String,Object>();
        List<InesvDayMarket> list = queryDayMarketInfo.queryDayMarketInfo(coinType);
        if(!list.isEmpty()){
        	map.put("code", ResponseCode.SUCCESS);
            map.put("msg",ResponseCode.SUCCESS_DESC);
            map.put("dayMarketList",list);
        }else{
        	map.put("code", ResponseCode.FAIL);
            map.put("msg",ResponseCode.FAIL_DESC);
        }
        return map;
    }
    
    /**
     * 获取首页的每日行情
     * @param request
     * @return
     */
    /*@AutoDocMethod(author = DeveloperType.DEFAULT, createTime = "2016-12-20",
    cver = VersionType.V100, name = "获取首页的每日行情", description = "获取首页的每日行情(当前日某种币种没有信息就获取该币种的最新信息)接口",
    model = ModelType.DAYMARKET, dtoClazz = BaseRes.class,
    progress = ProgressType.FINISHED)
    @AutoDocMethodParam(note = "", name = "")*/
    @RequestMapping(value ="/dayMarket/getDayMarket",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getDayMarket(){
        Map<String,Object> map = new HashMap<String,Object>();
        List<InesvDayMarket> list = queryDayMarketInfo.getDayMarketInfo();
        if(!list.isEmpty()){
        	map.put("code", ResponseCode.SUCCESS);
            map.put("msg",ResponseCode.SUCCESS_DESC);
            map.put("dayMarketList",list);
        }else{
        	map.put("code", ResponseCode.FAIL);
            map.put("msg",ResponseCode.FAIL_DESC);
        }
        return map;
    }
}
