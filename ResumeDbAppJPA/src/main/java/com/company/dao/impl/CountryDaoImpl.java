package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.CountryDaoInter;
import com.company.entity.Country;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author M
 */
public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter {

    //NamedQuery
    @Override
    public List<Country> getAll() {
        EntityManager em = em();
        Query query = em.createNamedQuery("Country.findAll", Country.class);
        return query.getResultList();
    }

    @Override
    public Country getById(int userId) {
        EntityManager em = em();
        Country u = em.find(Country.class,
                userId);
        em.close();
        return u;
    }

    @Override
    public boolean updateCountry(Country u) {
        EntityManager em = em();
        em.getTransaction().begin();
        em.merge(u);
        em.getTransaction().commit();

        em.close();

        return true;
    }

    @Override
    public boolean insertCountry(Country u) {
        EntityManager em = em();

        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();

        em.close();

        return true;
    }

    @Override
    public boolean removeCountry(int id) {
        EntityManager em = em();
        Country c = em.find(Country.class,
                id);
        em.getTransaction().begin();
        em.remove(c);
        em.getTransaction().commit();

        em.close();
        return true;
    }

}
