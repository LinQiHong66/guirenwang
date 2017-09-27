package com.inesv.digiccy.query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.PlanDto;


@Component
public class QueryPlan {
	
	private static Logger logger = LoggerFactory.getLogger(QueryPlan.class);
	
	@Autowired
	private QueryRunner queryRunner;
	
	/**
	 * 查询所有事件
	 * */
	public List<PlanDto> queryAll() {
		String sql = "select * from t_inesv_plan";
		List<PlanDto> planDtos = new ArrayList<PlanDto>();
		try {
			planDtos = queryRunner.query(sql, new BeanListHandler<PlanDto>(PlanDto.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return planDtos;
	}
	
	/**
	 * 根据id查询事件
	 * */
	public PlanDto queryById(Long id){
		String sql = "select * from t_inesv_plan where id = ?";
		PlanDto planDto = new PlanDto();
		try {
			planDto = queryRunner.query(sql,new BeanHandler<PlanDto>(PlanDto.class), id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return planDto;
	}
	
	/**
	 * 查询卖出委托计划
	 * */
	public List<PlanDto> queryOfBuy() {
		String sql = "select * from t_inesv_plan where plan_type = 1 limit 30";
		List<PlanDto> planDtos = null;
		try {
			planDtos = queryRunner.query(sql, new BeanListHandler<PlanDto>(PlanDto.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return planDtos;
	}
	
	/**
	 * 查询买入委托计划
	 * */
	public List<PlanDto> queryOfSell() {
		String sql = "select * from t_inesv_plan where plan_type = 0 limit 30";
		List<PlanDto> planDtos = null;
		try {
			planDtos = queryRunner.query(sql, new BeanListHandler<PlanDto>(PlanDto.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return planDtos;
	}
	
	/**
	 * 查询未被委托的记录
	 * */
	public List<PlanDto> queryPlanIng(){
		String sql = "select * from t_inesv_plan where plan_status = 0 and remark is null";
		List<PlanDto> list = new ArrayList<PlanDto>();
		try {
			list = queryRunner.query(sql, new BeanListHandler<PlanDto>(PlanDto.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("**************" + list.size());
		return list;
	}
	
	public List<PlanDto> queryPlanLimit(Long user_id,Long bill_id){
		String sql ="select * from t_inesv_plan where user_id = ? and bill_id = ? ORDER BY plan_time DESC limit 20";
		List<PlanDto> list = new ArrayList<>();
		Object params[] = {user_id,bill_id};
		try {
			list = queryRunner.query(sql, new BeanListHandler<PlanDto>(PlanDto.class),params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}	
}
