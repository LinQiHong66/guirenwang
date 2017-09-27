package com.inesv.digiccy.util.excel.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.inesv.digiccy.util.excel.ExcelTypes;

public class BalanceExcelContent implements ExcelContent{
	private final Map<String, List<String>> content = new HashMap<String, List<String>>();
	private boolean hasCols = true;
	public BalanceExcelContent(Map<String, List<String>> content) {

		String[] allCols = ExcelTypes.getColNames(ExcelTypes.balanceType);
		//存放需要的列
		if(content != null) {
			for(String col : allCols) {
				List<String> val = content.get(col);
				if(val != null) {
					this.content.put(col, val);
				}
			}
		}
		List<String> cols = getCols();
		//判断是否包含必须列
		for(String mastCol : allCols) {
			boolean hasCur = false;
			for(String curCol : cols) {
				if(mastCol.equals(curCol)) {
					hasCur = true;
					break;
				}
			}
			if(!hasCur) {
				hasCols = false;
				break;
			}
		}
	}
	//获取列
	@Override
	public List<String> getCols() {
		Set<String> set = content.keySet();
		Iterator<String> it = set.iterator();
		List<String> cols = new ArrayList<String>();
		while(it.hasNext()) {
			cols.add(it.next());
		}
		return cols;
	}
	//获取某个列的数据
	@Override
	public List<String> getColContent(String title) {
		return content.get(title);
	}
	//根据两个列获取一个map
	@Override
	public Map<String, String> toMap(String keyCol, String valueCol) {
		Map<String, String> map = new HashMap<String, String>();
		List<String> keys = content.get(keyCol);
		List<String> values = content.get(valueCol);
		int k = keys.size();
		if(k < values.size()) {
			k = values.size();
		}
		for(int i = 0; i < k; i++) {
			map.put(keys.get(i), values.get(i));
		}
		return map;
	}
	//简单的判断
	@Override
	public String checkContent() {
		if(content == null || content.size() == 0 || hasCols == false) {
			return "缺少列";
		}
		return "success";
	}

}
