package com.inesv.digiccy.common.autocreate.bean;

/**
 * @description: 思埠快购APP版本
 * @author hxc
 * @date 2015-05-15 下午4:53:20
 */
public enum VersionType
{ 
    /**
     * V1.0.0版本
     */
    V100("V1.0.0"),
    
    V101("V1.0.1"),
    
    V102("V1.0.2"),
    
    V103("V1.0.3"),
    
    V104("V1.0.4"),
    
    V200("V2.0.0"),
    
    V300("V3.0.0"),
    
    V305("V3.0.5"),
    
    V_DEFAULT("");
    
    private final String des;
    
    VersionType(String des)
    {
        this.des = des;
    }
    
    public String getDes()
    {
        return des;
    }
    
}
