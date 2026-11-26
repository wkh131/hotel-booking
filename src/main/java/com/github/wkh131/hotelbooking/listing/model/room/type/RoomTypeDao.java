package com.github.wkh131.hotelbooking.listing.model.room.type;

import java.util.Optional;

/**
 * Data Access Object for {@link RoomType}.
 */
public interface RoomTypeDao {
    
    /**
     * Finds a RoomType by ID.
     *
     * @param id the id of the RoomType.
     * @return an optional containing the RoomType, if found, otherwise empty.
     */
    Optional<RoomType> findRoomType(String id);

    /**
     * Creates a RoomType.
     *
     * @param roomType the RoomType to create.
     * @return true if not already existed and is created, otherwise false.
     */
    boolean createRoomType(RoomType roomType);

    /**
     * Updates a RoomType.
     *
     * @param roomType the value of the RoomType to update to.
     * @return true if already existed and is updated, otherwise false.
     */
    boolean updateRoomType(RoomType roomType);

    /**
     * Deletes a RoomType.
     *
     * @param id the id of the RoomType to delete.
     * @return true if already existed and is deleted, otherwise false.
     */
    boolean deleteRoomType(String id);
}
