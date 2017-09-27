package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.BillDto;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
@Component
public class QueryBill {

    private static Logger logger = LoggerFactory.getLogger(QueryBill.class);

    @Autowired
    private QueryRunner queryRunner;

    /**
     * create by huguokai date:2016年11月16日15:17:49
     * 查询话费充值记录
     * @param userNo
     * @return list
     */
    public List<BillDto> getBillInfo(Integer userNo){
        String sql = "select * from t_inesv_bill where user_no = ?";
        Object params[] = {userNo};
        List<BillDto> list = null;
        try {
            list = queryRunner.query(sql,new BeanListHandler<BillDto>(BillDto.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("查询话费充值记录失败");
        }
        return list;
    }

    /**
     * 查询所有话费充值记录
     * */
    public List<BillDto> getAllBill(){
        List<BillDto> list = new ArrayList<>();
        try {
            String sql = "select * from t_inesv_bill";
            list = queryRunner.query(sql, new BeanListHandler<>(BillDto.class));
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     * create by huguokai date:2016年11月16日15:17:49
     * 查询话费充值记录
     * @return list
     */
    public List<BillDto> getBillInfo(){
        String sql = "select b.*,u.username as attr1 from t_inesv_bill b join t_inesv_user u " +
                "on b.user_no = u.user_no";
        List<BillDto> list = null;
        try {
            list = queryRunner.query(sql,new BeanListHandler<BillDto>(BillDto.class));
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("查询话费充值记录失败");
        }
        return list;
    }

    public BillDto getBillinfoByIdAndPhone(Integer id,String phone){
        String sql = "select * from t_inesv_bill where id = ? and recharge_phone = ?";
        BillDto list = null;
        Object params[] = {id,phone};
        try {
            list = queryRunner.query(sql,new BeanHandler<BillDto>(BillDto.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("查询话费充值记录失败");
        }
        return list;
    }

    //-----------------------
    public List<BillDto> getBillAll() 
    {
    	List<BillDto> billDtos=new ArrayList<BillDto>();
    	try {
			billDtos=queryRunner.query("SELECT *FROM t_inesv_bill",new BeanListHandler<BillDto>(BillDto.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return billDtos;
    }

}
