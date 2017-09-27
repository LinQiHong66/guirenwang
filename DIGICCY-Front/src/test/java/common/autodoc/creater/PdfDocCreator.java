package common.autodoc.creater;

import java.util.List;
import common.autodoc.bean.ConfigBean;
import common.autodoc.bean.DocBean;
import common.autodoc.util.ITextPDFUtil;

public class PdfDocCreator implements DocCreator
{
    
    @Override
    public void create(List<DocBean> docBeanList, List<DocBean> baseParamList, ConfigBean configBean)
    {
        ITextPDFUtil.createPDF(docBeanList, baseParamList, configBean);
    }
}
