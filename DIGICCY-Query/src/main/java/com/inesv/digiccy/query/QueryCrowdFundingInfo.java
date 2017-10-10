package com.inesv.digiccy.query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.CrowdFundingDetailsDto;
import com.inesv.digiccy.dto.CrowdFundingDto;

/**
 * 众筹项目查询 Created by JimJim on 2017/06/05 0017.
 */
@Component
public class QueryCrowdFundingInfo {

	private static Logger logger = LoggerFactory.getLogger(QueryCrowdFundingInfo.class);

	@Autowired
	QueryRunner queryRunner;

	/**
	 * 查询所有众筹项目信息
	 * 
	 * @return
	 */
	public List<CrowdFundingDto> queryAllCrowdFunding(String pageSize, String lineSize) {
		List<CrowdFundingDto> crowdfundingList = new ArrayList<>();
		String sql = "SELECT * FROM t_crowdfunding WHERE ico_state != 2 limit ?,?";
		Object params[] = { Integer.valueOf(pageSize) * Integer.valueOf(lineSize), Integer.valueOf(lineSize) };
		try {
			crowdfundingList = queryRunner.query(sql, new BeanListHandler<>(CrowdFundingDto.class), params);
		} catch (SQLException e) {
			logger.error("查询众筹项目失败");
			e.printStackTrace();
		}
		return crowdfundingList;
	}

	public Long getCrowdFundingSize() {
		String sql = "SELECT count(*) as count  FROM t_crowdfunding where ico_state != 2";
		try {
			return (Long) queryRunner.query(sql, new ColumnListHandler<>("count")).get(0);
		} catch (SQLException e) {
			e.printStackTrace();
			return Long.parseLong("0");
		}
	}

	/**
	 * 查询所有众筹项目信息
	 * 
	 * @return
	 */
	public List<CrowdFundingDto> queryAllCrowdFundingBack() {
		List<CrowdFundingDto> crowdfundingList = new ArrayList<>();
		String sql = "SELECT * FROM t_crowdfunding WHERE ico_state != 2";
		try {
			crowdfundingList = queryRunner.query(sql, new BeanListHandler<>(CrowdFundingDto.class));
		} catch (SQLException e) {
			logger.error("查询众筹项目失败");
			e.printStackTrace();
		}
		return crowdfundingList;
	}

	/**
	 * 查询指定众筹项目信息
	 * 
	 * @return
	 */
	public CrowdFundingDto queryCrowdFundingInfo(String icoNo) {
		CrowdFundingDto crowdfunding = new CrowdFundingDto();
		String sql = "SELECT * FROM t_crowdfunding WHERE ico_no=? for update";
		Object params[] = { icoNo };
		try {
			crowdfunding = queryRunner.query(sql, new BeanHandler<>(CrowdFundingDto.class), params);
		} catch (SQLException e) {
			logger.error("查询众筹项目失败");
			e.printStackTrace();
		}
		return crowdfunding;
	}

	/**
	 * 根据id查询用户众筹项目信息
	 * 
	 * @return
	 */
	public List<CrowdFundingDetailsDto> queryCrowdFundingDetailBackById(int id) {
		System.out.println("id:" + id);
		List<CrowdFundingDetailsDto> crowdfundingList = new ArrayList<>();
		String sql = "SELECT a.id AS id,b.username AS attr1,c.ico_name AS ico_id,a.ico_user_number AS ico_user_number,ROUND(a.ico_user_number/333) AS attr2,a.ico_user_sumprice AS ico_user_sumprice,a.date AS date "
				+ " FROM t_crowdfunding_details a,t_inesv_user b,t_crowdfunding c "
				+ " WHERE a.user_id = b.user_no AND a.ico_id = c.ico_no and a.user_id=? ORDER BY a.date DESC";
		Object params[] = { id };
		// String sql = "SELECT * FROM t_crowdfunding_details";
		try {
			crowdfundingList = queryRunner.query(sql, new BeanListHandler<>(CrowdFundingDetailsDto.class), params);
		} catch (SQLException e) {
			logger.error("查询众筹项目失败");
			e.printStackTrace();
		}
		return crowdfundingList;
	}

	/**
	 * 查询所有用户众筹项目信息
	 * 
	 * @return
	 */
	public List<CrowdFundingDetailsDto> queryAllCrowdFundingDetailBack() {
		List<CrowdFundingDetailsDto> crowdfundingList = new ArrayList<>();
		String sql = "SELECT a.id AS id,b.username AS attr1, a.logistics_company,a.logistics_number,a.logistics_status,c.ico_name AS ico_id,a.ico_user_number AS ico_user_number,ROUND(a.ico_user_number/333) AS attr2,a.ico_user_sumprice AS ico_user_sumprice,a.date AS date "
				+ " FROM t_crowdfunding_details a,t_inesv_user b,t_crowdfunding c "
				+ " WHERE a.user_id = b.user_no AND a.ico_id = c.ico_no ORDER BY a.date DESC";
		// String sql = "SELECT * FROM t_crowdfunding_details";
		try {
			crowdfundingList = queryRunner.query(sql, new BeanListHandler<>(CrowdFundingDetailsDto.class));
		} catch (SQLException e) {
			logger.error("查询众筹项目失败");
			e.printStackTrace();
		}
		return crowdfundingList;
	}

	/**
	 * 查询所有用户众筹项目信息-机构
	 * 
	 * @return
	 */
	public List<CrowdFundingDetailsDto> queryAllCrowdFundingDetailBack_Jigou() {
		List<CrowdFundingDetailsDto> crowdfundingList = new ArrayList<>();
		String sql = "SELECT IFNULL(SUM(a.ico_user_number),0) AS ico_user_number , IFNULL(SUM(ROUND(a.ico_user_number/333)),0) AS attr1, "
				+ "	b.username AS attr2, a.date AS DATE FROM t_crowdfunding_details a , t_inesv_user b WHERE a.user_id = b.user_no AND b.org_type = 0 GROUP BY user_id ORDER BY attr1 DESC LIMIT 50";
		try {
			crowdfundingList = queryRunner.query(sql, new BeanListHandler<>(CrowdFundingDetailsDto.class));
		} catch (SQLException e) {
			logger.error("查询众筹项目失败");
			e.printStackTrace();
		}
		return crowdfundingList;
	}

	/**
	 * 查询所有用户众筹项目信息-yonghu
	 * 
	 * @return
	 */
	public List<CrowdFundingDetailsDto> queryAllCrowdFundingDetailBack_User() {
		List<CrowdFundingDetailsDto> crowdfundingList = new ArrayList<>();
		String sql = "SELECT IFNULL(SUM(a.ico_user_number),0) AS ico_user_number , IFNULL(SUM(ROUND(a.ico_user_number/333)),0) AS attr1, "
				+ " b.username AS attr2, a.date AS DATE FROM t_crowdfunding_details a , t_inesv_user b WHERE a.user_id = b.user_no GROUP BY user_id ORDER BY attr1 DESC LIMIT 50";
		try {
			crowdfundingList = queryRunner.query(sql, new BeanListHandler<>(CrowdFundingDetailsDto.class));
		} catch (SQLException e) {
			logger.error("查询众筹项目失败");
			e.printStackTrace();
		}
		return crowdfundingList;
	}

	/**
	 * 查询所有用户众筹项目信息-jigou_detail
	 * 
	 * @return
	 */
	public List<CrowdFundingDetailsDto> queryAllCrowdFundingDetailBack_Jigou_detail(String orgCode) {
		List<CrowdFundingDetailsDto> crowdfundingList = new ArrayList<>();
		String sql = "SELECT user_id , ico_user_number , DATE , ROUND(t1.ico_user_number/333) AS attr2 FROM t_crowdfunding_details t1 WHERE EXISTS ( "
				+ " SELECT 1 FROM t_inesv_user t2 WHERE user_no = t1.user_id AND EXISTS ("
				+ " SELECT 1 FROM t_inesv_user t3 WHERE org_code = t2.org_parent_code AND EXISTS ("
				+ " SELECT 1 FROM t_inesv_user t4 WHERE org_code = t3.org_parent_code AND EXISTS ("
				+ " SELECT 1 FROM t_inesv_user t5 WHERE org_code = t4.org_parent_code AND org_code = ?)))) "
				+ " UNION ALL "
				+ " SELECT user_id , ico_user_number , DATE , ROUND(t1.ico_user_number/333) AS attr2 FROM t_crowdfunding_details t1 WHERE EXISTS ( "
				+ " SELECT 1 FROM t_inesv_user t2 WHERE user_no = t1.user_id AND EXISTS ("
				+ " SELECT 1 FROM t_inesv_user t3 WHERE org_code = t2.org_parent_code AND EXISTS ("
				+ " SELECT 1 FROM t_inesv_user t4 WHERE org_code = t3.org_parent_code AND org_code = ?)))"
				+ " UNION ALL "
				+ " SELECT user_id , ico_user_number , DATE , ROUND(t1.ico_user_number/333) AS attr2 FROM t_crowdfunding_details t1 WHERE EXISTS ( "
				+ " SELECT 1 FROM t_inesv_user t2 WHERE user_no = t1.user_id AND EXISTS ("
				+ " SELECT 1 FROM t_inesv_user t3 WHERE org_code = t2.org_parent_code AND org_code = ?))"
				+ " UNION ALL "
				+ " SELECT user_id , ico_user_number , DATE , ROUND(t1.ico_user_number/333) AS attr2 FROM t_crowdfunding_details t1 WHERE user_id = (SELECT user_no FROM t_inesv_user t2 WHERE org_code = ?)";
		Object params[] = { orgCode, orgCode, orgCode, orgCode };
		try {
			crowdfundingList = queryRunner.query(sql, new BeanListHandler<>(CrowdFundingDetailsDto.class), params);
		} catch (SQLException e) {
			logger.error("查询众筹项目失败");
			e.printStackTrace();
		}
		return crowdfundingList;
	}

	/**
	 * 查询指定用户众筹项目信息
	 * 
	 * @return
	 */
	public List<CrowdFundingDetailsDto> queryAllCrowdFundingDetailById(String userId, String icoNo) {
		List<CrowdFundingDetailsDto> crowdfundingList = new ArrayList<>();
		String sql = "SELECT * FROM t_crowdfunding_details WHERE user_id = ? AND ico_id = ?";
		Object params[] = { userId, icoNo };
		try {
			crowdfundingList = queryRunner.query(sql, new BeanListHandler<>(CrowdFundingDetailsDto.class), params);
		} catch (SQLException e) {
			logger.error("查询众筹项目失败");
			e.printStackTrace();
		}
		return crowdfundingList;
	}

	public CrowdFundingDetailsDto queryCrowdFunding1() {
		CrowdFundingDetailsDto crowdfunding = new CrowdFundingDetailsDto();
		String sql = "SELECT IFNULL(SUM(ico_user_number),0) AS ico_user_number FROM t_crowdfunding_details";
		try {
			crowdfunding = queryRunner.query(sql, new BeanHandler<>(CrowdFundingDetailsDto.class));
		} catch (SQLException e) {
			logger.error("查询众筹项目失败");
			e.printStackTrace();
		}
		return crowdfunding;
	}

	public CrowdFundingDetailsDto queryCrowdFunding2() {
		CrowdFundingDetailsDto crowdfunding = new CrowdFundingDetailsDto();
		String sql = "SELECT IFNULL(SUM(ico_user_number),0) AS ico_user_number FROM t_crowdfunding_details WHERE TO_DAYS(DATE) = TO_DAYS(NOW())";
		try {
			crowdfunding = queryRunner.query(sql, new BeanHandler<>(CrowdFundingDetailsDto.class));
		} catch (SQLException e) {
			logger.error("查询众筹项目失败");
			e.printStackTrace();
		}
		return crowdfunding;
	}

	public CrowdFundingDetailsDto queryCrowdFunding3() {
		CrowdFundingDetailsDto crowdfunding = new CrowdFundingDetailsDto();
		String sql = "SELECT IFNULL(SUM(ROUND(ico_user_number/333)),0) AS ico_user_number FROM t_crowdfunding_details";
		try {
			crowdfunding = queryRunner.query(sql, new BeanHandler<>(CrowdFundingDetailsDto.class));
		} catch (SQLException e) {
			logger.error("查询众筹项目失败");
			e.printStackTrace();
		}
		return crowdfunding;
	}

	public CrowdFundingDetailsDto queryCrowdFunding4() {
		CrowdFundingDetailsDto crowdfunding = new CrowdFundingDetailsDto();
		String sql = "SELECT IFNULL(SUM(ROUND(ico_user_number/333)),0) AS ico_user_number FROM t_crowdfunding_details WHERE TO_DAYS(DATE) = TO_DAYS(NOW()) ";
		try {
			crowdfunding = queryRunner.query(sql, new BeanHandler<>(CrowdFundingDetailsDto.class));
		} catch (SQLException e) {
			logger.error("查询众筹项目失败");
			e.printStackTrace();
		}
		return crowdfunding;
	}
	
	
	   /**
     * 查询所有需要更新物流信息的
     * @return
     */
    public List<CrowdFundingDetailsDto> query_wl(List<String> list) {
        List<CrowdFundingDetailsDto> crowdfundingList = new ArrayList<>();
//        Map<String, Object> map = new HashMap<>();
        
        String sql = "select * from t_crowdfunding_details as tc where tc.id in (";
        for(int i=list.size()-1;i>=0;i--){
        	if(i==list.size()-1){
        		sql+=list.get(i);
        	}else{
        		sql+=","+list.get(i);
        	}
        }
        sql+=")";

        
//        Object params[] = { list.get(0),list.get(1) };
        
        try {
            crowdfundingList = queryRunner.query(sql, new BeanListHandler<>(CrowdFundingDetailsDto.class));
        } catch (SQLException e) {
            logger.error("查询众筹项目失败");
            e.printStackTrace();
        }
        return crowdfundingList;
    }

}
