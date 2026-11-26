package com.github.wkh131.hotelbooking.listing.model.room.override;

import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Default implementation of {@link RoomTypeOverrideDao}.
 */
@Repository
public class DefaultRoomTypeOverrideDao implements RoomTypeOverrideDao {
    
    @Override
    public Optional<RoomTypeOverride> findRoomTypeOverride(String id) {
        return Optional.empty();
    }

    @Override
    public boolean createRoomTypeOverride(RoomTypeOverride roomTypeOverride) {
        return false;
    }

    @Override
    public boolean updateRoomTypeOverride(RoomTypeOverride roomTypeOverride) {
        return false;
    }

    @Override
    public boolean deleteRoomTypeOverride(String id) {
        return false;
    }
}
