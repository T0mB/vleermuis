package com.planner.vleermuis.businesslogic.impl;

import com.planner.vleermuis.businesslogic.SourceSiteLogic;
import com.planner.vleermuis.data.SourceSite;
import com.planner.vleermuis.data.dao.SourceSiteDAO;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class SourceSiteLogicImpl implements SourceSiteLogic {

    @Autowired
    public SourceSiteDAO sourceSiteDAO;

    @Override
    public void createLink(String name, String link) {

        UrlValidator validator = new UrlValidator();
        if(validator.isValid(link)){
            SourceSite site = new SourceSite(name, link);
            sourceSiteDAO.save(site);
        }
        else {
            throw new RuntimeException("false link");
            //TODO better messaging etc.
        }
    }

    @Override
    public List<SourceSite> getAllSites() {
        return StreamSupport.stream(sourceSiteDAO.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
