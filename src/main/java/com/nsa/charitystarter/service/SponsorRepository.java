package com.nsa.charitystarter.service;

import com.nsa.charitystarter.service.events.SponsorMade;

public interface SponsorRepository {

    public void saveSponsorDonationEvent(SponsorMade sponsorEvent);

}
