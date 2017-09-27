package com.inesv.digiccy.back.security;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

/**
 * 密码验证类
 * Created by JimJim on 2016/11/1 0001.
 */
public class MyPasswordEncode extends MessageDigestPasswordEncoder{

    public MyPasswordEncode(String encode) {
        super(encode);
    }

    @Override
    public boolean isPasswordValid(String savePass, String submitPass, Object salt) {
    
        Md5PasswordEncoder md5 = new Md5PasswordEncoder();	
        System.out.println("密码校验--------------------------:savePass:"+savePass+",submit:"+md5.encodePassword(submitPass,salt));
        
        return savePass.equalsIgnoreCase(md5.encodePassword(submitPass,salt));
    
    }

 

}
 