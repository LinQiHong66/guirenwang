package common.autodoc.process;

import java.util.List;
import java.util.Set;

import common.autodoc.bean.DocBean;
import common.autodoc.bean.PackageBean;


public interface Processor {
	/**
	 * 处理控制器类的入参，响应
	 * @param pbSet
	 * @return	文档实体列表
	 */
	public List<DocBean> process(Set<PackageBean> pbSet);
	
	/**
	 * 基础请求参数
	 * @return
	 */
	public List<DocBean> getBaseParams();
}
