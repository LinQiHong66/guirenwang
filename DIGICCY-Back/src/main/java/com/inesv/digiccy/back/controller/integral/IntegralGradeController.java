package com.inesv.digiccy.back.controller.integral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inesv.digiccy.validata.integra.IntegralGradeValidata;
import com.integral.dto.IntegralGradeDto;
import com.pagination.PaginationDto;
import com.respon.R;

/**
 * 积分等级
 * @author fnagzhenxing
 * time 2017年9月19日17:41:43
 */
@Controller
@RequestMapping("/Integral/Grade")
public class IntegralGradeController {

	@Autowired
	private IntegralGradeValidata gradeValidata;
	
	
	/**
	 * 按条件查询积分等级
	 * @param integralGradeDto
	 * @param paginationDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryIntegralGrade")
	public R query(IntegralGradeDto gradeDto,PaginationDto paginationDto){
		return gradeValidata.query(gradeDto, paginationDto);
	}
	
	/**
	 * 添加一个积分等级
	 * @param gradeDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addIntegralGrade")
	public R insert(IntegralGradeDto gradeDto){
		return gradeValidata.insert(gradeDto);
	}
	
	/**
	 * 删除一个积分等级
	 * @param gradeDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteGrade")
	public R delete(IntegralGradeDto gradeDto){
		return gradeValidata.delete(gradeDto);
	}
	
	/**
	 * 修改一个积分等级
	 * @param gradeDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateGrade")
	public R update(IntegralGradeDto gradeDto){
		return gradeValidata.update(gradeDto);
	}
	
	/**
	 * 跳转到主页
	 * @param gradeDto
	 * @return
	 */
	@RequestMapping(value="/gotoGrade")
	public String gotoHome(){
		return "/integral/integral_grade";
	}
}
