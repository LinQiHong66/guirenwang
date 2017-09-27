package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.AddressDto;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JimJim on 2016/12/16 0016.
 */
@Component
public class QueryAddress {

    @Autowired
    QueryRunner queryRunner;

    public List<AddressDto> queryAddressByUser(String userNo){
        List<AddressDto> addressDtoList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM t_inesv_user_address WHERE user_no = ?";
            addressDtoList = queryRunner.query(sql,new BeanListHandler<>(AddressDto.class),userNo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addressDtoList;
    }

}
