package com.intexsoft.salary_distribution_service.controller;

import com.intexsoft.salary_distribution_service.annotation.ValidPeriod;
import com.intexsoft.salary_distribution_service.model.db.Budget;
import com.intexsoft.salary_distribution_service.service.BudgetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/budget")
@Validated
@Slf4j
public class BudgetController {
    private final BudgetService budgetService;

    @PostMapping
    @ResponseStatus(CREATED)
    public Budget saveBudget(@RequestBody @Valid Budget budget) {
        log.info("IN saveBudget: budget = {}", budget);
        var saved = budgetService.saveBudget(budget);
        log.info("OUT saveBudget: saved budget = {}", saved);
        return budget;
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<Budget> findAllBudgets() {
        log.info("IN findAllBudgets");
        var allBudgets = budgetService.findAllBudgets();
        log.info("OUT findAllBudgets: count of budgets = {}, content of budgets = {}", allBudgets.size(), allBudgets);
        return allBudgets;
    }

    @GetMapping("/{period}")
    @ResponseStatus(OK)
    public Budget findBudgetByPeriod(@PathVariable @ValidPeriod String period) {
        log.info("IN findBudgetByPeriod: period = {}", period);
        var budgetByPeriod = budgetService.findBudgetByPeriod(period);
        log.info("OUT findBudgetByPeriod: budget = {}", budgetByPeriod);
        return budgetByPeriod;
    }

    @PutMapping("/{period}")
    @ResponseStatus(OK)
    public Budget updateBudget(@PathVariable @ValidPeriod String period, @RequestBody @Valid Budget budget) {
        log.info("IN updateBudget: period = {}, budget needed to update = {}", period, budget);
        var updatedBudget = budgetService.updateBudget(period, budget);
        log.info("OUT updateBudget: updated budget = {}", updatedBudget);
        return updatedBudget;
    }

    @DeleteMapping("/{period}")
    @ResponseStatus(NO_CONTENT)
    public void deleteBudget(@PathVariable @ValidPeriod String period) {
        log.info("IN deleteBudget: period = {}", period);
        budgetService.deleteBudget(period);
        log.info("OUT deleteBudget: budget with period = {} was deleted", period);
    }
}
