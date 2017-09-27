package com.inesv.digiccy.back.controller;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.inesv.digiccy.dto.CommandRedDto;
import com.inesv.digiccy.validata.CommandRedValidata;
import com.inesv.digiccy.validata.CrowdFundingValidata;
import com.inesv.digiccy.validata.coin.CoinValidata;

/**
 * Created by Administrator on 2016/06/05 0016.
 */
@Controller
@RequestMapping("/commandRed")
public class CommandRedController{
	
	@Autowired
	CrowdFundingValidata crowdFundingValidata;
	@Autowired
	CommandRedValidata commandRedValidata;
	@Autowired
	CoinValidata coinValidata;
	
	@RequestMapping(value = "gotoCommandRed",method = RequestMethod.GET)
    public String gotoCommandRed(){
        return "/commandRed/commandRed";
    }
	
	/*@RequestMapping(value = "gotoCommandRedInfo",method = RequestMethod.GET)
    public String gotoCommandRedInfo(String command_no,ModelAndView model){
		mode
        return "/commandRed/commandRedInfo";
    }*/
	@RequestMapping(value = "gotoCommandRedInfo",method = RequestMethod.GET)
    public ModelAndView gotoCommandRedInfo(String command_no){
		Map<String,Object> map= new HashMap<>();
		map.put("command_no", command_no);
        return new ModelAndView("/commandRed/commandRedInfo",map);
    }
	
	@RequestMapping(value = "gotoAdd",method = RequestMethod.GET)
    public String gotoAdd(){
        return "/commandRed/add";
    }
	
	@RequestMapping(value = "gotoEdit",method = RequestMethod.GET)
	@ResponseBody
    public ModelAndView gotoEdit(String command_no){
		Map<String,Object> map = new HashMap<>();
		map.put("commandRed", commandRedValidata.validataCommandRed(command_no));
        return new ModelAndView("/commandRed/edit",map);
    }
	/**
     * 得到所有口令红包的信息
     * @return
     */
    @RequestMapping(value = "getAllCommandRed" ,method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getAllCrowdFunding(){
        Map<String,Object> commandRedMap = commandRedValidata.validataAllCommandRed();
        return commandRedMap;
    }
    /**
     * 得到口令红包的信息
     * @return
     */
    @RequestMapping(value = "getCommandRed" ,method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getCommandRed(String command_no){
        Map<String,Object> commandRedMap = commandRedValidata.validataCommandRed(command_no);
        return commandRedMap;
    }
    /**
     * 得到指定口令红包的信息
     * @return
     */
    @RequestMapping(value = "getCommandRedInfo" ,method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getCommandRedInfo(String command_no){
        Map<String,Object> commandRedMap = commandRedValidata.validataCommandRedInfo(command_no);
        return commandRedMap;
    }
	/**
     * 增加口令红包的信息
     * @return
	 * @throws Exception 
     */
    @RequestMapping(value = "addCommandRed" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addCommandRed(@RequestParam String command_name,@RequestParam Integer command_prize_type,@RequestParam String command_name_price,@RequestParam String command_remark,@RequestParam Integer number,HttpServletRequest request) throws Exception{
    	Map<String,Object> crowdFundingMap = new HashMap<String,Object>();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Integer num1 = (int) (Math.random()*9000+1000);
        String command_no = sdf.format(new Date())+num1;
        Integer state = 0;
        for(int i=0;i<number;i++){
        	Integer num2 = (int) (Math.random()*9000+1000);
        	String command_number = command_no + i + num2 + i; 
        	crowdFundingMap = commandRedValidata.validataAddCommandRed(command_no,command_name,command_prize_type,command_name_price,command_number,command_remark,state,new Date());
        }
    	return crowdFundingMap;
    }
    /**
     * 修改口令红包的信息
     * @return
	 * @throws Exception 
     */
    @RequestMapping(value = "editCommandRed" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> editCommandRed(@RequestParam String command_no,@RequestParam String command_name,@RequestParam Integer command_prize_type,@RequestParam String command_name_price,@RequestParam String command_remark,HttpServletRequest request) throws Exception{
    	Map<String,Object> crowdFundingMap = new HashMap<String,Object>();
    	crowdFundingMap = commandRedValidata.validataEditCommandRed(command_no,command_name,command_prize_type,command_name_price,command_remark);
    	return crowdFundingMap;
    }
    /**
     * 删除口令红包的信息
     * @return
	 * @throws Exception 
     */
    @RequestMapping(value = "deleteCommandRed" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteCommandRed(@RequestParam String command_no) throws Exception{
        Map<String,Object> crowdFundingMap = commandRedValidata.validataDeleteCommandRed(command_no);
        return crowdFundingMap;
    }
    /**
     * 删除指定口令红包的信息
     * @return
	 * @throws Exception 
     */
    @RequestMapping(value = "deleteCommandRedInfo" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteCommandRedInfo(@RequestParam String id) throws Exception{
        Map<String,Object> crowdFundingMap = commandRedValidata.validataDeleteCommandRedInfo(id);
        return crowdFundingMap;
    }
    /**
     * 导出excel文件
     * @return
	 * @throws Exception 
     */
    @RequestMapping("outputCommandRed")
	public String outputCommandRed(String command_no,HttpServletResponse response){
		OutputStream os;
		WritableWorkbook book;
		try {
			String emails = "口令红包列表" ;
			response.setContentType("application/vnd.ms-excel"); //保证不乱码
			String fileName = emails + ".xls";  
			response.setHeader("Content-Disposition", "attachment;"  
			        + " filename="  
			        + new String(fileName.getBytes(), "ISO-8859-1"));  
			os = response.getOutputStream();  
			
            book = Workbook.createWorkbook(os);  
			WritableSheet sheet1 = book.createSheet("第一页", 0);
			Label label1 = new Label(0, 0, "红包项目名称");
			sheet1.addCell(label1); 
			Label label2 = new Label(1, 0, "红包奖品类型");  
			sheet1.addCell(label2);
			Label label3 = new Label(2, 0, "奖品类型/货币金额");  
			sheet1.addCell(label3);
			Label label4 = new Label(3, 0, "口令随机数");  
			sheet1.addCell(label4);
			Label label5 = new Label(4, 0, "状态");  
			sheet1.addCell(label5);
			Label label6 = new Label(5, 0, "时间");  
			sheet1.addCell(label6);
			List<CommandRedDto> commandRedList = (List<CommandRedDto>) commandRedValidata.validataCommandRedInfo(command_no).get("data"); 
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//emailLists=this.back.emailLists() ;
			for(int i=1; i<commandRedList.size()+1; i++){    //控制行数（从第二行开始）  
			    for(int j=0 ;j<10 ;j++) { //控制列数  
			        if(j==0) {
			            Label label = new Label(j, i,commandRedList.get(i-1).getCommand_name());  
			            sheet1.addCell(label);  
			        }  
			        if(j==1) {
			        	String prize_type = null;
			        	if(commandRedList.get(i-1).getCommand_prize_type()==0){
			        		prize_type = "人民币";
			        	}
			        	if(commandRedList.get(i-1).getCommand_prize_type()==1){
			        		prize_type = "奖品";
			        	}
			            Label label = new Label(j, i,prize_type);  
			            sheet1.addCell(label);  
			        }  
			        if(j==2) {  
			        	Label label = new Label(j, i, commandRedList.get(i-1).getCommand_name_price());  
			            sheet1.addCell(label);  
			        } 
			        if(j==3) {  
			        	Label label = new Label(j, i, commandRedList.get(i-1).getCommand_number());  
			            sheet1.addCell(label);  
			        } 
			        if(j==4) {  
			        	String state = null;
			        	if(commandRedList.get(i-1).getState()==0){
			        		state = "进行中";
			        	}
			        	if(commandRedList.get(i-1).getState()==1){
			        		state = "已结束";
			        	}
			        	if(commandRedList.get(i-1).getState()==2){
			        		state = "已删除";
			        	}
			            Label label = new Label(j, i,state);  
			            sheet1.addCell(label);  
			        }
			        if(j==5) {  
			            Label label = new Label(j, i, sf.format(commandRedList.get(i-1).getDate()));  
			            sheet1.addCell(label);  
			        }
			    }         
			}
			book.write();  
            book.close();  
            os.close();
		} catch (Exception e) {  
	        System.out.println("生成信息表(Excel格式)时出错：");  
	        e.printStackTrace();  
	    }  
		return null;
	}
	
}
