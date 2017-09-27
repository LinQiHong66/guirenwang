package common.autodoc.process;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.RequestMapping;

import com.inesv.digiccy.common.autocreate.annotation.AutoDocMethod;
import com.inesv.digiccy.common.autocreate.annotation.AutoDocMethodParam;
import com.inesv.digiccy.common.dto.BaseRes;
import common.autodoc.bean.DocBean;
import common.autodoc.bean.PackageBean;
import common.autodoc.bean.ParamField;
import common.autodoc.bean.ResponseBean;
import common.autodoc.util.ExcelComparator;

public class QuickControllerProcessor extends ProcessorHelper implements Processor
{
    // 请求基类Action
    private String actionPackagePath = "com.inesv.digiccy";
    
    public List<DocBean> process(Set<PackageBean> pbSet)
    {
         
        List<DocBean> docBeanList = new ArrayList<DocBean>(pbSet.size());
        
        for(PackageBean pb : pbSet)
        {
            Class<?> clazz = pb.getClaz();
            
            if(clazz.getSimpleName().equals("QuickBaseController")||clazz.getSimpleName().equals("QuickLoginedBaseController")){
                
                continue;
            }
            
            
            if(null == clazz) continue;
            
            if(clazz.getSuperclass() == null) continue;
            //
            Method[] methods = clazz.getDeclaredMethods();
            for(Method method : methods)
            {
                
                List<ParamField> params = new ArrayList<ParamField>();
                String methodName = method.getName();
                
                Class<?>[] carray = method.getParameterTypes();
                
                AutoDocMethodParam adField = method.getAnnotation(AutoDocMethodParam.class);
                if(adField==null){
                	continue;
                }
                
                RequestMapping url = method.getAnnotation(RequestMapping.class);
                System.out.println(url.value().length);
                String mappingUrl=url.value()[0];
                int length=adField.name().split("@@").length;
                
                if(adField.name().equals("")){
                	
                	
                	
                	
                }else{
                    for(int i = 0; i < length; i++)
                    {
                        Class<?> cla = carray[i];
                         
                        String name = adField.name().split("@@")[i];
                        String note = adField.note().split("@@")[i];
                        
                        if(null != adField)
                        {
                            params.add(new ParamField(name, cla.getSimpleName(), note));
                            continue;
                        }
                    }
                }
                
          
                ResponseBean response = new ResponseBean();
                DocBean docBean = new DocBean();
                AutoDocMethod autoDocMethod = method.getAnnotation(AutoDocMethod.class);
                if(autoDocMethod == null) continue;
                
                
                docBean.setMappingUrl(mappingUrl);
                docBean.setMethodName(methodName);
                docBean.setRequestMode(autoDocMethod.requestMode().getDes());
                
                docBean.setModelName(autoDocMethod.model().getDes());
                docBean.setActionName(autoDocMethod.name());
                docBean.setActionDesc(autoDocMethod.description());
                docBean.setAuthor(autoDocMethod.author());
                docBean.setCreateTime(autoDocMethod.createTime());
                
                docBean.setClassName(clazz.getSimpleName());
                if(clazz.getSuperclass() != Object.class)
                {
                    // System.out.println("---------------" +
                    // clazz.getSuperclass().getSimpleName());
                    docBean.setSuperClassName(clazz.getSuperclass().getSimpleName());
                }
                docBean.setVersion(autoDocMethod.cver().getDes());
                docBean.setUpdateVersion(autoDocMethod.uver().getDes());
                
                docBean.setReqParams(autoDocMethod.reqParams());
                docBean.setParams(params); // 属性
                
                
                if(clazz.getSuperclass().getSimpleName().contains("QuickLoginedBaseController")){

                    params.add(new ParamField("token", "String", "会话token"));
                    String ss[]=autoDocMethod.reqParams();
                    String ss1[]=Arrays.copyOf(ss, ss.length+1);
                    ss1[ss.length]="token";
                    docBean.setReqParams(ss1);
                    docBean.setParams(params); // 属性
                } 
                
             
                docBean.setDeprecated(autoDocMethod.deprecated());
                docBean.setActionUrl(url.value()[0]);
                docBean.setModifyRecodes(autoDocMethod.modify());
                docBean.setUpdateTime(autoDocMethod.updateTime());
                docBean.setUpdateBy(autoDocMethod.updateBy().getDes());
                docBean.setStopVersion(autoDocMethod.stopVer().getDes());
                docBean.setProgress(autoDocMethod.progress().getDesc());
                
             
                
                Class<?> dtoClazz = autoDocMethod.dtoClazz();
                setResponse(response, null, dtoClazz);
                docBean.setResponse(response); // 响应吗
                
                docBeanList.add(docBean);
            }
            
        }
        
        Collections.sort(docBeanList, new ExcelComparator());// 排序
        
        return docBeanList;
    }
    
    @Override
    public List<DocBean> getBaseParams()
    {
        // 添加基础信息
        List<DocBean> baseParamList = new ArrayList<DocBean>(20);
        // addBaseProperties(baseParamList);
        // System.out.println(">>>>基础参数bean个数： " + baseParamList.size());
        
        return baseParamList;
    }
        
    
    private void setResponse(ResponseBean response, Field f, Class<?> fieldClass)
    {
        Class<?> superClass = fieldClass.getSuperclass();
        if(null != superClass)
        {
            if(BaseRes.class.isAssignableFrom(superClass))
            {
                response.setBeanName(fieldClass.getName());// 字段名
                response.setClassName(fieldClass.getSimpleName());
                
                // 如果父类不是BaseRes,则把父类的属性添加进来
                if(!BaseRes.class.getSimpleName().equals(superClass.getSimpleName()))
                {
                    setResponse(response, null, superClass);
                }
                
                Field[] rfs = fieldClass.getDeclaredFields();
                loadResponseField(fieldClass, rfs, response);
            }
            else
            {
                
                if(BaseRes.class == fieldClass)
                {
                    setResponse(response, null, superClass);
                    Field[] rfs = fieldClass.getDeclaredFields();
                    loadResponseField(fieldClass, rfs, response);
                }
            }
        }
    }
    
}
