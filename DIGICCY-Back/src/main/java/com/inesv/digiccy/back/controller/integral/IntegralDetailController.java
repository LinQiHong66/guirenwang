package com.inesv.digiccy.back.controller.integral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.inesv.digiccy.validata.integra.IntegralDetailValidata;
import com.integral.dto.IntegralDetailDto;
import com.pagination.PaginationDto;
import com.respon.R;


@Controller
@RequestMapping("/Integral/Detail")
public class IntegralDetailController {
	
	@Autowired
	private  IntegralDetailValidata detailValidata;
	
	/**
	 * 按条件查询所有记录
	 * @param detailDto
	 * @param paginationDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryIntegralDetail")
	public R query(IntegralDetailDto detailDto,PaginationDto paginationDto){
		return detailValidata.queryIntegralDetail(detailDto, paginationDto);
	}
	
	/**
	 * 添加一条记录
	 * @param detailDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addIntegralDetail")
	public R insert(IntegralDetailDto detailDto){
		return detailValidata.insert(detailDto);
	}
	
	
	/**
	 * 前往主页
	 * @return
	 */
	@RequestMapping(value="/gotoDetail")
	public String gotoDetail(){
		return "/integral/integral_detail";
	}
}
