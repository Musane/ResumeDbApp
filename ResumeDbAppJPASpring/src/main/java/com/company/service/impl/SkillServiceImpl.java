package com.company.service.impl;

import com.company.dao.impl.SkillRepositoryCustom;
import com.company.entity.Skill;
import com.company.service.inter.SkillServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SkillServiceImpl implements SkillServiceInter {

    @Autowired
    private SkillRepositoryCustom skillRepo;

    @Override
    public List<Skill> getAll() {
        return skillRepo.getAll();
    }

    @Override
    public Skill getById(int userId) {
        return skillRepo.getById(userId);
    }

    @Override
    public boolean updateSkill(Skill u) {
        return skillRepo.updateSkill(u);
    }

    @Override
    public boolean removeSkill(int id) {
        return skillRepo.removeSkill(id);
    }

    @Override
    public List<Skill> getByName(String name) {
        return skillRepo.getByName(name);
    }

    @Override
    public boolean insertSkill(Skill skill) {
        return skillRepo.insertSkill(skill);
    }
}
