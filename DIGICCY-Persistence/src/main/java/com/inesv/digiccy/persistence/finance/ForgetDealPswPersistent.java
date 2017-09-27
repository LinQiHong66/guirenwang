package com.inesv.digiccy.persistence.finance;

import com.inesv.digiccy.dto.UserInfoDto;
import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * Created by yc on 2016/12/12 0012.
 */
@Component
public class ForgetDealPswPersistent {

    private static Logger logger = LoggerFactory.getLogger(ForgetDealPswPersistent.class);

    @Autowired
    QueryRunner queryRunner;

    /**
     *修改交易密码
     */
    public void updateDealPwd(String dealPwd,String username){

        String sql = "UPDATE t_inesv_user set deal_pwd = ?,deal_pwdstate=1 where username=?";
        Object parmas[] = {dealPwd,username};
        try {
            queryRunner.update(sql,parmas);
        } catch (SQLException e) {
            logger.error("交易密码修改失败");
            e.printStackTrace();
        }
    }

}
