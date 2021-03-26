package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.entity.EmploymentHistory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author M
 */
@Repository
public class EmploymentHistoryRepositoryCustomImpl extends AbstractDAO implements EmploymentHistoryRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    private EmploymentHistory getEmploymentHistory(ResultSet rs) throws Exception {
        String header = rs.getString("header");
        String jobDescription = rs.getString("job_description");
        Date beginDate = rs.getDate("begin_date");
        Date endDate = rs.getDate("end_date");
        int userId = rs.getInt("user_id");
        EmploymentHistory emp = null;
        return emp;
    }

    @Override
    public List<EmploymentHistory> getAll() {
        String jpql = "select u from EmploymentHistory u ";

        Query query = em.createQuery(jpql, EmploymentHistory.class);

        return query.getResultList();
    }

    @Override
    public List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId) {
        List<EmploymentHistory> result = new ArrayList<>();
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select * from employment_history where user_id = ?");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                EmploymentHistory emp = getEmploymentHistory(rs);

                result.add(emp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;

    }

    @Override
    public EmploymentHistory getById(int empId) {
        String jpql = "select u from EmploymentHistory u where 1=1";

        jpql += " and u.id=:empId ";

        Query query = em.createQuery(jpql, EmploymentHistory.class);

        query.setParameter("empId", empId);


        List<EmploymentHistory> list = query.getResultList();
        if (list.size() == 1) {
            return list.get(0);
        }

        return null;
    }


    @Override
    public boolean insertEmpHistory(EmploymentHistory u) {
        em.persist(u);
        return true;
    }

    @Override
    public boolean updateEmpHistory(EmploymentHistory u) {
        em.merge(u);
        return true;
    }

    @Override
    public boolean removeEmpHistory(int id) {
        EmploymentHistory u = em.find(EmploymentHistory.class, id);
        em.remove(u);
        return true;
    }

}
