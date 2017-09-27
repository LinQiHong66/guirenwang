package com.inesv.digiccy.persistence.sequence;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.dto.SequenceDto;

@Component
public class SequenceOper {

	private static Logger logger = LoggerFactory.getLogger(SequenceOper.class);

	@Autowired
	private QueryRunner queryRunner;
	
	public void addSequence(String seqName,int step){
		String sql = "insert into t_sequence(seq_name,current_val,step) values(?,?,?)";
		Object params[] = {seqName,0,step};
		try {
			queryRunner.update(sql,params);
		} catch (SQLException e) {
			logger.error("新增序列失败",e);
		}
	}
	
	@Transactional
	public Integer getNextVal(String seqName){
		if(seqName == null || seqName.equals("")) {
			return null;
		}
		try {
			/*
			 * 查看数据与计数器的对比
			 */
			String sql1 = "SELECT id FROM t_inesv_user WHERE org_parent_code = (SELECT org_code FROM t_inesv_user WHERE invite_num = ?)";
			Object params1[] = {seqName};
			List<InesvUserDto> userDto = queryRunner.query(sql1, new BeanListHandler<InesvUserDto>(InesvUserDto.class), params1);
			String sql2 = "SELECT * FROM t_sequence WHERE seq_name LIKE ? ORDER BY id DESC LIMIT 1";
			Object params2[] = {seqName + "-%"};
			SequenceDto seqDto = queryRunner.query(sql2, new BeanHandler<SequenceDto>(SequenceDto.class), params2);
			if(seqDto == null) {
				String sql3 = "SELECT * FROM t_sequence WHERE seq_name = ?";
				Object params3[] = {seqName};
				seqDto = queryRunner.query(sql3, new BeanHandler<SequenceDto>(SequenceDto.class), params3);
			}
			Integer num = 0;
			if(userDto.size() <= seqDto.getCurrent_val()) {
				num = 0;
			}
			if(userDto.size() > seqDto.getCurrent_val()) {
				num = userDto.size() - seqDto.getCurrent_val();
			}
			/*
			 * 修改数据与计数器
			 */
			String findBySeqName1 = "SELECT * FROM t_sequence WHERE seq_name LIKE ? ORDER BY id DESC LIMIT 1";
			Object seqNameParams1[] = {seqName + "-%"};
			SequenceDto sequence1 = queryRunner.query(findBySeqName1, new BeanHandler<SequenceDto>(SequenceDto.class), seqNameParams1);
			if(sequence1 != null) {
				String updateNextValSql="UPDATE t_sequence SET current_val=current_val+step+? WHERE seq_name LIKE ? AND id = ( " + 
					" SELECT t2.ids FROM ( " + 
					" SELECT t1.id AS ids FROM t_sequence t1 WHERE t1.seq_name LIKE ? ORDER BY t1.id DESC LIMIT 1 " + 
					" ) t2" + 
					" ) ";
		        int count=queryRunner.update(updateNextValSql, num , seqName + "-%", seqName + "-%");
		        if(count==0){
		        	logger.error(String.format("未成功找到对应的序列,当前序列名为:%s", seqName));
		        	return null;
		        }
		        sequence1 = queryRunner.query(findBySeqName1, new BeanHandler<SequenceDto>(SequenceDto.class), seqNameParams1);
		        return sequence1.getCurrent_val();
			}else {
				String findBySeqName2 = "SELECT * FROM t_sequence WHERE seq_name = ? ORDER BY id DESC LIMIT 1";
				Object seqNameParams2[] = {seqName};
				String updateNextValSql="UPDATE t_sequence SET current_val=current_val+step+? WHERE seq_name = ?";
			    int count=queryRunner.update(updateNextValSql, num , seqName);
			    if(count==0){
			        logger.error(String.format("未成功找到对应的序列,当前序列名为:%s", seqName));
			        return null;
			    }
			    SequenceDto sequence2 = queryRunner.query(findBySeqName2, new BeanHandler<SequenceDto>(SequenceDto.class), seqNameParams2);
			    return sequence2.getCurrent_val();
			}
        } catch (Exception e) {
            logger.error("获取下一个序列失败，当前序列名称为:"+seqName,e);
            return null;
        }
	}
	/*@Transactional
	public Integer getNextVal(String seqName){
		if(seqName == null || seqName.equals("")) {
			return null;
		}
		String findBySeqName = "SELECT * FROM t_sequence WHERE seq_name LIKE ? ORDER BY id DESC LIMIT 1";
		Object seqNameParams[] = {seqName + "%"};
		String updateNextValSql="UPDATE t_sequence SET current_val=current_val+step WHERE seq_name LIKE ? AND id = ( " + 
				" SELECT t2.ids FROM ( " + 
				" SELECT t1.id AS ids FROM t_sequence t1 WHERE t1.seq_name LIKE ? ORDER BY t1.id DESC LIMIT 1 " + 
				" ) t2" + 
				" ) ";
        try {
        	int count=queryRunner.update(updateNextValSql, seqName + "%", seqName + "%");
        	if(count==0){
        		logger.error(String.format("未成功找到对应的序列,当前序列名为:%s", seqName));
        		return null;
        	}
        	SequenceDto sequence = queryRunner.query(findBySeqName, new BeanHandler<SequenceDto>(SequenceDto.class), seqNameParams);
        	return sequence.getCurrent_val();
        } catch (SQLException e) {
            logger.error("获取下一个序列失败，当前序列名称为:"+seqName,e);
            return null;
        }
	}*/

}
