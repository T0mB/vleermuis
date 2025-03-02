package com.planner.vleermuis.businesslogic.impl;

import com.planner.vleermuis.businesslogic.AgendaLogic;
import com.planner.vleermuis.data.Activity;
import com.planner.vleermuis.data.Agenda;
import com.planner.vleermuis.data.dao.AgendaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class AgendaLogicImpl implements AgendaLogic {

    @Autowired
    AgendaDAO agendaDAO;

    @Override
    public void addActivityToAgenda(Activity activity, Agenda agenda) {

    }

    @Override
    public Optional<Agenda> findAgendaById(long id) {
        return agendaDAO.findById(id);
    }

    @Override
    public void createAgendaIfNotPresent(long id, String name, List<Activity> list) {
        if(agendaDAO.findById(id).isEmpty()){
            Agenda mainAgenda = new Agenda();
            mainAgenda.setId(id);
            mainAgenda.setName("main");
            mainAgenda.setActivities(Collections.emptyList());
            agendaDAO.save(mainAgenda);
        }
    }
}
