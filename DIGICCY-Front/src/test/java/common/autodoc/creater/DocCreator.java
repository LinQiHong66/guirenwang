package common.autodoc.creater;

import java.util.List;
import common.autodoc.bean.ConfigBean;
import common.autodoc.bean.DocBean;

public interface DocCreator
{
    public void create(List<DocBean> docBeanList, List<DocBean> baseParamList, ConfigBean configBean);
}
