package com.cdk.finaltest.controller;


import com.cdk.finaltest.dao.CustomerDAO;
import com.cdk.finaltest.dto.Customer;
import com.cdk.finaltest.dto.LoginInputDto;
import com.cdk.finaltest.service.LoginService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.codehaus.jackson.map.util.JSONPObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


/**
 * Created by bagades on 8/30/2016.
 */
@Controller
public class LogInController {

    @Autowired
    private LoginService loginService = null;

    public LoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/login")
    public
    @ResponseBody
    Customer login(@RequestBody LoginInputDto loginInputDto) {
        Customer customer = loginService.get(loginInputDto.getEmail(), loginInputDto.getPassword());
        return customer;
    }

    @RequestMapping(value = "/logout/{id}", method = RequestMethod.POST)
    public
    @ResponseBody
    String logout(@PathVariable String id) {
     //   Logger logger = LoggerFactory.getLogger(LogInController.class);
        int customerId = Integer.parseInt(id);
        Customer customer = loginService.getById(customerId);
        System.out.println(customer);
        customer = null;
       // logger.info("Logout successfully");
        return "logout successful";
    }
}