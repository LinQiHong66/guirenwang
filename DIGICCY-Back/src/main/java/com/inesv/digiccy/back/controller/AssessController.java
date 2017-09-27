package com.inesv.digiccy.back.controller;

import com.inesv.digiccy.validata.AssessValidata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by JimJim on 2016/12/2 0002.
 */
@Controller
@RequestMapping("/assess")
public class AssessController {

    private static Logger logger = LoggerFactory.getLogger(AssessController.class);

    @Autowired
    AssessValidata        assessValidata;


    @RequestMapping(value = "gotoCount", method = RequestMethod.GET)
    public String gotoCount() {
        return "/assess/count";
    }

    @RequestMapping(value = "gotoAssess", method = RequestMethod.GET)
    public String gotoAssess() {
        return "/assess/assess";
    }

    @RequestMapping(value = "getAssess", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAssess() {
        Map<String, Object> map = assessValidata.getAssessList();
        return map;
    }

    @RequestMapping(value = "getAssessCount", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAssessCount() {
        Map<String, Object> map = assessValidata.getAssessCount();
        return map;
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView index() {
        return new ModelAndView("index");

    }

}
