package com.company.service.inter;

import com.company.entity.Skill;

import java.util.List;

/**
 *
 * @author M
 */
public interface SkillServiceInter {

    public List<Skill> getAll();

    public Skill getById(int userId);

    boolean updateSkill(Skill u);

    boolean removeSkill(int id);

    public List<Skill> getByName(String name);

    public boolean insertSkill(Skill skill);
}
