package com.nsa.charitystarter.web;


import com.nsa.charitystarter.domain.converters.Charity;
import com.nsa.charitystarter.service.CharityFinder;
import com.nsa.charitystarter.service.DonationCreator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(com.nsa.charitystarter.web.DonorController.class)
public class MakeDonations {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CharityFinder finder; //don't need to set the mock here yet.  Can pass the Charity object to the session directly.
    @MockBean
    private DonationCreator creator;

    @Test
    public void theOneWhereTheDonorDetailsAreOK() throws Exception {

        mvc.perform
                (post
                        ("/donorDetails")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "Bob Geldof")
                        .param("addressLine1", "Boomtown Towers")
                        .param("addressLine2", "Ravenhill")
                        .param("city", "Belfast")
                        .param("postcode", "BF1 1BG")
                        .param("countryISO", "GB")
                        .param("donationAmount", "1000")
                        .param("isGiftAidEligible", "1")
                        .sessionAttr("donorKey", new DonorForm())
                        .sessionAttr("charityKey", new Charity(
                                3L,
                                "Cancer Research UK",
                                "22345678",
                                "CRUK",
                                "cancer, research, oncology"))
                )
                .andDo(
                        print()
                )
                .andExpect(
                        status().isOk()
                )
                .andExpect(
                        content().string(containsString("You are donating to:"))
                )
                .andExpect(
                        content().string(containsString("<span>Cancer Research UK</span>"))
                )
                .andExpect(
                        content().string(containsString("<title>Payment</title>"))
                )
        ;


    }
}
