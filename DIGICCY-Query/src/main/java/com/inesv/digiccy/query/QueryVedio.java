package com.inesv.digiccy.query;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.VedioDto;

/**
 * 
 * @author Liukeling
 *
 */
@Component
public class QueryVedio {
	@Autowired
	QueryRunner queryRunner;
	
	//获取首页视频
	public VedioDto getHomeVedio(){
		VedioDto vedioDto = new VedioDto();
		String sql = "select vedio_name as name, vedio_url as url, vedio_info as info from t_inesv_vedio where id=?";
		Object[] params = new Object[]{-1};
		try {
			vedioDto = queryRunner.query(sql, new BeanHandler<VedioDto>(VedioDto.class), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vedioDto;
	}
}
