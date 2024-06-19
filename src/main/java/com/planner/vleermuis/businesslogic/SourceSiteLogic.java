package com.planner.vleermuis.businesslogic;

import com.planner.vleermuis.data.SourceSite;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SourceSiteLogic {

    void createLink(String name, String link);

    void deleteLinks(List<SourceSite> links);

    List<SourceSite> getAllSites();

}
