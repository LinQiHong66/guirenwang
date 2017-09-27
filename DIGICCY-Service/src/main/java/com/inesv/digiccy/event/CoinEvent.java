package com.inesv.digiccy.event;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by JimJim on 2016/11/17 0017.
 */
public class CoinEvent {

    private Long coinId;
    /** 货币编号 */
    private Integer coinNo;
    /** 货币名称 */
    private String coinName;
    /** 货币代号 */
    private String coinCore;
    /** 是否开启投票 */
    private Integer vote;
    /** 状态 */
    private Integer state;
    /** 接口地址*/
    private String address;
    /** 图标 */
    private String icon;
    /** 提现手续费*/

    private BigDecimal sell_withdraw_poundatge_one;
    private BigDecimal sell_withdraw_poundatge_twe;
    private BigDecimal sell_withdraw_poundatge_three;

    private Date date;

    private String attr1;

    private String attr2;

    private String operation;
    
    /**买进手续费*/
    private BigDecimal buy_poundatge;
    
    /**卖出手续费*/
    private BigDecimal sell_poundatge;
    
    private int block;

    public CoinEvent(Long coinId, Integer coinNo, String coinName, String coinCore, Integer vote, Integer state,
                     String address, String icon, Date date, String attr1, String attr2, String operation,
                     BigDecimal buy_poundatge,BigDecimal sell_poundatge,int block,BigDecimal sell_withdraw_poundatge_one,BigDecimal sell_withdraw_poundatge_twe,BigDecimal sell_withdraw_poundatge_three) {
        this.coinId = coinId;
        this.coinNo = coinNo;
        this.coinName = coinName;
        this.coinCore = coinCore;
        this.vote = vote;
        this.state = state;
        this.address = address;
        this.icon = icon;
        this.date = date;
        this.attr1 = attr1;
        this.attr2 = attr2;
        this.operation = operation;
        this.buy_poundatge = buy_poundatge;
        this.sell_poundatge = sell_poundatge;
        this.block = block;
        this.sell_withdraw_poundatge_one = sell_withdraw_poundatge_one;
        this.sell_withdraw_poundatge_twe = sell_withdraw_poundatge_twe;
        this.sell_withdraw_poundatge_three = sell_withdraw_poundatge_three;
    }
    
    

    public BigDecimal getBuy_poundatge() {
		return buy_poundatge;
	}



	public void setBuy_poundatge(BigDecimal buy_poundatge) {
		this.buy_poundatge = buy_poundatge;
	}



	public BigDecimal getSell_poundatge() {
		return sell_poundatge;
	}



	public void setSell_poundatge(BigDecimal sell_poundatge) {
		this.sell_poundatge = sell_poundatge;
	}



	public Long getCoinId() {
        return coinId;
    }

    public Integer getCoinNo() {
        return coinNo;
    }

    public String getCoinName() {
        return coinName;
    }

    public String getCoinCore() {
        return coinCore;
    }

    public Integer getState() {
        return state;
    }

    public Date getDate() {
        return date;
    }

    public String getAttr1() {
        return attr1;
    }

    public String getAttr2() {
        return attr2;
    }

    public String getOperation() {
        return operation;
    }

    public Integer getVote() {
        return vote;
    }

    public String getAddress() {
        return address;
    }

    public String getIcon() {
        return icon;
    }



	public int getBlock() {
		return block;
	}



	public void setBlock(int block) {
		this.block = block;
	}
	public BigDecimal getSell_withdraw_poundatge_one() {
		return sell_withdraw_poundatge_one;
	}

	public void setSell_withdraw_poundatge_one(BigDecimal sell_withdraw_poundatge_one) {
		this.sell_withdraw_poundatge_one = sell_withdraw_poundatge_one;
	}

	public BigDecimal getSell_withdraw_poundatge_twe() {
		return sell_withdraw_poundatge_twe;
	}

	public void setSell_withdraw_poundatge_twe(BigDecimal sell_withdraw_poundatge_twe) {
		this.sell_withdraw_poundatge_twe = sell_withdraw_poundatge_twe;
	}

	public BigDecimal getSell_withdraw_poundatge_three() {
		return sell_withdraw_poundatge_three;
	}

	public void setSell_withdraw_poundatge_three(BigDecimal sell_withdraw_poundatge_three) {
		this.sell_withdraw_poundatge_three = sell_withdraw_poundatge_three;
	}
    
}
