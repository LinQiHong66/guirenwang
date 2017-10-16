package com.inesv.digiccy.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.query.QueryUserBasicInfo;
import com.inesv.digiccy.util.QiniuUploadUtil;
import com.inesv.digiccy.validata.UserVoucherValidate;
import com.inesv.digiccy.validata.user.OpUserValidata;
import com.inesv.digiccy.validata.user.UserBasicInfoValidata;

/**
 * 实名认证接口
 * 
 * @author Liukeling
 *
 */
@RequestMapping("voucher")
@Controller
public class UserVoucherController {

	@Autowired
	UserVoucherValidate userVoucherValidate;

	@Autowired
	UserBasicInfoValidata userBasicInfoValidata;

	@Autowired
	OpUserValidata opUserValidata;

	// 获取用户实名认证的信息
	@RequestMapping(value = "/getCardValidateInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getValidateCardInfo(int userNo) {
		return userVoucherValidate.getValidateInfo(userNo);
	}

	@RequestMapping(value = "validateCardId", method = RequestMethod.POST)
	@ResponseBody
	// 判断用户身份证是否正确 正确就通过实名认证
	public Map<String, Object> cardIdIsTrue(String Name, String cardId, int userNo, String startDate, String endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (Name.equals(new String(Name.getBytes("iso-8859-1"), "iso-8859-1"))) {
				Name = new String(Name.getBytes("iso-8859-1"), "utf-8");
			}
			System.out.println(Name);
			// 添加审核记录
			userVoucherValidate.startVoucher(cardId, 1, "", "", "", userNo, Name, "", startDate, endDate);
			// 判断身份证与名字是否一致
			Map<String, Object> map1 = new HashMap<String, Object>();
			// map1.put("code", "100");
			map1 = userVoucherValidate.validateCardId(Name, cardId);
			if ("100".equals(map1.get("code"))) {
				// 确认通过审核
				opUserValidata.modifyVoucher(userNo, 4);
				// 更改用户基本信息的真实名称
				userBasicInfoValidata.modifyRealName(userNo, Name);

				map.put("code", ResponseCode.SUCCESS);
				map.put("desc", ResponseCode.SUCCESS_DESC);
				map.put("msg", "验证成功");
			} else if ("300".equals(map1.get("code"))) {

				map.put("code", ResponseCode.FAIL);
				map.put("desc", ResponseCode.FAIL_DESC);
				map.put("msg", "请求超时，请重试");
			} else {
				map.put("code", ResponseCode.FAIL);
				map.put("desc", ResponseCode.FAIL_DESC);
				map.put("msg", "用戶名和证件号不一致");
			}
		} catch (UnsupportedEncodingException e) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
			map.put("msg", "系统繁忙，请稍后重试");
			e.printStackTrace();
		}
		return map;
	}
	// @RequestMapping(value = "verifyuser", method = RequestMethod.POST)
	// @ResponseBody
	// public Map verifyUser(MultipartFile files, int voucherType, String realName,
	// String voucherId, int userNo) {
	// String imgUrl1 = "";
	// String imgUrl2 = "";
	// String imgUrl3 = "";
	// System.out.println(files);
	// if (files != null && !files.isEmpty()) {
	// String url = saveMultipartFile(files);
	// if (url != null && !"".equals(url)) {
	// if ("".equals(imgUrl1) || imgUrl1 == null) {
	// imgUrl1 = url;
	// } else if ("".equals(imgUrl2) || imgUrl2 == null) {
	// imgUrl2 = url;
	// } else if ("".equals(imgUrl3) || imgUrl3 == null) {
	// imgUrl3 = url;
	// }
	// }
	// }
	//
	// System.out.println(voucherType+"----"+realName+"----"+voucherId+"---"+userNo+"=---"+imgUrl1+"----"+imgUrl2+"----"+imgUrl3);
	// return userVoucherValidate.startVoucher(voucherId, voucherType, imgUrl1,
	// imgUrl2, imgUrl3, userNo, realName,
	// "");
	// }

	// 保存图片的方法
	public String saveMultipartFile(MultipartFile file) {
		String originalName = file.getOriginalFilename();
		String imgUrl = "";
		try {
			String startS = QiniuUploadUtil.getStartStaff();
			imgUrl = startS + QiniuUploadUtil.upLoadImage(file.getInputStream(), originalName);
			if (startS.equals(imgUrl)) {
				imgUrl = "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imgUrl;
	}
}
