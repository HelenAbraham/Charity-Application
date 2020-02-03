package com.nsa.charitystarter.data;

import com.nsa.charitystarter.domain.converters.Charity;
import com.nsa.charitystarter.service.events.DonationMade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;

@Slf4j
@Repository
public class DonationRepositoryJDBC implements com.nsa.charitystarter.service.DonationRepository {

    private JdbcOperations jdbc;

    public DonationRepositoryJDBC(JdbcOperations aJdbc) {
        jdbc = aJdbc;
    }

    @Override
    public void saveDonationEvent(DonationMade donationEvent) {


        Long addressId = saveAddress(donationEvent.getDonorAddress1(), donationEvent.getDonorAddress2(), donationEvent.getDonorCity(),
                donationEvent.getDonorCountry(), donationEvent.getDonorPostcode());
        Long donorId = saveDonor(donationEvent.getDonorName(), addressId);
        Long donationId = saveDonation(donationEvent.getDonationAmount(), donationEvent.getDonationTime(),
                donationEvent.getIsGiftAidEligible(), donationEvent.getPaymentReference(),
                donationEvent.getTheCharity(), donorId);

        log.debug("The new donation key is being saved ... ");


        log.debug("The new donation key is " + donationId);


    }

    private Long saveAddress(String donorAddress1, String donorAddress2, String donorCity, String donorCountry, String donorPostcode) {

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

                        ps.setString(1, donorAddress1);
                        ps.setString(2, donorAddress2);
                        ps.setString(3, donorCity);
                        ps.setString(4, donorPostcode);
                        ps.setString(5, donorCountry);
                        return ps;
                    }
                },
                holder);

        return holder.getKey().longValue();

    }

    private Long saveDonor(String donorName, Long addressId) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        String[] names = donorName.split(" ");
        String firstName = names[0];
        String lastName = names[names.length - 1];

        jdbc.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                                connection.prepareStatement(
                                        "insert into donor(first_name, last_name, address_id) " +
                                                "values (?,?,?)",
                                        Statement.RETURN_GENERATED_KEYS);

                        ps.setString(1, firstName);
                        ps.setString(2, lastName);
                        ps.setLong(3, addressId);
                        return ps;
                    }
                },
                holder);

        return holder.getKey().longValue();

    }

    private Long saveDonation(Double donationAmount,
                              LocalDateTime donationTime,
                              Boolean isGiftAidEligible,
                              String paymentReference,
                              Charity theCharity,
                              Long donorId) {

        GeneratedKeyHolder holder = new GeneratedKeyHolder();


        jdbc.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                                connection.prepareStatement("insert into donation(amount_in_pence, donation_date, is_own_money, " +
                                        "has_no_benefit_to_donor, wishes_to_gift_aid, donor_id, charity_id) " +
                                        "values (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);


                        ps.setLong(1, Math.round(donationAmount * 100));
                        ps.setDate(2, Date.valueOf(donationTime.toLocalDate()));
                        ps.setBoolean(3, isGiftAidEligible);
                        ps.setBoolean(4, isGiftAidEligible);
                        ps.setBoolean(5, isGiftAidEligible);
                        ps.setLong(6, donorId);
                        ps.setLong(7, theCharity.getId());
                        return ps;
                    }
                },
                holder);

        return holder.getKey().longValue();
    }


}
