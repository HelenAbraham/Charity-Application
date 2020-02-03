package com.nsa.charitystarter.web;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class SponsorPaymentFormTest {

    @Test
    public void getSponsorCardNumber() throws NoSuchFieldException, IllegalAccessException {
        //given
        SponsorPaymentForm sponsorPaymentForm = new SponsorPaymentForm();
        Field field = sponsorPaymentForm.getClass().getDeclaredField("sponsorCardNumber");
        field.setAccessible(true);
        field.set(sponsorPaymentForm, "magic_values");

        //when
        String result = sponsorPaymentForm.getSponsorCardNumber();

        //then
        assertEquals("field wasn't retrieved properly", result, "magic_values");
    }

    @Test
    public void setSponsorCardNumber() throws NoSuchFieldException, IllegalAccessException {

        //given
        SponsorPaymentForm sponsorPaymentForm = new SponsorPaymentForm();

        //when
        sponsorPaymentForm.setSponsorCardNumber("123445");

        //then
        final Field field = sponsorPaymentForm.getClass().getDeclaredField("sponsorCardNumber");
        field.setAccessible(true);
        assertEquals("Fields didnt match", field.get(sponsorPaymentForm), "123445");

    }

    @Test
    public void setIsCardAddressHomeAddressSponsor() throws NoSuchFieldException, IllegalAccessException  {

        //given
        SponsorPaymentForm sponsorPaymentForm = new SponsorPaymentForm();

        //when
        sponsorPaymentForm.setIsCardAddressHomeAddressSponsor(false);

        //then
        final Field field = sponsorPaymentForm.getClass().getDeclaredField("isCardAddressHomeAddressSponsor");
        field.setAccessible(true);
        assertEquals("Fields didnt match", field.get(sponsorPaymentForm), false);
    }
}