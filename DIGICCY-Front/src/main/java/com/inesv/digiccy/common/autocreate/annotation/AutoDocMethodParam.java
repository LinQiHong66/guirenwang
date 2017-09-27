package com.inesv.digiccy.common.autocreate.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自动生成接口文档--请求参数，必须要添加此注解
 * 
 * @author hxc
 * @date 2013-10-30
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
// 运行时，反射可获得
@Target(ElementType.METHOD)
// 字段注解
public @interface AutoDocMethodParam
{
    /**
     * 字段简称
     * 
     * @return
     */
    public String note();
    
    /**
     * 字段名字
     * 
     * @return
     */
    public String name();
}
