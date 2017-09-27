package com.inesv.digiccy.dto;

/**
 * Created by yc on 2016/12/14 0014.
 */
/**
 *钱包链接信息
 */
public class WalletLinkDto {

    /**编号*/
    private Integer id;
    /***/
    private Integer coin_no;

    private String host;

    private String post;

    private String wallet_name;

    private String wallet_pwd;

    private String wallet_lockpwd;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCoin_no() {
        return coin_no;
    }

    public void setCoin_no(Integer coin_no) {
        this.coin_no = coin_no;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getWallet_name() {
        return wallet_name;
    }

    public void setWallet_name(String wallet_name) {
        this.wallet_name = wallet_name;
    }

    public String getWallet_pwd() {
        return wallet_pwd;
    }

    public void setWallet_pwd(String wallet_pwd) {
        this.wallet_pwd = wallet_pwd;
    }

    public String getWallet_lockpwd() {
        return wallet_lockpwd;
    }

    public void setWallet_lockpwd(String wallet_lockpwd) {
        this.wallet_lockpwd = wallet_lockpwd;
    }
}
