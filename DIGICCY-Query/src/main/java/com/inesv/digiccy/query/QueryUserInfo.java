package com.inesv.digiccy.query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.AddressDto;
import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.dto.LoginLogDto;
import com.inesv.digiccy.dto.MessageLogDto;
import com.inesv.digiccy.dto.UserVoucherDto;
import com.inesv.digiccy.dto.auth.AuthRoleDto;
import com.inesv.digiccy.dto.auth.ResourceDto;
import com.inesv.digiccy.persistence.integral.IntegralDetailOperation;
import com.inesv.digiccy.query.util.TimeUtil;
import com.inesv.digiccy.util.MD5;
import com.inesv.digiccy.util.StringUtil;
import com.integral.dto.IntegralDetailDto;
import com.integral.dto.IntegralGradeDto;
import com.pagination.PaginationDto;
import com.respon.R;
import com.respon.ResultEncoding;

@Component
public class QueryUserInfo implements UserDetailsService {

	private static Logger log = LoggerFactory.getLogger(QueryUserInfo.class);

	@Autowired
	private QueryRunner queryRunner;

	// 防止并发导致的数据错误
	static ConcurrentHashMap<String, String> userMap = new ConcurrentHashMap<>();

	@Autowired
	private IntegralDetailOperation detailOperation;

	// public UserDetails loginByUsernamePassWord(String username,String
	// password){
	// String sql = "select * from user where name = ? and password = ?";
	// queryResourceURL();
	// Object params[] = {username,password};
	// User user = null;
	// try {
	// UserDto userdto = (UserDto)queryRunner.query(sql,new
	// BeanHandler<UserDto>(UserDto.class),params);
	// Set<GrantedAuthority> grantedAuths = getGrantedAuthorities(userdto);
	// //灏佽鎴恠pring security鐨剈ser
	// return new
	// User(userdto.getName(),userdto.getPassword(),true,true,true,true,grantedAuths);
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// return user;
	// }
	public long getSize(InesvUserDto selectSome) {
		String sql = "select count(ua.user_no) as count from t_inesv_user ua where 1=1";
		List<InesvUserDto> userlist = null;
		ArrayList<Object> params = new ArrayList<>();

		// 用户机构编号
		if (selectSome.getOrg_code() != null && !"".equals(selectSome.getOrg_code())) {
			sql += " and ua.org_code like ?";
			params.add("%" + selectSome.getOrg_code() + "%");
		}
		// 手机号
		if (selectSome.getPhone() != null && !"".equals(selectSome.getPhone())) {
			sql += " and (ua.username like ? or ua.phone like ?)";
			params.add("%" + selectSome.getPhone() + "%");
			params.add("%" + selectSome.getPhone() + "%");
		}

		// 姓名查询
		if (selectSome.getUsername() != null && !"".equals(selectSome.getUsername())) {
			sql += " and (ua.username like ? or ua.real_name like ?)";
			params.add("%" + selectSome.getUsername() + "%");
			params.add("%" + selectSome.getUsername() + "%");
		}
		// 时间区间选择
		if (selectSome.getDate() != null && selectSome.getSpareDate() != null) {
			sql += " and ua.date between ? and ?";
			params.add(selectSome.getDate());
			params.add(selectSome.getSpareDate());
		}
		// 用户类型选择
		if (selectSome.getOrg_type() != null && selectSome.getOrg_type() != -1) {
			sql += " and ua.org_type=?";
			params.add(selectSome.getOrg_type());
		}
		// 状态查询
		if (selectSome.getState() != null && selectSome.getState() != -1) {
			sql += " and ua.state = ?";
			params.add(selectSome.getState());
		}
		try {
			return (long) queryRunner.query(sql, new ColumnListHandler<>("count"), params.toArray(new Object[] {}))
					.get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	/* 娴嬭瘯 */
	public List<InesvUserDto> getAllUsers() {
		String sql = "SELECT * FROM t_inesv_user";
		List<InesvUserDto> user = null;
		try {
			user = (List<InesvUserDto>) queryRunner.query(sql, new BeanListHandler(InesvUserDto.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public List<AuthRoleDto> queryUserRole(Long userid) {
		String sql = "select id,name,description from t_inesv_role where id IN (SELECT role_id FROM t_inesv_user_role WHERE user_id=?)";
		Object params[] = { userid };

		List<AuthRoleDto> role = null;
		try {
			role = (List<AuthRoleDto>) queryRunner.query(sql, new BeanListHandler(AuthRoleDto.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return role;
	}

	/**
	 * 鏍规嵁鐢ㄦ埛鑾峰彇璇ョ敤鎴锋嫢鏈夌殑瑙掕壊
	 * 
	 * @param user
	 * @return
	 */
	private Set<GrantedAuthority> getGrantedAuthorities(InesvUserDto user) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		List<AuthRoleDto> roles = queryUserRole(user.getId());
		if (roles != null) {
			for (AuthRoleDto role : roles) {
				grantedAuthorities.add(new GrantedAuthorityImpl(role.getName()));
			}
		}
		return grantedAuthorities;
	}

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		String sql = "select * from t_inesv_user where username = ?";
		queryResourceURL();
		Object params[] = { s };
		User user = null;
		try {
			InesvUserDto userdto = (InesvUserDto) queryRunner.query(sql, new BeanHandler(InesvUserDto.class), params);

			// Set<GrantedAuthority> grantedAuths =
			// getGrantedAuthorities(userdto);
			Set<GrantedAuthority> grantedAuths = getGrantedAuthorities(userdto);
			// 灏佽鎴恠pring security鐨剈ser
			return new User(userdto.getUsername(), userdto.getPassword(), true, true, true, true, grantedAuths);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public InesvUserDto loadUserByPhoneNumber(String mobile) throws UsernameNotFoundException {
		String sql = "select * from t_inesv_user where username = ?";
		queryResourceURL();
		Object params[] = { mobile };
		InesvUserDto userdto = null;
		try {
			userdto = (InesvUserDto) queryRunner.query(sql, new BeanHandler(InesvUserDto.class), params);
			return userdto;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userdto;
	}

	public InesvUserDto loadUser(String name, String pass) throws UsernameNotFoundException {
		pass = new MD5().getMD5(pass);
		String sql = "select * from t_inesv_user where username = ? and password = ?";
		queryResourceURL();
		Object params[] = { name, pass };
		try {
			InesvUserDto userdto = (InesvUserDto) queryRunner.query(sql, new BeanHandler(InesvUserDto.class), params);

			return userdto;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param selectSome
	 *            查询的字段
	 * @param pageItem
	 *            分页 每页多少条
	 * @param pageNum
	 *            分页 当前第几页
	 * @param orderItem
	 *            排序字段 可以有 countuser和countlogin以及用户表里的任意字段
	 * @param orderType
	 *            排序类型 desc和asc
	 * @return
	 */
	public List<InesvUserDto> getAllUser(InesvUserDto selectSome, int pageItem, int pageNum, String orderItem,
			String orderType) {
		String whereSql = " where 1=1 ";

		List<InesvUserDto> userlist = null;
		ArrayList<Object> params = new ArrayList<>();

		// 用户机构编号
		if (selectSome.getOrg_code() != null && !"".equals(selectSome.getOrg_code())) {
			whereSql += " and ua.org_code like ?";
			params.add("%" + selectSome.getOrg_code() + "%");
		}
		// 手机号
		if (selectSome.getPhone() != null && !"".equals(selectSome.getPhone())) {
			whereSql += " and (ua.username like ? or ua.phone like ?)";
			params.add("%" + selectSome.getPhone() + "%");
			params.add("%" + selectSome.getPhone() + "%");
		}

		// 姓名查询
		if (selectSome.getUsername() != null && !"".equals(selectSome.getUsername())) {
			whereSql += " and (ua.username like ? or ua.real_name like ?)";
			params.add("%" + selectSome.getUsername() + "%");
			params.add("%" + selectSome.getUsername() + "%");
		}
		// 时间区间选择
		if (selectSome.getDate() != null && selectSome.getSpareDate() != null) {
			whereSql += " and ua.date between ? and ?";
			params.add(selectSome.getDate());
			params.add(selectSome.getSpareDate());
		}
		// 用户类型选择
		if (selectSome.getOrg_type() != null && selectSome.getOrg_type() != -1) {
			whereSql += " and ua.org_type=?";
			params.add(selectSome.getOrg_type());
		}
		// 状态查询
		if (selectSome.getState() != null && selectSome.getState() != -1) {
			whereSql += " and ua.state = ?";
			params.add(selectSome.getState());
		}
		String orderBy = "";
		// 排序字段为空则默认根据id排序
		if (orderItem == null || "".equals(orderItem) || "null".equals(orderItem)) {
			orderItem = "ua.user_no";
			orderType = "desc";
		}

		// 如果排序方式为空则按asc排序
		if (orderType == null || "".equals(orderType)) {
			orderType = "asc";
		}

		String sql = "";
		orderBy = " order by " + orderItem + " " + orderType;
		if ("countuser".equals(orderItem)) {
			// 根据用户邀请人数排序
			sql = "select count(ub.org_code) as countuser, ua.username, ua.user_no, ua.real_name, ua.state , ua.org_code, ua.org_parent_code, ua.date, ua.integral from t_inesv_user ua LEFT JOIN (select org_code, org_parent_code from t_inesv_user) ub on ua.org_code=ub.org_parent_code ";
			sql += whereSql + " GROUP BY ua.user_no " + orderBy + " limit ?,?";
		} else if ("countlogin".equals(orderItem)) {
			// 根据用户活跃度排序
			sql = "select count(ul.user_no) as countlogin, ua.username, ua.user_no, ua.real_name, ua.state , ua.org_code, ua.org_parent_code, ua.date, ua.integral from t_inesv_user ua LEFT JOIN (select user_no from t_inesv_login_log) ul on ua.user_no=ul.user_no ";
			sql += whereSql + " GROUP BY ua.user_no " + orderBy + " limit ?,?";
		} else {
			sql = "select ua.username, ua.user_no, ua.real_name, ua.state , ua.org_code, ua.org_parent_code, ua.date, ua.integral from t_inesv_user ua "
					+ whereSql + orderBy + " LIMIT ?,?";
		}

		params.add((pageNum - 1) * pageItem);
		params.add(pageItem);
		System.out.println("getAllUserSql-----------------");

		System.out.println(sql);
		System.out.println(params.toString());
		try {
			userlist = queryRunner.query(sql, new BeanListHandler<InesvUserDto>(InesvUserDto.class),
					params.toArray(new Object[] {}));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("returnlist------------------");
		return userlist;
	}

	public List<InesvUserDto> getUserByPhone(String phone) {
		String sql = "select * from t_inesv_user where state=1 and ";
		List<InesvUserDto> userlist = null;
		ArrayList<Object> params = new ArrayList<>();
		if (phone != null && !"".equals(phone)) {
			sql += " phone like ?";
			params.add("%" + phone + "%");
		}
		sql += " order by user_no desc";
		try {
			userlist = (List<InesvUserDto>) queryRunner.query(sql, new BeanListHandler(InesvUserDto.class),
					params.toArray(new Object[] {}));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userlist;
	}

	/**
	 * 鏌ヨ璁块棶url
	 */
	public HashMap<String, Object> queryResourceURL() {
		String sql = "select id,type,value from t_inesv_resource";

		HashMap<String, Object> resource = null;
		try {
			resource = (HashMap<String, Object>) queryRunner.query(sql, new MapHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resource;
	}

	/**
	 * 鏌ヨ鎵€鏈夎鑹?
	 */
	public List<AuthRoleDto> queryRole() {
		String sql = "select id,name,description from t_inesv_role";

		List<AuthRoleDto> roleList = null;
		try {
			roleList = (List<AuthRoleDto>) queryRunner.query(sql, new BeanListHandler(AuthRoleDto.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roleList;
	}

	/**
	 * 鏌ヨ瑙掕壊瀵瑰簲鐨剈rl
	 */
	public List<ResourceDto> queryRoleResource(Integer roleid) {
		String sql = "select id,type,value from t_inesv_resource where id in (select resource_id from t_inesv_role_resource where role_id = ?)";
		Object params[] = { roleid };
		List<ResourceDto> roleList = null;
		try {
			roleList = (List<ResourceDto>) queryRunner.query(sql, new BeanListHandler(ResourceDto.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roleList;
	}

	// 鑾峰彇鎵€鏈夌殑楠岃瘉鐢ㄦ埛
	public List<UserVoucherDto> getAllVoucher(String filed, String value) {
		String sql = "select voucher_id as id, voucher_cardid as cardId,"
				+ " voucher_type as cardType, voucher_imgurl1 as imgUrl1,"
				+ " voucher_imgurl2 as imgUrl2, voucher_imgurl3 as imgUrl3,"
				+ " voucher_state as state, userNo as userNo,"
				+ " realName as trueName, voucher_mytype as myvoucherType" + " from t_inesv_user_voucher";
		if (filed != null && !"".equals(filed) && value != null && !"".equals(value)) {
			sql += " where " + filed + "=" + value;
		}
		List<UserVoucherDto> vouchers = null;
		try {
			vouchers = queryRunner.query(sql, new BeanListHandler<UserVoucherDto>(UserVoucherDto.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vouchers;
	}

	public InesvUserDto getUserInfoById(Long id) {
		String sql = "select * from t_inesv_user where id = ?";
		Object param[] = { id };
		InesvUserDto userInfo = null;
		try {
			userInfo = queryRunner.query(sql, new BeanHandler<>(InesvUserDto.class), param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userInfo;
	}

	public InesvUserDto getUserInfoByNo(int userNo) {
		String sql = "select * from t_inesv_user where user_no = ?";
		Object param[] = { userNo };
		InesvUserDto userInfo = null;
		try {
			userInfo = queryRunner.query(sql, new BeanHandler<>(InesvUserDto.class), param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userInfo;
	}

	public List<InesvUserDto> getUserInfo(int userNo, String phone, String idcard) {
		Object param[] = null;
		StringBuffer sb = new StringBuffer("select * from t_inesv_user where user_no != ?");
		if (!StringUtil.isEmpty(phone)) {
			if (!StringUtil.isEmpty(idcard)) {
				sb.append(" and (phone=? or certificate_num=?)");
				Object param2[] = { userNo, phone, idcard };
				param = param2;
			} else {
				sb.append(" and phone=?");
				Object param2[] = { userNo, phone };
				param = param2;
			}
		} else {
			if (!StringUtil.isEmpty(idcard)) {
				sb.append(" and  certificate_num=?");
				Object param2[] = { userNo, idcard };
				param = param2;
			}
		}

		List<InesvUserDto> userInfo = null;
		try {
			System.out.println("userNo:" + userNo + ",phone：" + phone + ",idcard:" + idcard + ",sql:" + sb.toString());
			userInfo = queryRunner.query(sb.toString(), new BeanListHandler<InesvUserDto>(InesvUserDto.class), param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userInfo;
	}

	/**
	 * 鏌ヨ鐢ㄦ埛ip
	 * 
	 * @param userNo
	 * @return list
	 */
	public List<LoginLogDto> getLoginLogInfo(Integer userNo) {
		String sql = "select * from t_inesv_login_log where user_no = ? order by date desc";
		Object params[] = { userNo };
		List<LoginLogDto> list = null;
		try {
			list = queryRunner.query(sql, new BeanListHandler<LoginLogDto>(LoginLogDto.class), params);
		} catch (SQLException e) {
			log.error("鏌ヨ鐢ㄦ埛ip澶辫触");
			e.printStackTrace();
		}
		return list;
	}

	/** 鏌ヨ鐢ㄦ埛鎺ㄨ崘鐮? */
	public InesvUserDto queryInviteNum(int userNo) {
		InesvUserDto userInfo = null;
		String sql = "select * from t_inesv_user where user_no = ?";
		Object parmas[] = { userNo };
		try {
			userInfo = queryRunner.query(sql, new BeanHandler<>(InesvUserDto.class), parmas);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userInfo;
	}

	/** 鏌ヨ鐢ㄦ埛鏄惁瀹炲悕璁よ瘉 */
	public UserVoucherDto queryVoucher(int userNo) {
		UserVoucherDto userInfo = null;
		String sql = "select * from t_inesv_user_voucher where userNo = ?";
		Object parmas[] = { userNo };
		try {
			userInfo = queryRunner.query(sql, new BeanHandler<>(UserVoucherDto.class), parmas);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userInfo;
	}

	/** 鏌ヨ鐢ㄦ埛鏄惁濉啓鏀惰揣鍦板潃 */
	public AddressDto queryAddress(int userNo) {
		AddressDto addressDto = null;
		String sql = "select * from t_inesv_user_address where user_no = ?";
		Object parmas[] = { userNo };
		try {
			addressDto = queryRunner.query(sql, new BeanHandler<>(AddressDto.class), parmas);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return addressDto;
	}

	/**
	 * 鍒ゆ柇璇ョ敤鎴锋槸鍚﹀凡娉ㄥ唽
	 * 
	 * @param phone
	 * @return
	 */
	public InesvUserDto getPhoneIsUnique(String phone) {
		InesvUserDto userInfo = null;
		String sql = "select * from t_inesv_user where username = ?";
		Object parmas[] = { phone };
		try {
			userInfo = queryRunner.query(sql, new BeanHandler<>(InesvUserDto.class), parmas);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userInfo;
	}

	/** 鏌ヨ鐢ㄦ埛鎺ㄨ崘鐮? */
	public InesvUserDto queryUserByInviteNum(String invite_num) {
		InesvUserDto userInfo = null;
		String sql = "select * from t_inesv_user where invite_num = ?";
		Object parmas[] = { invite_num };
		try {
			userInfo = queryRunner.query(sql, new BeanHandler<>(InesvUserDto.class), parmas);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userInfo;
	}

	/**
	 * 鑾峰彇鎴戠殑閭€璇蜂汉
	 * 
	 * @param userNo
	 * @return
	 */
	public List<InesvUserDto> getInesvByUserNo(int userNo) {
		String sql = "select u.id,u.username,u.user_no,u.region,u.real_name,u.mail,u.phone,r.rec_user,r.rec_type,r.auth,r.date from t_inesv_user u,t_inesv_rec r where r.rec_user=u.user_no and r.user_no=?";

		Object parmas[] = { userNo };
		try {
			List<InesvUserDto> list = queryRunner.query(sql, new BeanListHandler<InesvUserDto>(InesvUserDto.class),
					parmas);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 鏁版嵁缁熻
	 */
	public InesvUserDto queryUser() {
		InesvUserDto userInfo = null;
		String sql = "	SELECT (SELECT COUNT(username) FROM t_inesv_user) AS username ,"
				+ " (SELECT COUNT(username) FROM t_inesv_user WHERE TO_DAYS(DATE) = TO_DAYS(NOW())) AS phone ,"
				+ " (SELECT COUNT(username) FROM t_inesv_user WHERE org_type = 0) AS password ,"
				+ " (SELECT COUNT(username) FROM t_inesv_user WHERE org_type = 1) AS region ,"
				+ " (SELECT COUNT(username) FROM t_inesv_user WHERE org_type = 2) AS mail ,"
				+ " (SELECT COUNT(username) FROM t_inesv_user WHERE org_type = 3) AS alipay ,"
				+ " (SELECT COUNT(username) FROM t_inesv_user WHERE org_type = 2 AND TO_DAYS(DATE) = TO_DAYS(NOW())) AS state ,"
				+ " (SELECT COUNT(username) FROM t_inesv_user WHERE org_type = 3 AND TO_DAYS(DATE) = TO_DAYS(NOW())) AS invite_num"
				+ " FROM t_inesv_user LIMIT 1";
		try {
			userInfo = queryRunner.query(sql, new BeanHandler<>(InesvUserDto.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userInfo;
	}

	/**
	 * 查询用户ip
	 * 
	 * @param userNo
	 * @return list
	 */
	public List<MessageLogDto> getMessageLogLimitTime(Integer userNo, String startTime, String endTime) {
		String sql = "select * from t_inesv_message_log where user_id = ? and update_time between ? and ? order by update_time desc";
		Object params[] = { userNo, startTime, endTime };
		List<MessageLogDto> list = null;
		try {
			list = queryRunner.query(sql, new BeanListHandler<MessageLogDto>(MessageLogDto.class), params);
		} catch (SQLException e) {
			log.error("查询用户短信记录失败");
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 查询用户在限制时间短的登录次数
	 * 
	 * @param userNo
	 * @param limitTime
	 * @return
	 */
	public List<MessageLogDto> getMessageLogLimitTime(Integer userNo, int limitTime) {
		String startTime = TimeUtil.getStartTime(limitTime);
		String endTime = TimeUtil.getCurrentTime();
		return getMessageLogLimitTime(userNo, startTime, endTime);
	}

	/**
	 * 添加积分数据
	 * 
	 * @param userId
	 * @param number
	 * @return
	 */
	public boolean addIntegralUserI(Long userId, int number) {

		// 防止并发的数据错误
		while (userMap.putIfAbsent(userId.toString(), userId.toString()) != null) {

			try {
				InesvUserDto inesvUserDto = this.getUserInfoById(userId);
				int num = number + Integer.parseInt(inesvUserDto.getIntegral());
				Object[] objects = { num, userId };
				String sql = "update t_inesv_user set integral=? where id=?";
				int i = queryRunner.update(sql, objects);
				if (i > 0) {
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				userMap.remove(userId);
			}

		}

		return false;
	}

	/**
	 * 添加积分数据
	 * 
	 * @param userId
	 * @param number
	 * @return
	 */
	public boolean addIntegral(String userId, int number, String type, String typrCode) {

		try {

			while (userMap.putIfAbsent(userId.toString(), userId.toString()) == null) {
				String is = userMap.get(userId);
				try {
					InesvUserDto inesvUserDto = this.getUserById(userId);
					int num = number + Integer.parseInt(inesvUserDto.getIntegral());
					Object[] objects = { num, userId };
					String sql = "update t_inesv_user set integral=? where id=?";
					int i = queryRunner.update(sql, objects);
					if (i > 0) {
						IntegralDetailDto detailDto = new IntegralDetailDto();
						detailDto.setId(UUID.randomUUID().toString());
						detailDto.setIdentifier(typrCode);
						detailDto.setUserId(userId.toString());
						detailDto.setNumber(String.valueOf(number));
						detailDto.setType(type);
						detailOperation.insert(detailDto);
						return true;
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					userMap.remove(userId);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 获取vip等级
	 * 
	 * @param userId
	 * @return
	 */
	public R queryDegree(Long userId) {
		R r = new R();
		HashMap<String, Object> hashMap = new HashMap<>();
		try {
			Integer number = Integer.parseInt(getUserInfoById(userId).getIntegral());
			hashMap.put("numbers", number);
			String sql = "SELECT grade.* from t_integral_grade as grade ORDER BY (grade.conditions+0)  ASC";
			List<IntegralGradeDto> dtos = new ArrayList<>();
			dtos = queryRunner.query(sql, new BeanListHandler<IntegralGradeDto>(IntegralGradeDto.class));
			for (int i = dtos.size() - 1; i >= 0; i--) {

				if (dtos.get(i).getAdditional().equals("0")) {
					if (number == Integer.parseInt(dtos.get(i).getConditions())) {
						hashMap.put("vip", dtos.get(i).getGrade());
						break;
					}
				}

				if (dtos.get(i).getAdditional().equals("1")) {
					if (number > Integer.parseInt(dtos.get(i).getConditions())) {
						hashMap.put("vip", dtos.get(i).getGrade());
						break;
					}
				}

				if (dtos.get(i).getAdditional().equals("2")) {
					if (number < Integer.parseInt(dtos.get(i).getConditions())) {
						hashMap.put("vip", dtos.get(i).getGrade());
						break;
					}
				}

				if (dtos.get(i).getAdditional().equals("10")) {
					if (number >= Integer.parseInt(dtos.get(i).getConditions())) {
						hashMap.put("vip", dtos.get(i).getGrade());
						break;
					}
				}

				if (dtos.get(i).getAdditional().equals("20")) {
					if (number <= Integer.parseInt(dtos.get(i).getConditions())) {
						hashMap.put("vip", dtos.get(i).getGrade());
						break;
					}
				}

			}
			hashMap.put("grade", dtos);
			r.setData(hashMap);
		} catch (Exception e) {
			r.setCode(ResultEncoding.R_ERR);
			r.setMsg("获取个人积分信息失败");
			e.printStackTrace();
		}
		return r;
	}

	public InesvUserDto getUserById(String id) {
		String sql = "select * from t_inesv_user where id = ?";
		Object param[] = { id };
		InesvUserDto userInfo = null;
		try {
			userInfo = queryRunner.query(sql, new BeanHandler<>(InesvUserDto.class), param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userInfo;
	}
}
