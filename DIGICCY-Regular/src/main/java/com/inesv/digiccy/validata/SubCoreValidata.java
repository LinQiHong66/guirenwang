package com.inesv.digiccy.validata;

import com.inesv.digiccy.api.command.SubCoreCommand;
import com.inesv.digiccy.api.command.SubRecordCommand;
import com.inesv.digiccy.api.command.ThawCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.SubCoreDto;
import com.inesv.digiccy.dto.SubRecordDto;
import com.inesv.digiccy.dto.UserBalanceDto;
import com.inesv.digiccy.dto.UserInfoDto;
import com.inesv.digiccy.query.QuerySubCore;
import com.inesv.digiccy.query.coin.QueryCoin;

import org.apache.commons.collections.map.HashedMap;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

/**
 * Created by Administrator on 2016/11/9 0009.
 */
@Component
public class SubCoreValidata {

    private static Logger logger = LoggerFactory.getLogger(SubCoreValidata.class);

    @Autowired
    private QuerySubCore querySubCore;

    @Autowired
    private QueryCoin queryCoin;

    @Autowired
    private CommandGateway commandGateway;

    /**
     * Create by huguokai date:2016年11月9日09:52:36
     * 校验认购中心记录数据
     * @return map
     */
    public Map<String , Object> validataRecordInfo(){
        Map<String , Object> map = new HashMap<>();
        List<SubCoreDto> list = querySubCore.getSubRecordInfo();
        if(list == null || list.size() == 0){
            map.put("code", ResponseCode.FAIL_SUB_CORE);
            map.put("desc",ResponseCode.FAIL_SUB_CORE_DESC);
        }else{
            map.put("data",list);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);
        }
        return map;
    }

    public Map<String , Object> validataAllRecordInfo(){
        Map<String , Object> map = new HashMap<>();
        List<SubRecordDto> list = querySubCore.getAllSubRecordInfo();
        if(list == null || list.size() == 0){
            map.put("code", ResponseCode.FAIL);
            map.put("desc",ResponseCode.FAIL_DESC);
        }else{
            map.put("data",list);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);
        }
        return map;
    }

    public Map<String , Object> validataRecordInfoById(int id){
        Map<String , Object> map = new HashMap<>();
        SubCoreDto subCoreDto = querySubCore.getSubCoreById(id);
        if(subCoreDto == null){
            map.put("code", ResponseCode.FAIL);
            map.put("desc",ResponseCode.FAIL_DESC);
        }else{
            map.put("data",subCoreDto);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);
        }
        return map;
    }

    /**
     * create by huguokai date:2016年11月9日09:56:07
     * 根据货币种类校验数据
     * @param coinType
     * @return map
     */
    public Map<String , Object> validataRecordInfoByCoinType(String coinType){
        Map<String , Object> map = new HashMap<>();
        List<SubCoreDto> list = querySubCore.getSubRecordInfoByCoinType(Integer.parseInt(coinType));
        if(list == null || list.size() == 0){
            map.put("code",ResponseCode.FAIL_SUB_CORE_BYCOIN);
            map.put("desc",ResponseCode.FAIL_SUB_CORE_BYCOIN_DESC);
        }else{
            map.put("data",list);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);
        }
        return map;
    }

    /**
     * create By huguokai date:2016年11月9日10:06:05
     * 校验用户资产数据
     * @param userNo
     * @return map
     */
    public Map<String , Object> validataUserBalance(Integer userNo,Integer coinType){
        Map<String , Object> map = new HashMap<>();
        UserBalanceDto ubd = querySubCore.getUserBalance(userNo,coinType);
        if(ubd == null){
            map.put("code",ResponseCode.FAIL_USER_BALANCE);
            map.put("desc",ResponseCode.FAIL_USER_BALANCE_DESC);
        }else{
            map.put("data",ubd.getEnable_coin());
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);
        }
        return map;
    }

    /**
     * create by huguokai date:2016年11月9日10:46:11
     * 校验认购中心数据
     * @param userNo
     * @param buyNum
     * @param dealPwd
     * @param coinType
     * @return map
     */
    public Map<String , Object> validateBuyCoinByUserNo(Integer userNo, Integer buyNum, String dealPwd, Integer coinType){
        Map<String , Object> map = new HashMap<>();
        if(buyNum <= 0){
            map.put("code",ResponseCode.FAIL_BUY_COIN_NUM);
            map.put("desc",ResponseCode.FAIL_BUY_COIN_NUM_DESC);
        }else{
            UserInfoDto uid = querySubCore.getUserInfo(userNo);
            if(uid == null){
                map.put("code",ResponseCode.FAIL_USER_INFO);
                map.put("desc",ResponseCode.FAIL_USER_INFO_DESC);
            }else{
                if(dealPwd.equals(uid.getDeal_pwd())){
                    UserBalanceDto ubd =  querySubCore.getUserBalance(userNo,coinType);
                    if(ubd == null){
                        map.put("code",ResponseCode.FAIL_USER_BALANCE);
                        map.put("desc",ResponseCode.FAIL_USER_BALANCE_DESC);
                    }else{
                        List<SubCoreDto> list = querySubCore.getSubRecordInfoByCoinType(coinType);
                        if(list == null || list.size() == 0){
                            map.put("code",ResponseCode.FAIL_SUB_CORE_BYCOIN);
                            map.put("desc",ResponseCode.FAIL_SUB_CORE_BYCOIN_DESC);
                        }else{
                            SubCoreDto scd = list.get(0);
                            if(new BigDecimal(buyNum).compareTo(scd.getLimit_buy()) == 1){
                                map.put("code",ResponseCode.FAIL_LIMIT_BUY);
                                map.put("desc",ResponseCode.FAIL_LIMIT_BUY_DESC);
                            }else{
                                UserBalanceDto userBalanceDto =  querySubCore.getUserBalance(userNo,0);
                                BigDecimal sumPrice = scd.getPrice().multiply(new BigDecimal(buyNum));
                                if(userBalanceDto.getEnable_coin().compareTo(sumPrice) == -1){
                                    map.put("code",ResponseCode.FAIL_ENABLE_COIN);
                                    map.put("desc",ResponseCode.FAIL_ENABLE_COIN_DESC);
                                }else{
                                    if(scd.getNum().compareTo(scd.getAlready().add(new BigDecimal(buyNum))) == 1 || scd.getNum().compareTo(scd.getAlready().add(new BigDecimal(buyNum))) == 0){
                                        try {
                                            SubRecordCommand subRecordCommand = null;
                                            if(scd.getThaw_num() == 0){
                                                subRecordCommand = new SubRecordCommand(userNo,coinType,scd.getSub_name(),scd.getPrice(),new BigDecimal(buyNum),sumPrice,scd.getThaw_num(),new Date(),new BigDecimal(0),2,new Date(),new BigDecimal(buyNum));
                                            }else{
                                                subRecordCommand = new SubRecordCommand(userNo,coinType,scd.getSub_name(),scd.getPrice(),new BigDecimal(buyNum),sumPrice,scd.getThaw_num(),new Date(),new BigDecimal(buyNum),0,new Date(),new BigDecimal(0));
                                            }
                                            commandGateway.sendAndWait(subRecordCommand);
                                            map.put("code",ResponseCode.SUCCESS);
                                            map.put("desc",ResponseCode.SUCCESS_DESC);
                                        }catch (Exception e){
                                            logger.error("认购失败");
                                            e.printStackTrace();
                                            map.put("code",ResponseCode.FAIL);
                                            map.put("desc",ResponseCode.FAIL_DESC);
                                        }
                                    }else{
                                        map.put("code",ResponseCode.FAIL_COIN_NO_ENOUGH);
                                        map.put("desc",ResponseCode.FAIL_COIN_NO_ENOUGH_DESC);
                                    }
                                }
                            }
                        }
                    }
                }else{
                    map.put("code",ResponseCode.FAIL_TRADE_PASSWORD);
                    map.put("desc",ResponseCode.FAIL_TRADE_PASSWORD_DESC);
                }
            }
        }
        return map;
    }

    /**
     * create by huguokai date:2016年11月16日15:35:06
     * 校验认购记录数据
     * @param userNo
     * @return map
     */
    /*public Map<String ,Object> getSubRecordInfoByUserNoAndCoinType(Integer userNo,Integer pageRecorders,Integer currentPage){
        Map<String ,Object> map = new HashMap<>();
        List<SubRecordDto> list = querySubCore.getRecordInfoByUserNo(userNo);
        if(list == null || list.size() == 0){
            map.put("code",ResponseCode.FAIL_SUB_RECORD);
            map.put("desc",ResponseCode.FAIL_SUB_RECORD_DESC);
        }else{
        	PageModel pm = new PageModel(list, pageRecorders);
            List sublist = pm.getObjects(currentPage);
            map.put("data",sublist);
            map.put("total", pm.getTotalRows());
            map.put("next", pm.getNextPage());
            map.put("pre", pm.getPreviousPage());
            map.put("current", pm.getPage());
            map.put("size", pm.getPageRecorders());
            map.put("pm", pm);
            map.put("code",ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);
        }
        return map;
    }*/
    public Map<String ,Object> getSubRecordInfoByUserNoAndCoinType(Integer userNo){
        Map<String ,Object> map = new HashMap<>();
        List<SubRecordDto> list = querySubCore.getRecordInfoByUserNo(userNo);
        if(list == null || list.size() == 0){
            map.put("code",ResponseCode.FAIL_SUB_RECORD);
            map.put("desc",ResponseCode.FAIL_SUB_RECORD_DESC);
        }else{
            map.put("data",list);
            map.put("code",ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);
        }
        return map;
    }
    

    /**
     * create by huguokai date:2016年11月18日10:38:14
     * 校验解冻数据
     * @param userNo
     * @param coinType
     * @return
     */
    public Map<String ,Object> thawCoin(Integer userNo,Integer coinType,Integer id){
        Map<String ,Object> map = new HashMap<>();
        List<SubCoreDto> list = querySubCore.getSubRecordInfoByCoinType(coinType);
        if(list == null || list.size() == 0){
            map.put("code",ResponseCode.FAIL_SUB_CORE_BYCOIN);
            map.put("desc",ResponseCode.FAIL_SUB_CORE_BYCOIN_DESC);
        }else{
            List<SubRecordDto> recordDtoList = querySubCore.getSubRecordInfoById(id);
            if(recordDtoList == null || list.size() == 0){
                map.put("code",ResponseCode.FAIL_SUB_RECORD);
                map.put("desc",ResponseCode.FAIL_SUB_RECORD_DESC);
            }else{
                SubRecordDto subRecordDto = recordDtoList.get(0);
                SubCoreDto subCoreDto = list.get(0);
                if(subRecordDto.getThaw_num() <= subCoreDto.getThaw_num()){
                    if(subRecordDto.getThaw_num() == 0 ){
                        map.put("code",ResponseCode.FAIL_THAW_COIN);
                        map.put("desc",ResponseCode.FAIL_THAW_COIN_DESC);
                    }else{
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(subRecordDto.getThaw_time());
                        calendar.add(Calendar.DAY_OF_MONTH, subCoreDto.getCycle());//今天的时间加解冻周期
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(new Date());
                        if(cal.compareTo(calendar) == 1){
                            try{
                                ThawCommand thawCommand = null;
                                MathContext mc = new MathContext(2, RoundingMode.HALF_DOWN);
                                if(subRecordDto.getThaw_num() == 1){
                                    thawCommand = new ThawCommand(id,userNo,coinType,subCoreDto.getSub_name(),subCoreDto.getPrice(),subRecordDto.getSub_num(),subRecordDto.getSum_price(),0,new Date(),new BigDecimal(0),2,new Date(),subRecordDto.getSur_frozen());
                                }else{
                                    //剩余冻结数量除以解冻次数得到解冻数量
                                    BigDecimal thawSum = subRecordDto.getSur_frozen().divide(new BigDecimal(subRecordDto.getThaw_num()),mc);
                                    thawCommand = new ThawCommand(id,userNo,coinType,subCoreDto.getSub_name(),subCoreDto.getPrice(),subRecordDto.getSub_num(),subRecordDto.getSum_price(),
                                            subRecordDto.getThaw_num()-1,new Date(),subRecordDto.getSur_frozen().subtract(thawSum),1,new Date(),thawSum);
                                }
                                commandGateway.sendAndWait(thawCommand);
                                map.put("code",ResponseCode.SUCCESS);
                                map.put("desc",ResponseCode.SUCCESS_DESC);
                            }catch (Exception e){
                                e.printStackTrace();
                                logger.error("解冻用户货币失败");
                            }
                        }else{
                            map.put("code",ResponseCode.FAIL_THAW_DATE);
                            map.put("desc",ResponseCode.FAIL_THAW_DATE_DESC);
                        }
                    }
                }else{
                    map.put("code",ResponseCode.FAIL_THAW_INFO);
                    map.put("desc",ResponseCode.FAIL_THAW_INFO_DESC);
                }
            }
        }
        return map;
    }


    public Map<String,Object> addSubCore(String sub_name,int coin,BigDecimal price, BigDecimal num,
                                          BigDecimal limit_buy, Integer thaw_num, Integer cycle,MultipartFile myfiles){
    	System.out.println(3);
        Map<String, Object> map = new HashMap<String, Object>();
        ModelMap modelMap = new ModelMap();
        //初始默认返回
        modelMap.put("result", 0);
        boolean flag = false;
        String message = "";
        // 新文件名
        String fileName[] = new String[2];
        // 原文件名
        String oldFileName = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");//日期格式
        InputStream file = null;
        int i = 0;
    	System.out.println(4);
        if(!myfiles.isEmpty()) {
            if (!myfiles.isEmpty()) {
                //如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
                oldFileName = myfiles.getOriginalFilename();
                // 拼接文件名
                fileName[i] = sdf.format(new Date()) + oldFileName.substring(oldFileName.lastIndexOf("."), oldFileName.length());
                try {
                    file = myfiles.getInputStream();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (file != null) {
                //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
                try {

                	System.out.println(5);
                    //存放在本地
                    String imgcacheurl = "/picture";
                    copyInputStreamToFile(file, new File(imgcacheurl, fileName[i]));

                	System.out.println(6);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    	System.out.println(7);
        Map<String,Object> result = new HashedMap();
        List<SubCoreDto> list = queryCoin.queryCoinByCoinType(coin);
        //这里获取到的集合不是空而是空的集合，所以这里还要判断他是否为空的集合 --刘科领注
        if(list != null && !list.isEmpty()){
            result.put("code",ResponseCode.FAIL_COIN_TYPE_ISNOT);
            result.put("desc",ResponseCode.FAIL_COIN_TYPE_ISNOT_DESC);
        }else{
            try {
            	System.out.println(8);
                SubCoreCommand command = new SubCoreCommand(0,sub_name,coin,price,num,new BigDecimal(0),limit_buy,new Date(),thaw_num,cycle,0,"insert");
                command.setPhoto(fileName[0].toString());
                commandGateway.send(command);

            	System.out.println(9);
                result.put("code",ResponseCode.SUCCESS);
                result.put("desc",ResponseCode.SUCCESS_DESC);
            }catch (Exception e){
                e.printStackTrace();
                result.put("code",ResponseCode.FAIL);
                result.put("desc",ResponseCode.FAIL_DESC);
            }
        }
        return result;
    }

    public Map<String,Object> updateSubCore(int id,String sub_name,int coin_type,BigDecimal price, BigDecimal num,
                                         BigDecimal limit_buy, Integer thaw_num, Integer cycle,Integer status){
        Map<String,Object> result = new HashedMap();
        try {
            SubCoreCommand command = new SubCoreCommand(id,sub_name,coin_type,price,num,new BigDecimal(0),limit_buy,new Date(),thaw_num,cycle,status,"update");
            commandGateway.send(command);
            result.put("code",ResponseCode.SUCCESS);
            result.put("desc",ResponseCode.SUCCESS_DESC);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",ResponseCode.FAIL);
            result.put("desc",ResponseCode.FAIL_DESC);
        }
        return result;
    }

    public Map<String,Object> deleteSubCode(int id){
        Map<String,Object> result = new HashedMap();
        BigDecimal bigDecimal = new BigDecimal(0);
        try {
            SubCoreCommand command = new SubCoreCommand(id,"",0,bigDecimal,bigDecimal,bigDecimal,bigDecimal,new Date(),0,0,0,"delete");
            commandGateway.send(command);
            result.put("code",ResponseCode.SUCCESS);
            result.put("desc",ResponseCode.SUCCESS_DESC);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",ResponseCode.FAIL);
            result.put("desc",ResponseCode.FAIL_DESC);
        }
        return result;
    }

    public Map<String ,Object> getAllSubRecordInfo(){
        Map<String ,Object> map = new HashMap<>();
        List<SubRecordDto> list = querySubCore.getAllSubRecordInfo();
        if(list == null ){
            map.put("code",ResponseCode.FAIL);
            map.put("desc",ResponseCode.FAIL_DESC);
        }else{
            map.put("data",list);
            map.put("code",ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);
        }
        return map;
    }

    public Map<String ,Object> getAllSubRecord(){
        Map<String ,Object> map = new HashMap<>();
        List<SubRecordDto> list = querySubCore.getRecordInfo();
        if(list == null ){
            map.put("code",ResponseCode.FAIL);
            map.put("desc",ResponseCode.FAIL_DESC);
        }else{
            map.put("data",list);
            map.put("code",ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);
        }
        return map;
    }

    public Map<String ,Object> getAllSubInfo(){
        Map<String ,Object> map = new HashMap<>();
        List<SubCoreDto> list = querySubCore.getSubCoreInfo();
        if(list == null ){
            map.put("code",ResponseCode.FAIL);
            map.put("desc",ResponseCode.FAIL_DESC);
        }else{
            map.put("data",list);
            map.put("code",ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);
        }
        return map;
    }


}
