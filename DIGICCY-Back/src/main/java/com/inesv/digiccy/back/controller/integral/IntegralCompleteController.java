package com.inesv.digiccy.back.controller.integral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inesv.digiccy.validata.integra.IntegralCompleteValidata;
import com.integral.dto.IntegralCompleteDto;
import com.pagination.PaginationDto;
import com.respon.R;

/**
 * 完成任务获取积分状态
 * @author fangzhenxing
 * time 2017年9月18日18:18:45
 */
@Controller
@RequestMapping("/Integral/Complete")
public class IntegralCompleteController {
	
	@Autowired
	private IntegralCompleteValidata completeValidata;
	
	
	@RequestMapping(value = "/gotoHome" ,method = RequestMethod.GET)
	public String gotoHome(){
		return "/integral/integral_complete";
	}
	
	/**
	 * 条件查询
	 * @param completeDto
	 * @param paginationDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/querComplete" ,method = RequestMethod.POST)
	public R queryIntegralComplete(IntegralCompleteDto completeDto,PaginationDto paginationDto){
		return completeValidata.queryIntegralComplete(completeDto, paginationDto);
	}
	
	/**
	 * 添加一条记录
	 * @param completeDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addComplete" ,method = RequestMethod.POST)
	public R insert(IntegralCompleteDto completeDto){
		return completeValidata.insert(completeDto);
	}
	
	/**
	 * 删除一条记录
	 * @param completeDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteComplete" ,method = RequestMethod.POST)
	public R delete(IntegralCompleteDto completeDto){
		return completeValidata.delete(completeDto);
	}
	
	/**
	 * 修改一条记录
	 * @param completeDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateComplete" ,method = RequestMethod.POST)
	public R update(IntegralCompleteDto completeDto){
		return completeValidata.update(completeDto);
	}
}
