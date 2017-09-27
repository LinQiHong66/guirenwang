package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.FicWithdrawDto;
import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.dto.UserBalanceDto;
import com.inesv.digiccy.dto.UserInfoDto;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public List<FicWithdrawDto> queryAllFicWithdrawInfo(String userName, String coinTypeSearch, String startData,String endData){
        List<FicWithdrawDto> list = null;
        String sql="select w.*,u.username as attr1,c.coin_name as attr2 from t_inesv_fic_withdraw w " +
                "join t_inesv_user u on w.user_no = u.user_no " +
                "join t_inesv_coin_type c on c.coin_no = w.coin_no ";
        ArrayList<Object> paramArr = new ArrayList<>();
        if(userName != null && !"".equals(userName) && !"-1".equals(userName)){
        	String str = sql.contains("where")?" and u.user_no=?":" where u.user_no=?";
        	sql += str;
        	paramArr.add(userName);
        }
        if(coinTypeSearch != null && !"".equals(coinTypeSearch) && !"-1".equals(coinTypeSearch)){
        	String str = sql.contains("where")?" and c.coin_no=?":" where c.coin_no=?";
        	sql += str;
        	paramArr.add(coinTypeSearch);
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
        return list;
    }


}
