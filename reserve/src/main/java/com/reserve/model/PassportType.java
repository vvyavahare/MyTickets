package com.reserve.model;

import lombok.Getter;

@Getter
public enum PassportType {
    ORDINARY("Ordinary"),

    OFFICIAL("official"),

    SERVICE("service"),

    DIPLOMAT("diplomat"),

    EMERGENCY("emergency");

    private final String passportType;

    PassportType(String type) {
        this.passportType = type;
    }

}
