package com.inesv.digiccy.dto;

import java.math.BigDecimal;

/**
 * Created by JimJim on 2016/12/14 0014.
 */
public class StaticParamsDto {

    private Integer id;

    private String param;

    private BigDecimal value;

    private String code;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getCode() {
		return code;
	}
    
    public void setCode(String code) {
		this.code = code;
	}
    
	public StaticParamsDto(Integer id, String param, BigDecimal value, String code) {
		this.id = id;
		this.param = param;
		this.value = value;
		this.code = code;
	}

	public StaticParamsDto() {
	}
    
    
}
