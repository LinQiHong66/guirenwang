
package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.DayMarketDto;
import com.inesv.digiccy.dto.DealDetailDto;
import com.inesv.digiccy.dto.InesvDayMarket;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/9 0009.
 */
@Component
public class QueryDayMarketInfo {
    private static Logger log = LoggerFactory.getLogger(QueryUserInfo.class);

    @Autowired
    private QueryRunner queryRunner;

    /**查询每日详情表*/
    public List<InesvDayMarket> queryDayMarketInfo(Integer coinType){
        List<InesvDayMarket> inesvDayMarketList = new ArrayList<>();
        String sql = "select * from t_inesv_day_market where coin_type = ? order by date desc ";
        Object params[] = {coinType};
        try {
            inesvDayMarketList = queryRunner.query(sql,new BeanListHandler<InesvDayMarket>(InesvDayMarket.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("查询每日详情表！！！");
        }
        return inesvDayMarketList;
    }
    /**
     * 通过交易记录得到每一天的相关信息
     * @return
     * @throws SQLException 
     */
	public List<InesvDayMarket> queryDealDetailInfoByDay(String day){
    	List<InesvDayMarket> inesvDayMarketList = new ArrayList<>();
        String querySql = "select a.coin_no as coin_type,a.deal_price as newes_deal,sum(a.deal_num) as deal_num,sum(a.sum_price) as deal_price from (select * from t_inesv_deal_detail where date like ? order by date desc )a group by a.coin_no";
        Object params[] = {day+"%"};
        try {
        	inesvDayMarketList=queryRunner.query(querySql,new BeanListHandler<InesvDayMarket>(InesvDayMarket.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("查询失败");
		}
        return inesvDayMarketList;
    }
	
	/**
     * 通过交易记录得到每一天的相关信息
     * @return
     * @throws SQLException 
     */
	public List<DayMarketDto> queryDealDetailInfoByDay() {
		List<DayMarketDto> inesvDayMarketList = new ArrayList<>();
		/*String querySql = " SELECT coin_no as coin_type, "
				+ " @num1:=IFNULL((select d1.deal_price from t_inesv_deal_detail d1 where d1.coin_no = d.coin_no order by date desc limit 1),0) as  newes_deal, "
				+ " IFNULL((select max(d2.deal_price) from t_inesv_deal_detail d2 where d2.deal_type = 0 and d2.coin_no = d.coin_no ),0) as  buy_price, "
				+ " IFNULL((select min(d3.deal_price) from t_inesv_deal_detail d3 where d3.deal_type = 1 and d3.coin_no = d.coin_no ),0) as  sell_price, "
				+ " SUM(deal_num) as 'deal_num', "
				+ " SUM(deal_price) as 'deal_price', "
				+ " @num2:=IFNULL((select d4.deal_price from t_inesv_deal_detail d4 where d4.coin_no = d.coin_no and datediff(now(),date)>0 order by date desc limit 1),0) as 'zrsp', "
				+ " FORMAT(IFNULL((((@num1-@num2)/@num2)*100),0),2) as 'day_percent', date_format(date,'%Y-%m-%d') as date "
				+ " FROM t_inesv_deal_detail d WHERE TO_DAYS(date) = TO_DAYS(NOW()) GROUP BY coin_no ";*/
		String querySql = " SELECT coin_no AS coin_type, "
				 + " IFNULL((SELECT d1.deal_price FROM t_inesv_deal_detail d1 WHERE d1.coin_no = d.coin_no ORDER BY DATE DESC LIMIT 1),0) AS  newes_deal, "
				 + " @num1:=IFNULL((SELECT d2.deal_price FROM t_inesv_deal_detail d2 WHERE d2.deal_type = 0 AND d2.coin_no = d.coin_no ORDER BY DATE DESC LIMIT 1),0) AS  buy_price, "
				 + " @num2:=IFNULL((SELECT d3.deal_price FROM t_inesv_deal_detail d3 WHERE d3.deal_type = 1 AND d3.coin_no = d.coin_no ORDER BY DATE DESC LIMIT 1),0) AS  sell_price, "
				 + " IF(@num1>=@num2,"
				 + " IFNULL((SELECT d2.deal_price FROM t_inesv_deal_detail d2 WHERE d2.deal_type = 0 AND d2.coin_no = d.coin_no ORDER BY DATE DESC LIMIT 1),0),"
				 + " IFNULL((SELECT d3.deal_price FROM t_inesv_deal_detail d3 WHERE d3.deal_type = 1 AND d3.coin_no = d.coin_no ORDER BY DATE DESC LIMIT 1),0)) AS max_price, "
				 + " IF(@num1<=@num2,"
				 + " IFNULL((SELECT d2.deal_price FROM t_inesv_deal_detail d2 WHERE d2.deal_type = 0 AND d2.coin_no = d.coin_no ORDER BY DATE DESC LIMIT 1),0),"
				 + " IFNULL((SELECT d3.deal_price FROM t_inesv_deal_detail d3 WHERE d3.deal_type = 1 AND d3.coin_no = d.coin_no ORDER BY DATE DESC LIMIT 1),0)) AS min_price "
				 + " FROM t_inesv_deal_detail d  GROUP BY coin_no ";
		try {
			inesvDayMarketList = queryRunner.query(querySql,new BeanListHandler<DayMarketDto>(DayMarketDto.class));
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("通过交易记录得到每一天的相关信息失败");
		}
		return inesvDayMarketList;
	}
	
	/**
     * 通过交易记录得到每一天的相关信息
     * @return
     * @throws SQLException 
     */
	public DayMarketDto queryDealDetailInfoByDayAndCoinNo(Integer coinNo) {
		DayMarketDto inesvDayMarket = new DayMarketDto();
		String querySql = " SELECT coin_no AS coin_type, "
				  + " IFNULL((SELECT d1.deal_price FROM t_inesv_deal_detail d1 WHERE d1.coin_no = d.coin_no ORDER BY id DESC LIMIT 1),0) AS  newes_deal, "
				  + " @num1:=IFNULL((SELECT d2.deal_price FROM t_inesv_deal_detail d2 WHERE d2.deal_type = 0 AND d2.coin_no = d.coin_no ORDER BY id DESC LIMIT 1),0) AS  buy_price, "
				  + " @num2:=IFNULL((SELECT d3.deal_price FROM t_inesv_deal_detail d3 WHERE d3.deal_type = 1 AND d3.coin_no = d.coin_no ORDER BY id DESC LIMIT 1),0) AS  sell_price, "
				  + " IF(@num1>=@num2, "
				  + " IFNULL((SELECT d2.deal_price FROM t_inesv_deal_detail d2 WHERE d2.deal_type = 0 AND d2.coin_no = d.coin_no ORDER BY id DESC LIMIT 1),0), "
				  + " IFNULL((SELECT d3.deal_price FROM t_inesv_deal_detail d3 WHERE d3.deal_type = 1 AND d3.coin_no = d.coin_no ORDER BY id DESC LIMIT 1),0)) AS max_price, "
				  + " IF(@num1<=@num2, "
				  + " IFNULL((SELECT d2.deal_price FROM t_inesv_deal_detail d2 WHERE d2.deal_type = 0 AND d2.coin_no = d.coin_no ORDER BY id DESC LIMIT 1),0), "
				  + " IFNULL((SELECT d3.deal_price FROM t_inesv_deal_detail d3 WHERE d3.deal_type = 1 AND d3.coin_no = d.coin_no ORDER BY id DESC LIMIT 1),0)) AS min_price "
				  + " FROM t_inesv_deal_detail d  WHERE coin_no = ? GROUP BY coin_no ";
		Object params[] = {coinNo};
		try {
			inesvDayMarket = queryRunner.query(querySql,new BeanHandler<DayMarketDto>(DayMarketDto.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("通过交易记录得到每一天的相关信息失败");
		}
		return inesvDayMarket;
	}
	
	/**
     * 通过交易记录得到每一天的相关信息
     * @return
     * @throws SQLException 
     */
	public List<DealDetailDto> queryYesCoin() {
		List<DealDetailDto> inesvDealDetailList = new ArrayList<>();
		String querySql = " SELECT * FROM t_inesv_deal_detail GROUP BY coin_no ";
		try {
			inesvDealDetailList = queryRunner.query(querySql,new BeanListHandler<DealDetailDto>(DealDetailDto.class));
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("通过交易记录得到每一天的相关信息失败");
		}
		return inesvDealDetailList;
	}
	
	/**
     * 通过交易记录得到每一天的相关信息
     * @return
     * @throws SQLException 
     */	
	public List<DayMarketDto> queryDealDetailInfoByDayAndCoin(String coin_no) {
		List<DayMarketDto> inesvDayMarketList = new ArrayList<>();
		String querySql = " SELECT coin_no AS coin_type, " 
				+ " @num1:=IFNULL((SELECT d1.deal_price FROM t_inesv_deal_detail d1 WHERE d1.coin_no = d.coin_no ORDER BY DATE DESC LIMIT 1),0) AS  newes_deal, "
				+ " IFNULL((SELECT d2.deal_price FROM t_inesv_deal_detail d2 WHERE d2.deal_type = 0 AND d2.coin_no = d.coin_no AND TO_DAYS(DATE) = TO_DAYS(NOW()) ORDER BY DATE DESC LIMIT 1),0) AS  buy_price, "
				+ " IFNULL((SELECT d3.deal_price FROM t_inesv_deal_detail d3 WHERE d3.deal_type = 1 AND d3.coin_no = d.coin_no AND TO_DAYS(DATE) = TO_DAYS(NOW()) ORDER BY DATE DESC LIMIT 1),0) AS  sell_price, "
				+ " SUM(deal_num) AS 'deal_num', "
				+ " SUM(deal_price) AS 'deal_price', "
				+ " @num2:=MAX(deal_price) AS 'max_price', "
				+ " MIN(deal_price) AS 'min_price', "
				+ " FORMAT(IFNULL((((@num1-@num2)/@num2)*100),0),2) AS 'day_percent', "
				+ " DATE_FORMAT(DATE,'%Y-%m-%d') AS DATE "
				+ " FROM t_inesv_deal_detail d WHERE TO_DAYS(DATE) = TO_DAYS(NOW())  AND coin_no = ? GROUP BY coin_no ";
		Object params[] = {coin_no};
		try {
			inesvDayMarketList = queryRunner.query(querySql,new BeanListHandler<DayMarketDto>(DayMarketDto.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("通过交易记录得到每一天的相关信息失败");
		}
		return inesvDayMarketList;
	}
	
	/**
	 * 获取首页的每日行情
	 * @return
	 */
	public List<InesvDayMarket> getDayMarketInfo() {
		List<InesvDayMarket> inesvDayMarketList = new ArrayList<>();
        String sql = "select a.id,c.coin_no as coin_type,a.newes_deal,a.buy_price,a.sell_price,a.deal_num,a.deal_price,a.day_percent,a.state,a.date,c.icon as attr1,c.coin_name as attr2 from (select * from (select * from t_inesv_day_market order by date DESC) day_market group by coin_type ) a right join t_inesv_coin_type c on a.coin_type=c.coin_no";
        try {
            inesvDayMarketList = queryRunner.query(sql,new BeanListHandler<InesvDayMarket>(InesvDayMarket.class));
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("获取首页的每日行情是失败！！！");
        }
        return inesvDayMarketList;
	}
	
	/**
	 * 判断是否有对应货币的今日行情
	 * @return
	 */
	public List<InesvDayMarket> getDayMarketInfoByCoin(Integer coin_no) {
		List<InesvDayMarket> inesvDayMarketList = new ArrayList<>();
        String sql = "select * from t_inesv_day_market where coin_type = ? and TO_DAYS(DATE) = TO_DAYS(NOW())";
        Object params[] = {coin_no};
        try {
            inesvDayMarketList = queryRunner.query(sql,new BeanListHandler<InesvDayMarket>(InesvDayMarket.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("获取货币的今日行情是失败！！！");
        }
        return inesvDayMarketList;
	}
    /**
     * 查询最新成交价
     * */
	public DealDetailDto queryNewesDeal(Integer coin_type){
		//BigDecimal bigDecimal = null;
		DealDetailDto dealDetailDto = new DealDetailDto();
		String sql = "select * from t_inesv_deal_detail where coin_no = ? ORDER BY id DESC LIMIT 1";
		try {
			dealDetailDto = queryRunner.query(sql, new BeanHandler<DealDetailDto>(DealDetailDto.class),coin_type);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dealDetailDto;
	}
	
	/**
	 * 开盘买价
	 * */
	public DealDetailDto queryNewesDealOfBuy(Integer coin_no){
		//BigDecimal bigDecimal = null;
		DealDetailDto dealDetailDto = new DealDetailDto();
		String sql = "SELECT * FROM `t_inesv_deal_detail` WHERE DATE < DATE_FORMAT(NOW(),'%Y-%m-%d 00:00:00') and coin_no = ? and deal_type=0 ORDER BY id DESC LIMIT 0,1";
		 Object params[] = {coin_no};
		try {
			dealDetailDto = queryRunner.query(sql, new BeanHandler<DealDetailDto>(DealDetailDto.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dealDetailDto;
	}
	/**
	 * 开盘卖价
	 * */
	public DealDetailDto queryNewesDealOfSell(Integer coin_no){
		//BigDecimal bigDecimal = null;
		DealDetailDto dealDetailDto = new DealDetailDto();
		String sql = "SELECT * FROM t_inesv_deal_detail WHERE DATE < DATE_FORMAT(NOW(),'%Y-%m-%d 00:00:00') and coin_no = ? and deal_type=1 ORDER BY id DESC LIMIT 0,1";
		 Object params[] = {coin_no};
		try {
			dealDetailDto = queryRunner.query(sql, new BeanHandler<DealDetailDto>(DealDetailDto.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dealDetailDto;
	}
}
