package common.autodoc.bean;

import java.util.List;
import org.apache.commons.lang.StringUtils;

public class ResponseBean
{
    private String beanName; // 这个bean的名称
    
    private String className;
    
    private List<ParamField> params; // 参数[类型，字段名称]
    
    private List<ResponseBean> subBeans; // 嵌套的list
    
    @Override
    public String toString()
    {
        if("MyFavouriteShopRes".equals(className)) System.out.println("dfdfd");
        
        StringBuffer sb = new StringBuffer();
        print(sb, this, "");
        
        return sb.toString();
    }
    
    private void print(StringBuffer sb, ResponseBean bean, String s)
    {
        
        if(bean == null || StringUtils.isEmpty(bean.getBeanName())) return;
        
        String space = "    ";
        
        sb.append(s + bean.getBeanName()).append("<" + bean.getClassName() + ">").append("{\n");
        if(null != bean.getParams() && bean.getParams().size() > 0)
        {
            for(ParamField f : bean.getParams())
            {
                sb.append(s + space + f.getName() + "<" + f.getType() + ">" + " : " + f.getNote() + "\n");
            }
        }
        
        // 递归
        if(bean.getSubBeans() != null && bean.getSubBeans().size() > 0)
        {
            for(ResponseBean subBean : bean.getSubBeans())
            {
                print(sb, subBean, s + space);
                sb.append("\n");
            }
        }
        
        sb.append(s + "}");
    }
    
    public String getBeanName()
    {
        return beanName;
    }
    
    public void setBeanName(String beanName)
    {
        this.beanName = beanName;
    }
    
    public String getClassName()
    {
        return className;
    }
    
    public void setClassName(String className)
    {
        this.className = className;
    }
    
    public List<ParamField> getParams()
    {
        return params;
    }
    
    public void setParams(List<ParamField> params)
    {
        this.params = params;
    }
    
    public List<ResponseBean> getSubBeans()
    {
        return subBeans;
    }
    
    public void setSubBeans(List<ResponseBean> subBeans)
    {
        this.subBeans = subBeans;
    }
    
}
