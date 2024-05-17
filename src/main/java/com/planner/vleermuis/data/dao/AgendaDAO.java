package com.planner.vleermuis.data.dao;

import com.planner.vleermuis.data.Agenda;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaDAO extends CrudRepository<Agenda, Long> {
}
