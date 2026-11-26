package com.github.wkh131.hotelbooking.listing.model.visualasset.photo;

import java.util.Optional;

/**
 * Data Access Object for {@link Photo}.
 */
public interface PhotoDao {

    /**
     * Finds a Photo by ID.
     *
     * @param id the id of the Photo.
     * @return an optional containing the Photo, if found, otherwise empty.
     */
    Optional<Photo> findPhoto(String id);

    /**
     * Creates a Photo.
     *
     * @param photo the Photo to create.
     * @return true if not already existed and is created, otherwise false.
     */
    boolean createPhoto(Photo photo);

    /**
     * Updates a Photo.
     *
     * @param photo the value of the Photo to update to.
     * @return true if already existed and is updated, otherwise false.
     */
    boolean updatePhoto(Photo photo);

    /**
     * Deletes a Photo.
     *
     * @param id the id of the Photo to delete.
     * @return true if already existed and is deleted, otherwise false.
     */
    boolean deletePhoto(String id);
}
