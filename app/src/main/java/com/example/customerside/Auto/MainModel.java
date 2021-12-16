package com.example.customerside.Auto;

public class MainModel {
    Integer carPartsList;
    String carPartsName;
    String carPartsPrice;


    public MainModel(Integer carPartsList,String carPartsName,String carPartsPrice){

        this.carPartsList = carPartsList;
        this.carPartsName = carPartsName;
        this.carPartsPrice = carPartsPrice;


    }


    public Integer getCarPartsList() {
        return carPartsList;
    }

    public String getCarPartsName() {
        return carPartsName;
    }

    public String getCarPartsPrice() {
        return carPartsPrice;
    }
}
