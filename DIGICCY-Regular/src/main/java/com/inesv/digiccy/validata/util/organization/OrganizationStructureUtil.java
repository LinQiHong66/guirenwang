package com.inesv.digiccy.validata.util.organization;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;

import com.inesv.digiccy.dto.UserInfoDto;
import com.inesv.digiccy.persistence.sequence.SequenceOper;

/**
 * 机构信息构建工具
 * 
 * @author Administrator
 *
 */
public class OrganizationStructureUtil {
	
	//顶级机构最大编号
	private final Integer TOP_ORG_MAX_NUM=99;
	
	//二级机构最大编号
	private final Integer SECEND_ORG_MAX_NUM=10;
	
	//三级机构最大编号
	private final Integer THIRD_ORG_MAX_NUM=999;
	
	//四级机构最大编号
	private final Integer FOUR_ORG_MAX_NUM=99999;

	private String invite_num;

	private UserInfoDto userInfoDto;

	private List<String> provinceAbbreviation;

	private SequenceOper sequenceOper;

	private boolean isTopOrg;

	public OrganizationStructureUtil(String invite_num, UserInfoDto userInfoDto, List<String> provinceAbbreviation,
			SequenceOper sequenceOper) {
		super();
		this.invite_num = invite_num;
		this.userInfoDto = userInfoDto;
		this.provinceAbbreviation = provinceAbbreviation;
		this.sequenceOper = sequenceOper;
		isTopOrg = isTopOrg();
	}

	// 构建机构对应信息
	public OrganizationStructureResult structure() {
		// 验证邀请码是否正确
		String validateResult = validate();	//判断是否为null || 是否不存在
		if (validateResult != null) {
			return errorStructureResult(validateResult);
		}
		// 机构编码数字的合法性
		String currentNumString = getCurrentNum();
		String validateNumResult = validateNumString(currentNumString);
		if (validateNumResult != null) {
			return errorStructureResult(validateNumResult);
		}
		return successStructureResult(currentNumString);
	}

	// 获取对应的机构编号数字
	private String getCurrentNum() {
		if (isTopOrg) {
			return getTopOrgNumberString();
		}
		// 根据编号获取对应的下一编号
		Integer nextSeq = sequenceOper.getNextVal(invite_num);
		return Objects.toString(nextSeq, null);
	}

	// 验证机构编号数字是否合法并返回对应错误信息
	private String validateNumString(String currentNumString) {
		if (numberLegitimate(currentNumString)) {
			// 合法
			return null;
		}
		Integer orgType=getOrgType();
		switch(orgType){
		   case 0:
			   return "输入的机构代码编号不合法";
		   case 1:
			   return "该邀请码下的子机构已满或该邀请码有误，请检查！";
		   case 2:
			   return "该邀请码下的经纪人已满或该邀请码有误，请检查！";
		   case 3:
			   return "该邀请码下的客户已满或该邀请码有误，请检查！";
		}
		return "机构编号数字无法正确验证";
	}

	//返回机构编码
	private OrganizationStructureResult successStructureResult(String currentNumString) {
		OrganizationStructureResult organizationStructureResult = new OrganizationStructureResult();
		organizationStructureResult.setSuccess(true);
		organizationStructureResult.setOrg_type(getOrgType());
		organizationStructureResult.setOrg_parent_code(getOrgParentCode());
		if (organizationStructureResult.getOrg_parent_code() == null) {
			organizationStructureResult.setOrg_code(invite_num);
		} else {
			//

			String formatReg=createFormatOrgCodeNumString(organizationStructureResult.getOrg_type());//机构下的机构编码生成规则
			currentNumString=String.format(formatReg, Integer.parseInt(currentNumString));
			organizationStructureResult.setOrg_code(organizationStructureResult.getOrg_parent_code()+currentNumString);
		}
		return organizationStructureResult;
	}

	// 将自增编号不够位数的前面补0 如1的经纪人编号 变成001
	private String createFormatOrgCodeNumString(Integer org_type) {
		// String.format("%04d",1) 会将1前自动补3个0
		// 本代码用于获取"%04d"类型的表达式
		String perfix = "%0";
		String suffix = "d";
		switch (org_type) {
		case 1://次级机构
			return perfix + String.valueOf(SECEND_ORG_MAX_NUM).length() + suffix;
		case 2://经纪人
			return perfix + String.valueOf(THIRD_ORG_MAX_NUM).length() + suffix;
		case 3://交易商
			return perfix + String.valueOf(FOUR_ORG_MAX_NUM).length() + suffix;
		default:
			return "";
		}
	}

	private String getOrgParentCode() {
		if (isTopOrg) {
			return null;
		}
		return userInfoDto.getOrg_code();
	}

	private Integer getOrgType() {
		if (isTopOrg) {
			return 0;
		}
		return userInfoDto.getOrg_type() + 1;
	}

	private OrganizationStructureResult errorStructureResult(String validateResult) {
		OrganizationStructureResult organizationStructureResult = new OrganizationStructureResult();
		organizationStructureResult.setSuccess(false);
		organizationStructureResult.setErrorMsg(validateResult);
		return organizationStructureResult;
	}

	// 基础验证
	private String validate() {
		// 验证邀请码是否存在
		if (StringUtils.isBlank(invite_num)) {
			return "未输入邀请码,无法注册";
		}
		if (!isTopOrg && userInfoDto == null) {
			return "输入的邀请码不存在";
		}
		return null;
	}

	// 获取顶级机构编号数字
	private String getTopOrgNumberString() {
		//return invite_num.substring(invite_num.indexOf("A")+1);
		return invite_num.substring(invite_num.lastIndexOf("G")+1);
	}

	// 判断是否为顶级机构
	private boolean isTopOrg() {
		 if(invite_num.indexOf("G")!=2){
			 return false;
		 }
		 String provinceString=invite_num.substring(0,invite_num.indexOf("G")-1);
		 if(!provinceAbbreviation.contains(provinceString)){
			 return false;
		 }
		 return true;
	}

	// 判断机构编号数字是否合法
	private boolean numberLegitimate(String numString) {
		Integer orgType = getOrgType();
		if (orgType == null || numString == null) {
			return false;
		}
		if (!numString.matches("\\d+")) {
			return false;
		}
		int currentNum=Integer.valueOf(numString);
		if(currentNum<=0){
			return false;
		}
		switch(orgType){
		   case 0:
			   return currentNum<=TOP_ORG_MAX_NUM && numString.length()==2;
		   case 1:
			   return currentNum<=SECEND_ORG_MAX_NUM;
		   case 2:
			   return currentNum<=THIRD_ORG_MAX_NUM;
		   case 3:
			   return currentNum<=FOUR_ORG_MAX_NUM;
		   default :
			   return false;
		}
	}

	public boolean isTopOrgUser() {
		return isTopOrg;
	}
}
