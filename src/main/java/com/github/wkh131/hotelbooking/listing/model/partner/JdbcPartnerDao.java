package com.github.wkh131.hotelbooking.listing.model.partner;

import com.github.wkh131.hotelbooking.listing.model.contact.AddressLocation;
import com.github.wkh131.hotelbooking.listing.model.contact.Contact;
import com.github.wkh131.hotelbooking.listing.v1.common.ModelDao;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTransientException;
import java.util.Optional;

/**
 * JDBC implementation of {@link PartnerDao}.
 * RETRY LOGIC FOR DAOS DUE TO TRANSIENT SQL EXCEPTIONS SHOULD BE DONE IN THE SERVICE LAYER.
 */
@Repository
public class JdbcPartnerDao implements ModelDao<Partner> {

    private static final String SQL_UNIQUE_VIOLATION = "23505";

    private final DataSource dataSource;

    @Autowired
    public JdbcPartnerDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Partner> get(String id) {
        String sqlGetPartner = """
                select title,
                       address_line,
                       locality,
                       state_province,
                       country_code,
                       postal_code,
                       phone_number,
                       email_address
                from partners
                where partners.resource_name = ?
                """;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlGetPartner)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String title = rs.getString("title");
                String addressLine = rs.getString("address_line");
                String locality = rs.getString("locality");
                String stateProvince = rs.getString("state_province");
                String countryCode = rs.getString("country_code");
                String postalCode = rs.getString("postal_code");
                String phoneNumber = rs.getString("phone_number");
                String emailAddress = rs.getString("email_address");
                AddressLocation location = new AddressLocation(addressLine, locality, stateProvince,
                        countryCode, postalCode);
                Contact contact = new Contact(phoneNumber, emailAddress);
                return Optional.of(new Partner(id, title, location, contact));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // DOES NOT NEED TO BE IDEMPOTENT BECAUSE NO SIDE EFFECTS OF REDOING. WILL JUST RETURN ALREADY
    // EXISTS
    // WELL ACTUALLY THE SIDE EFFECTS COME FROM POTENTIALLY DOING SOMETHING AFTER RESOURCE IS
    // CREATED, LIKE SENDING AN EMAIL. SO NOT ALL CREATES ARE IDEMPOTENT.
    // ALTHOUGH, IF CREATED IN DB, THEN POTENTIALLY CAN GUARANTEE THAT IT IS READ BY DOWNSTREAM
    // USING AN EXACTLY ONCE MESSAGE QUEUE
    // HOW DO WE DIFFERENTIATE BETWEEN DUPLICATE KEY AND TRANSACTION ROLLBACK? ROLLBACK CANT
    // HAPPEN HERE RIGHT SINCE THIS IS A PURE WRITE. DON'T KNOW ENOUGH SO ASSUME IT CAN.
    @Override
    public boolean create(Partner partner) {
        String sqlCreatePartner = """
                insert into partners (resource_name,
                                      title,
                                      address_line,
                                      locality,
                                      state_province,
                                      country_code,
                                      postal_code,
                                      phone_number,
                                      email_address)
                values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlCreatePartner)) {
            int count = setParamsAndExecute(partner, ps);
            Preconditions.checkState(count == 1, "expected to create exactly one partner");
            return true;
        } catch (SQLException e) {
            if (e instanceof SQLTransientException) {
                // retry????
                // currently throws runtimeException
            }
            // if duplicate key then return false
            if (e.getSQLState().equals(SQL_UNIQUE_VIOLATION)) {
                return false;
            }
            throw new RuntimeException(e);
        }
    }

    // SAME THING AS CREATE, NO SIDE EFFECTS AT THE MOMENT
    @Override
    public boolean update(Partner partner) {
        final String sqlUpdatePartner = """
                update partners
                set title          = ?,
                    address_line   = ?,
                    locality       = ?,
                    state_province = ?,
                    country_code   = ?,
                    postal_code    = ?,
                    phone_number   = ?,
                    email_address  = ?
                where resource_name = ?
                """;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlUpdatePartner)) {
            int count = setParamsAndExecute(partner, ps);
            return switch (count) {
                case 0 -> false;
                case 1 -> true;
                default -> throw new RuntimeException("expected unique partners");
            };
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // SAME THING AS DELETE, NO SIDE EFFECTS AT THE MOMENT
    @Override
    public boolean delete(String id) {
        String sqlDeletePartner = """
                delete
                from partners
                where resource_name = ?
                """;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlDeletePartner)) {
            ps.setString(1, id);
            int count = ps.executeUpdate();
            return switch (count) {
                case 0 -> false;
                case 1 -> true;
                default -> throw new RuntimeException("expected unique partners");
            };
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int setParamsAndExecute(Partner partner, PreparedStatement ps) throws SQLException {
        int i = 1;
        ps.setString(i++, partner.resourceName());
        ps.setString(i++, partner.title());
        ps.setString(i++, partner.location().addressLine());
        ps.setString(i++, partner.location().locality());
        ps.setString(i++, partner.location().stateProvince());
        ps.setString(i++, partner.location().countryCode());
        ps.setString(i++, partner.location().postalCode());
        ps.setString(i++, partner.contact().phoneNumber());
        ps.setString(i++, partner.contact().emailAddress());
        return ps.executeUpdate();
    }
}
