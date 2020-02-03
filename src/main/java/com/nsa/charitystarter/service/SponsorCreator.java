package com.nsa.charitystarter.service;

import com.nsa.charitystarter.service.events.SponsorMade;

public interface SponsorCreator {
    public void makeSponsor(SponsorMade sponsor);
}
