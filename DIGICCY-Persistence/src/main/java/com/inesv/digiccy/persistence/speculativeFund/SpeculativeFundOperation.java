package com.inesv.digiccy.persistence.speculativeFund;

import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.SpeculativeFundDto;

@Component
public class SpeculativeFundOperation {
	@Autowired
	QueryRunner queryRunner;

	/**
	 * 新增交易记录
	 * 
	 * @return
	 */
	public void addSpeculativeFund(SpeculativeFundDto speculativeFundDto) throws Exception {

		String insertSpeculativeFund = "INSERT INTO t_inesv_deal_detail(user_no,coin_no,deal_type,deal_price,optimum_price,most_amount,"
				+ " percent,deal_num,sum_price,poundage,date,attr1,attr2) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object params[] = { speculativeFundDto.getUser_no(), speculativeFundDto.getCoin_no(),
				speculativeFundDto.getDeal_type(), speculativeFundDto.getDeal_price(),
				speculativeFundDto.getOptimum_price(), speculativeFundDto.getMost_amount(),
				speculativeFundDto.getPercent(), speculativeFundDto.getDeal_num(), speculativeFundDto.getSum_price(),
				speculativeFundDto.getPoundage(), speculativeFundDto.getDate(), speculativeFundDto.getAttr1(),
				speculativeFundDto.getAttr2() };
		queryRunner.update(insertSpeculativeFund, params);
	}
}
