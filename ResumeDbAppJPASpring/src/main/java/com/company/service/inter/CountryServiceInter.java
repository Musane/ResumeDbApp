package com.company.service.inter;

import com.company.entity.Country;

import java.util.List;

/**
 *
 * @author M
 */
public interface CountryServiceInter {

    public List<Country> getAll();

    public Country getById(int id);

    boolean updateCountry(Country u);

    boolean insertCountry(Country u);

    boolean removeCountry(int id);

}
