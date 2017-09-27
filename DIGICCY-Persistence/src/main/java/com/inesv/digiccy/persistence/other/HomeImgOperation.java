package com.inesv.digiccy.persistence.other;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.HomeImgDto;
import com.inesv.digiccy.persistence.notice.NoticeOperation;

/**
 * 
 * @author Liukeling
 *
 */
@Component
public class HomeImgOperation {
    private static Logger logger = LoggerFactory.getLogger(HomeImgOperation.class);
    
    @Autowired
    QueryRunner queryRunner;
    //添加图片
    public void addHomeImg(HomeImgDto homeImgDto){
    	String sql = "insert into t_home_img (img_description,img_url) values (?,?)";
    	Object[] params = {homeImgDto.getImgDescription(),homeImgDto.getImgUrl()};
    	try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("添加轮播图失败");
			e.printStackTrace();
		}
    }
    //修改
    public void modifyHomeImg(HomeImgDto homeImgDto){
    	String url = homeImgDto.getImgUrl();
    	String sql = "update t_home_img set "+("none".equals(url)?"":"img_url='"+url+"' , ")+" img_description=? where id=?";
    	Object[] params = {homeImgDto.getImgDescription(),homeImgDto.getId()};
    	try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("添加轮播图失败");
			e.printStackTrace();
		}
    }
    //删除
    public void delHomeImg(int id){
    	String sql = "delete from t_home_img where id=?";
    	Object[] params={id};
    	try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("删除轮播图失败");
			e.printStackTrace();
		}
    }
}
