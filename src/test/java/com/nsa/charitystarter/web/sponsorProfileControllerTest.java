package com.nsa.charitystarter.web;

import com.nsa.charitystarter.service.SponsorFinder;
import com.nsa.charitystarter.service.SponsorQueries;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SponsorQueries.class)
public class sponsorProfileControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SponsorFinder finder;

    @Test
    public void sponsor4shouldBeIanJobs() throws Exception {

    }
}
