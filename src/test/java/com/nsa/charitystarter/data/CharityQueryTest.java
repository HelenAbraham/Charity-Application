package com.nsa.charitystarter.data;

import com.nsa.charitystarter.domain.converters.Charity;
import com.nsa.charitystarter.service.CharityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@JdbcTest
@ComponentScan
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class CharityQueryTest {

    @Autowired
    private CharityRepository repo;

    @Test
    public void theOneWhereOxfamIsReturned() throws Exception {

        Optional<Charity> aCharity = repo.findById(8);
        assertEquals("OXFAM", aCharity.get().getName().toUpperCase());


    }


}
