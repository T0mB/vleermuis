package com.planner.vleermuis.businesslogic.impl;

import com.planner.vleermuis.businesslogic.ActivityLogic;
import com.planner.vleermuis.data.Activity;
import com.planner.vleermuis.data.Agenda;
import com.planner.vleermuis.data.dao.ActivityDAO;
import com.planner.vleermuis.data.dao.AgendaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ActivityLogicImpl implements ActivityLogic {

    @Autowired
    ActivityDAO activityDAO;

    @Autowired
    AgendaDAO agendaDAO;


    @Override
    public void createActivity(Activity activity) {
        Optional<Agenda> agenda = agendaDAO.findById(1L);
        if(activity.getId() != null && agenda.isPresent()) {
            activity.setAgenda(agenda.get());
            activityDAO.save(activity);
        }
        else if(agenda.isPresent()){
            activity.setAgenda(agenda.get());
            activityDAO.save(activity);
        }
    }

    @Override
    public void deleteActivity(Activity activity) {
        activityDAO.delete(activity);
    }

    @Override
    public List<Activity> getAllActivitiesForMonthAndYear(Month month, Integer year) {
        return StreamSupport.stream(activityDAO.findAll().spliterator(), false)
                .filter(a -> a.getAtDate().getMonth().equals(month)
                        && a.getAtDate().getYear() == year)
                .toList();
    }


}
