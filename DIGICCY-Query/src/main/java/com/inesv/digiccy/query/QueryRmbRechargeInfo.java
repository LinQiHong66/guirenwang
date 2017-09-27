package com.inesv.digiccy.query;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.RmbRechargeDto;

/**
 * Created by yc on 2016/12/9 0009.
 */
@Component
public class QueryRmbRechargeInfo {

    @Autowired
    QueryRunner queryRunner;


    /**
     * 根据用户查询出用户的rmb充值信息
     *
     */
    public RmbRechargeDto qureyRechargeInfoByorder(String order) {
        RmbRechargeDto rmb = new RmbRechargeDto();
        String sql = "SELECT * FROM t_inesv_rmb_recharge WHERE recharge_order = ? ";
        Object parmas[] = { order };
        try {
            rmb = (RmbRechargeDto) queryRunner.query(sql, new BeanHandler(RmbRechargeDto.class), parmas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rmb;
    }

    /**
     * 根据用户查询出用户的rmb充值信息
     *
     */
    public List<RmbRechargeDto> qureyRechargeInfo(int userNo) {
        List<RmbRechargeDto> list = new ArrayList();
        String sql = "select * from t_inesv_rmb_recharge where user_no = ? order by date limit 30";
        Object parmas[] = { userNo };
        try {
            list = (List<RmbRechargeDto>) queryRunner.query(sql, new BeanListHandler(RmbRechargeDto.class), parmas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    /**
     * 查询出用户的rmb充值信息
     *
     */
    public RmbRechargeDto qureyRechargeBack() {
    	RmbRechargeDto dto = new RmbRechargeDto() ;
        String sql = "SELECT IFNULL(SUM(actual_price),0) AS recharge_price , " + 
        		" (SELECT IFNULL(SUM(actual_price),0) FROM t_inesv_rmb_recharge WHERE state = 1 AND TO_DAYS(DATE) = TO_DAYS(NOW())) AS actual_price " + 
        		" FROM t_inesv_rmb_recharge WHERE state = 1";
        try {
        	dto = (RmbRechargeDto)queryRunner.query(sql, new BeanHandler(RmbRechargeDto.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dto;
    }

    /* 查询人民币充值总条数 **/
    public long getRechargeSize(String userName, String state, String startDate, String endDate) throws SQLException {
        String sql = "select count(*) as cun from t_inesv_rmb_recharge r join t_inesv_user u on r.user_no = u.user_no";
        ArrayList<Object> paramArr = new ArrayList<>();
        if (userName != null && !"".equals(userName) && !"-1".equals(userName)) {
            String str = sql.contains("where") ? " and r.user_no like ? or u.username like ?" : " where r.user_no like ? or u.username like ?";
            sql += str;
            paramArr.add("%" + userName + "%");
            paramArr.add("%" + userName + "%");
        }
        if (state != null && !"".equals(state) && !"-1".equals(state)) {
            String str = sql.contains("where") ? " and r.state=?" : " where r.state=?";
            sql += str;
            paramArr.add(state);
        }
        if (startDate != null && !"".equals(startDate) && endDate != null && !"".equals(endDate)) {
            String str = sql.contains("where") ? " and r.date between ? and ?" : " where r.date between ? and ?";
            sql += str;
            paramArr.add(Date.valueOf(startDate));
            paramArr.add(Date.valueOf(endDate));
        }
        return (Long) queryRunner.query(sql, new ColumnListHandler<>("cun"), paramArr.toArray(new Object[] {})).get(0);
    }

    /**
     * 根据用户查询出用户的rmb充值信息
     *
     */
    public List<RmbRechargeDto> qureyRechargeInfo(String userName, String state, String startDate, String endDate, int curPage, int pageItem) {
        List<RmbRechargeDto> list = new ArrayList();
        String sql = "select r.*,u.username AS attr1 from t_inesv_rmb_recharge r join t_inesv_user u " + "on r.user_no = u.user_no";
        String last = " limit ?,?";
        ArrayList<Object> paramArr = new ArrayList<>();
        if (userName != null && !"".equals(userName) && !"-1".equals(userName)) {
            String str = sql.contains("where") ? " and r.user_no like ? or u.username like ?" : " where r.user_no like ? or u.username like ?";
            sql += str;
            paramArr.add("%" + userName + "%");
            paramArr.add("%" + userName + "%");
        }
        if (state != null && !"".equals(state) && !"-1".equals(state)) {
            String str = sql.contains("where") ? " and r.state=?" : " where r.state=?";
            sql += str;
            paramArr.add(state);
        }
        if (startDate != null && !"".equals(startDate) && endDate != null && !"".equals(endDate)) {
            String str = sql.contains("where") ? " and r.date between ? and ?" : " where r.date between ? and ?";
            sql += str;
            paramArr.add(Date.valueOf(startDate));
            paramArr.add(Date.valueOf(endDate));
        }
        sql += " order by r.date desc";
        sql += last;
        paramArr.add((curPage - 1) * pageItem);
        paramArr.add(pageItem);
        try {
            list = (List<RmbRechargeDto>) queryRunner.query(sql, new BeanListHandler(RmbRechargeDto.class), paramArr.toArray(new Object[] {}));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据用户查询出用户的rmb充值信息
     *
     */
    public List<RmbRechargeDto> qureyRechargeInfoByNo() {
        List<RmbRechargeDto> list = new ArrayList<RmbRechargeDto>();
        String sql = "select * from t_inesv_rmb_recharge where state = 0 and date > DATE_SUB(NOW(), INTERVAL 720 MINUTE)";
        try {
            list = (List<RmbRechargeDto>) queryRunner.query(sql, new BeanListHandler(RmbRechargeDto.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
