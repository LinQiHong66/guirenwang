package com.inesv.digiccy.util.excel.utilfactor;

import java.math.BigDecimal;

import org.apache.commons.dbutils.QueryRunner;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.inesv.digiccy.dto.StaticParamsDto;
import com.inesv.digiccy.util.excel.ExcelTypes;
import com.inesv.digiccy.util.excel.ExcelUtils;
import com.inesv.digiccy.util.excel.contentutils.BalanceExcelUtil;
import com.inesv.digiccy.util.excel.contentutils.ExcelBatch;
import com.inesv.digiccy.util.excel.contentutils.ExcelUtil;
import com.inesv.digiccy.util.excel.contentutils.RegExcelUtil;
import com.inesv.digiccy.util.excel.resultutils.BalanceResultUtil;
import com.inesv.digiccy.util.excel.resultutils.RegResultUtil;
import com.inesv.digiccy.util.excel.resultutils.ResultUtil;

@Component
public class ExcelUtilFactory {
	@Autowired
	QueryRunner queryRunner;
	@Autowired
	ExcelBatch batchAddBalance;
	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	public ExcelUtil getExcelUtil(String excelType, MultipartFile excelFile, String userName) throws Exception {
		ExcelUtil util = null;
		switch (excelType) {
		case ExcelTypes.regExcelType:
			util = new RegExcelUtil(ExcelUtils.getExcelContent(excelFile), queryRunner, redisTemplate, userName);
			break;
		case ExcelTypes.balanceType:
			StaticParamsDto dto = batchAddBalance.getParam("batchgold");
			BigDecimal maxGload = new BigDecimal(0);
			String goldCode = "has no code";
			if (dto != null) {
				maxGload = dto.getValue();
				goldCode = dto.getCode();
			}
			util = new BalanceExcelUtil(ExcelUtils.getExcelContent(excelFile), redisTemplate, userName, batchAddBalance,
					maxGload.intValue(), goldCode);
			break;
		}
		return util;
	}

	public ResultUtil getResultExcelUtil(String excelType, String userName) throws JSONException {
		String key = userName + "_" + excelType;

		System.out.println("getkey--------------");
		System.out.println(key);
		// 从redis获取结果json数据
		String val = (String) redisTemplate.opsForValue().get(key);
		System.out.println("getvalue-----------");
		System.out.println(val);
		switch (excelType) {
		case ExcelTypes.regExcelType:
			return new RegResultUtil(val, batchAddBalance);
		case ExcelTypes.balanceType:
			return new BalanceResultUtil(val);
		default:
			return null;
		}
	}
}
