package com.planner.vleermuis.common;

public enum Severity {
    DEBUG("DEBUG"),
    INFO("INFO"),
    WARNING("WARNING"),
    ERROR("ERROR");

    public final String text;

    private Severity(String text){
        this.text = text;
    }
}
