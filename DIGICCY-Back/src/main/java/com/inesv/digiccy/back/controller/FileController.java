package com.inesv.digiccy.back.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.inesv.digiccy.util.excel.ExcelTypes;
import com.inesv.digiccy.util.excel.ExcelUtils;
import com.inesv.digiccy.validata.excel.ExcelValidata;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    ExcelValidata excelValidata;


    //根据模板类型获取相应的模板
    @RequestMapping(value = "/getFile", method = RequestMethod.GET)
    public void testFile(HttpServletResponse response, String fileType) {
        try {
            //根据模板类型获取文件路径
            String filepath = "";
            switch (fileType) {
                case ExcelTypes.regExcelType:
                    filepath = this.getClass().getResource(ExcelTypes.regTemplateFile).toString();
                    break;
                case ExcelTypes.balanceType:
                    filepath = this.getClass().getResource(ExcelTypes.balanceTemplateFile).toString();
                    break;
                default:
                    filepath = "";
                    break;
            }
            System.out.println(filepath + "----------------------------");
            //根据文件路径获取Excel
            ExcelUtils.getExcel(response, filepath.substring(filepath.indexOf("file:") + 5, filepath.length()));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //检查excel
    @RequestMapping(value = "/checkExcel", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> checkExcel(HttpSession session, MultipartFile excelFile, String fileType) throws Exception {
        return excelValidata.checkExcel(excelFile, fileType);
    }

    //执行excel

    @RequestMapping(value = "/executeExcel", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> executeExcel(HttpSession session, @RequestParam MultipartFile excelFile, String fileType, boolean addAddress) throws Exception {
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        String userName = "null";
        try {
            userName = securityContextImpl.getAuthentication().getName();
        } catch (Exception e) {

        }
        return excelValidata.execute(fileType, excelFile, userName, addAddress);
    }

    //获取结果
    @RequestMapping(value = "/getResult", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getResult(HttpSession session, String fileType) throws Exception {
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        String userName = "null";
        try {
            userName = securityContextImpl.getAuthentication().getName();
        } catch (Exception e) {

        }
        return excelValidata.getResult(fileType, userName);
    }

    //获取excel结果
    @RequestMapping(value = "/getResultExcel", method = RequestMethod.GET)
    public void getResultExcel(String fileType, HttpSession session, HttpServletResponse response) throws Exception {
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        String userName = "null";
        try {
            userName = securityContextImpl.getAuthentication().getName();
        } catch (Exception e) {

        }
        Map<String, List<String>> excelResult = excelValidata.getExcelResult(fileType, userName);
        ExcelUtils.export(response, excelResult);
    }

    @RequestMapping(value = "gotobatch", method = RequestMethod.GET)
    public String gotoBatch() {
        return "file/main";
    }

    @RequestMapping(value = "batchReg", method = RequestMethod.GET)
    public String gotoReg() {
        return "file/reg";
    }

    @RequestMapping(value = "batchBlance", method = RequestMethod.GET)
    public String gotoBalance() {
        return "file/balance";
    }
}
