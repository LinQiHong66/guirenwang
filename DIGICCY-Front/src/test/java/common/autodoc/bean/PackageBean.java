package common.autodoc.bean;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * 
 * @author dixon
 * @date 2013/10/30
 */
public class PackageBean
{
    /**
     * 类名字
     */
    private String name;
    
    private String type;
    
    /**
     * 命名空间
     */
    private String namespace;
    
    /**
     * action的名字
     */
    private String actionName;
    
    /**
     * 字节class
     */
    private String clazz; // class name
    
    private String method;
    
    private Class<?> claz;
    
    private String actionUrl;
    
    /**
     * action接口地址
     * 
     * @return
     */
    public String getActionUrl()
    {
        if(StringUtils.isBlank(actionUrl))
        {
          // namespace + "/" +
            actionUrl =  claz.getName() + ".action";
        }
        return actionUrl;
    }
    
    @Override
    public String toString()
    {
        return getActionUrl();
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setNamespace(String namespace)
    {
        this.namespace = namespace;
    }
    
    public String getActionName()
    {
        return actionName;
    }
    
    public void setActionName(String actionName)
    {
        this.actionName = actionName;
    }
    
    public String getClazz()
    {
        return clazz;
    }
    
    public void setClazz(String clazz)
    {
        this.clazz = clazz;
    }
    
    public String getType()
    {
        return type;
    }
    
    public void setType(String type)
    {
        this.type = type;
    }
    
    public Class<?> getClaz()
    {
        return claz;
    }
    
    public void setClaz(Class<?> claz)
    {
        this.claz = claz;
    }
    
    public String getMethod()
    {
        return method;
    }
    
    public void setMethod(String method)
    {
        this.method = method;
    }
    
    public String getNamespace()
    {
        return namespace;
    }
    
}
