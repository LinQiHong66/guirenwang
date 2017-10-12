package com.inesv.digiccy.persistence.integral;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.integral.dto.IntegralDetailDto;

@Component
public class IntegralDetailOperation {
	
	//数据底层操作对象
	@Autowired
	QueryRunner queryRunner;
	
	/**
	 * 添加一条记录
	 * @param detailDto
	 * @return
	 */
	public Boolean insert(IntegralDetailDto detailDto){
		
		String sql="INSERT INTO t_integral_detail (id,type,number,user_id,identifier) values (?,?,?,?,?)";
		Object[] objects={detailDto.getId(),detailDto.getType(),detailDto.getNumber(),detailDto.getUserId(),detailDto.getIdentifier()};
		try {
			if(queryRunner.update(sql, objects)>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 删除一个奖励详细
	 * @param detailDto
	 * @return
	 */
	public Boolean delete(IntegralDetailDto detailDto){
		
		String sql="delete  from t_integral_detail where id=?";
		Object[] objects={detailDto.getId()};
		try {
			if(queryRunner.update(sql, objects)>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * 修改一个记录
	 * @param detailDto
	 * @return
	 */
//	public Boolean update(IntegralDetailDto detailDto){
//		
//		return false;
//	}
}
