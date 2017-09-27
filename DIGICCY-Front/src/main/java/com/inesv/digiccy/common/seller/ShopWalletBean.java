package com.inesv.digiccy.common.seller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

public class ShopWalletBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	private int uid;
	/**
	 * 支付密码（加密）
	 */
	private String pay_passwd;
	/**
	 * 钱包余额
	 */
	private BigDecimal money = new BigDecimal(0.00);
	/**
         * 
         */
	private String createtime;
	/**
         * 
         */
	private String updatetime;
	/**
	 * 支付密码错误 尝试次数
	 */
	private int try_pay_num;
	/**
	 * 状态 0 正常 1 锁定（连续3次输入错误）
	 */
	private int status;
	/**
	 * 锁定时间
	 */
	private String unlock_time;
	/**
	 * 解锁类型
	 */
	private int unlock_type;

	public int getUid(){

		return uid;
	}

	public void setUid(int uid){

		this.uid = uid;
	}

	public String getPay_passwd(){

		return pay_passwd;
	}

	public void setPay_passwd(String pay_passwd){

		this.pay_passwd = pay_passwd;
	}

	public BigDecimal getMoney(){

		return money;
	}

	public void setMoney(BigDecimal money){

		this.money = money;
	}

	public String getCreatetime(){

		return createtime;
	}

	public void setCreatetime(String createtime){

		this.createtime = createtime;
	}

	public String getUpdatetime(){

		return updatetime;
	}

	public void setUpdatetime(String updatetime){

		this.updatetime = updatetime;
	}

	public int getTry_pay_num(){

		return try_pay_num;
	}

	public void setTry_pay_num(int try_pay_num){

		this.try_pay_num = try_pay_num;
	}

	public int getStatus(){

		return status;
	}

	public void setStatus(int status){

		this.status = status;
	}

	public String getUnlock_time(){

		return unlock_time;
	}
 
	public void setUnlock_time(String unlock_time){

		this.unlock_time = unlock_time;
	}

	public int getUnlock_type(){

		return unlock_type;
	}

	public void setUnlock_type(int unlock_type){

		this.unlock_type = unlock_type;
	}

	public String toString(){
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Uid", uid);
		map.put("Pay_passwd", pay_passwd);
		map.put("Money", money);
		map.put("Createtime", createtime);
		map.put("Updatetime", updatetime);
		map.put("Try_pay_num", try_pay_num);
		map.put("Status", status);
		map.put("Unlock_time", unlock_time);
		map.put("Unlock_type", unlock_type);
		String value = "";
		try{
			value = mapper.writeValueAsString(map);
		} catch (Exception e){
			e.printStackTrace();
		}
		return value;
	}
}
