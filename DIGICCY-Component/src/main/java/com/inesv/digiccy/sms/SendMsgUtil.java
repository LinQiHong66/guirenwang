package com.inesv.digiccy.sms;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.redis.RedisCodeImpl;

@Component
public class SendMsgUtil implements ApplicationContextAware {

	/**
	 * Logger for this class
	 */
	private final Logger LOGGER = LoggerFactory.getLogger(SendMsgUtil.class);

	@Autowired
	RedisCodeImpl redisCodeImpl;

	public int getCode(String mobile, int type) {
		Integer code;
		code = getRandomCode();
		return code;
	}

	/*
	 * private Map<String, Object> sendMsg(String mobile, int type) { Map<String,
	 * Object> result = new HashMap<String, Object>(); TaobaoClient client = new
	 * DefaultTaobaoClient(SmsConstant.MESSAGE_url, SmsConstant.MESSAGE_appkey,
	 * SmsConstant.MESSAGE_secret); AlibabaAliqinFcSmsNumSendRequest req = new
	 * AlibabaAliqinFcSmsNumSendRequest(); req.setSmsType("normal");// 必须 短信类型
	 * req.setSmsFreeSignName("bg系统");// 签名 // 参数内容 Map<String, Object> param = new
	 * HashMap<String, Object>(); Integer code; code = getCode(mobile, type);
	 * param.put("number", code + ""); // 参数只接受string
	 * req.setSmsParamString(JSON.toJSONString(param)); req.setRecNum(mobile);//
	 * 短信接收者 req.setSmsTemplateCode(getSmsTemplateCode(type));// 短信模板id try {
	 * AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);//发送手机短信 // 存入缓存
	 * redisCodeImpl.setSms(mobile, type, code); LOGGER.info(rsp.getBody());
	 * result.put("code", code); System.out.println(redisCodeImpl.getSms(mobile,
	 * type)); return result; } catch (Exception e) { e.printStackTrace();
	 * result.put("code", 500); return result; } }
	 */

	// 获取验证码
	public int getRandomCode() {

		return (int) Math.abs(new Random().nextInt(899999) + 100000);
	}

	/**
	 * 
	 * @Title: getSmsTemplateCode
	 * @Description:验证类型获取不同的content
	 * @param type
	 * @return 设定文件
	 * @return String 返回类型
	 * @throws @date
	 *             2016年8月18日 下午12:12:35
	 */
	public String getSmsTemplateCode(int type) {
		String sysTemplate = "";
		switch (type) {
		case 1:// 修改密码
			sysTemplate = SmsConstant.sms_update;
			break;
		case 2:// 支付
			sysTemplate = SmsConstant.sms_payment;
			break;
		default:// 默认

			break;
		}
		return sysTemplate;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub

	}

}
