package com.intexsoft.salary_distribution_service.repository;

import com.intexsoft.salary_distribution_service.model.db.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, String> {
}
