package com.intexsoft.salary_distribution_service.service;

import com.intexsoft.salary_distribution_service.model.db.Budget;

import java.util.List;

public interface BudgetService {

    Budget saveBudget(Budget budget);

    List<Budget> findAllBudgets();

    Budget findBudgetByPeriod(String period);

    Budget updateBudget(String period, Budget budget);

    void deleteBudget(String period);
}
