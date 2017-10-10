package com.inesv.digiccy.util.excel.contentutils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;

import com.inesv.digiccy.bean.RegisterResultBean;
import com.inesv.digiccy.dto.AddressDto;
import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.util.HttpUtil;
import com.inesv.digiccy.util.JsonUtil;
import com.inesv.digiccy.util.StringUtil;
import com.inesv.digiccy.util.excel.ExcelTypes;
import com.inesv.digiccy.util.excel.content.RegExcelContent;
import com.inesv.digiccy.util.thread.MyTask;
import com.inesv.digiccy.util.thread.TaskListner;
import com.inesv.digiccy.util.thread.ThreadUtil;

public class RegExcelUtil implements ExcelUtil {

	QueryRunner queryRunner;

	// 注册接口
	private static String regUrl = "http://" + ExcelTypes.jiekouip + ":" + ExcelTypes.jiekouport + "/reg/addUser.do";
	// 实名认证接口
	private static String cerUrl = "http://" + ExcelTypes.jiekouip + ":" + ExcelTypes.jiekouport
			+ "/voucher/validateCardId.do";
	// excel解析的內容
	private RegExcelContent regContent;

	// 是否能够执行注册
	private boolean canExecuteReg = false;

	// 是否能够执行添加地址
	private boolean canExecuteAddress = false;

	// 已注册用户
	private int curReg = 0;

	// 一共多少用户
	private int totalReg = 0;

	// 信息
	private ArrayList<String> message = new ArrayList<String>();

	// redis
	private RedisTemplate<String, Object> redisTemplate;

	// 操作人
	private String userName;

	private TaskListner listner;

	public RegExcelUtil(Map<String, List<String>> content, QueryRunner queryRunner,
			RedisTemplate<String, Object> redisTemplate, String userName) throws Exception {
		this.regContent = new RegExcelContent(content);
		this.userName = userName;
		this.queryRunner = queryRunner;
		this.redisTemplate = redisTemplate;
		inintListner();
		// 是否能够执行添加地址
		canExecuteAddress = regContent.hasAddressCol();
		// 检验的结果信息
		message.addAll(check());
		if (canExecuteReg == false) {
			canExecuteAddress = false;
		}
	}

	private void inintListner() {
		listner = new TaskListner<String>() {

			@Override
			public synchronized void rusult(String result) throws Exception {
				// 每个用户注册的结果
				if (!"error".equals(result)) {
					String key = userName + "_" + ExcelTypes.regExcelType;
					String value = (String) redisTemplate.opsForValue().get(key);
					JSONArray arr = new JSONArray();
					arr = value == null ? arr : new JSONArray(value);
					try {
						JSONObject job = new JSONObject(result);
						arr.put(job);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("---------putresult----------" + key);
					redisTemplate.opsForValue().set(key, arr.toString(), 1, TimeUnit.DAYS);

				} else {

				}
			}
		};
	}

	private ArrayList<String> check() throws Exception {
		ArrayList<String> message = new ArrayList<String>();
		// 基本的验证
		String s = regContent.checkContent();
		// 基本內容验证成功后验证其他相关的验证
		if ("success".equals(s)) {
			boolean can = true;
			Map<String, String> map = regContent.toMap("注册人电话", "上级电话");
			Collection<String> parentPhones = map.values();
			Set<String> phones = map.keySet();
			totalReg = phones.size();
			Iterator<String> it = phones.iterator();
			while (it.hasNext()) {
				String phone = it.next();
				if (isReg(phone)) {
					message.add("该手机已注册" + phone);
					curReg++;
				}
			}
			for (String phone : parentPhones) {
				if (!isReg(phone)) {
					message.add("上级未注册！   phone:" + phone);
					can = false;
				}
				String inesv = getinesv(phone);
				if (inesv == null || "".equals(inesv)) {
					message.add("上级邀请码不存在    phone:" + phone);
					can = false;
				}
			}
			canExecuteReg = can;
		} else {
			canExecuteReg = false;
			message.add(s);
		}
		return message;
	}

	private int getUserNo(String phone) throws SQLException {
		String sql = "select user_no as userno from t_inesv_user where phone='" + phone + "'";
		List<Long> nos = queryRunner.query(sql, new ColumnListHandler<Long>("userno"));
		if (nos.size() > 0) {
			return Integer.parseInt("" + nos.get(0));
		}
		return 0;
	}

	private boolean isReg(String phone) throws Exception {
		boolean isReg = false;
		String sql = "select * from t_inesv_user where username='" + phone + "'";
		List<InesvUserDto> dtos = queryRunner.query(sql, new BeanListHandler<>(InesvUserDto.class));
		if (dtos.size() > 0) {
			isReg = true;
		}
		return isReg;
	}

	private boolean hasAddress(String phone) throws SQLException {
		boolean has = false;

		String sql = "select a.* from t_inesv_user_address a, t_inesv_user b where a.user_no = b.user_no and b.username="
				+ phone;
		List<AddressDto> addresss = queryRunner.query(sql, new BeanListHandler<AddressDto>(AddressDto.class));
		has = (addresss.size() > 0);
		return has;
	}

	private String getinesv(String phone) throws Exception {
		if (phone == null || "".equals(phone)) {
			return "";
		}

		String sql = "select invite_num as num from t_inesv_user where phone = '" + phone + "'";
		List<String> cols = queryRunner.query(sql, new ColumnListHandler<String>("num"));
		if (cols != null && cols.size() > 0) {
			return cols.get(0);
		}
		return "";
	}

	public ArrayList<String> getMessage() {
		return message;
	}

	public int execute(final Object addAddress) throws Exception {
		if (canExecuteReg) {
			List<String> phones = regContent.getColContent("注册人电话");
			List<String> idCards = regContent.getColContent("身份证号码");
			List<String> names = regContent.getColContent("姓名");
			int count = 0;
			final Map<String, String> parentPhone = regContent.toMap("注册人电话", "上级电话");
			final Map<String, String> address = regContent.toMap("注册人电话", "注册人地址");
			final Map<String, String> userNames = regContent.toMap("注册人电话", "注册人名字");
			for (int i = 0; i < phones.size(); i++) {
				final String mphone = phones.get(i);
				final String idcard = idCards.get(i);
				final String name = names.get(i);
				MyTask<String> task;
				if (!isReg(mphone)) {
					task = new MyTask<String>() {

						@Override
						public void run() {
							// 批量注册
							System.out.println("---------execute---------");
							String result = "";
							boolean addressOK = false;
							try {
								String p = "phone=" + mphone + "&password=123456&invite_num="
										+ getinesv(parentPhone.get(mphone));
								String regResult = HttpUtil.postData(regUrl, p);
								System.out.println("regResult:" + regResult);
								RegisterResultBean registerResultBean = JsonUtil.jsonParseToBean(regResult,
										RegisterResultBean.class);
								if (registerResultBean != null) {
									String code = registerResultBean.getCode();
									if ("100".equals(code) && !StringUtil.isEmpty(idcard) && !StringUtil.isEmpty(name)
											&& StringUtil.isIDCard(idcard)) {// 注册成功且身份证号 名字不为空 且身份证号码是正确的身份证号码
										String cerparam = "Name=" + name + "&cardId=" + idcard + "&userNo="
												+ registerResultBean.getUserNo() + "&startDate=" + "&endDate";
										String cerResult = HttpUtil.postData(cerUrl, cerparam);
										System.out.println("cerResult:" + cerResult);
									}
								}
								if (!hasAddress(mphone) && canExecuteAddress && addAddress instanceof Boolean
										&& new Boolean(true).equals(addAddress) && address != null) {
									String sql = "insert into t_inesv_user_address (user_no,name,phone,address,date) values ("
											+ getUserNo(mphone) + ",'" + userNames.get(mphone) + "','" + mphone + "','"
											+ address.get(mphone) + "',now())";
									int usize = queryRunner.update(sql);
									if (usize > 0) {
										addressOK = true;
									}
									System.out.println(mphone);
									System.out.println("addAddress-------------------");
								}
								try {
									JSONObject job = new JSONObject(regResult.toString());
									job.put("phone", mphone);
									job.put("addressAdd", addressOK);
									result = job.toString();
								} catch (Exception e) {
									JSONObject job = new JSONObject();
									job.put("code", "200");
									job.put("phone", mphone);
									job.put("addressAdd", addressOK);
									job.put("desc", regResult.toString());
									result = job.toString();
								}
							} catch (Exception e) {
								e.printStackTrace();
								try {
									JSONObject job = new JSONObject();
									job.put("code", "200");
									job.put("desc", "error");
									job.put("phone", mphone);
									job.put("addressAdd", addressOK);
									result = job.toString();
								} catch (Exception e1) {
									result = "error";
								}
							}
							if (this.listner != null) {
								try {
									listner.rusult(result);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					};

					count++;
				} else {
					task = new MyTask<String>() {

						@Override
						public void run() {
							JSONObject job = new JSONObject();
							try {
								job.put("code", 300);
								job.put("phone", mphone);
								job.put("addressAdd", false);
								job.put("desc", "已注册");
							} catch (JSONException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
								if (!hasAddress(mphone) && canExecuteAddress && addAddress instanceof Boolean
										&& new Boolean(true).equals(addAddress) && address != null) {
									String sql = "insert into t_inesv_user_address (user_no,name,phone,address,date) values ("
											+ getUserNo(mphone) + ",'" + userNames.get(mphone) + "','" + mphone + "','"
											+ address.get(mphone) + "',now())";
									int usize = queryRunner.update(sql);
									boolean addressOK = false;
									if (usize > 0) {
										addressOK = true;
									}
									job.put("addressAdd", addressOK);
									System.out.println(mphone);
									System.out.println("addAddress-------------------");
								}
							} catch (Exception e) {

							}
							if (this.listner != null) {
								try {
									this.listner.rusult(job.toString());
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					};
				}
				if (task != null) {
					task.setListner(listner);
					ThreadUtil.execute(task, 500, TimeUnit.MILLISECONDS);
				}
			}
			return count;
		} else {
			return 0;
		}
	}

	public boolean canExecute() {
		return canExecuteReg;
	}

	public boolean canExecuteAddress() {
		return canExecuteAddress;
	}

	public int getCurReg() {
		return curReg;
	}

	public int getTotalReg() {
		return totalReg;
	}
}
