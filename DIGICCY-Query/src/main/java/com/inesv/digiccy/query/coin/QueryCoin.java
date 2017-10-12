package com.inesv.digiccy.query.coin;

import com.inesv.digiccy.dto.CoinAndWalletLinkDto;
import com.inesv.digiccy.dto.CoinDto;

import com.inesv.digiccy.dto.CurpriceDto;
import com.inesv.digiccy.dto.DealDetailDto;

import com.inesv.digiccy.dto.DayMarketDto;

import com.inesv.digiccy.dto.SubCoreDto;

import com.inesv.digiccy.dto.UserBalanceDto;
import com.inesv.digiccy.dto.pricesSumsDto;
import com.inesv.digiccy.dto.rmbBalanceDto;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 币种查询 Created by JimJim on 2016/11/17 0017.
 */
@Component
public class QueryCoin {

	private static Logger logger = LoggerFactory.getLogger(QueryCoin.class);

	@Autowired
	QueryRunner queryRunner;

	// 獲取3天交易記錄
	public List<Double> getThressTrate(Integer coinType) {
		List<Double> deals = new ArrayList<Double>();
		List<DealDetailDto> dealdetails = new ArrayList<DealDetailDto>();
		String sql = "SELECT deal_price FROM t_inesv_deal_detail "
				+ "WHERE DATE_SUB(CURDATE(), INTERVAL 2 DAY) <=DATE(DATE) " + "AND coin_no=?  AND deal_type = 1";
		Object params[] = { coinType };
		try {
			dealdetails = queryRunner.query(sql, new BeanListHandler<>(DealDetailDto.class), params);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (dealdetails.size() == 0) {
			deals.add(0d);
			deals.add(0d);
		} else if (dealdetails.size() == 1) {
			deals.add(0d);
			deals.add(dealdetails.get(0).getDeal_price().doubleValue());
		} else {
			for (DealDetailDto dealdetail : dealdetails) {
				deals.add(dealdetail.getDeal_price().doubleValue());
			}
		}
		return deals;
	}

	/**
	 * 
	 * 货币资产 userNo : 用户 flag : 标识（0人民币，1其他币） lqh 2017-7-15
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<UserBalanceDto> queryUserBalance(Integer userNo, Integer flag) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		String sql = "SELECT * FROM t_inesv_user_balance WHERE user_no=? ";
		if (flag == 0) {
			sql += "AND coin_type=0";
		} else {
			sql += "AND coin_type!=0";
		}
		Object params[] = { userNo };
		List<UserBalanceDto> userBalancelist = new ArrayList<UserBalanceDto>();
		try {
			userBalancelist = queryRunner.query(sql, new BeanListHandler<>(UserBalanceDto.class), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userBalancelist;
	}

	/**
	 * 
	 * 指定币种折合成人民币 userNo : 用户 coinNo : 币种 lqh 2017-7-15
	 * 
	 * @return
	 * @throws SQLException
	 */
	public double queryRmb(Integer userNo, Integer coinType) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		String sql = " SELECT total_price*(SELECT deal_price FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY DATE DESC LIMIT 1) rmb FROM t_inesv_user_balance WHERE user_no=? AND coin_type=?";
		Object params[] = { coinType, userNo, coinType };

		double rmbDouble = 0d;
		try {
			List<rmbBalanceDto> rmbBalances = queryRunner.query(sql, new BeanListHandler<>(rmbBalanceDto.class),
					params);
			if (rmbBalances.size() != 0) {
				rmbDouble += rmbBalances.get(0).getRmb();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rmbDouble;
	}

	/**
	 * 
	 * 获取当前市价，总市价 userNo : 用户 coinNo : 币种 lqh 2017-7-15
	 * 
	 * @return
	 * @throws SQLException
	 */
	public CurpriceDto getcurPrice(Integer userNo, Integer coinType) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		String sql = "SELECT newes_deal curprice,newes_deal*(SELECT total_price FROM t_inesv_user_balance WHERE coin_type=? AND user_no=?) sumcurprice FROM t_inesv_day_market WHERE coin_type=? ORDER BY DATE DESC LIMIT 1";
		Object params[] = { coinType, userNo, coinType };

		CurpriceDto curprice = new CurpriceDto();
		try {
			List<CurpriceDto> curprices = queryRunner.query(sql, new BeanListHandler<>(CurpriceDto.class), params);
			if (curprices.size() != 0) {
				curprice = curprices.get(0);
			} else {
				curprice.setCurprice(0);
				curprice.setSumcurprice(0);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return curprice;
	}

	/**
	 * 
	 * 查询24小时交易额/交易量 coinNo : 币种 lqh 2017-7-15
	 * 
	 * @return
	 */
	public Map<Object, Object> queryPriceSum(Integer coinNo) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		BigDecimal prices = new BigDecimal(0); // 24小时交易额
		BigDecimal sums = new BigDecimal(0); // 24小时交易量

		pricesSumsDto pricesSums = null;
		try {
			Object params[] = { coinNo };
			String sql = "SELECT SUM(deal_price) prices,SUM(deal_num) sums FROM t_inesv_deal_detail WHERE TO_DAYS( NOW( ) ) - TO_DAYS(DATE) = 0 AND coin_no=?";
			List<pricesSumsDto> pricesSumslist = queryRunner.query(sql, new BeanListHandler<>(pricesSumsDto.class),
					params);
			if (pricesSumslist.size() != 0) {
				pricesSums = pricesSumslist.get(0);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		prices = pricesSums.getPrices();
		sums = pricesSums.getSums();

		if (prices == null) {
			map.put("prices", 0);
		} else {
			map.put("prices", prices);
		}
		if (sums == null) {
			map.put("sums", 0);
		} else {
			map.put("sums", sums);
		}
		return map;
	}

	/**
	 * 
	 * 查询最新成交价
	 * 
	 */
	public DealDetailDto querynewPrice( Integer coinNo) {
		 
		DealDetailDto deals = null;
		try {
			Object params[] = {coinNo };
			String sql = "SELECT deal_price FROM t_inesv_deal_detail WHERE coin_no=? ORDER BY DATE DESC  LIMIT 1";
			deals= queryRunner.query(sql, new BeanHandler<>(DealDetailDto.class), params);
		    
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return deals;
	}

	/**
	 * 
	 * 查询某日开盘\收盘价 datef ：0今天，1昨天，2两天前 coinNo : 币种 lqh 2017-7-14
	 * 
	 * @return
	 */
	public Map<Object, Object> queryOpenClose(Integer datef, Integer coinNo) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		DealDetailDto deal1 = null;
		DealDetailDto deal2 = null;
		BigDecimal openPrice = new BigDecimal(0);
		BigDecimal closePrice = new BigDecimal(0);

		try {
			Object params[] = { datef, coinNo };
			String sql = "SELECT deal_price,DATE FROM t_inesv_deal_detail WHERE TO_DAYS( NOW( ) ) - TO_DAYS(DATE) = ? AND coin_no=? LIMIT 1";
			List<DealDetailDto> deals = queryRunner.query(sql, new BeanListHandler<>(DealDetailDto.class), params);
			if (deals.size() != 0) {
				deal1 = deals.get(0);
			}

			sql = "SELECT deal_price,DATE FROM t_inesv_deal_detail WHERE TO_DAYS( NOW( ) ) - TO_DAYS(DATE) = ?  AND coin_no=? ORDER BY DATE DESC  LIMIT 1";
			List<DealDetailDto> deals2 = queryRunner.query(sql, new BeanListHandler<>(DealDetailDto.class), params);
			if (deals2.size() != 0) {
				deal2 = deals2.get(0);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (deal1 != null) {
			openPrice = deal1.getDeal_price();
		}
		if (deal2 != null) {
			closePrice = deal2.getDeal_price();
		}
		map.put("openPrice", openPrice);
		map.put("closePrice", closePrice);
		return map;
	}

	/**
	 * 查询所有货币
	 * 
	 * @return
	 */
	public List<CoinAndWalletLinkDto> queryAllCoin() {
		List<CoinAndWalletLinkDto> coinList = new ArrayList<>();
		try {

			String sql = "SELECT distinct c.id,c.coin_no,c.coin_name,c.coin_core,c.vote,c.icon,c.state,c.date,c.address,c.attr1,c.attr2,w.host,w.post,w.wallet_name,w.wallet_pwd,w.wallet_lockpwd,c.buy_poundatge,c.sell_poundatge,c.block,c.withdraw_poundatge_one,c.withdraw_poundatge_twe,c.withdraw_poundatge_three FROM t_inesv_coin_type c LEFT JOIN t_inesv_wallet_link w ON c.coin_no = w.coin_no where w.coin_no != 0";

			coinList = queryRunner.query(sql, new BeanListHandler<>(CoinAndWalletLinkDto.class));
		} catch (SQLException e) {
			logger.error("查询虚拟货币失败");
			e.printStackTrace();
		}
		return coinList;
	}

	/**
	 * 查询所有货币信息
	 * 
	 * @return
	 */
	public List<CoinDto> queryAllCoinInfo() {
		List<CoinDto> coinList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM t_inesv_coin_type";
			coinList = queryRunner.query(sql, new BeanListHandler<>(CoinDto.class));
		} catch (SQLException e) {
			logger.error("查询虚拟货币失败");
			e.printStackTrace();
		}
		return coinList;
	}

	/**
	 * 查询所有货币的编号及名字
	 * 
	 * @return
	 */
	public List<CoinDto> queryCoinNoAndCoinNameOfCoin() {
		List<CoinDto> coinList = new ArrayList<>();
		try {
			String sql = "SELECT coin_no,coin_name FROM t_inesv_coin_type";
			coinList = queryRunner.query(sql, new BeanListHandler<>(CoinDto.class));
		} catch (SQLException e) {
			logger.error("查询虚拟货币失败");
			e.printStackTrace();
		}
		return coinList;
	}

	/**
	 * 查询除人民币外的所有货币的编号及名字
	 * 
	 * @return
	 */
	public List<CoinDto> queryCoinNoAndCoinNameOfCoinExceptRMB() {
		List<CoinDto> coinList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM t_inesv_coin_type where coin_no not in(0)";
			coinList = queryRunner.query(sql, new BeanListHandler<>(CoinDto.class));
		} catch (SQLException e) {
			logger.error("查询虚拟货币失败");
			e.printStackTrace();
		}
		return coinList;
	}

	/**
	 * 根据coin_no查询货币
	 * 
	 * @return
	 */
	public List<CoinDto> queryCoinTypeByCoinNo(Integer coinNo) {
		List<CoinDto> coinList = new ArrayList<>();
		try {
			String sql = "SELECT * from t_inesv_coin_type where coin_no=?";
			coinList = queryRunner.query(sql, new BeanListHandler<>(CoinDto.class), coinNo);
		} catch (SQLException e) {
			logger.error("查询虚拟货币类型失败");
			e.printStackTrace();
		}
		return coinList;
	}

	/**
	 * 根据币种查询货币是否存在
	 * 
	 * @return
	 */
	public List<SubCoreDto> queryCoinByCoinType(Integer coin) {
		List<SubCoreDto> list = new ArrayList<>();
		Object params[] = { coin };
		try {
			String sql = "SELECT * FROM t_inesv_sub_core where  coin_type = ?";
			list = queryRunner.query(sql, new BeanListHandler<SubCoreDto>(SubCoreDto.class), params);
		} catch (SQLException e) {
			logger.error("查询虚拟货币失败");
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 根据没有交易记录的货币
	 * 
	 * @return
	 */
	public List<SubCoreDto> queryNoCoin(List<DayMarketDto> coinNo) {
		List<SubCoreDto> list = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM t_inesv_sub_core where coin_type !=0 and coin_type not in (");
		for (int i = 0; i < coinNo.size(); i++) {
			sql.append(coinNo.get(i).getCoin_type()).append(",");
		}
		sql.delete(sql.length() - 1, sql.length()).append(")");
		try {
			list = queryRunner.query(sql.toString(), new BeanListHandler<SubCoreDto>(SubCoreDto.class));
		} catch (SQLException e) {
			logger.error("查询虚拟货币失败");
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 根据币种查询买入手续费比例
	 */
	public CoinDto queryBuyPoundatge(Long coin_no) {
		CoinDto coinDto = new CoinDto();
		String sql = "select * from t_inesv_coin_type where coin_no = ?";
		try {
			coinDto = queryRunner.query(sql, new BeanHandler<CoinDto>(CoinDto.class), coin_no);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return coinDto;
	}

	/**
	 * 根据币种查询买入手续费比例
	 */
	public CoinDto querySellPoundatge(Long coin_no) {
		// BigDecimal sell_poundatge = null;
		CoinDto coinDto = new CoinDto();
		String sql = "select * from t_inesv_coin_type where coin_no = ?";
		try {
			coinDto = queryRunner.query(sql, new BeanHandler<CoinDto>(CoinDto.class), coin_no);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return coinDto;
	}

	public BigDecimal querySellPoundatge1(Long coin_no) {
		// BigDecimal sell_poundatge = null;
		// CoinDto coinDto = new CoinDto();
		BigDecimal sell_poundatge = null;
		String sql = "select sell_poundatge from t_inesv_coin_type where coin_no = ?";
		try {
			sell_poundatge = (BigDecimal) queryRunner.query(sql, new ScalarHandler<BigDecimal>(), coin_no);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sell_poundatge;
	}

	public BigDecimal queryBuyPoundatge1(Long coin_no) {
		// BigDecimal sell_poundatge = null;
		// CoinDto coinDto = new CoinDto();
		BigDecimal buy_poundatge = null;
		String sql = "select buy_poundatge from t_inesv_coin_type where coin_no = ?";
		try {
			buy_poundatge = (BigDecimal) queryRunner.query(sql, new ScalarHandler<BigDecimal>(), coin_no);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buy_poundatge;
	}

	public List<CoinDto> getCoinTypes() {
		String sql = "select coin_no,coin_name from t_inesv_coin_type";
		try {
			List<CoinDto> dtos = queryRunner.query(sql, new BeanListHandler<CoinDto>(CoinDto.class));
			return dtos;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询所有货币信息
	 * 
	 * @return
	 */
	public List<CoinDto> queryAllCoinByNoRMB() {
		List<CoinDto> coinList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM t_inesv_coin_type WHERE coin_no != 0";
			coinList = queryRunner.query(sql, new BeanListHandler<>(CoinDto.class));
		} catch (SQLException e) {
			logger.error("查询虚拟货币失败");
			e.printStackTrace();
		}
		return coinList;
	}
	
	/**
	 * 查询所有货币信息
	 * 
	 * @return
	 */
	public CoinDto queryCoinByCoinNo(Integer coinNo) {
		CoinDto dto = new CoinDto();
		try {
			String sql = "select buy_poundatge,sell_poundatge from t_inesv_coin_type where coin_no = ?";
			dto = queryRunner.query(sql, new BeanHandler<>(CoinDto.class),coinNo);
		} catch (SQLException e) {
			logger.error("查询虚拟货币失败");
			e.printStackTrace();
		}
		return dto;
	}
}
