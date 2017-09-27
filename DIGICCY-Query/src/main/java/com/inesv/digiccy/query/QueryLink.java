package com.inesv.digiccy.query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.LinkDto;

/**
 * 查询友情链接的类
 * @author LiuKeLing
 *
 */
@Component
public class QueryLink {
    private static Logger logger = LoggerFactory.getLogger(QueryLink.class);

    @Autowired
    private QueryRunner queryRunner;
    
    public List<LinkDto> getAllLink(String filed, String value){
    	ArrayList<LinkDto> list = new ArrayList<>();
    	String sql = "select id as id, link_name as linkName, link_url as linkUrl,link_type as linkType from t_inesv_link";
    	Object[] params;
    	if(filed != null && !filed.isEmpty() && value != null && !value.isEmpty()){
    		sql += " where "+filed+"=?";

    		params = new Object[]{value};
    	}else{
    		params = new Object[]{};
    	}
    	try {
			list.addAll(queryRunner.query(sql, new BeanListHandler<>(LinkDto.class), params));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("查询友情链接出错");
		}
    	return list;   
    }
    public Map<String, Object> getGroupLink(){
    	HashMap<String, Object> map = new HashMap<>();
    	String typeSql = "select distinct link_type  from t_inesv_link";
    	String sql = "select id as id, link_name as linkName, link_url as linkUrl,link_type as linkType from t_inesv_link";
    	try {
    		List<String> obs = (List<String>) queryRunner.query(typeSql, new ColumnListHandler());
			for(String s : obs){
				String mysql = sql+ " where link_type=?";
				Object[] pa = {s};
				List<LinkDto> list = (List<LinkDto>) queryRunner.query(mysql, new BeanListHandler<LinkDto>(LinkDto.class), pa);
				map.put(s, list);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return map;
    }
}
