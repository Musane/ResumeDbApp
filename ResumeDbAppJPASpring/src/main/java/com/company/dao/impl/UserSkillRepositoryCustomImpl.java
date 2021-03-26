package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.entity.UserSkill;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author M
 */
@Repository
public class UserSkillRepositoryCustomImpl extends AbstractDAO implements UserSkillRepositoryCustom {
@PersistenceContext
EntityManager em;
    private UserSkill getUserSkill(ResultSet rs) throws Exception {
        int userId = rs.getInt("id");
        int skillId = rs.getInt("skill_id");
        String skillName = rs.getString("skill_name");
        int power = rs.getInt("power");
        UserSkill u = null;
        return u;

    }

    @Override
    public List<UserSkill> getAllSkillByUserId(int userId) {
        List<UserSkill> result = new ArrayList<>();
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("SELECT "
                    + "	u.*, "
                    + "	us.skill_id, "
                    + "	s.NAME AS skill_name, "
                    + "	us.power  "
                    + "FROM "
                    + "	user_skill us "
                    + "	LEFT JOIN USER u ON us.user_id = u.id "
                    + "	LEFT JOIN skill s ON us.skill_id = s.id "
                    + "WHERE "
                    + "	us.user_id = ?");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                UserSkill u = getUserSkill(rs);

                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;

    }

    @Override
    public boolean insertUserSkill(UserSkill u) {
        em.persist(u);
        return true;
    }

    @Override
    public boolean updateUserSkill(UserSkill u) {
       em.merge(u);
       return true;
    }

    @Override
    public boolean removeUserSkill(int id) {
        UserSkill userSkill = em.find(UserSkill.class, id);
        em.remove(userSkill);

        return true;
    }
}
