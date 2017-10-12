package com.inesv.digiccy.validata.integra;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.persistence.integral.IntegralDetailOperation;
import com.inesv.digiccy.query.integral.QueryIntegral;
import com.integral.dto.IntegralDetailDto;
import com.pagination.PaginationDto;
import com.respon.R;
import com.respon.ResultEncoding;

/**
 * 积分明细操作类
 * @author fnagzhenxing
 * time 2017年9月18日20:18:52
 */
@Component
public class IntegralDetailValidata {
	
	@Autowired
	private  QueryIntegral integral;
	
	@Autowired
	private IntegralDetailOperation detailOperation;
	
	/**
	 * 查询积分明细
	 * @param detailDto
	 * @return
	 */
	public R queryIntegralDetail(IntegralDetailDto detailDto,PaginationDto paginationDto){
		R r=new R();
		try {
			paginationDto.setEntitys(integral.queryIntegralDetail(detailDto,paginationDto));
			r.setData(paginationDto);
			return r;
		} catch (Exception e) {
			e.printStackTrace();
			r.setCode(ResultEncoding.R_ERR);
			r.setMsg("查询错误");
		}
		return r;
	}
	
	/**
	 * 添加一条明细
	 * @param detailDto
	 * @return
	 */
	public R insert(IntegralDetailDto detailDto){
		
		R r=new R();
		try {
			detailDto.setId(UUID.randomUUID().toString());
			if(!(R.isNull(detailDto.getId()) && R.isNull(detailDto.getNumber()) && R.isNull(detailDto.getType()) 
					&& R.isNull(detailDto.getUserId()))){
				r.setCode(ResultEncoding.R_PARAMETER);
				r.setMsg("参数错误或者不全");
				return r;
			}
			if(detailOperation.insert(detailDto)){
				r.setMsg("添加成功");
				return r;
			}else{
				r.setCode(ResultEncoding.R_ERR);
				r.setMsg("添加失败");
				return r;
			}
		} catch (Exception e) {
			e.printStackTrace();
			r.setCode(ResultEncoding.R_ERR);
			r.setMsg("添加错误");
		}
		return r ;
	}
}
