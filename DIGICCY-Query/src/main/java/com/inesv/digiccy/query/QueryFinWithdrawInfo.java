package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.FicWithdrawDto;
import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.dto.UserBalanceDto;
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
 * Created by Administrator on 2016/12/1 0001.
 */
@Component
public class QueryFinWithdrawInfo {

    private static Logger logger = LoggerFactory.getLogger(QueryFinWithdrawInfo.class);

    @Autowired
    QueryRunner queryRunner;

    /**
     *根据id，查询提现信息
     */
    public FicWithdrawDto queryFicWithdrawInfoById(Integer id){
        FicWithdrawDto fic = new FicWithdrawDto();
        String sql="SELECT *FROM t_inesv_fic_withdraw WHERE id = ? ";
        Object parmas[] = {id};
        try {
        	fic =  (FicWithdrawDto)queryRunner.query(sql, new BeanHandler(FicWithdrawDto.class),parmas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fic;
    }
    
    
    /**
     *根据用户编号和币种查询相应用户的虚拟币提现信息
     */
    public List<FicWithdrawDto> queryFicWithdrawInfo(int userNo,int cointype){
        List<FicWithdrawDto> list = null;
        String sql="select * from t_inesv_fic_withdraw where user_no = ? and coin_no = ?";
        Object parmas[] = {userNo,cointype};
        try {
            list = (List<FicWithdrawDto>)queryRunner.query(sql, new BeanListHandler(FicWithdrawDto.class),parmas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 查询用户的交易密码
     */
    public UserInfoDto queryDeaPSW(int userNo){
        String sql = "select * from t_inesv_user where user_no = ?";
        Object parmas[] = {userNo};
        UserInfoDto userInfoDto = null;
        try {
            userInfoDto = queryRunner.query(sql,new BeanHandler<UserInfoDto>(UserInfoDto.class),parmas); 
            return userInfoDto;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userInfoDto;
    }

    /**
     *查询所有虚拟币提现信息
     */
    public List<FicWithdrawDto> queryAllFicWithdrawInfo(String userCode, String phone, String realName,String state,String coinType,String startData,String endData,pageDto page){
        List<FicWithdrawDto> list = null;
        String limitstr = " limit ?,? ";
        Integer firstRecord = page.getFirstRecord();
        Integer pageSize = page.getPageSize();
        String sql="SELECT w.id AS id,u.username AS userName,u.real_name AS realName,u.org_code AS userCode,c.coin_name AS coinName,w.address AS addressFrom,w.coin_sum AS number,w.actual_price AS realNumber ,w.date AS DATE,poundage AS poundage,w.sate AS sate"+
                " FROM t_inesv_fic_withdraw w " +
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
        	String str = sql.contains("where")?" and w.sate like ?":" where w.sate like ?";
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
        	System.out.println("**************sql**********: "+sql);
            list = queryRunner.query(sql, new BeanListHandler<>(FicWithdrawDto.class),paramArr.toArray(new Object[]{}));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }



    /**
     *查询所有虚拟币提现信息--总记录数
     */
    public Integer queryAllFicWithdrawInfoSize(String userCode, String phone, String realName,String state,String coinType,String startData,String endData){
        List<FicWithdrawDto> list = null;
 
        String sql="SELECT w.id AS id,u.username AS userName,u.real_name AS realName,u.org_code AS userCode,c.coin_name AS coinName,w.address AS addressFrom,w.coin_sum AS number,w.actual_price AS realNumber ,w.date AS DATE,poundage AS poundage"+
                " FROM t_inesv_fic_withdraw w " +
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
        	String str = sql.contains("where")?" and w.sate like ?":" where w.sate like ?";
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
        	System.out.println("**************sql**********: "+sql);
            list = queryRunner.query(sql, new BeanListHandler<>(FicWithdrawDto.class),paramArr.toArray(new Object[]{}));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.size();
    }
    
    
}
