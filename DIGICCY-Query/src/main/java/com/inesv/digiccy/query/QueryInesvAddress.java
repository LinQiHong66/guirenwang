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

import com.inesv.digiccy.dto.AddressDto;
import com.inesv.digiccy.dto.InesvAddressDto;
import com.inesv.digiccy.query.coin.QueryCoin;


@Component
public class QueryInesvAddress {
	
	private static Logger looger =  LoggerFactory.getLogger(QueryInesvAddress.class);
	
	@Autowired
	private QueryRunner queryRunner;
	
	public List<InesvAddressDto> queryAll(){
		String sql = "select * from t_inesv_address";
		List<InesvAddressDto> list = new ArrayList<InesvAddressDto>();
		try {
			list = queryRunner.query(sql, new BeanListHandler<InesvAddressDto>(InesvAddressDto.class));
		} catch (SQLException e) {
			looger.error("查询接口列表出错");
			e.printStackTrace();
		}
		return list;
	}
	
	public InesvAddressDto queryByAddressName(String address_name) {
		String sql = "select * from t_inesv_address where address_name = ?";
		InesvAddressDto inesvAddressDto = new InesvAddressDto();
		Object param = address_name;
		try {
			inesvAddressDto = queryRunner.query(sql, new BeanHandler<InesvAddressDto>(InesvAddressDto.class),param);
		} catch (SQLException e) {
			looger.error("根据接口名查询失败");
			e.printStackTrace();
		}
		return inesvAddressDto;
	}
	
	public List<AddressDto> queryByUserNo(String userNo) {
		String sql = "select * from t_inesv_user_address where user_no = ?";
		List<AddressDto> inesvAddressDto = new ArrayList<AddressDto>();
		Object param = userNo;
		try {
			inesvAddressDto = queryRunner.query(sql, new BeanListHandler<AddressDto>(AddressDto.class),param);
		} catch (SQLException e) {
			looger.error("根据接口名查询失败");
			e.printStackTrace();
		}
		return inesvAddressDto;
	}
	
}
