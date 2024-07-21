package com.planner.vleermuis.util;

import org.apache.commons.validator.routines.UrlValidator;

public class PlannerUtil {

    public static String sanitizeUrl(String url){
        UrlValidator validator = new UrlValidator();
        String HTTPS_PREFIX = "https://";
        if(validator.isValid(url)){
            return url;
        }
        else {
            return HTTPS_PREFIX + url;
        }
    }
}
