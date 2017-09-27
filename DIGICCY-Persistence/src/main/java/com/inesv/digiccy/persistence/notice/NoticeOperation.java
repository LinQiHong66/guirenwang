package com.inesv.digiccy.persistence.notice;

import com.inesv.digiccy.dto.NoticeDto;
import com.inesv.digiccy.util.ObjectChangeUtil;
import com.inesv.digiccy.util.TableName;
import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * Created by JimJim on 2016/12/13 0013.
 */
@Component
public class NoticeOperation {

	private static Logger logger = LoggerFactory.getLogger(NoticeOperation.class);

	@Autowired
	QueryRunner queryRunner;

	/**
	 * 新增公告
	 * 
	 * @param notice
	 */
	public void addNotice(NoticeDto notice) {
		String sql = new ObjectChangeUtil<NoticeDto>().objectToSql(notice, TableName.T_INESV_NOTICE);
		Object params[] = new ObjectChangeUtil<NoticeDto>().objectToArray(notice);
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 修改公告
	 * 
	 * @param notice
	 */
	public void updateNotice(NoticeDto notice) {
		String sql = "update t_inesv_notice set title = ?,notice_type = ?,context = ?,notice_nametype=? where id =? ";
		Object params[] = { notice.getTitle(), notice.getNotice_type(), notice.getContext(), notice.getNotice_nametype(), notice.getId() };
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("修改公告错误");
			e.printStackTrace();
		}
	}

	/**
	 * 删除公告
	 * 
	 * @param notice
	 */
	public void deleteNotice(Long id) {
		String sql = "delete from t_inesv_notice where id =? ";
		Object params[] = { id };
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("删除公告错误");
			e.printStackTrace();
		}
	}

}
