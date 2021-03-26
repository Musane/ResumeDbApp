package com.company.service.impl;

import com.company.dao.impl.CountryRepositoryCustom;
import com.company.entity.Country;
import com.company.service.inter.CountryServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CountryServiceImpl implements CountryServiceInter {

    @Autowired
    private CountryRepositoryCustom countryRepo;

    @Override
    public List<Country> getAll() {
        return countryRepo.getAll();
    }

    @Override
    public Country getById(int id) {
        return countryRepo.getById(id);
    }

    @Override
    public boolean updateCountry(Country u) {
        return countryRepo.updateCountry(u);
    }

    @Override
    public boolean insertCountry(Country u) {
        return countryRepo.insertCountry(u);
    }

    @Override
    public boolean removeCountry(int id) {
        return countryRepo.removeCountry(id);
    }
}
