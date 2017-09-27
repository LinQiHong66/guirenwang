package com.inesv.digiccy.common.autocreate.bean;

public enum DeveloperType
{
    // 黄炜航
    HUANGWEIHANG("黄炜航"),
    // 陈外晴
    CHENWAIQING("陈外晴"),
    //许权
    XUQUAN("许权"),
    //刘美静
    LIUMEIJIN("刘美静"),
    
    YANCHAO("颜超"),
    
    DEFAULT("") ;
    
    private final String des;
    
    DeveloperType(String des)
    {
        this.des = des;
    }
    
    public String getDes()
    {
        return des;
    }
}
