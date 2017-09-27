package lkl.test;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class MyTest {
	public static void main(String[] args) throws Exception {
		for (int k = 0; k < 100; k++) {
			String p = "phone=" + 12345678+(k+"") + "&password=123456&invite_num=EVXTFI";
			HttpURLConnection conn = (HttpURLConnection) new URL("http://localhost:8081/reg/addUser.do").openConnection();
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			OutputStreamWriter oos = new OutputStreamWriter(conn.getOutputStream());
			oos.write(p);
			oos.flush();
			InputStream ips = conn.getInputStream();
			StringBuffer sb = new StringBuffer();

			byte[] bts = new byte[1024];
			int len;
			while ((len = ips.read(bts)) != -1) {
				sb.append(new String(bts, 0, len));
			}
			System.out.println(sb.toString()+"....."+k);
		}
	}
}
