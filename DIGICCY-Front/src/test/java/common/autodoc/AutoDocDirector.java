package common.autodoc;

import common.autodoc.analyzer.Analyzer;
import common.autodoc.analyzer.QuickControllerAnalyzer;
import common.autodoc.bean.ConfigBean;
import common.autodoc.creater.DocCreator;
import common.autodoc.creater.PdfDocCreator;

public class AutoDocDirector
{
    private ConfigBean configBean;
    
    public AutoDocDirector()
    {
        
    }
    
    public AutoDocDirector(ConfigBean configBean)
    {
        this.configBean = configBean;
    }
    
    public void autoDoc()
    {
        AutoDocBuilder builder = new AutoDocBuilder();
        
        Analyzer strutsAnalyzer = new QuickControllerAnalyzer();
        // Analyzer strutsOldAnalyzer = new MerchantStrutsOldAnalyzer();
        
        builder.addAnalyzer(strutsAnalyzer);
        // builder.addAnalyzer(strutsOldAnalyzer);
        
        // Add doc creators
        DocCreator pdfDC = new PdfDocCreator();
        builder.addDocCreator(pdfDC);
        
        // build
        builder.analyse();
        builder.processing();
        builder.createDoc(configBean);
    }
}
