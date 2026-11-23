package com.github.wkh131.hotelbooking.listing.model.partner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Spring-jdbc based implementation of {@link PartnerDao}.
 */
@Repository
public class DefaultPartnerDao implements PartnerDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultPartnerDao.class);

    private static final String SQL_CREATE_PARTNER = """
            INSERT into partners (name,
                                  title,
                                  country_code,
                                  address_line,
                                  phone_number,
                                  email
            )
            VALUES (?,?,?,?,?,?)
            """;

    private final DataSource dataSource;

    @Autowired
    public DefaultPartnerDao(DataSource dataSource) {
        this.dataSource = dataSource;
        createPartner(null);
    }

    @Override
    public void createPartner(Partner partner) {
        try {
            Connection conn = dataSource.getConnection();
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(SQL_CREATE_PARTNER);
            ps.setString(1, "testersss");
            ps.setString(2, "title");
            ps.setString(3, "US");
            ps.setString(4, "my_address");
            ps.setString(5, "18001234567");
            ps.setString(6, "myemail@gmail.com");
            int val = ps.executeUpdate();
            LOGGER.debug("updated {} rows", val);
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
