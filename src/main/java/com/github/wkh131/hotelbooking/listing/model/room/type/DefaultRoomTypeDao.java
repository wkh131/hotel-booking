package com.github.wkh131.hotelbooking.listing.model.room.type;

import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Default implementation of {@link RoomTypeDao}.
 */
@Repository
public class DefaultRoomTypeDao implements RoomTypeDao {

    @Override
    public Optional<RoomType> findRoomType(String id) {
        return Optional.empty();
    }

    @Override
    public boolean createRoomType(RoomType roomType) {
        return false;
    }

    @Override
    public boolean updateRoomType(RoomType RoomType) {
        return false;
    }

    @Override
    public boolean deleteRoomType(String id) {
        return false;
    }
}
