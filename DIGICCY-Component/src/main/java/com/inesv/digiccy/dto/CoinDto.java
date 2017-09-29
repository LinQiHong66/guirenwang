package com.inesv.digiccy.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;

/**
 * 币种Dto
 * Created by JimJim on 2016/11/17 0017.
 */
public class CoinDto {

    private Long id;
    /** 货币编号 */
    private Integer coin_no;
    /** 货币名称 */
    private String coin_name;
    /** 货币代号 */
    private String coin_core;
    /** 是否开启投票 */
    private Integer vote;
    /** 接口地址*/
    private String address;
    /** 图标 */
    private String icon;
    /** 状态 */
    private Integer state;

    private Date date;

    private String attr1;

    private String attr2;
 
    private BigDecimal withdraw_poundatge_one;
    private BigDecimal withdraw_poundatge_twe;
    private BigDecimal withdraw_poundatge_three;
 
    /**买进手续费*/
    private BigDecimal buy_poundatge;
    
    /**卖出手续费*/
    private BigDecimal sell_poundatge;
    
    private int block;
    
 
    public BigDecimal getWithdraw_poundatge_one() {
		return withdraw_poundatge_one;
	}


	public void setWithdraw_poundatge_one(BigDecimal withdraw_poundatge_one) {
		this.withdraw_poundatge_one = withdraw_poundatge_one;
	}

	public BigDecimal getWithdraw_poundatge_twe() {
		return withdraw_poundatge_twe;
	}

	public void setWithdraw_poundatge_twe(BigDecimal withdraw_poundatge_twe) {
		this.withdraw_poundatge_twe = withdraw_poundatge_twe;
	}

	public BigDecimal getWithdraw_poundatge_three() {
		return withdraw_poundatge_three;
	}

	public void setWithdraw_poundatge_three(BigDecimal withdraw_poundatge_three) {
		this.withdraw_poundatge_three = withdraw_poundatge_three;
	}

 
 
    
    
    public int getBlock() {
		return block;
	}

	public void setBlock(int block) {
		this.block = block;
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

 

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCoin_no() {
        return coin_no;
    }

    public void setCoin_no(Integer coin_no) {
        this.coin_no = coin_no;
    }

    public Long getId() {
		return id;
	}

	public String getCoin_name() {
        return coin_name;
    }

    public void setCoin_name(String coin_name) {
        this.coin_name = coin_name;
    }

    public String getCoin_core() {
        return coin_core;
    }

    public void setCoin_core(String coin_core) {
        this.coin_core = coin_core;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
	
}
