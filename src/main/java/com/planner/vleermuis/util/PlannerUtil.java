package com.planner.vleermuis.util;

import com.planner.vleermuis.data.SourceSite;
import org.apache.commons.validator.routines.UrlValidator;

public class PlannerUtil {

    private static String HTTP_PREFIX = "http://";
    private static String HTTPS_PREFIX = "https://";

    public static String sanitizeUrl(String url){
        UrlValidator validator = new UrlValidator();
        if(validator.isValid(url)){
            return url;
        }
        else if(!url.substring(0, 6).equals(HTTP_PREFIX) || !url.substring(0, 7).equals(HTTPS_PREFIX)){
            return HTTPS_PREFIX + url;
        }
        return null;
    }
}
