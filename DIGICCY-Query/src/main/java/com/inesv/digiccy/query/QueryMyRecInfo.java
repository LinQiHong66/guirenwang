package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.dto.MyRecDto;
import com.inesv.digiccy.dto.ResultFunctionDto;
import com.inesv.digiccy.dto.SubCoreDto;
import com.inesv.digiccy.dto.ThreeGenerationOfRecommandDto;
import com.inesv.digiccy.dto.UserInfoDto;
import com.inesv.digiccy.persistence.operation.SubCorePer;
import com.inesv.digiccy.dto.UserInfoDto;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yc on 2016/12/9 0009.
 * 查询我的推荐人表信息
 */
@Component
public class QueryMyRecInfo {

	private static Logger logger = LoggerFactory.getLogger(QueryMyRecInfo.class);
	
    @Autowired
    QueryRunner queryRunner;

    /**
     *查询我的推荐
     */
    public List<MyRecDto> queryMyRecInfoByUserNo(String userNo){
        List<MyRecDto> list = new ArrayList();
        String sql = "SELECT * from t_inesv_rec where user_no = ?";
        Object parmas[] = {userNo};
        try {
            list = (List<MyRecDto>)queryRunner.query(sql,new BeanListHandler(MyRecDto.class),parmas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     *根据推荐码查询用户的
     */
    public UserInfoDto queryUserInfoByInvitNum(String inviteNum){
        UserInfoDto userInfoDto = null;
        String sql = "select * from t_inesv_user where invite_num = ?";
        Object parmas[]={inviteNum};
        try {
            userInfoDto = (UserInfoDto) queryRunner.query(sql,new BeanHandler(UserInfoDto.class),parmas);
        } catch (SQLException e) {
            logger.error("根据推荐码查询用户信息失败");
            e.printStackTrace();
        }
        return userInfoDto;
    }

    /**
     *根据用户的注册手机号获取用户的用户编号userNO
     */
    public UserInfoDto queryUserInfoByUserName(String phone){
        UserInfoDto userInfoDto = null;
        String sql = "select * from t_inesv_user where phone = ?";
        Object parmas[]={phone};
        try {
            userInfoDto = (UserInfoDto) queryRunner.query(sql,new BeanHandler(UserInfoDto.class),parmas);
        } catch (SQLException e) {
            logger.error("根据用户名查询用户信息失败");
            e.printStackTrace();
        }
        return userInfoDto;
    }

    /**
     * 根据用户编号查找该用户三代推荐人编号
     * @param user_no
     */
	public ThreeGenerationOfRecommandDto getUserNoByRecUser(Integer user_no) {
		String sql="select @num1:=IFNULL((select user_no from t_inesv_rec where rec_user=?),0) as 'first',"
				+ "@num2:=IFNULL((select user_no from t_inesv_rec where rec_user = @num1),0) as 'second',"
				+ "@num3:=IFNULL((select user_no from t_inesv_rec where rec_user = @num2),0) as 'third'";
		Object parmas[] = {user_no};
		ThreeGenerationOfRecommandDto threeGenerationOfRecommandInfo = null;
        try {
        	threeGenerationOfRecommandInfo = queryRunner.query(sql,new BeanHandler<ThreeGenerationOfRecommandDto>(ThreeGenerationOfRecommandDto.class),parmas);
		} catch (SQLException e) {
			logger.debug("根据用户编号查找该用户三代推荐人编号信息失败");
			e.printStackTrace();
		}
		return threeGenerationOfRecommandInfo;
	}
	
	 /**
     *根据用户的机构编号获取用户信息
     */
    public UserInfoDto queryUserInfoByOrgCode(String orgCode){
        UserInfoDto userInfoDto = null;
        String sql = "select * from t_inesv_user where org_code = ?";
        Object parmas[]={orgCode};
        try {
            userInfoDto = (UserInfoDto) queryRunner.query(sql,new BeanHandler(UserInfoDto.class),parmas);
        } catch (SQLException e) {
            logger.error("据用户的机构编号获取用户信息失败",e);
        }
        return userInfoDto;
    }
    
    /**
     *根据用户的机构编号获取用户信息
     */
    public UserInfoDto queryUserInfoByOrgParentCode(String orgParentCode){
        UserInfoDto userInfoDto = null;
        String sql = "select * from t_inesv_user where org_code = ?";
        Object parmas[]={orgParentCode};
        try {
            userInfoDto = (UserInfoDto) queryRunner.query(sql,new BeanHandler(UserInfoDto.class),parmas);
        } catch (SQLException e) {
            logger.error("据用户的机构编号获取用户信息失败",e);
        }
        return userInfoDto;
    }
    
    /**
     * create by huguokai date:2016年11月9日10:45:26
     * 根据用户ID得到上级的上级等级
     * @param userNo
     * @return UserInfoDto
     */
     public UserInfoDto getInesvParentUserByUserNo(Integer userNo,String state){
    	 String sql="";
    	 if(state.equals("dayu")) {//机构等级小于父级机构等级
    		 sql = "SELECT * FROM t_inesv_user WHERE org_code = "
    	         		+ "( SELECT org_parent_code FROM t_inesv_user WHERE org_code = "
    	         		+ "( SELECT org_parent_code FROM t_inesv_user WHERE user_no = ?) LIMIT 1) LIMIT 1" ; 
    	 }
    	 if(state.equals("dengyu")) {//机构等级等于父级机构等级
    		 sql = "SELECT * FROM t_inesv_user WHERE org_code = "
    					+ "( SELECT org_parent_code FROM t_inesv_user WHERE org_code = "
    				    + "( SELECT org_parent_code FROM t_inesv_user WHERE org_code = "
    				    + "( SELECT org_parent_code FROM t_inesv_user WHERE user_no = ?) LIMIT 1) LIMIT 1) LIMIT 1";
    	 }
         Object params[] = {userNo};
         UserInfoDto uid = null;
         try {
             uid = queryRunner.query(sql,new BeanHandler<UserInfoDto>(UserInfoDto.class),params);
         } catch (SQLException e) {
             e.printStackTrace();
             logger.error("查询用户信息失败");
         }
         return uid;
     }
     
     /*
 	 * 函数调用
 	 */
 	public ResultFunctionDto queryByFunction(Integer user_no,Integer level_grade) throws Exception{
 		String sql = "SELECT get_level_orgcode(?,?) AS level_user_no";
 		Object params[] = {user_no , level_grade};
 		ResultFunctionDto resultDunctionDto = queryRunner.query(sql, new BeanHandler<ResultFunctionDto>(ResultFunctionDto.class),params);
 		return resultDunctionDto;
 	}
	
	
}
