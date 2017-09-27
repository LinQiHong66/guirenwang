package com.lkl.test;

public class MyTest {
	public static void main(String[] args) {
		String key = "湘BG54645165195";
		System.out.println(isTopOrg(key));
	}
	//判断是否为顶级机构
		private static boolean isTopOrg(String invite_num){
			if(invite_num.indexOf("G")!=2){
				return false;
			}
			String provinceString=invite_num.substring(0,invite_num.indexOf("G")-1);
//			if(!provinceAbbreviation.contains(provinceString)){
//				return false;
//			}
			if(!"湘".equals(provinceString)) {
				return false;
			}
			return true;
		}
}
