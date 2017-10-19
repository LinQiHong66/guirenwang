package com.inesv.digiccy.persistence.finance;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.inesv.digiccy.dto.FicWithdrawDto;
import com.inesv.digiccy.dto.UserBalanceDto;
import com.inesv.digiccy.dto.WalletLinkDto;
import com.inesv.digiccy.wallet.BitcoinAPI;
import com.inesv.digiccy.wallet.EthcoinAPI;

/**
 * Created by Administrator on 2016/12/6 0006.
 */
@Component
public class FicWithdrawPersistence {

    private static Logger logger = LoggerFactory.getLogger(FicWithdrawPersistence.class);

    @Autowired
    QueryRunner           queryRunner;


    /**
     * 虚拟币提现审核-事务处理
     * @throws SQLException **************
     */
    @Transactional(rollbackFor = { Exception.class, RuntimeException.class })
    public void ficWithTansac(BigDecimal actual_price, BigDecimal unable_coin, BigDecimal total_price, Integer user_no, Integer coin_no, BigDecimal poundage, Integer id, BigDecimal truenum,
            String address) throws Exception {

        //修改提现记录的状态
        String sql = "UPDATE t_inesv_fic_withdraw SET actual_price=?,sate=? WHERE id = ?";
        Object parmas[] = { actual_price, 1, id };
        queryRunner.update(sql, parmas);

        //修改用户资产-
        String sql2 = "UPDATE t_inesv_user_balance SET  unable_coin=?,total_price = ? WHERE user_no = ? and coin_type = ?";
        Object parmas2[] = { unable_coin, total_price, user_no, coin_no };
        queryRunner.update(sql2, parmas2);

        //添加手续费记录
        String sql3 = "INSERT INTO t_inesv_poundage(user_no,optype,type,money,date,attr1) VALUES(?,?,?,?,?,?)";
        Object parmas3[] = { user_no, 3, coin_no, poundage, new Date(), coin_no };
        queryRunner.update(sql3, parmas3);

        //转币
        if (coin_no == 20 || coin_no == 40) //以太坊|以太经典
        {
            EthcoinAPI eth = getETHcoinAPI(coin_no);
            // 转换成最小单位以太坊
            BigDecimal wei = new BigDecimal("1000000000000000000");
            BigDecimal truenumZXDW = truenum.multiply(wei);
            String reslut = eth.sendTransaction(eth.getMainaccount(), address, "0x" + Long.toHexString(truenumZXDW.longValue())); // 往钱包转账
            if (reslut == null || reslut == "") {
                int i = 1 / 0;
            }
        } else {
            //其他币
            BitcoinAPI bitcoinAPI = getBitcoinAPI(coin_no);

            bitcoinAPI.closewallet();// 关闭钱包
            bitcoinAPI.openwallet();// 打开钱包

            Double trNum = truenum.doubleValue();
            String reslut = bitcoinAPI.sendToAddress(address, trNum);// 往钱包转账

            bitcoinAPI.closewallet();// 关闭钱包
            if (reslut == null || reslut.equals("")) {
                int i = 1 / 0;
            }
        }
    }

    /**
     * 获取钱包接口
     */
    public BitcoinAPI getBitcoinAPI(int coinType) {
        WalletLinkDto walletLinkDto = WalletLinkInfo(coinType);
        String host = walletLinkDto.getHost();
        String post = walletLinkDto.getPost();
        String walletName = walletLinkDto.getWallet_name();
        String walletPwd = walletLinkDto.getWallet_pwd();
        String lockPwd = walletLinkDto.getWallet_lockpwd();
        return new BitcoinAPI(walletName, walletPwd, host, post, lockPwd);
    }

    /**
     * 获取钱包接口--以太坊
     */
    public EthcoinAPI getETHcoinAPI(int coinType) {
        WalletLinkDto walletLinkDto = WalletLinkInfo(coinType);
        String host = walletLinkDto.getHost();
        String post = walletLinkDto.getPost();
        String walletName = walletLinkDto.getWallet_name();
        String walletPwd = walletLinkDto.getWallet_pwd();
        String lockPwd = walletLinkDto.getWallet_lockpwd();
        return new EthcoinAPI(walletName, walletPwd, host, post, lockPwd);
    }

    /** 查询币种钱包链接信息 */
    public WalletLinkDto WalletLinkInfo(int coinType) {
        WalletLinkDto walletLinkDto = null;
        String sql = "select * from t_inesv_wallet_link where coin_no = ?";
        Object parmas[] = { coinType };
        try {
            walletLinkDto = (WalletLinkDto) queryRunner.query(sql, new BeanHandler(WalletLinkDto.class), parmas);
            return walletLinkDto;
        } catch (SQLException e) {
            logger.error("查询钱包链接信息失败");
            e.printStackTrace();
        }

        return walletLinkDto;
    }

    /**
     * 虚拟币提现审核
     */
    public int updateStateWithdrawInfo(FicWithdrawDto ficWithdrawDto) {
        String sql = "UPDATE t_inesv_fic_withdraw SET actual_price=?,sate=? WHERE id = ?";
        Object parmas[] = { ficWithdrawDto.getActual_price(), ficWithdrawDto.getSate(), ficWithdrawDto.getId() };
        try {
            int i = queryRunner.update(sql, parmas);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return 0;
    }

    /**
     * 新增虚拟币提现记录
     */
    public int insertWithdrawInfo(FicWithdrawDto ficWithdrawDto) {
        String sql = "insert into t_inesv_fic_withdraw(user_no,coin_no,coin_sum,address,poundage,actual_price,sate,date) VALUES (?,?,?,?,?,?,?,?)";
        Object parmas[] = { ficWithdrawDto.getUser_no(), ficWithdrawDto.getCoin_no(), ficWithdrawDto.getCoin_sum(), ficWithdrawDto.getAddress(), ficWithdrawDto.getPoundage(),
                ficWithdrawDto.getActual_price(), ficWithdrawDto.getSate(), ficWithdrawDto.getDate() };
        try {
            int i = queryRunner.update(sql, parmas);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("新增虚拟币提现记录失败");
        }
        return 0;
    }

    /* 虚拟币提现-前台 */
    @Transactional(rollbackFor = { Exception.class, RuntimeException.class })
    public void confirmWithdrawInfo(FicWithdrawDto ficWithdrawDto, UserBalanceDto balanceDto) throws Exception {
        String sql1 = "insert into t_inesv_fic_withdraw(user_no,coin_no,coin_sum,address,poundage,actual_price,sate,date) VALUES (?,?,?,?,?,?,?,?)";
        Object parmas1[] = { ficWithdrawDto.getUser_no(), ficWithdrawDto.getCoin_no(), ficWithdrawDto.getCoin_sum(), ficWithdrawDto.getAddress(), ficWithdrawDto.getPoundage(),
                ficWithdrawDto.getActual_price(), ficWithdrawDto.getSate(), new Date() };
        queryRunner.update(sql1, parmas1);

        //资产--将资产从可用转到冻结
        String sql2 = "SELECT * FROM t_inesv_user_balance WHERE user_no = ? AND coin_type = ? for update";
        Object parmas2[] = { balanceDto.getUser_no(), balanceDto.getCoin_type() };
        UserBalanceDto userBalanceDto = queryRunner.query(sql2, new BeanHandler<UserBalanceDto>(UserBalanceDto.class), parmas2);
        if (userBalanceDto.getEnable_coin().doubleValue() - ficWithdrawDto.getCoin_sum().doubleValue() < 0) {
            int exception = 1 / 0; //手动抛出异常
        }

        String sql3 = "UPDATE t_inesv_user_balance SET enable_coin = ?,unable_coin = ? WHERE user_no = ? and coin_type = ?";
        Object parmas3[] = { balanceDto.getEnable_coin(), balanceDto.getTotal_price(), balanceDto.getUser_no(), balanceDto.getCoin_type() };
        queryRunner.update(sql3, parmas3);
    }

}
