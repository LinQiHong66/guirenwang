package com.pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页和返回操作实体
 * @author fangzhenxing
 * time 2017年9月18日15:28:11
 */
public class PaginationDto {
		
	private int currentPageNum = 1;// 当前第几页(默认第一页),---主要用于传递到前台显示  
    private int totalPageNum;// 总页数  
    private int totalCount;// 总记录数  
    private int perPageSize = 10;// 每页显示的记录条数(默认10条)  
  
    private List entitys = new ArrayList();// 记录当前页中的数据条目  
  
    //默认无参构造
    public PaginationDto(){
    	
    }
    
    // 所有参数都进行修改  
    public PaginationDto(int currentPageNum, int totalCount, int perPageSize,  
            List entitys) {  
        this.totalCount = totalCount;  
        this.perPageSize = perPageSize;  
        this.totalPageNum = totalCount % perPageSize == 0 ? totalCount  
                / perPageSize : totalCount / perPageSize + 1;  
        this.entitys = entitys;  
        this.currentPageNum = currentPageNum<1?1:(currentPageNum>totalPageNum?totalPageNum:currentPageNum);//如果当前页小于第一页，则停留在第一页  
    }  
  
    // 使用默认的当前页和每页显示记录条数  
    public PaginationDto( int totalCount, List entitys) {  
        this.totalCount = totalCount;  
        this.totalPageNum = totalCount % perPageSize == 0 ? totalCount  
                / perPageSize : totalCount / perPageSize + 1;  
        this.entitys = entitys;  
        this.currentPageNum = currentPageNum<1?1:(currentPageNum>totalPageNum?totalPageNum:currentPageNum);//如果当前页小于第一页，则停留在第一页  
    }  
    
    /**
     * 获取当前第几页
     * @return
     */
    public int getCurrentPageNum() {  
        return currentPageNum;  
    }  
    
    /**
     * 设置当前第几页
     * @param currentPageNum
     */
    public void setCurrentPageNum(int currentPageNum) {  
        this.currentPageNum = currentPageNum<1?1:currentPageNum;//如果当前页小于第一页，则停留在第一页  
    }  
    
    /**
     * 获取总页数
     * @return
     */
    public int getTotalPageNum() {  
        return totalPageNum;  
    }  
    
    /**
     * 设置总页数
     * @param totalPageNum
     */
    public void setTotalPageNum(int totalPageNum) {  
        this.totalPageNum = totalCount % perPageSize == 0 ? totalCount  
                / perPageSize : totalCount / perPageSize + 1;  
    }  
    
    /**
     * 获取总记录数
     * @return
     */
    public int getTotalCount() {  
        return totalCount;  
    }  
    
    /**
     * 设置总记录数
     * @param totalCount
     */
    public void setTotalCount(int totalCount) {  
        this.totalCount = totalCount;  
    }  
    
    /**
     * 获取每页显示条数
     * @return
     */
    public int getPerPageSize() {  
        return perPageSize;  
    }  
    
    /**
     * 设置每页显示条数
     * @param perPageSize
     */
    public void setPerPageSize(int perPageSize) {  
        this.perPageSize = perPageSize;  
    }  
  
    public List getEntitys() {  
        return entitys;  
    }  
  
    public void setEntitys(List entitys) {  
        this.entitys = entitys;  
    }  
  
   
    
    
    
    @Override  
    public String toString() {  
        return "PageUtil [currentPageNum=" + currentPageNum + ", totalPageNum="  
                + totalPageNum + ", totalCount=" + totalCount  
                + ", perPageSize=" + perPageSize + ", entitys=" + entitys + "]";  
    }  
}
