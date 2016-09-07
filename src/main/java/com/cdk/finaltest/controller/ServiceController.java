package com.cdk.finaltest.controller;

import com.cdk.finaltest.service.CarService;
import com.cdk.finaltest.dto.CarServiceDTO;
import com.cdk.finaltest.util.DateUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

/**
 * Created by bagades on 9/3/2016.
 */
@Controller
public class ServiceController {
    @Autowired
    private CarService carService;
    public CarService getCarServiceDAO() {
        return carService;
    }

    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @RequestMapping(value = "/addVehicleForService/{id}&{vin}&{date}&{type}",method = RequestMethod.POST)
    public @ResponseBody
    String addforRepair(@PathVariable String id,@PathVariable String vin,@PathVariable String date, @PathVariable String type){
       // Logger logger = LoggerFactory.getLogger(ServiceController.class);
        String reply=null;
        if(id!= null) {
            java.util.Date utilDate = DateUtility.stringToDate(date, "yyyy-MM-dd");
            Date sqlDate = new Date(utilDate.getTime());
            CarServiceDTO carServiceDTO = new CarServiceDTO();
            carServiceDTO.setServiceType(type);
            carServiceDTO.setVin(Integer.parseInt(vin));
            carServiceDTO.setDateOfServicing(sqlDate);
            carServiceDTO.setStatus("Requested");
            int i =carService.addForRepair(carServiceDTO);
            if(i == 0){
                reply = "Request already under process";
            }else if(i==2) {
                reply = "All booking for this date have been exhausted. <br>Please choose another date";
            } else{
                reply = "Request has been registered. Your service id is :: "+i;
            }
        }
        else
            reply = "invalid";
        //logger.info(reply);
        return reply;
    }
    @RequestMapping(value = "/checkAvailable/{id}&{date}",method = RequestMethod.GET)
    public @ResponseBody
    String checkAvailablity(@PathVariable String id,@PathVariable String date){
      //  Logger logger = LoggerFactory.getLogger(ServiceController.class);
        String reply;
        if(id!=null) {
            java.util.Date utilDate = DateUtility.stringToDate(date, "yyyy-MM-dd");
            Date sqlDate = new Date(utilDate.getTime());
            boolean flg = carService.checkAvailability(sqlDate);
            if (!flg) {
                reply = "All booking for this date have been exhausted. <br>Please choose another date";
            } else
                reply = "Slot available for booking.";
        }
        else
            reply = "invalid";
        return reply;
    }

}
