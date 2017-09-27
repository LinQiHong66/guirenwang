
package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.AssessCountDto;
import com.inesv.digiccy.dto.AssessDto;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/9 0009.
 */
@Component
public class QueryAssessInfo {
    private static Logger log = LoggerFactory.getLogger(QueryAssessInfo.class);

    @Autowired
    private QueryRunner queryRunner;

    /**
     * 查询评论
     * @return
     */
    public List<AssessDto> queryAssessList(){
        List<AssessDto> assessList = new ArrayList<>();
        String sql = "select a.*,c.coin_name as attr1,u.username as attr2 from t_inesv_assess a " +
                "join t_inesv_coin_type c on a.coin_no = c.coin_no " +
                "join t_inesv_user u on u.user_no = a.username";
        try {
			assessList =queryRunner.query(sql,new BeanListHandler<AssessDto>(AssessDto.class));
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("查询评论失败");
		}
        return assessList;

    }

    public List<AssessCountDto> queryAssessCount(){
        List<AssessCountDto> assessList = new ArrayList<>();
        String sql = "select c.coin_name as coin,avg(assess_level) as assess," +
                "avg(skill_assess) as skill_assess," +
                "avg(apply_assess) as apply_assess," +
                "avg(vista_assess) as vista_assess " +
                "from t_inesv_assess a " +
                "join t_inesv_coin_type c on a.coin_no = c.coin_no " +
                "group by a.coin_no";
        try {
            assessList =queryRunner.query(sql,new BeanListHandler<AssessCountDto>(AssessCountDto.class));
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("查询评论失败");
        }
        return assessList;

    }


}
