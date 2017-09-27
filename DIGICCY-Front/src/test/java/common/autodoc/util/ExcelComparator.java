package common.autodoc.util;

import java.util.Comparator;
import common.autodoc.bean.DocBean;

public class ExcelComparator implements Comparator<DocBean>
{
    @Override
    public int compare(DocBean o1, DocBean o2)
    {
        if(!o1.getModelName().equals(o2.getModelName()))
        {
            return o1.getModelName().compareTo(o2.getModelName());
        }
        else if(!o1.getActionName().equals(o2.getActionName()))
        {
            return o1.getActionName().compareTo(o2.getActionName());
        }
        else
        {
            return o1.getVersion().compareTo(o2.getVersion());
        }
    }
}
