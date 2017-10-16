package com.inesv.digiccy.validata.integra;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.persistence.integral.IntegralGradeOperation;
import com.inesv.digiccy.query.integral.QueryIntegral;
import com.integral.dto.IntegralGradeDto;
import com.pagination.PaginationDto;
import com.respon.R;
import com.respon.ResultEncoding;

@Component
public class IntegralGradeValidata {
	
	@Autowired
	private IntegralGradeOperation gradeOperation;
	
	@Autowired
	private  QueryIntegral integral;
	
	/**
	 * 条件查询所有的等级特权
	 * @return
	 */
	public R query(IntegralGradeDto gradeDto,PaginationDto paginationDto){
		R r=new R();
		
		try {
			paginationDto.setEntitys(integral.queryIntegralGrade(gradeDto, paginationDto));
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
	 * 添加一个记录
	 * @param gradeDto
	 * @return
	 */
	public R insert(IntegralGradeDto gradeDto){
		
		R r=new R();
		gradeDto.setId(UUID.randomUUID().toString());
		
		try {
			//数据验证
			if(!(R.isNull(gradeDto.getId()) && R.isNull(gradeDto.getConditions()) && R.isNull(gradeDto.getGrade()) 
					&& R.isNull(gradeDto.getAdditional()) && R.isNull(gradeDto.getQuicks()) && R.isNull(gradeDto.getSpeed()))){
				r.setCode(ResultEncoding.R_PARAMETER);
				r.setMsg("参数错误或者参数缺失");
				return r;
			}
			
			if(gradeOperation.insert(gradeDto)){
				r.setMsg("添加成功");
				return r;
			}
			
		} catch (Exception e) {
			r.setCode(ResultEncoding.R_ERR);
			r.setMsg("添加错误");
			e.printStackTrace();
		}
		
		
		return r;
	}
	

	/**
	 * 添加一个记录
	 * @param gradeDto
	 * @return
	 */
	public R update(IntegralGradeDto gradeDto){
		
		R r=new R();
		
		try {
			//数据验证
			if(!(R.isNull(gradeDto.getId()) && R.isNull(gradeDto.getConditions()) && R.isNull(gradeDto.getGrade()) 
					&& R.isNull(gradeDto.getQuicks()) && R.isNull(gradeDto.getSpeed()))){
				r.setCode(ResultEncoding.R_PARAMETER);
				r.setMsg("参数错误或者参数缺失");
				return r;
			}
			
			if(gradeOperation.update(gradeDto)){
				r.setMsg("修改成功");
				return r;
			}
			
		} catch (Exception e) {
			r.setCode(ResultEncoding.R_ERR);
			r.setMsg("修改错误");
			e.printStackTrace();
		}
		
		
		return r;
	}
	
	
	/**
	 * 删除一个记录
	 * @param gradeDto
	 * @return
	 */
	public R delete(IntegralGradeDto gradeDto){
		
		R r=new R();
		
		try {
			//数据验证
			if(!(R.isNull(gradeDto.getId()))){
				r.setCode(ResultEncoding.R_PARAMETER);
				r.setMsg("参数错误或者参数缺失");
				return r;
			}
			
			if(gradeOperation.delete(gradeDto)){
				r.setMsg("删除成功");
				return r;
			}
			
		} catch (Exception e) {
			r.setCode(ResultEncoding.R_ERR);
			r.setMsg("删除错误");
			e.printStackTrace();
		}
		
		
		return r;
	}
}
