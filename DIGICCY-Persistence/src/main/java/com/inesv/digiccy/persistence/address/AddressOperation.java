package com.inesv.digiccy.persistence.address;

import com.inesv.digiccy.dto.AddressDto;
import com.inesv.digiccy.util.ObjectChangeUtil;
import com.inesv.digiccy.util.TableName;
import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * Created by JimJim on 2016/12/16 0016.
 */
@Component
public class AddressOperation {

    private static Logger logger = LoggerFactory.getLogger(AddressOperation.class);

    @Autowired
    QueryRunner queryRunner;

    /**
     * 新增地址
     * @param address
     */
    public void insertAddress(AddressDto address){
        String sql = new ObjectChangeUtil<AddressDto>().objectToSql(address, TableName.T_INESV_USER_ADDRESS);
        Object params[] = new ObjectChangeUtil<AddressDto>().objectToArray(address);
        try {
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            logger.error("新增地址错误");
            e.printStackTrace();
        }
    }

    
    /**
     * 修改地址
     * @param address
     */
    public void updateAddress(AddressDto address){
        String sql = "UPDATE t_inesv_user_address SET name = ? ,phone = ?,address = ? WHERE id = ?";
        Object params[] = {address.getName(),address.getPhone(),address.getAddress(),address.getId()};
        try {
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            logger.error("修改地址错误");
            e.printStackTrace();
        }
        //删除该用户的其他地址
        String sql1 = "delete from t_inesv_user_address where user_no=? and id not in (?)";
        Object[] params1 = {address.getUser_no(), address.getId()};
        try {
            queryRunner.update(sql1,params1);
        } catch (SQLException e) {
            logger.error("删除其他地址失败");
            e.printStackTrace();
        }
    }
    
    /**
     * 修改地址
     * @param address
     */
    public void updateAddressByUser(AddressDto address){
        String sql = "UPDATE t_inesv_user_address SET name = ? ,phone = ?, remark_address = ?, address = ?, card = ? WHERE user_no = ?";
        Object params[] = {address.getName(),address.getPhone(),address.getRemark_address(),address.getAddress(),address.getCard(),address.getUser_no()};
        try {
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            logger.error("修改地址错误");
            e.printStackTrace();
        }
    }

    /**
     * 删除地址
     * @param address
     */
    public void deleteAddress(AddressDto address){
        String sql = "DELETE FROM t_inesv_user_address WHERE id = ?";
        Object params[] = {address.getId()};
        try {
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            logger.error("删除地址错误");
            e.printStackTrace();
        }
    }

}
