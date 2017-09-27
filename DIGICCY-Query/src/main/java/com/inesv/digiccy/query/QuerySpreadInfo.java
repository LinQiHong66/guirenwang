package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.dto.MyRecUserDto;
import com.inesv.digiccy.dto.RankDto;
import com.inesv.digiccy.dto.RecProfitDto;
import com.inesv.digiccy.dto.UserInfoDto;
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
 * Created by huguokai on 2016/12/14 0014.
 */
@Component
public class QuerySpreadInfo {

    private static Logger logger = LoggerFactory.getLogger(QuerySpreadInfo.class);

    @Autowired
    private QueryRunner queryRunner;

    /**
     * create by huguokai date:2016年12月14日15:55:47
     * 根据用户编号查询用户邀请码
     * @param userNo
     * @return
     */
    public InesvUserDto getInvite(Integer userNo){
        String sql = "select invite_num from t_inesv_user where user_no = ?";
        Object params[] = {userNo};
        InesvUserDto result = null;
        try {
        	result = queryRunner.query(sql,new BeanHandler<InesvUserDto>(InesvUserDto.class),params);
        } catch (SQLException e) {
            logger.error("查询邀请码失败");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * create by huguokai date:2016年12月14日15:55:47
     * 邀请人数排行榜
     * @return list
     */
    public List<RankDto> getRankInfo(){
        String sql = "SELECT @rownum:=@rownum+1 AS rank, t.user_no,COUNT(CASE WHEN t.user_no IS NOT NULL THEN t.rec_user ELSE NULL END) AS people  FROM (SELECT @rownum:=0) temp, t_inesv_rec t GROUP BY t.user_no ORDER BY rank limit 10";
        List<RankDto> list = null;
        try {
            list = queryRunner.query(sql,new BeanListHandler<RankDto>(RankDto.class));
        } catch (SQLException e) {
            logger.error("查询邀请排行榜失败");
            e.printStackTrace();
        }
        return list;
    }

    /**
     * create by huguokai date:2016年12月14日15:55:47
     * 收益排行榜排行榜
     * @return list
     */
    public List<RankDto> getDetailRankInfo(){
        String sql = "SELECT @rownum:=@rownum+1 AS rank, t.user_no,t.detail FROM (SELECT @rownum:=0) temp,(SELECT t.user_no,SUM(CASE WHEN t.profit_price IS NOT NULL THEN t.profit_price ELSE NULL END) AS detail  FROM t_inesv_rec_profit t GROUP BY t.user_no ORDER BY detail DESC) t limit 10";
        List<RankDto> list = null;
        try {
            list = queryRunner.query(sql,new BeanListHandler<RankDto>(RankDto.class));
        } catch (SQLException e) {
            logger.error("查询收益排行榜失败");
            e.printStackTrace();
        }
        return list;
    }

    /**
     * create by huguokai date:2016-12-15 10:40:53
     * 查询推荐收益
     * @return list
     */
    public List<RecProfitDto> getRecUser(Integer userNo){
        String sql = "SELECT * FROM t_inesv_rec_profit where user_no = ?";
        Object params[] = {userNo};
        List<RecProfitDto> list = null;
        try {
            list = queryRunner.query(sql,new BeanListHandler<RecProfitDto>(RecProfitDto.class),params);
        } catch (SQLException e) {
            logger.error("查询推荐收益失败失败");
            e.printStackTrace();
        }
        return list;
    }

    /**
     * create by huguokai date:2016-12-15 10:40:53
     * 查询我的推广
     * @return list
     */
    public List<MyRecUserDto> getMyRecUserInfo(Integer userNo){
        String sql = "SELECT SUM(profit_price) as price ,COUNT(rec_user) as register,COUNT(CASE WHEN recharge = 1 THEN rec_user ELSE NULL END) as recharge FROM t_inesv_rec_profit WHERE user_no = ?";
        Object params[] = {userNo};
        List<MyRecUserDto> list = null;
        try {
            list = queryRunner.query(sql,new BeanListHandler<MyRecUserDto>(MyRecUserDto.class),params);
        } catch (SQLException e) {
            logger.error("查询我的推广失败");
            e.printStackTrace();
        }
        return list;
    }

}
