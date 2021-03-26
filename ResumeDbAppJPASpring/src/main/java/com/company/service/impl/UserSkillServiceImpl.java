package com.company.service.impl;

import com.company.dao.impl.UserSkillRepositoryCustom;
import com.company.entity.UserSkill;
import com.company.service.inter.UserSkillServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserSkillServiceImpl implements UserSkillServiceInter {

    @Autowired
    private UserSkillRepositoryCustom userSkillRepo;

    @Override
    public List<UserSkill> getAllSkillByUserId(int userId) {
        return userSkillRepo.getAllSkillByUserId(userId);
    }

    @Override
    public boolean insertUserSkill(UserSkill u) {
        return userSkillRepo.insertUserSkill(u);
    }

    @Override
    public boolean updateUserSkill(UserSkill u) {
        return userSkillRepo.updateUserSkill(u);
    }

    @Override
    public boolean removeUserSkill(int userId) {
        return userSkillRepo.removeUserSkill(userId);
    }
}
