package com.inesv.digiccy.validata.integra;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.persistence.integral.IntegralCompleteOperation;
import com.inesv.digiccy.query.integral.QueryIntegral;
import com.integral.dto.CompletionDto;
import com.integral.dto.IntegralCompleteDto;
import com.integral.dto.UserImgDto;
import com.pagination.PaginationDto;
import com.respon.R;
import com.respon.ResultEncoding;

@Component
public class IntegralCompleteValidata {
	
	@Autowired
	private  QueryIntegral integral;
	
	@Autowired
	private IntegralCompleteOperation operation;
	
	/**
	 * 按条件查询所有的完成任务获取积分状态
	 * @param completeDto
	 * @param paginationDto
	 * @return
	 */
	public R queryIntegralComplete(IntegralCompleteDto completeDto,PaginationDto paginationDto){
			R r=new R();
		try {
			List<IntegralCompleteDto> integralCompleteDtos=integral.queryIntegralComplete(completeDto, paginationDto);
			
			if(completeDto.getUserId()!=null){
				List<CompletionDto> completionDtos=integral.queryCompletion(completeDto.getUserId());
					for(int j=integralCompleteDtos.size()-1;j>=0;j--){
						for(int i=completionDtos.size()-1;i>=0;i--){
							if(completionDtos.get(i).getIntegralId().equals(integralCompleteDtos.get(j).getId())){
								integralCompleteDtos.get(j).setState("1");
								integralCompleteDtos.get(j).setUserId(completeDto.getUserId());
								break;
						}
					}
						//当前未完成的任务
						if(integralCompleteDtos.get(j).getState()==null || "".equals(integralCompleteDtos.get(j).getState()) ){
							
							//当前手持身份证的数据
							if(integralCompleteDtos.get(j).getType().contains("手持身份证")){
								
								List<UserImgDto> dtos=integral.queryCheckImgUri(completeDto.getUserId());
								if(dtos.size()>0){
									 //当前待审核的数据
									if(dtos.get(0).getState().equals("0")){
										integralCompleteDtos.get(j).setState("3");
										integralCompleteDtos.get(j).setUserId(completeDto.getUserId());
										integralCompleteDtos.get(j).setZ_name(dtos.get(0).getUri_front());
										integralCompleteDtos.get(j).setF_name(dtos.get(0).getUri_reverse());
									}else if(dtos.get(0).getState().equals("2")){
										//当前被拒绝的数据
										integralCompleteDtos.get(j).setState("2");
										integralCompleteDtos.get(j).setUserId(completeDto.getUserId());
										integralCompleteDtos.get(j).setRefuse_reason(dtos.get(0).getRefuse_reason());
										integralCompleteDtos.get(j).setZ_name(dtos.get(0).getUri_front());
										integralCompleteDtos.get(j).setF_name(dtos.get(0).getUri_reverse());
									}
									
								}else{//手持不存在
									
									integralCompleteDtos.get(j).setState("0");
									integralCompleteDtos.get(j).setUserId(completeDto.getUserId());
								}
							
							}else{//当前非手持身份证的数据
								
								integralCompleteDtos.get(j).setState("0");
								integralCompleteDtos.get(j).setUserId(completeDto.getUserId());
							}
							
						}
				}
			}
			paginationDto.setEntitys(integralCompleteDtos);
			 r.setData(paginationDto);
		} catch (Exception e) {
			e.printStackTrace();
			r.setCode(1);
			r.setMsg("查询异常");
		}
		
		return r;
	}
	
	/**
	 * 添加一条记录
	 * @param completeDto
	 * @return
	 */
	public R insert(IntegralCompleteDto completeDto){
		R r=new R();
			
		completeDto.setId(UUID.randomUUID().toString());
		//数据校验
		if(!(R.isNull(completeDto.getId()) && R.isNull(completeDto.getNum()))){
			 r.setCode(ResultEncoding.R_PARAMETER);
			 r.setMsg("错误缺失或者数据错误");
			 return r;
		}
		
		try {
			if(operation.insert(completeDto)){
				r.setMsg("添加成功");
			}else{
				r.setMsg("添加失败");
				r.setCode(ResultEncoding.R_ERR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			r.setCode(ResultEncoding.R_ERR);
			r.setMsg("添加异常");
		}
		
		return r;
	}
	
	/**
	 * 删除一个记录
	 * @param completeDto
	 * @return
	 */
	public R delete(IntegralCompleteDto completeDto){
		R r=new R();
		
		//数据校验
		if(completeDto.getId()==null || completeDto.getClass().equals("")){
			r.setCode(ResultEncoding.R_PARAMETER);
			r.setMsg("参数缺失");
			return r;
		}
		
		try {
			if(operation.removed(completeDto)){
				r.setMsg("删除成功");
			}else{
				r.setCode(ResultEncoding.R_ERR);
				r.setMsg("删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			r.setCode(1);
			r.setMsg("删除异常");
		}
		return r;
	}
	
	
	/**
	 * 修改一条记录
	 * @param completeDto
	 * @return
	 */
	public R update(IntegralCompleteDto completeDto){
		R r=new R();
		
		//数据校验
		if(!(R.isNull(completeDto.getId()) && R.isNull(completeDto.getNum()) && R.isNull(completeDto.getType()))){
			 r.setCode(ResultEncoding.R_PARAMETER);
			 r.setMsg("错误缺失或者错误");
			 return r;
		}
		
		try {
			if(operation.update(completeDto)){
				r.setMsg("修改成功");
			}else{
				r.setMsg("修改失败");
				r.setCode(ResultEncoding.R_ERR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			r.setCode(ResultEncoding.R_ERR);
			r.setMsg("修改异常");
		}
		
		return r;
	}
	
	public static void main(String[] args) {
		String i="手持身份证123456456";
		System.out.println(i.contains("手持身份证"));
	}
}
