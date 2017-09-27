package com.inesv.digiccy.common;



public @interface  Constants {
	//!$&)_077545&()_+/>
	public final String SYS_TOKEN=new com.inesv.digiccy.util.MD5().getMD5("111111");
	public static final String TOKEN_CACHE = "token_user_";// 令牌 token
	public final int SYS_SUCCESS=0;
	public final String SYS_SUCCESS_MSG="成功";
	

	public final int SYS_FAILURE=-1;
	public final String SYS_FAILURE_MSG="失败";
	
	
	interface ERP_QUICK{
		 final int companyCode=888;
		
		 final String companyName="交易平台";
		
		 final String cardCode="CB0001";
		
		/**
		 * 客户名称：固定值：网络客户
		 */
		 final String cardname="网络客户";
		
		/**
		 * 订单类型固定值：游客
		 */
		 final String orderType="网络客户";
		
		/**
		 * 出货类型固定值：代发
		 */
		 final String shipType="代发";

		 final String expressType = " ";
		
		 final String ItemType= "商品";
		
	}
	
	
	
	
}
