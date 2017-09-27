package com.inesv.digiccy.lkl.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.aliyuncs.exceptions.ClientException;
import com.inesv.digiccy.util.SmsUtil;

public class MyTest {
	public static void main(String[] args) throws UnknownHostException, IOException {
		sendFile();
	}
	public static String getRootPath() {
		MyTest t = new MyTest();
		String classPath = MyTest.class.getClassLoader().getResource("/").getPath();
		String rootPath = "";
		// windows下
		if ("\\".equals(File.separator)) {
			rootPath = classPath.substring(1, classPath.indexOf("/WEB-INF/classes"));
			rootPath = rootPath.replace("/", "\\");
		}
		// linux下
//		if ("/".equals(File.separator)) {
//			rootPath = classPath.substring(0, classPath.indexOf("/WEB-INF/classes"));
//			rootPath = rootPath.replace("\\", "/");
//		}
		return rootPath;
	}
	public static void sendFile() throws UnknownHostException, IOException {
		Socket socket = new Socket("172.16.2.112", 12241);
		OutputStream oos = socket.getOutputStream(); 
		FileInputStream fis = new FileInputStream(new File("D:\\a.rar"));
		byte[] b = new byte[1024];
		int len;
		while((len = fis.read(b))!=-1) {
			oos.write(b,0,len);
			oos.flush();
		}
		oos.close();
		fis.close();
	}
}
