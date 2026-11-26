package com.github.wkh131.hotelbooking.listing.model.policy;

import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Default implementation for {@link HotelPolicyDao}.
 */
@Repository
public class DefaultHotelPolicyDao implements HotelPolicyDao {
    
    @Override
    public Optional<HotelPolicy> findHotelPolicy(String id) {
        return Optional.empty();
    }

    @Override
    public boolean createHotelPolicy(HotelPolicy hotelPolicy) {
        return false;
    }

    @Override
    public boolean updateHotelPolicy(HotelPolicy hotelPolicy) {
        return false;
    }

    @Override
    public boolean deleteHotelPolicy(String id) {
        return false;
    }
}
