package com.planner.vleermuis.data.dao;

import com.planner.vleermuis.data.SourceSite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceSiteDAO extends CrudRepository<SourceSite, Long> {
}
