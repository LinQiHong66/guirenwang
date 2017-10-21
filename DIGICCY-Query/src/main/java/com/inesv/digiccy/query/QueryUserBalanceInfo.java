package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.CoinCountDto;
import com.inesv.digiccy.dto.FicWithdrawDto;
import com.inesv.digiccy.dto.UserBalanceDto;
import com.inesv.digiccy.dto.UserInfoDto;
import com.inesv.digiccy.dto.WalletAddressDto;
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
 * 查询用户拥有的资源
 * Created by JimJim on 2016/11/4 0004.
 */
@Component
public class QueryUserBalanceInfo {

	private static Logger logger = LoggerFactory.getLogger(QueryUserBalanceInfo.class);
	
    @Autowired
    QueryRunner queryRunner;


 
    
    /**
     * 获取钱包地址
     * @paramuserAuthoritys
     * @return
     * @throws SQLException
     */
    public List<String> getBalanceByCoinType(Integer coinType){
    	List<String> res=new ArrayList<>();
    	List<UserBalanceDto> userBalanceInfos=null;
        String querySql = "SELECT *FROM t_inesv_user_balance WHERE coin_type = ? ";
        Object params[] = {coinType};
        try {
            userBalanceInfos=queryRunner.query(querySql,new BeanListHandler<UserBalanceDto>(UserBalanceDto.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("根据货币种类查询认购中心失败");
        }
        for(UserBalanceDto ub : userBalanceInfos){
        	if(ub.getWallet_address()!=null&&!ub.getWallet_address().equals("")){
        		res.add(ub.getWallet_address());
        	}
        }
        return res;
    }
    
    /**
     * 根据钱包地址和币种获取用户资产信息
     * @paramuserAuthoritys
     * @return
     * @throws SQLException
     */
    public UserBalanceDto getBalanceByAddressAndCoinType(String address,Integer coinType){
        UserBalanceDto userBalanceInfo=null;
        String querySql = "SELECT *FROM t_inesv_user_balance WHERE  wallet_address = ? AND coin_type= ? ";
        Object params[] = {address,coinType};
        try {
            userBalanceInfo=queryRunner.query(querySql,new BeanHandler<UserBalanceDto>(UserBalanceDto.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("根据货币种类查询认购中心失败");
        }
        return userBalanceInfo;
    }
    
    /**
     * 根据用户查询用户的财务信息
     * @paramuserAuthoritys
     * @return
     * @throws SQLException
     */
 
    public UserBalanceDto queryUserBalanceInfoByUserNoAndCoinType(String userNo,String coinType){
        UserBalanceDto userBalanceInfo=null;
        String querySql = "select * from t_inesv_user_balance where user_no=? and coin_type=? for update";
        Object params[] = {userNo,coinType};
        try {
            userBalanceInfo=queryRunner.query(querySql,new BeanHandler<UserBalanceDto>(UserBalanceDto.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("根据货币种类查询认购中心失败");
        }
        return userBalanceInfo;
    }
    
    /**
     * 交易中心-根据用户查询用户的财务信息
     * @paramuserAuthoritys
     * @return
     * @throws SQLException
     */
    public List<UserBalanceDto> queryUserBalanceInfoByUserNoAndCoinTypeDealDetail(String userNo,String coinType){
    	List<UserBalanceDto> userBalanceInfo= new ArrayList<UserBalanceDto>();
        StringBuffer querySql = new StringBuffer();
        String coinTypes[] = coinType.split(",");
        querySql.append("select * from t_inesv_user_balance where user_no=? and coin_type in ( ");
        	for(int i=0;i<coinTypes.length;i++){
            	querySql.append(coinTypes[i]).append(",");
            }
        querySql.delete(querySql.length()-1, querySql.length()).append(")");
        Object params[] = {userNo};
        try {
            userBalanceInfo=queryRunner.query(querySql.toString(),new BeanListHandler<UserBalanceDto>(UserBalanceDto.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("根据货币种类查询认购中心失败");
        }
        return userBalanceInfo;
    }

    /**
     *查看用户是否有钱包地址
     */
    public List<UserBalanceDto> queryWallteAddress(int userNo,int coinNO){
        List<UserBalanceDto> list = null;
        String sql = "select address from t_inesv_wallet_address where user_no = ? and coin_no = ?";
        Object parmas[] = {userNo,coinNO};
        try {
            list = (List<UserBalanceDto>)queryRunner.query(sql,new BeanHandler<UserBalanceDto>(UserBalanceDto.class),parmas);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("查询用户钱包地址失败");
        }
        return list;
    }

    /**
     *查询用户相应币种的可用余额
     */
    public UserBalanceDto queryEnableCoin(int userNo,int coinNo){
        UserBalanceDto userBalanceInfo = null;
        String sql = "select * from t_inesv_user_balance where user_no = ? and coin_type = ?";
        //where user_no = ? and coin_no = ?
        Object params[] = {userNo,coinNo};
        try {
            userBalanceInfo=(UserBalanceDto)queryRunner.query(sql,new BeanHandler(UserBalanceDto.class),params);
            return userBalanceInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userBalanceInfo;
    }

    /**
     * 查询用户的交易密码
     */
    public UserInfoDto queryDeaPSW(int userNo){
        String sql = "select * from t_inesv_user where user_no = ?";
        Object parmas[] = {userNo};
        UserInfoDto userInfoDto = null;
        try {
            userInfoDto = (UserInfoDto)queryRunner.query(sql,new BeanHandler(UserInfoDto.class),parmas);
            return userInfoDto;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userInfoDto;
    }


    /**
     * 查询所有用户的钱包地址
     * @param condition
     * @param value
     * @return
     */
    public List<WalletAddressDto> queryAllUserWallet(String userCode, String phone, String realName,String coinType,String startData,String endData,pageDto page){
        List<WalletAddressDto> userBalanceInfo=null;
        String limitstr = " limit ?,? ";
        Integer firstRecord = page.getFirstRecord();
        Integer pageSize = page.getPageSize();
        
        String sql = " SELECT w.id AS id,u.username AS userName,u.real_name AS realName,u.org_code AS userCode,c.coin_name AS coinName,w.address AS address,w.date AS DATE,w.idtf AS addressTag" + 
        		"	FROM t_inesv_wallet_address w  " + 
        		"	JOIN t_inesv_user u ON w.user_no = u.user_no " + 
        		"	JOIN t_inesv_coin_type c ON c.coin_no = w.coin_no where c.coin_no!=0 ";
      
        ArrayList<Object> paramArr = new ArrayList<>();
        
        if(userCode != null && !"".equals(userCode) && !"-1".equals(userCode)) {
        	try {
				userCode = new String(userCode.getBytes("iso-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				 
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
        	userBalanceInfo = queryRunner.query(sql, new BeanListHandler<>(WalletAddressDto.class),paramArr.toArray(new Object[]{}));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userBalanceInfo;
    }

    
    /**
     * 查询所有用户的钱包地址--总记录
     * @param condition
     * @param value
     * @return
     */
    public Integer queryAllUserWalletSize(String userCode, String phone, String realName,String coinType,String startData,String endData){
        WalletAddressDto  wallet=null;
 
        
        String sql = "SELECT  COUNT(*) AS size FROM t_inesv_wallet_address w JOIN t_inesv_user u ON w.user_no = u.user_no JOIN t_inesv_coin_type c ON c.coin_no = w.coin_no where  c.coin_no!=0 ";
      
        ArrayList<Object> paramArr = new ArrayList<>();
        
        if(userCode != null && !"".equals(userCode) && !"-1".equals(userCode)) {
        	try {
				userCode = new String(userCode.getBytes("iso-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				 
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
         
        	wallet = queryRunner.query(sql, new BeanHandler<>(WalletAddressDto.class),paramArr.toArray(new Object[]{}));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wallet.getSize();
    }


    /**
     * 根据用户查询用户的财务信息
     * @paramuserAuthoritys
     * @return
     * @throws SQLException
     */
    public List<UserBalanceDto> queryUserBalanceInfoByUserNo(String userNo){
        List<UserBalanceDto> list = null;
        String querySql = "select * from t_inesv_user_balance where user_no=?";
        Object params[] = {userNo};
        try {
            list=(List<UserBalanceDto>)queryRunner.query(querySql,new BeanListHandler(UserBalanceDto.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("根据用户编号查询用户各个币种的资产信息");
        }
        return list;
    }



    /**
     * 查询各种币在平台的总额
     * @return
     */
    public List<CoinCountDto> queryCoinCount(){
        List<CoinCountDto> coinCountDtos=null;
        String querySql = "SELECT c.coin_no AS coinId,c.coin_name AS coinname,IFNULL(SUM(total_price),0)  AS total " +
                "FROM t_inesv_user_balance b " +
                "RIGHT JOIN t_inesv_coin_type c ON c.coin_no = b.coin_type " +
                "GROUP BY coin_no";
        try {
            coinCountDtos=queryRunner.query(querySql,new BeanListHandler<>(CoinCountDto.class));
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("查询各种币在平台的总额失败");
        }
        return coinCountDtos;
    }

    /**
     * 查询各种币在平台的总额
     * @return
     */
    public List<CoinCountDto> queryCoinCount(Integer coinId){
        List<CoinCountDto> coinCountDtos=null;
        String querySql = "SELECT c.id AS coinId,c.coin_name AS coinname,IFNULL(SUM(total_price),0)  AS total " +
                "FROM t_inesv_user_balance b " +
                "RIGHT JOIN t_inesv_coin_type c ON c.coin_no = b.coin_type " +
                "WHERE c.id = ? " +
                "GROUP BY coin_type";
        Object params[] = {coinId};
        try {
            coinCountDtos=queryRunner.query(querySql,new BeanListHandler<>(CoinCountDto.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("查询各种币在平台的总额失败");
        }
        return coinCountDtos;
    }

    /**
     *查询用户是否有此币种
     */
    public UserBalanceDto queryTranUserCoinType(int userNo,int coinType){
        UserBalanceDto userBalanceInfo = null;
        String querySql = "select * from t_inesv_user_balance where user_no=? and coin_type=?";
        Object params[] = {userNo,coinType};
        try {
            userBalanceInfo=queryRunner.query(querySql,new BeanHandler<UserBalanceDto>(UserBalanceDto.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("根据货币种类查询用户是否持有此币种失败");
        }
        return userBalanceInfo;

    }


    /**
     *根据钱包地址查询出用户信息
     */
    public UserBalanceDto queryUserInfoByAddress(String address){
        UserBalanceDto userBalanceInfo = null;
        String sql = "select * from t_inesv_user_balance where wallet_address = ?";
        Object parmas[] = {address};
        try {
            userBalanceInfo = queryRunner.query(sql,new BeanHandler<UserBalanceDto>(UserBalanceDto.class),parmas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userBalanceInfo;
    }

    /**
     *货币统计
     */
    public UserBalanceDto queryUserBalanceCount(Integer coin){
        UserBalanceDto userBalanceInfo = null;
        String sql = "SELECT t1.coin_type AS coin_type, t2.coin_name AS coinName , IFNULL(SUM(t1.total_price),0) AS total_price , IFNULL(SUM(t1.unable_coin),0) AS unable_coin , IFNULL(SUM(t1.enable_coin),0) AS enable_coin "
        		+ "FROM t_inesv_user_balance t1 , t_inesv_coin_type t2 WHERE coin_type = ? AND t1.coin_type = t2.coin_no";
        Object parmas[] = {coin};
        try {
            userBalanceInfo = queryRunner.query(sql,new BeanHandler<UserBalanceDto>(UserBalanceDto.class),parmas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userBalanceInfo;
    }
    
    /**
     *货币统计报表
     * @throws Exception 
     */
    public List<UserBalanceDto> queryUserCoinCount(Integer coin) throws Exception{
    	List<UserBalanceDto> list = new ArrayList<UserBalanceDto>();
    	UserBalanceDto dto = new UserBalanceDto();
    	String sql = null;
    	for(int i = 15 ; i >= 0 ; i -- ) {
    		sql = "SELECT * FROM (" + 
    					"(SELECT IFNULL(SUM(t1.total_price),0) AS total_price FROM t_inesv_user_balance t1 WHERE t1.coin_type = ? AND DATE_FORMAT(t1.date, '%y-%m-%d') <= DATE_FORMAT(DATE_SUB(NOW(), INTERVAL " + i + " DAY), '%y-%m-%d')) AS a," + 
    					"(SELECT IFNULL(SUM(t2.deal_num),0) AS attr1 FROM t_inesv_deal_detail t2 WHERE t2.coin_no = ? AND deal_type = 1 AND DATE_FORMAT(t2.date, '%y-%m-%d') <= DATE_FORMAT(DATE_SUB(NOW(), INTERVAL " + i + " DAY), '%y-%m-%d')) AS b," + 
    					"(SELECT IFNULL(SUM(t3.actual_price),0) AS attr2 ,DATE_FORMAT(DATE_SUB(NOW(), INTERVAL " + i + " DAY),'%y-%m-%d') AS dates FROM t_inesv_fic_recharge t3  WHERE t3.coin_no = ? AND DATE_FORMAT(t3.date, '%y-%m-%d') <= DATE_FORMAT(DATE_SUB(NOW(), INTERVAL " + i + " DAY), '%y-%m-%d')) AS c" + 
    				")";
    		Object parmas[] = {coin,coin,coin};
    		dto = queryRunner.query(sql,new BeanHandler<UserBalanceDto>(UserBalanceDto.class),parmas);
    		list.add(dto);
    	}
        return list;
    }

}
