package com.inesv.digiccy.persistence.trade;

import com.inesv.digiccy.dto.CoinDto;
import com.inesv.digiccy.dto.CoinLevelProportionDto;
import com.inesv.digiccy.dto.DayMarketDto;
import com.inesv.digiccy.dto.DealDetailDto;
import com.inesv.digiccy.dto.EntrustDto;
import com.inesv.digiccy.dto.UserBalanceDto;
import com.inesv.digiccy.dto.UserRelations;
import com.inesv.digiccy.persistence.bonus.BonusOperation;
import com.inesv.digiccy.persistence.plan.PlanOperation;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 交易中心 委托记录 增删改
 * @author qing
 *
 */
@Transactional
@Component
public class TradePersistence {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TradePersistence.class);
	
	@Autowired
	QueryRunner queryRunner;
	
	@Autowired
	PlanOperation operation;
	
	@Autowired
	BonusOperation bonusOperation;
	
	@Autowired
	TradeAutualPersistence tradeAutualPersistence;

	private Lock lock = new ReentrantLock();// 锁对象 
	/**
	 * 新增 委托记录
	 * 
	 * @return
	 */
	public void addEntrust(EntrustDto entrust) throws SQLException {
		String insertEntrust = "insert into t_inesv_entrust(user_no,entrust_coin,entrust_type,entrust_price,entrust_num,deal_num,piundatge,state,date,attr1) values(?,?,?,?,?,?,?,?,?,?)";
		Object insertparams[] = {entrust.getUser_no(),entrust.getEntrust_coin(),entrust.getEntrust_type(),entrust.getEntrust_price(),entrust.getEntrust_num(),entrust.getDeal_num(),entrust.getPiundatge(),entrust.getState(),entrust.getDate(),entrust.getAttr1()};
		queryRunner.update(insertEntrust, insertparams);
	}
	/**
	 * 修改 委托记录 状态
	 * 
	 * @return
	 */
	public void updateEntrustState(EntrustDto entrustModel) throws Exception {
		String updateEntrust = "UPDATE t_inesv_entrust SET state = ? WHERE id = ?";
		Object params[] = {entrustModel.getState(),entrustModel.getId()};
		queryRunner.update(updateEntrust, params);

	}

	/**
	 * 删除 委托记录
	 * 
	 * @return
	 */
	public void deleteEntrust(EntrustDto entrustModel) throws Exception {
		String deleteEntrust = "DELETE FROM t_inesv_entrust WHERE id = ? ";
		Object params[] = { entrustModel.getId() };
		queryRunner.update(deleteEntrust, params);
	}
	
	/**
	 * 添加委托记录并修改用户资产
	 * @param entrust
	 * @param xnb 虚拟币资产
	 * @param rmb 人民币资产
	 * @throws SQLException
	 */
	@Transactional(rollbackFor={Exception.class, RuntimeException.class})
	public void addEntrustActual(EntrustDto entrust) throws Exception{
		//虚拟币	
        String queryXnbSql = "select * from t_inesv_user_balance where user_no=? and coin_type=? for update";
        Object xnbParams[] = {entrust.getUser_no().toString(), entrust.getEntrust_coin().toString()};
        UserBalanceDto xnbBalanceInfo=queryRunner.query(queryXnbSql,new BeanHandler<UserBalanceDto>(UserBalanceDto.class),xnbParams);
		//人民币
        String queryRmbSql = "select * from t_inesv_user_balance where user_no=? and coin_type=? for update";
        Object rmbParams[] = {entrust.getUser_no().toString(), entrust.getConvert_coin().toString()};
        UserBalanceDto rmbBalanceInfo=queryRunner.query(queryRmbSql,new BeanHandler<UserBalanceDto>(UserBalanceDto.class),rmbParams);
		if(entrust.getEntrust_type() == 0){//买 
			if(entrust.getConvert_coin() == 0) {
				if(rmbBalanceInfo.getEnable_coin().doubleValue() < (entrust.getEntrust_price().multiply(entrust.getEntrust_num())).doubleValue()) { //人民币余额不足
					int numException = 1/0;
				}
				rmbBalanceInfo.setEnable_coin(rmbBalanceInfo.getEnable_coin().subtract((entrust.getEntrust_price().multiply(entrust.getEntrust_num()))));
				rmbBalanceInfo.setUnable_coin(rmbBalanceInfo.getUnable_coin().add(entrust.getEntrust_price().multiply(entrust.getEntrust_num())));
			}else {
				if(entrust.getConvert_price().doubleValue() * rmbBalanceInfo.getEnable_coin().doubleValue() < (entrust.getEntrust_price().multiply(entrust.getEntrust_num())).doubleValue()) {
					int numException = 1/0;
				}
				double enable_coin = rmbBalanceInfo.getEnable_coin().doubleValue()-(entrust.getEntrust_price().multiply(entrust.getEntrust_num())).doubleValue() / entrust.getConvert_price().doubleValue();
				double unable_coin = rmbBalanceInfo.getUnable_coin().doubleValue()+(entrust.getEntrust_price().multiply(entrust.getEntrust_num())).doubleValue() / entrust.getConvert_price().doubleValue();
				BigDecimal en_bg = new BigDecimal(enable_coin);  
				BigDecimal un_bg = new BigDecimal(unable_coin);  
				rmbBalanceInfo.setEnable_coin(new BigDecimal(en_bg.setScale(6,BigDecimal.ROUND_DOWN).toString()));
				rmbBalanceInfo.setUnable_coin(new BigDecimal(un_bg.setScale(6,BigDecimal.ROUND_DOWN).toString()));
			}
		}
		if(entrust.getEntrust_type() == 1){//卖
			if(xnbBalanceInfo.getEnable_coin().doubleValue() - entrust.getEntrust_num().doubleValue() < 0){//虚拟币余额不足
				int numException = 1/0;
			}
			xnbBalanceInfo.setUnable_coin(xnbBalanceInfo.getUnable_coin().add(entrust.getEntrust_num()));
			xnbBalanceInfo.setEnable_coin(xnbBalanceInfo.getEnable_coin().subtract(entrust.getEntrust_num()));
		}
		//添加委托记录
		String insertEntrust = "insert into t_inesv_entrust(user_no,entrust_coin,convert_coin,convert_price,convert_sum_price,convert_deal_price,entrust_type,entrust_price,entrust_num,deal_num,piundatge,state,date) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object insertParams[] = {entrust.getUser_no(),entrust.getEntrust_coin(),entrust.getConvert_coin(),entrust.getConvert_price(),entrust.getConvert_sum_price(),
				entrust.getConvert_deal_price(),entrust.getEntrust_type(),entrust.getEntrust_price(),entrust.getEntrust_num(),entrust.getDeal_num(),entrust.getPiundatge(),
				entrust.getState(),entrust.getDate()};
		queryRunner.update(insertEntrust, insertParams);
		//修改该用户的虚拟币资产
		String updateUserBalance = "update t_inesv_user_balance set enable_coin=?,unable_coin=?,total_price=? where user_no=? and coin_type=?";
		Object updateParams[] = {xnbBalanceInfo.getEnable_coin(),xnbBalanceInfo.getUnable_coin(),xnbBalanceInfo.getTotal_price(),xnbBalanceInfo.getUser_no(),xnbBalanceInfo.getCoin_type()};
		queryRunner.update(updateUserBalance, updateParams);
		//修改该用户的人民币资产
		String updateUserBalanceRmb = "update t_inesv_user_balance set enable_coin=?,unable_coin=?,total_price=? where user_no=? and coin_type=?";
		Object updateRmbParams[] = {rmbBalanceInfo.getEnable_coin(),rmbBalanceInfo.getUnable_coin(),rmbBalanceInfo.getTotal_price(),rmbBalanceInfo.getUser_no(),rmbBalanceInfo.getCoin_type()};
		queryRunner.update(updateUserBalanceRmb, updateRmbParams);
		//暂时注释调，交易用的代码
		/*lock.lock();// 得到锁  
		try {
			String querySql = "select * from t_inesv_entrust where user_no = ? order by id desc limit 1";
			EntrustDto entrusts = queryRunner.query(querySql,new BeanHandler<EntrustDto>(EntrustDto.class),entrust.getUser_no());
			tradeAutualPersistence.validateTradeCoinActualBySQL(entrusts);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();// 释放锁
		}*/
	}
	/*@Transactional(rollbackFor={Exception.class, RuntimeException.class})
	public void addEntrustActual(final EntrustDto entrust, UserBalanceDto xnb,UserBalanceDto rmb) throws Exception{
		//--------------------------创建委托单号，修改用户资产
		//添加委托记录
		String insertEntrust = "insert into t_inesv_entrust(user_no,entrust_coin,entrust_type,entrust_price,entrust_num,deal_num,piundatge,state,date,attr1) values(?,?,?,?,?,?,?,?,?,?)";
		Object insertParams[] = {entrust.getUser_no(),entrust.getEntrust_coin(),entrust.getEntrust_type(),entrust.getEntrust_price(),entrust.getEntrust_num(),
				entrust.getDeal_num(),entrust.getPiundatge(),entrust.getState(),entrust.getDate(),entrust.getAttr1()};
		queryRunner.update(insertEntrust, insertParams);
		//修改该用户的虚拟币资产
		String updateUserBalance = "update t_inesv_user_balance set enable_coin=?,unable_coin=?,total_price=? where user_no=? and coin_type=?";
		Object updateParams[] = {xnb.getEnable_coin(),xnb.getUnable_coin(),xnb.getTotal_price(),xnb.getUser_no(),xnb.getCoin_type()};
		queryRunner.update(updateUserBalance, updateParams);
		//修改该用户的人民币资产
		String updateUserBalanceRmb = "update t_inesv_user_balance set enable_coin=?,unable_coin=?,total_price=? where user_no=? and coin_type=?";
		Object updateRmbParams[] = {rmb.getEnable_coin(),rmb.getUnable_coin(),rmb.getTotal_price(),rmb.getUser_no(),rmb.getCoin_type()};
		queryRunner.update(updateUserBalanceRmb, updateRmbParams);
		new Thread(new Runnable() {
			@Override
			public void run() {
				lock.lock();// 得到锁  
				try {
					String querySql = "select * from t_inesv_entrust where user_no = ? order by id desc limit 1 for update";
					EntrustDto entrusts = queryRunner.query(querySql,new BeanHandler<EntrustDto>(EntrustDto.class),entrust.getUser_no());
					tradeAutualPersistence.validateTradeCoinActualBySQL(entrusts);//进入实时买卖
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					lock.unlock();// 释放锁
				}
			}
		}).start();
	}*/
	
	/**
	 * 删除（撤销）委托记录并修改用户资产
	 * @param entrust
	 * @param xnb
	 * @param rmb
	 * @throws SQLException
	 */
	@Transactional(rollbackFor={Exception.class, RuntimeException.class})
	public void updateEntrust(EntrustDto entrust, UserBalanceDto xnb,
			UserBalanceDto rmb) throws SQLException{
		//删除（撤销）该委托记录
		String updateEntrust = "UPDATE t_inesv_entrust SET state = 2 WHERE id = ?";
		Object params[] = {entrust.getId()};
		queryRunner.update(updateEntrust, params);
		//修改该用户的虚拟币资产
		String updateUserBalance = "update t_inesv_user_balance set enable_coin=?,unable_coin=?,total_price=? where user_no=? and coin_type=?";
		Object updateParams[] = {xnb.getEnable_coin(),xnb.getUnable_coin(),xnb.getTotal_price(),xnb.getUser_no(),xnb.getCoin_type()};
		queryRunner.update(updateUserBalance, updateParams);
		//修改该用户的人民币资产
		String updateUserBalanceRmb = "update t_inesv_user_balance set enable_coin=?,unable_coin=?,total_price=? where user_no=? and coin_type=?";
		Object updateRmbParams[] = {rmb.getEnable_coin(),rmb.getUnable_coin(),rmb.getTotal_price(),rmb.getUser_no(),rmb.getCoin_type()};
		queryRunner.update(updateUserBalanceRmb, updateRmbParams);
	}

	/**
	 * 确认委托
	 * @throws SQLException
     */
	@Transactional(rollbackFor={Exception.class, RuntimeException.class})
	public void confirmEntrust(EntrustDto entrust) throws SQLException{
		//修改委托记录状态
		String updateEntrust = "UPDATE t_inesv_entrust SET state = 1 WHERE id = ?";
		queryRunner.update(updateEntrust,entrust.getId());
		String queryUserBalance = "SELECT * FROM t_inesv_user_balance WHERE coin_type = ? and user_no =?";
		String queryUserRmb = "SELECT * FROM t_inesv_user_balance WHERE coin_type = 0 and user_no =?";
		Object userBalanceParam[] = {entrust.getEntrust_coin(),entrust.getUser_no()};
		UserBalanceDto userBalance = queryRunner.query(queryUserBalance,new BeanHandler<>(UserBalanceDto.class),userBalanceParam);
		UserBalanceDto userRmb = queryRunner.query(queryUserRmb,new BeanHandler<>(UserBalanceDto.class),entrust.getUser_no());
		//买
		if(entrust.getEntrust_type() == 0){
			BigDecimal rmbUnable = userRmb.getUnable_coin().subtract(entrust.getEntrust_price().multiply(entrust.getEntrust_num())).add(entrust.getPiundatge());
			BigDecimal xnbEnable = userBalance.getUnable_coin().add(entrust.getEntrust_num());
			BigDecimal xnbUnable = userBalance.getUnable_coin().subtract(entrust.getEntrust_num());
			String updateUserXnb = "UPDATE t_inesv_user_balance SET enable_coin = ? , unable_coin = ? WHERE user_no = ? and coin_type = ?";
			String updateUserRmb = "UPDATE t_inesv_user_balance SET unable_coin = ? WHERE user_no = ? and coin_type = 0";
			Object xnbParam[] = {xnbEnable,xnbUnable,entrust.getUser_no(),entrust.getEntrust_coin()};
			Object rmbParam[] = {rmbUnable,entrust.getUser_no(),entrust.getEntrust_coin()};
			queryRunner.update(updateUserXnb,xnbParam);
			queryRunner.update(updateUserRmb,rmbParam);
		}
		//卖
		if(entrust.getEntrust_type() == 1){
			BigDecimal rmbEnable = userRmb.getUnable_coin().add(entrust.getEntrust_num());
			BigDecimal rmbUnable = userRmb.getUnable_coin().subtract(entrust.getEntrust_price().multiply(entrust.getEntrust_num())).add(entrust.getPiundatge());
			BigDecimal xnbUnable = userBalance.getUnable_coin().subtract(entrust.getEntrust_num());
			String updateUserXnb = "UPDATE t_inesv_user_balance SET enable_coin = ? , unable_coin = ? WHERE user_no = ? and coin_type = 0";
			String updateUserRmb = "UPDATE t_inesv_user_balance SET unable_coin = ? WHERE user_no = ? and coin_type = ?";
			Object xnbParam[] = {xnbUnable,entrust.getUser_no(),entrust.getEntrust_coin()};
			Object rmbParam[] = {rmbEnable,rmbUnable,entrust.getUser_no(),entrust.getEntrust_coin()};
			queryRunner.update(updateUserXnb,xnbParam);
			queryRunner.update(updateUserRmb,rmbParam);
		}
		//判断是否是第一次交易
		String tradeFristsql="select * from t_inesv_deal_detail where user_no=?";
		List<DealDetailDto> deatList= queryRunner.query(tradeFristsql,new BeanListHandler<DealDetailDto>(DealDetailDto.class),entrust.getUser_no());
		if(deatList==null || deatList.size()<=0){
			String updateProfit="UPDATE t_inesv_rec_profit SET deal=1 WHERE user_no = ? ";
			queryRunner.update(updateProfit,entrust.getUser_no());
		}
		
		String insertDeal = "INSERT INTO t_inesv_deal_detail(user_no,coin_no,deal_type,deal_price,deal_num,sum_price,poundage,date) VALUES(?,?,?,?,?,?,?)";
		Object dealParam[] = {entrust.getUser_no(),entrust.getEntrust_coin(),entrust.getEntrust_type(),
				entrust.getEntrust_price(),entrust.getEntrust_num(),entrust.getDeal_num(),
				entrust.getDeal_num().multiply(entrust.getEntrust_price()),entrust.getPiundatge(),new Date()};
		queryRunner.update(insertDeal,dealParam);
	}
	
	/**
	 * 用户资产
	 * @param user_no
	 * @param entrust_coin
	 * @return
	 * @throws SQLException
	 */
	private UserBalanceDto queryUserBalanceInfoByUserNoAndCoinType(Integer user_no, Integer entrust_coin) throws SQLException {
        String querySql = "select * from t_inesv_user_balance where user_no=? and coin_type=? for update";
        Object params[] = {user_no,entrust_coin};
        UserBalanceDto userBalanceInfo=queryRunner.query(querySql,new BeanHandler<UserBalanceDto>(UserBalanceDto.class),params);
		return userBalanceInfo;
	}
	
	public CoinLevelProportionDto queryByCoinNo(Long coin_no) throws Exception{
		String sql = "select * from t_coin_level_proportion where coin_no = ?";
		CoinLevelProportionDto coinLevelProportionDto = new CoinLevelProportionDto();
			coinLevelProportionDto = queryRunner.query(sql, new BeanHandler<CoinLevelProportionDto>(CoinLevelProportionDto.class),coin_no);
		return coinLevelProportionDto;
	}
	
	public UserRelations queryRelationByUserNo(Long user_no) throws Exception{
		String sql = "select * from t_user_relations where user_no = ?";
		UserRelations Relations = new UserRelations();
			Relations = queryRunner.query(sql, new BeanHandler<UserRelations>(UserRelations.class),user_no);
		return Relations;
	}
	
	/**
	 * 根据委托单号查询记录
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public EntrustDto queryEntrustById(Long id) throws SQLException{
		String sql="select * from t_inesv_entrust where id = ? for update";
		Object params[] = {id};
		EntrustDto dto= queryRunner.query(sql,new BeanHandler<EntrustDto>(EntrustDto.class),params);
		return dto;
	}
}
