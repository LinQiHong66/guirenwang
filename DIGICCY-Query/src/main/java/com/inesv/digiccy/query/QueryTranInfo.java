package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.TranDto;
import com.inesv.digiccy.dto.UserInfoDto;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/7 0007.
 */
@Component
public class QueryTranInfo {


    @Autowired
    QueryRunner queryRunner;


    /**
     * 查询用户转账记录
     */
    public List<TranDto> queryTranInfo(int userNo,int coinType){
        List<TranDto> list = new ArrayList();
        
        /*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
        /*String sql = "select * from t_inesv_tran where user_no = ? and coin_type = ?"; */
        String sql = "select * from t_inesv_tran where user_no = ? and coin_type = ? ORDER BY id desc"; 
        /*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
        
        //where user_no = ? and coin_type = ?
        Object parmas[] = {userNo,coinType};
        try {
            list = (List<TranDto>) queryRunner.query(sql,new BeanListHandler(TranDto.class),parmas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     *查询是否存在此用户
     */
    public List<UserInfoDto> queryUserInfo(int userNo){
        List<UserInfoDto> list = new ArrayList();
        String sql = "select * from t_inesv_user where user_no = ?";
        Object parmas[] ={userNo};
        try {
            list = (List<UserInfoDto>)queryRunner.query(sql,new BeanListHandler(UserInfoDto.class),parmas);
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }





}
