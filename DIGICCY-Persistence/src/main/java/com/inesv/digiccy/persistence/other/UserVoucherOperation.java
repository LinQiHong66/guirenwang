package com.inesv.digiccy.persistence.other;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 
 * @author Liukeling
 *
 */
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.inesv.digiccy.dto.UserVoucherDto;

import jnr.ffi.Struct.swblk_t;

@Component
public class UserVoucherOperation {
	private static Logger logger = LoggerFactory.getLogger(UserVoucherOperation.class);

	@Autowired
	QueryRunner queryRunner;

	// 开始审核
	public void insert(UserVoucherDto dto) {
		String sql = "insert into t_inesv_user_voucher (voucher_cardid, voucher_type, voucher_imgurl1, voucher_imgurl2, voucher_imgurl3, voucher_state, userNo, realName, voucher_mytype, id_startdate, id_enddate) values (?,?,?,?,?,?,?,?,?, ?, ?)";
		ArrayList<Object> params = new ArrayList<>();
		params.add(dto.getCardId());
		params.add(dto.getCardType());
		params.add(dto.getImgUrl1());
		params.add(dto.getImgUrl2());
		params.add(dto.getImgUrl3());
		params.add(1);
		params.add(dto.getUserNo());
		params.add(dto.getTrueName());
		params.add(dto.getCardType() == 0 ? dto.getMyvoucherType() : "");
		params.add(dto.getStartDate() == null ? new Date() : dto.getStartDate());
		params.add(dto.getEnDateDate() == null ? new Date() : dto.getEnDateDate());
		try {
			queryRunner.update(sql, params.toArray(new Object[] {}));
		} catch (SQLException e) {
			logger.error("添加一条身份验证数据失败");
			e.printStackTrace();
		}
	}

	// 重新开始审核
	public void updatestartByUserNo(UserVoucherDto dto) {
		String sql = "update t_inesv_user_voucher set voucher_cardid=?, voucher_type=?, voucher_imgurl1=?, voucher_imgurl2=?, voucher_imgurl3=?, voucher_state=?, realName=?, voucher_mytype=? where userNo=?";
		Object[] params = { dto.getCardId(), dto.getCardType(), dto.getImgUrl1(), dto.getImgUrl2(), dto.getImgUrl3(), 1,
				dto.getTrueName(), dto.getCardType() == 0 ? dto.getMyvoucherType() : "", dto.getUserNo() };
		try {
			queryRunner.update(sql, params);
		} catch (Exception e) {
			logger.error("更新开始审核身份失败");
			e.printStackTrace();
		}
	}

	// 更改状态
	@Transactional(rollbackFor = { Exception.class })
	public void modifyVoucherState(UserVoucherDto dto) throws Exception {
		String sql = "update t_inesv_user_voucher set voucher_state=? where userNo=?";
		Object[] params = { dto.getState(), dto.getUserNo() };
		queryRunner.update(sql, params);
		String sql1 = "update t_inesv_user set certificate_num=?, certificate_type=?,real_name=? where user_no=?";
		Object[] params1;
		if (dto.getState() == 4) {
			params1 = new Object[] { dto.getCardId(), dto.getCardType(), dto.getTrueName(), dto.getUserNo() };
		} else {
			params1 = new Object[] { "", "", "", dto.getUserNo() };
		}
		queryRunner.update(sql1, params1);
	}
}
