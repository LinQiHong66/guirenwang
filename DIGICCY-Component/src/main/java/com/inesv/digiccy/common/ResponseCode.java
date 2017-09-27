package com.inesv.digiccy.common;

/**
 * Created by Administrator on 2016/11/8 0008.
 */
public class ResponseCode {

    // 成功
    public static final String SUCCESS = "100";

    public static final String SUCCESS_DESC = "success";

    // 失败
    public static final String FAIL = "200";

    public static final String FAIL_DESC = "fail";
    
    //注册
    public static final String REG_FAIL_NULL = "11";

    public static final String REG_FAIL_NULL_DESC = "手机号，密码或交易密码为空，注册失败！";
    
    public static final String REG_FAIL_NOT_UNIQUE = "12";

    public static final String REG_FAIL_NOT_UNIQUE_DESC = "该手机号已被注册！";

    //交易余额不足
    public static final String FAIL_TRADE_INSUFFICIENT = "21";

    public static final String FAIL_TRADE_INSUFFICIENT_DESC = "余额不足,失败";

    //交易密码错误
    public static final String FAIL_TRADE_PASSWORD = "22";

    public static final String FAIL_TRADE_PASSWORD_DESC = "交易密码错误,失败";

    //登录密码错误
    public static final String FAIL_TRADE_LOGIN_PASSWORD = "23";

    public static final String FAIL_TRADE_LOGIN_PASSWORD_DESC = "登录密码错误,失败";

    //交易大厅撤销委托
    public static final String FAIL_TRADE_DELENTRUST = "31";

    public static final String FAIL_TRADE_DELENTRUST_DESC = "委托不存在，撤销失败";

    //交易大厅撤销委托
    public static final String FAIL_TRADE_DELENTRUST_STATE = "32";

    public static final String FAIL_TRADE_DELENTRUST_STATE_DESC = "该委托不能撤销，撤销失败";
    
    //交易大厅委托失败
    public static final String FAIL_TRADE_ADDTRUST_STATE = "33";

    public static final String FAIL_TRADE_ADDTRUST_STATE_DESC = "委托失败";

    //查询认购中心所有币种失败
    public static final String FAIL_SUB_CORE = "201";

    public static final String FAIL_SUB_CORE_DESC = "查询认购中心所有币种失败";
    //根据货币种类查询认购中心失败
    public static final String FAIL_SUB_CORE_BYCOIN = "202";

    public static final String FAIL_SUB_CORE_BYCOIN_DESC = "根据货币种类查询认购中心失败";
    //查询用户资产失败
    public static final String FAIL_USER_BALANCE = "203";

    public static final String FAIL_USER_BALANCE_DESC = "查询用户资产失败";
    //购买数量不能小于0
    public static final String FAIL_BUY_COIN_NUM = "204";

    public static final String FAIL_BUY_COIN_NUM_DESC = "购买数量不能小于0";
    //查询用户信息失败
    public static final String FAIL_USER_INFO = "205";

    public static final String FAIL_USER_INFO_DESC = "查询用户信息失败";
    //购买数量大于认购上限
    public static final String FAIL_LIMIT_BUY = "206";

    public static final String FAIL_LIMIT_BUY_DESC = "购买数量大于认购上限";
    //账号余额不足
    public static final String FAIL_ENABLE_COIN = "207";

    public static final String FAIL_ENABLE_COIN_DESC = "账号余额不足";
    //当前可认购数量不足
    public static final String FAIL_COIN_NO_ENOUGH = "208";

    public static final String FAIL_COIN_NO_ENOUGH_DESC = "当前可认购数量不足";
    //查询认购记录失败
    public static final String FAIL_SUB_RECORD = "209";

    public static final String FAIL_SUB_RECORD_DESC = "查询认购记录失败";
    //解冻失败
    public static final String FAIL_THAW_INFO = "210";

    public static final String FAIL_THAW_INFO_DESC = "查询认购记录失败";
    //当前货币无法解冻
    public static final String FAIL_THAW_COIN = "211";

    public static final String FAIL_THAW_COIN_DESC = "当前货币无法解冻";
    //当前货币解冻时间还未到周期时间
    public static final String FAIL_THAW_DATE = "212";

    public static final String FAIL_THAW_DATE_DESC = "当前货币解冻时间还未到周期时间";
    //查询投票记录失败
    public static final String FAIL_VOTE_INFO = "213";

    public static final String FAIL_VOTE_INFO_DESC = "查询投票记录失败";
    //根据用户编号和货币种类查询投票记录失败
    public static final String FAIL_VOTE_TO_COIN = "214";

    public static final String FAIL_VOTE_TO_COIN_DESC = "您已对该币种进行了投票";
    //查询理财中心失败
    public static final String FAIL_FINANCIAL = "215";

    public static final String FAIL_FINANCIAL_DESC = "查询理财中心失败";
    //查询分红记录失败
    public static final String FAIL_BONUS_RECORD = "216";

    public static final String FAIL_BONUS_RECORD_DESC = "查询分红记录失败";
    //查询分红中心失败
    public static final String FAIL_BONUS_INFO = "217";

    public static final String FAIL_BONUS_INFO_DESC = "查询分红中心失败";
    //查询话费充值记录失败
    public static final String FAIL_BILL_INFO = "218";

    public static final String FAIL_BILL_INFO_DESC = "查询分红中心失败";
    //充值金额不能小于0
    public static final String FAIL_PRICE = "219";

    public static final String FAIL_PRICE_DESC = "充值金额不能小于0";
    //退出失败
    public static final String FAIL_LOGOUT = "220";

    public static final String FAIL_LOGOUT_DESC = "退出失败，服务器异常";

    public static final Object FAIL_USER_PHONE_STATE_TRUE = "221";

    public static final Object FAIL_USER_PHONE_STATE_TRUE_DESC = "用户已绑定手机号";

    public static final Object FAIL_USER_PHONE_STATE_FALSE = "222";

    public static final Object FAIL_USER_PHONE_STATE_FALSE_DESC = "用户未绑定手机号";

    public static final Object FAIL_USER_ALIPAY_STATE_TRUE = "223";

    public static final Object FAIL_USER_ALIPAY_STATE_TRUE_DESC = "用户已绑定支付宝号";

    public static final Object FAIL_USER_ALIPAY_STATE_FALSE = "224";

    public static final Object FAIL_USER_ALIPAY_STATE_FALSE_DESC = "用户未绑定支付宝号";
    
    public static final Object FAIL_USER_VALIDATEPWD_STATE_TRUE = "225";

    public static final Object FAIL_USER_VALIDATEPWD_STATE_TRUE_DESC = "用户已双重认证";

    public static final Object FAIL_USER_VALIDATEPWD_STATE_FALSE = "226";

    public static final Object FAIL_USER_VALIDATEPWD_STATE_FALSE_DESC = "用户未双重认证";


    public static final Object FAIL_WITHDRAW_RIGHT_PRICE_FALSE = "260";

    public static final Object FAIL_WITHDRAW_RIGHT_PRICE_FALSE_DESC = "请输入正确的提现金额";

    public static final Object FAIL_WITHDRAW_PRICE_ENOUGH_FALSE = "261";

    public static final Object FAIL_WITHDRAW_PRICE_ENOUGH_FALSE_DESC = "余额不足";

    public static final Object FAIL_WITHDRAW_DEAL_PASSWORD_FALSE = "262";

    public static final Object FAIL_WITHDRAW_DEAL_PASSWORD_FALSE_DESC = "交易密码错误";

    public static final Object FAIL_SMS_VALIDATE_FALSE = "263";

    public static final Object FAIL_SMS_VALIDATE_FALSE_DESC = "短信验证码不正确";

    public static final Object FAIL_TRAN_COIN_FALSE = "264";

    public static final Object FAIL_TRAN_COIN_FALSE_DESC = "请输入正确的提现金额";

    public static final Object FAIL_TRAN_USER_FALSE = "264";

    public static final Object FAIL_TRAN_USER_FALSE_DESC = "用户不存在";


    public static final Object FAIL_CODE_FALSE = "280";

    public static final Object FAIL_CODE_FALSE_DESC = "验证码不正确";

    public static final Object FAIL_USER_PHONE_FALSE = "290";

    public static final Object FAIL_USER_PHONE_FALSE_DESC = "用户名或手机号不正确";

    public static final String FAIL_USER_INVITE = "300";

    public static final String FAIL_USER_INVITE_DESC = "查询用户邀请码失败";

    public static final String FAIL_USER_RANK = "301";

    public static final String FAIL_USER_RANK_DESC = "查询推荐排行榜失败";

    public static final String FAIL_USER_RANK_DETAIL = "302";

    public static final String FAIL_USER_RANK_DETAIL_DESC = "查询推荐排行榜失败";

    public static final String FAIL_REC_USER = "303";

    public static final String FAIL_REC_USER_DESC = "暂无已推荐会员信息";

    public static final String FAIL_MY_REC_USER = "304";

    public static final String FAIL_MY_REC_USER_DESC = "查询我的推广信息失败";

    public static final String FAIL_USER_IP_LOG = "305";

    public static final String FAIL_USER_IP_LOG_DESC = "查询用户ip失败";

    public static final String FAIL_COIN_TYPE_ISNOT = "306";

    public static final String FAIL_COIN_TYPE_ISNOT_DESC = "查询用户ip失败";

    public static final String FAIL_ERROR_CODE = "400";

    public static final String FAIL_ERROR_CODE_DESC = "验证码不正确";

    public static final String FAIL_ERROR_COINTYPE_DESC = "不存在改币种";
    
    public static final String FAIL_ADDRESS_WORN = "401";

    public static final String FAIL_ADDRESS_WORN_DESC = "钱包地址不正确";

    public static final String FAIL_INPUT_COIN = "402";

    public static final String FAIL_INPUT_COIN_DESC = "请输入正确的金额";

    public static final String FAIL_DEALPWD = "403";

    public static final String FAIL_DEALPWD_DESC = "交易密码不正确";

    public static final String FAIL_DEAL_ID = "404";

    public static final String FAIL_DEAL_ID_DESC = "交易密码不正确";

    public static final String FAIL_BALANCE_NUM = "405";

    public static final String FAIL_BALANCE_NUM_DESC = "余额不足";

    public static final String FAIL_EMPTY_USER = "406";

    public static final String FAIL_EMPTY_USER_DESC = "用户不存在";
    
    public static final String FAIL_PLAN_INFO = "407";
    
    public static final String FAIL_PLAN_INFO_DESC = "计划设置失败";
    
    public static final String FAIL_PLAN_INFO_CODE = "408";
    
    public static final String FAIL_PLAN_INFO_CODE_DESC = "查询委托计划列表失败";

    public static final String FAIL_PLAN_OVER_CODE = "409";
    
    public static final String FAIL_PLAN_OVER_DESC = "委托计划取消成功";
    
    public static final String FAIL_ADDRESS_INFO_CODE = "410";
    
    public static final String FAIL_ADDRESS_INFO_CODE_DESC = "查询接口权限列表失败";
    
    public static final String FAIL_ADDRESS_NAME_CODE = "411";
    
    public static final String FAIL_ADDRESS_NAME_CODE_DESC = "查询接口失败";
    
    public static final String FAIL_ADDRESS_UPDATE_LEVEL_CODE = "412";
    
    public static final String FAIL_ADDRESS_UPDATE_LEVEL_CODE_DESC = "修改接口权限失败";
    
    public static final String FAIL_USER_LEVEL_INFO_CODE = "413";
    
    public static final String FAIL_USER_LEVEL_INFO_CODE_DESC = "查询用户权限列表失败";
    
    public static final String FAIL_USER_LEVEL_ID_INFO_CODE = "414";
    
    public static final String FAIL_USER_LEVEL_IID_NFO_CODE_DESC = "查询用户权限失败";
    
    public static final String FAIL_USER_LEVEL_UPDATE_INFO_CODE = "415";
    
    public static final String FAIL_USER_LEVEL_UPDATE_INFO_CODE_DESC = "修改用户权限失败";
    
    public static final String FAIL_COIN_LEVEL_PROPORTION_INFO_CODE = "416";
    
    public static final String FAIL_COIN_LEVEL_PROPORTION_INFO_CODE_DESC = "查询币种等级分红百分比失败";
    
    public static final String FAIL_COIN_LEVEL_PROPORTION_COINNO_INFO_CODE = "417";
    
    public static final String FAIL_COIN_LEVEL_PROPORTION_COINNO_INFO_CODE_INFO_CODE_DESC = "根据查询币种等级分红百分比失败"; 
    
    public static final String FAIL_COIN_LEVEL_PROPORTION_INSERT_INFO_CODE = "418";
    
    public static final String FAIL_COIN_LEVEL_PROPORTION_INSERT_INFO_CODE_DESC = "新增币种等级比例";  
    
    public static final String FAIL_COIN_LEVEL_PROPORTION_UPDATE_INFO_CODE = "419";
    
    public static final String FAIL_COIN_LEVEL_PROPORTION_UPDATE_INFO_CODE_DESC = "修改币种等级比例失败";   
    
    public static final String FAIL_BUY_ENTRUST_INFO_CODE = "420";
    
    public static final String FAIL_BUY_ENTRUST_INFO_CODE_DESC = "查询市场深度图失败";   
}
