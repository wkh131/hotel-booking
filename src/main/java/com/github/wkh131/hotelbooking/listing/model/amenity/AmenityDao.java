package com.github.wkh131.hotelbooking.listing.model.amenity;

import java.util.Optional;

/**
 * Data Access Object for {@link Amenity}.
 */
public interface AmenityDao {

    /**
     * Finds an Amenity by ID.
     *
     * @param id the id of the Amenity.
     * @return an optional containing the Amenity, if found, otherwise empty.
     */
    Optional<Amenity> findAmenity(String id);

    /**
     * Creates an Amenity.
     *
     * @param amenity the Amenity to create.
     * @return true if not already existed and is created, otherwise false.
     */
    boolean createAmenity(Amenity amenity);

    /**
     * Updates an Amenity.
     *
     * @param amenity the value of the Amenity to update to.
     * @return true if already existed and is updated, otherwise false.
     */
    boolean updateAmenity(Amenity amenity);

    /**
     * Deletes an Amenity.
     *
     * @param id the id of the Amenity to delete.
     * @return true if already existed and is deleted, otherwise false.
     */
    boolean deleteAmenity(String id);
}
