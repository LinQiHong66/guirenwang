package com.inesv.digiccy.validata.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import com.inesv.digiccy.dto.InesvUserDto;

import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelUtils {
	public final static void export(HttpServletResponse response,Map<String, List<String>>... contacts) {
		// 文件名
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = sdf.format(new Date()) + ".xls";

		response.setContentType("application/x-excel");
		response.setCharacterEncoding("UTF-8");
		response.addHeader("Content-Disposition", "attachment;filename=" + fileName);// excel文件名

		try {
			// 1.创建excel文件
			WritableWorkbook book = Workbook.createWorkbook(response.getOutputStream());
			// 居中
			WritableCellFormat wf = new jxl.write.WritableCellFormat();
			wf.setAlignment(Alignment.CENTRE);

			for (int i = 0; i < contacts.length; i ++) {
				Map<String, List<String>> contact = contacts[i];
				WritableSheet sheet = null;
				SheetSettings settings = null;
				// 2.创建sheet并设置冻结前两行
				sheet = book.createSheet("data"+i, i);
				settings = sheet.getSettings();
				settings.setVerticalFreeze(1);
				Set<String> keys = contact.keySet();
				int col = 0;
				for(String key : keys){
					List<String> values = contact.get(key);
					int row = 0;
					sheet.addCell(new Label(col, row, key, wf));
					for(String value : values){
						row++;
						sheet.addCell(new Label(col, row, value, wf));
					}
					col++;
				}
				// 3.添加第一行及第二行标题数据
//				sheet.addCell(new Label(0, 0, "名称", wf));
//				sheet.addCell(new Label(1, 0, itemList.get(i).getPhone(), wf));
//				sheet.addCell(new Label(2, 0, "ID", wf));
//				sheet.addCell(new Label(3, 0, itemList.get(i).getId() + "", wf));
//				sheet.addCell(new Label(0, 1, "时间", wf));
//				sheet.addCell(new Label(1, 1, "值", wf));
				// 4.历史数据，业务数据，不用关注
				// List<HisData> hisList = new ArrayList<HisData>();
				// if (hisList != null && hisList.size() > 0) {
				//
				//
				//
				// // 5.将历史数据添加到单元格中
				// for (int j = 0; j < hisList.size(); j++) {
				// sheet.addCell(new Label(0, j + 2, hisList.get(j)
				// .getTime() + "", wf));
				// sheet.addCell(new Label(1, j + 2, hisList.get(j)
				// .getValue() + "", wf));
				// }
				// }
			}
			response.flushBuffer();
			// 6.写入excel并关闭
			book.write();
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
