package com.inesv.digiccy.validata;

import com.aliyuncs.exceptions.ClientException;
import com.inesv.digiccy.api.command.InesvPhoneCommand;
import com.inesv.digiccy.api.command.UserCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.query.QueryUserNamePhoneInfo;
import com.inesv.digiccy.redis.RedisCodeImpl;
import com.inesv.digiccy.sms.SendMsgUtil;
import com.inesv.digiccy.util.MD5;
import com.inesv.digiccy.util.SmsUtil;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yc on 2016/12/9 0009.
 * 校验修改密码
 */
@Component
public class ForgetDealPswValidate {

    @Autowired
    QueryUserNamePhoneInfo queryUserNamePhoneInfo;

    @Autowired
    RedisCodeImpl redisCode;

    @Autowired
    SendMsgUtil sendMsgUtil;

    @Autowired
    private CommandGateway commandGateway;


    public Map<String, Object> validateUpdateDealPwd(String username, String phone, String code, String updatePwd) {

        Map<String, Object> map = new HashMap<>();
        List<InesvUserDto> list = queryUserNamePhoneInfo.getUserNamePhoneInfo(username);
        if(validataCompare(phone,code)==1){
        	if (!list.isEmpty()) {
                MD5 md5 = new MD5();
                String dealPwd = md5.getMD5(updatePwd);//将交易密码转化成MD5加密格式
                UserCommand command = new UserCommand(33,username,dealPwd,"updateDealPsw");
                commandGateway.send(command);
                map.put("code", ResponseCode.SUCCESS);
                map.put("desc", ResponseCode.SUCCESS_DESC);
            }else{
                map.put("code", ResponseCode.FAIL_USER_PHONE_FALSE);
                map.put("desc", ResponseCode.FAIL_USER_PHONE_FALSE_DESC);//用户名或手机号不正确
            }
        }else{
        	map.put("code", ResponseCode.FAIL_SMS_VALIDATE_FALSE);
            map.put("desc", ResponseCode.FAIL_SMS_VALIDATE_FALSE_DESC);//短信验证码不正确
            return map;
        }
        return map;
    }


    /**
     * 发送短信验证码
     */
    public Map<String, Object> validatePhoneCode(int type, String mobile) {

    	
    	int mCode = sendMsgUtil.getCode(mobile, type);

    	redisCode.setSms(mobile, type, mCode);
    	boolean ok = false;
    	try {
			ok = SmsUtil.sendMySms(mobile, mCode+"");
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
    	
    	
    	Map<String, Object> map = new HashMap<>();
        try {
			ok = SmsUtil.sendMySms(mobile, mCode+"");
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        InesvPhoneCommand command = new InesvPhoneCommand(0, null, mobile, 1, mCode, "insert");
        commandGateway.send(command);
        if (ok) {
        	map.put("getCode",mCode);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
        } else {
            map.put("code", ResponseCode.FAIL);
            map.put("desc", ResponseCode.FAIL_DESC);
        }
        return map;
    }

    /**
     * 校验短信验证码
     */
    public int validataCompare(String mobile, String code) {
        int smsNum = redisCode.getSms(mobile, 1);//获取缓存里面的验证码
        //通用手机号码验证
        if (code.equals(smsNum + "")) {
            return 1;
        }
        return 0;
    }

}
