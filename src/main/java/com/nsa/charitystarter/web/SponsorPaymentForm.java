package com.nsa.charitystarter.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SponsorPaymentForm {

    @CreditCardNumber(message = "Invalid Credit Card Number")
    private String sponsorCardNumber;
    private Boolean isCardAddressHomeAddressSponsor = Boolean.FALSE;

}
