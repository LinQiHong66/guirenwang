package com.inesv.digiccy.validata.integra;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.persistence.integral.IntegralRuleOperation;
import com.inesv.digiccy.query.integral.QueryIntegral;
import com.integral.dto.IntegralRuleDto;
import com.pagination.PaginationDto;
import com.respon.R;
import com.respon.ResultEncoding;


/**
 * 积分规则
 * @author fangzhenxing
 * time 2017年9月19日17:20:29
 */
@Component
public class IntegralRuleValidata {
	
	@Autowired
	private  QueryIntegral integral;
	
	@Autowired
	private  IntegralRuleOperation operation;
	
	/**
	 * 按条件查询积分特权
	 * @param integralRuleDto
	 * @return
	 */
	public R query(IntegralRuleDto integralRuleDto,PaginationDto paginationDto){
		R r=new R();
		
		try {
			paginationDto.setEntitys(integral.queryIntegralRule(integralRuleDto, paginationDto));
			r.setData(paginationDto);
		} catch (Exception e) {
			e.printStackTrace();
			r.setCode(ResultEncoding.R_ERR);
			r.setMsg("查询积分规则异常");
		}
		
		return r;
		
	}
	
	/**
	 * 添加一个积分规则
	 * @param integralRuleDto
	 * @return
	 */
	public R insert(IntegralRuleDto integralRuleDto){
		R r=new R();
		integralRuleDto.setId(UUID.randomUUID().toString());
		//数据校验
		if(!(R.isNull(integralRuleDto.getId()) && R.isNull(integralRuleDto.getInstruction()) && R.isNull(integralRuleDto.getNumber())
				&& R.isNull(integralRuleDto.getReward()) && R.isNull(integralRuleDto.getIdentifier())	&& R.isNull(integralRuleDto.getConditions()))){
			r.setCode(ResultEncoding.R_PARAMETER);
			r.setMsg("参数错误或缺失");
			return r;
		}
		
		
		try {
			if(operation.insert(integralRuleDto)){
				r.setMsg("添加成功");
			}else{
				r.setCode(ResultEncoding.R_ERR);
				r.setMsg("添加失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
	
	
	/**
	 * 修改一个积分规则
	 * @param integralRuleDto
	 * @return
	 */
	public R update(IntegralRuleDto integralRuleDto){
		R r=new R();
		
		//数据校验
		if(!(R.isNull(integralRuleDto.getId()) && R.isNull(integralRuleDto.getInstruction()) && R.isNull(integralRuleDto.getNumber())
				&& R.isNull(integralRuleDto.getReward()) && R.isNull(integralRuleDto.getIdentifier())	&& R.isNull(integralRuleDto.getConditions()))){
			r.setCode(ResultEncoding.R_PARAMETER);
			r.setMsg("参数错误或缺失");
			return r;
		}
		
		
		try {
			if(operation.update(integralRuleDto)){
				r.setMsg("修改成功");
			}else{
				r.setCode(ResultEncoding.R_ERR);
				r.setMsg("修改失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 删除一个积分规则
	 * @param integralRuleDto
	 * @return
	 */
	public R delete(IntegralRuleDto integralRuleDto){
		R r=new R();
		
		//数据校验
		if(!(R.isNull(integralRuleDto.getId()))){
			r.setCode(ResultEncoding.R_PARAMETER);
			r.setMsg("参数错误或缺失");
			return r;
		}
		try {
			if(operation.delete(integralRuleDto)){
				r.setMsg("添加成功");
			}else{
				r.setCode(ResultEncoding.R_ERR);
				r.setMsg("添加失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
}
