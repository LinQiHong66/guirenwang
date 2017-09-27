package common.autodoc.bean;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.inesv.digiccy.common.autocreate.annotation.ReqParams;
import com.inesv.digiccy.common.autocreate.bean.DeveloperType;




/**
 * 生成xml的bean
 * 
 * @author hxc
 * @date 2013/10/30
 */
public class DocBean
{
    private String modelName; // 模块名字
    
    private String actionName; // action简称
    
    private String actionDesc; // action描述
    
    private String mappingUrl;//method url
    
    private DeveloperType author;
    
    private String createTime; // 创建时间
    
    private String updateTime; // 修改时间
    
    private String updateBy;// 修改人
    
    private String requestMode; //请求方式
    
    private String className; // 类名
    
    private String superClassName;// 父类类名， 制作锚点
    
    private String methodName;// 方法名字
    
    private String actionUrl; // action路径
    
    private String version;
    
    private String updateVersion; // 修改的版本
    
    private List<ParamField> params; // 参数,Map<paramName, description>
    
    private ResponseBean response; // 响应参数
    
    private String[] modifyRecodes; // 修改记录
    
    private boolean deprecated; // 是否已停用
    
    private String[] reqParams; // 请求参数列表
    
    private String stopVersion;// 接口停用的版本号
    
    private String progress; //接口开发进度
    
    @Override
    public String toString()
    {
      //"\n响应码:" + response + 
        return "版本："
                + version + "\n创建者：" + author.getDes() + "\n时间:" + createTime + "\n简称:" + actionName + "\n类名:" + className + "\n方法名:" + methodName + "\n路径:"
                + actionUrl + "\n参数:" + showParam() + "\n开发进度:" + progress + "\n修改记录:" + showModifyRecods()
                + "\n-------------------------";
    }
    
    
    // 请求举例
    public String showCallExample(){
    	
    	   boolean noParams = false;
    	
        if(params != null || params.size() == 0)
        {
            List<String> reqParamList = null;
            if(reqParams != null && reqParams.length!=0)
            {
                if(reqParams.length > 0)
                {
                    
                    if(reqParams[0].equals(ReqParams.DEFAULT))
                    {
                        reqParamList = null;
                    }
                    else if(reqParams[0].equals(ReqParams.NONE))
                    {
                        noParams = true;
                    }
                    else
                    {
                        reqParamList = Arrays.asList(reqParams);
                    }
                    
                }
                
            }
            
            StringBuffer sb = new StringBuffer();
            
            if(noParams){
                sb.append("http://192.168.10.226:30000"+mappingUrl+".do");
            }else{
            	sb.append("http://192.168.10.226:30000"+mappingUrl+".do?");
            }
            if(!noParams)
            {
                int i = 0;
                int m=0;
                for(ParamField pf : params)
                {
                	m++;
 
                	
                    if(reqParamList == null || reqParamList.isEmpty())
                    {
                        
                    	
                    	if(m==1){
                    	 	sb.append(pf.getName()+"=xxx");
                            
                            if(++i < params.size()) sb.append("\n");
                    	}else{
                    	 	sb.append("&"+pf.getName()+"=xxx");
                            
                            if(++i < params.size()) sb.append("\n");
                    	}
                    	
                   
                    }
                    else
                    {
                        if(reqParamList.contains(pf.getName()))
                        {
                        	if(m==1){
                        		sb.append(""+pf.getName()+"=xxx");
                                
                                if(++i < params.size()) sb.append("\n");
                        	}else{
                        		sb.append("&"+pf.getName()+"=xxx");
                                
                                if(++i < params.size()) sb.append("\n");
                        	}
                        	
                        }
                    }
                }
            }
            
            return sb.toString();

            
        }
    	
    	
    	return "";
    	
    	
    }
    
   
    
    
    
    public String showParam()
    {
        boolean noParams = false;
        
        if(params != null || params.size() > 0)
        {
            List<String> reqParamList = null;
            if(reqParams != null)
            {
                if(reqParams.length > 0)
                {
                    
                    if(reqParams[0].equals(ReqParams.DEFAULT))
                    {
                        reqParamList = null;
                    }
                    else if(reqParams[0].equals(ReqParams.NONE))
                    {
                        noParams = true;
                    }
                    else
                    {
                        reqParamList = Arrays.asList(reqParams);
                    }
                    
                }
                
            }
            
            StringBuffer sb = new StringBuffer();
            
            if(!noParams)
            {
                int i = 0;
                for(ParamField pf : params)
                {
                    if(reqParamList == null || reqParamList.isEmpty())
                    {
                        
                        sb.append("·" + pf.getName() + "<").append(pf.getType() + "> : ").append(pf.getNote());
                        
                        if(++i < params.size()) sb.append("\n");
                    }
                    else
                    {
                        if(reqParamList.contains(pf.getName()))
                        {
                            sb.append("·" + pf.getName() + "<").append(pf.getType() + "> : ").append(pf.getNote());
                            
                            if(++i < params.size()) sb.append("\n");
                        }
                    }
                }
            }
            
            return sb.toString();
            
            
            
            
            
        }
        
        return "";
    }
    
    public String showModifyRecods()
    {
        StringBuffer sb = new StringBuffer();
        int i = 0;
        if(modifyRecodes != null && StringUtils.isNotEmpty(modifyRecodes[0]))
        {
            for(String s : modifyRecodes)
            {
                if(++i < modifyRecodes.length)
                    sb.append("·" + s + "\n");
                else sb.append("·" + s);
            }
        }
        
        return sb.toString();
    }
    
    public String getModelName()
    {
        return modelName;
    }
    
    public void setModelName(String modelName)
    {
        this.modelName = modelName;
    }
    
    public String getActionName()
    {
        return actionName;
    }
    
    public void setActionName(String actionName)
    {
        this.actionName = actionName;
    }
    
    public String getClassName()
    {
        return className;
    }
    
    public void setClassName(String className)
    {
        this.className = className;
    }
    
    public String getSuperClassName()
    {
        return superClassName;
    }
    
    public void setSuperClassName(String superClassName)
    {
        this.superClassName = superClassName;
    }
    
    public String getActionDesc()
    {
        return actionDesc;
    }
    
    public void setActionDesc(String actionDesc)
    {
        this.actionDesc = actionDesc;
    }
    
    public String getActionUrl()
    {
        return actionUrl;
    }
    
    public void setActionUrl(String actionUrl)
    {
        this.actionUrl = actionUrl;
    }
    
    public String getVersion()
    {
        return version;
    }
    
    public void setVersion(String version)
    {
        this.version = version;
    }
    
    public List<ParamField> getParams()
    {
        return params;
    }
    
    public void setParams(List<ParamField> params)
    {
        this.params = params;
    }
    
    public ResponseBean getResponse()
    {
        return response;
    }
    
    public void setResponse(ResponseBean response)
    {
        this.response = response;
    }
    
    public String[] getModifyRecodes()
    {
        return modifyRecodes;
    }
    
    public void setModifyRecodes(String[] modifyRecodes)
    {
        this.modifyRecodes = modifyRecodes;
    }
    
    public DeveloperType getAuthor()
    {
        return author;
    }
    
    public void setAuthor(DeveloperType author)
    {
        this.author = author;
    }
    
    public String getMethodName()
    {
        return methodName;
    }
    
    public void setMethodName(String methodName)
    {
        this.methodName = methodName;
    }
    
    public String getUpdateVersion()
    {
        return updateVersion;
    }
    
    public void setUpdateVersion(String updateVersion)
    {
        this.updateVersion = updateVersion;
    }
    
    public String getCreateTime()
    {
        return createTime;
    }
    
    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }
    
    public boolean isDeprecated()
    {
        return deprecated;
    }
    
    public void setDeprecated(boolean deprecated)
    {
        this.deprecated = deprecated;
    }
    
    public String[] getReqParams()
    {
        return reqParams;
    }
    
    public void setReqParams(String[] reqParams)
    {
        this.reqParams = reqParams;
    }
    
    public String getUpdateTime()
    {
        return updateTime;
    }
    
    public void setUpdateTime(String updateTime)
    {
        this.updateTime = updateTime;
    }
    
    public String getUpdateBy()
    {
        return updateBy;
    }
    
    public void setUpdateBy(String updateBy)
    {
        this.updateBy = updateBy;
    }
    
    public String getStopVersion()
    {
        return stopVersion;
    }
    
    public void setStopVersion(String stopVersion)
    {
        this.stopVersion = stopVersion;
    }

    public String getProgress()
    {
        return progress;
    }

    public void setProgress(String progress)
    {
        this.progress = progress;
    }

	public String getRequestMode() {
		return requestMode;
	}

	public void setRequestMode(String requestMode) {
		this.requestMode = requestMode;
	}


    public String getMappingUrl()
    {
        return mappingUrl;
    }


    public void setMappingUrl(String mappingUrl)
    {
        this.mappingUrl = mappingUrl;
    }
    
    
    
    
    
}
