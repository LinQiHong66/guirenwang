package com.inesv.digiccy.grt;

 
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;
import com.inesv.digiccy.util.thread.MyTask;
import com.inesv.digiccy.util.thread.TaskListner;
import com.inesv.digiccy.util.thread.ThreadUtil;
 

public class grtUtil {
	 //请求服务器
     public final static String host = "http://47.92.80.205:3000";   //47.92.80.205:3000  测试环境地址
     
     //请求url
     public static String url = "";   
     
     
    
     /**
      * 1.1创建钱包
      * @param address ： 钱包地址
      * @return  
      */
     public  static Map<String,Object> createWallet() {
     	 Map<String,Object> resultMap = new HashMap<>();
     	 url = host + "/v1/wallet/new";
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
    	 url = host + "/v1/accounts/" + address + "/balances";
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
    	 url = host + "/v1/accounts/"+address1+"/"+address2+"/balances";
         String result = HttpUtil.sendGet(url, null);
    	 resultMap = JSON.parseObject(result, Map.class);
    	 return resultMap;
    }
    
    
 
 
    /**
     * 2.1支付请求
     * @param source_address : 支付方的钱包地址
     * @param validated : 是否等待支付结果
     * @param secret ： 支付方的钱包私钥
     * @param client_resource_id ： 此次请求的交易单号
     * @param payment ： 支付对象
     * @return
     */
    public  static Map<String,Object> goPayRequest(String source_address,Boolean validated,String secret,String client_resource_id,Object payment) {
    	 Map<String,Object> resultMap = new HashMap<>();
    	 url = host + "/v1/accounts/"+source_address+"/payments";
    	 
    	 Map<String,Object> params = new HashMap<>();
    	 params.put("validated",validated);
    	 params.put("secret",secret);
    	 params.put("client_resource_id",client_resource_id);
    	 params.put("payment",payment);
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
    	 url = host + "/v1/accounts/"+ address +"/payments/"+ id;
         String result = HttpUtil.sendGet(id, null);
         System.out.println(result);
    	 resultMap = JSON.parseObject(result, Map.class);
    	 return resultMap;
    }
    
    
 
    /**
     * 2.3获得支付历史
     * @param address : 支付相关的钱包地址
     * @param source_account : 支付方地址 (可选，不选时传入null)
     * @param destination_account : 支付接收方地址 (可选，不选时传入null)
     * @param exclude_failed : 是否移除失败的支付历史(可选，不选时传入null)
     * @param direction : 支付方向，incoming或outgoing(可选，不选时传入null)
     * @param results_per_page : 返回的每页数据量，默认每页10项
     * @param page : 返回第几页的数据，从第1页开始
     * @return
     */
    public  static Map<String,Object> getPayHishtory(String address,String source_account,String destination_account,Boolean exclude_failed,
    		String direction,Integer results_per_page,Integer page) {
    	 Map<String,Object> resultMap = new HashMap<>();
    	 url = host + "/v1/accounts/"+address+"/payments";
    	 //参数签名
    	 Map<String,String> params = new HashMap<>();
    	 if(source_account!=null) {
    	   params.put("source_account", source_account);
    	 }
    	 if(destination_account!=null) {
    	 params.put("destination_account", source_account);
    	 }
    	 if(exclude_failed!=null) {
    	 params.put("exclude_failed", exclude_failed.toString());
    	 }
    	 if(direction!=null) {
    	 params.put("direction", direction);
    	 }
    	 params.put("results_per_page", results_per_page.toString());
    	 params.put("page", page.toString());
      
         String result = HttpUtil.sendGet(url, params);
         System.out.println(result);
    	 resultMap = JSON.parseObject(result, Map.class);
    	 return resultMap;
    }
    
    
    
 
    /**
     * 3.1提交挂单
     * @param address : 用户的钱包地址
     * @param validated : 是否等待响应结果
     * @param secret ： 用户的钱包私钥
     * @param type ： 挂单的类型，sell或buy
     * @param currency1 ： 货币名称（支付方）
     * @param counterparty1 ： 货币发行方（支付方）
     * @param value1  ：  交易金额（支付方）
     * @param currency2 ： 货币名称（收款方）
     * @param counterparty2 ：  货币发行方（收款方）
     * @param value2 ： 交易金额（收款方）
     * @return
     */
    public  static Map<String,Object> submitOrder(String address,Boolean validated,String secret,String type,
    		                                      String currency1,String counterparty1,String value1,
    		                                      String currency2,String counterparty2,String value2 	) {
    	 Map<String,Object> resultMap = new HashMap<>();
    	 url = host + "/v1/accounts/"+address+"/orders";
         //请求参数
    	 Map<String,Object> orderMap = new HashMap<>();
    	 Map<String,String> taker_paysMap = new HashMap<>();
    	 taker_paysMap.put("currency", currency1);
    	 taker_paysMap.put("counterparty", counterparty1);
    	 taker_paysMap.put("value", value1);
    	 Map<String,Object> taker_getsMap = new HashMap<>();
    	 taker_getsMap.put("currency", currency2);
    	 taker_getsMap.put("counterparty", counterparty2);
    	 taker_getsMap.put("value", value2);
    	 orderMap.put("type", type);
    	 orderMap.put("taker_pays", taker_paysMap);
    	 orderMap.put("taker_gets", taker_getsMap);
      
    	 Map<String,Object> params = new HashMap<String,Object>();
    	 params.put("validated", validated);
    	 params.put("secret", secret);
    	 params.put("order", JSON.toJSONString(orderMap));  //*****
    	 
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
    	 url = host + "/v1/accounts/" + address + "/orders/"+order;
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
    	 url = host + "/v1/accounts/"+ address +"/orders"; 
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
    	 url = host + "/v1/accounts/"+address+"/orders/"+hash; 
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
    	 url = host + "/v1/accounts/"+ address +"/order_book/"+base+"/"+counter; 
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
    	 url = host + "/v1/accounts/"+address+"/transactions/"+id; 
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
    public  static Map<String,Object> queryTrateRecord(String address,String source_account,String destination_account,Boolean exclude_failed,
    		                                           String direction,Integer results_per_page,Integer page
    		                                           ) {
    	 Map<String,Object> resultMap = new HashMap<>();
    	 url = host + "/v1/accounts/"+address+"/transactions"; 
    	 Map<String,String> params = new HashMap<String,String>();
    	 if(source_account!=null) {
      	   params.put("source_account", source_account);
      	 }
      	 if(destination_account!=null) {
      	 params.put("destination_account", source_account);
      	 }
      	 if(exclude_failed!=null) {
      	 params.put("exclude_failed", exclude_failed.toString());
      	 }
      	 if(direction!=null) {
      	 params.put("direction", direction);
      	 }
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
    	 url = host + "/v1/server/connected"; 
         String result = HttpUtil.sendGet(url, null);
         System.out.println(result);
    	 resultMap = JSON.parseObject(result, Map.class);
    	 return resultMap;
    }
        
    public static void main(String[] args) {
    	 
    	 //System.out.println(grtUtil.getBalances("aa"));
    	//System.out.println(grtUtil.getDoubleAccountBalances("aa","bb"));
    	 //System.out.println(grtUtil.goPayRequest("aaaa",true));
    	//System.out.println(grtUtil.getPayInfo("aa","aaa"));
    	//grtUtil.getPayHishtory("aa", "bbb", null,null, null, 10,1);
    	//grtUtil.submitOrder("address",true,"ssleflsdlfsid", "sell", "CNY", "gMRtbNgt5MqsWd9ninxDgzr4UHzZEM8n4y","1", "sds","dsefdsds", "2");
    	//grtUtil.deleteOrder("aa","123", true, "sss");
    	//grtUtil.getUserOrders("aaa");
    	//grtUtil.getCurrencyOrders("aa","12", "ss");
    	 //System.out.println(grtUtil.getServerConnectState());
    	
    	//新建任务
    	MyTask task = new MyTask<String>() {
    		@Override
    		public void run() {
    			// TODO Auto-generated method stub
    			for(int i=0;i<2;i++) {
    				
    				System.out.println(grtUtil.createWallet());
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
    	 
    	ThreadUtil.execute(task,1, TimeUnit.MICROSECONDS);
    	 
    }
}
