package com.github.wkh131.hotelbooking.listing.model.partner;

import com.github.wkh131.hotelbooking.listing.model.contact.EmailAddress;
import com.github.wkh131.hotelbooking.listing.model.contact.Location;
import com.github.wkh131.hotelbooking.listing.model.contact.PhoneNumber;
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
public class JdbcPartnerDao implements PartnerDao {

    private static final String SQL_UNIQUE_VIOLATION = "23505";

    private static final String SQL_GET_PARTNER = """
            select resource_name,
                   title,
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

    private static final String SQL_CREATE_PARTNER = """
            INSERT into partners (resource_name,
                                  title,
                                  address_line,
                                  locality,
                                  state_province,
                                  country_code,
                                  postal_code,
                                  phone_number,
                                  email_address
            )
            VALUES (?,?,?,?,?,?.?,?,?,?)
            """;

    private static final String SQL_UPDATE_PARTNER = """
            UPDATE partners
            SET title = ?,
                address_line = ?,
                locality = ?,
                state_province = ?,
                country_code = ?,
                postal_code = ?,
                phone_number = ?,
                email_address = ?
            WHERE resource_name = ?
            """;

    private static final String SQL_DELETE_PARTNER = """
            DELETE from partners
            WHERE resource_name = ?
            """;

    private final DataSource dataSource;

    @Autowired
    public JdbcPartnerDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Partner> findPartner(String id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_GET_PARTNER)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                rs.moveToInsertRow();
                String resourceName = rs.getString("resource_name");
                String title = rs.getString("title");
                String addressLine = rs.getString("address_line");
                String locality = rs.getString("locality");
                String stateProvince = rs.getString("state_province");
                String countryCode = rs.getString("country_code");
                String postalCode = rs.getString("postal_code");
                Location location = new Location(addressLine, locality, stateProvince, countryCode,
                                                 postalCode);
                PhoneNumber phoneNumber = new PhoneNumber(rs.getString("phone_number"));
                EmailAddress emailAddress = new EmailAddress(rs.getString("email_address"));
                return Optional.of(
                        new Partner(resourceName, title, location, phoneNumber, emailAddress));
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
    public boolean createPartner(Partner partner) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_CREATE_PARTNER)) {
            int i = 1;
            ps.setString(i++, partner.resourceName());
            ps.setString(i++, partner.title());
            ps.setString(i++, partner.location().addressLine());
            ps.setString(i++, partner.location().locality());
            ps.setString(i++, partner.location().stateProvince());
            ps.setString(i++, partner.location().countryCode());
            ps.setString(i++, partner.location().postalCode());
            ps.setString(i++, partner.phoneNumber().number());
            ps.setString(i++, partner.emailAddress().email());
            int count = ps.executeUpdate();
            Preconditions.checkState(count == 1, "expected to create exactly one partner");
            return true;
        } catch (SQLException e) {
            if (e instanceof SQLTransientException) {
                // retry????
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
    public boolean updatePartner(Partner partner) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_UPDATE_PARTNER)) {
            int i = 1;
            ps.setString(i++, partner.resourceName());
            ps.setString(i++, partner.title());
            ps.setString(i++, partner.location().addressLine());
            ps.setString(i++, partner.location().locality());
            ps.setString(i++, partner.location().stateProvince());
            ps.setString(i++, partner.location().countryCode());
            ps.setString(i++, partner.location().postalCode());
            ps.setString(i++, partner.phoneNumber().number());
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

    // SAME THING AS DELETE, NO SIDE EFFECTS AT THE MOMENT
    @Override
    public boolean deletePartner(String id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_DELETE_PARTNER)) {
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
}
