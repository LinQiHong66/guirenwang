package com.inesv.digiccy.back.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.inesv.digiccy.back.utils.QiniuUploadUtil;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.HomeImgDto;
import com.inesv.digiccy.validata.ContactValidata;
import com.inesv.digiccy.validata.HomeImgValidate;
import com.inesv.digiccy.validata.LinkValidate;
import com.inesv.digiccy.validata.NoticeValidate;
import com.inesv.digiccy.validata.VedioValidata;


@Controller
@RequestMapping("other")
public class OhterController {
	@Autowired
	HomeImgValidate homeImgValidate;

	@Autowired
	NoticeValidate noticeValidate;

	@Autowired
	LinkValidate linkValidate;

	@Autowired
	VedioValidata vedioValidata;
	@Autowired
	ContactValidata contactValidata;

	@RequestMapping(value = "home_img", method = RequestMethod.GET)
	public String gotoHomeImg() {
		return "/other/homeimg";
	}

	// 到首页视频管理界面
	@RequestMapping(value = "homevodio", method = RequestMethod.GET)
	public String gotoHomeVideo() {
		return "/other/homevodio";
	}

	// 跳到联系地址管理界面
	@RequestMapping(value = "contact", method = RequestMethod.GET)
	public String gotoContact() {
		return "/other/contact";
	}

	// 查询所有联系方式
	@RequestMapping(value = "getAllContact", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAllContact() {
		return contactValidata.getAllContact("", "");
	}

	// 根据id查询联系方式
	@RequestMapping(value = "getContactById", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findContactById(int id) {
		return contactValidata.getAllContact("id", id + "");
	}

	// 修改联系方式
	@RequestMapping(value = "modifyContact", method = RequestMethod.POST)
	public ModelAndView modifyContact(int id, String email, String weixin, MultipartFile wxFile, MultipartFile qqFile,
			String qq, String address, String remark, String telphone, String modifyFile, String authority_account) {
		String wxurl = "";
		String qqurl = "";
		if (modifyFile != null && qqFile != null) {
			String originalName = qqFile.getOriginalFilename();
			originalName = QiniuUploadUtil.createFileName(originalName);
			try {
				qqurl = QiniuUploadUtil.getStartStaff()
						+ QiniuUploadUtil.upLoadImage(qqFile.getInputStream(), originalName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (modifyFile != null && wxFile != null) {
			String originalName = wxFile.getOriginalFilename();
			originalName = QiniuUploadUtil.createFileName(originalName);
			try {
				wxurl = QiniuUploadUtil.getStartStaff()
						+ QiniuUploadUtil.upLoadImage(wxFile.getInputStream(), originalName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new ModelAndView("/other/contact", contactValidata.modifyContact(id, email, weixin, wxurl, qqurl, qq,
				address, remark, modifyFile != null, telphone, authority_account));
	}

	// 删除联系方式
	@RequestMapping(value = "delContact", method = RequestMethod.POST)
	@ResponseBody
	public Map delContact(int id) {
		return contactValidata.delContact(id);
	}

	// 添加联系方式
	@RequestMapping(value = "addContact", method = RequestMethod.POST)
	public ModelAndView addContact(String email, String weixin, MultipartFile wxFile, MultipartFile qqFile, String qq,
			String address, String remark, String telphone, String authority_account) {
		String wxurl = "";
		String qqurl = "";
		if (qqFile != null) {
			String originalName = qqFile.getOriginalFilename();
			originalName = QiniuUploadUtil.createFileName(originalName);
			try {
				qqurl = QiniuUploadUtil.getStartStaff()
						+ QiniuUploadUtil.upLoadImage(qqFile.getInputStream(), originalName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (wxFile != null) {
			String originalName = wxFile.getOriginalFilename();
			originalName = QiniuUploadUtil.createFileName(originalName);
			try {
				wxurl = QiniuUploadUtil.getStartStaff()
						+ QiniuUploadUtil.upLoadImage(wxFile.getInputStream(), originalName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new ModelAndView("/other/contact",
				contactValidata.addContact(email, weixin, wxurl, qqurl, qq, address, remark, telphone, authority_account));
	}

	// 获取首页视频
	@RequestMapping(value = "getHomeVedio", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getHomeVedio() {
		return vedioValidata.getHomeVedio();
	}

	// 上传首页视频
	@RequestMapping(value = "saveVedio", method = RequestMethod.POST)
	public ModelAndView saveHomeVedio(MultipartFile file, HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<>();

		String originalName = file.getOriginalFilename();
		originalName = QiniuUploadUtil.createFileName(originalName);
		String url = "";
		try {
			originalName = QiniuUploadUtil.upLoadImage(file.getInputStream(), originalName + ".mp4");
			url = QiniuUploadUtil.getStartStaff() + originalName;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.putAll(vedioValidata.updateHomeVedio(url));
		return new ModelAndView("/other/homevodio", map);
	}

	// 获取所有图片
	@RequestMapping(value = "getHomeImg", method = RequestMethod.POST)
	@ResponseBody
	public Map getHomeImg() {
		Map<String, Object> map = homeImgValidate.getAllHomeImg();
		return map;
	}

	// 添加图片
	@RequestMapping(value = "addHomeImg", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView addHomeImg(MultipartFile imgFile, String imgInfo) {
		Map map = new HashMap<String, Object>();
		String originalName = imgFile.getOriginalFilename();
		originalName = originalName.lastIndexOf('0') == -1 ? QiniuUploadUtil.createFileName(originalName)
				: QiniuUploadUtil.createFileName(originalName.substring(0, originalName.lastIndexOf('.')));
		String imgUrl = saveMultipartFile(imgFile);
		if (imgUrl != null && !"".equals(imgUrl)) {
			map.putAll(homeImgValidate.addHomeImg(imgUrl, imgInfo));
			map = map == null ? new HashMap<>() : map;
		} else {
			map.put("data", "上传图片失败");
			map.put("code", ResponseCode.FAIL_BILL_INFO);
			map.put("desc", ResponseCode.FAIL_BILL_INFO_DESC);
		}
		return new ModelAndView("/other/homeimg", map);
	}

	// 根据id查询图片
	@RequestMapping(value = "gethomeimgbyid", method = RequestMethod.POST)
	@ResponseBody
	public Map getHomeImgById(int id) {
		return homeImgValidate.findHomeImgById(id);
	}

	// 修改图片
	@RequestMapping(value = "modifyhomeimg", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView modifyHomeImg(int id, MultipartFile imgFile, String imgInfo, String modifyfile) {
		// System.out.println(id + "---" + (file == null) + "----" + imgInfo +
		// "--------" + modifyfile);
		HomeImgDto dto = new HomeImgDto();
		dto.setId(id);
		String imgUrl = "on".equals(modifyfile) && imgFile != null ? saveMultipartFile(imgFile) : "none";
		dto.setImgUrl(imgUrl);
		dto.setImgDescription(imgInfo);
		Map map = homeImgValidate.modifyHomeImg(dto);
		return new ModelAndView("/other/homeimg", map);
	}

	// 删除图片
	@RequestMapping(value = "delhomeimg", method = RequestMethod.POST)
	@ResponseBody
	public Map delHomeImg(int id) {
		System.out.println(id);
		return homeImgValidate.delHomeImg(id);
	}

	// 跳转到友情链接界面
	@RequestMapping(value = "link", method = RequestMethod.GET)
	public String toLink() {
		return "/other/link";
	}

	// 查询链接
	@RequestMapping(value = "getAllLink", method = RequestMethod.POST)
	@ResponseBody
	public Map getAllLink() {
		return linkValidate.getAllLink();
	}

	@RequestMapping(value = "getLinkById", method = RequestMethod.POST)
	@ResponseBody
	public Map getLinkById(int id) {
		return linkValidate.getById(id);
	}

	// 添加链接
	@RequestMapping(value = "addLink", method = RequestMethod.POST)
	public ModelAndView addLink(String linkName, String linkUrl, String linkType) {
		return new ModelAndView("redirect:/other/link", linkValidate.addLink(linkName, linkUrl, linkType));
	}

	// 删除链接
	@RequestMapping(value = "delLink", method = RequestMethod.POST)
	@ResponseBody
	public Map delLink(int id) {
		return linkValidate.delLink(id);
	}

	// 修改链接
	@RequestMapping(value = "updateLink", method = RequestMethod.POST)
	public ModelAndView updateLink(int id, String linkName, String linkUrl, String linkType) {
		System.out.println(linkName + "---" + linkUrl + "---" + linkType);
		return new ModelAndView("/other/link", linkValidate.updateLink(id, linkName, linkUrl, linkType));
	}

//	@RequestMapping(value = "mytest", method = RequestMethod.GET)
//	@ResponseBody
//	public void myTest(HttpServletResponse response){
//		Map<String, List<String>> contact = new HashMap<String, List<String>>();
//		String s1 = "title1";
//		ArrayList<String> as1 = new ArrayList<>();
//		as1.add("test11");
//		as1.add("test12");
//		as1.add("test13");
//
//		String s2 = "title2";
//		ArrayList<String> as2 = new ArrayList<>();
//		as2.add("test21");
//		as2.add("test22");
//		as2.add("test23");
//		contact.put(s1, as1);
//		contact.put(s2, as2);
//		ExcelUtils.export(response, contact);
//	}

	// 获取所有的友情链接
	// @RequestMapping(value="getAllLink",method=RequestMethod.POST)
	// @ResponseBody
	// public Map getAllLink(){
	//
	// }
	// 保存图片的方法
	public String saveMultipartFile(MultipartFile file) {
		String originalName = file.getOriginalFilename();
		String imgUrl = "";
		try {
			imgUrl = QiniuUploadUtil.getStartStaff() + QiniuUploadUtil.upLoadImage(file.getInputStream(), originalName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imgUrl;
	}
}
