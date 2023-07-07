package com.intexsoft.salary_distribution_service.controller;

import com.intexsoft.salary_distribution_service.model.CreateTeamDto;
import com.intexsoft.salary_distribution_service.model.db.Team;
import com.intexsoft.salary_distribution_service.service.TeamService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/team")
@Validated
@Slf4j
public class TeamController {
    private final TeamService teamService;

    @PostMapping
    @ResponseStatus(CREATED)
    public Team saveTeam(@RequestBody @Valid CreateTeamDto createTeamDto) {
        log.info("IN saveTeam: team = {}", createTeamDto);
        var newTeam = Team.builder()
                .codename(createTeamDto.getCodename())
                .members(createTeamDto.getMembers())
                .build();
        newTeam.getMembers().forEach(employee -> employee.setTeam(newTeam));
        var savedTeam = teamService.saveTeam(newTeam);
        log.info("OUT saveTeam: saved team = {}", savedTeam);
        return savedTeam;
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<Team> findAllTeams(@RequestParam(required = false, defaultValue = "") String codename) {
        log.info("IN findAllTeams: codename = {}", codename);
        var allTeams = teamService.findAllTeams(codename);
        log.info("OUT findAllTeams: count of teams = {}, content of teams = {}", allTeams.size(), allTeams);
        return allTeams;
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public Team findTeamById(@PathVariable("id") @Positive Long teamId) {
        log.info("IN findTeamById: id = {}", teamId);
        var teamById = teamService.findTeamById(teamId);
        log.info("OUT findTeamById: team = {}", teamById);
        return teamById;
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public Team updateTeam(@PathVariable("id") @Positive Long teamId, @RequestBody @Valid Team team) {
        log.info("IN updateTeam: id = {}, team needed to update = {}", teamId, team);
        var updatedTeam = teamService.updateTeam(teamId, team);
        log.info("OUT updateTeam: updated team = {}", updatedTeam);
        return updatedTeam;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteTeam(@PathVariable("id") @Positive Long teamId) {
        log.info("IN deleteTeam: id = {}", teamId);
        teamService.deleteTeam(teamId);
        log.info("OUT deleteTeam: team with id = {} was deleted", teamId);
    }
}
