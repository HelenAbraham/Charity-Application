package com.nsa.charitystarter.service;

import com.nsa.charitystarter.service.events.SponsorMade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SponsorProcessor implements SponsorCreator {

    private SponsorRepository sponsorRepository;

    public SponsorProcessor(SponsorRepository aRepo){
        sponsorRepository = aRepo;
    }

    @Override
    public void makeSponsor(SponsorMade sponsor){
        log.debug("Sponsor Donation sent = " + sponsor);

        sponsorRepository.saveSponsorDonationEvent(sponsor);

    }
}
