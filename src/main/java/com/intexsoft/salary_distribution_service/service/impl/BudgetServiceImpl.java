package com.intexsoft.salary_distribution_service.service.impl;

import com.intexsoft.salary_distribution_service.exceptions.ResourceNotFoundException;
import com.intexsoft.salary_distribution_service.model.db.Budget;
import com.intexsoft.salary_distribution_service.repository.BudgetRepository;
import com.intexsoft.salary_distribution_service.service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;

    @Override
    public Budget saveBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    @Override
    public List<Budget> findAllBudgets() {
        return budgetRepository.findAll();
    }

    @Override
    public Budget findBudgetByPeriod(String period) {
        return budgetRepository.findById(period)
                .orElseThrow(() -> {
                    var message = String.format("Budget for the period: {%s} not found", period);
                    return new ResourceNotFoundException(message);
                });
    }

    @Override
    public Budget updateBudget(String period, Budget budget) {
        var toUpdateBudget = findBudgetByPeriod(period);
        toUpdateBudget.setTotalAmount(budget.getTotalAmount());
        return budgetRepository.save(toUpdateBudget);
    }

    @Override
    public void deleteBudget(String period) {
        budgetRepository.deleteById(period);
    }
}
