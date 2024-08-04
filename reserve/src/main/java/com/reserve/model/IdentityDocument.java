package com.reserve.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class IdentityDocument {
    //public sealed class IdentityDocument permits DrivingIdentity, NationalIdentity, TaxationIdentity {
    @Id
    private String documentNumber;
    private String dateOfIssue;
    private String expiryDate;

    @OneToOne
    private Passenger passenger;
}
