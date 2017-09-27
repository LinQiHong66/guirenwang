package com.inesv.digiccy.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisCodeImpl {
    
  
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    
      //短信验证码的key前缀 jedis.setex(key, seconds, value);
      private static final String SMS_KEY = "app";
      
      
      private static String makeKey(String mobile, int type){
          return new StringBuilder(SMS_KEY).append("_").append(mobile).append("_").append
                  (type).toString();
      }
      
      
      /**
       * 获取短信验证码
       * @param mobile
       * @param type
       * @return
       */
      public Integer getSms(String mobile,int type){
          Integer result;
          String key = makeKey(mobile, type);
          if(redisTemplate.opsForValue().get(key)==null){
        	  return 0;
          }
          result = (Integer) redisTemplate.opsForValue().get(key);
          return result;
      }
      
      
      /**
       * 存短信验证码
       * @param mobile
       * @param type
       * @param code
       * @return
       */
      public void setSms(String mobile,int type,int code){
    	  
          String key = makeKey(mobile, type);
          //redisTemplate.opsForValue().set(key, code);
          delete(mobile, type);
          redisTemplate.opsForValue().set(key, code,5,TimeUnit.MINUTES);
      }
      
      /**
       * 删除
       * @param mobile
       * @param type
       * @return
       */
      public void delete(String mobile,int type){
          
          String key = makeKey(mobile, type);
          redisTemplate.delete(key);
      }

}
