package com.reserve.model;

import lombok.Getter;

@Getter
public enum RPType {
    TYPE1("temporary regular"),

    TYPE2("permanent regular"),

    TYPE3("temporary asylum"),

    TYPE4("permanent asylum"),

    TYPE5("long-term EU resident");
    private final String rpType;

    RPType(String val) {
        this.rpType = val;
    }
}
