package com.github.wkh131.hotelbooking.listing.model.hotel;

import com.github.wkh131.hotelbooking.listing.model.contact.AddressLocation;
import com.github.wkh131.hotelbooking.listing.model.contact.Contact;
import com.github.wkh131.hotelbooking.listing.model.contact.GeoLocation;
import com.github.wkh131.hotelbooking.listing.v1.common.ModelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

/**
 * Spring-JDBC implementation of {@link HotelDao}.
 */
@Repository
public class SpringJdbcHotelDao implements ModelDao<Hotel> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SpringJdbcHotelDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Hotel> get(String id) {
        final String sqlGetHotel = """
                select partners.resource_name as partners_resource_name,
                       hotels.title,
                       hotels.address_line,
                       hotels.locality,
                       hotels.state_province,
                       hotels.country_code,
                       hotels.postal_code,
                       hotels.phone_number,
                       hotels.email_address,
                       hotels.latitude,
                       hotels.longitude,
                       hotels.rating_value,
                       hotels.rating_out_of
                from hotels
                         join partners
                              using (partner_id)
                where hotels.resource_name = ?
                """;
        Hotel hotel = jdbcTemplate.queryForObject(sqlGetHotel, (resultSet, rowNum) -> {
            String partnerResourceName = resultSet.getString("partner_resource_name");
            String title = resultSet.getString("title");
            String addressLine = resultSet.getString("address_line");
            String locality = resultSet.getString("locality");
            String countryCode = resultSet.getString("state_province");
            String countryCode1 = resultSet.getString("country_code");
            String postalCode = resultSet.getString("postal_code");
            String phoneNumber = resultSet.getString("phone_number");
            String emailAddress = resultSet.getString("email_address");
            float latitude = resultSet.getFloat("latitude");
            float longitude = resultSet.getFloat("longitude");
            float ratingValue = resultSet.getFloat("rating_value");
            float ratingOutOf = resultSet.getFloat("rating_out_of");
            Rating rating = new Rating(ratingValue, ratingOutOf);
            return new Hotel(id, partnerResourceName, title,
                    new AddressLocation(addressLine, locality, countryCode, countryCode1, postalCode),
                    new GeoLocation(latitude, longitude), new Contact(phoneNumber, emailAddress), rating);
        }, id);
        return Optional.ofNullable(hotel);
    }

    @Override
    public boolean create(Hotel hotel) {
        final String sqlCreateHotel = """
                with pval as (select partner_id
                              from partners
                              where resource_name = ?)
                insert
                into hotels(resource_name,
                            partner_id,
                            title,
                            address_line,
                            locality,
                            state_province,
                            country_code,
                            postal_code,
                            phone_number,
                            email_address,
                            latitude,
                            longitude,
                            rating_value,
                            rating_out_of)
                select ?, partner_id, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
                from pval
                """;
        try {
            this.jdbcTemplate.update(sqlCreateHotel, hotel.partnerResourceName(), hotel.resourceName(),
                    hotel.title(), hotel.address().addressLine(), hotel.address().locality(),
                    hotel.address().stateProvince(), hotel.address().countryCode(),
                    hotel.address().postalCode(), hotel.contact().phoneNumber(),
                    hotel.contact().emailAddress(), hotel.geoLocation().latitude(),
                    hotel.geoLocation().longitude(), hotel.rating().value(), hotel.rating().outOf());
            return true;
        } catch (DuplicateKeyException e) {
            return false;
        }
    }

    @Override
    public boolean update(Hotel hotel) {
        final String sqlUpdateHotel = """
                update hotels
                set title          = ?,
                    address_line   = ?,
                    locality       = ?,
                    state_province = ?,
                    country_code   = ?,
                    postal_code    = ?,
                    phone_number   = ?,
                    email_address  = ?,
                    latitude       = ?,
                    longitude      = ?,
                    rating_value   = ?,
                    rating_out_of  = ?
                where resource_name = ?
                """;
        int updateCount = this.jdbcTemplate.update(sqlUpdateHotel, hotel.title(),
                hotel.address().addressLine(), hotel.address().locality(), hotel.address().stateProvince(),
                hotel.address().countryCode(), hotel.address().postalCode(), hotel.contact().phoneNumber(),
                hotel.contact().emailAddress(), hotel.geoLocation().latitude(),
                hotel.geoLocation().longitude(), hotel.rating().value(), hotel.rating().outOf(),
                hotel.resourceName());
        return updateCount == 1;
    }

    @Override
    public boolean delete(String id) {
        final String sqlDeleteHotel = """
                delete
                from hotels
                where resource_name = ?
                """;
        int deleteCount = this.jdbcTemplate.update(sqlDeleteHotel, id);
        return deleteCount == 1;
    }
}
