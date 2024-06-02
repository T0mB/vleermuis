package com.planner.vleermuis.businesslogic.impl;

import com.planner.vleermuis.businesslogic.SourceSiteLogic;
import com.planner.vleermuis.data.SourceSite;
import com.planner.vleermuis.data.dao.SourceSiteDAO;
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
        SourceSite site = new SourceSite(name, link);
        sourceSiteDAO.save(site);
    }

    @Override
    public List<SourceSite> getAllSites() {
        return StreamSupport.stream(sourceSiteDAO.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
