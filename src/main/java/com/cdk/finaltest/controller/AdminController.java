package com.cdk.finaltest.controller;

import com.cdk.finaltest.dto.CarServiceDTO;
import com.cdk.finaltest.dto.Vehicle;
import com.cdk.finaltest.service.AdminService;
import com.cdk.finaltest.util.DateUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;

/**
 * Created by bagades on 9/3/2016.
 */
@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    public AdminService getAdminService() {
        return adminService;
    }

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }
    @RequestMapping(value = "/updateStatus/{sid}&{status}",method = RequestMethod.POST)
    public @ResponseBody String updateStatus(@PathVariable String sid,@PathVariable String status){
     //   Logger logger = LoggerFactory.getLogger(AdminController.class);
        int serviceId = Integer.parseInt(sid);
        CarServiceDTO carServiceDTO = adminService.updateStatus(serviceId,status);
        String result = "Successfully updated status with service_id "+carServiceDTO.getServiceId()+" to "+carServiceDTO.getStatus();
      //  logger.info(result);
        return result;
    }
    @RequestMapping(value = "/getDetailsByDate/{date}", method = RequestMethod.POST)
    public @ResponseBody String getDetailsByDate(@PathVariable String date){
       // Logger logger = LoggerFactory.getLogger(AdminController.class);
        Date sqlDate = new Date(DateUtility.stringToDate(date,"yyyy-MM-dd").getTime());
        List<Object[]> vehicleList= adminService.getVehiclesByDate(sqlDate);
        String str = "{\"Vehicles\":[";
        for (Object[] vehicle : vehicleList) {
            str += "{" +
                    "\"serviceId\":\"" + vehicle[0] + "\"" +
                    ", \"dateOfService\":\"" + vehicle[1] + "\"" +
                    ", \"type\":\"" + vehicle[2] + '\"' +
                    ", \"status\":\"" + vehicle[3] + '\"' +
                    ", \"name\":\"" + vehicle[4] + '\"' +
                    ", \"address\":\"" + vehicle[5] + "\"" +
                    "},";
        }
        str = str.substring(0, str.length() - 1);
        str += "]}";
        //logger.info(str);
        return str;
    }
}
