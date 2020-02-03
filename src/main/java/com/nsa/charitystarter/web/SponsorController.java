package com.nsa.charitystarter.web;

import com.nsa.charitystarter.domain.converters.Charity;
import com.nsa.charitystarter.service.CharityFinder;
import com.nsa.charitystarter.service.SponsorCreator;
import com.nsa.charitystarter.service.events.SponsorMade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@SessionAttributes({"charityKey", "sponsorKey", "paymentKey"})
public class SponsorController {

    private CharityFinder finder;
    private SponsorCreator sponsorCreator;


    //@Autowired
    public SponsorController(CharityFinder aFinder, SponsorCreator aCreator) {
        finder = aFinder;
        sponsorCreator = aCreator;
    }

    static final Logger LOG = LoggerFactory.getLogger(DonorController.class);

    //if 'make a sponsor form' button is pressed
    //then this controller is executed


    //creation of the sponsor form, asks for name, action, fURL
    @RequestMapping(path = "/makeSponsor/{id}", method = RequestMethod.GET)
    public String makeSponsor(@PathVariable("id") Integer charityId, Model model) {

        Charity charityToBenefit = finder.findCharityByIndex(charityId).get();

        LOG.debug("Putting charity on  model (and therefore on session) with key 'charity'");
        model.addAttribute("charityKey", charityToBenefit);
        model.addAttribute("sponsorKey", new SponsorForm());
        return "t_sponsor_page";

    }

    //asks for their details plus how much they going to donate
    //carries charity sessionAttribute
    @PostMapping("/sponsoringForCharity")
    public String sponsorDetails(@SessionAttribute("charityKey") Charity charityToBenefit,
                                 @ModelAttribute("sponsorKey") @Valid SponsorForm sponsor,
                                 BindingResult bindingResult,
                                 Model model) {

        if (bindingResult.hasErrors()) {
            LOG.error(bindingResult.toString());
            LOG.error("Sponsor Form has binding errors");
            return "t_sponsor_page";
        }

        LOG.debug(sponsor.toString());
        LOG.debug("Charity = " + charityToBenefit);

        model.addAttribute("sponsorKey", new SponsorDonation());

        return "t_sponsor_donation_form_page";
    }

    //asks for credit card number
    @PostMapping(path = "/sponsorDetails")
    public String sponsorDetails(@SessionAttribute("charityKey") Charity charityToBenefit,
                                 @ModelAttribute("sponsorKey") @Valid SponsorDonation sponsorDonation,
                                 BindingResult bindingResult,
                                 Model model) {

        if (bindingResult.hasErrors()) {
            LOG.error(bindingResult.toString());
            LOG.error("Sponsor Form has binding errors");
            return "t_sponsor_donation_form_page";
        }

        LOG.debug(sponsorDonation.toString());
        LOG.debug("Charity = " + charityToBenefit);

        model.addAttribute("paymentKey", new SponsorPaymentForm());

        return "t_sponsor_donation_payment";
    }

    //confirmation page - reveals their name, amount to be donated plus last 4 digits of their card no.
    @PostMapping(path = "/sponsorPaymentDetails")
    public String sponsorPaymentDetails(@SessionAttribute("sponsorKey") SponsorDonation sponsorDonation,
                                        @SessionAttribute("charityKey") Charity charityToBenefit,
                                        @ModelAttribute("paymentKey") @Valid SponsorPaymentForm payment,
                                        BindingResult bindingResult,
                                        Model model) {

        LOG.debug("From session..." + sponsorDonation);
        LOG.debug("From session..." + charityToBenefit);

        if (bindingResult.hasErrors()) {
            LOG.error(bindingResult.toString());
            LOG.error("Sponsor Payment Form has binding errors");
            return "t_sponsor_donation_payment";
        }

        model.addAttribute("last4CardKey", payment.getSponsorCardNumber().substring(payment.getSponsorCardNumber().length() - 4));

        return "t_sponsor_payment_confirmation";

    }

    //receipt and thanks for donation and sponsoring
    @RequestMapping(path = "/confirmSponsorDonation", method = RequestMethod.GET)
    public String confirmSponsorDonation(@SessionAttribute("sponsorKey") SponsorDonation sponsorDonation,
                                         @SessionAttribute("charityKey")Charity charityToBenefit,
                                         @SessionAttribute("paymentKey") SponsorPaymentForm payment,
                                         Model model,
                                         HttpSession session){


        //process the donation
        SponsorMade sponsor = new SponsorMade(
                charityToBenefit,
                sponsorDonation.getSponsorDonationName(),
                sponsorDonation.getSponsorAddressLine1(),
                sponsorDonation.getSponsorAddressLine2(),
                sponsorDonation.getSponsorCity(),
                sponsorDonation.getSponsorPostcode(),
                "GB",
                sponsorDonation.getSponsorDonationAmount(),
                sponsorDonation.getSponsorIGiftAidEligible(),
                LocalDateTime.now(),
                "pay");

        sponsorCreator.makeSponsor(sponsor);


        session.invalidate();
        return "t_sponsor_receipt";
    }




}
