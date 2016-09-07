package com.cdk.finaltest.dto;

import java.sql.Date;

/**
 * Created by bagades on 9/3/2016.
 */
public class CarServiceDTO {
    private int serviceId;
    private int vin;
    private Date dateOfServicing;
    private String serviceType;
    private String status;

    public CarServiceDTO() {
    }

    public CarServiceDTO(int serviceId, int vin, Date dateOfServicing, String serviceType, String status) {
        this.serviceId = serviceId;
        this.vin = vin;
        this.dateOfServicing = dateOfServicing;
        this.serviceType = serviceType;
        this.status = status;
    }

    @Override
    public String toString() {
        return "CarServiceDTO{" +
                "serviceId=" + serviceId +
                ", vin=" + vin +
                ", dateOfServicing=" + dateOfServicing +
                ", serviceType='" + serviceType + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getVin() {
        return vin;
    }

    public void setVin(int vin) {
        this.vin = vin;
    }

    public Date getDateOfServicing() {
        return dateOfServicing;
    }

    public void setDateOfServicing(Date dateOfServicing) {
        this.dateOfServicing = dateOfServicing;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
