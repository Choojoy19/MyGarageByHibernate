package com.mygarage.byhibernate.model;

public enum EngineType {
    PETROL("PETROL"),
    DIESEl("DIESEl"),
    GAS("GAS"),
    HYBRID("HYBRID"),
    ELECTRIC("ELECTRIC");
    private String value;

    private EngineType(String value) {
        this.value = value;

    }
    public String getValue() {
        return this.value;
    }

    public static EngineType fromString(String value) {
        for (EngineType et : EngineType.values()) {
            if (et.value.equalsIgnoreCase(value)) {
                return et;
            }
        }
        return null;
    }
}
