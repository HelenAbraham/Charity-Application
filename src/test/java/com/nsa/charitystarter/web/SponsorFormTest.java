package com.nsa.charitystarter.web;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class SponsorFormTest {

    @Test
    public void getName() throws NoSuchFieldException, IllegalAccessException {

        //given
        SponsorForm sponsorForm = new SponsorForm();
        Field field = sponsorForm.getClass().getDeclaredField("name");
        field.setAccessible(true);
        field.set(sponsorForm, "magic_values");

        //when
        String result = sponsorForm.getName();

        //then
        assertEquals("field wasn't retrieved properly", result, "magic_values");


    }

    @Test
    public void getAction() throws NoSuchFieldException, IllegalAccessException {
        //given
        SponsorForm sponsorForm = new SponsorForm();
        Field field = sponsorForm.getClass().getDeclaredField("action");
        field.setAccessible(true);
        field.set(sponsorForm, "magic_values");

        //when
        String result = sponsorForm.getAction();

        //then
        assertEquals("field wasn't retrieved properly", result, "magic_values");
    }

    @Test
    public void getFURL() throws NoSuchFieldException, IllegalAccessException {
        //given
        SponsorForm sponsorForm = new SponsorForm();
        Field field = sponsorForm.getClass().getDeclaredField("fURL");
        field.setAccessible(true);
        field.set(sponsorForm, "magic_values");

        //when
        String result = sponsorForm.getFURL();

        //then
        assertEquals("field wasn't retrieved properly", result, "magic_values");
    }

    @Test
    public void setName() throws NoSuchFieldException, IllegalAccessException{

        //given
        SponsorForm sponsorForm = new SponsorForm();

        //when
        sponsorForm.setName("Monkey");

        //then
        final Field field = sponsorForm.getClass().getDeclaredField("name");
        field.setAccessible(true);
        assertEquals("Fields didnt match", field.get(sponsorForm), "Monkey");

    }

    @Test
    public void setAction() throws NoSuchFieldException, IllegalAccessException {
        //given
        SponsorForm sponsorForm = new SponsorForm();

        //when
        sponsorForm.setAction("running for the longest time ever");

        //then
        final Field field = sponsorForm.getClass().getDeclaredField("action");
        field.setAccessible(true);
        assertEquals("Fields didnt match", field.get(sponsorForm), "running for the longest time ever");
    }

    @Test
    public void setFURL() throws NoSuchFieldException, IllegalAccessException {
        //given
        SponsorForm sponsorForm = new SponsorForm();

        //when
        sponsorForm.setFURL("localhost:8085/sponsor/{name}");

        //then
        final Field field = sponsorForm.getClass().getDeclaredField("fURL");
        field.setAccessible(true);
        assertEquals("Fields didnt match", field.get(sponsorForm), "localhost:8085/sponsor/{name}");

    }
}