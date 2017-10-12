package com.inesv.digiccy.query.integral;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.integral.dto.CompletionDto;
import com.integral.dto.IntegralCompleteDto;
import com.integral.dto.IntegralDetailDto;
import com.integral.dto.IntegralGradeDto;
import com.integral.dto.IntegralRuleDto;
import com.integral.dto.UserImgDto;
import com.pagination.PaginationDto;
import com.respon.R;

/**
 * 查询积分奖励的相关信息
 * @author Administrator
 *
 */
@Component
public class QueryIntegral {

	@Autowired
	QueryRunner queryRunner;
	
	/**
	 * 查询完成任务获取积分状态实体
	 * @param completeDto  完成任务获取积分状态实体
	 * @param paginationDto 分页实体
	 * @return
	 */
	public List<IntegralCompleteDto> queryIntegralComplete(IntegralCompleteDto completeDto,PaginationDto paginationDto){
		List<IntegralCompleteDto> dtosList=new ArrayList<IntegralCompleteDto>();
		ArrayList<Object> paramArr = new ArrayList<>();
		try {
			String sql="select com.id as id  ,com.type as type ,com.number as num	";
			       sql+=" from t_integral_complete as com where 1=1";
			       //id
			       if(completeDto.getId()!=null && !completeDto.getId().equals("")){
			    	   sql+=" and com.id=?";
			    	   paramArr.add(completeDto.getId());
			       }
			       //积分奖励数
			       if(completeDto.getNum()!=null && !completeDto.getNum().equals("")){
			    	   sql+=" and com.number=?";
			    	   paramArr.add(completeDto.getNum());
			       }
			       //类型
			       if(completeDto.getType()!=null && !completeDto.getType().equals("")){
			    	   sql+=" and com.type like ?";
			    	   paramArr.add("%"+completeDto.getType()+"%");
			       }
			       //分页
			         int count=queryRunner.query(sql, new BeanListHandler<>(IntegralCompleteDto.class),paramArr.toArray(new Object[]{})).size();
					 paginationDto.setTotalCount(count);
					 paginationDto.setTotalPageNum(count/paginationDto.getPerPageSize());
			       
			       sql+=" limit ?,?";
			       paramArr.add((paginationDto.getCurrentPageNum()-1)*paginationDto.getPerPageSize());
			       paramArr.add(paginationDto.getPerPageSize());
			       
			        System.out.println("**************sql**********: "+sql);
			        dtosList = queryRunner.query(sql, new BeanListHandler<>(IntegralCompleteDto.class),paramArr.toArray(new Object[]{}));
			       
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtosList;
	}
	
	/**
	 * 查找积分明细
	 * @return
	 */
	public List<IntegralDetailDto> queryIntegralDetail(IntegralDetailDto detailDto,PaginationDto paginationDto){
		List<IntegralDetailDto> detailDtos=new ArrayList<>();	
		ArrayList<Object> paramArr = new ArrayList<>();
		try {
			 String sql="select de.id,de.createTime,de.type,de.number,de.user_id as userId,us.real_name  from";
                    sql+=" t_integral_detail as de LEFT JOIN t_inesv_user as us on de.user_id=us.id where 1=1";
			 if(R.isNull(detailDto.getId())){
				sql+=" and de.id=?"; 
				paramArr.add(detailDto.getId());
			 }
			 
			 if(R.isNull(detailDto.getType())){
				 sql+=" and de.createTime=?";
				 paramArr.add(detailDto.getCreateTime());
			 }
			 
			 if(R.isNull(detailDto.getNumber())){
				 sql+=" and de.number=?";
				 paramArr.add(detailDto.getNumber());
			 }
			 
			 if(R.isNull(detailDto.getType())){
				 sql+=" and de.type=?";
				 paramArr.add(detailDto.getType());
			 }
			 if(R.isNull(detailDto.getUserName())){
				 sql+=" and us.real_name like ?";
				 paramArr.add("%"+detailDto.getUserName()+"%");
			 }
			 if(R.isNull(detailDto.getIdentifier())){
				 sql+=" and de.identifier = ?";
				 paramArr.add(detailDto.getIdentifier());
			 }
			 
			 //分页
			 int count=queryRunner.query(sql, new BeanListHandler<>(IntegralDetailDto.class),paramArr.toArray(new Object[]{})).size();
			 paginationDto.setTotalCount(count);
			 paginationDto.setTotalPageNum(count/paginationDto.getPerPageSize());
			
			 sql+=" limit ?,?";
		     paramArr.add((paginationDto.getCurrentPageNum()-1)*paginationDto.getPerPageSize());
		     paramArr.add(paginationDto.getPerPageSize());
			 
			 System.out.println("**************sql**********: "+sql);
			 detailDtos = queryRunner.query(sql, new BeanListHandler<>(IntegralDetailDto.class),paramArr.toArray(new Object[]{}));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return detailDtos;
	}
	
	/**
	 * 查询等级特权
	 * @return
	 */
	public List<IntegralGradeDto> queryIntegralGrade(IntegralGradeDto gradeDto,PaginationDto paginationDto){
		List<IntegralGradeDto> gradeDtos=new ArrayList<>();
		ArrayList<Object> paramArr = new ArrayList<>();
		try {
			
			String sql="select gr.id,gr.grade,gr.conditions,gr.quicks,gr.speed,gr.additional from t_integral_grade as gr where 1=1";
			if(R.isNull(gradeDto.getId())){
				sql+=" and gr.id=?";
				paramArr.add(gradeDto.getId());
			}
			if(R.isNull(gradeDto.getGrade())){
				sql+=" and gr.grade like ?";
				paramArr.add("%"+gradeDto.getGrade()+"%");
			}
			if(R.isNull(gradeDto.getConditions())){
				sql+=" and gr.conditions=?";
				paramArr.add(gradeDto.getConditions());
			}
			if(R.isNull(gradeDto.getQuicks())){
				sql+=" and gr.quicks=?";
				paramArr.add(gradeDto.getQuicks());
			}
			if(R.isNull(gradeDto.getSpeed())){
				sql+=" and gr.speed=?";
				paramArr.add(gradeDto.getSpeed());
			}
				
			//分页
			int count=queryRunner.query(sql, new BeanListHandler<>(IntegralGradeDto.class),paramArr.toArray(new Object[]{})).size();
			 paginationDto.setTotalCount(count);
			 paginationDto.setTotalPageNum(count/paginationDto.getPerPageSize());
			
			sql+=" limit ?,?";
		    paramArr.add((paginationDto.getCurrentPageNum()-1)*paginationDto.getPerPageSize());
		    paramArr.add(paginationDto.getPerPageSize());
			
			System.out.println("**************sql**********: "+sql);
			gradeDtos = queryRunner.query(sql, new BeanListHandler<>(IntegralGradeDto.class),paramArr.toArray(new Object[]{}));
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return gradeDtos;
	}
	
	/**
	 * 查询积分获取规则
	 * @return
	 */
	public List<IntegralRuleDto> queryIntegralRule(IntegralRuleDto integralRuleDto,PaginationDto paginationDto){
			
		List<IntegralRuleDto> gradeDtos=new ArrayList<>();
		try {
			String sql="select ru.id,ru.instruction,ru.number,ru.type,ru.identifier,ru.conditions,ru.reward"
					+ " from t_integral_rule as ru where 1=1";
			ArrayList<Object> paramArr = new ArrayList<>();
			//通过id查询
			if(R.isNull(integralRuleDto.getId())){
				sql+=" and ru.id=?";
				paramArr.add(integralRuleDto.getId());
			}
			if(R.isNull(integralRuleDto.getInstruction())){
				sql+=" and ru.instruction=?";
				paramArr.add(integralRuleDto.getInstruction());
			}
			if(R.isNull(integralRuleDto.getNumber())){
				sql+=" and ru.number=?";
				paramArr.add(integralRuleDto.getNumber());
			}
			if(R.isNull(integralRuleDto.getType())){
				sql+=" and ru.type like ?";
				paramArr.add("%"+integralRuleDto.getType()+"%");
			}
			if(R.isNull(integralRuleDto.getIdentifier())){
				sql+=" and ru.identifier = ?";
				paramArr.add(integralRuleDto.getIdentifier());
			}
			
			
			//分页
			int count=queryRunner.query(sql, new BeanListHandler<>(IntegralRuleDto.class),paramArr.toArray(new Object[]{})).size();
			 paginationDto.setTotalCount(count);
			 paginationDto.setTotalPageNum(count/paginationDto.getPerPageSize());
			
			
			sql+=" limit ?,?";
		    paramArr.add((paginationDto.getCurrentPageNum()-1)*paginationDto.getPerPageSize());
		    paramArr.add(paginationDto.getPerPageSize());
			
			System.out.println("**************sql**********: "+sql);
			gradeDtos = queryRunner.query(sql, new BeanListHandler<>(IntegralRuleDto.class),paramArr.toArray(new Object[]{}));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return gradeDtos;
	}
	
	/**
	 * 查找当前用忽的任务完成情况
	 * @param completionDto
	 * @return
	 */
	public List<CompletionDto> queryCompletion(String id){
		List<CompletionDto> completions=new ArrayList<>();
		ArrayList<Object> paramArr = new ArrayList<>();
		try {
			
			String sql="select com.id as comId , com.integral_id as integralId , com.user_id as userId from t_user_completion as com"
					+ " where 1=1";
					if(!(R.isNull(id))){
						sql+=" and com.id=?";
						paramArr.add(id);
					}
					System.out.println("**************sql**********: "+sql);
					completions = queryRunner.query(sql, new BeanListHandler<>(CompletionDto.class),paramArr.toArray(new Object[]{}));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return completions;
	}
	
	
	/**
	 * 查找当前用忽的任务完成情况
	 * @param completionDto
	 * @return
	 */
	public List<CompletionDto> queryCompletionByPlete(String userId,String integral_id){
		List<CompletionDto> completions=new ArrayList<>();
		ArrayList<Object> paramArr = new ArrayList<>();
		try {
			
			String sql="select com.id as comId , com.integral_id as integralId , com.user_id as userId from t_user_completion as com"
					+ " where 1=1";
					if(R.isNull(userId)){
						sql+=" and user_id=?";
						paramArr.add(userId);
					}
					if(R.isNull(integral_id)){
						sql+=" and integral_id=?";
						paramArr.add(integral_id);
					}
					System.out.println("**************sql**********: "+sql);
					completions = queryRunner.query(sql, new BeanListHandler<>(CompletionDto.class),paramArr.toArray(new Object[]{}));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return completions;
	}
	
	/**
	 * 获取当天的积分
	 * @param userId
	 * @param identifier
	 * @return
	 */
	public int queryCount(String userId,String identifier){
		
		int number=0;
		try {
			List<IntegralDetailDto> detailDtos=new ArrayList<>();
			String sql="select number from t_integral_detail where user_id=? and identifier=? and createTime like ?";
			ArrayList<Object> arrayList=new ArrayList<>();
			arrayList.add(userId);
			arrayList.add(identifier);
			arrayList.add("%"+getDate()+"%");
			System.out.println("**************sql**********: "+sql);
			detailDtos = queryRunner.query(sql, new BeanListHandler<>(IntegralDetailDto.class),arrayList.toArray(new Object[]{}));
			for(int i=detailDtos.size()-1;i>=0;i--){
				number=number+Integer.parseInt(detailDtos.get(i).getNumber());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return number;
	}
	
	/**
	 * 获取时间
	 * @return
	 */
	public String getDate(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(date);
		return str;
	}
	
	
	/**
	 * 查询所有的手持身份证
	 * @return
	 */
	public List<UserImgDto> queryImgUri(UserImgDto imgDto,PaginationDto paginationDto){
		
		List<UserImgDto> imgDtos=new ArrayList<>();
		
		try {
			
			String sql="select ui.id,ui.uri_front,ui.Refuse_reason,ui.uri_reverse,ui.crate_time,ui.state,ui.user_id,ius.real_name as user_name"
					+ "  from t_user_img as ui LEFT JOIN t_inesv_user as ius ON ius.id=ui.user_id";
			ArrayList<Object> paramArr = new ArrayList<>();
			//通过id查询
			if(R.isNull(imgDto.getId())){
				sql+=" and ui.id=?";
				paramArr.add(imgDto.getId());
			}
			if(R.isNull(imgDto.getState())){
				sql+=" and ui.state=?";
				paramArr.add(imgDto.getState());
			}
			if(R.isNull(imgDto.getUser_id())){
				sql+=" and ius.id=?";
				paramArr.add(imgDto.getUser_id());
			}
			
			//分页
			int count=queryRunner.query(sql, new BeanListHandler<>(UserImgDto.class),paramArr.toArray(new Object[]{})).size();
			 paginationDto.setTotalCount(count);
			 paginationDto.setTotalPageNum(count/paginationDto.getPerPageSize());
			
			
			sql+=" limit ?,?";
		    paramArr.add((paginationDto.getCurrentPageNum()-1)*paginationDto.getPerPageSize());
		    paramArr.add(paginationDto.getPerPageSize());
			
		    System.out.println("**************sql**********: "+sql);
		    imgDtos = queryRunner.query(sql, new BeanListHandler<>(UserImgDto.class),paramArr.toArray(new Object[]{}));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return imgDtos;
	}
	
	
	
	/**
	 * 查询所有的手持身份证,验证当前状态
	 * @return
	 */
	public List<UserImgDto> queryCheckImgUri(String userId){
		
		List<UserImgDto> imgDtos=new ArrayList<>();
		
		try {
			if(userId==null){
				return imgDtos;
			}
			String sql="select  * from  t_user_img where user_id=? ORDER BY crate_time  DESC LIMIT 1 ";
			ArrayList<Object> paramArr = new ArrayList<>();
			paramArr.add(userId);
		    System.out.println("**************sql**********: "+sql);
		    imgDtos = queryRunner.query(sql, new BeanListHandler<>(UserImgDto.class),paramArr.toArray(new Object[]{}));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return imgDtos;
	}
}
