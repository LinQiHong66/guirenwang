package com.inesv.digiccy.util.excel.resultutils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.util.excel.contentutils.ExcelBatch;

public class RegResultUtil implements ResultUtil{
	private JSONArray redisResult;
	private ExcelBatch excelBatch;
	public RegResultUtil(String redisResult, ExcelBatch excelBatch) throws JSONException{
		this.excelBatch = excelBatch;
		this.redisResult = new JSONArray(redisResult);
	}
	@Override
	public Map<String, Object> getMapResult() throws JSONException {
		Map<String, Object> map = new HashMap<String, Object>();

		//成功注册的个数
		int successCount = 0;
		//失败注册的个数
		int errorCount = 0;
		int addressOk = 0;
		ArrayList<String> errorPhone = new ArrayList<>();
		ArrayList<String> phones = new ArrayList<>();
		//已注册的电话
		ArrayList<String> regsPhone = new ArrayList<>();
		ArrayList<String> addAddress = new ArrayList<>();
		
		for(int k = 0; k < redisResult.length(); k ++) {
			Object ojob = redisResult.get(k);
			if(!(ojob instanceof JSONObject)) {
				continue;
			}
			JSONObject job = (JSONObject)ojob;
			Object code = job.get("code");
			System.out.println(job);
			System.out.println("--------job-----"+code);
			if(code != null) {
				int c = Integer.parseInt(code.toString());
				phones.add(job.get("phone")+"");
				if(c == 100) {
					successCount++;
					
				}else if(c == 300) {
					regsPhone.add(job.get("phone")+"");
				}else {
					errorCount ++;
					errorPhone.add(job.get("phone")+" : "+job.get("desc"));
					
				}
				addAddress.add(job.get("phone")+":"+(job.getBoolean("addressAdd")==true?"添加地址成功":"添加地址失败"));
				if(job.getBoolean("addressAdd")) {
					addressOk++;
				}
			}else {
				errorCount ++;
				
				errorPhone.add(job.get("phone")+"");
			}
		}
		
		map.put("successCount", successCount);
		map.put("errorCount", errorCount);
		map.put("errList", errorPhone);
		map.put("addressAdd", addAddress);
		map.put("addressCount", addressOk);
		map.put("regPhones", regsPhone);
		map.put("AllList", phones);
		return map;
	}
	@Override
	public Map<String, List<String>> getExcelResult() throws Exception {
		Map<String, List<String>> map = new HashMap<String, List<String>>();

		//成功注册的个数
		int successCount = 0;
		//失败注册的个数
		int errorCount = 0;
		int addressOk = 0;
		ArrayList<String> errorPhone = new ArrayList<>();
		ArrayList<String> phones = new ArrayList<>();
		//已注册的电话
		ArrayList<String> regsPhone = new ArrayList<>();
		ArrayList<String> codes = new ArrayList<>();
		ArrayList<String> parentcodes = new ArrayList<>();
		ArrayList<String> addAddress = new ArrayList<>();
		for(int k = 0; k < redisResult.length(); k ++) {
			Object ojob = redisResult.get(k);
			if(!(ojob instanceof JSONObject)) {
				continue;
			}
			JSONObject job = (JSONObject)ojob;
			Object code = job.get("code");
			System.out.println(job);
			System.out.println("--------job-----"+code);
			if(code != null) {
				int c = Integer.parseInt(code.toString());
				phones.add(job.get("phone")+"");
				if(c == 100) {
					//注册成功
					successCount++;
					regsPhone.add(job.get("phone")+"");
					InesvUserDto userCode = excelBatch.getCode(job.get("phone")+"");
					codes.add(userCode.getOrg_code());
					parentcodes.add(userCode.getOrg_parent_code());
				}else if(c == 300) {
					//已注册，只添加地址
					
				}else {
					//注册失败
					errorCount ++;
					errorPhone.add(job.get("phone")+" : "+job.get("desc"));
					
				}
				addAddress.add(job.get("phone")+":"+(job.getBoolean("addressAdd")==true?"添加地址成功":"添加地址失败"));
				if(job.getBoolean("addressAdd")) {
					addressOk++;
				}
			}else {
				errorCount ++;
				
				errorPhone.add(job.get("phone")+"");
			}
		}
		ArrayList<String> errCountList = new ArrayList<>();
		ArrayList<String> successCountList = new ArrayList<>();
		ArrayList<String> addressOkList = new ArrayList<>();
		errCountList.add(errorCount+"");
		successCountList.add(successCount+"");
		addressOkList.add(addressOk+"");
		map.put("所有电话", phones);
		map.put("注册成功的电话", regsPhone);
		map.put("注册成功电话的机构编号", codes);
		map.put("注册成功电话的上级机构编号", parentcodes);
		map.put("添加地址成功个数", addressOkList);
		map.put("注册失败的电话", errorPhone);
		map.put("注册成功总数", successCountList);
		map.put("注册失败总数", errCountList);
		return map;
	}

}
