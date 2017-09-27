package com.inesv.digiccy.util.excel.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.inesv.digiccy.util.excel.ExcelTypes;

public class RegExcelContent implements ExcelContent{
	//注册的数据内容
	private final Map<String, List<String>> content = new HashMap<String, List<String>>();
	//是否有必须列
	private boolean hasMustCol = true;
	//是否有添加地址所需的列
	private boolean hasAddressCol = true;
	public RegExcelContent(Map<String, List<String>> content){

		//存放需要的键值对
		if(content != null) {
			for(String keys : ExcelTypes.getColNames(ExcelTypes.regExcelType)) {
				List<String> value = content.get(keys);
				if(value != null) {
					this.content.put(keys, value);
				}
			}
		}
		//查询列是否有
		List<String> cols = getCols();
		String[] cols1 = ExcelTypes.getColNames(ExcelTypes.regExcelType);
		//循环模板内的所有字段    用来判断模板的字段和上传文件的字段是否吻合
		for(String col : cols1) {
			//判断非必要字段是否存在
			if(col.equals("注册人名字") || col.equals("注册人地址")) {
				boolean contact = false;
				//循环上传文件的字段并与非必要字段进行匹配
				for(String col1 : cols) {
					if(col.equals(col1)) {
						contact = true;
						break;
					}
				}
				//如果不存在某个非必要字段则标记
				if(contact == false) {
					hasAddressCol = false;
					continue;
				}
				//如果是非必要字段直接过掉进行下一次循环
				continue;
			}
			//这里是必要字段的判断
			boolean contact = false;
			for(String col1 : cols) {
				if(col.equals(col1)) {
					contact = true;
					break;
				}
			}
			if(contact == false) {
				hasMustCol = false;
				break;
			}
		}
	}
	public boolean hasAddressCol() {
		return hasAddressCol;
	}
	@Override
	public List<String> getCols() {
		Set<String> keys = content.keySet();
		Iterator<String> it = keys.iterator();
		List<String> list = new ArrayList<String>();
		while(it.hasNext()) {
			list.add(it.next());
		}
		return list;
	}
	
	@Override
	public List<String> getColContent(String title) {
		return content.get(title);
	}

	@Override
	public Map<String, String> toMap(String keyCol, String valueCol) {
		List<String> keys = content.get(keyCol);
		List<String> values = content.get(valueCol);
		if(keys == null || values == null) {
			return null;
		}
		int length = keys.size();
		if(length < values.size()) {
			length = values.size();
		}
		Map<String, String> map = new HashMap<String, String>();
		for(int k = 0; k < length; k ++) {
			map.put(keys.get(k), values.get(k));
		}
		return map;
	}
	//基本检验
	@Override
	public String checkContent() {
		if(content == null || content.size() == 0 || hasMustCol == false) {
			return "缺少列";
		}
		return "success";
	}
}
