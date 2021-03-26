package com.company.service.impl;

import com.company.dao.impl.EmploymentHistoryRepositoryCustom;
import com.company.entity.EmploymentHistory;
import com.company.service.inter.EmploymentHistoryServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmploymentHistoryServiceImpl implements EmploymentHistoryServiceInter {

    @Autowired
    private EmploymentHistoryRepositoryCustom empRepo;

    @Override
    public List<EmploymentHistory> getAll() {
        return empRepo.getAll();
    }

    @Override
    public List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId) {
        return empRepo.getAllEmploymentHistoryByUserId(userId);
    }

    @Override
    public EmploymentHistory getById(int id) {
        return empRepo.getById(id);
    }

    @Override
    public boolean insertEmpHistory(EmploymentHistory u) {
        return empRepo.insertEmpHistory(u);
    }

    @Override
    public boolean updateEmpHistory(EmploymentHistory u) {
        return empRepo.updateEmpHistory(u);
    }

    @Override
    public boolean removeEmpHistory(int id) {
        return empRepo.removeEmpHistory(id);
    }
}
