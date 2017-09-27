package com.inesv.digiccy.persistence.operation;

import com.inesv.digiccy.dto.BillDto;
import com.inesv.digiccy.util.ObjectChangeUtil;
import com.inesv.digiccy.util.TableName;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
@Component
public class BillPer {

    @Autowired
    private QueryRunner queryRunner;

    /**
     * 插入话费充值记录
     * @param billDto
     */
    public void billRecharge(BillDto billDto){
        String sql = new ObjectChangeUtil<BillDto>().objectToSql(billDto, TableName.T_INESV_BILL);
        Object params[] = new ObjectChangeUtil<BillDto>().objectToArray(billDto);
        try {
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改电话充值记录
     * */
    public int updateBillRechargeRecord(String recharge_phone,Integer id){
        String sql="update t_inesv_bill set state = 1, handle_date = now() where recharge_phone = ? and id = ?";
        Object params[] = {recharge_phone,id};
        int count = 0;
        try {
            count=queryRunner.update(sql, params);
            return count;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 修改用户余额信息
     * @param enableRmb
     * @param userNo
     * @param coinType
     */
    public void editUserBalance(BigDecimal enableRmb,Integer userNo,Integer coinType){
        String sql = "update t_inesv_user_balance set enable_coin = ? where user_no = ? and coin_type = ?";
        Object params[] = {enableRmb,userNo,coinType};
        try {
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBillState(int state,Integer user_no){
        String sql = "update t_inesv_bill set sate=? where user_no=?";
        Object params[] = {state,user_no};
        try{
            queryRunner.update(sql,params);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
