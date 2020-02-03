package com.nsa.charitystarter.service;

import com.nsa.charitystarter.service.events.DonationMade;

public interface DonationCreator {
    public void makeDonation(DonationMade donation);
}
