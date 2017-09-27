package com.inesv.digiccy.back.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.inesv.digiccy.back.utils.QiniuUploadUtil;
import com.inesv.digiccy.dto.CrowdFundingDto;
import com.inesv.digiccy.util.AbstractAction;
import com.inesv.digiccy.validata.CrowdFundingValidata;
import com.inesv.digiccy.validata.coin.CoinValidata;

/**
 * Created by Administrator on 2016/06/05 0016.
 */
@Controller
@RequestMapping("/crowFunding")
public class CrowdFundingController extends AbstractAction{
	
	@Autowired
	CrowdFundingValidata crowdFundingValidata;
	@Autowired
	CoinValidata coinValidata;
	
	@RequestMapping(value = "gotoCrowd",method = RequestMethod.GET)
    public String gotoCrowd(){
        return "/crowd/crowd";
    }
	
	@RequestMapping(value = "gotoAdd",method = RequestMethod.GET)
    public String gotoAdd(){
        return "/crowd/add";
    }
	
	@RequestMapping(value = "gotoEdit",method = RequestMethod.GET)
	@ResponseBody
    public ModelAndView gotoEdit(String icoNo) throws Exception{
		Map<String,Object> map = new HashMap<>();
		map.put("crowd", crowdFundingValidata.validataCrowdFundingInfoBack(icoNo));
		map.put("coin", coinValidata.getAllCrowdCoin());
        return new ModelAndView("/crowd/edit",map);
    }
	
	/**
     * 得到所有众筹项目的信息
     * @return
     */
    @RequestMapping(value = "getAllCrowdFunding" ,method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getAllCrowdFunding(){
        Map<String,Object> map = crowdFundingValidata.validataAllCrowdFundingBack();
        return map;
    }
	/**
     * 增加众筹项目的信息
     * @return
	 * @throws Exception 
     */
    @RequestMapping(value = "addCrowdFunding" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addCrowdFunding(@RequestParam MultipartFile photoFile,@RequestParam MultipartFile photoRemarkFile,@RequestParam String icoName,@RequestParam Integer icoTarget,
    		@RequestParam String icoPriceType,@RequestParam BigDecimal icoPrice,@RequestParam String icoRemark,@RequestParam String icoExplain,@RequestParam String minNumber,
    		@RequestParam String maxNumber,@RequestParam String numberInteger,@RequestParam String beginDate,@RequestParam String endDate,@RequestParam String convert,@RequestParam String convertFunction,
    		HttpServletRequest request) throws Exception{
    	Map<String,Object> crowdFundingMap = new HashMap<String,Object>();
    	SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
    	String fileName1 = QiniuUploadUtil.createFileName("ico_photo");
    	String fileName2 = QiniuUploadUtil.createFileName("ico_remark_photo");
		try {
			fileName1 = QiniuUploadUtil.upLoadImage(photoFile.getInputStream(), fileName1);
			fileName2 = QiniuUploadUtil.upLoadImage(photoRemarkFile.getInputStream(), fileName2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String savePath1 = QiniuUploadUtil.getStartStaff() + fileName1;
		String savePath2 = QiniuUploadUtil.getStartStaff() + fileName2;
    	crowdFundingMap = crowdFundingValidata.validataAddCrowdFunding(icoName,savePath1,savePath2,icoTarget,
    			icoPriceType,icoPrice,icoRemark,icoExplain,minNumber,maxNumber,numberInteger,convert,convertFunction,sf.parse(beginDate),sf.parse(endDate));
    	return crowdFundingMap;
    }
    /**
     * 修改众筹项目的信息
     * @return
	 * @throws Exception 
     */
    @RequestMapping(value = "editCrowdFunding" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> editCrowdFunding(@RequestParam MultipartFile photoFile,@RequestParam MultipartFile photoRemarkFile,@RequestParam String icoNo,@RequestParam String icoName,@RequestParam Integer icoTarget,
    		@RequestParam String icoPriceType,@RequestParam BigDecimal icoPrice,@RequestParam String icoRemark,@RequestParam String icoExplain,@RequestParam String minNumber,@RequestParam String maxNumber,
    		@RequestParam String numberInteger,@RequestParam String convert,@RequestParam String convertFunction,@RequestParam String beginDate,@RequestParam String endDate,HttpServletRequest request) throws Exception{
    	Map<String,Object> crowdFundingMap = new HashMap<String,Object>();
    	SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
    	String fileName1 = QiniuUploadUtil.createFileName("ico_photo");
    	String fileName2 = QiniuUploadUtil.createFileName("ico_remark_photo");
		String savePath1 = "";
		String savePath2 = "";
		if (!photoFile.isEmpty()) {
			try {
				fileName1 = QiniuUploadUtil.upLoadImage(photoFile.getInputStream(), fileName1);
			} catch (IOException e) {
				e.printStackTrace();
			}
			savePath1 = QiniuUploadUtil.getStartStaff() + fileName1;
		}
		if (!photoRemarkFile.isEmpty()) {
			try {
				fileName2 = QiniuUploadUtil.upLoadImage(photoRemarkFile.getInputStream(), fileName2);
			} catch (IOException e) {
				e.printStackTrace();
			}
			savePath2 = QiniuUploadUtil.getStartStaff() + fileName2;
		}
		if (!savePath1.equals("") && !savePath2.equals("")) {
			crowdFundingMap = crowdFundingValidata.validataEditCrowdFunding(icoNo,icoName,savePath1,savePath2,icoTarget,icoPriceType,icoPrice,icoRemark,icoExplain,minNumber,maxNumber,numberInteger,convert,convertFunction,sf.parse(beginDate),sf.parse(endDate));
		}else if(!savePath1.equals("") && savePath2.equals("")){
			Map<String,Object> crowdMap = crowdFundingValidata.validataCrowdFundingInfoBack(icoNo);
			CrowdFundingDto crowdDto = (CrowdFundingDto)crowdMap.get("data");
			crowdFundingMap = crowdFundingValidata.validataEditCrowdFunding(icoNo,icoName,savePath1,crowdDto.getAttr1(),icoTarget,icoPriceType,icoPrice,icoRemark,icoExplain,minNumber,maxNumber,numberInteger,convert,convertFunction,sf.parse(beginDate),sf.parse(endDate));
		}else if(savePath1.equals("") && !savePath2.equals("")){
			Map<String,Object> crowdMap = crowdFundingValidata.validataCrowdFundingInfoBack(icoNo);
			CrowdFundingDto crowdDto = (CrowdFundingDto)crowdMap.get("data");
			crowdFundingMap = crowdFundingValidata.validataEditCrowdFunding(icoNo,icoName,crowdDto.getIco_photo(),savePath2,icoTarget,icoPriceType,icoPrice,icoRemark,icoExplain,minNumber,maxNumber,numberInteger,convert,convertFunction,sf.parse(beginDate),sf.parse(endDate));
		}else if(savePath1.equals("") && savePath2.equals("")){
			Map<String,Object> crowdMap = crowdFundingValidata.validataCrowdFundingInfoBack(icoNo);
			CrowdFundingDto crowdDto = (CrowdFundingDto)crowdMap.get("data");
			crowdFundingMap = crowdFundingValidata.validataEditCrowdFunding(icoNo,icoName,crowdDto.getIco_photo(),crowdDto.getAttr1(),icoTarget,icoPriceType,icoPrice,icoRemark,icoExplain,minNumber,maxNumber,numberInteger,convert,convertFunction,sf.parse(beginDate),sf.parse(endDate));
		}
    	return crowdFundingMap;
    }
    /**
     * 删除众筹项目的信息
     * @return
	 * @throws Exception 
     */
    @RequestMapping(value = "deleteCrowdFunding" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteCrowdFunding(@RequestParam String icoNo) throws Exception{
        Map<String,Object> crowdFundingMap = crowdFundingValidata.validataDeleteCrowdFunding(icoNo);
        return crowdFundingMap;
    }
	
    /**
     * 得到指定众筹项目的信息
     * @return
     */
    @RequestMapping(value = "getCrowdFundingInfo" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getCrowdFundingInfo(String icoNo){
        Map<String,Object> crowdFundingMap = crowdFundingValidata.validataCrowdFundingInfoBack(icoNo);
        return crowdFundingMap;
    }
    
	@Override
	public String getType() {
		return null;
	}
	@Override
	public String getFileUploadDir() {
		return "/crowdFunding/crowd";
	}
}
