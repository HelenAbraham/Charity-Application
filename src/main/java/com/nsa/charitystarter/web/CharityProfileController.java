package com.nsa.charitystarter.web;

import com.nsa.charitystarter.domain.converters.Charity;
import com.nsa.charitystarter.service.CharityFinder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class CharityProfileController {

    private CharityFinder finder;

    public CharityProfileController(CharityFinder aFinder) {
        finder = aFinder;
    }

    //find charity based from input
    //click charity with its id on URL

    @GetMapping("charity/{i}")
    public String showCharityPage(@PathVariable("i") Integer index, Model model) {

        Optional<Charity> charity = finder.findCharityByIndex(index);

        if (charity.isPresent()) {
            model.addAttribute("charityKey", charity.get());
            return "t_charity_profile_page";

        } else {
            return "404";
        }
    }

    @GetMapping("findCharity")
    public String findCharity(@RequestParam("search") String searchTerm, Model model) {

        List<Charity> charities = finder.findCharityBySearch(searchTerm);

        model.addAttribute("searchTermKey", searchTerm);
        model.addAttribute("charitiesKey", charities);
        return "t_charity_list_page";

    }
}

