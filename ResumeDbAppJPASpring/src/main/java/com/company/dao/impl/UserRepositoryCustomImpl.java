package com.company.dao.impl;

import com.company.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 *
 * @author M
 */
@Repository
@Qualifier("userDao1")
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    @PersistenceContext
    EntityManager em;
    //JPQL
    @Override
    public List<User> getAll(String name, String surname, Integer nationalityId) {
        String jpql = "select u from User u where 1=1";

        if (name != null && !name.trim().isEmpty()) {
            jpql += " and u.name=:name ";
        }

        if (surname != null && !surname.trim().isEmpty()) {
            jpql += " and u.surname=:surname ";
        }

        if (nationalityId != null) {
            jpql += " and u.nationality_id=:nid ";
        }

        Query query = em.createQuery(jpql, User.class);

        if (name != null && !name.trim().isEmpty()) {
            query.setParameter("name", name);

        }

        if (surname != null && !surname.trim().isEmpty()) {
            query.setParameter("surname", surname);
        }

        if (nationalityId != null) {
            query.setParameter("nid", nationalityId);
        }

        return query.getResultList();

    }

    @Override
    public boolean updateUser(User u) {
        em.merge(u);
        return true;

    }

    @Override
    public boolean removeUser(int id) {
        User u = em.find(User.class,
                id);
        em.remove(u);
        return true;

    }

    @Override
    public User getById(int userId) {
        User u = em.find(User.class,
                userId);
        return u;

    }

    private static BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();

    @Override
    public boolean addUser(User u) {
        u.setPassword(crypt.encode(u.getPassword()));
        em.persist(u);
        return true;
    }

    //CriteriaBuilder
    @Override
    public User findByEmailAndPassword(String email, String password) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> q1 = cb.createQuery(User.class);
        Root<User> postRoot = q1.from(User.class);
        CriteriaQuery<User> q2 = q1
                .where(cb.equal(postRoot.get("email"), email), cb.equal(postRoot.get("password"), password));

        Query query = em.createQuery(q2);
        List<User> list = query.getResultList();
        if (list.size() == 1) {
            return list.get(0);
        }

        return null;
    }

    //Native SQL
    @Override
    public User findByEmail(String email) {
        Query query = em.createNativeQuery("select * from user where email=?", User.class
        );
        query.setParameter(1, email);
        List<User> list = query.getResultList();
        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

}
