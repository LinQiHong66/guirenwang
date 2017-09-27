package com.inesv.digiccy.controller;


import com.inesv.digiccy.common.autocreate.annotation.AutoDocMethod;
import com.inesv.digiccy.common.autocreate.annotation.AutoDocMethodParam;
import com.inesv.digiccy.common.autocreate.bean.DeveloperType;
import com.inesv.digiccy.common.autocreate.bean.ModelType;
import com.inesv.digiccy.common.autocreate.bean.ProgressType;
import com.inesv.digiccy.common.autocreate.bean.VersionType;
import com.inesv.digiccy.common.dto.BaseRes;
import com.inesv.digiccy.dto.SubCoreDto;
import com.inesv.digiccy.util.MD5;
import com.inesv.digiccy.validata.SubCoreValidata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by huguokai on 2016/11/8 0008.
 * 认购中心Controller
 */
@Controller
@RequestMapping("/sub")
public class SubCoreController {

    @Autowired
    private SubCoreValidata subCoreValidata;
    /**
     * Create by huguokai date:2016年11月8日14:18:11
     * 获取认购中心各个货币种类的信息
     * @return Json
     */
    /*@AutoDocMethod(author = DeveloperType.HUGUOKAI, createTime = "2016-12-2",
            cver = VersionType.V100, name = "认购中心接口", description = "认购中心接口",
            model = ModelType.SUBCORE, dtoClazz = BaseRes.class,
            //reqParams = {"tid","uid"},//有参才需要加的
            progress = ProgressType.FINISHED)
    @AutoDocMethodParam(note = "", name = "")*/
    @RequestMapping(value = "/getSubRecord" , method = RequestMethod.POST)
    public @ResponseBody Map<String , Object> getSubRecordInfo(HttpServletResponse rsp){
        Map<String , Object> map = subCoreValidata.validataRecordInfo();
        return map;
    }

    /**
     * Create by huguokai date:2016年11月8日14:18:43
     * 根据货币种类获取货币种类信息
     * @param coinType
     * @return Json
     */
    /*@AutoDocMethod(author = DeveloperType.HUGUOKAI, createTime = "2016-12-2",
            cver = VersionType.V100, name = "根据货币种类获取货币种类信息接口", description = "根据货币种类获取货币种类信息接口",
            model = ModelType.SUBCORE, dtoClazz = BaseRes.class,
            reqParams = {"coinType"},//有参才需要加的
            progress = ProgressType.FINISHED)
    @AutoDocMethodParam(note = "货币类型", name = "coinType")*/
    @RequestMapping(value = "/getSubRecordInfoByCoinType" , method = RequestMethod.POST)
    public @ResponseBody Map<String , Object> getSubRecordInfoByCoinType(Integer coinType){
        Map<String , Object> map = subCoreValidata.validataRecordInfoByCoinType(coinType.toString());
        return map;
    }

    /**
     * Create by huguokai date:2016年11月8日16:09:59
     * 查询用户资产
     * @param userNo
     * @return Json
     */
    /*@AutoDocMethod(author = DeveloperType.HUGUOKAI, createTime = "2016-12-2",
            cver = VersionType.V100, name = "用户资产接口", description = "用户资产接口",
            model = ModelType.SUBCORE, dtoClazz = BaseRes.class,
            reqParams = {"userNo","coinType"},//有参才需要加的
            progress = ProgressType.FINISHED)
    @AutoDocMethodParam(note = "用户编号@@货币类型", name = "userNo@@coinType")*/
    @RequestMapping(value = "/getUserBalance" , method = RequestMethod.POST)
    public @ResponseBody Map<String , Object> getUserBalance(Integer userNo,Integer coinType){
        Map<String , Object> map = subCoreValidata.validataUserBalance(userNo,coinType);
        return map;
    }

    /**
     * Create by huguokai date:2016年11月9日10:44:33
     * 购买货币操作
     * @param userNo
     * @param buyNum
     * @param dealPwd
     * @param coinType
     * @return Json
     */
    /*@AutoDocMethod(author = DeveloperType.HUGUOKAI, createTime = "2016-12-2",
            cver = VersionType.V100, name = "购买货币接口", description = "购买货币接口",
            model = ModelType.SUBCORE, dtoClazz = BaseRes.class,
            reqParams = {"userNo","buyNum","dealPwd","coinType"},//有参才需要加的
            progress = ProgressType.FINISHED)
    @AutoDocMethodParam(note = "用户编号@@购买数量@@交易密码@@货币类型", name = "userNo@@buyNum@@dealPwd@@coinType")*/
    @RequestMapping(value = "/buyCoinByUserNo" , method = RequestMethod.POST)
    public @ResponseBody Map<String , Object> buyCoinByUserNo(Integer userNo,Integer buyNum,String dealPwd,Integer coinType){
        Map<String , Object> map = subCoreValidata.validateBuyCoinByUserNo(userNo,buyNum,new MD5().getMD5(dealPwd),coinType);
        return map;
    }

    /**
     * Create by huguokai date:2016年11月16日15:29:11
     * 查询认购记录
     * @param userNo
     * @return Json
     */
    /*@AutoDocMethod(author = DeveloperType.HUGUOKAI, createTime = "2016-12-2",
            cver = VersionType.V100, name = "认购记录接口", description = "认购记录接口",
            model = ModelType.SUBCORE, dtoClazz = BaseRes.class,
            reqParams = {"userNo"},//有参才需要加的
            progress = ProgressType.FINISHED)
    @AutoDocMethodParam(note = "用户编号", name = "userNo")*/
    @RequestMapping(value = "/getSubRecordInfoByUserNoAndCoinType" , method = RequestMethod.POST)
    public @ResponseBody Map<String , Object> getSubRecordInfoByUserNoAndCoinType(Integer userNo,Integer pageRecorders,Integer currentPage){
        //Map<String , Object> map = subCoreValidata.getSubRecordInfoByUserNoAndCoinType(userNo,pageRecorders,currentPage);
    	Map<String , Object> map = subCoreValidata.getSubRecordInfoByUserNoAndCoinType(userNo);
        return map;
    }

    /**
     * create by huguokai date:2016年11月18日16:57:20
     * 解冻操作
     * @param userNo
     * @param coinType
     * @param id
     * @return
     */
    /*@AutoDocMethod(author = DeveloperType.HUGUOKAI, createTime = "2016-12-2",
            cver = VersionType.V100, name = "解冻接口", description = "解冻接口",
            model = ModelType.SUBCORE, dtoClazz = BaseRes.class,
            reqParams = {"userNo","coinType","id"},//有参才需要加的
            progress = ProgressType.FINISHED)
    @AutoDocMethodParam(note = "用户编号@@货币类型@@认购记录id", name = "userNo@@coinType@@id")*/
    @RequestMapping(value = "/thawCoinByUserNoAndCoinType" , method = RequestMethod.POST)
    public @ResponseBody Map<String ,Object> thawCoinByUserNoAndCoinType(Integer userNo,Integer coinType,Integer id){
        Map<String ,Object> map = subCoreValidata.thawCoin(userNo,coinType,id);
        return map;
    }

    /**
     * 取得上传的图片
     * @param rsp
     * @param value
     */
    @RequestMapping(value = "getImage")
    public void getImage(HttpServletResponse rsp,String value){
        OutputStream out = null;
        FileInputStream fin = null;
        try {
            if (!value.equals("") && value!=null) {
                out = rsp.getOutputStream();
                // /picture E:/imgfile
                fin = new FileInputStream("/picture"+"/"+value);
                int c;
                while((c=fin.read())!=-1){
                    out.write(c);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (fin != null) {
                    fin.close();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    }


}
