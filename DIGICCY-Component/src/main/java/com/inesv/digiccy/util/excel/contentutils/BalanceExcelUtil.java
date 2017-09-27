package com.inesv.digiccy.util.excel.contentutils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;

import com.inesv.digiccy.util.excel.ExcelTypes;
import com.inesv.digiccy.util.excel.content.BalanceExcelContent;
import com.inesv.digiccy.util.thread.MyTask;
import com.inesv.digiccy.util.thread.TaskListner;
import com.inesv.digiccy.util.thread.ThreadUtil;

public class BalanceExcelUtil implements ExcelUtil {
	private TaskListner<String> listner = new TaskListner<String>() {

		@Override
		public synchronized void rusult(String result) throws Exception {
			if (result != null && !"".equals(result)) {
				String key = userName + "_" + ExcelTypes.balanceType;
				String value = (String) redisTemplate.opsForValue().get(key);
				JSONArray arr = value == null ? new JSONArray() : new JSONArray(value);
				JSONObject job = new JSONObject(result);
				arr.put(job);
				redisTemplate.opsForValue().set(key, arr.toString(), 1, TimeUnit.DAYS);
				Boolean ok = job.getBoolean("executeOk");
				if((ok != null) && ok) {
					batchAddBalance.updateBatchGload(job.getInt("money"));
				}
			}
		}
	};
	private ExcelBatch batchAddBalance;
	private BalanceExcelContent balanceExcelContent;
	private boolean canExecute = true;
	private boolean toMuchGold = true;
	private int maxGold = 0;
	private String goldCode = "";
	private ArrayList<String> message = new ArrayList<String>();
	// redis
	private RedisTemplate<String, Object> redisTemplate;
	private String userName = "";

	public BalanceExcelUtil(Map<String, List<String>> content, RedisTemplate<String, Object> redisTemplate,
			String userName, ExcelBatch batchAddBalance, int maxGold, String goldCode) throws Exception {
		// 初始化数据
		this.maxGold = maxGold;
		this.goldCode = goldCode;
		this.batchAddBalance = batchAddBalance;
		balanceExcelContent = new BalanceExcelContent(content);
		// 检查相关数据并获取信息
		message.addAll(check());
		this.redisTemplate = redisTemplate;
		this.userName = userName;
	}

	private List<String> check() throws Exception {
		List<String> message = new ArrayList<String>();
		// 检查金额是不是1000的整数倍
		List<String> eartens = balanceExcelContent.getColContent("入金金额");
		int glod = 0;
		for (String money : eartens) {
			try {
				int mo = Integer.parseInt(money);
				if (mo % 1000 != 0) {
					canExecute = false;
					message.add("金额不是1000的整数倍：" + money);
				}
				glod += mo;
			} catch (Exception e) {
				canExecute = false;
				message.add("金额不是整数：" + money);
			}
		}
		if (glod <= maxGold) {
			toMuchGold = false;
		}
		// 判断用户是否存在
		List<String> phones = balanceExcelContent.getColContent("入金人电话");
		for (String phone : phones) {
			if (!batchAddBalance.isReg(phone)) {
				canExecute = false;
				message.add("用户不存在" + phone);
			}
		}
		if (toMuchGold == true) {
			canExecute = false;
		}
		return message;
	}

	@Override
	public boolean canExecute() {
		return canExecute;
	}

	@Override
	public int execute(Object params) throws Exception {
		int executeCount = 0;
		if (canExecute) {
			List<String> phones = balanceExcelContent.getColContent("入金人电话");
			final Map<String, String> moneys = balanceExcelContent.toMap("入金人电话", "入金金额");

			for (String phone : phones) {
				final String mphone = phone;
				MyTask<String> task = new MyTask<String>() {

					@Override
					public void run() {
						String result = "";
						result = mphone + "入金" + moneys.get(mphone) + "失败！！！";
						boolean executeSuccess = false;
						int money = 0;
						try {
							money = Integer.parseInt(moneys.get(mphone));
							boolean ok = batchAddBalance.addBalance(mphone, money);
							if (ok) {
								result = mphone + "入金成功，入金" + money;
								executeSuccess = true;
							}
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JSONObject job = new JSONObject();
						try {
							job.put("result", result);
							job.put("executeOk", executeSuccess);
							job.put("phone", mphone);
							job.put("money", money);
						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
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
				task.setListner(listner);
				if (task != null) {
					ThreadUtil.execute(task, 500, TimeUnit.MILLISECONDS);
					executeCount++;
				}
			}
		}
		return executeCount;
	}

	@Override
	public ArrayList<String> getMessage() {
		return message;
	}

	public boolean toMuchGold() {
		return toMuchGold;
	}

	public String getGoldCode() {
		return goldCode;
	}
}
