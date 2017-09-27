package common.autodoc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import common.autodoc.analyzer.Analyzer;
import common.autodoc.bean.ConfigBean;
import common.autodoc.bean.DocBean;
import common.autodoc.creater.DocCreator;
import common.autodoc.util.ExcelComparator;

public class AutoDocBuilder
{
    private List<Analyzer> analyzerList;
    
    private List<DocCreator> docCreatorList;
    
    private List<DocBean> docBeanList = new ArrayList<DocBean>(200);
    
    private List<DocBean> baseParamList = new ArrayList<DocBean>(20);
    
    /**
     * 分析生成actionBean
     */
    public void analyse()
    {
        System.out.println(">>Analysing sources for ActionBean...");
        if(analyzerList != null)
        {
            System.out.println(">>>>Find " + analyzerList.size() + " analyzers.");
            for(Analyzer analyzer : analyzerList)
            {
                if(analyzer.validate())
                {
                    analyzer.analyse();
                }
            }
        }
        else
        {
            System.err.println(">>>>Do not find any analyzer.");
        }
        
        System.out.println(">>>>Analyze done... \n");
    }
    
    /**
     * 处理actionBean
     */
    public void processing()
    {
        System.out.println(">>processing to create docbean...");
        if(analyzerList != null)
        {
            for(Analyzer analyzer : analyzerList)
            {
                analyzer.process(docBeanList, baseParamList);
            }
            
            Collections.sort(docBeanList, new ExcelComparator());// 排序
        }
    }
    
    /**
     * 生成文档
     */
    public void createDoc(ConfigBean configBean)
    {
        System.out.println(">>Doc creating...");
        if(docCreatorList != null)
        {
            System.out.println(">>>>Find " + docCreatorList.size() + " doc creator.");
            
            for(DocCreator creator : docCreatorList)
            {
                creator.create(docBeanList, baseParamList, configBean);
            }
        }
        else
        {
            System.err.println(">>>>Do not find any doc creator.");
        }
        
        System.out.println(">>>>Doc created...\n");
    }
    
    /**
     * 添加分析器
     * 
     * @param analyzer
     */
    public void addAnalyzer(Analyzer analyzer)
    {
        if(analyzerList == null) analyzerList = new ArrayList<Analyzer>(2);
        
        analyzerList.add(analyzer);
    }
    
    /**
     * 添加文档生成器
     * 
     * @param docCreator
     */
    public void addDocCreator(DocCreator docCreator)
    {
        if(docCreatorList == null) docCreatorList = new ArrayList<DocCreator>(2);
        
        docCreatorList.add(docCreator);
    }
    
}
