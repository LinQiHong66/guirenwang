package com.inesv.digiccy.util.excel.resultutils;

import java.util.List;
import java.util.Map;

public interface ResultUtil {
	Map<String, Object> getMapResult() throws Exception;
	Map<String, List<String>> getExcelResult() throws Exception;
}
