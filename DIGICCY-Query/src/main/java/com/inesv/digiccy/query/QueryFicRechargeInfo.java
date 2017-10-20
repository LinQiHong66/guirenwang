package com.inesv.digiccy.query;

import com.alibaba.fastjson.JSON;
import com.inesv.digiccy.dto.CoinDto;
import com.inesv.digiccy.dto.FicRechargeDto;
import com.inesv.digiccy.dto.FicWithdrawDto;
import com.inesv.digiccy.dto.RmbRechargeDto;
import com.inesv.digiccy.dto.RmbWithdrawDto;
import com.inesv.digiccy.dto.UserInfoDto;
import com.inesv.digiccy.dto.pageDto;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
@Component
public class QueryFicRechargeInfo {

    private static Logger logger = LoggerFactory.getLogger(QueryFicRechargeInfo.class);

    @Autowired
    QueryRunner queryRunner;


    /**
     *根据时间查询提现记录
     *
     */
    public List<FicWithdrawDto> getwithdrawInfo(String start,String end,String date,Integer userId){
    	
    	List<FicWithdrawDto> list=null;
        String sql="";
         if(!start.equals("")){
        	 System.out.println("=======提现1====start========"+start+",end:"+end+",date:"+date);
        	sql="SELECT *FROM t_inesv_fic_withdraw WHERE DATE BETWEEN ? AND ? AND user_no = ?";
        	 System.out.println("=======sql======="+sql);
        	 Object parmas1[] = {start,end,userId};
        	    try {
        	    	list=(List<FicWithdrawDto>)queryRunner.query(sql, new BeanListHandler(FicWithdrawDto.class),parmas1);
        	    	System.out.println("=======list===="+list.size());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        }else{
        	System.out.println("=======提现2====start========"+start+",end:"+end+",date:"+date);
        	sql="SELECT *FROM t_inesv_fic_withdraw WHERE DATE_SUB(CURDATE(), INTERVAL ? DAY) <= DATE(DATE) AND user_no = ? ";
        	Object parmas2[] = {date,userId};
        	try {
    	    	list= (List<FicWithdrawDto>) queryRunner.query(sql, new BeanListHandler(FicWithdrawDto.class),parmas2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
         return list;
    }
    
    /**
     *根据时间查询提现记录 --人民币
     *
     */
    public List<RmbWithdrawDto> getwithdrawInfoRMB(String start,String end,String date,Integer userId){
    	
    	List<RmbWithdrawDto> list=null;
        String sql="";
         if(!start.equals("")){
        	 System.out.println("=======提现1====start========"+start+",end:"+end+",date:"+date);
        	sql="SELECT *FROM t_inesv_rmb_withdraw WHERE DATE BETWEEN ? AND ? AND user_no = ?";
        	 System.out.println("=======sql======="+sql);
        	 Object parmas1[] = {start,end,userId};
        	    try {
        	    	list=(List<RmbWithdrawDto>)queryRunner.query(sql, new BeanListHandler(RmbWithdrawDto.class),parmas1);
        	    	System.out.println("=======list===="+list.size());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        }else{
        	System.out.println("=======提现2====start========"+start+",end:"+end+",date:"+date);
        	sql="SELECT *FROM t_inesv_rmb_withdraw WHERE DATE_SUB(CURDATE(), INTERVAL ? DAY) <= DATE(DATE) AND user_no = ? ";
        	Object parmas2[] = {date,userId};
        	try {
    	    	list= (List<RmbWithdrawDto>) queryRunner.query(sql, new BeanListHandler(RmbWithdrawDto.class),parmas2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
         return list;
    }
    
    /**
     *根据时间查询充值记录
     *
     */
    public List<FicRechargeDto> getrechargeInfo(String start,String end,String date,Integer userId){
    	System.out.println("=======充值====start========"+start+",end:"+end+",date:"+date);
    	List<FicRechargeDto> list=null;
        String sql="";
         if(!start.equals("")){
        	sql="SELECT *FROM t_inesv_fic_recharge WHERE DATE BETWEEN ? AND ? and user_no=?";
        	
        	 Object parmas1[] = {start,end,userId};
        	    try {
        	    	list=(List<FicRechargeDto>)queryRunner.query(sql, new BeanListHandler(FicRechargeDto.class),parmas1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        }else{
        	sql="SELECT *FROM t_inesv_fic_recharge WHERE DATE_SUB(CURDATE(), INTERVAL ? DAY) <= DATE(DATE) AND user_no=?";
        	Object parmas2[] = {date,userId};
        	try {
    	    	list=(List<FicRechargeDto>)queryRunner.query(sql, new BeanListHandler(FicRechargeDto.class),parmas2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
         return list;
    }
    
    
    /**
     *根据时间查询充值记录 -- 人民币
     *
     */
    public List<RmbRechargeDto> getrechargeInfoRMB(String start,String end,String date,Integer userId){
    	System.out.println("=======充值====start========"+start+",end:"+end+",date:"+date);
    	List<RmbRechargeDto> list=null;
        String sql="";
         if(!start.equals("")){
        	sql="SELECT *FROM t_inesv_rmb_recharge WHERE DATE BETWEEN ? AND ? and user_no=?";
        	
        	 Object parmas1[] = {start,end,userId};
        	    try {
        	    	list=(List<RmbRechargeDto>)queryRunner.query(sql, new BeanListHandler(RmbRechargeDto.class),parmas1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        }else{
        	sql="SELECT *FROM t_inesv_rmb_recharge WHERE DATE_SUB(CURDATE(), INTERVAL ? DAY) <= DATE(DATE) AND user_no=?";
        	Object parmas2[] = {date,userId};
        	try {
    	    	list=(List<RmbRechargeDto>)queryRunner.query(sql, new BeanListHandler(RmbRechargeDto.class),parmas2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
         return list;
    }
    
    /**
     *根据用户编号和币种查询相应用户的虚拟币充值信息
     *
     */
    public List<FicRechargeDto> queryFicRechargeInfo(int userNO,int coinNo){
        List<FicRechargeDto> list = null;
        String sql = "select * from t_inesv_fic_recharge where user_no = ? and coin_no = ?";
        Object parmas[] = {userNO,coinNo};
        try {
            list = (List<FicRechargeDto>) queryRunner.query(sql, new BeanListHandler(FicRechargeDto.class),parmas);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("查询虚拟币充值记录失败");
        }
        return list;
    }


    /**
     *
     *查询是充值ID
     */
    public FicRechargeDto qurtytxid(String txid){
        FicRechargeDto list = null;
        String sql = "select * from t_inesv_fic_recharge where txid = ?";
        Object parmas[] ={txid};
        try {
            list = (FicRechargeDto)queryRunner.query(sql,new BeanHandler(FicRechargeDto.class),parmas);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("查询钱包交易ID失败");
        }
        return list;
    }

 
    /**
     *查询所有虚拟币充值信息
     */
    public List<FicRechargeDto> queryAllFicRechargeInfo(String userCode, String phone, String realName,String state,String coinType,String startData,String endData,pageDto page){
        List<FicRechargeDto> list = new ArrayList<>();
        String limitstr = " limit ?,? ";
        Integer firstRecord = page.getFirstRecord();
        Integer pageSize = page.getPageSize();
        
        String sql="SELECT w.id AS id,u.username AS userName,u.real_name AS realName,u.org_code AS userCode,c.coin_name AS coinName,w.address AS addressFrom,w.actual_price AS number,w.actual_price AS realNumber ,w.date AS date"+
                " FROM t_inesv_fic_recharge w" + 
                " JOIN t_inesv_user u ON w.user_no = u.user_no" + 
                " JOIN t_inesv_coin_type c ON c.coin_no = w.coin_no"; 
        ArrayList<Object> paramArr = new ArrayList<>();
        
        if(userCode != null && !"".equals(userCode) && !"-1".equals(userCode)) {
        	try {
				userCode = new String(userCode.getBytes("iso-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	String str = sql.contains("where")?" and u.org_code like ?":" where u.org_code like ?";
        	sql += str;
        	paramArr.add("%"+userCode+"%");
        }
        if(phone != null && !"".equals(phone) && !"-1".equals(phone)) {
        	String str = sql.contains("where")?" and u.username like ?":" where u.username like ?";
        	sql += str;
        	paramArr.add("%"+phone+"%");
        }
        
        if(realName != null && !"".equals(realName) && !"-1".equals(realName)) {
        	try {
        		realName = new String(realName.getBytes("iso-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	String str = sql.contains("where")?" and u.real_name like ?":" where u.real_name like ?";
        	sql += str;
        	paramArr.add("%"+realName+"%");
        }
        
        
        if(state != null && !"".equals(state) && !"-1".equals(state)) {
        	String str = sql.contains("where")?" and w.state like ?":" where w.state like ?";
        	sql += str;
        	paramArr.add("%"+state+"%");
        }
        
        if(coinType != null && !"".equals(coinType) && !"-1".equals(coinType)) {
        	String str = sql.contains("where")?" and c.coin_no like ?":" where c.coin_no like ?";
        	sql += str;
        	paramArr.add("%"+coinType+"%");
        }
 
        if(startData != null && !"".equals(startData) && endData != null && !"".equals(endData)){
        	String str = sql.contains("where")?" and w.date between ? and ?":" where w.date between ? and ?";
        	sql += str;
        	Date sdate = Date.valueOf(startData);
        	Date edate = Date.valueOf(endData);
        	paramArr.add(sdate);
        	paramArr.add(edate);
        }
        
        sql +=limitstr;
        paramArr.add(firstRecord);
        paramArr.add(pageSize);
        		
        try {
            list = queryRunner.query(sql, new BeanListHandler<FicRechargeDto>(FicRechargeDto.class),paramArr.toArray(new Object[]{}));
            System.out.println("list========================= "+JSON.toJSONString(list));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("------------sql:"+sql);
        System.out.println("------------paramArr:"+JSON.toJSONString(paramArr));

        return list;
    }


    /**
     *查询所有虚拟币充值信息--总记录数
     */
    public Integer queryAllFicRechargeInfoSize(String userCode, String phone, String realName,String state,String coinType,String startData,String endData){
        List<FicRechargeDto> list = new ArrayList<>();
 
        
        String sql="SELECT w.id AS id,u.username AS userName,u.real_name AS realName,u.org_code AS realName,c.coin_name AS coinName,w.address AS addressFrom,w.actual_price AS number,w.actual_price AS realNumber"+
                " FROM t_inesv_fic_recharge w" + 
                " JOIN t_inesv_user u ON w.user_no = u.user_no" + 
                " JOIN t_inesv_coin_type c ON c.coin_no = w.coin_no"; 
        ArrayList<Object> paramArr = new ArrayList<>();
        
        if(userCode != null && !"".equals(userCode) && !"-1".equals(userCode)) {
        	try {
				userCode = new String(userCode.getBytes("iso-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	String str = sql.contains("where")?" and u.org_code like ?":" where u.org_code like ?";
        	sql += str;
        	paramArr.add("%"+userCode+"%");
        }
        
        if(phone != null && !"".equals(phone) && !"-1".equals(phone)) {
        	String str = sql.contains("where")?" and u.username like ?":" where u.username like ?";
        	sql += str;
        	paramArr.add("%"+phone+"%");
        }
        
        if(realName != null && !"".equals(realName) && !"-1".equals(realName)) {
        	try {
        		realName = new String(realName.getBytes("iso-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	String str = sql.contains("where")?" and u.real_name like ?":" where u.real_name like ?";
        	sql += str;
        	paramArr.add("%"+realName+"%");
        }
        
        
        if(state != null && !"".equals(state) && !"-1".equals(state)) {
        	String str = sql.contains("where")?" and w.state like ?":" where w.state like ?";
        	sql += str;
        	paramArr.add("%"+state+"%");
        }
        
        if(coinType != null && !"".equals(coinType) && !"-1".equals(coinType)) {
        	String str = sql.contains("where")?" and c.coin_no like ?":" where c.coin_no like ?";
        	sql += str;
        	paramArr.add("%"+coinType+"%");
        }
 
        if(startData != null && !"".equals(startData) && endData != null && !"".equals(endData)){
        	String str = sql.contains("where")?" and w.date between ? and ?":" where w.date between ? and ?";
        	sql += str;
        	Date sdate = Date.valueOf(startData);
        	Date edate = Date.valueOf(endData);
        	paramArr.add(sdate);
        	paramArr.add(edate);
        }
        
 
        try {
            list = queryRunner.query(sql, new BeanListHandler<FicRechargeDto>(FicRechargeDto.class),paramArr.toArray(new Object[]{}));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        System.out.println("------queryAllFicRechargeInfoSize------sql:"+sql);
        System.out.println("------queryAllFicRechargeInfoSize------paramArr:"+JSON.toJSONString(paramArr));

        return list.size();
    }
    

    /**
     *查询出所有虚拟币信息
     */
    public List<CoinDto> queryAllCoinType(){
        List<CoinDto> list = new ArrayList<>();
        String sql = "select * from t_inesv_coin_type where coin_no != 0";
        try {
            list = queryRunner.query(sql,new BeanListHandler<CoinDto>(CoinDto.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     *根据用户名查询用户信息
     */
    public UserInfoDto queryUserINfoByUsername(String username){
        UserInfoDto userInfoDto = null;
        String sql = "select * from t_inesv_user where username = ?";
        Object parmas[] = {username};
        try {
            userInfoDto = (UserInfoDto)queryRunner.query(sql,new BeanHandler(UserInfoDto.class),parmas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userInfoDto;
    }

    /**
     *查询充值记录是否够6条
     */
    public List<FicRechargeDto> queryState(int userNo,int coinNo,int state){
        List<FicRechargeDto> list = new ArrayList<>();
        String sql = "select * from t_inesv_fic_recharge where user_no =? and coin_no = ? and state=? and ((select count(*) from t_inesv_fic_recharge where user_no=? and coin_no = ? and state=?) > 5)";
        Object parmas[] = {userNo,coinNo,state,userNo,coinNo,state};
        try {
            list = queryRunner.query(sql,new BeanListHandler<FicRechargeDto>(FicRechargeDto.class),parmas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     *获取充值总额
     */
    public FicRechargeDto querySumPrice(int userNo,int coinNo,int state){
        FicRechargeDto ficRechargeDto = null;
        String sql = "select sum(actual_price) as actual_price from t_inesv_fic_recharge where user_no=? and coin_no = ? and state=? ";
        Object parmas[] = {userNo,coinNo,state};
        try {
            ficRechargeDto = queryRunner.query(sql,new BeanHandler<FicRechargeDto>(FicRechargeDto.class),parmas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ficRechargeDto;
    }


}
