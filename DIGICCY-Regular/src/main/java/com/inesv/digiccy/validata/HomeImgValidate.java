package com.inesv.digiccy.validata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.api.command.HomeImgCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.HomeImgDto;
import com.inesv.digiccy.query.QueryHomeImg;

/**
 * 
 * @author Liukeling
 *
 */
@Component
public class HomeImgValidate {
	@Autowired
	QueryHomeImg queryHomeImg;

	@Autowired
	CommandGateway commandGateway;
	//获取所有的轮播图
	public Map<String, Object> getAllHomeImg() {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<HomeImgDto> imgs = queryHomeImg.getAllhomeImg();
			map.put("imgResult", imgs);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
			map.put("imgResult", "none");
		}
		return map;
	}
	//添加轮播图
	public Map<String, Object> addHomeImg(String imgUrl, String imgInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		HomeImgCommand command = new HomeImgCommand(0, imgInfo, imgUrl, "insert");
		try {
			commandGateway.send(command);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("result", "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", "插入数据失败！！");
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
	//根据id查询轮播图
	public Map<String, Object> findHomeImgById(int id){
		Map<String, Object> map = new HashMap<>();
		HomeImgDto img = queryHomeImg.findById(id);
		if(img != null){
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("img", img);
		}else{
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
			map.put("img", "");
		}
		return map;
	}
	//修改图片
	public Map<String, Object> modifyHomeImg(HomeImgDto imgDto){
		Map<String, Object> map = new HashMap<String, Object>();
		HomeImgCommand command = new HomeImgCommand(imgDto.getId(), imgDto.getImgDescription(), imgDto.getImgUrl(), "update");
		try {
			commandGateway.send(command);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("result", "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", "插入数据失败！！");
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
	//删除图片
	public Map<String, Object> delHomeImg(int id){
		HashMap<String, Object> map = new HashMap<String, Object>();
		HomeImgCommand command = new HomeImgCommand();
		command.setId(id);
		command.setOperation("delete");
		try {
			commandGateway.send(command);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("result", "删除成功!!");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", "删除数据失败！！");
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
}
