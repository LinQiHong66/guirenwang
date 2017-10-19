package com.inesv.digiccy.grt;

 
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.inesv.digiccy.util.OrderIDGenerate;
import com.inesv.digiccy.util.thread.MyTask;
import com.inesv.digiccy.util.thread.TaskListner;
import com.inesv.digiccy.util.thread.ThreadUtil;
 

public class grtUtil {
	 //请求服务器
     public final static String host = "http://47.92.80.205:3002";   //47.92.80.205:3000  测试环境地址
     
     //请求服务器
     public final static String version = "v2";   //47.92.80.205:3000  测试环境地址
  
     
     //请求url
     public static String url = "";   
     
     
    
     /**
      * 1.1创建钱包
      * @param address ： 钱包地址
      * @return  
      */
     public  static Map<String,Object> createWallet() {
     	 Map<String,Object> resultMap = new HashMap<>();
     	 url = host + "/"+version+"/wallet/new";
          String result = HttpUtil.sendGet(url, null);
     	 resultMap = JSON.parseObject(result, Map.class);
     	 return resultMap;
     }
 
    /**
     * 1.2获取账户余额
     * @param address ：钱包地址
     * @param currency ： 指定返回对应货币的余额（可选，不选时传null）
     * @param counterparty : 指定返回对应银关发行的货币（可选，不选时传null）
     * @return
     */
    public  static Map<String,Object> getBalances(String address,String currency,String counterparty) {
    	 Map<String,Object> resultMap = new HashMap<>();
    	 url = host + "/"+version+"/accounts/" + address + "/balances";
    	 Map<String,String> params = new HashMap<>();
    	 if(currency!=null) {
    		params.put("currency",currency); 
    	 }
    	 if(currency!=null) {
     		params.put("counterparty",counterparty); 
     	 }
    	 
         String result = HttpUtil.sendGet(url, params);
    	 resultMap = JSON.parseObject(result, Map.class);
    	 return resultMap;
    }
    
  
    /**
     * 1.3获得两个账号余额
     * @param address1 ： 第一个钱包地址
     * @param address2 ： 第二个钱包地址
     * @return
     */
    public  static Map<String,Object> getDoubleAccountBalances(String address1,String address2) {
    	 Map<String,Object> resultMap = new HashMap<>();
    	 url = host + "/"+version+"/accounts/"+address1+"/"+address2+"/balances";
         String result = HttpUtil.sendGet(url, null);
    	 resultMap = JSON.parseObject(result, Map.class);
    	 return resultMap;
    }
    
    

    /**
     *  2.1支付请求
     * @param source_address ： 支付方钱包地址
     * @param destination ：收款方钱包地址
     * @param validated  : 是否等待支付结果
     * @param secret  ： 支付方的钱包私钥
     * @param client_id ： 此次请求的交易单号
     * @param value ： 支付数量
     * @param currency : 币种
     * @param memo : 备注
     * @return
     */
    public  static Map<String,Object> goPayRequest(String source_address,String destination , Boolean validated,String secret,String client_id,
    		                                     String value,String  currency,String memo)   {
    	 Map<String,Object> resultMap = new HashMap<>();
    	 url = host + "/"+version+"/accounts/"+source_address+"/payments";
 
    	 Map<String,Object> params = new HashMap<>();
    	 params.put("secret",secret);
    	 params.put("client_id",client_id);
    	 //组织支付对象
    	 Map<String,Object> payment = new HashMap<String,Object>();
    	 payment.put("source", source_address);
    	 payment.put("destination", destination);
    	 Map<String,Object> amount = new HashMap<>();
    	 amount.put("value", value);
    	 amount.put("currency", currency);
    	 amount.put("issuer", ""); //贵人通传空字符
    	 payment.put("amount", amount);  
    	 String[] memos = {memo};
         payment.put("memos", memos);
    	 params.put("payment", payment );
    	 
    	 System.out.println(JSON.toJSONString(params));
         String result = HttpUtil.sendPost(url, params);
         System.out.println(result);
    	 resultMap = JSON.parseObject(result, Map.class);
    	 return resultMap;
    }
    
    
    
    
 
    /**
     * 2.2获得支付信息
     * @param address ： 支付用户的钱包地址
     * @param id ： 支付交易的hash或资源号
     * @return
     */
    public  static Map<String,Object> getPayInfo(String address,String id) {
    	 Map<String,Object> resultMap = new HashMap<>();
    	 url = host + "/"+version+"/accounts/"+address+"/payments/"+id;
         String result = HttpUtil.sendGet(url, null);
         System.out.println(result);
    	 resultMap = JSON.parseObject(result, Map.class);
    	 return resultMap;
    }
    
    
 
    /**
     * 2.3获得支付历史
     * @param address : 支付相关的钱包地址
     * @param results_per_page : 返回的每页数据量，默认每页10项
     * @param page : 返回第几页的数据，从第1页开始
     * @return
     */
    public  static Map<String,Object> getPayHishtory(String address,String results_per_page,String page) {
    	 Map<String,Object> resultMap = new HashMap<>();
    	 url = host + "/"+version+"/accounts/"+address+"/payments";
    	 //参数签名
    	 Map<String,String> params = new HashMap<>();
    	 
    	 params.put("results_per_page", results_per_page);
    	 params.put("page", page);
      
         String result = HttpUtil.sendGet(url, params);
         System.out.println(result);
    	 resultMap = JSON.parseObject(result, Map.class);
    	 return resultMap;
    }
    
    
    
    
    /**
     * 3.1提交挂单
     * @param address: 用户的钱包地址
     * @param validated : 是否等待响应结果
     * @param secret ： 用户的钱包私钥
     * @param type ： 挂单的类型，sell或buy
     * @param pair ：交易的货币对
     * @param amount ： 挂单的数量
     * @param price ： 挂单的价格
     * @return
     */
    public  static Map<String,Object> submitOrder(String address,Boolean validated,String secret,String type,String pair,String amount,String price) {
    	 Map<String,Object> resultMap = new HashMap<>();
    	 url = host + "/"+version+"/accounts/"+address+"/orders";
         //请求参数
    	 Map<String,Object> params = new HashMap<String,Object>();
    	 Map<String,Object> order = new HashMap<String,Object>();
    	 order.put("type",type);
    	 order.put("pair",pair);
    	 order.put("amount",amount);
    	 order.put("price",price);
    	 params.put("secret", secret);
    	 params.put("order", order);
         //发送请求
         String result = HttpUtil.sendPost(url, params);
         System.out.println(result);
    	 resultMap = JSON.parseObject(result, Map.class);
    	 return resultMap;
    }
    
    
    
    
    
    /**
     * 3.2取消挂单
     * @param address : 挂单方的钱包地址
     * @param order : 订单的序号
     * @param validated : 是否等待响应结果
     * @param secret ： 钱包私钥
     * @return
     */
    public  static Map<String,Object> deleteOrder(String address,String order,Boolean validated,String secret) {
    	 Map<String,Object> resultMap = new HashMap<>();
    	 url = host + "/"+version+"/accounts/" + address + "/orders/"+order;
    	 Map<String,Object> params = new HashMap<String,Object>();
    	 params.put("validated",validated);
    	 params.put("secret",secret);
         String result = HttpUtil.sendDelete(url, params);
         System.out.println(result);
    	 resultMap = JSON.parseObject(result, Map.class);
    	 return resultMap;
    }
    
    
    
    /**
     * 3.3获取用户挂单
     * @param address : 钱包地址
     */
    public  static Map<String,Object> getUserOrders(String address) {
    	 Map<String,Object> resultMap = new HashMap<>();
    	 url = host + "/"+version+"/accounts/"+ address +"/orders"; 
         String result = HttpUtil.sendGet(url, null);
         System.out.println(result);
    	 resultMap = JSON.parseObject(result, Map.class);
    	 return resultMap;
    }
    
    
    /**
     * 3.4获取挂单信息
     * @param address : 钱包地址
     * @param hash : 挂单交易哈希号
     */
    public  static Map<String,Object> getUserOrdersInfo(String address,String hash) {
    	 Map<String,Object> resultMap = new HashMap<>();
    	 url = host + "/"+version+"/accounts/"+address+"/orders/"+hash; 
         String result = HttpUtil.sendGet(url, null);
         System.out.println(result);
    	 resultMap = JSON.parseObject(result, Map.class);
    	 return resultMap;
    }
  
    
    
    /**
     * 3.5获得货币对的挂单列表
     * @param address : 挂单方的钱包地址
     * @param base : 基准货币（currency+counterparty）
     * @param counter : 目标货币（currency+counterparty）
     * @return
     */
    public  static Map<String,Object> getCurrencyOrders(String address,String base,String counter) {
    	 Map<String,Object> resultMap = new HashMap<>();
    	 url = host + "/"+version+"/accounts/"+ address +"/order_book/"+base+"/"+counter; 
         String result = HttpUtil.sendGet(url, null);
         System.out.println(result);
    	 resultMap = JSON.parseObject(result, Map.class);
    	 return resultMap;
    }
    
    
    
    /**
     * 4.1查询交易信息
     * @param address : 挂单方的钱包地址
     * @param id : 交易资源号或者交易hash
     * @return
     */
    public  static Map<String,Object> queryTrateInfo(String address,String id) {
    	 Map<String,Object> resultMap = new HashMap<>();
    	 url = host + "/"+version+"/accounts/"+address+"/transactions/"+id; 
         String result = HttpUtil.sendGet(url, null);
         System.out.println(result);
    	 resultMap = JSON.parseObject(result, Map.class);
    	 return resultMap;
    }
    
    
    /**
     * 4.2查询交易记录
     * @param address : 挂单方的钱包地址
     * @param source_account : 支付方地址 (可选，不选时传入null)
     * @param destination_account : 支付接收方地址 (可选，不选时传入null)
     * @param exclude_failed : 是否移除失败的支付历史(可选，不选时传入null)
     * @param direction : 支付方向，incoming或outgoing(可选，不选时传入null)
     * @param results_per_page : 返回的每页数据量，默认每页10项
     * @param page : 返回第几页的数据，从第1页开始
     * @return
     */
    public  static Map<String,Object> queryTrateRecord(String address,Integer results_per_page,Integer page
    		                                           ) {
    	 Map<String,Object> resultMap = new HashMap<>();
    	 url = host + "/"+version+"/accounts/"+address+"/transactions"; 
    	 Map<String,String> params = new HashMap<String,String>();
      	 params.put("results_per_page", results_per_page.toString());
      	 params.put("page", page.toString());
         String result = HttpUtil.sendGet(url, params);
         System.out.println(result);
    	 resultMap = JSON.parseObject(result, Map.class);
    	 return resultMap;
    }
    
    
    
    /**
     *5.1获得服务端连接状态
     * @return
     */
    public  static Map<String,Object> getServerConnectState() {
    	 Map<String,Object> resultMap = new HashMap<>();
    	 url = host + "/"+version+"/server/connected"; 
         String result = HttpUtil.sendGet(url, null);
         System.out.println(result);
    	 resultMap = JSON.parseObject(result, Map.class);
    	 return resultMap;
    }
        
    
    
    public static void main(String[] args) {
    
    	// 1.1创建钱包
    	System.out.println(grtUtil.createWallet());
    	
		//1.2查询余额
		// System.out.println(grtUtil.getBalances("jwUbM496vC6zyLzwBZvN4dZBR2Z8FTDVtL", null, null));
		
		//1.3获得两个账号余额
		//System.out.println(grtUtil.getDoubleAccountBalances("jwUbM496vC6zyLzwBZvN4dZBR2Z8FTDVtL", "jwUbM496vC6zyLzwBZvN4dZBR2Z8FTDVtL"));
		
		//2.1支付请求
 		 String source_address = "jwUbM496vC6zyLzwBZvN4dZBR2Z8FTDVtL";  //支付方的钱包地址
		 String destination = "jn1L2H9we3j6VnNp9GxsEaFwzDuHrWLZyP"; //收款方钱包地址
		 boolean validated =true; //等待响应
		 String secret = "sngkrDJhk2BDkebqqHRkUZBNRXwAF"; //支付方的钱包私钥
		 String client_id = OrderIDGenerate.getOrderId(); //单号
		 String value = "10";
		 String currency ="GRT";
		 String memo = "贵人。。。。";
		 grtUtil.goPayRequest(source_address, destination, validated, secret, client_id, value, currency, memo); 
    	
 
    	
    	//2.3支付信息
    	/*String address = "jwUbM496vC6zyLzwBZvN4dZBR2Z8FTDVtL";
    	String id = "B273B8760509A19ACDC7C319B88B42779EBDE5DCE07B9777D1E9DF3E0F028943";
    	grtUtil.getPayInfo(address, id); */
	 
    	
    	
/*    	//新建任务
    	MyTask task = new MyTask<String>() {
    		@Override
    		public void run() {
    			// TODO Auto-generated method stub
    			for(int i=0;i<1;i++) {

    			}
    		
    			
    			if(this.listner != null) {
    				try {
						listner.rusult("执行成功");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
    		}
		};
		
		
		TaskListner<String> listner = new TaskListner<String>() {
			
			@Override
			public void rusult(String result) throws Exception {
				// TODO Auto-generated method stub
				System.out.println("任务回执："+result);
			}
		};
    	task.setListner(listner);
    	//执行任务  
    	//task ： 任务
    	//i ： 延迟
        //unit ：时间单位
    	 
    	ThreadUtil.execute(task,1, TimeUnit.MICROSECONDS);*/
    	 
    }
}
