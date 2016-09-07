package com.cdk.finaltest.controller;

import com.cdk.finaltest.dao.CustomerDAO;
import com.cdk.finaltest.dto.Customer;
import com.cdk.finaltest.service.RegisterService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by bagades on 8/30/2016.
 */
@Controller
public class RegistrationController {
    @Autowired
    private RegisterService registerService = null;

    public RegisterService getRegisterService() {
        return registerService;
    }

    public void setRegisterService(RegisterService registerService) {
        this.registerService = registerService;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public @ResponseBody
    Customer add(@RequestBody Customer customer) {
      //  Logger logger = LoggerFactory.getLogger(RegistrationController.class);
        customer= registerService.save(customer) ;
        System.out.println(customer);
        //logger.info(customer.toString());
        return customer;
    }


}
