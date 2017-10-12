package com.inesv.digiccy.util;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 网络请求工具类
 * 
 * @author lumia
 *
 */
public class HttpUtil {
	/**
	 * post请求数据
	 * 
	 * @param url
	 * @param param
	 * @return
	 */
	public static String postData(String url, String param) {
		StringBuffer sb = new StringBuffer();
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			OutputStreamWriter oos = new OutputStreamWriter(conn.getOutputStream());
			oos.write(param);
			oos.flush();
			InputStream ips = conn.getInputStream();
			byte[] bts = new byte[1024];
			int len;
			while ((len = ips.read(bts)) != -1) {
				sb.append(new String(bts, 0, len, "UTF-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
