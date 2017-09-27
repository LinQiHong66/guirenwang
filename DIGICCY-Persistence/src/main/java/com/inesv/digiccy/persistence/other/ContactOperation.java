package com.inesv.digiccy.persistence.other;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.ContactDto;

/**
 * 
 * @author Liukeling
 *
 */
@Component
public class ContactOperation {
	private static Logger logger = LoggerFactory.getLogger(UserVoucherOperation.class);

	@Autowired
	QueryRunner queryRunner;

	public void insertContact(ContactDto dto) {
		String sql = "insert into t_inesv_contact (email, wx, wx_qrcord, qq_qrcord, qq, address, remark,telphone) values (?,?,?,?,?,?,?,?)";
		Object[] params = { dto.getEmail(), dto.getWeixin(), dto.getWxqrcord(), dto.getQqqrcord(), dto.getQq(),
				dto.getAddress(), dto.getRemark(), dto.getTelphone() };
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("插入一条联系数据失败");
		}
	}

	public void delById(int id) {
		String sql = "delete from t_inesv_contact where id=?";
		Object[] params = { id };
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("插入一条联系数据失败");
		}
	}

	public void updateContact(ContactDto dto) {
		String sql = "update t_inesv_contact set email=?, wx=?, "
				+ ("none".equals(dto.getWxqrcord()) ? "" : "wx_qrcord=?,")
				+ ("none".equals(dto.getQqqrcord()) ? "" : " qq_qrcord=?,") + " qq=?, address=?, remark=?,telphone=?  where id=?";
		// Object[] params;
		// if (!"none".equals(dto.getQrcord())) {
		// params = new Object[] { dto.getEmail(), dto.getWeixin(),
		// dto.getQrcord(), dto.getQq(), dto.getAddress(),
		// dto.getRemark(), dto.getId() };
		// } else {
		// params = new Object[] { dto.getEmail(), dto.getWeixin(), dto.getQq(),
		// dto.getAddress(), dto.getRemark(),
		// dto.getId() };
		// }
		//
		ArrayList A_params = new ArrayList<>();
		A_params.add(dto.getEmail());
		A_params.add(dto.getWeixin());
		if (!"none".equals(dto.getWxqrcord())) {
			A_params.add(dto.getWxqrcord());
		}
		if (!"none".equals(dto.getQqqrcord())) {
			A_params.add(dto.getQqqrcord());
		}
		A_params.add(dto.getQq());
		A_params.add(dto.getAddress());
		A_params.add(dto.getRemark());
		A_params.add(dto.getTelphone());
		A_params.add(dto.getId());
		try {
			queryRunner.update(sql, A_params.toArray());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("更新一条联系数据失败");
		}
	}
}
