package com.nsa.charitystarter.service;

import com.nsa.charitystarter.domain.converters.Charity;

import java.util.List;
import java.util.Optional;

public interface CharityRepository {

    public Optional<Charity> findById(Integer id);

    public List<Charity> findBySearch(String searchTerm);

}
