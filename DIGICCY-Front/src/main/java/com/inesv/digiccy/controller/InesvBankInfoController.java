package com.inesv.digiccy.controller;

import com.inesv.digiccy.common.autocreate.annotation.AutoDocMethod;
import com.inesv.digiccy.common.autocreate.annotation.AutoDocMethodParam;
import com.inesv.digiccy.common.autocreate.bean.DeveloperType;
import com.inesv.digiccy.common.autocreate.bean.ModelType;
import com.inesv.digiccy.common.autocreate.bean.ProgressType;
import com.inesv.digiccy.common.autocreate.bean.VersionType;
import com.inesv.digiccy.common.dto.BaseRes;
import com.inesv.digiccy.validata.bank.InesvBankInfoValidata;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
@Controller
public class InesvBankInfoController {

     @Autowired
      private CommandGateway commandGateway;

      @Autowired
      InesvBankInfoValidata inesvBankInfoValidata;

    /**
     * 添加银行地址
     * @param remark_name
     * @param bank
     * @param province
     * @param city
     * @param branch
     * @param name
     * @param bank_num
     * @return
     */
      /*@AutoDocMethod(author = DeveloperType.DEFAULT, createTime = "2016-12-2",
              cver = VersionType.V100, name = "测试添加银行卡地址接口", description = "测试添加银行卡地址接口",
              model = ModelType.BANK, dtoClazz = BaseRes.class,
              reqParams = {"remark_name","bank","province","city","branch","name","bank_num","userNo"},//有参才需要加的
              progress = ProgressType.FINISHED)
      @AutoDocMethodParam(note = "备注名称@@开户银行@@开户省份@@开户城市@@开户支行@@开户姓名@@开户卡号@@用户编号", name = "remark_name@@bank@@province@@city@@branch@@name@@bank_num@@userNo")*/
      @RequestMapping(value = "/inervbank/bankinfo", method = RequestMethod.POST)
      public @ResponseBody Map<String, Object> insertBankInfo(String remark_name, String bank, String province, String city, String branch, String name, String bank_num,Integer userNo){
          Map<String, Object> map = inesvBankInfoValidata.insertBankInfo(remark_name, bank, province, city, branch, name, bank_num,userNo);
          return map;
      }
      /*@AutoDocMethod(author = DeveloperType.DEFAULT, createTime = "2016-12-2",
      		cver = VersionType.V100, name = "测试添加银行卡地址接口", description = "测试添加银行卡地址接口",
      		model = ModelType.BANK, dtoClazz = BaseRes.class,
      		reqParams = {"remark_name","bank","province","city","branch","name","bank_num","userNo"},//有参才需要加的
      		progress = ProgressType.FINISHED)
		@AutoDocMethodParam(note = "备注名称@@开户银行@@开户省份@@开户城市@@开户支行@@开户姓名@@开户卡号@@用户编号", name = "remark_name@@bank@@province@@city@@branch@@name@@bank_num@@userNo")*/
      	@RequestMapping(value = "/inervbank/userBankinfo", method = RequestMethod.POST)
      	public @ResponseBody Map<String, Object> insertUserBankInfo(String bank, String address, String branch, String name, String bank_num,Integer userNo){
      		try {
				bank   = new String( bank.getBytes("ISO-8859-1") , "UTF-8");
				address   = new String( address.getBytes("ISO-8859-1") , "UTF-8");
				branch   = new String( branch.getBytes("ISO-8859-1") , "UTF-8");
				name   = new String( name.getBytes("ISO-8859-1") , "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}  
      		Map<String, Object> map = inesvBankInfoValidata.insertUserBankInfo(bank, address, branch, name, bank_num,userNo);
      			return map;
      	}

    /**
     * 修改银行卡地址
     * @param remark_name
     * @param bank
     * @param province
     * @param city
     * @param branch
     * @param name
     * @param bank_num
     * @return
     */
    /*@AutoDocMethod(author = DeveloperType.DEFAULT, createTime = "2016-12-2",
              cver = VersionType.V100, name = "测试修改银行卡地址接口", description = "测试修改银行卡地址接口",
              model = ModelType.BANK, dtoClazz = BaseRes.class,
              reqParams = {"bankId","remark_name","bank","province","city","branch","name","bank_num","userNo"},//有参才需要加的
              progress = ProgressType.FINISHED)
    @AutoDocMethodParam(note = "银行地址id@@用户编号@@备注名称@@开户银行@@开户省份@@开户城市@@开户支行@@开户姓名@@开户卡号", name = "bankId@@userNo@@remark_name@@bank@@province@@city@@branch@@name@@bank_num")*/
    @RequestMapping(value = "/inervbank/upbankinfo", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> updateBankInfo(Long bankId,Integer userNo,String remark_name, String bank, String province, String city, String branch, String name, String bank_num){
        Map<String, Object> mapupdate = inesvBankInfoValidata.updateBankInfo(bankId,userNo,remark_name, bank, province, city, branch, name, bank_num);
        return mapupdate;
    }

    /**
     * 得到银行卡信息
     * @param request
     * @return
     */
    /*@AutoDocMethod(author = DeveloperType.DEFAULT, createTime = "2016-12-2",
            cver = VersionType.V100, name = "测试查看银行卡地址接口", description = "测试查看银行卡地址接口",
            model = ModelType.BANK, dtoClazz = BaseRes.class,
            reqParams = {"userNo"},//有参才需要加的
            progress = ProgressType.FINISHED)
    @AutoDocMethodParam(note = "用户编号", name = "userNo")*/
    @RequestMapping(value = "/inervbank/getBankInfo",method = RequestMethod.POST)
    public @ResponseBody Map<Object,Object> getBankInfo(HttpServletRequest request, @RequestParam String userNo){
        return inesvBankInfoValidata.getBankInfo(userNo);
    }
    
  
    /**
     * @param id 根据id删除
     * @return
     */
    /*@AutoDocMethod(author = DeveloperType.DEFAULT, createTime = "2016-12-2",
            cver = VersionType.V100, name = "测试删除银行卡地址接口", description = "测试删除银行卡地址接口",
            model = ModelType.BANK, dtoClazz = BaseRes.class,
            reqParams = {"id","userNo"},//有参才需要加的
            progress = ProgressType.FINISHED)
    @AutoDocMethodParam(note = "银行记录id@@用户编号", name = "id@@userNo")*/
    @RequestMapping(value = "/inervbank/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> deleteBankInfo(Long id,Integer userNo){
        Map<Object, Object> mapdelete = inesvBankInfoValidata.deleteBankInfo(id,userNo);
        return mapdelete;
    }
    

    /**
     * 删除银行卡地址
     * @param
     * @return
     
    
    @AutoDocMethod(author = DeveloperType.DEFAULT, createTime = "2016-12-2",
            cver = VersionType.V100, name = "测试删除银行卡地址接口", description = "测试删除银行卡地址接口",
            model = ModelType.BANK, dtoClazz = BaseRes.class,
            reqParams = {"remark_name","bank","province","city","branch","name","bank_num","userNo","id"},//有参才需要加的
            progress = ProgressType.FINISHED)
    @AutoDocMethodParam(note = "用户ID@@用户编号@@备注名称@@开户银行@@开户省份@@开户城市@@开户支行@@开户姓名@@开户卡号", name = "id@@userNo@@remark_name@@bank@@province@@city@@branch@@name@@bank_num")
    @RequestMapping(value = "/inervbank/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> deleteBankInfo(Integer id,Integer userNo,String remark_name, String bank, String province, String city, String branch, String name, String bank_num){
        Map<Object, Object> mapdelete = inesvBankInfoValidata.deleteBankInfo(id,userNo,remark_name, bank, province, city, branch, name, bank_num);
        return mapdelete;
    }
    */
    
}
