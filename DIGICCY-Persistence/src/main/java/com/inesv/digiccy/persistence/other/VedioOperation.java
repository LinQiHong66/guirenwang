package com.inesv.digiccy.persistence.other;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.VedioDto;

@Component
public class VedioOperation {
	private static Logger logger = LoggerFactory.getLogger(UserVoucherOperation.class);

	@Autowired
	QueryRunner queryRunner;
	
	public void insert(VedioDto dto){
		String sql = "insert into t_inesv_vedio (vedio_name, vedio_url, vedio_info) values (?,?,?)";
		try {
			queryRunner.update(sql, new Object[]{dto.getName(), dto.getUrl(),dto.getInfo()});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void insertHomeVedio(VedioDto dto){
		String sql = "insert into t_inesv_vedio (vedio_name, vedio_url, vedio_info,id) values (?,?,?,?)";
		try {
			queryRunner.update(sql, new Object[]{dto.getName(), dto.getUrl(),dto.getInfo(),dto.getId()});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void update(VedioDto dto){
		String sql = "update t_inesv_vedio set vedio_name=?,vedio_url=?,vedio_info=? where id=?";
		try {
			queryRunner.update(sql, new Object[]{dto.getName(), dto.getUrl(),dto.getInfo(),dto.getId()});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
