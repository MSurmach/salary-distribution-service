package com.intexsoft.salary_distribution_service.service;

import com.intexsoft.salary_distribution_service.model.db.Team;

import java.util.List;

public interface TeamService {
    Team saveTeam(Team team);

    List<Team> findAllTeams(String codename);

    Team findTeamById(Long teamId);

    Team updateTeam(Long teamId, Team team);

    void deleteTeam(Long teamId);
}
