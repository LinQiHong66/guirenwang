package com.inesv.digiccy.controller;

import com.inesv.digiccy.common.autocreate.annotation.AutoDocMethod;
import com.inesv.digiccy.common.autocreate.annotation.AutoDocMethodParam;
import com.inesv.digiccy.common.autocreate.bean.DeveloperType;
import com.inesv.digiccy.common.autocreate.bean.ModelType;
import com.inesv.digiccy.common.autocreate.bean.ProgressType;
import com.inesv.digiccy.common.autocreate.bean.VersionType;
import com.inesv.digiccy.common.dto.BaseRes;
import com.inesv.digiccy.validata.CommonValidata;
import com.inesv.digiccy.validata.coin.CoinValidata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 公共接口
 * Created by JimJim on 2016/12/9 0009.
 */
@Controller
@RequestMapping("/common")
public class CommonController {

    @Autowired
    CoinValidata coinValidata;
    
    @Autowired
    CommonValidata commonValidata;

    @RequestMapping(value = "getCoin",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getAllCoin(){
        Map<String,Object> coinMap = coinValidata.getAllCoin();
        return coinMap;
    }
    
    /**
     * 得到所有币的信息
     * @return
     */
    @RequestMapping(value = "getAllCoinInfo",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getAllCoinInfo(){
        Map<String,Object> coinMap = coinValidata.getAllCoinInfo();
        return coinMap;
    }
    
    /**
     * 得到所有的委托货币种类信息
     * @param request
     * @return
     */
	/*@AutoDocMethod(author = DeveloperType.CHENWAIQING, createTime = "2016-12-8",cver = VersionType.V100, name = "货币类型", description = "得到委托管理显示记录的货币种类接口",
			model = ModelType.OTHER, dtoClazz = BaseRes.class,progress = ProgressType.TESTING)
	@AutoDocMethodParam(note = "", name = "")*/
    @RequestMapping(value = "getBasicCoinType",method = RequestMethod.POST)
    public @ResponseBody Map<Object,Object> getBasicCoinType(HttpServletRequest request){
		return commonValidata.validateBasicCoinType();
    }
    
    /**
     * 得到除人民币外的所有的委托货币种类信息
     * @param request
     * @return
     */
	/*@AutoDocMethod(author = DeveloperType.CHENWAIQING, createTime = "2016-12-8",cver = VersionType.V100, name = "除人民币外的货币类型", description = "得到除人民币外的所有的委托货币种类信息接口",
			model = ModelType.OTHER, dtoClazz = BaseRes.class,progress = ProgressType.TESTING)
	@AutoDocMethodParam(note = "", name = "")*/
    @RequestMapping(value = "getBasicCoinTypeExceptRMB",method = RequestMethod.POST)
    public @ResponseBody Map<Object,Object> getBasicCoinTypeExceptRMB(HttpServletRequest request){
		return commonValidata.validateBasicCoinTypeExceptRMB();
    }

	/**
     * 判断交易密码是否正确
     * @param request
     * @return
     */
	/*@AutoDocMethod(author = DeveloperType.CHENWAIQING, createTime = "2016-12-8",cver = VersionType.V100, name = "判断交易密码", description = "判断交易密码是否正确接口", 
			model = ModelType.OTHER, dtoClazz = BaseRes.class,progress = ProgressType.TESTING,reqParams={"userNo","dealPwd"})
	@AutoDocMethodParam(note = "用户编号@@交易密码", name = "userNo@@dealPwd")
    @RequestMapping(value = "/common/getTradePassword",method = RequestMethod.POST)*/
    @RequestMapping(value = "getTradePassword",method = RequestMethod.POST)
    public @ResponseBody Map<Object,Object> getTradePassword(Integer userNo,String dealPwd){
		return commonValidata.validateTradePassword(userNo,dealPwd);
    }

}
