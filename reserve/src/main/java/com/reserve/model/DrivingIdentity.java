package com.reserve.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public non-sealed class DrivingIdentity extends IdentityDocument {
    private List<VehicleType> drivingVehicleType;

}
