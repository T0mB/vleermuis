package com.planner.vleermuis.data.dao;

import com.planner.vleermuis.data.Activity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityDAO extends CrudRepository<Activity, Long> {
}
