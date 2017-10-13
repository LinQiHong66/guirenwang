package com.inesv.digiccy.persistence.integral;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.persistence.inesvaddress.InesvAddressOper;
import com.integral.dto.CompletionDto;
import com.integral.dto.IntegralCompleteDto;

/**
 * 操作完成任务获取积分状态
 * @author fangzhenxing
 * time 2017年9月18日18:37:18
 */
@Component
public class IntegralCompleteOperation {
	
	//日志
	private static Logger logger = LoggerFactory.getLogger(InesvAddressOper.class);
	
	//数据底层操作对象
	@Autowired
	QueryRunner queryRunner;
	
	/**
	 * 添加任务
	 * @return
	 */
	public boolean insert(IntegralCompleteDto completeDto){
		
		try {
			String sql="insert into t_integral_complete (id,type,number)VALUES(?,?,?)";
			Object[] objects={completeDto.getId(),completeDto.getType(),completeDto.getNum()};
			queryRunner.update(sql,objects);
			return true;
		} catch (Exception e) {
			logger.info("添加完成任务获取积分状态错误");
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * 添加任务完成记录
	 * @return
	 */
	public boolean insertCompletion(CompletionDto completeDto){
		
		try {
			String sql="insert into t_user_completion (id,user_id,integral_id)VALUES(?,?,?)";
			Object[] objects={completeDto.getComId(),completeDto.getUserId(),completeDto.getIntegralId()};
			int i=queryRunner.update(sql,objects);
			if(i>0){
				return true;
			}
		} catch (Exception e) {
			logger.info("添加完成任务获取积分状态错误");
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	/**
	 * 修改方法
	 * @param completeDto
	 * @return
	 */
	public boolean update(IntegralCompleteDto completeDto){
		
		try {
			String sql="update t_integral_complete set  type=? , number=? where  id=?";
			Object[] objects={completeDto.getType(),completeDto.getNum(),completeDto.getId()};
			int i=queryRunner.update(sql,objects);
			if(i>0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 删除一条记录
	 * @return
	 */
	public boolean removed(IntegralCompleteDto completeDto){
		
		try {
			String sql="delete from t_integral_complete where id=?";
			Object[] objects={completeDto.getId()};
			int i=queryRunner.update(sql,objects);
			if(i>0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
