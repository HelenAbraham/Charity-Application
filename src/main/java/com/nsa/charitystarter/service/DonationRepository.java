package com.nsa.charitystarter.service;

import com.nsa.charitystarter.service.events.DonationMade;

public interface DonationRepository {

    public void saveDonationEvent(DonationMade donationEvent);


}
