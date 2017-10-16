package com.inesv.digiccy.query;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.UserVoucherDto;

/**
 * 
 * @author Liukeling
 *
 */
@Component
public class QueryUserVoucher {

	private static Logger logger = LoggerFactory.getLogger(QueryUserVoucher.class);
	@Autowired
	private QueryRunner queryRunner;
	
	//查询认证记录
	public UserVoucherDto findByUserNo(int userNo) {
		String sql = "select voucher_id as id, voucher_cardid as cardId, voucher_type as cardType,voucher_state as state, realName as trueName, id_startdate as startDate, id_enddate as enDateDate from t_inesv_user_voucher where userNo=?";
		Object[] params = { userNo };
		UserVoucherDto dto = null;
		try {
			dto = queryRunner.query(sql, new BeanHandler<UserVoucherDto>(UserVoucherDto.class), params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
}
