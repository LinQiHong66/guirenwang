package com.inesv.digiccy.common.autocreate.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.inesv.digiccy.common.autocreate.bean.DeveloperType;
import com.inesv.digiccy.common.autocreate.bean.ModelType;
import com.inesv.digiccy.common.autocreate.bean.ProgressType;
import com.inesv.digiccy.common.autocreate.bean.RequestModeType;
import com.inesv.digiccy.common.autocreate.bean.VersionType;
import com.inesv.digiccy.common.dto.BaseRes;


/**
 * @description: struts配置文件多接口的注解
 * @author hxc 
 * @date 2014-6-27 下午4:52:00
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
// 运行时，反射可获得
@Target(ElementType.METHOD)
// 类注解
public @interface AutoDocMethod
{
    /**
     * @description 业务模块。使用方式：参考ModelType枚举类
     * @author：hxc 
     * @createtime 2014-6-27下午5:27:50
     * @return
     */
    public ModelType model();
    
    /**
     * 
     * @description 接口名称简写，最好控制在10各字以内。
     * @author：hxc 
     * @createtime 2014-6-27下午5:24:42
     * @return
     */
    public String name();
    
    /**
     * @description 接口描述(可选)。接口名字的详细描述
     * @author：hxc 
     * @createtime 2014-6-27下午5:27:32
     * @return
     */
    public String description() default ""; // 描述
    
    /**
     * @description create version: 创建接口的app版本. 如VersionType.V240
     * @author：hxc 
     * @createtime 2014-6-27下午5:15:50
     * @return
     */
    public VersionType cver();
    
    /**
     * @description： update version：修改接口的版本. 如VersionType.V240
     * @createversion： V2.5.0
     * @author：hxc 
     * @createtime 2014-7-16上午10:55:01
     * @return
     */
    public VersionType uver() default VersionType.V_DEFAULT;
    
    /**
     * @description 作者
     * @author：hxc 
     * @createtime 2014-6-27下午5:27:43
     * @return
     */
    public DeveloperType author(); // 作者
    
    /**
     * @description 接口创建时间
     * @author：hxc 
     * @createtime 2014-6-27下午5:28:19
     * @return
     */
    public String createTime();
    
    /**
     * @description： 接口修改时间
     * @author：hxc 
     * @createtime 2014-7-24上午10:21:57
     * @return
     */
    public String updateTime() default "";
    
    /**
     * @description： 修改人，默认值是空
     * @author：hxc 
     * @createtime 2014-8-7上午11:04:31
     * @return
     */
    public DeveloperType updateBy() default DeveloperType.DEFAULT;
    
    /**
     * @description 应答类DTO的class。默认值是基类
     * @author：hxc 
     * @createtime 2014-6-30下午12:20:34
     * @return
     */
    public Class<? extends BaseRes> dtoClazz() default BaseRes.class;
    
    /**
     * @description 修改的原因可以在这里描述
     * @author：hxc 
     * @createtime 2014-6-30下午3:38:16
     * @return
     */
    public String[] modify() default ""; // 更新历史
    
    /**
     * @description： 请求参数列表
     * @author：hxc 
     * @createtime 2014-7-31下午6:16:42
     * @return
     */
    public String[] reqParams() default ReqParams.DEFAULT;
    
    /**
     * @description 接口是否已停用，true代表已停用
     * @author：hxc 
     * @createtime 2014-7-1下午4:55:13
     * @return
     */
    public boolean deprecated() default false;
    
    /**
     * @description： 接口截止到指定版本，就停止使用。
     * @author：hxc 
     * @createtime 2014-8-13上午11:08:55
     * @return
     */
    public VersionType stopVer() default VersionType.V_DEFAULT;
    
    /**
     * @description： 接口完成进度。主要用于开发测试联调阶段
     * @createversion： V2.6.0
     * @author：hxc 
     * @createtime 2014-8-14下午2:27:22
     * @return
     */
    public ProgressType progress() default ProgressType.DEVELOPING;
    
    /**
     * @description： 接口请求方式。主要用于开发测试联调阶段
     * @createversion： V2.6.0
     * @author：hxc 
     * @createtime 2014-8-14下午2:27:22
     * @return
     */
    public RequestModeType requestMode() default RequestModeType.DEFAULT;
    
}
