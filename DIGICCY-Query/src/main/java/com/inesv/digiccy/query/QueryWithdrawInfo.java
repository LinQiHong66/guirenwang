package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.FicWithdrawDto;
import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.dto.RmbWithdrawDto;
import com.inesv.digiccy.dto.UserBalanceDto;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/11 0011.
 */
@Component
public class QueryWithdrawInfo {

    @Autowired
    QueryRunner queryRunner;


    /**
     *根据用户查询出用户的提现信息
     *
     */
    public List<FicWithdrawDto> queryWithdrawInfo(int userNo){
        List<FicWithdrawDto> list = new ArrayList<>();
        String sql = "SELECT * FROM t_inesv_fic_withdraw WHERE user_no = ?";
        Object params[]= {userNo};
        try {
            list = (List<FicWithdrawDto>) queryRunner.query(sql,new BeanListHandler(FicWithdrawDto.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**查询出用户的交易密码*/
    public List<InesvUserDto> queryUserDealpwd(int userNo){
        List<InesvUserDto> list = new ArrayList();
        String sql = "select deal_pwd from t_inesv_user where user_no = ?";
        Object parmas[] = {userNo};
        try {
            list = (List<InesvUserDto>) queryRunner.query(sql,new BeanListHandler(InesvUserDto.class),parmas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     *根据用户查询出用户的提现信息
     *
     */
    public List<UserBalanceDto> getUserBalanceAndAddress(Integer userNo, Integer coinNo){
        List<UserBalanceDto> list = new ArrayList<>();
        String sql = "SELECT n.user_no ,d.coin_type,d.enable_coin,n.address as attr2 FROM t_inesv_wallet_address n LEFT JOIN t_inesv_user_balance d ON d.user_no = n.user_no WHERE n.user_no= ? AND d.coin_type = n.coin_no AND n.coin_no = ?";
        Object params[]= {userNo,coinNo};
        try {
            list = (List<UserBalanceDto>) queryRunner.query(sql,new BeanListHandler(UserBalanceDto.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     *根据用户编号查询出用户的提现信息
     *
     */
    public List<FicWithdrawDto> getUserBalanceWithdraw(Integer userNo, Integer coinNo){
        List<FicWithdrawDto> list = new ArrayList<>();
        String sql = "SELECT * FROM t_inesv_fic_withdraw WHERE user_no = ? AND coin_no = ?";
        Object params[]= {userNo,coinNo};
        try {
            list = (List<FicWithdrawDto>) queryRunner.query(sql,new BeanListHandler(FicWithdrawDto.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
