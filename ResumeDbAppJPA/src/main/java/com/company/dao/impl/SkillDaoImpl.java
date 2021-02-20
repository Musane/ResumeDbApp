package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.SkillDaoInter;
import com.company.entity.Skill;
import com.company.entity.UserSkill;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author M
 */
public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter {

    @Override
    public List<Skill> getAll() {
        EntityManager em = em();
        Query query = em.createNamedQuery("UserSkill.findAll", UserSkill.class);
        return query.getResultList();

    }

    @Override
    public Skill getById(int userId) {
        EntityManager em = em();
        Skill skill = em.find(Skill.class, userId);
        em.close();
        return skill;
    }

    @Override
    public boolean updateSkill(Skill u) {
        EntityManager em = em();
        em.getTransaction().begin();
        em.merge(u);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    @Override
    public boolean removeSkill(int id) {
        EntityManager em = em();
        Skill skill = em.find(Skill.class, id);
        em.getTransaction().begin();
        em.remove(skill);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    @Override
    public List<Skill> getByName(String skillName) {

        EntityManager em = em();
        Query query = em.createNamedQuery("Skill.findByName", Skill.class);
        return query.getResultList();
    }

    @Override
    public boolean insertSkill(Skill skill) {
        EntityManager em = em();
        em.getTransaction().begin();
        em.persist(skill);
        em.getTransaction().commit();
        em.close();
        return true;

    }
}
