package com.nsa.charitystarter.service.events;

import com.nsa.charitystarter.domain.converters.Charity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SponsorMade {

    private Charity theCharity;
    private String sponsorName;
    private String sponsorAddress1;
    private String sponsorAddress2;
    private String sponsorCity;
    private String sponsorPostcode;
    private String sponsorCountry = "GB";
    private Double sponsorDonationAmount;
    private Boolean isGiftAidEligibleSponsor;
    private LocalDateTime sponsorDonationTime;
    private String sponsorPaymentReference = "payment";
}
