package com.cdk.finaltest.dao;

import com.cdk.finaltest.domain.CarService;
import com.cdk.finaltest.dto.CarServiceDTO;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bagades on 9/3/2016.
 */
@Component
public class CarServiceDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
    public int checkAvailablity(Date date){
        List<CarServiceDTO> carServiceDTOList = hibernateTemplate.findByNamedParam("from com.cdk.finaltest.domain.CarService where dateOfServicing=:date and status!='Completed'","date",date);
        return carServiceDTOList.size();
    }
    public CarServiceDTO getLastServiceDetails(int vin){
        List<CarService> carServiceDTOList = hibernateTemplate.findByNamedParam("from com.cdk.finaltest.domain.CarService cs where cs.vin=:vin order by dateOfServicing desc","vin",vin);
        CarServiceDTO carServiceDTO = null;
        System.out.println(carServiceDTOList.size());
        if(carServiceDTOList.size() !=0 && carServiceDTOList != null){
            carServiceDTO = new CarServiceDTO();
            CarService carService = carServiceDTOList.get(0);
            carServiceDTO.setVin(carService.getVin());
            carServiceDTO.setServiceType(carService.getServiceType());
            carServiceDTO.setStatus(carService.getStatus());
            carServiceDTO.setDateOfServicing(carService.getDateOfServicing());
            carServiceDTO.setServiceId(carService.getServiceId());
        }
        System.out.println(carServiceDTO);
        return carServiceDTO;
    }
    public int addForRepair(CarServiceDTO carServiceDTO){
        CarService carService = new CarService();
        carService.setServiceType(carServiceDTO.getServiceType());
        carService.setStatus(carServiceDTO.getStatus());
        carService.setDateOfServicing(carServiceDTO.getDateOfServicing());
        carService.setVin(carServiceDTO.getVin());
        List<CarService> carServiceList = hibernateTemplate.findByNamedParam("from com.cdk.finaltest.domain.CarService where status  in ('Requested','Started')  and vin=:vin","vin",carService.getVin());

        System.out.println("Size"+  carServiceList.size());
        if (carServiceList.size()==0) {
            List<CarServiceDTO> carServiceDTOList = hibernateTemplate.findByNamedParam("from com.cdk.finaltest.domain.CarService where dateOfServicing=:dateOfServicing and status  in ('Requested','Started')","dateOfServicing",carServiceDTO.getDateOfServicing());
            System.out.println(carServiceDTOList.size());
            if(carServiceDTOList.size()<5){
                hibernateTemplate.save(carService);
                carServiceDTO.setServiceId(carService.getServiceId());
                return carServiceDTO.getServiceId();
            }
            else
                return 2;
        }


        return 0;
    }
    public List<CarServiceDTO> list(){
        List<CarService> domainCarServiceList = hibernateTemplate.loadAll(CarService.class);
        List<CarServiceDTO> dtoCarServiceList = null;
        if (null != domainCarServiceList && domainCarServiceList.size() != 0) {
            dtoCarServiceList = new ArrayList<>();
            for (CarService v : domainCarServiceList) {
                CarServiceDTO carServiceDTO= new CarServiceDTO();
                carServiceDTO.setVin(v.getVin());
                carServiceDTO.setServiceId(v.getServiceId());
                carServiceDTO.setServiceType(v.getServiceType());
                carServiceDTO.setStatus(v.getStatus());
                carServiceDTO.setDateOfServicing(v.getDateOfServicing());
                dtoCarServiceList.add(carServiceDTO);
            }
        }
        return dtoCarServiceList;
    }

}
