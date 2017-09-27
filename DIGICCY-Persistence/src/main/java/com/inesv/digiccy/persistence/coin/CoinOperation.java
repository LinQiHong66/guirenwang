package com.inesv.digiccy.persistence.coin;

import com.inesv.digiccy.dto.CoinDto;
import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.util.ObjectChangeUtil;
import com.inesv.digiccy.util.TableName;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 虚拟货币持久层，事务处理层 Created by JimJim on 2016/11/17 0017.
 */
@Component
public class CoinOperation {

	private static Logger logger = LoggerFactory.getLogger(CoinOperation.class);

	@Autowired
	QueryRunner queryRunner;

	/**
	 * 新增货币
	 * 
	 * @param coinDto
	 * @throws SQLException
	 */
	@Transactional(rollbackFor = { Exception.class })
	public void addCoin(CoinDto coinDto) throws SQLException {
		String sql = new ObjectChangeUtil<CoinDto>().objectToSql(coinDto, TableName.T_INESV_COIN_TYPE);
		Object params[] = new ObjectChangeUtil<CoinDto>().objectToArray(coinDto);
		String sql1 = "select * from  t_inesv_user";
		try {
			//添加新币种
			Map<Long, Map<String, Object>> key = queryRunner.insert(sql, new KeyedHandler<Long>(), params);

			Object kid = key.keySet().toArray()[0];
			String querySql = "select * from t_inesv_coin_type where id=?";
			Object[] queryParam = {kid};
			CoinDto coin = queryRunner.query(querySql, new BeanHandler<>(CoinDto.class), queryParam);
			System.out.println(coin);
			//查询所有用户
			List<InesvUserDto> dtos = queryRunner.query(sql1, new BeanListHandler<InesvUserDto>(InesvUserDto.class));
			for(InesvUserDto dto : dtos){
				String sql2 = "insert into t_inesv_user_balance (user_no, coin_type, enable_coin, unable_coin, total_price, wallet_address, date) values (?,?,?,?,?,?,?)";
				Object params2[] = {dto.getUser_no(), coin.getCoin_no(), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,"",new Date(System.currentTimeMillis())};
				queryRunner.update(sql2,params2);
			}
		} catch (SQLException e) {
			logger.error("新增货币错误");
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 修改货币
	 * 
	 * @param coinDto
	 */
	public void updateCoin(CoinDto coinDto) {
		String sql = "UPDATE t_inesv_coin_type "
				+ "SET coin_name = ?,coin_core = ?,address = ? ,block = ?,buy_poundatge = ?,sell_poundatge = ?,withdraw_poundatge_one=?, withdraw_poundatge_twe=?,withdraw_poundatge_three=?"
				+ "WHERE coin_no = ?";

		Object params[] = { coinDto.getCoin_name(), coinDto.getCoin_core(), coinDto.getAddress(), coinDto.getBlock(),
				coinDto.getBuy_poundatge(), coinDto.getSell_poundatge(), coinDto.getWithdraw_poundatge_one(),
				coinDto.getWithdraw_poundatge_twe(), coinDto.getWithdraw_poundatge_three(), coinDto.getCoin_no() };
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("修改货币错误");
			e.printStackTrace();
		}
	}

	/**
	 * 删除货币
	 * 
	 * @param coin_no
	 */
	public void deleteCoin(Integer coin_no) {
		String sql = "DELETE FROM t_inesv_coin_type WHERE coin_no = ?";
		Object params[] = { coin_no };
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("删除货币错误");
			e.printStackTrace();
		}
	}

	/**
	 * 修改货币状态
	 */
	public void changeState(Long coinId, Integer state) {
		String sql = "UPDATE t_inesv_coin_type " + "SET state = ? " + "WHERE id = ?";
		Object params[] = { state, coinId };
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("修改货币状态错误");
			e.printStackTrace();
		}
	}

	/**
	 * 开启货币投票
	 */
	public void startVote(Long coinId, Integer vote) {
		String sql = "UPDATE t_inesv_coin_type " + "SET vote = ? " + "WHERE id = ?";
		Object params[] = { vote, coinId };
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("开启货币投票");
			e.printStackTrace();
		}
	}

	/**
	 * 修改货币状态
	 */
	public void changeIcon(Long coinId, String icon) {
		String sql = "UPDATE t_inesv_coin_type " + "SET icon = ? " + "WHERE id = ?";
		Object params[] = { icon, coinId };
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("修改货币状态错误");
			e.printStackTrace();
		}
	}

	/**
	 * 修改货币买进手续费
	 */
	public void changeBuyPoundatge(double buy_poundatge, Long id) {
		String sql = "update t_inesv_coin_type set buy_poundatge = ? where id = ?";
		Object params[] = { buy_poundatge, id };
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 修改货币卖出手续费
	 */
	public void changeSellPoundatge(double sell_poundatge, Long id) {
		String sql = "update t_inesv_coin_type set sell_poundatge = ? where id = ?";
		Object params[] = { sell_poundatge, id };
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
