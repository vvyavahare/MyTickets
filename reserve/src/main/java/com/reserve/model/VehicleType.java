package com.reserve.model;

import lombok.Getter;

@Getter
public enum VehicleType {
    LV("Light Vehicles - Bikes"),
    MGV("Medium Goods Vehicle"),
    HMV("Heavy Goods Vehicle"),

    AGR("Agricultural vehicles");

    private final String vehicleType;

    VehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

}
