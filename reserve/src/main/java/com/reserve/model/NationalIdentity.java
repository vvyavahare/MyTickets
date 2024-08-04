package com.reserve.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
//public sealed class NationalIdentity extends IdentityDocument permits PassportDetail, ResidencePermitDetail {
public  class NationalIdentity extends IdentityDocument{
    private String issuingCountry;
    private String fistName;
    private String lastName;
    private String placeOfIssue;
}
