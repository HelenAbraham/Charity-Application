package com.nsa.charitystarter.web;

import com.nsa.charitystarter.domain.converters.Charity;
import com.nsa.charitystarter.service.CharityFinder;
import com.nsa.charitystarter.service.DonationCreator;
import com.nsa.charitystarter.service.events.DonationMade;
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
@SessionAttributes({"charityKey", "donorKey", "paymentKey"})
public class DonorController {

    private CharityFinder finder;
    private DonationCreator donationCreator;

//if donate button selected, this controller executed

    //@Autowired
    public DonorController(CharityFinder aFinder, DonationCreator aCreator) {
        finder = aFinder;
        donationCreator = aCreator;
    }
    static final Logger LOG = LoggerFactory.getLogger(DonorController.class);


    @RequestMapping(path = "/donateToCharity/{id}", method = RequestMethod.GET)
    public String startDonation(@PathVariable("id") Integer charityId, Model model) {

        Charity charityToBenefit = finder.findCharityByIndex(charityId).get();
        LOG.debug("Putting charity on  model (and therefore on session) with key 'charity'");
        model.addAttribute("charityKey", charityToBenefit);
        model.addAttribute("donorKey", new DonorForm());
        return "t_donor_details_page";

    }

    @RequestMapping(path = "donorDetails", method = RequestMethod.POST)
    public String donorDetails(@SessionAttribute("charityKey") Charity charityToBenefit,
                               @ModelAttribute("donorKey") @Valid DonorForm donor, //from form
                               BindingResult bindingResult,
                               Model model) {

        if (bindingResult.hasErrors()) {
            LOG.error(bindingResult.toString());
            LOG.error("Donation Form has binding errors");
            return "t_donor_details_page";
        }

        LOG.debug(donor.toString());
        LOG.debug("Charity = " + charityToBenefit);

        model.addAttribute("paymentKey", new PaymentForm());


        return "t_donation_payment_page";
    }

    @RequestMapping(path = "paymentDetails", method = RequestMethod.POST)
    public String donorDetails(@SessionAttribute("donorKey") DonorForm donor,
                               @SessionAttribute("charityKey") Charity charityToBenefit,
                               @ModelAttribute("paymentKey") @Valid PaymentForm payment,
                               BindingResult bindingResult,
                               Model model) {

        LOG.debug("From session..." + donor);
        LOG.debug("From session..." + charityToBenefit);

        if (bindingResult.hasErrors()) {
            LOG.error(bindingResult.toString());
            LOG.error("Payment Form has binding errors");
            return "t_donation_payment_page";
        }


        model.addAttribute("last4CardKey", payment.getCardNumber().substring(payment.getCardNumber().length() - 4));


        return "t_payment_confirmation_page";
    }

    @RequestMapping(path = "/confirm", method = RequestMethod.GET)
    public String confirmDonation(@SessionAttribute("donorKey") DonorForm donor,
                                  @SessionAttribute("charityKey") Charity charityToBenefit,
                                  @SessionAttribute("paymentKey") PaymentForm payment,
                                  Model model,
                                  HttpSession session) {



        //process the donation
        DonationMade donation = new DonationMade(
                charityToBenefit,
                donor.getName(),
                donor.getAddressLine1(),
                donor.getAddressLine2(),
                donor.getCity(),
                donor.getPostcode(),
                "GB",
                donor.getDonationAmount(),
                donor.getIsGiftAidEligible(),
                LocalDateTime.now(),
                "pay");

        donationCreator.makeDonation(donation);

        session.invalidate();
        return "t_donation_receipt_page";
    }



}
