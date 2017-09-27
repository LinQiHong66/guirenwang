package common.autodoc.analyzer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import common.autodoc.bean.DocBean;
import common.autodoc.bean.PackageBean;
import common.autodoc.process.Processor;
import common.autodoc.process.QuickControllerProcessor;
import common.autodoc.util.Validater;

/**
 * @description: 快够项目的接口分析
 * @author hxc 
 * @date 2014-6-26 上午9:10:29
 */
public class QuickControllerAnalyzer implements Analyzer
{
    public final static String CONTROLLER_PATH = "\\src\\main\\java\\com\\inesv\\digiccy\\controller";
    
    private String sourcePath = new File("").getAbsolutePath();
    
    private Processor processor;
    
    private Set<PackageBean> packageBeanSet;
    
    private List<String> modelList = new ArrayList<String>();
    
    public QuickControllerAnalyzer()
    {
        /**
         * 创建Controller类处理器
         */
        processor = new QuickControllerProcessor();
    }
    
    @Override
    public boolean validate()
    {
        System.out.println(">>>>controller analyzer validating...");
        
        if(!Validater.isRightSourcePath()) return false;
        
        return true;
    }
    
    @Override
    public void analyse()
    {
        Object[] models = getAvailableModelV2();
        packageBeanSet = new HashSet<PackageBean>(models.length);
        
        System.out.println(">>>>获取对象的class");
        Class<?> clazz = null;
        PackageBean pb = null;
        for(Object model : models)
        {
            
            String packageName = (String) model;
            try
            {
                clazz = Class.forName(packageName);
                System.out.println(clazz.getName()+"---------"+clazz.getFields());
                /*if(clazz == null){
                    System.out.println(">>>>packageName path is error=" + packageName);
                    continue;
                }*/
                pb = new PackageBean();
                pb.setClaz(clazz);
                packageBeanSet.add(pb);
                
            }
            catch (ClassNotFoundException e)
            {
                continue;
            }
        }
        System.out.println(">>>>Controller analyse result size=" + packageBeanSet.size());
    }
    
    @Override
    public void process(List<DocBean> resultDocBeanList, List<DocBean> resultBaseParamList)
    {
        
        List<DocBean> docBean = processor.process(packageBeanSet);
        
        if(null == resultDocBeanList) resultDocBeanList = new ArrayList<DocBean>(docBean.size());
        if(null != docBean && !docBean.isEmpty()) resultDocBeanList.addAll(docBean);
        
        List<DocBean> baseParams = processor.getBaseParams();
        if(null != baseParams && !baseParams.isEmpty())
        {
            if(null == resultBaseParamList) resultBaseParamList = new ArrayList<DocBean>(baseParams.size());
            
            resultBaseParamList.addAll(baseParams);
        }
        
    }
    
    private Object[] getAvailableModelV2()
    {
        File dir = new File(sourcePath + File.separator + CONTROLLER_PATH);
        showAllFiles(dir);
        
        return modelList.toArray();
    }
    
    /**
     * @description 递归文件夹dir所有类
     * @author：hxc
     * @createtime 2014-6-27上午11:24:29
     * @param dir
     *            文件夹
     */
    public void showAllFiles(File dir)
    {
        File[] fs = dir.listFiles();
        for(int i = 0; i < fs.length; i++)
        {
            if(fs[i].isDirectory())
            {
                try
                {
                    showAllFiles(fs[i]);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                String absolutePath = fs[i].getAbsolutePath();
                String path = absolutePath.substring(absolutePath.indexOf("com"), absolutePath.length());
                if(path.contains(".java")) path = path.substring(0, path.indexOf(".java"));
                path = path.replace("\\", ".");
                
                if(path.contains("Controller"))
                {
                    modelList.add(path);
                }
            }
        }
    }
    
}
