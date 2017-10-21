package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.BuyEntrustDepthDto;
import com.inesv.digiccy.dto.EntrustDto;
import com.inesv.digiccy.dto.FicWithdrawDto;
import com.inesv.digiccy.dto.pageDto;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 鏌ヨ鐢ㄦ埛鎷ユ湁鐨勮祫婧�
 * Created by JimJim on 2016/11/4 0004.
 */
@Component
public class QueryEntrustInfo {
	private static Logger log = LoggerFactory.getLogger(QueryEntrustInfo.class);
	
    @Autowired
    QueryRunner queryRunner;

    
    public Long getEntrustSize(int userNo, int coinType){
    	String sql = "SELECT count(*) as count  FROM t_inesv_entrust a, t_inesv_user b, t_inesv_coin_type c WHERE a.user_no=b.user_no AND a.entrust_coin=c.coin_no and a.user_no=?";
    	ArrayList<Object> paraArr = new ArrayList<>();
    	paraArr.add(userNo);
    	if(coinType != 0){
    		sql += " and a.entrust_coin=?";
    		paraArr.add(coinType);
    	}
    	try {
			return (Long) queryRunner.query(sql, new ColumnListHandler<>("count"), paraArr.toArray(new Object[]{})).get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Long.parseLong("0");
		}
    }
    
    /**
     * 鍒嗛〉鏌ヨ濮旀墭璁板綍
     */
    public List<EntrustDto> queryPagingEntrust(int pageNum, int itemCount, int userNo, int coinType){
    	List<EntrustDto> entrustList = new ArrayList<EntrustDto>();
    	String last = " order by a.date desc limit ?,?";
    	String sql = "SELECT a.user_no, a.entrust_coin,a.id, a.entrust_type, a.entrust_price, a.entrust_num, a.deal_num, a.piundatge, a.state,a.date, b.username AS attr2, c.coin_name AS attr3  FROM t_inesv_entrust a, t_inesv_user b, t_inesv_coin_type c WHERE a.user_no=b.user_no AND a.entrust_coin=c.coin_no and a.user_no=?";
    	ArrayList<Object> paraArr = new ArrayList<>();
    	paraArr.add(userNo);
    	if(coinType != 0){
    		sql += " and a.entrust_coin=?";
    		paraArr.add(coinType);
    	}
    	sql += last;
    	int startItem = itemCount*(pageNum-1);
    	paraArr.add(startItem);
    	paraArr.add(itemCount);
    	try {
			entrustList = queryRunner.query(sql, new BeanListHandler<EntrustDto>(EntrustDto.class), paraArr.toArray(new Object[]{}));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("鏌ヨ鍒嗛〉濮旀墭璁板綍澶辫触");
			return null;
		}
    	return entrustList;
    }
    /**
     * 鏍规嵁鐢ㄦ埛鏌ヨ濮旀墭璁板綍
     * @param userNo
     * @return
     * @throws SQLException 
     */
	public List<EntrustDto> queryEntrustInfoByUserNo(String userNo){
    	List<EntrustDto> EntrustList = new ArrayList<>();
        String querySql = "select e.*,c.coin_name as attr1 from t_inesv_entrust e " +
                "join t_inesv_coin_type c on c.coin_no = e.entrust_coin where user_no=? ";
        Object params[] = {userNo};
        try {
			EntrustList=(List<EntrustDto>)queryRunner.query(querySql,new BeanListHandler<EntrustDto>(EntrustDto.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("鏍规嵁鐢ㄦ埛鏌ヨ濮旀墭璁板綍澶辫触");
		}
        return EntrustList;
    }

	/**
	 * 寰楀埌璇ョ敤鎴峰墠10鏉℃湭浜ゆ槗鐨勫鎵樿褰�
	 * @param userNo
	 * @return
	 */
	public List<EntrustDto> getNotTradeEntrustOfTradeCenter(Integer userNo,Integer state,Integer number) {
		List<EntrustDto> EntrustList = new ArrayList<>();
        String querySql = "select * from t_inesv_entrust  where user_no=? and state=? order by date desc limit ? ";
        Object params[] = {userNo,state,number};
        try {
			EntrustList=(List<EntrustDto>)queryRunner.query(querySql,new BeanListHandler<EntrustDto>(EntrustDto.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("寰楀埌璇ョ敤鎴峰墠10鏉℃湭浜ゆ槗鐨勫鎵樿褰曪紒锛侊紒");
		}
        return EntrustList;
	}

	/**
	 * 寰楀埌鏌愮璐у竵鍓�15鏉＄殑涔板崠濮旀墭璁板綍
	 * @param entrustCoin
	 * @param entrustType
	 * @return
	 */
	public List<EntrustDto> queryEntrustInfoOfTradeCenter(Integer entrustCoin,Integer entrustType, Integer number) {
		List<EntrustDto> EntrustList = new ArrayList<>();
        String querySql = "select * from t_inesv_entrust  where entrust_coin=? and entrust_type=? order by date desc limit ?";
        Object params[] = {entrustCoin,entrustType,number};
        try {
			EntrustList=(List<EntrustDto>)queryRunner.query(querySql,new BeanListHandler<EntrustDto>(EntrustDto.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("寰楀埌鏌愮璐у竵鍓�15鏉＄殑涔板崠濮旀墭璁板綍澶辫触锛侊紒锛�");
		}
        return EntrustList;
	}

    /**
     * 鏍规嵁濮旀墭浠锋牸锛岃揣甯佺被鍨嬶紝浜ゆ槗绫诲瀷锛屽鎵樼姸鎬佹煡鎵惧鎵樿褰�
     * @param entrustPrice
     * @param entrustCoin
     * @param entrustType
     * @param state
     * @return
     */
    public List<EntrustDto> queryEntrustByEntrustPriceEntrustCoinAndEntrustTypeAndState(BigDecimal entrustPrice, Integer entrustCoin, Integer entrustType, Integer state){
        String sql="select * from t_inesv_entrust where entrust_price=? entrust_coin =? and entrust_type=? and state=? and entrust_num!=deal_num order by date asc";
        Object params[] = {entrustPrice,entrustCoin,entrustType,state};
        List<EntrustDto> list = null;
        try {
            list = queryRunner.query(sql,new BeanListHandler<EntrustDto>(EntrustDto.class),params);
        } catch (SQLException e) {
            log.debug("鏍规嵁濮旀墭浠锋牸,璐у竵绫诲瀷锛屼氦鏄撶被鍨嬶紝濮旀墭鐘舵�佹煡鎵惧鎵樿褰曞け璐�");
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 鏍规嵁浜ゆ槗绫诲瀷锛屽鎵樼姸鎬佹煡鎵惧鎵樿褰�
     * @param entrustType
     * @param state
     * @return
     */
    public List<EntrustDto> queryEntrustByEntrustTypeAndState(Integer entrustType,Integer state){
        String sql="select * from t_inesv_entrust where entrust_type=? and state=? and entrust_num!=deal_num order by date asc for update";
        Object params[] = {entrustType,state};
        List<EntrustDto> list = null;
        try {
            list = queryRunner.query(sql,new BeanListHandler<EntrustDto>(EntrustDto.class),params);
        } catch (SQLException e) {
            log.debug("鏍规嵁浜ゆ槗绫诲瀷锛屽鎵樼姸鎬佹煡鎵惧鎵樿褰曞け璐�");
            e.printStackTrace();
        }
        return list;
    }
    
    public Integer getEntrustSize(String userCode, String phone, String realName,String state,String startData,String endData ){
    	   EntrustDto entrustDto = null;
 
           String sql="SELECT COUNT(*) AS count FROM  t_inesv_entrust e INNER JOIN t_inesv_user u ON e.user_no = u.user_no " + 
           		  	  " INNER JOIN t_inesv_coin_type c ON c.coin_no = e.entrust_coin";
        
           ArrayList<Object> paramArr = new ArrayList<>();
           
           if(userCode != null && !"".equals(userCode) && !"-1".equals(userCode)) {
           	try {
   				userCode = new String(userCode.getBytes("iso-8859-1"),"utf-8");
   			} catch (UnsupportedEncodingException e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   			}
           	String str = sql.contains("where")?" and u.org_code like ?":" where u.org_code like ?";
           	sql += str;
           	paramArr.add("%"+userCode+"%");
           }
           if(phone != null && !"".equals(phone) && !"-1".equals(phone)) {
           	String str = sql.contains("where")?" and u.username like ?":" where u.username like ?";
           	sql += str;
           	paramArr.add("%"+phone+"%");
           }
           
           if(realName != null && !"".equals(realName) && !"-1".equals(realName)) {
           	try {
           		realName = new String(realName.getBytes("iso-8859-1"),"utf-8");
   			} catch (UnsupportedEncodingException e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   			}
           	String str = sql.contains("where")?" and u.real_name like ?":" where u.real_name like ?";
           	sql += str;
           	paramArr.add("%"+realName+"%");
           }
           
           
           if(state != null && !"".equals(state) && !"-1".equals(state)) {
           	String str = sql.contains("where")?" and e.state like ?":" where e.state like ?";
           	sql += str;
           	paramArr.add("%"+state+"%");
           }
    
    
           if(startData != null && !"".equals(startData) && endData != null && !"".equals(endData)){
           	String str = sql.contains("where")?" and w.date between ? and ?":" where w.date between ? and ?";
           	sql += str;
           	Date sdate = Date.valueOf(startData);
           	Date edate = Date.valueOf(endData);
           	paramArr.add(sdate);
           	paramArr.add(edate);
           }
  
           
           try {
           	System.out.println("**************sql**********: "+sql);
           	entrustDto = queryRunner.query(sql, new BeanHandler<>(EntrustDto.class),paramArr.toArray(new Object[]{}));
           } catch (SQLException e) {
               e.printStackTrace();
           }
    
           return  entrustDto.getCount();
    }
    public List<EntrustDto> queryEntrustInfoAll(String userCode, String phone, String realName,String state,String startData,String endData,pageDto page){
        List<EntrustDto> entrustDtoList = new ArrayList<>();
 
        String limitstr = " limit ?,? ";
        Integer firstRecord = page.getFirstRecord();
        Integer pageSize = page.getPageSize();
        String sql="SELECT e.id AS id,u.username AS userName,u.real_name AS realName,u.org_code AS userCode,c.coin_name AS coinName,e.entrust_type AS entrust_type,e.entrust_price AS entrust_price,e.entrust_num AS entrust_num," + 
        		"e.deal_num AS deal_num,e.piundatge AS piundatge,e.date AS DATE,e.state AS state " + 
        		"FROM  t_inesv_entrust e INNER JOIN t_inesv_user u ON e.user_no = u.user_no " + 
        		" INNER JOIN t_inesv_coin_type c ON c.coin_no = e.entrust_coin";
     
        ArrayList<Object> paramArr = new ArrayList<>();
        
        if(userCode != null && !"".equals(userCode) && !"-1".equals(userCode)) {
        	try {
				userCode = new String(userCode.getBytes("iso-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	String str = sql.contains("where")?" and u.org_code like ?":" where u.org_code like ?";
        	sql += str;
        	paramArr.add("%"+userCode+"%");
        }
        if(phone != null && !"".equals(phone) && !"-1".equals(phone)) {
        	String str = sql.contains("where")?" and u.username like ?":" where u.username like ?";
        	sql += str;
        	paramArr.add("%"+phone+"%");
        }
        
        if(realName != null && !"".equals(realName) && !"-1".equals(realName)) {
        	try {
        		realName = new String(realName.getBytes("iso-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	String str = sql.contains("where")?" and u.real_name like ?":" where u.real_name like ?";
        	sql += str;
        	paramArr.add("%"+realName+"%");
        }
        
        
        if(state != null && !"".equals(state) && !"-1".equals(state)) {
        	String str = sql.contains("where")?" and e.state like ?":" where e.state like ?";
        	sql += str;
        	paramArr.add("%"+state+"%");
        }
 
 
        if(startData != null && !"".equals(startData) && endData != null && !"".equals(endData)){
        	String str = sql.contains("where")?" and w.date between ? and ?":" where w.date between ? and ?";
        	sql += str;
        	Date sdate = Date.valueOf(startData);
        	Date edate = Date.valueOf(endData);
        	paramArr.add(sdate);
        	paramArr.add(edate);
        }
        
        sql +=limitstr;
        paramArr.add(firstRecord);
        paramArr.add(pageSize);
        
        
        try {
        	System.out.println("**************sql**********: "+sql);
        	entrustDtoList = queryRunner.query(sql, new BeanListHandler<>(EntrustDto.class),paramArr.toArray(new Object[]{}));
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return  entrustDtoList;
    }
    
    /**
     * 娣卞害鍥�
     * */
    public List<BuyEntrustDepthDto> queryBuyEntrustByEntrustCoinOrderBy(Integer entrust_coin){
    	String sql = "SELECt entrust_coin,entrust_price,count(*) FROM t_inesv_entrust WHERE entrust_coin = ? and entrust_type = 0 GROUP BY entrust_price DESC";
    	Object param[] = {entrust_coin};
    	List<BuyEntrustDepthDto> list = new ArrayList<BuyEntrustDepthDto>();
    	try {
			list = queryRunner.query(sql, new BeanListHandler<BuyEntrustDepthDto>(BuyEntrustDepthDto.class),param);
		} catch (SQLException e) {
			log.error("鏌ヨ娣卞害鍥惧け璐�");
			e.printStackTrace();
		}
    	return list;
    }
    public List<BuyEntrustDepthDto> querySellEntrustByEntrustCoinOrderBy(Integer entrust_coin){
    	String sql = "SELECt entrust_coin,entrust_price,count(*) FROM t_inesv_entrust WHERE entrust_coin = ? and entrust_type = 1 GROUP BY entrust_price DESC";
    	Object param[] = {entrust_coin};
    	List<BuyEntrustDepthDto> list = new ArrayList<BuyEntrustDepthDto>();
    	try {
			list = queryRunner.query(sql, new BeanListHandler<BuyEntrustDepthDto>(BuyEntrustDepthDto.class),param);
		} catch (SQLException e) {
			log.error("鏌ヨ娣卞害鍥惧け璐�");
			e.printStackTrace();
		}
    	return list;
    }
    
    public List<EntrustDto> queryEntrustInfoAllByDay(){
        List<EntrustDto> entrustDtoList = new ArrayList<>();
        String sql = "SELECT * FROM t_inesv_entrust WHERE TO_DAYS(DATE) = TO_DAYS(NOW())";
        try {
            entrustDtoList = queryRunner.query(sql,new BeanListHandler<EntrustDto>(EntrustDto.class));
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("鏌ヨ鎵�鏈夊鎵樿褰曞け璐�");
        }
        return  entrustDtoList;
    }
    
    public List<EntrustDto> queryEntrustInfoAllPaging(Integer pageSize,Integer lineSize){
        List<EntrustDto> entrustDtoList = new ArrayList<>();
        String sql = "select * from t_inesv_entrust limit ?,?";
        Object params[] = {(pageSize * lineSize),lineSize};
        try {
            entrustDtoList = queryRunner.query(sql,new BeanListHandler<EntrustDto>(EntrustDto.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("鏌ヨ鎵�鏈夊鎵樿褰曞け璐�");
        }
        return  entrustDtoList;
    }


    
    /**
     * 鏍规嵁濮旀墭id 鏌ヨ濮旀墭璁板綍
     */
    public EntrustDto queryEntrustInfoByID(Long id){
        String querySql = "select * FROM t_inesv_entrust  WHERE id=? ";
        Object params[] = {id};
    	EntrustDto ddd=null;
		try {
			ddd = queryRunner.query(querySql,new BeanHandler<EntrustDto>(EntrustDto.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("鏍规嵁濮旀墭id鍜岀敤鎴风紪鍙锋煡璇㈠鎵樿褰曞け璐�");
		}
        return ddd;
    }

    
    public EntrustDto queryEntrustInfoByAttr1(Long attr1){
    	EntrustDto entrustDto = new EntrustDto();
    	String sql = "select * from t_inesv_entrust where attr1 = ?";
    	Object params[] = {attr1};
    	try {
    		entrustDto = queryRunner.query(sql,new BeanHandler<EntrustDto>(EntrustDto.class),params);
    	} catch (SQLException e) {
    		e.printStackTrace();
    		log.error("鏌ヨ鎵�鏈夊鎵樿褰曞け璐�");
    	}
    	return  entrustDto;
    }
}
