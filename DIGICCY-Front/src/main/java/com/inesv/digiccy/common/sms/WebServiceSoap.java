package com.inesv.digiccy.common.sms;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inesv.digiccy.api.utils.PropertiesUtil;

public class WebServiceSoap {

    /**
     * 短信备用接口
     */
    private static final String SDK2   = PropertiesUtil.getPropertiesValue("frontKey.properties", "SDK2");

    /**
     * 短信主接口
     */
    private static final String SDK    = PropertiesUtil.getPropertiesValue("frontKey.properties", "SDK");

    private final Logger        logger = LoggerFactory.getLogger(WebServiceSoap.class);


    private WebServiceSoap() {}


    private static final WebServiceSoap WEB_SERVICE_SOAP = new WebServiceSoap();


    //静态工厂方法   
    public static WebServiceSoap getInstance() {
        return WEB_SERVICE_SOAP;
    }

    /**
     * 发送短信
     * @param sn 序列号
     * @param pwd 密码
     * @param mobile 手机号
     * @param content 短信内容
     * @return 返回接口代码
     */
    public String sendSMS(String sn, String password, String mobile, String content) {
        return sendSMSEx(sn, password, mobile, content, "");
    }

    /**
     * 发送短信(加扩展码)
     * @param sn 序列号
     * @param password 密码
     * @param mobile 手机号
     * @param content 短信内容
     * @param ext 扩展码
     * @return 返回接口代码
     */
    public String sendSMSEx(String sn, String password, String mobile, String content, String ext) {
        String result = "";
        try {
            password = getMD5(sn + password);
            content = URLEncoder.encode(content, "utf8");
            result = mdsmssendSdk2OrSdk(sn, password, mobile, content, ext, "", "", "");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 方法名称：getMD5
     * 功 能：字符串MD5加密
     * 参 数：待转换字符串
     * 返 回 值：加密之后字符串
     */
    private String getMD5(String sourceStr) {
        String resultStr = "";
        try {
            byte[] temp = sourceStr.getBytes();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(temp);
            byte[] b = md5.digest();
            for (int i = 0; i < b.length; i++) {
                char[] digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
                char[] ob = new char[2];
                ob[0] = digit[(b[i] >>> 4) & 0X0F];
                ob[1] = digit[b[i] & 0X0F];
                resultStr += new String(ob);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            resultStr = null;
        }
        return resultStr;
    }

    /**
     * 使用sdk2发送短信，失败后使用sdk发送（主要是链接超时）
     * @param sn 序列号
     * @param pwd 密码
     * @param mobile 手机号
     * @param content 内容
     * @param ext 扩展码
     * @param stime 定时时间
     * @param rrid 唯一标识
     * @param msgfmt 内容编码
     * @return 唯一标识，如果不填写rrid将返回系统生成的
     */
    private String mdsmssendSdk2OrSdk(String sn, String password, String mobile, String content, String ext, String stime, String rrid, String msgfmt) {
        String result = "";
        try {
            result = mdsmssend(SDK2, sn, password, mobile, content, ext, stime, rrid, msgfmt);
            logger.info(mobile + "调用短信接口成功返回码：" + result);
        } catch (Exception e) {
            logger.info(mobile + "调用短信接口失败：" + SDK2, e);
            try {
                logger.info(mobile + "开始调用备用短信接口：" + SDK);
                // 备用接口调用
                result = mdsmssend(SDK, sn, password, mobile, content, ext, stime, rrid, msgfmt);
                logger.info(mobile + "调用备用短信接口成功返回码：" + result);
            } catch (Exception e1) {
                logger.info(mobile + "调用备用短信接口失败：" + SDK, e1);
            }
        }
        return result;
    }

    /**
     * 发送短信
     * @param sdkUrl 短信接口
     * @param sn 序列号
     * @param pwd 密码
     * @param mobile 手机号
     * @param content 内容
     * @param ext 扩展码
     * @param stime 定时时间
     * @param rrid 唯一标识
     * @param msgfmt 内容编码
     * @return 唯一标识，如果不填写rrid将返回系统生成的
     * @throws Exception
     */
    private String mdsmssend(String sdkUrl, String sn, String password, String mobile, String content, String ext, String stime, String rrid, String msgfmt) throws Exception {
        // 返回结果
        String result = "";
        HttpURLConnection httpURLConnection = null;
        OutputStream os = null;
        InputStreamReader isr = null;
        BufferedReader in = null;
        // 设置参数
        String soapAction = PropertiesUtil.getPropertiesValue("frontKey.properties", "soapAction");
        StringBuffer mdsmssendXml = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        mdsmssendXml
                .append("<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
        mdsmssendXml.append("<soap:Body>");
        mdsmssendXml.append("<mdsmssend  xmlns=\"http://entinfo.cn/\">");
        mdsmssendXml.append("<sn>").append(sn).append("</sn>");
        mdsmssendXml.append("<pwd>").append(password).append("</pwd>");
        mdsmssendXml.append("<mobile>").append(mobile).append("</mobile>");
        mdsmssendXml.append("<content>").append(content).append("</content>");
        mdsmssendXml.append("<ext>").append(ext).append("</ext>");
        mdsmssendXml.append("<stime>").append(stime).append("</stime>");
        mdsmssendXml.append("<rrid>").append(rrid).append("</rrid>");
        mdsmssendXml.append("<msgfmt>").append(msgfmt).append("</msgfmt>");
        mdsmssendXml.append("</mdsmssend>");
        mdsmssendXml.append("</soap:Body>");
        mdsmssendXml.append("</soap:Envelope>");
        try {
            URL url = new URL(sdkUrl);// 创建url
            httpURLConnection = (HttpURLConnection) (url.openConnection());// 打开http连接
            // 写参数
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            bout.write(mdsmssendXml.toString().getBytes());
            byte[] b = bout.toByteArray();
            // 设置http连接属性
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(b.length));
            httpURLConnection.setRequestProperty("Content-Type", "text/xml; charset=gb2312");
            httpURLConnection.setRequestProperty("SOAPAction", soapAction);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            // 建立连接
            httpURLConnection.connect();
            // 发送请求数据
            os = httpURLConnection.getOutputStream();
            os.write(b);
            os.close();
            // 接收返回数据
            isr = new InputStreamReader(httpURLConnection.getInputStream());
            in = new BufferedReader(isr);
            String inputLine;
            Pattern pattern = Pattern.compile("<mdsmssendResult>(.*)</mdsmssendResult>");
            Matcher matcher = null;
            while (null != (inputLine = in.readLine())) {
                matcher = pattern.matcher(inputLine);
                while (matcher.find()) {
                    result = matcher.group(1);
                }
            }
            in.close();
            isr.close();
            httpURLConnection.disconnect();
            return result;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (in != null) {
                    os.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}