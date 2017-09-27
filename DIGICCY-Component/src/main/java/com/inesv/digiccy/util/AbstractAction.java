package com.inesv.digiccy.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartFile;

import com.inesv.digiccy.util.UploadFileUtil;

public abstract class AbstractAction {
	/**
	 * 主要是进行批量数据处理的时候转换操作，在前端批量删除或更新的时候会传入一个ids的参数
	 * 参数的内容组成：“id|id|id...”
	 * @param request
	 * @return
	 */
	public Set<Integer> getBatchIds(HttpServletRequest request) {
		Set<Integer> set = new HashSet<Integer>() ;
		String ids = request.getParameter("ids") ;
		String result [] = ids.split("\\|") ;
		for (int x = 0 ; x < result.length ; x ++) {
			set.add(Integer.parseInt(result[x])) ;
		}
		return set ;
	}

	/**
	 * 根据指定的参数接收一组数据
	 * 
	 * @param request
	 * @param param
	 * @return
	 */
	public Set<Integer> getSetByInteger(HttpServletRequest request, String param) {
		Set<Integer> all = new HashSet<Integer>();
		String values[] = request.getParameterValues(param);
		for (int x = 0; x < values.length; x++) {
			if (values[x].matches("\\d+")) {
				all.add(Integer.parseInt(values[x]));
			}
		}
		return all;
	}

	/**
	 * 专门负责数据的输出，可以输出各种数据，主要用于Ajax处理上
	 * 
	 * @param response
	 * @param value
	 */
	public void print(HttpServletResponse response, Object value) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(value);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成图片名称
	 * 
	 * @param photo
	 * @return
	 */
	public String createFileName(MultipartFile photo) {
		if (photo.isEmpty()) { // 没有文件上传
			return "nophoto.gif"; // 默认没有文件
		} else { // 需要自己生成一个文件
			return UploadFileUtil.createFileName(photo.getContentType());
		}
	}

	/**
	 * 取得要操作数据的标记
	 * 
	 * @return
	 */
	public abstract String getType();

	/**
	 * 进行文件的保存处理
	 * 
	 * @param photo
	 */
	public boolean saveFile(MultipartFile photo, String fileName, HttpServletRequest request) { // 保存上传的图片名称
		if (!photo.isEmpty()) {
			String filePath = request.getServletContext().getRealPath(this.getFileUploadDir()) + fileName;
			try {
				return UploadFileUtil.save(photo.getInputStream(), new File(filePath));
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 保存上传文件路径
	 * 
	 * @return
	 */
	public abstract String getFileUploadDir();

	@Resource
	private MessageSource msgSource; // 表示此对象直接引用配置好的类对象（根据类型匹配）

	/**
	 * 根据指定的key的信息进行资源数据的读取控制
	 * 
	 * @param msgKey
	 *            表示要读取的资源文件的key的内容
	 * @return 表示资源对应的内容
	 */
	public String getValue(String msgKey, Object... args) {
		return this.msgSource.getMessage(msgKey, args, Locale.getDefault());
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) { // 方法名称自己随便写
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 本方法的处理指的是追加有一个自定义的转换编辑器，如果遇见的操作目标类型为java.util.Date类
		// 则使用定义好的SimpleDateFormat类来进行格式化处理，并且允许此参数的内容为空
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(sdf, true));
	}
}
