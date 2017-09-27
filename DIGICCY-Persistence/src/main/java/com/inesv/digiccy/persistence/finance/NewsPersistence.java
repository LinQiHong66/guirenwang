package com.inesv.digiccy.persistence.finance;

import com.inesv.digiccy.dto.NewsDto;
import com.inesv.digiccy.dto.RmbRechargeDto;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
@Component
public class NewsPersistence {

    @Autowired
    QueryRunner queryRunner;


    /**添加新闻信息*/
    public void add(NewsDto newsDto){
        String sql = "INSERT INTO t_inesv_news(news_title,news_content,news_author,DATE,TYPE) VALUES(?,?,?,?,?)";
        Object parmas[] = {newsDto.getNews_title(),newsDto.getNews_content(),newsDto.getNews_author(),newsDto.getDate(),newsDto.getType()};
        try {
            queryRunner.update(sql,parmas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 

}
