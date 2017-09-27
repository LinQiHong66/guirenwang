package com.inesv.digiccy.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * 生成各种字符串的工具类
* @ClassName: GenerateUtil
* @module  : 模块描述
* @comment : 模块包括的子功能
* @Description: TODO(这里用一句话描述这个类的作用)
* @author zhanyihuang
* @date 2016年8月16日 上午10:16:02
 */
public class GenerateUtil {

  /**
   * 生成UUID
  * @Title: generateUUID
  * @Description: TODO(这里用一句话描述这个方法的作用)
  * @return    设定文件
  * @return String    返回类型
  * @throws
  * @date 2016年8月16日 上午10:16:20
   */
  public static String generateUUID(){
    String uuid = UUID.randomUUID().toString();
    return uuid;
  }
  
  /**
   * 生成订单号 (时间戳加4位随机数)
  * @Title: generateUUID
  * @Description: TODO(这里用一句话描述这个方法的作用)
  * @return    设定文件
  * @return String    返回类型
  * @throws
  * @date 2016年8月16日 上午10:16:20
   */
  public static String generateOrderNo(){
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    Integer num = (int) (Math.random()*9000+1000);
    String str = sdf.format(date)+num;
    return str;
  }
  
  /**
   * 生成日期
  * @Title: generateDate
  * @Description: TODO(这里用一句话描述这个方法的作用)
  * @return    设定文件
  * @return Timestamp    返回类型
  * @throws
  * @date 2016年8月16日 上午10:17:21
   */
  public static Timestamp generateDate() {
    String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    Timestamp date = Timestamp.valueOf(nowTime);
    return date;
  }
  
  /**
   * 获取时间间距
  * @Title: getBetweenDay
  * @Description: TODO(这里用一句话描述这个方法的作用)
  * @return    设定文件
  * @return Long    返回类型
  * @throws
  * @date 2016年8月29日 上午10:55:27
   */
  public static Long getBetweenDay(Date date) {
    Date now = new Date();
    long l=now.getTime()-date.getTime();
    long day=l/(24*60*60*1000);  
    return day;
  }
  
  public static Long getBetweenDay(Date before,Date after) {
    long l=after.getTime()-before.getTime();
    long day=l/(24*60*60*1000);  
    return day;
  }
  
  /**
   * 获取时间
  * @Title: getBetweenDay
  * @Description: TODO(这里用一句话描述这个方法的作用)
  * @return    设定文件
  * @return Long    返回类型
  * @throws
  * @date 2016年8月29日 上午10:55:27
   */
  public static Timestamp getAfterDay(Integer afterMonth) {
    Calendar c = Calendar.getInstance();
    c.add(Calendar.MONTH, afterMonth);
    String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
    Timestamp date = Timestamp.valueOf(time);
    return date;
  }


}
