package com.github.wkh131.hotelbooking.listing.v1.common;

import java.util.Optional;

/**
 * Data Access Object.
 * TODO: update docs
 */
public interface ModelDao<M> {

    /**
     * Finds a resource by ID.
     *
     * @param id the unique ID of the resource.
     * @return an optional containing the resource, if found, otherwise empty.
     */
    Optional<M> get(String id);

    /**
     * Creates a resource.
     *
     * @param rModel the domain model of the resource to create.
     * @return true if the resource didn't already exist and was created successfully, otherwise false.
     */
    boolean create(M rModel);

    /**
     * Updates a resource.
     *
     * @param rModel the domain model of the resource to update to.
     * @return true if the resource already existed and was updated successfully, otherwise false.
     */
    boolean update(M rModel);

    /**
     * Deletes a resource.
     *
     * @param id the unique id of the resource to delete.
     * @return true if the resource already existed and was deleted successfully, otherwise false.
     */
    boolean delete(String id);
}
