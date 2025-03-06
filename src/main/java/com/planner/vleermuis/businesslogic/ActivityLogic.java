package com.planner.vleermuis.businesslogic;

import com.planner.vleermuis.data.Activity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.List;

@Service
public interface ActivityLogic {

    void createActivity(Activity activity);

    void deleteActivities(List<Activity> activities);

    List<Activity> getAllActivitiesForMonthAndYear(Month month, Integer year);
}
