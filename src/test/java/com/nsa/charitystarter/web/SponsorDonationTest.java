package com.nsa.charitystarter.web;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class SponsorDonationTest {

    @Test
    public void setSponsorDonationName() throws NoSuchFieldException, IllegalAccessException {

        //given
        SponsorDonation sponsorDonation = new SponsorDonation();

        //when
        sponsorDonation.setSponsorDonationName("Carl");

        //then
        final Field field = sponsorDonation.getClass().getDeclaredField("sponsorDonationName");
        field.setAccessible(true);
        assertEquals("Fields didnt match", field.get(sponsorDonation), "Carl");
    }

    @Test
    public void setSponsorAddressLine1() throws NoSuchFieldException, IllegalAccessException {

        //given
        SponsorDonation sponsorDonation = new SponsorDonation();

        //when
        sponsorDonation.setSponsorAddressLine1("12 Argosy Way");

        //then
        final Field field = sponsorDonation.getClass().getDeclaredField("sponsorAddressLine1");
        field.setAccessible(true);
        assertEquals("Fields didnt match", field.get(sponsorDonation), "12 Argosy Way");
    }

    @Test
    public void setSponsorAddressLine2()  throws NoSuchFieldException, IllegalAccessException {

        //given
        SponsorDonation sponsorDonation = new SponsorDonation();

        //when
        sponsorDonation.setSponsorAddressLine2("Argosy Way");

        //then
        final Field field = sponsorDonation.getClass().getDeclaredField("sponsorAddressLine2");
        field.setAccessible(true);
        assertEquals("Fields didnt match", field.get(sponsorDonation), "Argosy Way");
    }

    @Test
    public void setSponsorCity()  throws NoSuchFieldException, IllegalAccessException {

        //given
        SponsorDonation sponsorDonation = new SponsorDonation();

        //when
        sponsorDonation.setSponsorCity("Bristol");

        //then
        final Field field = sponsorDonation.getClass().getDeclaredField("sponsorCity");
        field.setAccessible(true);
        assertEquals("Fields didnt match", field.get(sponsorDonation), "Bristol");
    }

    @Test
    public void setSponsorPostcode() throws NoSuchFieldException, IllegalAccessException {

        //given
        SponsorDonation sponsorDonation = new SponsorDonation();

        //when
        sponsorDonation.setSponsorPostcode("NP20 4DG");

        //then
        final Field field = sponsorDonation.getClass().getDeclaredField("sponsorPostcode");
        field.setAccessible(true);
        assertEquals("Fields didnt match", field.get(sponsorDonation), "NP20 4DG");
    }

    @Test
    public void setSponsorDonationAmount()  throws NoSuchFieldException, IllegalAccessException {

        //given
        SponsorDonation sponsorDonation = new SponsorDonation();

        //when
        sponsorDonation.setSponsorDonationAmount(Double.valueOf(78));

        //then
        final Field field = sponsorDonation.getClass().getDeclaredField("sponsorDonationAmount");
        field.setAccessible(true);
        assertEquals("Fields didnt match", field.get(sponsorDonation), (Double.valueOf(78)));
    }

    @Test
    public void setSponsorIGiftAidEligible() throws NoSuchFieldException, IllegalAccessException {

        //given
        SponsorDonation sponsorDonation = new SponsorDonation();

        //when
        sponsorDonation.setSponsorIGiftAidEligible(false);

        //then
        final Field field = sponsorDonation.getClass().getDeclaredField("sponsorIGiftAidEligible");
        field.setAccessible(true);
        assertEquals("Fields didnt match", field.get(sponsorDonation), false);
    }
}