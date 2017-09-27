package com.inesv.digiccy.persistence.operation;

import com.inesv.digiccy.dto.RecSubProfitDto;
import com.inesv.digiccy.dto.SubCoreDto;
import com.inesv.digiccy.dto.SubRecordDto;
import com.inesv.digiccy.dto.ThreeGenerationOfRecommandDto;
import com.inesv.digiccy.dto.UserBalanceDto;
import com.inesv.digiccy.persistence.finance.RecSubProfitPersistence;
import com.inesv.digiccy.util.ObjectChangeUtil;
import com.inesv.digiccy.util.TableName;

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

/**
 * Created by huguokai on 2016/11/9 0009.
 */
@Transactional
@Component
public class SubCorePer {

    private static Logger logger = LoggerFactory.getLogger(SubCorePer.class);

    @Autowired
    private QueryRunner queryRunner;
    @Autowired 
    private RecSubProfitPersistence recSubProfitPersistence; 

    /**
     * create by huguokai date:2016年11月14日15:35:38
     * 根据货币种类修改认购中心货币数量
     * @param coinType
     * @param already
     * @throws Exception
     */
    public void editSubCoreByCoinType(Integer coinType, BigDecimal already) throws Exception {
        String sql = "update t_inesv_sub_core set already = ? where coin_type = ?";
        Object params[] = {already,coinType};
        queryRunner.update(sql,params);
    }
    
    /**
     * create by huguokai date:2016年11月14日15:35:38
     * 添加个人购买虚拟货币记录
     * @param subRecordDto
     * @throws Exception
     */
    public void addSubRecordByUserNo(SubRecordDto subRecordDto) throws Exception {
        String sql = new ObjectChangeUtil<SubRecordDto>().objectToSql(subRecordDto, TableName.T_INESV_SUB_RECORD);
        Object params[] = new ObjectChangeUtil<SubRecordDto>().objectToArray(subRecordDto);
        queryRunner.update(sql,params);
    }
    
    /**
     * create by huguokai date:2016年11月14日15:35:38
     * 添加个人购买虚拟货币记录
     * @param subRecordDto
     * @throws Exception
     */
    public void addSubRecordDto(SubRecordDto subRecordDto) throws Exception {
    	String sql = "insert into t_inesv_sub_record(user_no,coin_no,sub_name,sub_price,sub_num,sum_price,thaw_num,thaw_time,sur_frozen,state,date,thaw_sum,attr1,attr2) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object params[] = {subRecordDto.getUser_no(),subRecordDto.getCoin_no(),subRecordDto.getSub_name(),subRecordDto.getSub_price(),subRecordDto.getSub_num(),subRecordDto.getSum_price(),subRecordDto.getThaw_num(),subRecordDto.getThaw_time(),subRecordDto.getSur_frozen(),subRecordDto.getState(),subRecordDto.getDate(),subRecordDto.getThaw_sum(),subRecordDto.getAttr1(),subRecordDto.getAttr2()};
        queryRunner.update(sql,params);
    }

    /**
     * create by huguokai date:2016年11月14日15:35:38
     * 修改个人资产
     * @param userNo
     * @param coinType
     * @param unableCoin
     * @throws Exception
     */
    public void editUserBalance(Integer userNo,Integer coinType,BigDecimal unableCoin) throws Exception {
        String sql = "update t_inesv_user_balance set unable_coin = ? where user_no = ? and coin_type = ?";
        Object params[] = {unableCoin,userNo,coinType};
        queryRunner.update(sql,params);
    }

    /**
     * create by huguokai date:2016年11月14日15:35:38
     * 修改个人资产
     * @param userNo
     * @param coinType
     * @param unableCoin
     * @throws Exception
     */
    public void editCoinNum(Integer userNo,Integer coinType,BigDecimal unableCoin) throws Exception {
        String sql = "update t_inesv_user_balance set enable_coin = ? where user_no = ? and coin_type = ?";
        Object params[] = {unableCoin,userNo,coinType};
        queryRunner.update(sql,params);
    }

    /**
     * create by huguokai date:2016年11月18日14:27:31
     * 解冻修改认购记录
     * @param id
     * @param thawNum
     * @param thawTime
     * @param surFrozen
     * @param thawSum
     * @throws Exception
     */
    public void editUserRecordInfo(long id, Integer thawNum, Date thawTime, BigDecimal surFrozen, BigDecimal thawSum, Integer state) throws Exception {
        String sql = "update t_inesv_sub_record set thaw_num = ? , thaw_time = ? , sur_frozen =  ? , thaw_sum = ? ,state = ? where id = ?";
        Object params[] = {thawNum,thawTime,surFrozen,thawSum,state,id};
        queryRunner.update(sql,params);
    }

    /**
     * create by huguokai date:2016年11月18日15:32:33
     * 解冻修改个人资产
     * @param userNo
     * @param coinType
     * @param enableCoin
     * @param unableCoin
     * @throws Exception
     */
    public void editUserBalanceInfo(Integer userNo,Integer coinType,BigDecimal enableCoin,BigDecimal unableCoin) throws Exception {
        String sql = "update t_inesv_user_balance set enable_coin = ? , unable_coin = ? where user_no = ? and coin_type = ?";
        Object params[] = {enableCoin,unableCoin,userNo,coinType};
        queryRunner.update(sql,params);
    }

    public void addSubCore(SubCoreDto subCoreDto) throws Exception{
//        String sql = new ObjectChangeUtil<SubCoreDto>().objectToSql(subCoreDto, TableName.T_INESV_SUB_CORE);
//        Object params[] = new ObjectChangeUtil<SubCoreDto>().objectToArray(subCoreDto);
        String sql = "insert into t_inesv_sub_core(sub_name,coin_type,price,num,limit_buy,thaw_num,cycle,status,date,photo)" +
                "values(?,?,?,?,?,?,?,?,?,?)";
        Object params[] = {subCoreDto.getSub_name(),subCoreDto.getCoin_type(),subCoreDto.getPrice(),subCoreDto.getNum(),
                subCoreDto.getLimit_buy(),subCoreDto.getThaw_num(),subCoreDto.getCycle(),
                subCoreDto.getStatus(),new Date(),subCoreDto.getPhoto()};
        queryRunner.update(sql,params);
    }

    public void updateSubCore(SubCoreDto subCoreDto) throws Exception{
        String sql = "UPDATE t_inesv_sub_core SET sub_name = ?,coin_type=?,price=?,num=?,limit_buy=?," +
                "thaw_num=?,cycle=?,status=? where id = ?";
        Object params[] = {subCoreDto.getSub_name(),subCoreDto.getCoin_type(),subCoreDto.getPrice(),subCoreDto.getNum(),
                            subCoreDto.getLimit_buy(),subCoreDto.getThaw_num(),subCoreDto.getCycle(),
                            subCoreDto.getStatus(),subCoreDto.getId()};
        queryRunner.update(sql,params);
    }

    @Transactional
    public void deleteSubCore(SubCoreDto subCoreDto) throws Exception{
        String sql = "Delete from t_inesv_sub_core where id = ?";
        Object params[] = {subCoreDto.getId()};
        queryRunner.update(sql,params);
    }

    /**
     * 根据用户编号及币种编号获取认购记录
     * @param user_no
     * @param coin_no
     * @return
     */
	public List<SubRecordDto> getRecordByUserNoAndCoinType(Integer user_no, Integer coin_no) {
		String sql = "select * from t_inesv_sub_record where user_no=? and coin_no = ?";
        Object params[] = {user_no,coin_no};
        List<SubRecordDto> list = null;
        try {
            list = queryRunner.query(sql,new BeanListHandler<SubRecordDto>(SubRecordDto.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("根据货币种类查询认购中心失败");
        }
        return list;
	}
    
	/**
	 * 认购返佣
	 * @param subRecordDto
	 * @throws Exception
	 */
	@Transactional(rollbackFor={Exception.class, RuntimeException.class})
	public void addSubRecordTranster(SubRecordDto subRecordDto) throws Exception {
		//判断是不是第一次认购
        //是第一次认购就要返佣 
        List<SubRecordDto> subRecord=getRecordByUserNoAndCoinType(subRecordDto.getUser_no(),subRecordDto.getCoin_no());
        if(subRecord==null || subRecord.size()<=0){
        	//判断该用户是否有推荐人,获取用户的三代推荐人编号（没有以0代替）
        	String sql="select @num1:=IFNULL((select user_no from t_inesv_rec where rec_user=?),0) as 'first',"
    				+ "@num2:=IFNULL((select user_no from t_inesv_rec where rec_user = @num1),0) as 'second',"
    				+ "@num3:=IFNULL((select user_no from t_inesv_rec where rec_user = @num2),0) as 'third'";
        	ThreeGenerationOfRecommandDto threeGenerationOfRecommandInfo=queryRunner.query(sql,new BeanHandler<ThreeGenerationOfRecommandDto>(ThreeGenerationOfRecommandDto.class),subRecordDto.getUser_no());
        	//判断第一代推荐人是否为空（为空则为0）
        	if(threeGenerationOfRecommandInfo.getFirst()!=0){
        		// TODO 获取每一代推荐人的返佣比例
        		BigDecimal firstRate=new BigDecimal(0.003);
        		BigDecimal secondRate=new BigDecimal(0.002);
        		BigDecimal thirdRate=new BigDecimal(0.001);
                //得到认购用户的推荐人的个人资产（该货币）
        		UserBalanceDto firstUserBalance = getUserBalance(threeGenerationOfRecommandInfo.getFirst(),subRecordDto.getCoin_no());
                //修改第一代推荐人的货币资产
                editCoinNum(firstUserBalance.getUser_no(),firstUserBalance.getCoin_type(),firstUserBalance.getEnable_coin().add(subRecordDto.getSub_num().multiply(firstRate)));
                //添加返佣记录
                RecSubProfitDto firstRecSubProfitDto=new RecSubProfitDto(firstUserBalance.getUser_no(),subRecordDto.getUser_no(),subRecordDto.getCoin_no(),subRecordDto.getSub_num().multiply(firstRate),subRecordDto.getDate());
                recSubProfitPersistence.addRecProfit(firstRecSubProfitDto);
                //判断第二代推荐人是否为空（为空则为0）
        		if(threeGenerationOfRecommandInfo.getSecond()!=0){
        			//得到认购用户的推荐人的个人资产（该货币）
                    UserBalanceDto secondUserBalance = getUserBalance(threeGenerationOfRecommandInfo.getSecond(),subRecordDto.getCoin_no());
                    //修改第二代推荐人的货币资产
                    editCoinNum(secondUserBalance.getUser_no(),secondUserBalance.getCoin_type(),secondUserBalance.getEnable_coin().add(subRecordDto.getSub_num().multiply(secondRate)));
                    //添加返佣记录
                    RecSubProfitDto secondRecSubProfitDto=new RecSubProfitDto(secondUserBalance.getUser_no(),subRecordDto.getUser_no(),subRecordDto.getCoin_no(),subRecordDto.getSub_num().multiply(secondRate),subRecordDto.getDate());
                    recSubProfitPersistence.addRecProfit(secondRecSubProfitDto);
                    //判断第三代推荐人是否为空（为空则为0）
        			if(threeGenerationOfRecommandInfo.getThird()!=0){
        				//得到认购用户的推荐人的个人资产（该货币）
                        UserBalanceDto thirdUserBalance = getUserBalance(threeGenerationOfRecommandInfo.getThird(),subRecordDto.getCoin_no());
                        //修改第三代推荐人的货币资产
                        editCoinNum(thirdUserBalance.getUser_no(),thirdUserBalance.getCoin_type(),thirdUserBalance.getEnable_coin().add(subRecordDto.getSub_num().multiply(thirdRate)));
                        //根据货币种类修改认购中心货币数量
                        editSubCoreByCoinType(subRecordDto.getCoin_no(),getSubRecordInfoByCoinType(subRecordDto.getCoin_no()).getAlready().add(subRecordDto.getSub_num().multiply(firstRate.add(secondRate).add(thirdRate))));
                        //添加返佣记录
                        RecSubProfitDto thirdRecSubProfitDto=new RecSubProfitDto(thirdUserBalance.getUser_no(),subRecordDto.getUser_no(),subRecordDto.getCoin_no(),subRecordDto.getSub_num().multiply(thirdRate),subRecordDto.getDate());
                        recSubProfitPersistence.addRecProfit(thirdRecSubProfitDto);
        			}else{
                		//根据货币种类修改认购中心货币数量
                        editSubCoreByCoinType(subRecordDto.getCoin_no(),getSubRecordInfoByCoinType(subRecordDto.getCoin_no()).getAlready().add(subRecordDto.getSub_num().multiply(firstRate.add(secondRate))));
                		opSubRecord(subRecordDto);
                	}
            	}else{
            		//根据货币种类修改认购中心货币数量
                    editSubCoreByCoinType(subRecordDto.getCoin_no(),getSubRecordInfoByCoinType(subRecordDto.getCoin_no()).getAlready().add(subRecordDto.getSub_num().multiply(firstRate)));
            		opSubRecord(subRecordDto);
            	}
        	}else{
        		opSubRecord( subRecordDto);
        	}
        }else{
        	opSubRecord(subRecordDto);
        }
	}

	public void opSubRecord(SubRecordDto subRecordDto) throws Exception {
		//添加认购记录
		addSubRecordDto(subRecordDto);
        //获取认购货币类型
        SubCoreDto subCoreDto = getSubRecordInfoByCoinType(subRecordDto.getCoin_no());
        //根据货币种类修改认购中心货币数量
        editSubCoreByCoinType(subRecordDto.getCoin_no(),subCoreDto.getAlready().add(subRecordDto.getSub_num()));
        //得到认购用户的个人资产（该货币以及人民币资产）
        UserBalanceDto userBalanceDto =getUserBalance(subRecordDto.getUser_no(),subCoreDto.getCoin_type());
        UserBalanceDto ubd =getUserBalance(subRecordDto.getUser_no(),0);
        if(subCoreDto.getThaw_num() == 0){
            editCoinNum(subRecordDto.getUser_no(),subRecordDto.getCoin_no(),userBalanceDto.getEnable_coin().add(subRecordDto.getSub_num()));
        }else{
            editUserBalance(subRecordDto.getUser_no(),subCoreDto.getCoin_type(),userBalanceDto.getUnable_coin().add(subRecordDto.getSub_num()));
        }
        editCoinNum(subRecordDto.getUser_no(),0,ubd.getEnable_coin().subtract(subRecordDto.getSum_price()));
	}
	
	
	/**
     * Create by huguokai date:2016年11月8日15:24:17
     * @param userNo
     * @return UserBalanceDto
     * @throws SQLException
     */
    public UserBalanceDto getUserBalance(Integer userNo,Integer coinType){
        String sql = "select * from t_inesv_user_balance where user_no = ? and coin_type = ?";
        Object params[] = {userNo,coinType};
        UserBalanceDto userBalanceDto = null;
        try {
            userBalanceDto = queryRunner.query(sql,new BeanHandler<UserBalanceDto>(UserBalanceDto.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("根据用户编号查询用户资产失败");
        }
        return userBalanceDto;
    }
    
    /**
     * Create by huguokai date:2016年11月8日14:17:52
     * @param coinType
     * @return list
     * @throws SQLException
     */
    public SubCoreDto getSubRecordInfoByCoinType(Integer coinType){
        String sql = "select * from t_inesv_sub_core where coin_type = ? order  by date desc limit 0,1";
        Object params[] = {coinType};
        SubCoreDto list = null;
        try {
            list = queryRunner.query(sql,new BeanHandler<SubCoreDto>(SubCoreDto.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("根据货币种类查询认购中心失败");
        }
        return list;
    }

}
