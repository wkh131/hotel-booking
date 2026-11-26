package com.github.wkh131.hotelbooking.listing.model.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Spring-JDBC implementation of {@link HotelDao}.
 */
@Repository
public class SpringJdbcHotelDao implements HotelDao {

    private static final String SQL_GET = """
            select 
            """;

    private final DataSource dataSource;

    @Autowired
    public SpringJdbcHotelDao(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Hotel> findHotel(String id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET)) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean createHotel(Hotel hotel) {
        return false;
    }

    @Override
    public boolean updateHotel(Hotel hotel) {
        return false;
    }

    @Override
    public boolean deleteHotel(String id) {
        return false;
    }
}
