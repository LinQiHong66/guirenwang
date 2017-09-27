package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.VoteDto;
import com.inesv.digiccy.dto.VoteInfoDto;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
@Component
public class QueryVoteInfo {

    private static Logger logger = LoggerFactory.getLogger(QueryVoteInfo.class);

    @Autowired
    private QueryRunner queryRunner;

    /**
     * create by huguokai date:2016年11月17日14:31:14
     * 查询投票信息
     * @return list
     */
    public List<VoteInfoDto> getVoteInfo(){
        String sql = "SELECT c.coin_no as coin_type,COUNT(CASE WHEN v.vote_type = 1 THEN v.user_no ELSE NULL END ) AS Support,COUNT(CASE WHEN v.vote_type = 2 THEN v.user_no ELSE NULL END) AS Opposition,COUNT(v.user_no) AS Total  FROM t_inesv_coin_type c LEFT JOIN t_inesv_vote v ON c.coin_no = v.coin_type where c.coin_no != 0 GROUP BY c.coin_no";
        List<VoteInfoDto> list = null;
        try {
            list = queryRunner.query(sql,new BeanListHandler<VoteInfoDto>(VoteInfoDto.class));
        } catch (SQLException e) {
            logger.error("查询投票记录失败");
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据币种查询投票情况
     * @return
     */
    public VoteInfoDto getVoteInfoByCoin(Integer coin){
        String sql = "SELECT coin_type,COUNT(CASE WHEN vote_type = 1 THEN user_no ELSE NULL END ) AS Support," +
                "COUNT(CASE WHEN vote_type = 2 THEN user_no ELSE NULL END) AS Opposition,COUNT(user_no) as Total " +
                "FROM t_inesv_vote " +
                "WHERE coin_type = ?";
        VoteInfoDto count = null;
        Object params[] = {coin};
        try {
            count = queryRunner.query(sql,new BeanHandler<>(VoteInfoDto.class),params);
        } catch (SQLException e) {
            logger.error("根据币种查询投票记录失败");
            e.printStackTrace();
        }
        return count;
    }

    public List<VoteDto> getVoteInfoByUserNoCoinType(Integer userNo, Integer coinType){
        String sql = "select * from t_inesv_vote where user_no = ? and coin_type = ?";
        Object params[] = {userNo,coinType};
        List<VoteDto> list = null;
        try {
            list = queryRunner.query(sql,new BeanListHandler<VoteDto>(VoteDto.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
