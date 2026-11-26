package com.github.wkh131.hotelbooking.listing.model.amenity;

import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Default implementation of {@link AmenityDao}.
 */
@Repository
public class DefaultAmenityDao implements AmenityDao {
    @Override
    public Optional<Amenity> findAmenity(String id) {
        return Optional.empty();
    }

    @Override
    public boolean createAmenity(Amenity amenity) {
        return false;
    }

    @Override
    public boolean updateAmenity(Amenity amenity) {
        return false;
    }

    @Override
    public boolean deleteAmenity(String id) {
        return false;
    }
}
