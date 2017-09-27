package com.inesv.digiccy.query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.HomeImgDto;

/**
 * 查询轮播图的类
 * @author LiuKeLing
 *
 */
@Component
public class QueryHomeImg {
    private static Logger logger = LoggerFactory.getLogger(QueryHomeImg.class);

    @Autowired
    private QueryRunner queryRunner;
    //获取所有的轮播图
    public List<HomeImgDto> getAllhomeImg(){
    	List<HomeImgDto> imgs = new ArrayList<HomeImgDto>();
    	String sql = "select id,img_description as imgDescription,img_url as imgUrl from t_home_img";
    	try {
			imgs = queryRunner.query(sql, new BeanListHandler<>(HomeImgDto.class));
		} catch (SQLException e) {
			logger.error("查询所有首页轮播图失败");
			e.printStackTrace();
		}
    	return imgs;
    }
    //根据id查询轮播图
    public HomeImgDto findById(int id){
    	HomeImgDto img = new HomeImgDto();
    	String sql = "select id,img_description as imgDescription,img_url as imgUrl from t_home_img where id=?";
    	Object[] param = {id};
    	try{
    		img = queryRunner.query(sql, new BeanHandler<>(HomeImgDto.class), param);
    	}catch(Exception e){
    		e.printStackTrace();
    		return null;
    	}
    	return img;
    }
}
