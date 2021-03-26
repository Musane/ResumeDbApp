package com.company.dao.impl;

import com.company.entity.Country;

import java.util.List;

/**
 *
 * @author M
 */
public interface CountryRepositoryCustom {

    public List<Country> getAll();

    public Country getById(int id);

    boolean updateCountry(Country u);

    boolean insertCountry(Country u);

    boolean removeCountry(int id);

}
