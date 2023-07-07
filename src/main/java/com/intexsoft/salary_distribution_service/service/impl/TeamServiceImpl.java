package com.intexsoft.salary_distribution_service.service.impl;

import com.intexsoft.salary_distribution_service.exceptions.ResourceNotFoundException;
import com.intexsoft.salary_distribution_service.model.db.Team;
import com.intexsoft.salary_distribution_service.repository.TeamRepository;
import com.intexsoft.salary_distribution_service.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    @Override
    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public List<Team> findAllTeams(String codename) {
        var teamsByCodename = teamRepository.findAllByCodenameContainsAllIgnoreCase(codename);
        if (teamsByCodename.isEmpty()) {
            var message = String.format("Unable to find teams by codename: {%s}", codename);
            throw new ResourceNotFoundException(message);
        }
        return teamsByCodename;
    }

    @Override
    public Team findTeamById(Long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> {
                    var message = String.format("Team with id: {%s} not found", teamId);
                    return new ResourceNotFoundException(message);
                });
    }

    @Override
    public Team updateTeam(Long teamId, Team team) {
        var toUpdateTeam = findTeamById(teamId);
        toUpdateTeam.setCodename(team.getCodename());
        toUpdateTeam.setMembers(team.getMembers());
        return teamRepository.save(toUpdateTeam);
    }

    @Override
    public void deleteTeam(Long teamId) {
        teamRepository.deleteById(teamId);
    }
}
