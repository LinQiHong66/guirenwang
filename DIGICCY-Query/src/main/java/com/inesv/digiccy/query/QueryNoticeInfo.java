
package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.dto.NoticeDto;
import com.inesv.digiccy.dto.NoticeTypeDto;

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
 * Created by Administrator on 2016/11/9 0009.
 */
@Component
public class QueryNoticeInfo {
	private static Logger logger = LoggerFactory.getLogger(QueryUserInfo.class);

	@Autowired
	private QueryRunner queryRunner;

	
	/** 查询公告类型 */
	public List<NoticeTypeDto> queryNoticeType() {
		List<NoticeTypeDto> noticeTypes = new ArrayList<>();
		String sql = "SELECT *FROM t_notice_type WHERE state =0";
		try {
			noticeTypes = (List<NoticeTypeDto>) queryRunner.query(sql, new BeanListHandler(NoticeTypeDto.class));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noticeTypes;
	}
	
	/*
	 * 查询最新公告
	 */
	public NoticeDto queryNoticeOne(String type) {
		Object param[] = { type };
		String sql = "SELECT * FROM t_inesv_notice WHERE notice_type=? and state != 1 ORDER BY DATE DESC LIMIT 1";
		NoticeDto notice = null;
		try {
			notice = (NoticeDto) queryRunner.query(sql, new BeanHandler(NoticeDto.class), param);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return notice;
	}

	/** 查询公告表 */
	public List<NoticeDto> queryNoticeInfo() {
		List<NoticeDto> noticeDtoList = new ArrayList<>();
		String sql = "select * from t_inesv_notice  where state != 1";
		try {
			noticeDtoList = (List<NoticeDto>) queryRunner.query(sql, new BeanListHandler(NoticeDto.class));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noticeDtoList;
	}

	/** 根据类型查询公告表 */
	public List<NoticeDto> queryNoticeInfoByType(String type) {
		List<NoticeDto> noticeDtoList = new ArrayList<>();
		Integer k = Integer.parseInt(type);
		if (k > 0) {
			String sql = "select * from t_inesv_notice where notice_type = ?  and state != 1";
			Object param[] = { type };
			try {
				noticeDtoList = (List<NoticeDto>) queryRunner.query(sql, new BeanListHandler(NoticeDto.class), type);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			String sql = "select * from t_inesv_notice where notice_nametype=?";
			Object param[] = { -k };
			try {
				noticeDtoList = (List<NoticeDto>) queryRunner.query(sql, new BeanListHandler(NoticeDto.class), param);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return noticeDtoList;
	}

	/** 根据id查询公告表 */
	public NoticeDto queryNoticeInfoById(Integer id) {
		NoticeDto noticeDto = new NoticeDto();
		String sql = "select * from t_inesv_notice where id = ?  and state != 1";
		Object param[] = { id };
		try {
			noticeDto = (NoticeDto) queryRunner.query(sql, new BeanHandler(NoticeDto.class), param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noticeDto;
	}

	/** 根据用户名和手机号码查询 */
	public InesvUserDto getUsernamePhone(String username, String phone) throws SQLException {
		InesvUserDto inesvUserDto = null;
		String sql = "select * from t_inesv_user where username = ? and phone = ? and state != 1";
		Object params[] = { username, phone };
		inesvUserDto = (InesvUserDto) queryRunner.query(sql, new BeanHandler(InesvUserDto.class), params);
		return inesvUserDto;

	}

	/** 查询所有的自定义类型 type:0为后台查询，1为前台查询 */
	public List<NoticeTypeDto> getNameType(int type) {
		if (type == 0) {
			String sql = "select * from t_notice_type where state != 1";
			List<NoticeTypeDto> list = new ArrayList<NoticeTypeDto>();
			try {
				list = queryRunner.query(sql, new BeanListHandler<NoticeTypeDto>(NoticeTypeDto.class));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			return list;
		} else if (type == 1) {
			String sql = "select -1*id as id,name from t_notice_type where state != 1";
			List<NoticeTypeDto> list = new ArrayList<NoticeTypeDto>();
			try {
				list = queryRunner.query(sql, new BeanListHandler<NoticeTypeDto>(NoticeTypeDto.class));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			return list;
		}
		return null;
	}

	public NoticeTypeDto findTypeById(int id) {
		String sql = "select * from t_notice_type where id=? and state != 1";
		Object[] params = { id };
		NoticeTypeDto dto = new NoticeTypeDto();
		try {
			dto = queryRunner.query(sql, new BeanHandler<NoticeTypeDto>(NoticeTypeDto.class), params);
		} catch (Exception e) {
			e.printStackTrace();
			dto = null;
		}
		return dto;
	}
}
