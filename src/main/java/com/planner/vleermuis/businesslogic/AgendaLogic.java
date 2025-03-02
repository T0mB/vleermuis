package com.planner.vleermuis.businesslogic;

import com.planner.vleermuis.data.Activity;
import com.planner.vleermuis.data.Agenda;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AgendaLogic {

    void addActivityToAgenda(Activity activity, Agenda agenda);

    Optional<Agenda> findAgendaById(long id);

    void createAgendaIfNotPresent(long id, String name, List<Activity> list);
}
