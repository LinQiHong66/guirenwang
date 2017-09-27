package common.autodoc.process;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.inesv.digiccy.common.autocreate.annotation.AutoDocField;
import common.autodoc.bean.ParamField;
import common.autodoc.bean.ResponseBean;
import common.autodoc.util.Utils;

public class ProcessorHelper
{
    /**
     * 递归地取出响应体
     * 
     * @param resClass
     * @param rfs
     * @param response
     * @return
     */
    protected void loadResponseField(Class<?> resClass, Field[] rfs, ResponseBean response)
    {
        List<ParamField> params = response.getParams();
        if(null == params) params = new ArrayList<ParamField>(); // set field
        // ResponseBean subResBean = null; //set field
        List<ResponseBean> subResBeans = response.getSubBeans();
        if(null == subResBeans) subResBeans = new ArrayList<ResponseBean>(3);
        
        for(Field rf : rfs)
        {
            // 需过滤不是返回的字段，如果字段又为list，再提取子字段
            if(Utils.isFieldHaveGetMethod(resClass, rf))
            {
                // 字段是否是list类型 or set类型
                if(rf.getType().isAssignableFrom(List.class) || rf.getType().isAssignableFrom(Set.class))
                {
                    // System.out.println(rf.getType().getName());
                    String collectionType = rf.getType().getSimpleName();// 字段list类型的要加上list<xxx>
                    
                    ResponseBean subResBean = new ResponseBean();
                    
                    Type type = rf.getGenericType();
                    if(type != null && type instanceof ParameterizedType)
                    {
                        ParameterizedType pt = (ParameterizedType) type;
                        
                        Type tempType = pt.getActualTypeArguments()[0];
                        if(!tempType.equals("T") && tempType instanceof Class)
                        {
                            Class<?> genericClazz = (Class<?>) tempType;// 得到参数泛型的第一个参数的class
                            
                            if(!genericClazz.isAssignableFrom(resClass))
                            {// 加此条件防止死循环递归（子bean是自己类型）
                                Field[] gfs = genericClazz.getDeclaredFields();
                                subResBean.setBeanName(rf.getName());// 字段名称
                                subResBean.setClassName(collectionType + "<" + genericClazz.getSimpleName() + ">");
                                
                                loadResponseField(genericClazz, gfs, subResBean);// 递归
                                subResBeans.add(subResBean);
                            }
                        }
                        
                    }
                }
                else if(isBean(rf))
                {
                    ResponseBean subResBean = new ResponseBean();
                    Class<?> genericClazz = rf.getType();
                    if(!genericClazz.isAssignableFrom(resClass))
                    {
                        Field[] gfs = genericClazz.getDeclaredFields();
                        subResBean.setBeanName(rf.getName());// 字段名称
                        
                        loadResponseField(genericClazz, gfs, subResBean);// 递归
                        subResBeans.add(subResBean);
                    }
                }
                else
                {// 普通字段
                    ParamField pf = new ParamField();
                    pf.setName(rf.getName());
                    pf.setType(rf.getType().getSimpleName());
                    AutoDocField adField = rf.getAnnotation(AutoDocField.class);
                    if(null != adField)
                        pf.setNote(adField.note());
                    else pf.setNote("");
                    params.add(pf);
                    
                }
            }
        }
        
        response.setParams(params);
        response.setSubBeans(subResBeans);
        if(StringUtils.isBlank(response.getClassName())) response.setClassName(resClass.getSimpleName());
    }
    
    /**
     * 是否是一个bean类型 以是否有子属性来判断
     * 
     * @param clazz
     * @return
     */
    protected boolean isBean(Field field)
    {
        Class<?> clazz = field.getType();
        // System.out.println(field.getName());
        Field[] fs = clazz.getDeclaredFields();
        if(fs != null && fs.length > 0)
        {
            for(Field f : fs)
            {
                
            }
        }
        
        return false;
    }
}
