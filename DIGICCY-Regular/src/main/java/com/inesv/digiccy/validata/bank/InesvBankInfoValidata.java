package com.inesv.digiccy.validata.bank;

import com.inesv.digiccy.api.command.InesvBankinfoCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.InesvBankInfo;
import com.inesv.digiccy.query.QueryBankInfo;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
@Component
public class InesvBankInfoValidata {

	@Autowired
	private CommandGateway commandGateway;

	@Autowired
	private QueryBankInfo queryBankInfo;

	/**
	 * 查询银行地址
	 * 
	 * @return
	 */
	public Map<String, Object> getAllBankInfo(int curPage, int pageItem, String userOrgCode, String bankName,
			String bankUserName, String phone, String userName, String startDate, String endDate) {
		Map<String, Object> map = new HashMap<>();
		List<InesvBankInfo> list = queryBankInfo.getAllBankInfo(curPage, pageItem, userOrgCode, bankName, bankUserName,
				phone, userName, startDate, endDate);
		if (list == null) {
			map.put("code", ResponseCode.FAIL_BILL_INFO);
			map.put("desc", ResponseCode.FAIL_BILL_INFO_DESC);
		} else {
			map.put("total",
					queryBankInfo.getSize(userOrgCode, bankName, bankUserName, phone, userName, startDate, endDate));
			map.put("rows", list);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}
		return map;
	}

	/**
	 * 添加银行地址
	 * 
	 * @param remark_name
	 * @param bank
	 * @param province
	 * @param city
	 * @param branch
	 * @param name
	 * @param bank_num
	 * @return
	 */
	public Map<String, Object> insertBankInfo(String remark_name, String bank, String province, String city,
			String branch, String name, String bank_num, Integer userNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		InesvBankinfoCommand inesvBankinfoCommand = new InesvBankinfoCommand(userNo, remark_name, bank, province, city,
				branch, name, bank_num, new Date(), "insert");
		commandGateway.sendAndWait(inesvBankinfoCommand);
		map.put("code", ResponseCode.SUCCESS);
		map.put("desc", ResponseCode.SUCCESS_DESC);
		return map;
	}

	public Map<String, Object> insertUserBankInfo(String bank, String address, String branch, String name,
			String bank_num, Integer userNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		InesvBankinfoCommand inesvBankinfoCommand = new InesvBankinfoCommand(userNo, "", bank, address, address, branch,
				name, bank_num, new Date(), "insert");
		commandGateway.sendAndWait(inesvBankinfoCommand);
		map.put("code", ResponseCode.SUCCESS);
		map.put("desc", ResponseCode.SUCCESS_DESC);
		return map;
	}

	/**
	 * 修改银行卡地址
	 * 
	 * @param remark_name
	 * @param bank
	 * @param province
	 * @param city
	 * @param branch
	 * @param name
	 * @param bank_num
	 * @return
	 */
	public Map<String, Object> updateBankInfo(Long bankId, Integer userNo, String remark_name, String bank,
			String province, String city, String branch, String name, String bank_num) {
		Map<String, Object> mapupdate = new HashMap<String, Object>();
		InesvBankInfo bankInfo = queryBankInfo.getBankInfoById(bankId);
		if (bankInfo == null) {
			mapupdate.put("code", ResponseCode.FAIL);
			mapupdate.put("desc", ResponseCode.FAIL_DESC);
			return mapupdate;
		}
		if (!userNo.equals(bankInfo.getUser_no())) {
			mapupdate.put("code", ResponseCode.FAIL);
			mapupdate.put("desc", ResponseCode.FAIL_DESC);
			return mapupdate;
		}
		InesvBankinfoCommand inesvBankinfoCommand = new InesvBankinfoCommand(bankId, userNo, remark_name, bank,
				province, city, branch, name, bank_num, bankInfo.getDate(), "update");
		commandGateway.sendAndWait(inesvBankinfoCommand);
		mapupdate.put("code", ResponseCode.SUCCESS);
		mapupdate.put("desc", ResponseCode.SUCCESS_DESC);
		return mapupdate;
	}

	/**
	 * 通过银行记录id删除银行卡地址
	 * 
	 * @param id
	 * @return
	 */
	public Map<Object, Object> deleteBankInfo(Long id, Integer userNo) {
		Map<Object, Object> mapdelete = new HashMap<Object, Object>();
		InesvBankInfo bankInfo = queryBankInfo.getBankInfoById(id);
		if (bankInfo == null || bankInfo.getUser_no() != userNo) {
			mapdelete.put("code", ResponseCode.FAIL);
			mapdelete.put("desc", ResponseCode.FAIL_DESC);
			return mapdelete;
		}
		try {
			InesvBankinfoCommand inesvBankinfoCommand = new InesvBankinfoCommand(id, bankInfo.getUser_no(),
					bankInfo.getRemark_name(), bankInfo.getBank(), bankInfo.getProvince(), bankInfo.getCity(),
					bankInfo.getBranch(), bankInfo.getName(), bankInfo.getBank_num(), bankInfo.getDate(), "delete");
			commandGateway.sendAndWait(inesvBankinfoCommand);
			mapdelete.put("code", ResponseCode.SUCCESS);
			mapdelete.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			mapdelete.put("code", ResponseCode.FAIL);
			mapdelete.put("desc", ResponseCode.FAIL_DESC);
		}
		return mapdelete;
	}

	/**
	 * 删除银行卡地址
	 * 
	 * @return
	 * 
	 * 		public Map<Object, Object> deleteBankInfo(Integer id,Integer
	 *         userNo,String remark_name, String bank, String province, String city,
	 *         String branch, String name, String bank_num){ Map<Object, Object>
	 *         mapdelete = new HashMap<Object, Object>(); try { InesvBankinfoCommand
	 *         inesvBankinfoCommand = new
	 *         InesvBankinfoCommand(id,userNo,remark_name, bank, province, city,
	 *         branch, name, bank_num,new Date(),"delete");
	 *         commandGateway.sendAndWait(inesvBankinfoCommand);
	 *         mapdelete.put("code",ResponseCode.SUCCESS);
	 *         mapdelete.put("desc",ResponseCode.SUCCESS_DESC); }catch (Exception
	 *         e){ mapdelete.put("code",ResponseCode.FAIL);
	 *         mapdelete.put("desc",ResponseCode.FAIL_DESC); } return mapdelete; }
	 */

	/**
	 * 查询银行卡信息
	 * 
	 * @param userNo
	 * @return
	 */
	public Map<Object, Object> getBankInfo(String userNo) {
		Map<Object, Object> getBankinfo = new HashMap<Object, Object>();
		List<InesvBankInfo> bankInfoList = queryBankInfo.getBankInfo(userNo);
		if (bankInfoList != null) {
			for (InesvBankInfo bank : bankInfoList) {
				if (bank.getBank_num().length() > 10) {
					System.out.println("==========================111");
					bank.setBank_num(bank.getBank_num().substring(0, 4) + "*****" + bank.getBank_num()
							.substring(bank.getBank_num().length() - 4, bank.getBank_num().length()));
				}
			}
			getBankinfo.put("bankInfoList", bankInfoList);
			getBankinfo.put("code", ResponseCode.SUCCESS);
			getBankinfo.put("desc", ResponseCode.SUCCESS_DESC);
		} else {
			getBankinfo.put("code", ResponseCode.FAIL);
			getBankinfo.put("desc", ResponseCode.FAIL_DESC);
		}
		return getBankinfo;
	}
}
