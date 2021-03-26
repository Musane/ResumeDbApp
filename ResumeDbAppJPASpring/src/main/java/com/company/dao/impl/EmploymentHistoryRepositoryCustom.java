package com.company.dao.impl;

import com.company.entity.EmploymentHistory;

import java.util.List;

/**
 *
 * @author M
 */
public interface EmploymentHistoryRepositoryCustom {

    List<EmploymentHistory> getAll();

    public List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId);

    public EmploymentHistory getById(int id);

    public boolean insertEmpHistory(EmploymentHistory u);

    boolean updateEmpHistory(EmploymentHistory u);

    boolean removeEmpHistory(int id);
}
