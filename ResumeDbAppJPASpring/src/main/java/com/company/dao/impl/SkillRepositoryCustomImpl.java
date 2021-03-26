package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.entity.Skill;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author M
 */
@Repository
public class SkillRepositoryCustomImpl extends AbstractDAO implements SkillRepositoryCustom {
@PersistenceContext
EntityManager em;
    @Override
    public List<Skill> getAll() {
    Query query = em.createNativeQuery("SELECT * FROM skill",Skill.class);
        return query.getResultList();

    }

    @Override
    public Skill getById(int userId) {
    Skill skill = em.find(Skill.class, userId);
     return skill;
    }

    @Override
    public boolean updateSkill(Skill u) {
     em.merge(u);
        return true;
    }

    @Override
    public boolean removeSkill(int id) {
     Skill skill = em.find(Skill.class, id);
      em.remove(skill);
   return true;
    }

    @Override
    public List<Skill> getByName(String skillName) {
        Query query = em.createNativeQuery("SELECT * FROM skill WHERE name LIKE = ?",Skill.class);
        query.setParameter(1, skillName);
        return query.getResultList();
    }

    @Override
    public boolean insertSkill(Skill skill) {
    em.persist(skill);
    return true;

    }
}
