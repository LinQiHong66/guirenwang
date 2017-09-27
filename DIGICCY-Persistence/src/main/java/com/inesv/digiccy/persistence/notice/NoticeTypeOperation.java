package com.inesv.digiccy.persistence.notice;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.inesv.digiccy.dto.NoticeTypeDto;

@Component
public class NoticeTypeOperation {
	private static Logger logger = LoggerFactory.getLogger(NoticeOperation.class);

	@Autowired
	QueryRunner queryRunner;
	/**添加类型*/
	public void addNoticeType(NoticeTypeDto dto){
		String sql = "insert into t_notice_type (name,state) values (?,0)";
		Object[] params = {dto.getName()};
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//修改
	public void modifyNoticeType(NoticeTypeDto dto){
		String sql = "update t_notice_type set name=? where id=?";
		Object params[] = {dto.getName(), dto.getId()};
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//删除
	@Transactional(rollbackFor={Exception.class, RuntimeException.class})
	public void delNoticeType(int id) throws Exception{
		String sql = "update t_notice_type set state=1 where id=?";
		String sql1 = "update t_inesv_notice set state=1 where notice_nametype=?";
		Object[] params={id};
		queryRunner.update(sql,params);
		queryRunner.update(sql1,params);
	}
}
