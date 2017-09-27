package common.autodoc;
//import com.sibu.agencyquick.common.autocreate.bean.VersionType;

import com.inesv.digiccy.common.autocreate.bean.VersionType;

import common.autodoc.bean.ConfigBean;

public class AutoDocMain
{  
    public static void main(String[] args)
    {
        AutoDocDirector autoDocDirector = new AutoDocDirector(buildConfigBean1());
        autoDocDirector.autoDoc();
    } 
      
    /**
     * @author：hxc
     * @createtime ： 2015年5月16日 上午1:49:34
     * @description 自动生成文档
     * @since version 初始于版本
     * @return 
     */  
    private static ConfigBean buildConfigBean1()
    {
        VersionType currentVersion = VersionType.V100;
        String filePath = "D:/测试接口文档" + currentVersion.getDes() + "版本.pdf";
        ConfigBean configBean = 
        new ConfigBean();
        configBean.setCurrentVersion(currentVersion);
        configBean.setDevelop(true);
        configBean.setFilePath(filePath);
        configBean.setProductAllNotDeprecatedInterface(false);
        configBean.setProductCurrnetVersionInterface(false);
        return configBean;
    } 
     
    /**
     * @description： 构建生成全量
     * @author：hxc
     * @createtime 2014-8-14下午3:17:55
     * @return 
     */
    private static ConfigBean buildConfigBean2()
    {
		  VersionType currentVersion = VersionType.V305;
	      String filePath = "D:/思埠快购服务端接口文档-全量" + currentVersion.getDes() + "版本.pdf";
        ConfigBean configBean = new ConfigBean();
        configBean.setCurrentVersion(currentVersion);
        configBean.setFilePath(filePath);
        configBean.setProductAllNotDeprecatedInterface(false);
        configBean.setProductCurrnetVersionInterface(false);
        return configBean;
    }
    
    /**
     * @description： 构建生成全量(排除已停用)
     * @author：hxc
     * @createtime 2014-8-14下午3:17:55
     * @return 
     */
    private static ConfigBean buildConfigBean3()
    {
    	 VersionType currentVersion = VersionType.V305;
        String filePath = "D:/思埠快购服务端接口文档-全量(除已停用).pdf";
        ConfigBean configBean = new ConfigBean();
        configBean.setCurrentVersion(currentVersion);
        configBean.setFilePath(filePath);
        configBean.setProductAllNotDeprecatedInterface(true);
        configBean.setProductCurrnetVersionInterface(false);
        return configBean;
    }
    
    
    
}
