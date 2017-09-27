package common.autodoc.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import common.autodoc.bean.PackageBean;

/**
 * 操作xml的工具
 * 
 * @author dixon
 * @date 2013/10/30
 */
public class XmlUtil
{
    private static String ATTR_NAME = "name";
    
    private static String ATTR_NAMESPACE = "namespace";
    
    private static String ATTR_CLASS = "class";
    
    private static String ATTR_TYPE = "type";
    
    private static String ATTR_METHOD = "method";
    
    public static List<PackageBean> readFromXml(File f) throws JDOMException, IOException
    {
        if(f == null) throw new NullPointerException("路径不正确：");
        
        SAXBuilder builder = new SAXBuilder();
        
        System.out.println(">>>>读取" + f.getAbsolutePath() + "文件内容......");
        long readStart = System.currentTimeMillis();
        
        Document document = builder.build(f);
        
        Element root = document.getRootElement(); // root element is "struts"
        
        List<Element> packageList = root.getChildren("package");
        
        System.out.println(">>>>package节点数：" + packageList.size());
        
        System.out.println(">>>>开始转换package节点为对象...");
        List<PackageBean> beanList = new ArrayList<PackageBean>(200);
        
        for(Element packageElement : packageList)
        {
            String packName = null;
            String namespace = null;
            
            // package属性
            List<Attribute> pattrList = packageElement.getAttributes();
            for(Attribute attr : pattrList)
            {
                if(ATTR_NAME.equals(attr.getName())) packName = attr.getValue();
                if(ATTR_NAMESPACE.equals(attr.getName())) namespace = attr.getValue();
            }
            
            // action
            List<Element> actionElements = packageElement.getChildren("action");
            for(Element action : actionElements)
            {
                PackageBean pack = new PackageBean();
                
                List<Attribute> aattrList = action.getAttributes();
                // action属性
                for(Attribute attr : aattrList)
                {
                    if(ATTR_NAME.equals(attr.getName())) pack.setActionName(attr.getValue());
                    if(ATTR_CLASS.equals(attr.getName()))
                    {
                        pack.setClazz(Utils.upperCaseFirstWord(attr.getValue()));
                    }
                    if(ATTR_METHOD.equals(attr.getName())) pack.setMethod(attr.getValue());
                }
                
                pack.setName(packName);
                pack.setNamespace(namespace);
                
                beanList.add(pack);
            }
        }
        
        System.out.println(">>>>转换完毕，转换的对象个数：" + beanList.size());
        
        long readEnd = System.currentTimeMillis();
        
        System.out.println(">>>>文档读取时间开销：" + (readEnd - readStart) + "ms");
        // System.out.println("......验证完毕\n");
        return beanList;
    }
    
    /**
     * 获取项目的版本号
     * 
     * @param args
     */
    public static String getVersion()
    {
        // String pom = new File("pom").getAbsolutePath();
        try
        {
            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(new File("pom.xml"));
            
            Element root = document.getRootElement();
            
            List<Element> list = root.getChildren();
            for(Element e : list)
            {
                // System.out.println(e.getName());
                if("version".equals(e.getName())) return e.getText();
            }
            
            // return document.getRootElement().getChild("version").getContent().toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return "1";
    }
    
    /**
     * 获取项目组 sns or o2o
     * 
     * @param args
     */
    public static String getProject()
    {
        try
        {
            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(new File("pom.xml"));
            
            Element root = document.getRootElement();
            
            List<Element> list = root.getChildren();
            for(Element e : list)
            {
                // System.out.println(e.getName());
                if("parent".equals(e.getName()))
                {
                    List<Element> pList = e.getChildren();
                    for(Element e2 : pList)
                    {
                        if("artifactId".equals(e2.getName()))
                        {
                            String artifactId = e2.getText();
                            if(StringUtils.isNotBlank(artifactId))
                            {
                                int index = artifactId.lastIndexOf("-");
                                return artifactId.substring(++index);
                            }
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return "主模块";
    }
    
}
