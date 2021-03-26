package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.entity.Country;
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
public class CountryRepositoryCustomImpl extends AbstractDAO implements CountryRepositoryCustom {
@PersistenceContext
EntityManager em;

    @Override
    public List<Country> getAll() {
        Query query = em.createNativeQuery("SELECT * FROM country", Country.class);
        return query.getResultList();
    }

    @Override
    public Country getById(int userId) {
        Country u = em.find(Country.class,
                userId);
        return u;
    }

    @Override
    public boolean updateCountry(Country u) {
        em.merge(u);
        return true;
    }

    @Override
    public boolean insertCountry(Country u) {
    em.persist(u);
        return true;
    }

    @Override
    public boolean removeCountry(int id) {
       Country c = em.find(Country.class,
                id);
         em.remove(c);
    return true;
    }

}
