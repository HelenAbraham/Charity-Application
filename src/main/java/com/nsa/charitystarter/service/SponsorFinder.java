package com.nsa.charitystarter.service;

import com.nsa.charitystarter.domain.converters.Charity;
import com.nsa.charitystarter.web.SponsorForm;

import java.util.List;
import java.util.Optional;

public interface SponsorFinder {

    public Optional<SponsorForm> findSponsorByIndex(Integer index);

//    public List<Charity> findSponsorBySearch(String searchTerm);
}
