/**
 * @className ConfigBean.java
 * @date 2014-8-14下午3:06:57
 *
 * Copyright merchant  2014
 * All right reserved.
 */
package common.autodoc.bean;

import com.inesv.digiccy.common.autocreate.bean.VersionType;




/**
 * @description: 生成文档参数bean
 * @author hxc
 * @date 2014-8-14 下午3:06:57
 */
public class ConfigBean
{
    /**
     * 生成文档保存的路径
     */
    private String filePath;
    
    /**
     * 当前版本
     */
    private VersionType currentVersion = VersionType.V100;
    
    /**
     * 是否生成全量接口，还是生成当前版本（包括新增和修改）
     */
    private boolean productCurrnetVersionInterface = false;
    
    /**
     * AutoDocMethod注解的Deprecated方法
     * 
     * PRODUCT_ALL_NOT_DEPRECATED_INTERFACE = true代表只生成没过时的接口
     * 
     * PRODUCT_ALL_NOT_DEPRECATED_INTERFACE = false生成所有接口，包括过时的。
     * 
     */
    private boolean productAllNotDeprecatedInterface = false;
    
    private boolean develop = true;
    
    public ConfigBean()
    {
        super();
    }
    
    public ConfigBean(String filePath, VersionType currentVersion, boolean productCurrnetVersionInterface,
            boolean productAllNotDeprecatedInterface, boolean develop)
    {
        super();
        this.filePath = filePath;
        this.currentVersion = currentVersion;
        this.productCurrnetVersionInterface = productCurrnetVersionInterface;
        this.productAllNotDeprecatedInterface = productAllNotDeprecatedInterface;
        this.develop = develop;
    }
    
    public String getFilePath()
    {
        return filePath;
    }
    
    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }
    
    public VersionType getCurrentVersion()
    {
        return currentVersion;
    }
    
    public void setCurrentVersion(VersionType currentVersion)
    {
        this.currentVersion = currentVersion;
    }
    
    public boolean isProductCurrnetVersionInterface()
    {
        return productCurrnetVersionInterface;
    }
    
    public void setProductCurrnetVersionInterface(boolean productCurrnetVersionInterface)
    {
        this.productCurrnetVersionInterface = productCurrnetVersionInterface;
    }
    
    public boolean isProductAllNotDeprecatedInterface()
    {
        return productAllNotDeprecatedInterface;
    }
    
    public void setProductAllNotDeprecatedInterface(boolean productAllNotDeprecatedInterface)
    {
        this.productAllNotDeprecatedInterface = productAllNotDeprecatedInterface;
    }
    
    public boolean isDevelop()
    {
        return develop;
    }
    
    public void setDevelop(boolean develop)
    {
        this.develop = develop;
    }
    
}
