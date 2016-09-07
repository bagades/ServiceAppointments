package com.cdk.finaltest.controller;


import com.cdk.finaltest.dao.VehicleDAO;
import com.cdk.finaltest.dto.CarServiceDTO;
import com.cdk.finaltest.service.CarService;
import com.cdk.finaltest.dto.Vehicle;
import com.cdk.finaltest.service.VehicleService;
import com.cdk.finaltest.util.DateUtility;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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
 * Created by bagades on 8/30/2016.
 */
@Controller
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private CarService carService;

    public CarService getCarService() {
        return carService;
    }

    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    public VehicleService getVehicleService() {
        return vehicleService;
    }

    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @RequestMapping(value = "/addVehicle/{id}",method = RequestMethod.POST)
    public @ResponseBody
    Vehicle add(@PathVariable String id, @RequestBody Vehicle vehicle){
 //       Logger logger = LoggerFactory.getLogger(VehicleController.class);
        if(id != null) {
            vehicle.setCustomerId(Integer.parseInt(id));
            return vehicleService.save(vehicle);
        }
   //     logger.info(id+" "+vehicle);
        return null;
    }
    @RequestMapping(value = "/listVehicle/{id}",method = RequestMethod.POST)
    public @ResponseBody
    List<Vehicle> list(@PathVariable String id){
     //   Logger logger = LoggerFactory.getLogger(VehicleController.class);
        if(id!=null) {
            return vehicleService.list();
        }
       // logger.info("list of Vehicles");
        return null;
    }
    @RequestMapping(value = "/getVehicleDetails/{id}",method = RequestMethod.POST)
    public @ResponseBody
    Vehicle getVehicle(@PathVariable String id, int vin){
        //Logger logger = LoggerFactory.getLogger(VehicleController.class);
        if(id!=null) {
          //  logger.info(id+" "+vin);
            return vehicleService.get(vin);
        }
        //logger.info("invalid car details"+vin);
        return null;
    }
    @RequestMapping(value = "/customerVehicleList/{id}",method = RequestMethod.POST)
    public @ResponseBody String customerVehicleList(@PathVariable String id){
        //Logger logger = LoggerFactory.getLogger(VehicleController.class);
        if(id!=null) {
            List<Vehicle> vehicleList = vehicleService.customerVehicleList(Integer.parseInt(id));
            CarServiceDTO carServiceDTO = null;
            Date date= null;
            String status ="";
            String str = "{\"Vehicles\":[";
            for (Vehicle vehicle : vehicleList) {
                carServiceDTO = carService.getLastServiceDetails(vehicle.getVin());
                    if (carServiceDTO!=null) {
                        date = carServiceDTO.getDateOfServicing();
                        status = carServiceDTO.getStatus();
                    }

                str += "{" +
                        "\"vin\":\"" + vehicle.getVin() + "\"" +
                        ", \"model\":\"" + vehicle.getModel() + '\"' +
                        ", \"company\":\"" + vehicle.getCompany() + '\"' +
                        ", \"last_servicing_date\":\"" + date + '\"' +
                        ", \"status\":\"" + status+ '\"' +
                        "},";
            }
            str = str.substring(0, str.length() - 1);
            str += "]}";
            System.out.println(str);
          //  logger.info(str);
            return str;
        }
        //logger.info("invalid/ not log in");
        return null;
    }
}
