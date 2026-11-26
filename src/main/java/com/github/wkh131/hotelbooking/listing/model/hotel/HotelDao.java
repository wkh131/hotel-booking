package com.github.wkh131.hotelbooking.listing.model.hotel;

import java.util.Optional;

/**
 * Data Access Object for {@link Hotel}.
 */
public interface HotelDao {

    /**
     * Finds a Hotel by ID.
     *
     * @param id the ID of the Hotel.
     * @return an optional containing the Hotel, if found, otherwise empty.
     */
    Optional<Hotel> findHotel(String id);

    /**
     * Creates a Hotel.
     *
     * @param hotel the Hotel to create.
     * @return true if the hotel didn't already exist and was created successfully, otherwise false.
     */
    boolean createHotel(Hotel hotel);

    /**
     * Updates a Hotel.
     *
     * @param hotel the value of the Hotel to update to.
     * @return true if the hotel already existed and was updated successfully, otherwise false.
     */
    boolean updateHotel(Hotel hotel);

    /**
     * Deletes a Hotel, if it exists.
     *
     * @param id the id of the Hotel to delete.
     * @return true if the hotel already existed and was deleted successfully, otherwise false.
     */
    boolean deleteHotel(String id);
}
