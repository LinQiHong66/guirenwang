package com.inesv.digiccy.util.excel.contentutils;


import com.inesv.digiccy.util.thread.TaskListner;

public interface ExcelUtil {
	boolean canExecute();
	int execute(final Object params) throws Exception;
	Object getMessage();
}
