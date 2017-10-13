package com.inesv.digiccy.back.controller.integral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inesv.digiccy.validata.integra.IntegralRuleValidata;
import com.integral.dto.IntegralRuleDto;
import com.pagination.PaginationDto;
import com.respon.R;

/**
 * 积分获取规则
 * @author fangzhenxing
 *
 */
@Controller
@RequestMapping(value="/Integral/Rule")
public class IntegralRuleController {
	
	@Autowired
	private IntegralRuleValidata ruleValidata;
	
	/**
	 * 查询所有
	 * @param integralRuleDto
	 * @param paginationDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryIntegralRule")
	public R query(IntegralRuleDto integralRuleDto,PaginationDto paginationDto){
		return ruleValidata.query(integralRuleDto, paginationDto);
	}
	
	/**
	 * 删除指定记录
	 * @param integralRuleDto
	 * @param paginationDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteIntegralRule")
	public R delete(IntegralRuleDto integralRuleDto){
		return ruleValidata.delete(integralRuleDto);
	}
	
	/**
	 * 添加一条记录
	 * @param integralRuleDto
	 * @param paginationDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addIntegralRule")
	public R insert(IntegralRuleDto integralRuleDto){
		return ruleValidata.insert(integralRuleDto);
	}
	
	
	/**
	 * 修改一条记录
	 * @param integralRuleDto
	 * @param paginationDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateIntegralRule")
	public R update(IntegralRuleDto integralRuleDto){
		return ruleValidata.update(integralRuleDto);
	}
	
	/**
	 * 跳到主页
	 * @return
	 */
	@RequestMapping(value="/gotoRule")
	public String gotoHome(){
		return "/integral/integral_rule";
	}
	
}
