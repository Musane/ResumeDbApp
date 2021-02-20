package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.CountryDaoInter;
import com.company.entity.Country;
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
public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter {

    public Country getCountry(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String nationality = rs.getString("nationality");
        return new Country(id, name, nationality);

    }

    @Override
    public List<Country> getAll() {
        List<Country> result = new ArrayList<>();

        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("SELECT "
                    + "	* "
                    + "FROM "
                    + "	country ");
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Country country = getCountry(rs);
                result.add(country);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public Country getById(int userId) {
        Country country = null;
        try (Connection c = connect()) {

            PreparedStatement stmt = c.prepareStatement("SELECT "
                    + "	* "
                    + "FROM "
                    + "	country "
                    + "WHERE "
                    + "	id = ?");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                country = getCountry(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return country;
    }

    @Override
    public boolean updateCountry(Country u) {
        boolean b = true;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("UPDATE country "
                    + "SET NAME = ?,"
                    + "nationality = ? "
                    + "WHERE "
                    + "	id = ?");

            stmt.setString(1, u.getName());
            stmt.setString(2, u.getNationality());
            stmt.setInt(3, u.getId());
            b = stmt.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }

    @Override
    public boolean insertCountry(Country u) {
        boolean b = true;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("INSERT INTO country (name, nationality)"
                    + "VALUES "
                    + "	(?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getNationality());

            ResultSet genKeys = stmt.getGeneratedKeys();
            if (genKeys.next()) {
                u.setId(genKeys.getInt(1));
            }
            b = stmt.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }

    @Override
    public boolean removeCountry(int id) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("DELETE FROM country WHERE id=?;");
            stmt.setInt(1, id);
            return stmt.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
