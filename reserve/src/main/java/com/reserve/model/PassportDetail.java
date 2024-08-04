package com.reserve.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public final class PassportDetail extends NationalIdentity {
    private PassportType passportType;
}
