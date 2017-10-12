package com.inesv.digiccy.util.excel.resultutils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BalanceResultUtil implements ResultUtil{
	private JSONArray redisResult;
	public BalanceResultUtil(String redisResult) throws JSONException{
		redisResult = redisResult==null?"[]":redisResult;
		this.redisResult = new JSONArray(redisResult);
	}
	@Override
	public Map<String, Object> getMapResult() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		int successCount = 0;
		int errorCount = 0;
		int total = 0;
		ArrayList<String> successPhones = new ArrayList<>();
		ArrayList<String> errorPhones = new ArrayList<>();
		for(int k = 0; k < redisResult.length(); k ++) {
			Object job = redisResult.get(k);
			if(!(job instanceof JSONObject)) {
				continue;
			}
			JSONObject jonObj = (JSONObject) job;
			String phone = jonObj.getString("phone");
			boolean ok = jonObj.getBoolean("executeOk");
			String result = jonObj.getString("result");
			if(ok) {
				successPhones.add(phone);
				successCount ++;
			}else {
				errorPhones.add(phone);
				errorCount ++;
			}
			total ++;
		}
		map.put("successCount", successCount);
		map.put("errorCount", errorCount);
		map.put("successList", successPhones);
		map.put("errorList", errorPhones);
		map.put("total", total);
		return map;
	}

	@Override
	public Map<String, List<String>> getExcelResult() throws Exception {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		String title1 = "电话号码";
		String title2 = "添加是否成功";
		String title3 = "结果备注";
		List<String> value1 = new ArrayList<String>();
		List<String> value2 = new ArrayList<String>();
		List<String> value3 = new ArrayList<String>();
		for(int k = 0; k < redisResult.length(); k ++) {
			Object job = redisResult.get(k);
			if(!(job instanceof JSONObject)) {
				continue;
			}
			JSONObject jonObj = (JSONObject) job;
			String phone = jonObj.getString("phone");
			boolean ok = jonObj.getBoolean("executeOk");
			String result = jonObj.getString("result");
			value1.add(phone);
			value2.add((ok==true?"成功":"失败"));
			value3.add(result);
		}
		map.put(title1, value1);
		map.put(title2, value2);
		map.put(title3, value3);
		return map;
	}

}
