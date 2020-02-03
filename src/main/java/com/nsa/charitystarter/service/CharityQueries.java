package com.nsa.charitystarter.service;

import com.nsa.charitystarter.domain.converters.Charity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CharityQueries implements CharityFinder{

    private CharityRepository charityRepository;

    public CharityQueries(CharityRepository repo) {
        charityRepository = repo;

//
//        Charity nspcc = new Charity(
//                1L,
//                "National Society for the Preventation of Cruelty to Children",
//                "12345678",
//                "NSPCC",
//                "childcare, children, protection"
//
//        );
//
//        Charity rspca = new Charity(
//                2L,
//                "Royal Society for the Preventation of Cruelty to Animals",
//                "12345679",
//                "RSPCA",
//                "animal, animals, welfare, protection");
//
//        Charity cruk = new Charity(
//                3L,
//                "Cancer Research UK",
//                "22345678",
//                "CRUK",
//                "cancer, research, oncology");
//
//        Charity bhf = new Charity(
//                4L,
//                "British Heart Foundation",
//                "12334444",
//                "BHF",
//                "heart, cardiac");
//
//        charities = List.of(nspcc,rspca,cruk,bhf);
    }

    /**
     * Return the charity according to their id
     *
     * @param index
     * @return
     */

    public Optional<Charity> findCharityByIndex(Integer index) {
    /*return charities
            .stream()
            .filter(c -> c.getId().equals(index.longValue()))
            .findFirst();*/

        return charityRepository.findById(index);
    }

    public List<Charity> findCharityBySearch(String searchTerm) {

        return charityRepository.findBySearch(searchTerm);
    }


}
