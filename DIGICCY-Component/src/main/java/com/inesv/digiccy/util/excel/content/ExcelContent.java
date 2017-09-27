package com.inesv.digiccy.util.excel.content;

import java.util.List;
import java.util.Map;

public interface ExcelContent {
	//获取所有列标题
	List<String> getCols();
	//获取某一列的内容
	List<String> getColContent(String title);
	//以两列分别为键和值转为map
	Map<String, String> toMap(String keyCol, String valueCol);
	//检查内容
	String checkContent();
}
