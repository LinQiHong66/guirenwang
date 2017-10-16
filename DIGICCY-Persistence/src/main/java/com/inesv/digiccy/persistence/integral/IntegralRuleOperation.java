package com.inesv.digiccy.persistence.integral;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Bool;

import com.inesv.digiccy.persistence.inesvaddress.InesvAddressOper;
import com.integral.dto.IntegralRuleDto;

@Component
public class IntegralRuleOperation {
	
	//日志
	private static Logger logger = LoggerFactory.getLogger(InesvAddressOper.class);
	
	//数据底层操作对象
	@Autowired
	QueryRunner queryRunner;
	
	/**
	 * 添加一个积分获取规则
	 * @param integralRuleDto
	 * @return
	 */
	public Boolean insert(IntegralRuleDto integralRuleDto){
		
		String sql="insert into t_integral_rule (id,type,number,instruction,identifier,conditions,reward)VALUES(?,?,?,?,?,?,?)";
		Object[] objects={integralRuleDto.getId(),integralRuleDto.getType(),integralRuleDto.getNumber()
				,integralRuleDto.getInstruction(),integralRuleDto.getIdentifier(),integralRuleDto.getConditions(),integralRuleDto.getReward()};
		try {
			 
			if(queryRunner.update(sql,objects)>0){
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加获取积分规则出错 错误信息为"+e.getMessage());
		}
		return false;
	}
	
	/**
	 * 修改一个积分获取规则
	 * @param integralRuleDto 规则实体
	 * @return
	 */
	public Boolean update(IntegralRuleDto integralRuleDto){
		
		String sql="update t_integral_rule set type=? ,number=? ,instruction=? ,identifier=?,conditions=?,reward=? where id=?";
		Object[] objects={integralRuleDto.getType(),integralRuleDto.getNumber()
				,integralRuleDto.getInstruction(),
				integralRuleDto.getIdentifier(),integralRuleDto.getConditions(),integralRuleDto.getReward(),integralRuleDto.getId()};
		try {
			 
			if(queryRunner.update(sql,objects)>0){
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改获取积分规则出错 错误信息为"+e.getMessage());
		}
		return false;
	}
	
	/**
	 * 删除一个积分获取规则
	 * @param integralRuleDto
	 * @return
	 */
	public Boolean delete(IntegralRuleDto integralRuleDto){
		
		String sql="delete from t_integral_rule where id=?";
		Object[] objects={integralRuleDto.getId()};
		
		try {
			 
			if(queryRunner.update(sql,objects)>0){
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除获取积分规则出错 错误信息为"+e.getMessage());
		}
		return false;
	}
	
	/**
	 * 修改状态
	 * @param state
	 * @return
	 */
	public Boolean updateState(String state,String id){
		
		String sql="update  t_integral_rule set state=? where id=?";
		Object[] objects={state,id};
		try {
			 
			if(queryRunner.update(sql,objects)>0){
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除获取积分规则出错 错误信息为"+e.getMessage());
		}
		return false;
	}
}
