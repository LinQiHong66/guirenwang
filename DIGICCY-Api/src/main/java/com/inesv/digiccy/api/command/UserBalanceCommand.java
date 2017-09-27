package com.inesv.digiccy.api.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/5 0005.
 */
public class UserBalanceCommand {

    @TargetAggregateIdentifier
    /** 编号 */
    private long id;
    /** 用户编号 */
    private Integer user_no;
    /** 货币类型 0:人民币，1:比特币 2:宏强股 3:莱特币*/
    private Integer coin_type;
    /** 可用货币 */
    private BigDecimal enable_coin;
    /** 冻结货币 */
    private BigDecimal unable_coin;
    /** 总资产 */
    private BigDecimal total_price;
    /** 钱包地址 */
    private String wallet_address;
    /** 时间 */
    private Date date;
    /** 备用字段1 */
    private String attr1;
    /** 备用字段2 */
    private String attr2;
    /**操作类型*/
    private String operation;

    public UserBalanceCommand(long id, Integer user_no, Integer coin_type, BigDecimal enable_coin, BigDecimal unable_coin, BigDecimal total_price, String wallet_address, Date date, String operation) {
        this.id = id;
        this.user_no = user_no;
        this.coin_type = coin_type;
        this.enable_coin = enable_coin;
        this.unable_coin = unable_coin;
        this.total_price = total_price;
        this.wallet_address = wallet_address;
        this.date = date;
        this.operation = operation;
    }

    public UserBalanceCommand(long id, Integer user_no, Integer coin_type, String wallet_address, String operation) {
        this.id = id;
        this.user_no = user_no;
        this.coin_type = coin_type;
        this.wallet_address = wallet_address;
        this.operation = operation;
    }

    public UserBalanceCommand(long id, Integer user_no, Integer coin_type, BigDecimal enable_coin, BigDecimal total_price,Date date, String operation) {
        this.id = id;
        this.user_no = user_no;
        this.coin_type = coin_type;
        this.enable_coin = enable_coin;
        this.total_price = total_price;
        this.date = date;
        this.operation = operation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getUser_no() {
        return user_no;
    }

    public void setUser_no(Integer user_no) {
        this.user_no = user_no;
    }

    public Integer getCoin_type() {
        return coin_type;
    }

    public void setCoin_type(Integer coin_type) {
        this.coin_type = coin_type;
    }

    public BigDecimal getEnable_coin() {
        return enable_coin;
    }

    public void setEnable_coin(BigDecimal enable_coin) {
        this.enable_coin = enable_coin;
    }

    public BigDecimal getUnable_coin() {
        return unable_coin;
    }

    public void setUnable_coin(BigDecimal unable_coin) {
        this.unable_coin = unable_coin;
    }

    public BigDecimal getTotal_price() {
        return total_price;
    }

    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
    }

    public String getWallet_address() {
        return wallet_address;
    }

    public void setWallet_address(String wallet_address) {
        this.wallet_address = wallet_address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public String getAttr2() {
        return attr2;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
