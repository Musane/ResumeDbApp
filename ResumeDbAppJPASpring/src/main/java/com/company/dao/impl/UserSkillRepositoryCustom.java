package com.company.dao.impl;

import com.company.entity.UserSkill;

import java.util.List;

/**
 *
 * @author M
 */
public interface UserSkillRepositoryCustom {

    public List<UserSkill> getAllSkillByUserId(int userId);

    public boolean insertUserSkill(UserSkill u);

    public boolean updateUserSkill(UserSkill u);

    public boolean removeUserSkill(int userId);
}
