package com.inesv.digiccy.util;

import com.google.gson.Gson;

/**
 * Created by chan on 2016/11/23.
 * <p>
 * json解析生成工具类
 */

public class JsonUtil {

	// 解析json
	public static <T> T jsonParseToBean(String jsonString, Class<T> clazz) {
		if (StringUtil.isEmpty(jsonString)) {
			return null;
		}
		Gson gson = new Gson();
		T t = null;
		try {
			t = gson.fromJson(jsonString, clazz);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			return t;
		}
	}
}
