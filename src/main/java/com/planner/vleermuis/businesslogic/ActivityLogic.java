package com.planner.vleermuis.businesslogic;

import com.planner.vleermuis.data.Activity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface ActivityLogic {

    void createActivity(String name, String location, LocalDateTime atDate, String description);

    void deleteActivity(Activity activity);
}
