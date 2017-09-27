package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.InesvBankInfo;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
@Component
public class QueryBankInfo {

    private static Logger logBank= LoggerFactory.getLogger(QueryBankInfo.class);

    @Autowired
    QueryRunner queryRunner;

    /**
     * 查询所有银行卡信息
     * @return
     */
    public List<InesvBankInfo> getAllBankInfo(){
        String sql = "select b.*,u.username as atte2 from t_inesv_bankinfo b join t_inesv_user u on u.user_no = b.user_no where  delstate!=1";
        List<InesvBankInfo> list = null;
        try {
            list = queryRunner.query(sql, new BeanListHandler<InesvBankInfo>(InesvBankInfo.class));
         
        }catch (SQLException e){
            e.printStackTrace();
            logBank.error("查询银行卡信息失败");
        }
        return list;
    }

    /**
     * 查询银行卡信息
     * @param userNo
     * @return
     */
    public List<InesvBankInfo> getBankInfo(String userNo){
        String sql = "select * from t_inesv_bankinfo where user_no = ? and  delstate!=1";
        Object params[] = {userNo};
        List<InesvBankInfo> list = null;
        try {
            list = queryRunner.query(sql, new BeanListHandler<InesvBankInfo>(InesvBankInfo.class),params);
        }catch (SQLException e){
            e.printStackTrace();
            logBank.error("查询银行卡信息失败");
        }
      return list;
    }
    
    /**
     * 查询银行卡信息
     * @param userNo
     * @return
     */
    public InesvBankInfo getBankInfoById(Long id){
        String sql = "select * from t_inesv_bankinfo where id = ? and  delstate!=1";
        Object params[] = {id};
        InesvBankInfo inesvBank = null;
        try {
        	inesvBank = queryRunner.query(sql, new BeanHandler<InesvBankInfo>(InesvBankInfo.class),params);
        }catch (SQLException e){
            e.printStackTrace();
            logBank.error("根据id查询银行卡信息失败");
        }
      return inesvBank;
    }
}
