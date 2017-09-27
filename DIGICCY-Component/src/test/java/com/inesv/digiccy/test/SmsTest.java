package com.inesv.digiccy.test;

import com.aliyuncs.exceptions.ClientException;
import com.inesv.digiccy.util.SmsUtil;

public class SmsTest {
	public static void main(String[] args) {
		try {
			SmsUtil.sendMySms("15140966354", "123456");
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
