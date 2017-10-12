package com.inesv.digiccy.persistence.integral;

import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.integral.dto.IntegralGradeDto;

@Component
public class IntegralGradeOperation {
	
	//数据底层操作对象
	@Autowired
	QueryRunner queryRunner;
	
	/**
	 * 添加一個等級权限
	 * @param gradeDto
	 * @return
	 */
	public Boolean insert(IntegralGradeDto gradeDto){
		
		String sql="INSERT INTO t_integral_grade (id,grade,conditions,quicks,speed,additional)values(?,?,?,?,?,?)";
		Object[] objects={gradeDto.getId(),gradeDto.getGrade(),gradeDto.getConditions()
				,gradeDto.getQuicks(),gradeDto.getSpeed(),gradeDto.getAdditional()};
		try {
			if(queryRunner.update(sql,objects)>0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * 刪除一個等級規則
	 * @param gradeDto
	 * @return
	 */
	public Boolean delete(IntegralGradeDto gradeDto){
		
		String sql="delete from t_integral_grade where id=?";
		Object[] objects={gradeDto.getId()};
		try {
			if(queryRunner.update(sql,objects)>0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	/**
	 * 修改一等级信息
	 * @param gradeDto
	 * @return
	 */
	public Boolean update(IntegralGradeDto gradeDto){
		
		String sql="update t_integral_grade set grade=?,conditions=?,quicks=?,speed=?,additional=? where id=?";
		Object[] objects={gradeDto.getGrade(),gradeDto.getConditions(),gradeDto.getQuicks(),gradeDto.getSpeed(),gradeDto.getAdditional(),gradeDto.getId()};
		try {
			if(queryRunner.update(sql,objects)>0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
