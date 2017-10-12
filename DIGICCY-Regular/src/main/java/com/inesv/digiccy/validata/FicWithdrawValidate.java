package com.inesv.digiccy.validata;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.dbutils.QueryRunner;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.exceptions.ClientException;
import com.inesv.digiccy.api.command.InesvPhoneCommand;
import com.inesv.digiccy.api.command.UserBalanceCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.CoinDto;
import com.inesv.digiccy.dto.FicRechargeDto;
import com.inesv.digiccy.dto.FicWithdrawDto;
import com.inesv.digiccy.dto.UserBalanceDto;
import com.inesv.digiccy.dto.UserInfoDto;
import com.inesv.digiccy.dto.WalletAddressDto;
import com.inesv.digiccy.dto.WalletLinkDto;
import com.inesv.digiccy.persistence.finance.FicWithdrawPersistence;
import com.inesv.digiccy.query.QueryFicRechargeInfo;
import com.inesv.digiccy.query.QueryFinWithdrawInfo;
import com.inesv.digiccy.query.QueryStaticParam;
import com.inesv.digiccy.query.QueryUserBalanceInfo;
import com.inesv.digiccy.query.QueryWalletAddressInfo;
import com.inesv.digiccy.query.QueryWalletLinkInfo;
import com.inesv.digiccy.query.QueryWithdrawInfo;
import com.inesv.digiccy.query.coin.QueryCoin;
import com.inesv.digiccy.redis.RedisCodeImpl;
import com.inesv.digiccy.sms.SendMsgUtil;
import com.inesv.digiccy.util.MD5;
import com.inesv.digiccy.util.SmsUtil;
import com.inesv.digiccy.util.StringUtil;
import com.inesv.digiccy.wallet.BitcoinAPI;
import com.inesv.digiccy.wallet.EthcoinAPI;

/**
 * Created by Administrator on 2016/12/1 0001.
 */
@Component
public class FicWithdrawValidate {

    @Autowired
    QueryFinWithdrawInfo      queryFinWithdrawInfo;

    @Autowired
    QueryUserBalanceInfo      queryUserBalanceInfo;

    @Autowired
    QueryWalletLinkInfo       queryWalletLinkInfo;

    @Autowired
    private CommandGateway    commandGateway;

    @Autowired
    QueryRunner               queryRunner;

    @Autowired
    private QueryWithdrawInfo queryWithdrawInfo;

    @Autowired
    private QueryCoin         queryCoin;

    @Autowired
    RedisCodeImpl             redisCode;

    @Autowired
    SendMsgUtil               sendMsgUtil;

    @Autowired
    QueryStaticParam          queryStaticParam;

    @Autowired
    QueryWalletAddressInfo    queryWalletAddressInfo;

    @Autowired
    QueryFicRechargeInfo      queryFicRechargeInfo;

    @Autowired
    FicWithdrawPersistence    ficWithdrawPersistence;


    /**
     * 账单信息
     */
    public Map<String, Object> validateBill(String start, String end, String date, String type, Integer userId) {
        Map<String, Object> resultmap = new HashMap();
        List<Map<Object, Object>> bills = new ArrayList<Map<Object, Object>>();
        if (type.equals("0")) // 充值
        {
            List<FicRechargeDto> rechs = queryFicRechargeInfo.getrechargeInfo(start, end, date, userId);
            for (FicRechargeDto ficr : rechs) {
                CoinDto coin = queryCoin.queryCoinTypeByCoinNo(ficr.getCoin_no()).get(0);
                Map<Object, Object> map = new HashMap<>();
                map.put("date", ficr.getDate());
                map.put("type", coin.getCoin_core() + "充值");
                map.put("money", ficr.getActual_price());
                map.put("fee", "-");
                map.put("state", ficr.getState());
                bills.add(map);
            }
        } else {
            List<FicWithdrawDto> withs = queryFicRechargeInfo.getwithdrawInfo(start, end, date, userId);
            for (FicWithdrawDto with : withs) {
                CoinDto coin = queryCoin.queryCoinTypeByCoinNo(with.getCoin_no()).get(0);
                Map<Object, Object> map = new HashMap<>();
                map.put("date", with.getDate());
                map.put("type", coin.getCoin_core() + "提现");
                map.put("money", with.getCoin_sum());
                map.put("fee", with.getPoundage());
                map.put("state", with.getSate());
                bills.add(map);
            }
        }
        resultmap.put("bills", bills);
        return resultmap;
    }

    /**
     * 查询出用户的提现信息
     */
    public Map<String, Object> validateWithdrawInfo(int userNo, int coinType) {
        Map<String, Object> map = new HashMap();
        UserBalanceDto userBalanceDto = queryUserBalanceInfo.queryEnableCoin(userNo, coinType); // 可用金额
        List<WalletAddressDto> walletAddress = queryWalletAddressInfo.queryWalletAddressInfo(userNo, coinType); // 用户钱包
        BigDecimal enableCoin = userBalanceDto == null ? new BigDecimal(0) : userBalanceDto.getEnable_coin();// 获取可用资产
        List<FicWithdrawDto> withdraws = queryWithdrawInfo.queryWithdrawInfo(userNo); // 查询虚拟币提现信息

        // 手续费列表
        List<BigDecimal> fees = new ArrayList<>();
        List<CoinDto> coins = queryCoin.queryCoinTypeByCoinNo(coinType);
        CoinDto coin = null;
        if (!coins.isEmpty()) {
            coin = coins.get(0);
        } else {
            map.put("code", ResponseCode.FAIL_ERROR_CODE);
            map.put("desc", ResponseCode.FAIL_ERROR_COINTYPE_DESC);// 不存在该币种
            return map;
        }
        if (coin.getWithdraw_poundatge_one() != null) {
            fees.add(coin.getWithdraw_poundatge_one());
        }
        if (coin.getWithdraw_poundatge_twe() != null) {
            fees.add(coin.getWithdraw_poundatge_twe());
        }
        if (coin.getWithdraw_poundatge_three() != null) {
            fees.add(coin.getWithdraw_poundatge_three());
        }

        // 查询用户银行信息

        map.put("enable", enableCoin);
        map.put("walletAddress", walletAddress);
        map.put("fees", fees);
        map.put("withdraws", withdraws);
        map.put("code", ResponseCode.SUCCESS);
        map.put("desc", ResponseCode.SUCCESS_DESC);

        return map;
    }

    /**
     * 查询相应币种的交易记录及可用余额，判断是否有钱包地址
     */
    public Map<String, Object> validateVirtualWithdraw(int userNo, int coinNo) {
        Map<String, Object> map = new HashMap();
        /* WalletLinkDto walletLinkDto = WalletLinkInfo(coinNo);//获取钱包链接信息 String host =
         * walletLinkDto.getHost();//获取钱包ip地址 String post =
         * walletLinkDto.getPost();//获取钱包端口号 String walletName =
         * walletLinkDto.getWallet_name();//钱包用户名 String walletPwd =
         * walletLinkDto.getWallet_pwd();//钱包密码 String lockPwd =
         * walletLinkDto.getWallet_lockpwd();//钱包锁 TransactionResult transactionResult =
         * new TransactionResult(host, post, walletName, walletPwd, lockPwd);//建立连接 */
        List<FicWithdrawDto> list = queryFinWithdrawInfo.queryFicWithdrawInfo(userNo, coinNo);// 查询虚拟币提现信息
        List<UserBalanceDto> addressLista = queryUserBalanceInfo.queryWallteAddress(userNo, coinNo);// 查询是否有钱地址
        BigDecimal enable = queryUserBalanceInfo.queryEnableCoin(userNo, coinNo).getEnable_coin();// 查询用户可用币种
        map.put("enable", enable);
        if (addressLista.isEmpty()) {
            map.put("data", list);
            map.put("coin", addressLista);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
        } else {
            map.put("data", list);
            map.put("coin", addressLista);
            map.put("code", ResponseCode.FAIL);
            map.put("desc", ResponseCode.FAIL_DESC);
        }
        return map;
    }

    /**
     * 用户新增钱包地址
     */
    public Map<String, Object> validateWalletAddress(String address, int userNo, int coinNo, BitcoinAPI bitcoinAPI) {
        Map<String, Object> map = new HashMap();
        String validateAddress = bitcoinAPI.validateaddress(address);// 校验钱包地址是否合法
        Map<String, Object> resultMap = (Map<String, Object>) JSON.parseObject(validateAddress, Map.class);// 校验地址的合法性
        if (((boolean) resultMap.get("isvalid")) == true) {
            UserBalanceCommand command = new UserBalanceCommand(6666, userNo, coinNo, address, "update");
            commandGateway.send(command);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
        } else {
            map.put("code", ResponseCode.FAIL);
            map.put("desc", ResponseCode.FAIL_DESC);
        }
        return map;
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

    /**
     * 确认提现-其他币
     * 
     * @param address
     *            :提现地址
     * @param bitcoinAPI
     * @param userNo
     * @param dealPwd
     * @param coinNo
     * @param fee
     *            ：手续费等级
     * @param trueNum1
     *            : 提现金额
     * @return
     * @throws Exception
     */

    //虚拟币提现审核
    public Map<String, Object> validateFirWithdrawSH(Integer id) throws Exception {
        Map<String, Object> map = new HashMap();
        //获取提现信息
        FicWithdrawDto ficwithdraw = queryFinWithdrawInfo.queryFicWithdrawInfoById(id);
        Integer user_no = ficwithdraw.getUser_no();
        Integer coin_no = ficwithdraw.getCoin_no();
        BigDecimal coin_sum = ficwithdraw.getCoin_sum();
        BigDecimal poundage = ficwithdraw.getPoundage();
        BigDecimal actual_price = ficwithdraw.getActual_price();
        Integer sate = ficwithdraw.getSate();
        String address = ficwithdraw.getAddress();
        BigDecimal truenum = coin_sum.subtract(poundage); //实际转账
        //获取资产信息
        UserBalanceDto userBalanceDto = queryUserBalanceInfo.queryEnableCoin(user_no, coin_no);
        BigDecimal unable_coin = userBalanceDto.getUnable_coin();
        BigDecimal total_price = userBalanceDto.getTotal_price();
        //修改状态为1，减去冻结金额及总金额,加上实际到账金额
        sate = 1;
        unable_coin = unable_coin.subtract(coin_sum);
        total_price = total_price.subtract(coin_sum);
        actual_price = actual_price.add(truenum);
        if (unable_coin.doubleValue() < 0 || total_price.doubleValue() < 0) {
            map.put("code", "200");
            map.put("desc", "提现失败");
            return map;
        }
        //修改资产-转币
        try {
        	ficWithdrawPersistence.ficWithTansac(actual_price, unable_coin, total_price, user_no, coin_no, poundage, id, truenum, address);
        	map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", "200");
            map.put("desc", "提现失败");
		}
        return map;
    }

    /**
     * 确认提现-以太币
     * 
     * @param address
     *            :提现地址
     * @param ethcoinAPI
     * @param userNo
     * @param dealPwd
     * @param coinNo
     * @param fee
     *            ：手续费等级
     * @param trueNum1
     *            : 提现金额
     * @return
     * @throws SQLException
     */
    @Transactional(rollbackFor = { Exception.class, RuntimeException.class })
    public Map<String, Object> validateFirWithdraw(String address, int userNo, String dealPwd, int coinNo, int fee, String trueNum1) throws Exception{
        Map<String, Object> map = new HashMap();
        // 获取不同等级的手续费
        BigDecimal Proces = new BigDecimal(0); // 获取手续费
        List<CoinDto> coins = queryCoin.queryCoinTypeByCoinNo(coinNo);
        CoinDto coin = null;
        if (!coins.isEmpty() || coins.size() != 0) {
            coin = coins.get(0);
        } else {
            map.put("code", ResponseCode.FAIL_ERROR_CODE);
            map.put("desc", ResponseCode.FAIL_ERROR_COINTYPE_DESC);// 不存在该币种
            return map;
        }
        if (fee == 1) {
            Proces = coin.getWithdraw_poundatge_one();
            if (Proces == null) {
                Proces = new BigDecimal(0);
            }
        } else if (fee == 2) {
            Proces = coin.getWithdraw_poundatge_twe();
            if (Proces == null) {
                Proces = new BigDecimal(0);
            }
        } else if (fee == 3) {
            Proces = coin.getWithdraw_poundatge_three();
            if (Proces == null) {
                Proces = new BigDecimal(0);
            }
        }

        double trueNum = Double.parseDouble(trueNum1);
        BigDecimal trueNumBig = new BigDecimal(trueNum1);

        UserInfoDto userInfoDto = queryFinWithdrawInfo.queryDeaPSW(userNo);
        String userDealPwd = userInfoDto.getDeal_pwd(); // 获取用户交易密码
        String md5DealPwd = new MD5().getMD5(dealPwd);// 将输入密码转化成MD5格式
        if (!md5DealPwd.equals(userDealPwd)) {
            map.put("code", ResponseCode.FAIL_DEALPWD);
            map.put("desc", ResponseCode.FAIL_DEALPWD_DESC);// 交易密码不正确
            return map;
        }
        UserBalanceDto userBalanceDto = queryUserBalanceInfo.queryEnableCoin(userNo, coinNo);
        BigDecimal enable = userBalanceDto.getEnable_coin();// 获取用户币种可用余额
        BigDecimal unenable = userBalanceDto.getUnable_coin(); // 获取用户币种冻结
        //BigDecimal Totalprice = userBalanceDto.getTotal_price();// 获取总额
        double doubleEnable = enable.doubleValue();
        if (trueNum >= doubleEnable || doubleEnable <= 0) {
            map.put("code", ResponseCode.FAIL);
            map.put("desc", "提现货币金额不足或提现货币金额小于0，请查看！");
            return map;
        }
        //double price = Proces.doubleValue(); // 手续费率
        //double procesPrice = trueNum * price; // 手续费
        //String fa = new DecimalFormat("#.000").format(trueNumBig);
        //BigDecimal BigTrueNum = new BigDecimal(fa);// 将提现金额转成BigDecimal
        Lock lock = new ReentrantLock();// 锁对象  
        lock.lock();// 得到锁  
        try {
            BigDecimal nowEnble = enable.subtract(trueNumBig);
            BigDecimal nowUnablePrice = unenable.add(trueNumBig);
            if (nowEnble.doubleValue() < 0) {
                int exception = 1 / 0;
            }
            //提现表
            FicWithdrawDto ficWithdrawDto = new FicWithdrawDto();
            ficWithdrawDto.setUser_no(userNo);
            ficWithdrawDto.setCoin_no(coinNo);
            ficWithdrawDto.setCoin_sum(trueNumBig);
            ficWithdrawDto.setAddress(address);
            ficWithdrawDto.setPoundage(Proces);
            ficWithdrawDto.setActual_price(new BigDecimal(0));
            ficWithdrawDto.setSate(0);
            //用户资产表
            UserBalanceDto balanceDto = new UserBalanceDto();
            balanceDto.setUser_no(userNo);
            balanceDto.setCoin_type(coinNo);
            balanceDto.setEnable_coin(nowEnble);
            balanceDto.setUnable_coin(nowUnablePrice);
            ficWithdrawPersistence.confirmWithdrawInfo(ficWithdrawDto, balanceDto);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
        } catch (Exception e) {
            map.put("code", ResponseCode.FAIL);
            map.put("desc", ResponseCode.FAIL_DESC);
        } finally {
            lock.unlock();// 释放锁
        }
        return map;
    }
    /* @Transactional
     * public Map<String, Object> validateFirWithdraw(String address, int userNo, String dealPwd, int coinNo, int fee,
     * String trueNum1) throws SQLException {
     * 
     * Map<String, Object> map = new HashMap();
     * // 获取不同等级的手续费
     * BigDecimal Proces = new BigDecimal(0); // 获取手续费
     * List<CoinDto> coins = queryCoin.queryCoinTypeByCoinNo(coinNo);
     * CoinDto coin = null;
     * if (!coins.isEmpty()) {
     * coin = coins.get(0);
     * } else {
     * map.put("code", ResponseCode.FAIL_ERROR_CODE);
     * map.put("desc", ResponseCode.FAIL_ERROR_COINTYPE_DESC);// 不存在该币种
     * return map;
     * }
     * if (fee == 1) {
     * Proces = coin.getWithdraw_poundatge_one();
     * } else if (fee == 2) {
     * Proces = coin.getWithdraw_poundatge_twe();
     * } else if (fee == 3) {
     * Proces = coin.getWithdraw_poundatge_three();
     * }
     * System.err.println("提现手续费：" + Proces);
     * 
     * double trueNum = Double.parseDouble(trueNum1);
     * BigDecimal trueNumBig = new BigDecimal(trueNum1);
     * 
     * UserInfoDto userInfoDto = queryFinWithdrawInfo.queryDeaPSW(userNo);
     * String userDealPwd = userInfoDto.getDeal_pwd(); // 获取用户交易密码
     * String md5DealPwd = new MD5().getMD5(dealPwd);// 将输入密码转化成MD5格式
     * 
     * UserBalanceDto userBalanceDto = queryUserBalanceInfo.queryEnableCoin(userNo, coinNo);
     * BigDecimal enable = userBalanceDto.getEnable_coin();// 获取用户币种可用余额
     * BigDecimal unenable = userBalanceDto.getUnable_coin(); // 获取用户币种冻结
     * BigDecimal Totalprice = userBalanceDto.getTotal_price();// 获取总额
     * double doubleEnable = enable.doubleValue();
     * 
     * if (trueNum <= doubleEnable && doubleEnable > 0) {
     * if (md5DealPwd.equals(userDealPwd)) {
     * double price = Proces.doubleValue(); // 手续费率
     * double procesPrice = trueNum * price; // 手续费
     * 
     * String fa = new DecimalFormat("#.000").format(trueNumBig);
     * BigDecimal BigTrueNum = new BigDecimal(fa);// 将提现金额转成BigDecimal
     * 
     * FicWithdrawCommand ficWithdrawCommand = new FicWithdrawCommand(55, userNo, coinNo, trueNumBig, address,
     * Proces, new BigDecimal(0), 0, new Date(), "insert");
     * commandGateway.send(ficWithdrawCommand);// 新增提现记录
     * BigDecimal nowEnble = enable.subtract(trueNumBig);
     * BigDecimal nowUnenablePrice = unenable.add(trueNumBig);
     * UserBalanceCommand command1 = new UserBalanceCommand(5535L, userNo, coinNo, nowEnble, nowUnenablePrice,
     * new Date(), "updateEnble");// 提现之后更新用户金额和总额
     * // queryRunner.query("select aaa from t_inesv_XXX", new BeanListHandler<>(UserBalanceDto.class));
     * commandGateway.sendAndWait(command1);
     * map.put("code", ResponseCode.SUCCESS);
     * map.put("desc", ResponseCode.SUCCESS_DESC);
     * 
     * } else {
     * map.put("code", ResponseCode.FAIL_DEALPWD);
     * map.put("desc", ResponseCode.FAIL_DEALPWD_DESC);// 交易密码不正确
     * }
     * } else {
     * map.put("code", ResponseCode.FAIL_INPUT_COIN);
     * map.put("desc", ResponseCode.FAIL_INPUT_COIN_DESC);// 请输入正确的提币金额
     * }
     * 
     * return map;
     * } */

    /**
     * 发送短信验证码
     */
    public Map<String, Object> validatePhoneCode(int type, String mobile) {

        int mCode = sendMsgUtil.getCode(mobile, type);

        redisCode.setSms(mobile, type, mCode);
        String msgContent = "";
        try {
        	msgContent = SmsUtil.sendMySms(mobile, mCode + "");
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Map<String, Object> map = new HashMap();
        try {
        	msgContent = SmsUtil.sendMySms(mobile + "", mCode + "");
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        InesvPhoneCommand command = new InesvPhoneCommand(0, null, mobile, 1, mCode, "insert");
        commandGateway.send(command);
        if (!StringUtil.isEmpty(msgContent)) {
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
        } else {
            map.put("code", ResponseCode.FAIL);
            map.put("desc", ResponseCode.FAIL_DESC);
        }
        return map;

    }

    /**
     * 校验短信验证码
     */
    public int validataCompare(String mobile, String code) {
        int smsNum = redisCode.getSms(mobile, 1);
        // 通用手机号码验证
        if (code.equals(smsNum + "")) { return 1; }
        return 0;
    }

    /** 查询币种钱包链接信息 */
    public WalletLinkDto WalletLinkInfo(int coinType) {
        WalletLinkDto walletLinkDto = queryWalletLinkInfo.queryLinkInfo(coinType);
        return walletLinkDto;
    }

    /** 查询用户钱包地址 */
    public Map<String, Object> getUserBalanceAndAddress(Integer userNo, Integer coinNo) {
        Map<String, Object> map = new HashMap<>();
        List<UserBalanceDto> list = queryWithdrawInfo.getUserBalanceAndAddress(userNo, coinNo);
        if (list != null) {
            map.put("data", list);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
        } else {
            map.put("code", ResponseCode.FAIL);
            map.put("desc", ResponseCode.FAIL_DESC);
        }
        return map;
    }

    /**
     * 查询币种提现信息
     */
    public Map<String, Object> getUserBalanceWithdraw(Integer userNo, Integer coinNo) {
        Map<String, Object> map = new HashMap<>();
        List<FicWithdrawDto> list = queryWithdrawInfo.getUserBalanceWithdraw(userNo, coinNo);
        if (list != null) {
            map.put("data", list);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
        } else {
            map.put("code", ResponseCode.FAIL);
            map.put("desc", ResponseCode.FAIL_DESC);
        }
        return map;
    }

}
