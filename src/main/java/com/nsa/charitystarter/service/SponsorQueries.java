package com.nsa.charitystarter.service;

import com.nsa.charitystarter.domain.converters.Charity;
import com.nsa.charitystarter.web.SponsorForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SponsorQueries implements SponsorFinder{

    private List<SponsorForm> sponsors = new ArrayList<>();

    public SponsorQueries() {

        SponsorForm Helen = new SponsorForm(
                "Helen Chacko",
                "Running for the London Marathon",
                "localhost:8080/sponsor/0");

        SponsorForm Steve = new SponsorForm(
                "Steve Jones",
                "Brick Fundraising",
                "localhost:8080/sponsor/1");

        SponsorForm Ian = new SponsorForm(
                "Ian Jobs",
                "Cookie Dough Fundraising",
                "localhost:8080/sponsor/2");

        SponsorForm Biju = new SponsorForm(
                "Biju Thundiyil",
                "epilepsy march / purple day ",
                "localhost:8080/sponsor/3");

        sponsors = List.of(Helen, Steve, Ian, Biju);
    }

    /**
     * Return the charity according to position in the list with index starting  at 1
     *
     * @param index
     * @return
     */
    public Optional<SponsorForm> findSponsorByIndex(Integer index) {
        if (index < 1 || index > sponsors.size()) {
            return Optional.empty();
        } else {
            return Optional.of(sponsors.get(index - 1));
        }
    }

//    public List<SponsorForm> findSponsorBySearch(String searchTerm) {
//
//        return sponsors
//                .stream()
//                .filter(c -> c.searchable().contains(searchTerm))
//                .collect(Collectors.toList());
//    }


}
