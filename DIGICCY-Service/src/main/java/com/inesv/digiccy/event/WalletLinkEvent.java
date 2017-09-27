package com.inesv.digiccy.event;

/**
 * Created by JimJim on 2016/12/20 0020.
 */
public class WalletLinkEvent {
    /**编号*/
    private Integer id;
    /***/
    private Integer coin_no;

    private String host;

    private String post;

    private String wallet_name;

    private String wallet_pwd;

    private String wallet_lockpwd;

    private String operation;

    public WalletLinkEvent(Integer id, Integer coin_no, String host, String post, String wallet_name, String wallet_pwd, String wallet_lockpwd, String operation) {
        this.id = id;
        this.coin_no = coin_no;
        this.host = host;
        this.post = post;
        this.wallet_name = wallet_name;
        this.wallet_pwd = wallet_pwd;
        this.wallet_lockpwd = wallet_lockpwd;
        this.operation = operation;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCoin_no() {
        return coin_no;
    }

    public String getHost() {
        return host;
    }

    public String getPost() {
        return post;
    }

    public String getWallet_name() {
        return wallet_name;
    }

    public String getWallet_pwd() {
        return wallet_pwd;
    }

    public String getWallet_lockpwd() {
        return wallet_lockpwd;
    }

    public String getOperation() {
        return operation;
    }
}
