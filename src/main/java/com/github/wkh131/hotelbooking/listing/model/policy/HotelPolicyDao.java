package com.github.wkh131.hotelbooking.listing.model.policy;

import java.util.Optional;

/**
 * Data Access Object for {@link HotelPolicyDao}.
 */
public interface HotelPolicyDao {

    /**
     * Finds a HotelPolicy by ID.
     *
     * @param id the id of the HotelPolicy.
     * @return an optional containing the HotelPolicy, if found, otherwise empty.
     */
    Optional<HotelPolicy> findHotelPolicy(String id);

    /**
     * Creates a HotelPolicy.
     *
     * @param hotelPolicy the HotelPolicy to create.
     * @return true if not already existed and is created, otherwise false.
     */
    boolean createHotelPolicy(HotelPolicy hotelPolicy);

    /**
     * Updates a HotelPolicy.
     *
     * @param hotelPolicy the value of the HotelPolicy to update to.
     * @return true if already existed and is updated, otherwise false.
     */
    boolean updateHotelPolicy(HotelPolicy hotelPolicy);

    /**
     * Deletes a HotelPolicy.
     *
     * @param id the id of the HotelPolicy to delete.
     * @return true if already existed and is deleted, otherwise false.
     */
    boolean deleteHotelPolicy(String id);
}
