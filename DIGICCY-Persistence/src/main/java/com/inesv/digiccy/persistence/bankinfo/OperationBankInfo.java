package com.inesv.digiccy.persistence.bankinfo;

import com.inesv.digiccy.dto.InesvBankInfo;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
@Component
public class OperationBankInfo {

    @Autowired
    QueryRunner queryRunner;

    /**
     * 查询银行卡信息
     * @param inesvBankInfo
     * @throws SQLException
     */
    public InesvBankInfo queryBankInfo(int id){
        String SQL = "SELECT *FROM t_inesv_bankinfo WHERE id= ? and delstate!=1";
        Object params[] = {id};
        InesvBankInfo bankinfo=null;
		try {
			bankinfo = queryRunner.query(SQL,new BeanHandler<>(InesvBankInfo.class), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return bankinfo;
    }
    
    /**
     * 添加银行卡地址
     * @param inesvBankInfo
     * @throws SQLException
     */
    public void insertBankInfo(InesvBankInfo inesvBankInfo) throws SQLException {
        String insert = "INSERT into t_inesv_bankinfo(user_no,remark_name,bank,province,city,branch,name,bank_num,date,state)VALUES(?,?,?,?,?,?,?,?,?,?)";
        Object params[] = { inesvBankInfo.getUser_no(),
                            inesvBankInfo.getRemark_name(),
                            inesvBankInfo.getBank(),
                            inesvBankInfo.getProvince(),
                            inesvBankInfo.getCity(),
                            inesvBankInfo.getBranch(),
                            inesvBankInfo.getName(),
                            inesvBankInfo.getBank_num(),
                            inesvBankInfo.getDate(),
                            inesvBankInfo.getState()
                       };
        queryRunner.update(insert,params);
    }

    /**
     * 修改银行卡地址
     * @param inesvBankInfo
     * @throws SQLException
     */
    public void updateBankinfo(InesvBankInfo inesvBankInfo) throws SQLException {
        String update = "update t_inesv_bankinfo set remark_name = ?,bank = ?,province = ?,city = ?,branch = ?,name = ?,bank_num = ?,date = ? where id = ?";
        Object params[] = {
                  inesvBankInfo.getRemark_name(),
                  inesvBankInfo.getBank(),
                  inesvBankInfo.getProvince(),
                  inesvBankInfo.getCity(),
                  inesvBankInfo.getBranch(),
                  inesvBankInfo.getName(),
                  inesvBankInfo.getBank_num(),
                  inesvBankInfo.getDate(),
                  inesvBankInfo.getId()
        };
        queryRunner.update(update,params);
    }
    
    /**
     * 修改银行卡地址
     * @param inesvBankInfo
     * @throws SQLException
     */
    public void updateBankinfoById(InesvBankInfo inesvBankInfo) throws SQLException {
        String update = "update t_inesv_bankinfo set remark_name = ?,bank = ?,province = ?,city = ?,branch = ?,name = ?,bank_num = ?,date = ? where id = ?";
        Object params[] = {
                  inesvBankInfo.getRemark_name(),
                  inesvBankInfo.getBank(),
                  inesvBankInfo.getProvince(),
                  inesvBankInfo.getCity(),
                  inesvBankInfo.getBranch(),
                  inesvBankInfo.getName(),
                  inesvBankInfo.getBank_num(),
                  inesvBankInfo.getDate(),
                  inesvBankInfo.getId()
        };
        queryRunner.update(update,params);
    }

    /**
     * 删除银行卡地址
     * @param inesvBankInfo
     * @throws SQLException
     */
    public void deleteBankInfo(InesvBankInfo inesvBankInfo) throws SQLException {
        String delete = "update t_inesv_bankinfo set  delstate=1 where id = ?";
        Object params[] = {inesvBankInfo.getId()};
        queryRunner.update(delete, params);
    }
}
