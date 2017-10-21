package com.inesv.digiccy.back.controller;

import com.inesv.digiccy.back.utils.QiniuUploadUtil;
import com.inesv.digiccy.validata.coin.CoinValidata;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JimJim on 2016/11/17 0017.
 */
@Controller
@RequestMapping("/coin")
public class CoinController {

    private static Logger logger = LoggerFactory.getLogger(CoinController.class);

    @Autowired
    CoinValidata          coinValidata;


    @RequestMapping(value = "gotoCoin", method = RequestMethod.GET)
    public String gotoCoin() {
        return "/coin/coin";
    }

    @RequestMapping(value = "getAllCoin", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAllCoin() {

        Map<String, Object> coinMap = coinValidata.getAllCoin();
        return coinMap;
    }

    @RequestMapping(value = "getAllCrowdCoin", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAllCrowdCoin() {
        Map<String, Object> coinMap = coinValidata.getAllCrowdCoin();
        return coinMap;
    }

    @RequestMapping(value = "addCoin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addCoin(@RequestParam String no, @RequestParam String name, @RequestParam String code, @RequestParam String address, @RequestParam String host,
            @RequestParam String post, @RequestParam String wallet_name, @RequestParam String wallet_pwd, @RequestParam String wallet_lockpwd, @RequestParam String buy_poundatge,
            @RequestParam String sell_poundatge, @RequestParam String block, @RequestParam String sell_withdraw_poundatge_one, @RequestParam String sell_withdraw_poundatge_twe,
            @RequestParam String sell_withdraw_poundatge_three) {
        Map<String, Object> coinAndWalletLinkMap = coinValidata.addCoin(Integer.valueOf(no), name, code, address, host, post, wallet_name, wallet_pwd, wallet_lockpwd, new BigDecimal(buy_poundatge),
                new BigDecimal(sell_poundatge), Integer.valueOf(block), new BigDecimal(sell_withdraw_poundatge_one), new BigDecimal(sell_withdraw_poundatge_twe), new BigDecimal(
                        sell_withdraw_poundatge_three));
        return coinAndWalletLinkMap;
    }

    @RequestMapping(value = "updateCoin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateCoin(@RequestParam String no, @RequestParam String name, @RequestParam String code, @RequestParam String address, @RequestParam String host,
            @RequestParam String post, @RequestParam String wallet_name, @RequestParam String wallet_pwd, @RequestParam String wallet_lockpwd, @RequestParam String buy_poundatge,
            @RequestParam String sell_poundatge, @RequestParam String block, @RequestParam String sell_withdraw_poundatge_one, @RequestParam String sell_withdraw_poundatge_twe,
            @RequestParam String sell_withdraw_poundatge_three) {
        Map<String, Object> coinMap = coinValidata.updateCoin(Integer.valueOf(no), name, code, address, host, post, wallet_name, wallet_pwd, wallet_lockpwd, new BigDecimal(buy_poundatge),
                new BigDecimal(sell_poundatge), Integer.valueOf(block), new BigDecimal(sell_withdraw_poundatge_one), new BigDecimal(sell_withdraw_poundatge_twe), new BigDecimal(
                        sell_withdraw_poundatge_three));

        return coinMap;
    }

    @RequestMapping(value = "deleteCoin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteCoin(@RequestParam String no) {
        Map<String, Object> coinMap = coinValidata.deleteCoin(Integer.valueOf(no));
        return coinMap;
    }

    @RequestMapping(value = "changeState", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> changeState(@RequestParam String id, @RequestParam String state) {
        Map<String, Object> coinMap = coinValidata.changeState(Long.valueOf(id), Integer.valueOf(state));
        return coinMap;
    }

    @RequestMapping(value = "startVote", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> startVote(@RequestParam String id, @RequestParam String vote) {
        Map<String, Object> coinMap = coinValidata.startVote(Long.valueOf(id), Integer.valueOf(vote));
        return coinMap;
    }

    @RequestMapping(value = "voteCount", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> voteCount(@RequestParam String id) {
        Map<String, Object> voteMap = coinValidata.getVoteInfoByCoin(Integer.valueOf(id));
        return voteMap;
    }

    @RequestMapping(value = "uploadIcon", method = RequestMethod.POST)
    public ModelAndView uploadIcon(@RequestParam String id, @RequestParam MultipartFile icon, HttpServletResponse rsp, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        ModelMap modelMap = new ModelMap();
        //初始默认返回
        modelMap.put("result", 0);
        boolean flag = false;
        String message = "";
        // 新文件名
        String fileName[] = new String[2];
        System.out.println(">>>>>>>>>>>>>>>fileName>>>>>>> ::" + fileName);
        // 原文件名
        String oldFileName = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");//日期格式

        //上传图片到七牛
        String savePath = "";

        InputStream file = null;
        int i = 0;
        if (!icon.isEmpty()) {

            if (!icon.isEmpty()) {
                //如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
oldFileName = icon.getOriginalFilename();

                // 拼接文件名
                fileName[i] = sdf.format(new Date()) + oldFileName.substring(oldFileName.lastIndexOf("."), oldFileName.length());

                try {
                    file = icon.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (file != null) {
                //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
                try {
                    //存放在本地
                    //System.out.println(request.getSession().getServletContext().getRealPath("/"));
                    //System.out.println(request.getSession().getServletContext().getRealPath("/")+"/imgfile");
                    String imgcacheurl = "/picture";
                    File img = new File(imgcacheurl, fileName[i]);

                    if (img.length() < 512000) {
                        /* copyInputStreamToFile(file,img); */
                        System.out.println(">>>>>>>>>>>>>>>length>>>>>>>");

                        try {
                            QiniuUploadUtil.upLoadImage(icon.getInputStream(), fileName[0]);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        savePath = QiniuUploadUtil.getStartStaff() + fileName[0];

                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>savePath::" + savePath);

                    } else {
                        message = "图片大小不能超过500KB";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                message = "操作成功";
                flag = true;
                modelMap.put("result", 1);
                coinValidata.changeIcon(Long.valueOf(id), savePath);
            } catch (Exception e) {
                e.printStackTrace();
                message = "操作失败";
            }
        } else {
            message = "请选择图片";
        }
        return new ModelAndView("/coin/coin", result);
        //return list(request);
    }

    //复制文件
    private static void copyInputStreamToFile(InputStream source, File destination) throws IOException {
        try {
            destination.length();
            FileOutputStream output = FileUtils.openOutputStream(destination);
            try {
                IOUtils.copy(source, output);

                output.close(); // don't swallow close Exception if copy completes normally
            } finally {
                IOUtils.closeQuietly(output);
            }
        } finally {
            IOUtils.closeQuietly(source);
        }
    }

    @RequestMapping(value = "getImage", method = RequestMethod.GET)
    public void getImage(HttpServletResponse rsp, String value) {
        OutputStream out = null;
        try {
            if (!value.equals("") && value != null) {
                out = rsp.getOutputStream();
                FileInputStream fin = null;
                String imgcacheurl = "/picture";
                imgcacheurl = QiniuUploadUtil.getStartStaff();
                try {
                    fin = new FileInputStream(imgcacheurl + "/" + value);
                    int c;
                    while ((c = fin.read()) != -1) {
                        out.write(c);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (fin != null) {
                            fin.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "getAllCoinType", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAllCoinType() {
        return coinValidata.getCoinTypes();
    }
}
