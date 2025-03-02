package com.planner.vleermuis.businesslogic.impl;

import com.planner.vleermuis.businesslogic.ActivityLogic;
import com.planner.vleermuis.data.Activity;
import com.planner.vleermuis.data.dao.ActivityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ActivityLogicImpl implements ActivityLogic {

    @Autowired
    ActivityDAO activityDAO;


    @Override
    public void createActivity(String name, String location, LocalDateTime atDate, String description) {
       // activityDAO.save(activity);
    }

    @Override
    public void deleteActivity(Activity activity) {
        activityDAO.delete(activity);
    }


}
