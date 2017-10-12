package com.inesv.digiccy.query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.ContactDto;

/**
 * Created by Liukeling
 */
@Component
public class QueryContact {
	private static Logger logger = LoggerFactory.getLogger(QueryBonus.class);

    @Autowired
    private QueryRunner queryRunner;
    
    /**
     * 根据条件查询
     * @param field 字段名
     * @param value 字段值
     * @return
     */
    public List<ContactDto> getAllContact(String field, String value){
    	String sql = "select id as id, email as email, wx as weixin, wx_qrcord as wxqrcord, address as address, remark as remark, "
    			+ " qq as qq,qq_qrcord as qqqrcord, telphone as telphone, authority_account as authority_account from t_inesv_contact";
    	ArrayList<ContactDto> list = null;
    	try {
    		Object params[];
    		if(field != null && !field.isEmpty() && value != null && !value.isEmpty()){
    			sql += " where "+field+"=?";
    			params = new Object[]{value};
    		}else{
    			params = new Object[]{};
    		}
			list = (ArrayList<ContactDto>) queryRunner.query(sql, new BeanListHandler<ContactDto>(ContactDto.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("查询联系方式失败");
		}
    	return list;
    }
    
}
