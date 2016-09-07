package com.cdk.finaltest.service;

import com.cdk.finaltest.dao.CarServiceDAO;
import com.cdk.finaltest.dto.CarServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * Created by bagades on 9/3/2016.
 */
@Service
public class CarService {
    @Autowired
    CarServiceDAO carServiceDAO;

    public CarServiceDAO getCarServiceDAO() {
        return carServiceDAO;
    }

    public void setCarServiceDAO(CarServiceDAO carServiceDAO) {
        this.carServiceDAO = carServiceDAO;
    }
    public boolean checkAvailability(Date date){
        int count = carServiceDAO.checkAvailablity(date);
        System.out.println("hello"+count);
        return count < 5;
    }
    public int addForRepair(CarServiceDTO carServiceDTO){
        return carServiceDAO.addForRepair(carServiceDTO);

    }
    public CarServiceDTO getLastServiceDetails(int vin){

        return carServiceDAO.getLastServiceDetails(vin);
    }
}
