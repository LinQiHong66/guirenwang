package com.inesv.digiccy.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.inesv.digiccy.dto.InesvUserDto;

import jxl.Cell;
import jxl.Sheet;
import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelUtils {
	//下载文件
	public final static void getExcel(HttpServletResponse response, String filePath) throws IOException {
		if (filePath != null && !"".equals(filePath)) {
			File exportFile = new File(filePath);

			String fileName = exportFile.getName();
			response.setContentType("application/x-" + fileName.substring(fileName.lastIndexOf(".") + 1));
			response.setCharacterEncoding("UTF-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);// excel文件名
			OutputStream oos = response.getOutputStream();
			FileInputStream fis = new FileInputStream(exportFile);
			byte[] b = new byte[10240];
			int len;
			while ((len = fis.read(b)) != -1) {
				oos.write(b, 0, len);
				oos.flush();
			}
			fis.close();
			oos.close();
		}
	}
	
	// 导出excel
	public final static void export(HttpServletResponse response, Map<String, List<String>>... contacts) {
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

			for (int i = 0; i < contacts.length; i++) {
				Map<String, List<String>> contact = contacts[i];
				WritableSheet sheet = null;
				SheetSettings settings = null;
				// 2.创建sheet并设置冻结前两行
				sheet = book.createSheet("data" + i, i);
				settings = sheet.getSettings();
				settings.setVerticalFreeze(1);
				Set<String> keys = contact.keySet();
				int col = 0;
				for (String key : keys) {
					List<String> values = contact.get(key);
					int row = 0;
					sheet.addCell(new Label(col, row, key, wf));
					for (String value : values) {
						row++;
						sheet.addCell(new Label(col, row, value, wf));
					}
					col++;
				}
			}
			response.flushBuffer();
			// 6.写入excel并关闭
			book.write();
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//获取重复或者空的列
	public static Map<String, Object> getRepeatAndNull(MultipartFile excelFile, String colTitle) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		//根据文件获取workbook对象
		Workbook book = Workbook.getWorkbook(excelFile.getInputStream());
		//获取工作表
		Sheet[] sheets = book.getSheets();
		if(sheets != null && sheets.length > 0) {
			Sheet shet = sheets[0];
			int k = shet.getColumns();
			int pCol = -1;
			for(int i = 0; i < k; i ++) {
				String title = shet.getCell(i, 0).getContents();
				if(colTitle.equals(title)) {
					pCol = i;
					break;
				}
			}
			if(pCol != -1) {
				List<String> chongfu = new ArrayList<String>();
				int nullCount = 0;
				int rows = shet.getRows();
				for(int i = 0; i < rows; i ++) {
					for(int j = 0; j < rows; j ++) {
						if(i != j) {
							String con1 = shet.getCell(pCol, i).getContents();
							String con2 = shet.getCell(pCol, j).getContents();
							if(con1 == null || "".equals(con1)) {
								nullCount ++;
							}
							if(con1 != null && !"".equals(con1) && con1.equals(con2)) {
								chongfu.add(con1);
							}
						}
					}
				}
				if(chongfu.size() == 0 && nullCount == 0) {
					map.put("msg", "success");
				}else {
					map.put("msg", "fail");
					map.put("nullCount", nullCount);
					map.put("cfList", chongfu);
				}
			}
		}else {
			map.put("msg", "error");
		}
		return map;
	}
	//将excel解析成map 只解析第一张工作表
	public static Map<String, List<String>> getExcelContent(MultipartFile excelFile) throws Exception{
		Map<String, List<String>> content = new HashMap<String, List<String>>();
		if(excelFile == null) {
			return content;
		}
		//根据文件获取workbook对象
		Workbook book = Workbook.getWorkbook(excelFile.getInputStream());
		//获取工作表
		Sheet[] sheets = book.getSheets();
		if(sheets.length <= 0) {
			return content;
		}
		Sheet shet = sheets[0];
		int cols = shet.getColumns();
		int rows = shet.getRows();
		for(int c = 0; c < cols; c++) {
			String title = "";
			List<String> cs = new ArrayList<String>();
			for(int r = 0; r < rows; r ++) {
				if(r == 0) {
					title = shet.getCell(c, r).getContents();
				}else {
					cs.add(shet.getCell(c, r).getContents());
				}
			}
			content.put(title, cs);
		}
		return content;
	}
}
