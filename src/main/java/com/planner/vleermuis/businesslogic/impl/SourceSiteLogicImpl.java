package com.planner.vleermuis.businesslogic.impl;

import com.planner.vleermuis.businesslogic.SourceSiteLogic;
import com.planner.vleermuis.data.SourceSite;
import com.planner.vleermuis.data.dao.SourceSiteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class SourceSiteLogicImpl implements SourceSiteLogic {

    @Autowired
    public SourceSiteDAO sourceSiteDAO;

    @Override
    public void createLink(String id, String name, String link) {
        SourceSite site = new SourceSite();
        if(id != null){
            Optional<SourceSite> existingSite = sourceSiteDAO.findById((Long.parseLong(id)));
            if(existingSite.isPresent()){
                site = existingSite.get();
                site.setName(name);
                site.setLink(link);
            }
        }
        else {
            site = new SourceSite(name, link);
        }

        sourceSiteDAO.save(site);
    }

    @Override
    public void deleteLinks(List<SourceSite> links) {
        sourceSiteDAO.deleteAll(links);
    }

    @Override
    public List<SourceSite> getAllSites() {
        return StreamSupport.stream(sourceSiteDAO.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
