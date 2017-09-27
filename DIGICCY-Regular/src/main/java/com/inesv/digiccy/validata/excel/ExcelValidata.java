package com.inesv.digiccy.validata.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.inesv.digiccy.util.excel.ExcelTypes;
import com.inesv.digiccy.util.excel.ExcelUtils;
import com.inesv.digiccy.util.excel.contentutils.BalanceExcelUtil;
import com.inesv.digiccy.util.excel.contentutils.ExcelUtil;
import com.inesv.digiccy.util.excel.contentutils.RegExcelUtil;
import com.inesv.digiccy.util.excel.resultutils.ResultUtil;
import com.inesv.digiccy.util.excel.utilfactor.ExcelUtilFactory;

@Component
public class ExcelValidata {
	
	@Autowired
	ExcelUtilFactory utilFactory;
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	//获取结果
	public Map<String, Object> getResult(String fileType, String userName) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		ResultUtil resultUtil = utilFactory.getResultExcelUtil(fileType, userName);
		
		if(resultUtil != null) {
			Map<String, Object> m = resultUtil.getMapResult();
			map.putAll(m);
			map.put("code", "success");
		}else {
			map.put("code", "error");
		}
		return map;
	}
	//获取excel结果
	public Map<String, List<String>> getExcelResult(String fileType, String userName) throws Exception{
		Map<String, List<String>> result = new HashMap<String, List<String>>();
		ResultUtil resultUtil = utilFactory.getResultExcelUtil(fileType, userName);
		Map<String, List<String>> map = resultUtil.getExcelResult();
		if(map != null) {
			result.putAll(map);
			return result;
		}else {
			ArrayList<String> al = new ArrayList<>();
			al.add("获取结果失败");
			result.put("错误信息", al);
			return result;
		}
	}
	//检查
	public Map<String, Object> checkExcel(MultipartFile excelFile, String fileType) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		//检查excel不需要传用户名称
		ExcelUtil util = utilFactory.getExcelUtil(fileType, excelFile, "");
		
			switch (fileType) {
			case ExcelTypes.regExcelType:
				map = checkReg(util, excelFile);
				break;
			case ExcelTypes.balanceType:
				map = checkBalance(util, excelFile);
				break;
			}

		
		return map;
	}
	
	//检查入金
	private Map<String, Object> checkBalance(ExcelUtil util, MultipartFile excelFile) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		//判断某列是否有重复的
		Map<String, Object> map1 = ExcelUtils.getRepeatAndNull(excelFile,"入金人电话");
		if ("success".equals(map1.get("msg"))) {
			BalanceExcelUtil excelUtil = (BalanceExcelUtil) util;
			ArrayList<String> message = excelUtil.getMessage();
			map.put("msg", message);
			map.put("canBalance", excelUtil.canExecute());
			map.put("toMuchGold", excelUtil.toMuchGold());
			map.put("goldCode", excelUtil.getGoldCode());
		}else {
			//有重复的用户
			map.put("msg", "fail");
		}
		return map;
	}
	//检查注册
	private Map<String, Object> checkReg(ExcelUtil util, MultipartFile excelFile) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		//判断某列是否有重复的
		Map<String, Object> map1 = ExcelUtils.getRepeatAndNull(excelFile,"注册人电话");
		if ("success".equals(map1.get("msg"))) {
			RegExcelUtil excelUtil = (RegExcelUtil) util;
			ArrayList<String> k = excelUtil.getMessage();
			map.put("msg", k);
			map.put("canReg", excelUtil.canExecute());
			map.put("canAddAddress", excelUtil.canExecuteAddress());
			map.put("curReg", excelUtil.getCurReg());
			map.put("totalReg", excelUtil.getTotalReg());
		} else {
			map.put("msg", "fail");
			map.put("nullCount", map1.get("nullCount"));
			map.put("repeat", map1.get("cfList"));
			map.put("canReg", false);
			map.put("canAddAddress", false);
		}
		return map;
	}
	//执行
	public Map<String, Object> execute(String fileType, MultipartFile excelFile, final String userName, final boolean addAddress) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ExcelUtil util = utilFactory.getExcelUtil(fileType, excelFile,userName);
		String key = userName+"_"+fileType;
		redisTemplate.delete(key);
		switch (fileType) {
		case ExcelTypes.regExcelType:
			RegExcelUtil excelUtil = (RegExcelUtil) util;
			// 处理的个数
			int k = excelUtil.execute(addAddress);
			map.put("executeSize", k);
			break;
		case ExcelTypes.balanceType:
			BalanceExcelUtil balanceExcelUtil = (BalanceExcelUtil) util;
			int k1 = balanceExcelUtil.execute("");
			map.put("executeSize", k1);
			break;
		}

		return map;
	}
}
