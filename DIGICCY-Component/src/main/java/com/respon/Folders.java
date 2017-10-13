package com.respon;

/**
 * 文件相关参数设置类
 * @author 方振兴
 *
 */
public  class  Folders {
	
	/**
	 * 未审批的图片的上传路径
	 */
	public static final  String PENDINGIMG=System.getProperty("file.separator")+"userImgUpload"+System.getProperty("file.separator") +"pending"+System.getProperty("file.separator");
	
	
	/**
	 * 审批通过的的图片的上传路径
	 */
	public static final  String THROUGHIMG=System.getProperty("file.separator")+"userImgUpload"+System.getProperty("file.separator") +"through"+System.getProperty("file.separator");
	
	/**
	 * 审批通过的的图片的上传路径
	 */
	public static final  String NOTTHROUGH=System.getProperty("file.separator")+"userImgUpload"+System.getProperty("file.separator") +"notThrough"+System.getProperty("file.separator");
	
	
}
