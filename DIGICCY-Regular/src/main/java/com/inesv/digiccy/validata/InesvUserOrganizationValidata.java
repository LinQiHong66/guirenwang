package com.inesv.digiccy.validata;

import com.alibaba.druid.util.StringUtils;
import com.inesv.digiccy.api.command.RegUserCommand;
import com.inesv.digiccy.api.command.UserOrganizationCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.dto.InesvUserOrganizationDto;
import com.inesv.digiccy.dto.ResultFunctionDto;
import com.inesv.digiccy.dto.UserInfoDto;
import com.inesv.digiccy.persistence.sequence.SequenceOper;
import com.inesv.digiccy.persistence.user.UserOrganizationOperation;
import com.inesv.digiccy.query.QueryMyRecInfo;
import com.inesv.digiccy.query.QueryProvinceAbbreviation;
import com.inesv.digiccy.query.QuerySubCore;
import com.inesv.digiccy.query.QueryUserOrganization;
import com.inesv.digiccy.util.MD5;
import com.inesv.digiccy.validata.util.organization.OrganizationStructureResult;
import com.inesv.digiccy.validata.util.organization.OrganizationStructureUtil;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/15 0015.
 */
@Component
public class InesvUserOrganizationValidata {
	@Autowired
	private CommandGateway commandGateway;

	@Autowired
	private QuerySubCore querySubCore;
	
	@Autowired
    QueryMyRecInfo queryMyRecInfo;
    
    @Autowired
    QueryUserOrganization queryUserOrganization;
    
    @Autowired
    UserOrganizationOperation userOrganizationOperation;
    
    @Autowired
    QueryProvinceAbbreviation queryProvinceAbbreviation;
    
    @Autowired
    SequenceOper sequenceOper;

	/**
	 * 手动申请升级
	 * @return
	 */
	public Map<String, Object> addOrganizationValidate(Integer userNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 判断用户是否存在
		InesvUserDto uid = querySubCore.getInesvUserByUserNo(userNo, false);
		if (uid == null) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
			return map;
		}
		if(uid.getOrg_type()==0 || uid.getOrg_type()==1) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "该用户已是机构或已是子机构，不能升级！");
			return map;
		}
		if(uid.getOrg_type()==2) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "该用户是经济人，暂不能升级，请等待后期通知！");
			return map;
		}
		InesvUserOrganizationDto allList = queryUserOrganization.getAllUserOrganization(userNo, uid.getOrg_type()-1);
		if(allList!=null) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "你已提交过该申请，请等待管理员审核！");
			return map;
		}
		try {
			UserOrganizationCommand org = new UserOrganizationCommand(0,uid.getUser_no(),0,uid.getOrg_type()-1,new Date(),"","","addUserOrganization");
			commandGateway.sendAndWait(org);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "添加升级申请失败！");
		}
		return map;
	}
	
	/**
	 * 申请后台审核通过
	 * @return
	 */
	public Map<String, Object> updateOrganizationValidate(Integer id,Integer userNo,Integer org_type) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		// 判断用户是否存在
		InesvUserDto uid = querySubCore.getInesvUserByUserNo(userNo, false);
		if (uid == null) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
			return map;
		}
		if(uid.getOrg_type()==0 || uid.getOrg_type()==1) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "该用户已是机构或已是子机构，不能升级！");
			return map;
		}
		if(uid.getOrg_type() == 2) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "该用户是经纪人，暂不能升级！");
			return map;
		}
		if(uid.getOrg_type()==org_type) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "该用户已是该等级！");
			return map;
		}
		UserInfoDto parentUserInfoDtos = queryMyRecInfo.queryUserInfoByOrgParentCode(uid.getOrg_parent_code());//上级用户信息
		if(parentUserInfoDtos == null) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "找不到用户的上级！");
			return map;
		}
		UserInfoDto parentUserInfoDto = new UserInfoDto();
		if(uid.getOrg_type() == parentUserInfoDtos.getOrg_type()) {//判断用户是否和上级机构等级一致
			parentUserInfoDto = queryMyRecInfo.getInesvParentUserByUserNo(userNo,"dengyu");//根据邀请码查询出此邀请码的用户信息
		}
		if(uid.getOrg_type() > parentUserInfoDtos.getOrg_type()) {//用户机构等级低于上级机构等级
			parentUserInfoDto = queryMyRecInfo.getInesvParentUserByUserNo(userNo,"dayu");//根据邀请码查询出此邀请码的用户信息
		}
		ResultFunctionDto functionDto = new ResultFunctionDto();
		if(parentUserInfoDto.getOrg_type() == parentUserInfoDtos.getOrg_type()) {//用户的上级的上级和用户的上级是一样的等级
			functionDto = queryMyRecInfo.queryByFunction(parentUserInfoDtos.getUser_no(),2);
			parentUserInfoDto = querySubCore.getInesvUserByUserNo(functionDto.getLevel_user_no());
		}
		if(parentUserInfoDto.getOrg_type() != 1) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "该交易商的上级没有子机构，不能升级！");
			return map;
		}
		if(uid.getOrg_type() - parentUserInfoDto.getOrg_type() > 2) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "该交易商的上级的上级为机构，不能跨2级，抱歉！");
			return map;
		}
		if(parentUserInfoDto == null) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "找不到父类的上级！");
			return map;
		}
    	List<String> provinceAbbreviationList=queryProvinceAbbreviation.getAllProvinceAbbreviation();//获取省份简写信息
    	OrganizationStructureUtil organizationStructureUtil=new OrganizationStructureUtil(parentUserInfoDto.getInvite_num(), parentUserInfoDto, provinceAbbreviationList, sequenceOper);
    	OrganizationStructureResult organizationStructureResult=organizationStructureUtil.structure();//解析验证并返回对应的机构信息
    	if(!organizationStructureResult.isSuccess()){
    		//未通过验证
    		map.put("code",ResponseCode.FAIL);
            map.put("desc",organizationStructureResult.getErrorMsg());
            return map;
    	}
    	String recCode = "";	
    	if(uid.getInvite_num() == null || uid.getInvite_num().equals("")) {	//交易商升级
    		recCode = createRecCodeByOrgType(organizationStructureResult.getOrg_type());//获取邀请码
    	}else {
    		recCode = uid.getInvite_num();	//非交易商升级
    	}
		try {
			userOrganizationOperation.updateUserOrganization(id, 1, 
					organizationStructureResult.getOrg_type(), organizationStructureResult.getOrg_code(), userNo, recCode);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
	
	/**
	 * 取得所有升级申请
	 * @return
	 */
	public Map<String, Object> getOrganizationValidate() {
		Map<String, Object> map = new HashMap<String, Object>();
		// 判断用户是否存在
		List<InesvUserOrganizationDto> uidOrganization = queryUserOrganization.getAllUserOrganization();
		if (uidOrganization != null) {
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("total", uidOrganization.size());
			map.put("data", uidOrganization);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
	
	//目前只有顶级机构和经纪人可以生成推荐码
    private String createRecCodeByOrgType(Integer orgType){
    	if(orgType>=3){
    		return null;
    	}
    	return creatRecCode().toUpperCase();
    }
    
    /** 生成推荐码 */
	public String creatRecCode() {
		String result = getCode();
		UserInfoDto userInfoDto = queryMyRecInfo.queryUserInfoByInvitNum(result);
//		if(userInfoDto != null){
//			result = getCode();
//		}
		boolean ok = userInfoDto != null;
		while(ok){
			result = getCode();
			userInfoDto = queryMyRecInfo.queryUserInfoByInvitNum(result);
			ok = userInfoDto != null;
		}
		return result;
	}
	public String getCode(){
		String result = "";
		for (int i = 0; i < 7; i++) {
			int intVal = (int) (Math.random() * 26 + 97);
			result = result + (char) intVal;
		}
		return result;
	}
}
