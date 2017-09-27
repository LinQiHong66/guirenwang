package common.autodoc.analyzer;

import java.util.List;
import common.autodoc.bean.DocBean;

public interface Analyzer
{
    /**
     * 校验文档目录结构等环境是否正常
     * 
     * @return
     */
    public boolean validate();
    
    /**
     * 分析结构，返回需生成文档的可执行action class
     * 
     * @return
     */
    public void analyse();
    
    /**
     * 处理可执行的action class，解析请求入参，响应体等
     */
    public void process(List<DocBean> resultDocBeanList, List<DocBean> resultBaseParamList);
}
