package com.nsa.charitystarter.web;

import com.nsa.charitystarter.service.SponsorFinder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class SponsorProfileController {

    private SponsorFinder sponsorFinder;


    public SponsorProfileController(SponsorFinder aFinder){
        sponsorFinder = aFinder;
    }

    @GetMapping("sponsor/{i}")
    public String showSponsorPage(@PathVariable("i") Integer index, Model model){

        Optional<SponsorForm> sponsor = sponsorFinder.findSponsorByIndex(index);

        if (sponsor.isPresent()) {
            model.addAttribute("sponsorKey", sponsor.get());
            return "t_sponsor_profile_page";
        }else {
            return "404";
        }

    }

}
