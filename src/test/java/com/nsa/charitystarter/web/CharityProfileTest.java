package com.nsa.charitystarter.web;

import com.nsa.charitystarter.domain.converters.Charity;
import com.nsa.charitystarter.service.CharityFinder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(com.nsa.charitystarter.web.CharityProfileController.class)
public class CharityProfileTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CharityFinder finder;

    @Test
    public void charity1shouldBeRSPCA() throws Exception {

        Charity rspca = new Charity(
                2L,
                "Royal Society for the Preventation of Cruelty to Animals",
                "12345679",
                "RSPCA",
                "animal, animals, welfare, protection");

        //Mockito.when(finder.findCharityByIndex(2)).thenReturn(Optional.of(rspca));
        given(this.finder.findCharityByIndex(2)).willReturn(Optional.of(rspca));

        mvc.perform
                (get
                        ("/charity/2")
                )
                .andDo(
                        print()
                )
                .andExpect(
                        status().isOk()
                )
                .andExpect(
                        content().string(containsString("Royal Society"))
                )

        ;






    }
}
