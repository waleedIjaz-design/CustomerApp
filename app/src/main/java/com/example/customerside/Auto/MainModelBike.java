package com.example.customerside.Auto;

public class MainModelBike {

    Integer bikePartsList;
    String bikePartsName;
    String bikePartsPrice;


    public MainModelBike(Integer bikePartsList,String bikePartsName,String bikePartsPrice){

        this.bikePartsList = bikePartsList;
        this.bikePartsName = bikePartsName;
        this.bikePartsPrice = bikePartsPrice;


    }


    public Integer getBikePartsList() {
        return bikePartsList;
    }

    public String getBikePartsName() {
        return bikePartsName;
    }

    public String getBikePartsPrice() {
        return bikePartsPrice;
    }

}
