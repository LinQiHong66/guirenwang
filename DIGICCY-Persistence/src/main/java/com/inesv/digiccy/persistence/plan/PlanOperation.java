package com.inesv.digiccy.persistence.plan;


import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.PlanDto;
import com.inesv.digiccy.util.ObjectChangeUtil;

@Component
public class PlanOperation {
	@Autowired
	QueryRunner queryRunner;
	
	/**
	 *新增计划表记录 
	 * */
	public void insert(PlanDto planDto) {
		String sql = "insert into t_inesv_plan(user_id,bill_id,"
				+ "plan_type,top_money_start,top_money_stop,low_money_start,"
				+ "low_money_stop, plan_status,plan_time, remark,plan_money) values(?,?,?,?,?,?,?,?,?,?,?)";
		planDto.setPlan_status(0);
		planDto.setRemark(null);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date formatDate = new Date();
		String newDate = simpleDateFormat.format(formatDate);
		Timestamp plan_time = Timestamp.valueOf(newDate);
		planDto.setPlan_time(plan_time);
		Object param[] = new ObjectChangeUtil<PlanDto>().objectToArray(planDto);
		try {
			queryRunner.update(sql, param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 结束计划
	 * */
	public void updateOver(Long id) {
		String sql = "update t_inesv_plan set plan_status = 2 where id = ?";
		try {
			queryRunner.update(sql,id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 完成计划
	 * */
	public void updateFinish(Long id){
		String sql = "update t_inesv_plan set plan_status = 1 where id = ?";
		try {
			queryRunner.update(sql,id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改委托单编号
	 * @throws SQLException 
	 * */
	public void updateRemark(Long remark,Long id) throws SQLException{
		String sql = "update t_inesv_plan set remark = ? where id = ?";
		Object params[] = {remark,id};
		queryRunner.update(sql,params);
	}
	
  public void updateStatus(Long id) throws SQLException{
	  String sql = "update t_inesv_plan set plan_status = 1 where id = ?";
	  queryRunner.update(sql,id);
  }
	
}
