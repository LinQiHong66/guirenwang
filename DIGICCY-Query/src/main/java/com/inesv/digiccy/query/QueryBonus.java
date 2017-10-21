package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.BonusDetailDto;
import com.inesv.digiccy.dto.BonusRecordDto;
import com.inesv.digiccy.dto.BounsInfoDto;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
@Component
public class QueryBonus {

    private static Logger logger = LoggerFactory.getLogger(QueryBonus.class);

    @Autowired
    private QueryRunner queryRunner;

    /**
     * create by huguokai date:2016年11月14日17:40:23
     * 根据用户编号查询用户分润信息
     * @param userNo
     * @return
     */
    public List<BonusRecordDto> getBonusRecord(Integer userNo){
        String sql = "select * from t_inesv_bonus_record where user_no = ?";
        Object params[] = {userNo};
        List<BonusRecordDto> list = null;
        try {
            list = queryRunner.query(sql,new BeanListHandler<BonusRecordDto>(BonusRecordDto.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("查询分润记录失败");
        }
        return list;
    }

    public List<BonusRecordDto> getBonusRecord(){
        String sql = "select b.*,u.username as attr1 from t_inesv_bonus_record b " +
                "join t_inesv_user u on b.user_no = u.user_no ";
        List<BonusRecordDto> list = null;
        try {
            list = queryRunner.query(sql,new BeanListHandler<BonusRecordDto>(BonusRecordDto.class));
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("查询分润记录失败");
        }
        return list;
    }


    /**
     * create by huguokai date:2016年11月16日10:11:26
     * 根据用户编号查询分润中心
     * @param userNo
     * @return list
     */
    public List<BounsInfoDto> getBonusInfo(Integer userNo){
        String sql = "select coin_type,SUM(enable_coin) as sumCoin,SUM(CASE WHEN user_no = ? THEN enable_coin ELSE NULL END) as myCoin,FORMAT(SUM(CASE WHEN user_no = ? THEN enable_coin ELSE NULL END)/SUM(enable_coin)*100,3) as ratio FROM t_inesv_user_balance where coin_type != 0 GROUP BY coin_type";
        Object params[] = {userNo,userNo};
        List<BounsInfoDto> list = null;
        try {
            list = queryRunner.query(sql,new BeanListHandler<BounsInfoDto>(BounsInfoDto.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("查询分润中心失败");
        }
        return list;
    }

    /**
     * create by huguokai date:2016年11月14日17:40:23
     * 查询所有分润项目
     * @return
     */
    public List<BonusDetailDto> getBonusDetail(){
        String sql = "select b.*,c.coin_name as attr1 from t_inesv_bonus_detail b " +
                "join t_inesv_coin_type c on b.coin_type = c.coin_no ";
        List<BonusDetailDto> list = null;
        try {
            list = queryRunner.query(sql,new BeanListHandler<BonusDetailDto>(BonusDetailDto.class));
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("查询分润记录失败");
        }
        return list;
    }

}
