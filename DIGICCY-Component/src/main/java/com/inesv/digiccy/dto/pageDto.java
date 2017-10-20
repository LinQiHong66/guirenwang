package com.inesv.digiccy.dto;

/**
 * 分页对象
 * @author lin
 *  
 */
public class pageDto {
	
	//当前页码
     private Integer pageNumber;
     //每页记录数
     private Integer pageSize;
     
     //每页的第一条记录
     private Integer firstRecord;
     
     
     public Integer getFirstRecord() {
    	 return  (pageNumber-1)*pageSize;
     }
    
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
     
     
}
