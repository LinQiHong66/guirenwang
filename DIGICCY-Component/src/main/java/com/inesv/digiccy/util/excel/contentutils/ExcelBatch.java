package com.inesv.digiccy.util.excel.contentutils;

import java.sql.SQLException;

import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.dto.StaticParamsDto;

public interface ExcelBatch {
	boolean addBalance(String phone, int money)throws Exception;
	boolean isReg(String phone)throws Exception;
	StaticParamsDto getParam(String key) throws SQLException;
	void updateBatchGload(int money) throws Exception;
	InesvUserDto getCode(String phone) throws Exception;
}
