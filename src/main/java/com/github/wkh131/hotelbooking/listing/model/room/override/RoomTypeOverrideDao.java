package com.github.wkh131.hotelbooking.listing.model.room.override;

import java.util.Optional;

/**
 * Data Access Object for {@link RoomTypeOverrideDao}.
 */
public interface RoomTypeOverrideDao {

    /**
     * Finds a RoomTypeOverride by ID.
     *
     * @param id the id of the RoomTypeOverride.
     * @return an optional containing the RoomTypeOverride, if found, otherwise empty.
     */
    Optional<RoomTypeOverride> findRoomTypeOverride(String id);

    /**
     * Creates a RoomTypeOverride.
     *
     * @param roomTypeOverride the RoomTypeOverride to create.
     * @return true if not already existed and is created, otherwise false.
     */
    boolean createRoomTypeOverride(RoomTypeOverride roomTypeOverride);

    /**
     * Updates a RoomTypeOverride.
     *
     * @param roomTypeOverride the value of the RoomTypeOverride to update to.
     * @return true if already existed and is updated, otherwise false.
     */
    boolean updateRoomTypeOverride(RoomTypeOverride roomTypeOverride);

    /**
     * Deletes a RoomTypeOverride.
     *
     * @param id the id of the RoomTypeOverride to delete.
     * @return true if already existed and is deleted, otherwise false.
     */
    boolean deleteRoomTypeOverride(String id);
}
