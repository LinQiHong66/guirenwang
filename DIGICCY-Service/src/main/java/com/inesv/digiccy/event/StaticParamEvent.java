package com.inesv.digiccy.event;

import java.math.BigDecimal;

/**
 * Created by JimJim on 2016/12/14 0014.
 */
public class StaticParamEvent {

    private Integer staticParamId;

    private String param;

    private BigDecimal value;
    
    private String code;

    private String operation;

    public StaticParamEvent(Integer staticParamId, String param, BigDecimal value, String code, String operation) {
        this.staticParamId = staticParamId;
        this.param = param;
        this.value = value;
        this.code = code;
        this.operation = operation;
    }

    public Integer getStaticParamId() {
        return staticParamId;
    }

    public String getParam() {
        return param;
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getOperation() {
        return operation;
    }
    
    public String getCode() {
		return code;
	}
    public void setCode(String code) {
		this.code = code;
	}
}
