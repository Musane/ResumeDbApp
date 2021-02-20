package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.SkillDaoInter;
import com.company.entity.Skill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author M
 */
public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter {

    public Skill getSkill(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        return new Skill(id, name);
    }

    @Override
    public List<Skill> getAll() {
        List<Skill> result = new ArrayList<>();
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("SELECT "
                    + "	* "
                    + "FROM "
                    + "	skill");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                Skill skill = getSkill(rs);
                result.add(skill);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;

    }

    @Override
    public Skill getById(int userId) {
        Skill user = null;
        try (Connection c = connect()) {
            PreparedStatement pStmt = c.prepareStatement("SELECT "
                    + "	* "
                    + "FROM "
                    + "	skill "
                    + "WHERE "
                    + "	id = ?");
            pStmt.setInt(1, userId);
            pStmt.execute();

            ResultSet rs = pStmt.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getNString("name");

                user = new Skill(id, name);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean updateSkill(Skill u) {
        boolean b = true;
        try (Connection c = connect()) {
            PreparedStatement pStmt = c.prepareStatement("UPDATE skill "
                    + "SET name = ? "
                    + "WHERE "
                    + "	id = ?");
            pStmt.setString(1, u.getName());
            pStmt.setInt(2, u.getId());
            b = pStmt.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
            b = false;
        }
        return b;
    }

    @Override
    public boolean removeSkill(int id) {
        try (Connection c = connect()) {
            PreparedStatement pStmt = c.prepareCall("DELETE FROM skill WHERE id=?;");
            pStmt.setInt(1, id);
            return pStmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Skill> getByName(String skillName) {
        List<Skill> list = new ArrayList<>();
        try (Connection c = connect()) {
            PreparedStatement pStmt = c.prepareStatement("SELECT "
                    + "	* "
                    + "FROM "
                    + "	skill "
                    + "WHERE "
                    + "	name LIKE ?;");
            pStmt.setString(1, skillName);
            pStmt.execute();
            ResultSet rs = pStmt.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                list.add(new Skill(id, name));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean insertSkill(Skill skill) {
        boolean b = true;
        try (Connection c = connect()) {
            PreparedStatement pStmt = c.prepareStatement("INSERT INTO skill (name) "
                    + "VALUES "
                    + "	(?);", Statement.RETURN_GENERATED_KEYS);

            pStmt.setString(1, skill.getName());
            b = pStmt.execute();
            ResultSet genKeys = pStmt.getGeneratedKeys();
            if (genKeys.next()) {
                skill.setId(genKeys.getInt(1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return b;
    }

}
