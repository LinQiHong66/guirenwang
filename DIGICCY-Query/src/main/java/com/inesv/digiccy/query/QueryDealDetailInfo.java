package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.CoinDetailDto;
import com.inesv.digiccy.dto.DealDetailDto;
import com.inesv.digiccy.dto.EntrustDto;
import com.inesv.digiccy.dto.KDealDetailDto;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 查询用户拥有的资源
 * Created by JimJim on 2016/11/4 0004.
 */
@Component
public class QueryDealDetailInfo {
	private static Logger log = LoggerFactory.getLogger(QueryDealDetailInfo.class);
	
    @Autowired
    QueryRunner queryRunner;
    
    /**
     * 根据货币类型，时间类型查询交易记录
     * @param request
     * @param detailType(1：1分钟，2：3分钟，3：5分钟，4：15分钟，5：30分钟，6：1小时，7：2小时，8：4小时，9：6小时，10：12小时，11：每天)
     * @param detailType("1min"：1分钟，"3min"：3分钟，"5min"：5分钟，"15min"：15分钟，"30min"：30分钟，"1hour"：1小时，"2hour"：2小时，"4hour"：4小时，"6hour"：6小时，"12hour"：12小时，"1day"：每天)
     * @return
     * @throws SQLException 
     */
    public List<KDealDetailDto> queryDealDetailInfoByType(String priceType,String detailType){
    	List<KDealDetailDto> kDealDetailList = new ArrayList<>();
    	String sql="";
    	if(detailType.equals("1")){
    		sql="SELECT * FROM " +
    				 " (SELECT * FROM (SELECT (deal_price) AS end_price,DATE AS end_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:%i:00')) AS end_date_num ,DATE_FORMAT(DATE,'%Y-%m-%d %H:%i') AS gt FROM t_inesv_deal_detail WHERE coin_no=? AND DATE IN (SELECT MAX(DATE) FROM t_inesv_deal_detail GROUP BY DATE_FORMAT(DATE,'%Y-%m-%d %H:%i')) ORDER BY end_date DESC) a GROUP BY DATE_FORMAT(end_date,'%Y-%m-%d %H:%i')) AS a " + 
    				 " , " +
    				 " (SELECT * FROM (SELECT (deal_price) AS begin_price,DATE AS begin_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:%i:00')) AS begin_date_num ,DATE_FORMAT(DATE,'%Y-%m-%d %H:%i') AS gt FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY begin_date ASC) a GROUP BY DATE_FORMAT(begin_date,'%Y-%m-%d %H:%i')) AS b " +
    				 " , " +
    				 " (SELECT  MAX(deal_price) AS max_price ,MIN(deal_price) AS min_price , SUM(deal_num) AS deal_num , " +
    				 " (MAX(deal_price)-MIN(deal_price)) AS difference_price ,((MAX(deal_price)-MIN(deal_price))/MAX(deal_price)) AS difference_percentage , " +
    				 " deal_type AS price_type ,DATE_FORMAT(DATE,'%Y-%m-%d %H:%i') AS gt FROM t_inesv_deal_detail WHERE coin_no=? AND DATE > DATE_SUB(NOW(),INTERVAL 12 HOUR) GROUP BY DATE_FORMAT(DATE,'%Y-%m-%d %H:%i') ORDER BY gt DESC) AS c " +
    				 " WHERE b.gt=c.gt AND a.gt=b.gt ";
    	}if(detailType.equals("2")){ 
    		sql="SELECT * FROM " +
    				" (SELECT * FROM (SELECT (deal_price) AS end_price,DATE AS end_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:%i:00')) AS end_date_num,CONCAT(DATE_FORMAT(DATE, '%Y-%m-%d %H:'),FLOOR(DATE_FORMAT(DATE, '%i') / 3)) AS gt " +
    				" FROM t_inesv_deal_detail WHERE coin_no=?  AND DATE IN (SELECT MAX(DATE) FROM t_inesv_deal_detail GROUP BY CONCAT(DATE_FORMAT(DATE, '%Y-%m-%d %H:'),FLOOR(DATE_FORMAT(DATE, '%i') / 3))) ORDER BY end_date DESC) a GROUP BY gt) AS a  " +
    				" , " +
    				" (SELECT * FROM (SELECT (deal_price) AS begin_price,DATE AS begin_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:%i:00')) AS begin_date_num ,CONCAT(DATE_FORMAT(DATE, '%Y-%m-%d %H:'),FLOOR(DATE_FORMAT(DATE, '%i') / 3)) AS gt FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY begin_date ASC) a GROUP BY gt) AS b " +
    				" , " +
    				" (SELECT  MAX(deal_price) AS max_price ,MIN(deal_price) AS min_price , SUM(deal_num) AS deal_num , " +
    				" (MAX(deal_price)-MIN(deal_price)) AS difference_price ,((MAX(deal_price)-MIN(deal_price))/MAX(deal_price)) AS difference_percentage , " +
    				" deal_type AS price_type ,CONCAT(DATE_FORMAT(DATE, '%Y-%m-%d %H:'),FLOOR(DATE_FORMAT(DATE, '%i') / 3)) AS gt FROM t_inesv_deal_detail WHERE coin_no=? AND TO_DAYS(DATE) = TO_DAYS(NOW()) GROUP BY gt ORDER BY DATE DESC) AS c " +
    				" WHERE a.gt=b.gt AND b.gt=c.gt ORDER BY a.end_date " ;	 
    	}if(detailType.equals("3")){
    		sql="SELECT * FROM " +
    				" (SELECT * FROM (SELECT (deal_price) AS end_price,DATE AS end_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:%i:00')) AS end_date_num,CONCAT(DATE_FORMAT(DATE, '%Y-%m-%d %H:'),FLOOR(DATE_FORMAT(DATE, '%i') / 5)) AS gt " +
    				" FROM t_inesv_deal_detail WHERE coin_no=?  AND DATE IN (SELECT MAX(DATE) FROM t_inesv_deal_detail GROUP BY CONCAT(DATE_FORMAT(DATE, '%Y-%m-%d %H:'),FLOOR(DATE_FORMAT(DATE, '%i') / 5))) ORDER BY end_date DESC) a GROUP BY gt) AS a  " +
    				" , " +
    				" (SELECT * FROM (SELECT (deal_price) AS begin_price,DATE AS begin_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:%i:00')) AS begin_date_num ,CONCAT(DATE_FORMAT(DATE, '%Y-%m-%d %H:'),FLOOR(DATE_FORMAT(DATE, '%i') / 5)) AS gt FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY begin_date ASC) a GROUP BY gt) AS b " +
    				" , " +
    				" (SELECT  MAX(deal_price) AS max_price ,MIN(deal_price) AS min_price , SUM(deal_num) AS deal_num , " +
    				" (MAX(deal_price)-MIN(deal_price)) AS difference_price ,((MAX(deal_price)-MIN(deal_price))/MAX(deal_price)) AS difference_percentage , " +
    				" deal_type AS price_type ,CONCAT(DATE_FORMAT(DATE, '%Y-%m-%d %H:'),FLOOR(DATE_FORMAT(DATE, '%i') / 5)) AS gt FROM t_inesv_deal_detail WHERE coin_no=? AND DATE > DATE_SUB(NOW(),INTERVAL 2 DAY) GROUP BY gt ORDER BY DATE DESC) AS c " +
    				" WHERE a.gt=b.gt AND b.gt=c.gt ORDER BY a.end_date " ;	 
    	}if(detailType.equals("4")){
    		sql="SELECT * FROM " +
    				" (SELECT * FROM (SELECT (deal_price) AS end_price,DATE AS end_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:%i:00')) AS end_date_num,CONCAT(DATE_FORMAT(DATE, '%Y-%m-%d %H:'),FLOOR(DATE_FORMAT(DATE, '%i') / 15)) AS gt " +
    				" FROM t_inesv_deal_detail WHERE coin_no=?  AND DATE IN (SELECT MAX(DATE) FROM t_inesv_deal_detail GROUP BY CONCAT(DATE_FORMAT(DATE, '%Y-%m-%d %H:'),FLOOR(DATE_FORMAT(DATE, '%i') / 15))) ORDER BY end_date DESC) a GROUP BY gt) AS a  " +
    				" , " +
    				" (SELECT * FROM (SELECT (deal_price) AS begin_price,DATE AS begin_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:%i:00')) AS begin_date_num ,CONCAT(DATE_FORMAT(DATE, '%Y-%m-%d %H:'),FLOOR(DATE_FORMAT(DATE, '%i') / 15)) AS gt FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY begin_date ASC) a GROUP BY gt) AS b " +
    				" , " +
    				" (SELECT  MAX(deal_price) AS max_price ,MIN(deal_price) AS min_price , SUM(deal_num) AS deal_num , " +
    				" (MAX(deal_price)-MIN(deal_price)) AS difference_price ,((MAX(deal_price)-MIN(deal_price))/MAX(deal_price)) AS difference_percentage , " +
    				" deal_type AS price_type ,CONCAT(DATE_FORMAT(DATE, '%Y-%m-%d %H:'),FLOOR(DATE_FORMAT(DATE, '%i') / 15)) AS gt FROM t_inesv_deal_detail WHERE coin_no=? AND DATE > DATE_SUB(NOW(),INTERVAL 4 DAY) GROUP BY gt ORDER BY DATE DESC) AS c " +
    				" WHERE a.gt=b.gt AND b.gt=c.gt ORDER BY a.end_date " ;	 
    	}if(detailType.equals("5")){
    		sql="SELECT * FROM " +
    				" (SELECT * FROM (SELECT (deal_price) AS end_price,DATE AS end_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:%i:00')) AS end_date_num,CONCAT(DATE_FORMAT(DATE, '%Y-%m-%d %H:'),FLOOR(DATE_FORMAT(DATE, '%i') / 30)) AS gt " +
    				" FROM t_inesv_deal_detail WHERE coin_no=?  AND DATE IN (SELECT MAX(DATE) FROM t_inesv_deal_detail GROUP BY CONCAT(DATE_FORMAT(DATE, '%Y-%m-%d %H:'),FLOOR(DATE_FORMAT(DATE, '%i') / 30))) ORDER BY end_date DESC) a GROUP BY gt) AS a  " +
    				" , " +
    				" (SELECT * FROM (SELECT (deal_price) AS begin_price,DATE AS begin_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:%i:00')) AS begin_date_num ,CONCAT(DATE_FORMAT(DATE, '%Y-%m-%d %H:'),FLOOR(DATE_FORMAT(DATE, '%i') / 30)) AS gt FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY begin_date ASC) a GROUP BY gt) AS b " +
    				" , " +
    				" (SELECT  MAX(deal_price) AS max_price ,MIN(deal_price) AS min_price , SUM(deal_num) AS deal_num , " +
    				" (MAX(deal_price)-MIN(deal_price)) AS difference_price ,((MAX(deal_price)-MIN(deal_price))/MAX(deal_price)) AS difference_percentage , " +
    				" deal_type AS price_type ,CONCAT(DATE_FORMAT(DATE, '%Y-%m-%d %H:'),FLOOR(DATE_FORMAT(DATE, '%i') / 30)) AS gt FROM t_inesv_deal_detail WHERE coin_no=? AND DATE > DATE_SUB(NOW(),INTERVAL 7 DAY) GROUP BY gt ORDER BY DATE DESC) AS c " +
    				" WHERE a.gt=b.gt AND b.gt=c.gt ORDER BY a.end_date " ;	 
    		
    	}if(detailType.equals("6")){
    		sql="SELECT * FROM " +
  				  	" (SELECT * FROM (SELECT (deal_price) AS end_price,DATE AS end_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:00:00')) AS end_date_num ,DATE_FORMAT(DATE,'%Y-%m-%d %H') AS gt FROM t_inesv_deal_detail WHERE coin_no=? AND DATE IN (SELECT MAX(DATE) FROM t_inesv_deal_detail GROUP BY DATE_FORMAT(DATE,'%Y-%m-%d %H')) ORDER BY end_date DESC) a GROUP BY DATE_FORMAT(end_date,'%Y-%m-%d %H')) AS a  " +
  				  	" , " +
  				  	" (SELECT * FROM (SELECT (deal_price) AS begin_price,DATE AS begin_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:00:00')) AS begin_date_num ,DATE_FORMAT(DATE,'%Y-%m-%d %H') AS gt FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY begin_date ASC) a GROUP BY DATE_FORMAT(begin_date,'%Y-%m-%d %H')) AS b " +
  				  	" , " +
  				  	" (SELECT  MAX(deal_price) AS max_price ,MIN(deal_price) AS min_price , SUM(deal_num) AS deal_num , " +
  				  	" (MAX(deal_price)-MIN(deal_price)) AS difference_price ,((MAX(deal_price)-MIN(deal_price))/MAX(deal_price)) AS difference_percentage , " +
  				  	" deal_type AS price_type ,DATE_FORMAT(DATE,'%Y-%m-%d %H') AS gt FROM t_inesv_deal_detail WHERE coin_no=? AND DATE > DATE_SUB(NOW(),INTERVAL 14 DAY) GROUP BY DATE_FORMAT(DATE,'%Y-%m-%d %H') ORDER BY gt DESC) AS c " +
  				  	" WHERE b.gt=c.gt AND a.gt=b.gt " ;
    	}if(detailType.equals("7")){
    		sql=" SELECT * FROM " + 
   				 " (SELECT * FROM (SELECT (deal_price) AS end_price,DATE AS end_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:00:00')) AS end_date_num,CONCAT(DATE_FORMAT(DATE, '%Y-%m-%d '),ROUND(DATE_FORMAT(DATE, '%H') / 2)) AS gt " +
   				 " FROM t_inesv_deal_detail WHERE coin_no=?  AND DATE IN (SELECT MAX(DATE) FROM t_inesv_deal_detail GROUP BY CONCAT(DATE_FORMAT(DATE, '%Y-%m-%d '),ROUND(DATE_FORMAT(DATE, '%H') / 2))) ORDER BY end_date DESC) a GROUP BY gt) AS a " +
   				 " , " +
   				 " (SELECT * FROM (SELECT (deal_price) AS begin_price,DATE AS begin_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:00:00')) AS begin_date_num ,CONCAT(DATE_FORMAT(DATE, '%Y-%m-%d '),ROUND(DATE_FORMAT(DATE, '%H') / 2)) AS gt FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY begin_date ASC) a GROUP BY gt) AS b " +
   				 " , " +
   				 " (SELECT  MAX(deal_price) AS max_price ,MIN(deal_price) AS min_price , SUM(deal_num) AS deal_num ,  " +
   				 " (MAX(deal_price)-MIN(deal_price)) AS difference_price ,((MAX(deal_price)-MIN(deal_price))/MAX(deal_price)) AS difference_percentage , " +
   				 " deal_type AS price_type ,CONCAT(DATE_FORMAT(DATE, '%Y-%m-%d '),ROUND(DATE_FORMAT(DATE, '%H') / 2)) AS gt FROM t_inesv_deal_detail WHERE coin_no=? AND DATE > DATE_SUB(NOW(),INTERVAL 30 DAY) GROUP BY gt ORDER BY DATE DESC) AS c  " +
   				 " WHERE a.gt=b.gt AND b.gt=c.gt ORDER BY a.end_date ";	 
    	}if(detailType.equals("8")){
    		sql=" SELECT * FROM " + 
    				 " (SELECT * FROM (SELECT (deal_price) AS end_price,DATE AS end_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:00:00')) AS end_date_num,CONCAT(DATE_FORMAT(DATE, '%Y-%m-%d '),ROUND(DATE_FORMAT(DATE, '%H') / 4)) AS gt " +
    				 " FROM t_inesv_deal_detail WHERE coin_no=?  AND DATE IN (SELECT MAX(DATE) FROM t_inesv_deal_detail GROUP BY CONCAT(DATE_FORMAT(DATE, '%Y-%m-%d '),ROUND(DATE_FORMAT(DATE, '%H') / 4))) ORDER BY end_date DESC) a GROUP BY gt) AS a " +
    				 " , " +
    				 " (SELECT * FROM (SELECT (deal_price) AS begin_price,DATE AS begin_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:00:00')) AS begin_date_num ,CONCAT(DATE_FORMAT(DATE, '%Y-%m-%d '),ROUND(DATE_FORMAT(DATE, '%H') / 4)) AS gt FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY begin_date ASC) a GROUP BY gt) AS b " +
    				 " , " +
    				 " (SELECT  MAX(deal_price) AS max_price ,MIN(deal_price) AS min_price , SUM(deal_num) AS deal_num ,  " +
    				 " (MAX(deal_price)-MIN(deal_price)) AS difference_price ,((MAX(deal_price)-MIN(deal_price))/MAX(deal_price)) AS difference_percentage , " +
    				 " deal_type AS price_type ,CONCAT(DATE_FORMAT(DATE, '%Y-%m-%d '),ROUND(DATE_FORMAT(DATE, '%H') / 4)) AS gt FROM t_inesv_deal_detail WHERE coin_no=? AND DATE > DATE_SUB(NOW(),INTERVAL 30 DAY) GROUP BY gt ORDER BY DATE DESC) AS c  " +
    				 " WHERE a.gt=b.gt AND b.gt=c.gt ORDER BY a.end_date ";	 
    	}if(detailType.equals("9")){
    		sql=" SELECT * FROM " + 
      				 " (SELECT * FROM (SELECT (deal_price) AS end_price,DATE AS end_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:00:00')) AS end_date_num,CONCAT(DATE_FORMAT(DATE, '%Y-%m-%d '),ROUND(DATE_FORMAT(DATE, '%H') / 6)) AS gt " +
      				 " FROM t_inesv_deal_detail WHERE coin_no=?  AND DATE IN (SELECT MAX(DATE) FROM t_inesv_deal_detail GROUP BY CONCAT(DATE_FORMAT(DATE, '%Y-%m-%d '),ROUND(DATE_FORMAT(DATE, '%H') / 6))) ORDER BY end_date DESC) a GROUP BY gt) AS a " +
      				 " , " +
      				 " (SELECT * FROM (SELECT (deal_price) AS begin_price,DATE AS begin_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:00:00')) AS begin_date_num ,CONCAT(DATE_FORMAT(DATE, '%Y-%m-%d '),ROUND(DATE_FORMAT(DATE, '%H') / 6)) AS gt FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY begin_date ASC) a GROUP BY gt) AS b " +
      				 " , " +
      				 " (SELECT  MAX(deal_price) AS max_price ,MIN(deal_price) AS min_price , SUM(deal_num) AS deal_num ,  " +
      				 " (MAX(deal_price)-MIN(deal_price)) AS difference_price ,((MAX(deal_price)-MIN(deal_price))/MAX(deal_price)) AS difference_percentage , " +
      				 " deal_type AS price_type ,CONCAT(DATE_FORMAT(DATE, '%Y-%m-%d '),ROUND(DATE_FORMAT(DATE, '%H') / 6)) AS gt FROM t_inesv_deal_detail WHERE coin_no=? AND DATE > DATE_SUB(NOW(),INTERVAL 182 DAY) GROUP BY gt ORDER BY DATE DESC) AS c  " +
      				 " WHERE a.gt=b.gt AND b.gt=c.gt ORDER BY a.end_date ";	 
    	}if(detailType.equals("10")){
    		sql=" SELECT * FROM " + 
   				 " (SELECT * FROM (SELECT (deal_price) AS end_price,DATE AS end_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:00:00')) AS end_date_num,CONCAT(DATE_FORMAT(DATE, '%Y-%m-%d '),ROUND(DATE_FORMAT(DATE, '%H') / 12)) AS gt " +
   				 " FROM t_inesv_deal_detail WHERE coin_no=?  AND DATE IN (SELECT MAX(DATE) FROM t_inesv_deal_detail GROUP BY CONCAT(DATE_FORMAT(DATE, '%Y-%m-%d '),ROUND(DATE_FORMAT(DATE, '%H') / 12))) ORDER BY end_date DESC) a GROUP BY gt) AS a " +
   				 " , " +
   				 " (SELECT * FROM (SELECT (deal_price) AS begin_price,DATE AS begin_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:00:00')) AS begin_date_num ,CONCAT(DATE_FORMAT(DATE, '%Y-%m-%d '),ROUND(DATE_FORMAT(DATE, '%H') / 12)) AS gt FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY begin_date ASC) a GROUP BY gt) AS b " +
   				 " , " +
   				 " (SELECT  MAX(deal_price) AS max_price ,MIN(deal_price) AS min_price , SUM(deal_num) AS deal_num ,  " +
   				 " (MAX(deal_price)-MIN(deal_price)) AS difference_price ,((MAX(deal_price)-MIN(deal_price))/MAX(deal_price)) AS difference_percentage , " +
   				 " deal_type AS price_type ,CONCAT(DATE_FORMAT(DATE, '%Y-%m-%d '),ROUND(DATE_FORMAT(DATE, '%H') / 12)) AS gt FROM t_inesv_deal_detail WHERE coin_no=? AND DATE > DATE_SUB(NOW(),INTERVAL 182 DAY) GROUP BY gt ORDER BY DATE DESC) AS c  " +
   				 " WHERE a.gt=b.gt AND b.gt=c.gt ORDER BY a.end_date ";	 
    	}
    	if(detailType.equals("11")){
    		sql="SELECT * FROM " +
  				" (SELECT * FROM (SELECT (deal_price) AS end_price,DATE AS end_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d 00:00:00')) AS end_date_num ,DATE_FORMAT(DATE,'%Y-%m-%d') AS gt FROM t_inesv_deal_detail WHERE coin_no=? AND DATE IN (SELECT MAX(DATE) FROM t_inesv_deal_detail GROUP BY DATE_FORMAT(DATE,'%Y-%m-%d')) ORDER BY end_date DESC) a GROUP BY DATE_FORMAT(end_date,'%Y-%m-%d')) AS a " +
  				" , " +
  				" (SELECT * FROM (SELECT (deal_price) AS begin_price,DATE AS begin_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d 00:00:00')) AS begin_date_num ,DATE_FORMAT(DATE,'%Y-%m-%d') AS gt FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY begin_date ASC) a GROUP BY DATE_FORMAT(begin_date,'%Y-%m-%d')) AS b " +
  				" , " +
  				" (SELECT  MAX(deal_price) AS max_price ,MIN(deal_price) AS min_price , SUM(deal_num) AS deal_num , " +
  				" (MAX(deal_price)-MIN(deal_price)) AS difference_price ,((MAX(deal_price)-MIN(deal_price))/MAX(deal_price)) AS difference_percentage , " +
  				" deal_type AS price_type ,DATE_FORMAT(DATE,'%Y-%m-%d') AS gt FROM t_inesv_deal_detail WHERE coin_no=? AND DATE > DATE_SUB(NOW(),INTERVAL 365 DAY) GROUP BY DATE_FORMAT(DATE,'%Y-%m-%d') ORDER BY gt DESC) AS c " +
  				" WHERE b.gt=c.gt AND a.gt=b.gt ";
    	}
        Object params[] = {priceType,priceType,priceType};
        try {
        	kDealDetailList=queryRunner.query(sql,new BeanListHandler<KDealDetailDto>(KDealDetailDto.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("根据用户查询交易记录失败");
		}
        return kDealDetailList;
    }
    
    /**
     * 根据货币类型，查询当前分钟数据
     * @param request
     * priceType
     * @return
     * @throws SQLException 
     */
    public KDealDetailDto queryMinuteDealDetailInfoByType(String priceType,String detailType){
    	KDealDetailDto kDealDetailList = new KDealDetailDto();
    	String sql="";
    	if(detailType.equals("1")){
    		sql="SELECT * FROM t_inesv_deal_detail WHERE coin_no = ? AND DATE >= DATE_FORMAT(NOW(),'%Y-%m-%d %H:%i:00') AND DATE <= DATE_FORMAT(NOW(),'%Y-%m-%d %H:%i:59') GROUP BY DATE DESC LIMIT 0,1";
    	}if(detailType.equals("2")){
    		sql="SELECT * FROM t_inesv_deal_detail WHERE coin_no = ? AND DATE >= DATE_SUB(NOW(),INTERVAL 3 MINUTE) GROUP BY DATE DESC LIMIT 0,1";
    	}if(detailType.equals("3")){
    		sql="SELECT * FROM t_inesv_deal_detail WHERE coin_no = ? AND DATE >= DATE_SUB(NOW(),INTERVAL 5 MINUTE) GROUP BY DATE DESC LIMIT 0,1";
    	}if(detailType.equals("4")){
    		sql="SELECT * FROM t_inesv_deal_detail WHERE coin_no = ? AND DATE >= DATE_SUB(NOW(),INTERVAL 15 MINUTE) GROUP BY DATE DESC LIMIT 0,1";
    	}if(detailType.equals("5")){
    		sql="SELECT * FROM t_inesv_deal_detail WHERE coin_no = ? AND DATE >= DATE_SUB(NOW(),INTERVAL 30 MINUTE) GROUP BY DATE DESC LIMIT 0,1";
    	}if(detailType.equals("6")){
    		sql="SELECT * FROM t_inesv_deal_detail WHERE coin_no = ? AND DATE >= DATE_SUB(NOW(),INTERVAL 60 MINUTE) GROUP BY DATE DESC LIMIT 0,1";
    	}if(detailType.equals("7")){
    		sql="SELECT * FROM t_inesv_deal_detail WHERE coin_no = ? AND DATE >= DATE_SUB(NOW(),INTERVAL 120 MINUTE) GROUP BY DATE DESC LIMIT 0,1";
    	}if(detailType.equals("8")){
    		sql="SELECT * FROM t_inesv_deal_detail WHERE coin_no = ? AND DATE >= DATE_SUB(NOW(),INTERVAL 240 MINUTE) GROUP BY DATE DESC LIMIT 0,1";
    	}if(detailType.equals("9")){
    		sql="SELECT * FROM t_inesv_deal_detail WHERE coin_no = ? AND DATE >= DATE_SUB(NOW(),INTERVAL 360 MINUTE) GROUP BY DATE DESC LIMIT 0,1";
    	}if(detailType.equals("10")){
    		sql="SELECT * FROM t_inesv_deal_detail WHERE coin_no = ? AND DATE >= DATE_SUB(NOW(),INTERVAL 720 MINUTE) GROUP BY DATE DESC LIMIT 0,1";
    	}if(detailType.equals("11")){
    		sql="SELECT * FROM t_inesv_deal_detail WHERE coin_no = ? AND TO_DAYS(DATE) = TO_DAYS(NOW()) GROUP BY DATE DESC LIMIT 0,1";
    	}
    	Object params[] = {priceType};
        try {
        	kDealDetailList=queryRunner.query(sql,new BeanHandler<KDealDetailDto>(KDealDetailDto.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("根据用户查询交易记录失败");
		}
        return kDealDetailList;
    }
    
    /**
     * 根据货币类型，查询最后一条数据数据
     * @param request
     * priceType
     * @return
     * @throws SQLException 
     */
    public KDealDetailDto queryEndDealDetailInfoByType(String priceType,String detailType){
    	KDealDetailDto kDealDetail = new KDealDetailDto();
    	String sql="";
    	if(detailType.equals("1")){
    		sql="SELECT * FROM " + 
    				" (SELECT * FROM (SELECT (deal_price) AS end_price,date AS end_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:%i:00')) AS end_date_num,ROUND(DATE/100)*100 AS gt FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY end_date DESC) a GROUP BY ROUND(end_date/100)*100) AS a " +
    				" INNER JOIN " + 
    				" (SELECT * FROM (SELECT (deal_price) AS begin_price,date AS begin_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:%i:00')) AS begin_date_num ,ROUND(DATE/100)*100 AS gt FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY begin_date ASC) a GROUP BY ROUND(begin_date/100)*100) AS b " + 
    				" ON a.gt=b.gt " + 
    				" INNER JOIN " +
    				" (SELECT  MAX(deal_price) AS max_price ,MIN(deal_price) AS min_price , SUM(deal_num) AS deal_num ," + 
    				" (MAX(deal_price)-MIN(deal_price)) AS difference_price ,((MAX(deal_price)-MIN(deal_price))/MAX(deal_price)) AS difference_percentage ," + 
    				" deal_type AS price_type ,ROUND(DATE/100)*100 AS gt FROM t_inesv_deal_detail WHERE coin_no=? GROUP BY gt ORDER BY gt DESC LIMIT 0,1) AS c " + 
    				" ON b.gt=c.gt ";
    	}if(detailType.equals("2")){
    		sql="SELECT * FROM " + 
    				" (SELECT * FROM (SELECT (deal_price) AS end_price,date AS end_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:%i:00')) AS end_date_num,ROUND(DATE/300)*300 AS gt FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY end_date DESC) a GROUP BY ROUND(end_date/300)*300) AS a " +
    				" INNER JOIN " + 
    				" (SELECT * FROM (SELECT (deal_price) AS begin_price,date AS begin_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:%i:00')) AS begin_date_num ,ROUND(DATE/300)*300 AS gt FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY begin_date ASC) a GROUP BY ROUND(begin_date/300)*300) AS b " + 
    				" ON a.gt=b.gt " + 
    				" INNER JOIN " +
    				" (SELECT  MAX(deal_price) AS max_price ,MIN(deal_price) AS min_price , SUM(deal_num) AS deal_num ," + 
    				" (MAX(deal_price)-MIN(deal_price)) AS difference_price ,((MAX(deal_price)-MIN(deal_price))/MAX(deal_price)) AS difference_percentage ," + 
    				" deal_type AS price_type ,ROUND(DATE/300)*300 AS gt FROM t_inesv_deal_detail WHERE coin_no=? GROUP BY gt ORDER BY gt DESC LIMIT 0,1) AS c " + 
    				" ON b.gt=c.gt ";
    	}if(detailType.equals("3")){
    		sql="SELECT * FROM " + 
    				" (SELECT * FROM (SELECT (deal_price) AS end_price,date AS end_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:%i:00')) AS end_date_num ,ROUND(DATE/500)*500 AS gt FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY end_date DESC) a GROUP BY ROUND(end_date/500)*500) AS a " +
    				" INNER JOIN " + 
    				" (SELECT * FROM (SELECT (deal_price) AS begin_price,date AS begin_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:%i:00')) AS begin_date_num ,ROUND(DATE/500)*500 AS gt FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY begin_date ASC) a GROUP BY ROUND(begin_date/500)*500) AS b " + 
    				" ON a.gt=b.gt " + 
    				" INNER JOIN " +
    				" (SELECT  MAX(deal_price) AS max_price ,MIN(deal_price) AS min_price , SUM(deal_num) AS deal_num ," + 
    				" (MAX(deal_price)-MIN(deal_price)) AS difference_price ,((MAX(deal_price)-MIN(deal_price))/MAX(deal_price)) AS difference_percentage ," + 
    				" deal_type AS price_type ,ROUND(DATE/500)*500 AS gt FROM t_inesv_deal_detail WHERE coin_no=? GROUP BY gt ORDER BY gt DESC LIMIT 0,1) AS c " + 
    				" ON b.gt=c.gt ";
    	}if(detailType.equals("4")){
    		sql="SELECT * FROM " + 
    				" (SELECT * FROM (SELECT (deal_price) AS end_price,date AS end_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:%i:00')) AS end_date_num ,ROUND(DATE/1500)*1500 AS gt FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY end_date DESC) a GROUP BY ROUND(end_date/1500)*1500) AS a " +
    				" INNER JOIN " + 
    				" (SELECT * FROM (SELECT (deal_price) AS begin_price,date AS begin_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:%i:00')) AS begin_date_num ,ROUND(DATE/1500)*1500 AS gt FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY begin_date ASC) a GROUP BY ROUND(begin_date/1500)*1500) AS b " + 
    				" ON a.gt=b.gt " + 
    				" INNER JOIN " +
    				" (SELECT  MAX(deal_price) AS max_price ,MIN(deal_price) AS min_price , SUM(deal_num) AS deal_num ," + 
    				" (MAX(deal_price)-MIN(deal_price)) AS difference_price ,((MAX(deal_price)-MIN(deal_price))/MAX(deal_price)) AS difference_percentage ," + 
    				" deal_type AS price_type ,ROUND(DATE/1500)*1500 AS gt FROM t_inesv_deal_detail WHERE coin_no=? GROUP BY gt ORDER BY gt DESC LIMIT 0,1) AS c " + 
    				" ON b.gt=c.gt ";
    	}if(detailType.equals("5")){
    		sql="SELECT * FROM " + 
    				" (SELECT * FROM (SELECT (deal_price) AS end_price,date AS end_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:%i:00')) AS end_date_num ,ROUND(DATE/3000)*3000 AS gt FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY end_date DESC) a GROUP BY ROUND(end_date/3000)*3000) AS a " +
    				" INNER JOIN " + 
    				" (SELECT * FROM (SELECT (deal_price) AS begin_price,date AS begin_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:%i:00')) AS begin_date_num ,ROUND(DATE/3000)*3000 AS gt FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY begin_date ASC) a GROUP BY ROUND(begin_date/3000)*3000) AS b " + 
    				" ON a.gt=b.gt " + 
    				" INNER JOIN " +
    				" (SELECT  MAX(deal_price) AS max_price ,MIN(deal_price) AS min_price , SUM(deal_num) AS deal_num ," + 
    				" (MAX(deal_price)-MIN(deal_price)) AS difference_price ,((MAX(deal_price)-MIN(deal_price))/MAX(deal_price)) AS difference_percentage ," + 
    				" deal_type AS price_type ,ROUND(DATE/3000)*3000 AS gt FROM t_inesv_deal_detail WHERE coin_no=? GROUP BY gt ORDER BY gt DESC LIMIT 0,1) AS c " + 
    				" ON b.gt=c.gt ";
    	}if(detailType.equals("6")){
    		sql="SELECT * FROM " + 
    				" (SELECT * FROM (SELECT (deal_price) AS end_price,date AS end_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:00:00')) AS end_date_num ,ROUND(DATE/6000)*6000 AS gt FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY end_date DESC) a GROUP BY ROUND(end_date/6000)*6000) AS a " +
    				" INNER JOIN " + 
    				" (SELECT * FROM (SELECT (deal_price) AS begin_price,date AS begin_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:00:00')) AS begin_date_num ,ROUND(DATE/6000)*6000 AS gt FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY begin_date ASC) a GROUP BY ROUND(begin_date/6000)*6000) AS b " + 
    				" ON a.gt=b.gt " + 
    				" INNER JOIN " + 
    				" (SELECT  MAX(deal_price) AS max_price ,MIN(deal_price) AS min_price , SUM(deal_num) AS deal_num ," + 
    				" (MAX(deal_price)-MIN(deal_price)) AS difference_price ,((MAX(deal_price)-MIN(deal_price))/MAX(deal_price)) AS difference_percentage ," + 
    				" deal_type AS price_type ,ROUND(DATE/6000)*6000 AS gt FROM t_inesv_deal_detail WHERE coin_no=? GROUP BY gt ORDER BY gt DESC LIMIT 0,1) AS c " + 
    				" ON b.gt=c.gt ";
    	}if(detailType.equals("7")){
    		sql="SELECT * FROM " + 
    				" (SELECT * FROM (SELECT (deal_price) AS end_price,date AS end_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:00:00')) AS end_date_num ,ROUND(DATE/12000)*12000 AS gt FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY end_date DESC) a GROUP BY ROUND(end_date/12000)*12000) AS a " +
    				" INNER JOIN " + 
    				" (SELECT * FROM (SELECT (deal_price) AS begin_price,date AS begin_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:00:00')) AS begin_date_num ,ROUND(DATE/12000)*12000 AS gt FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY begin_date ASC) a GROUP BY ROUND(begin_date/12000)*12000) AS b " + 
    				" ON a.gt=b.gt " + 
    				" INNER JOIN " + 
    				" (SELECT  MAX(deal_price) AS max_price ,MIN(deal_price) AS min_price , SUM(deal_num) AS deal_num ," + 
    				" (MAX(deal_price)-MIN(deal_price)) AS difference_price ,((MAX(deal_price)-MIN(deal_price))/MAX(deal_price)) AS difference_percentage ," + 
    				" deal_type AS price_type ,ROUND(DATE/12000)*12000 AS gt FROM t_inesv_deal_detail WHERE coin_no=? GROUP BY gt ORDER BY gt DESC LIMIT 0,1) AS c " + 
    				" ON b.gt=c.gt ";
    	}if(detailType.equals("8")){
    		sql="SELECT * FROM " + 
    				" (SELECT * FROM (SELECT (deal_price) AS end_price,date AS end_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:00:00')) AS end_date_num ,ROUND(DATE/24000)*24000 AS gt FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY end_date DESC) a GROUP BY ROUND(end_date/24000)*24000) AS a " +
    				" INNER JOIN " + 
    				" (SELECT * FROM (SELECT (deal_price) AS begin_price,date AS begin_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:00:00')) AS begin_date_num ,ROUND(DATE/24000)*24000 AS gt FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY begin_date ASC) a GROUP BY ROUND(begin_date/24000)*24000) AS b " + 
    				" ON a.gt=b.gt " + 
    				" INNER JOIN " + 
    				" (SELECT  MAX(deal_price) AS max_price ,MIN(deal_price) AS min_price , SUM(deal_num) AS deal_num ," + 
    				" (MAX(deal_price)-MIN(deal_price)) AS difference_price ,((MAX(deal_price)-MIN(deal_price))/MAX(deal_price)) AS difference_percentage ," + 
    				" deal_type AS price_type ,ROUND(DATE/24000)*24000 AS gt FROM t_inesv_deal_detail WHERE coin_no=? GROUP BY gt ORDER BY gt DESC LIMIT 0,1) AS c " + 
    				" ON b.gt=c.gt ";
    	}if(detailType.equals("9")){
    		sql="SELECT * FROM " + 
    				" (SELECT * FROM (SELECT (deal_price) AS end_price,date AS end_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:00:00')) AS end_date_num ,ROUND(DATE/36000)*36000 AS gt FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY end_date DESC) a GROUP BY ROUND(end_date/36000)*36000) AS a " +
    				" INNER JOIN " + 
    				" (SELECT * FROM (SELECT (deal_price) AS begin_price,date AS begin_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:00:00')) AS begin_date_num ,ROUND(DATE/36000)*36000 AS gt FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY begin_date ASC) a GROUP BY ROUND(begin_date/36000)*36000) AS b " + 
    				" ON a.gt=b.gt " + 
    				" INNER JOIN " +
    				" (SELECT  MAX(deal_price) AS max_price ,MIN(deal_price) AS min_price , SUM(deal_num) AS deal_num ," + 
    				" (MAX(deal_price)-MIN(deal_price)) AS difference_price ,((MAX(deal_price)-MIN(deal_price))/MAX(deal_price)) AS difference_percentage ," + 
    				" deal_type AS price_type ,ROUND(DATE/36000)*36000 AS gt FROM t_inesv_deal_detail WHERE coin_no=? GROUP BY gt ORDER BY gt DESC LIMIT 0,1) AS c " + 
    				" ON b.gt=c.gt ";
    	}if(detailType.equals("10")){
    		sql="SELECT * FROM " + 
    				" (SELECT * FROM (SELECT (deal_price) AS end_price,date AS end_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:00:00')) AS end_date_num ,ROUND(DATE/72000)*72000 AS gt FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY end_date DESC) a GROUP BY ROUND(end_date/72000)*72000) AS a " +
    				" INNER JOIN " + 
    				" (SELECT * FROM (SELECT (deal_price) AS begin_price,date AS begin_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d %H:00:00')) AS begin_date_num ,ROUND(DATE/72000)*72000 AS gt FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY begin_date ASC) a GROUP BY ROUND(begin_date/72000)*72000) AS b " + 
    				" ON a.gt=b.gt " + 
    				" INNER JOIN " +
    				" (SELECT  MAX(deal_price) AS max_price ,MIN(deal_price) AS min_price , SUM(deal_num) AS deal_num ," + 
    				" (MAX(deal_price)-MIN(deal_price)) AS difference_price ,((MAX(deal_price)-MIN(deal_price))/MAX(deal_price)) AS difference_percentage ," + 
    				" deal_type AS price_type ,ROUND(DATE/72000)*72000 AS gt FROM t_inesv_deal_detail WHERE coin_no=? GROUP BY gt ORDER BY gt DESC LIMIT 0,1) AS c " + 
    				" ON b.gt=c.gt ";
    	}
    	if(detailType.equals("11")){
    		sql="SELECT * FROM " + 
    				" (SELECT * FROM (SELECT (deal_price) AS end_price,date AS end_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d 00:00:00')) AS end_date_num ,ROUND(DATE/144000)*144000 AS gt FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY end_date DESC) a GROUP BY ROUND(end_date/144000)*144000) AS a " +
    				" INNER JOIN " + 
    				" (SELECT * FROM (SELECT (deal_price) AS begin_price,date AS begin_date, UNIX_TIMESTAMP(DATE_FORMAT(DATE,'%Y-%m-%d 00:00:00')) AS begin_date_num ,ROUND(DATE/144000)*144000 AS gt FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY begin_date ASC) a GROUP BY ROUND(begin_date/144000)*144000) AS b " + 
    				" ON a.gt=b.gt " + 
    				" INNER JOIN " +
    				" (SELECT  MAX(deal_price) AS max_price ,MIN(deal_price) AS min_price , SUM(deal_num) AS deal_num ," + 
    				" (MAX(deal_price)-MIN(deal_price)) AS difference_price ,((MAX(deal_price)-MIN(deal_price))/MAX(deal_price)) AS difference_percentage ," + 
    				" deal_type AS price_type ,ROUND(DATE/144000)*144000 AS gt FROM t_inesv_deal_detail WHERE coin_no=? GROUP BY gt ORDER BY gt DESC LIMIT 0,1) AS c " + 
    				" ON b.gt=c.gt ";
    	}
        Object params[] = {priceType,priceType,priceType};
        try {
        	kDealDetail=queryRunner.query(sql,new BeanHandler<KDealDetailDto>(KDealDetailDto.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("根据用户查询交易记录失败");
		}
        return kDealDetail;
    }
    
    /*
     * 查询货币最新状况
     */
    public CoinDetailDto queryCoinDetailInfo(String priceType){
    	 
    	CoinDetailDto coinDetailDto = new CoinDetailDto();
    	String querySql = "";
    	try {
    		querySql="SELECT newes_deal AS newPrice ,"
    			+ " buy_price AS buyPrice ,"
    			+ " sell_price AS sellPrice ,"
    			+ " deal_num AS volume ,"
    			+ " deal_price AS turnover ,"
    			+ " max_price AS maxPrice ,"
    			+ " min_price AS minPrice ,"
    			+ " day_percent AS highsAndLows "
    			+ " FROM t_inesv_day_market WHERE coin_type = ? AND TO_DAYS(DATE) = TO_DAYS(NOW()) ORDER BY DATE DESC LIMIT 1 ";
    		Object params1[] = {priceType};
    		coinDetailDto=queryRunner.query(querySql,new BeanHandler<CoinDetailDto>(CoinDetailDto.class),params1);
    		if(coinDetailDto==null){
    			querySql=" SELECT  "
  	    			 + " @num1:=IFNULL((SELECT deal_price FROM t_inesv_deal_detail WHERE coin_no= ? ORDER BY id DESC LIMIT 0,1),0) AS newPrice,  "
  	    			 + " IFNULL((SELECT deal_price FROM t_inesv_deal_detail WHERE deal_type=0 AND coin_no= ? ORDER BY id DESC LIMIT 0,1),0) AS buyPrice,  "
  	    			 + " IFNULL((SELECT deal_price FROM t_inesv_deal_detail WHERE deal_type=1 AND coin_no= ? ORDER BY id DESC LIMIT 0,1),0) AS sellPrice,  "
  	    			 + " @num2:=IFNULL(IFNULL(MAX(deal_price),IFNULL((SELECT deal_price FROM t_inesv_deal_detail WHERE coin_no= ? ORDER BY id DESC LIMIT 0,1),0)),0) AS maxPrice ,  "
  	    			 + " IFNULL(IFNULL(MIN(deal_price),IFNULL((SELECT deal_price FROM t_inesv_deal_detail WHERE coin_no= ? ORDER BY id DESC LIMIT 0,1),0)),0) AS minPrice,  "
  	    			 + " @num3:=IFNULL(IFNULL((SELECT deal_price FROM t_inesv_deal_detail WHERE coin_no = ? AND TO_DAYS(DATE) = TO_DAYS(NOW()) LIMIT 0,1), "
  	  	    		 + " (SELECT deal_price FROM t_inesv_deal_detail WHERE coin_no= ? ORDER BY id DESC LIMIT 0,1)),0) AS begin_price, "
  	    			 + " FORMAT(IFNULL((((@num1-@num3)/@num3)*100),0),2) AS highsAndLows,  IFNULL(SUM(deal_price*deal_num),0) AS turnover , IFNULL(SUM(deal_num),0) AS volume "
  	    			 + " FROM t_inesv_deal_detail WHERE TO_DAYS(DATE) = TO_DAYS(NOW()) AND coin_no= ? ";
    			String params2[] = {priceType,priceType,priceType,priceType,priceType,priceType,priceType,priceType};
    			coinDetailDto=queryRunner.query(querySql,new BeanHandler<CoinDetailDto>(CoinDetailDto.class),params2);
    		}   
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("根据用户查询交易记录失败");
		}
        return coinDetailDto;
    }
    
    /*
     * 查询货币升降状态
     */
    public CoinDetailDto queryCoinDetailInfoDynamic(String priceType){
    	CoinDetailDto coinDetailDto = null;
    	String querySql = "";
    	try {
    		querySql="SELECT IFNULL( "
    				+ " IFNULL((SELECT deal_price FROM t_inesv_deal_detail WHERE coin_no= ? ORDER BY id DESC LIMIT 1,1),(SELECT deal_price FROM t_inesv_deal_detail WHERE coin_no= ? ORDER BY id DESC LIMIT 1)),0) AS attr1 "
    				+ " FROM t_inesv_deal_detail LIMIT 1 ";
    		Object params[] = {priceType,priceType};
    		coinDetailDto=queryRunner.query(querySql,new BeanHandler<CoinDetailDto>(CoinDetailDto.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("根据用户查询交易记录失败");
		}
        return coinDetailDto;
    }

    /**
     * 根据用户查询交易记录
     * @param userAuthoritys
     * @return
     * @throws SQLException 
     */
	public List<DealDetailDto> queryDealDetailInfoByUserNo(String userNo){
    	List<DealDetailDto> dealDetailList = new ArrayList<>();
        String querySql = "select d.*,c.coin_name as attr1 from t_inesv_deal_detail d join t_inesv_coin_type c " +
				"on d.coin_no = c.coin_no where user_no=?";
        Object params[] = {userNo};
        try {
			dealDetailList=queryRunner.query(querySql,new BeanListHandler<DealDetailDto>(DealDetailDto.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("根据用户查询交易记录失败");
		}
        return dealDetailList;
    }
	
	/**
     * 查询某一天的交易记录
     * @param userAuthoritys
     * @return
     * @throws SQLException 
     */
	public List<DealDetailDto> queryDealDetailInfoByDay(String day){
    	List<DealDetailDto> dealDetailList = new ArrayList<>();
        String querySql = "select * from t_inesv_deal_detail where date like ? order by date desc";
        Object params[] = {day+"%"};
        try {
			dealDetailList=queryRunner.query(querySql,new BeanListHandler<DealDetailDto>(DealDetailDto.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("查询失败");
		}
        return dealDetailList;
    }
	
	/**
     * 查询某一天的交易记录的所有币种类型
     * @param userAuthoritys
     * @return
     * @throws SQLException 
     */
	public List<DealDetailDto> queryDealDetailTypeInfoByDay(String day){
    	List<DealDetailDto> dealDetailList = new ArrayList<>();
        String querySql = "select DISTINCT coin_no,deal_price from t_inesv_deal_detail where date like ? order by date desc";
        Object params[] = {day+"%"};
        try {
			dealDetailList=queryRunner.query(querySql,new BeanListHandler<DealDetailDto>(DealDetailDto.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("查询失败");
		}
        return dealDetailList;
    }

	/**
	 * 根据币种查询有关交易记录
	 * */
	public List<DealDetailDto> queryDealDetailByCoinNo(Integer coin_no,Integer deal_type,Integer limit){
		List<DealDetailDto> dealDetailDtos = new ArrayList<>();
		String sql = "select * from t_inesv_deal_detail where coin_no = ?" + (deal_type==2?"":" and deal_type = ? ")+  " ORDER BY date DESC limit ?";
		Object params[];
		params = deal_type==2?(new Object[]{coin_no, limit}):(new Object[]{coin_no,deal_type,limit});
		
		try {
			dealDetailDtos=queryRunner.query(sql, new BeanListHandler<DealDetailDto>(DealDetailDto.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dealDetailDtos;
	}
	
	/**
	 * 根据币种查询有关委托记录
	 * */
	public List<EntrustDto> queryEntrustByCoinNo(Integer coin_no,Integer deal_type,Integer limit){
		List<EntrustDto> entrustDtos = new ArrayList<>();
		String sql = "select * from t_inesv_entrust where entrust_coin = ?" + (deal_type==2?"":" and entrust_type = ? ")+  " ORDER BY id DESC limit ?";
		Object params[];
		params = deal_type==2?(new Object[]{coin_no, limit}):(new Object[]{coin_no,deal_type,limit});
		try {
			entrustDtos=queryRunner.query(sql, new BeanListHandler<EntrustDto>(EntrustDto.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entrustDtos;
	}
	
	/**
	 * 根据币种查询有关交易记录
	 * */
	public List<DealDetailDto> queryDetailByCoinNo(String coin_no){
		List<DealDetailDto> dealDetailDtos = new ArrayList<>();
		String sql = "select * from t_inesv_deal_detail where coin_no = ? ORDER BY id DESC limit 50";
		Object params[] = {coin_no};
		try {
			dealDetailDtos=queryRunner.query(sql, new BeanListHandler<DealDetailDto>(DealDetailDto.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dealDetailDtos;
	}
	
	/**
	 * 首页交易统计
	 * */
	public List<DealDetailDto> queryDetail(){
		List<DealDetailDto> dealDetailDtos = new ArrayList<>();
		String sql = "SELECT IFNULL(SUM(sum_price),0) AS sum_price , DATE_FORMAT(DATE,'%Y-%m-%d') dates FROM t_inesv_deal_detail GROUP BY dates";
		try {
			dealDetailDtos=queryRunner.query(sql, new BeanListHandler<DealDetailDto>(DealDetailDto.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dealDetailDtos;
	}
}
