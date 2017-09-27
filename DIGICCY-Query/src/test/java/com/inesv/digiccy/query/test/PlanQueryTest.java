package com.inesv.digiccy.query.test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;






import com.inesv.digiccy.dto.BuyEntrustDepthDto;
import com.inesv.digiccy.dto.CoinAndCoinProportion;
import com.inesv.digiccy.dto.CoinDto;
import com.inesv.digiccy.dto.CoinLevelProportionDto;
import com.inesv.digiccy.dto.EntrustDto;
import com.inesv.digiccy.dto.InesvAddressDto;
import com.inesv.digiccy.dto.InesvDayMarket;
import com.inesv.digiccy.dto.InesvUserAddressLevelDto;
import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.dto.PlanDto;
import com.inesv.digiccy.dto.UserBalanceDto;

public class PlanQueryTest {
	
	public AbstractApplicationContext abstractApplicationContext;
	
	public QueryRunner queryRunner;
	
	@Before
	public void init(){
		abstractApplicationContext = new ClassPathXmlApplicationContext("classpath*:META-INF/spring/digiccy-*.xml");
		abstractApplicationContext.start();
		abstractApplicationContext.registerShutdownHook();
		queryRunner = abstractApplicationContext.getBean(QueryRunner.class);
	}
	
	
	@Test
	public void ListTest() throws SQLException{
		String sql = "select l.id,c.coin_no,c.coin_name,l.level_one,l.level_two FROM t_inesv_coin_type c INNER JOIN t_coin_level_proportion l on c.coin_no = l.coin_no where c.coin_no != 0";
		List<CoinAndCoinProportion> coinAndCoinProportion = new ArrayList<CoinAndCoinProportion>();
		try {
			coinAndCoinProportion = queryRunner.query(sql, new BeanListHandler<CoinAndCoinProportion>(CoinAndCoinProportion.class));
		} catch (SQLException e) {
			//logger.error("根据币种编号查询分级比例");
			e.printStackTrace();
		}
		System.out.println(coinAndCoinProportion.toString());
		//System.out.println(coinAndCoinProportion.get(1).toString());
	}
	
	@After
	public void close(){
		abstractApplicationContext.close();
	}

}
