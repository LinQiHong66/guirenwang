package com.inesv.digiccy.common.autocreate.bean;

/**
 * @description: 快购业务模块
 * @author hxc 
 * @date 2014-6-27 下午4:58:09
 */
public enum ModelType {

	HOMEPAGE("首页"), FENLEI("分类及搜索"), CART("购物车"), STORE("收藏"), SELLERSHOPS("卖家商铺"), LOGIN("登录注册"), GUID("引导页"), GOGOGO(
			"下单页面"), MSGBOX("消息盒子"), OTHER("其他"), PAY("支付"), QUERYORDER("订单处理"), SYSTEM("系统模块"), EDITION("版本"), BASICINFO(
			"个人页面"), INTEGRAL("积分"), WEB("网页版接口"), TUIKUAN("退款"), WEBCASH("网页版提现"), RUSHORDER("网页版抢购"), SHENHEWEB(
			"提现审核"), CATEGORY("商品分类"), BANNER("滚动列表"),TEST("测试"),TRADE("交易大厅"),BANK("银行卡地址"),NOTICE("公告"),DAYMARKET("每日行情"),FINANCIAL("财务中心");
	private final String des;

	public String getDes() {
		return des;
	}

	ModelType(String des) {
		this.des = des;
	}
}
