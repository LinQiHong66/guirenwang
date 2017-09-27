package common.autodoc.analyzer;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;


public class BytesFileUpload {
	
	
	public static String key="TG9uZzZLSE04T3ZMWVZMSGV4N1BTQkcw";
	
	public static void main(String []args) throws Exception{
		
		System.out.println(("123.jpg").getBytes());
		//FileUtils.copyFile(new FileInputStream(new File("E:\\images\\_file\\upload\\1.jpg")), "E:\\images\\_file\\resources/20150425/20150425162143244.jpg", true);
		//key 璇峰埌https://resources.sibu.cn/auth?type=1涓婂幓鍙�
		
		//浣犵殑鏈湴鏂囦欢鍚�
		 String filePath =new String("E:\\333333");
	        // 鎶婁竴涓櫘閫氬弬鏁板拰鏂囦欢涓婁紶缁欎笅闈㈣繖涓湴鍧� 鏄竴涓猻ervlet
	       
	        // 鎶婃枃浠惰浆鎹㈡垚娴佸璞ileBody
	        File f =new File(filePath);
	        
	        showAllFiles(f);
	        
	       // if(!f.exists()){
	       // 	System.out.println("not exist!!");
	       // }if(f.exists()){
	        //	System.out.println("exist!!");
	       // }
	       // 
	        
	       
	}
	
    public static void maint(String []args) throws Exception{
		
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(1000*7200+new Date().getTime())));
		
	}
	
	 
	/**
     * 涓婁紶鏂规硶
     * 杩斿洖涓婁紶瀹屾瘯鐨勬枃浠跺悕
     * *
     */
    public static String upload(File f,String key)
    {
        try 
        {
            //鏈嶅姟鍣↖P(杩欓噷鏄粠灞炴�ф枃浠朵腑璇诲彇鍑烘潵鐨�)
        	String hostip ="https://resources.sibu.cn/upload/file/save?uptype=1&key="+key;
        	//String hostip ="http://localhost/upload/file/save?key="+key;
        	// String hostip ="http://localhost/uploadServlet?key="+key;
            URL url = new URL(hostip);
            
            HttpURLConnection uc = (HttpURLConnection) url.openConnection();
            //涓婁紶鍥剧墖鐨勪竴浜涘弬鏁拌缃�
            uc
                    .setRequestProperty(
                            "Accept",
                            "image/gif,   image/x-xbitmap,   image/jpeg,   image/pjpeg,   application/vnd.ms-excel,   application/vnd.ms-powerpoint,   application/msword,   application/x-shockwave-flash,   application/x-quickviewplus,   */*");
            uc.setRequestProperty("Accept-Language", "zh-cn");
            uc.setRequestProperty("content-type", "text/html");
            uc.setRequestProperty("Accept-Encoding", "gzip,   deflate");
            uc
                    .setRequestProperty("User-Agent",
                            "Mozilla/4.0   (compatible;   MSIE   6.0;   Windows   NT   5.1)");
            uc.setRequestProperty("Connection", "Keep-Alive");
            uc.setDoOutput(true);
            uc.setUseCaches(true);
        
            //璇诲彇鏂囦欢娴�
            int size = (int) f.length();
            byte[] data = new byte[size];
            FileInputStream fis = new FileInputStream(f);
            OutputStream out = uc.getOutputStream();
            fis.read(data, 0, size);
            //鍐欏叆鏂囦欢鍚�
            out.write(f.getName().trim().getBytes("utf-8"));
            //鍐欏叆鍒嗛殧绗�
            out.write('|');
            //鍐欏叆鍥剧墖娴�
            out.write(data);
            //鍐欏叆鏂囦欢鍒嗛殧绗︼紙鐢ㄤ簬浼犲涓枃浠讹級
           // out.write('&');
            out.flush();
            out.close();
            fis.close();
            
            //璇诲彇鍝嶅簲鏁版嵁
            int code = uc.getResponseCode();
            String sCurrentLine = "";
            //瀛樻斁鍝嶅簲缁撴灉
            String sTotalString = "";
            if (code == 200)
            {
                java.io.InputStream is = uc.getInputStream();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(is));
                while ((sCurrentLine = reader.readLine()) != null)
                    if (sCurrentLine.length() > 0)
                        sTotalString = sTotalString + sCurrentLine.trim();
            }
            else
            {
                sTotalString = "杩滅▼鏈嶅姟鍣ㄨ繛鎺ュけ璐�,閿欒浠ｇ爜:" + code;
            }
//            System.out.println(sTotalString);
            
            
            
            JSONObject  obejct=JSONObject.parseObject(sTotalString);
            String str1=obejct.getString("data");
            String str2=JSONObject.parseObject(str1).getString("url");
            
//            System.out.println("'"+str2+"'");
            
            
            
             
            
            return str2;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    
    final static void showAllFiles(File dir) throws Exception{
    	  File[] fs = dir.listFiles();
    	  for(int i=0; i<fs.length; i++){
    		  if(fs[i].isDirectory()){
    			  System.out.println();
    			  System.out.println(fs[i].getAbsolutePath()+"下：");
    		  }
    		  
    		  if(!fs[i].isDirectory()){
    			  String path=fs[i].getAbsolutePath().replaceAll("\\\\", "\\\\\\\\");
    			 // System.out.println();
    			  File f =new File(path);
    			 String url= upload(f, key);
    			 System.out.print("'"+url+"',");  			 
    		  }
    	  
    	   if(fs[i].isDirectory()){
    	    try{
    	     showAllFiles(fs[i]);
    	    }catch(Exception e){}
    	   }
    	  }
    	 }
}

//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class BytesFileUpload {
//	
//	public static void main(String []args) throws Exception{
////		key=
//		System.out.println(("123.jpg").getBytes());
//		//FileUtils.copyFile(new FileInputStream(new File("E:\\images\\_file\\upload\\1.jpg")), "E:\\images\\_file\\resources/20150425/20150425162143244.jpg", true);
//		//key 请到https://resources.sibu.cn/auth?type=1上去取
//		String key="TG9uZ3d1VlpCeVRiYWNXa0JOSFBUWDFt";
//		//你的本地文件名
//		 String filePath =new String("E:\\333333\\load.jpg");
//	        // 把一个普通参数和文件上传给下面这个地址 是一个servlet
//	         
//	        // 把文件转换成流对象FileBody
//	        File f =new File(filePath);
//	        if(!f.exists()){
//	        	System.out.println("not exist!!");
//	        }if(f.exists()){
//	        	System.out.println("exist!!");
//	        }
//	        upload(f, key);
//	       
//	       
//	}
//	
//    public static void maint(String []args) throws Exception{
//		
//		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(1000*7200+new Date().getTime())));
//		
//	}
//	
//	
//	/**
//     * 上传方法
//     * 返回上传完毕的文件名
//     * *
//     */
//    public static String upload(File f,String key)
//    {
//        try
//        {
//            //服务器IP(这里是从属性文件中读取出来的)
//        	String hostip ="https://resources.sibu.cn/upload/file/save?uptype=1&key="+key;
//        	//String hostip ="http://localhost/upload/file/save?key="+key;
//        	// String hostip ="http://localhost/uploadServlet?key="+key;
//            URL url = new URL(hostip);
//            
//            HttpURLConnection uc = (HttpURLConnection) url.openConnection();
//            //上传图片的一些参数设置
//            uc
//                    .setRequestProperty(
//                            "Accept",
//                            "image/gif,   image/x-xbitmap,   image/jpeg,   image/pjpeg,   application/vnd.ms-excel,   application/vnd.ms-powerpoint,   application/msword,   application/x-shockwave-flash,   application/x-quickviewplus,   */*");
//            uc.setRequestProperty("Accept-Language", "zh-cn");
//            uc.setRequestProperty("content-type", "text/html");
//            uc.setRequestProperty("Accept-Encoding", "gzip,   deflate");
//            uc
//                    .setRequestProperty("User-Agent",
//                            "Mozilla/4.0   (compatible;   MSIE   6.0;   Windows   NT   5.1)");
//            uc.setRequestProperty("Connection", "Keep-Alive");
//            uc.setDoOutput(true);
//            uc.setUseCaches(true);
//        
//            //读取文件流
//            int size = (int) f.length();
//            byte[] data = new byte[size];
//            FileInputStream fis = new FileInputStream(f);
//            OutputStream out = uc.getOutputStream();
//            fis.read(data, 0, size);
//            //写入文件名
//            out.write(f.getName().trim().getBytes("utf-8"));
//            //写入分隔符
//            out.write('|');
//            //写入图片流
//            out.write(data);
//            //写入文件分隔符（用于传多个文件）
//           // out.write('&');
//            out.flush();
//            out.close();
//            fis.close();
//            
//            //读取响应数据
//            int code = uc.getResponseCode();
//            String sCurrentLine = "";
//            //存放响应结果
//            String sTotalString = "";
//            if (code == 200)
//            {
//                java.io.InputStream is = uc.getInputStream();
//                BufferedReader reader = new BufferedReader(
//                        new InputStreamReader(is));
//                while ((sCurrentLine = reader.readLine()) != null)
//                    if (sCurrentLine.length() > 0)
//                        sTotalString = sTotalString + sCurrentLine.trim();
//            }
//            else
//            {
//                sTotalString = "远程服务器连接失败,错误代码:" + code;
//            }
//            System.out.println(sTotalString);
//            return sTotalString;
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
