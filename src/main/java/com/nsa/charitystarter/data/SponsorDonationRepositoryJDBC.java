package com.nsa.charitystarter.data;

import com.nsa.charitystarter.domain.converters.Charity;
import com.nsa.charitystarter.service.events.DonationMade;
import com.nsa.charitystarter.service.events.SponsorMade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;

@Slf4j
@Repository
public class SponsorDonationRepositoryJDBC implements com.nsa.charitystarter.service.SponsorRepository {

    private JdbcOperations jdbc;

    public SponsorDonationRepositoryJDBC(JdbcOperations aJdbc) {
        jdbc = aJdbc;
    }


    @Override
    public void saveSponsorDonationEvent(SponsorMade sponsorEvent) {


        Long addressId = saveSponsorAddress(sponsorEvent.getSponsorAddress1(), sponsorEvent.getSponsorAddress2(), sponsorEvent.getSponsorCity(),
                sponsorEvent.getSponsorCountry(), sponsorEvent.getSponsorPostcode());
        Long sponsorId = saveSponsor(sponsorEvent.getSponsorName());
        Long SponsorDonationId = saveSponsorDonation(sponsorEvent.getSponsorDonationAmount(), sponsorEvent.getSponsorDonationTime(),
                sponsorEvent.getIsGiftAidEligibleSponsor(), sponsorEvent.getSponsorPaymentReference(),
                sponsorEvent.getTheCharity(), sponsorId);

        log.debug("... The new Sponsor donation key is being saved ... ");

        log.debug("The new Sponsor donation key is " + SponsorDonationId);

    }

    private Long saveSponsorAddress(String sponsorAddress1, String sponsorAddress2, String sponsorCity, String sponsorCountry, String sponsorPostcode) {

        //Need an object to manage the keys that are generated

        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        jdbc.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                                connection.prepareStatement(
                                        "insert into address(street, district, city, postal_code, country_iso_code) " +
                                                "values (?,?,?,?,?)"
                                        , Statement.RETURN_GENERATED_KEYS);

                        ps.setString(1, sponsorAddress1);
                        ps.setString(2, sponsorAddress2);
                        ps.setString(3, sponsorCity);
                        ps.setString(4, sponsorPostcode);
                        ps.setString(5, sponsorCountry);
                        return ps;
                    }
                },
                holder);

        return holder.getKey().longValue();

    }

    private Long saveSponsor(String sponsorName) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        String[] names = sponsorName.split(" ");
        String sponsorFirstName = names[0];
        String sponsorLastName = names[names.length - 1];

        jdbc.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                                connection.prepareStatement(
                                        "insert into sponsor_form(fundraiser_name) " +
                                                "values (?)",
                                        Statement.RETURN_GENERATED_KEYS);

                        ps.setString(1, sponsorFirstName);
                        return ps;
                    }
                },
                holder);

        return holder.getKey().longValue();

    }

    private Long saveSponsorDonation(Double sponsorDonationAmount,
                                     LocalDateTime sponsorDonationTime,
                                     Boolean isGiftAidEligibleSponsor,
                                     String sponsorPaymentReference,
                                     Charity theCharity,
                                     Long sponsorId) {

        GeneratedKeyHolder holder = new GeneratedKeyHolder();


        jdbc.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                                connection.prepareStatement("insert into donation(amount_in_pence, donation_date, is_own_money, " +
                                        "has_no_benefit_to_donor, wishes_to_gift_aid, donor_id, charity_id) " +
                                        "values (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);


                        ps.setLong(1, Math.round(sponsorDonationAmount * 100));
                        ps.setDate(2, Date.valueOf(sponsorDonationTime.toLocalDate()));
                        ps.setBoolean(3, isGiftAidEligibleSponsor);
                        ps.setBoolean(4, isGiftAidEligibleSponsor);
                        ps.setBoolean(5, isGiftAidEligibleSponsor);
                        ps.setLong(6, sponsorId);
                        ps.setLong(7, theCharity.getId());
                        return ps;
                    }
                },
                holder);

        return holder.getKey().longValue();
    }

}
