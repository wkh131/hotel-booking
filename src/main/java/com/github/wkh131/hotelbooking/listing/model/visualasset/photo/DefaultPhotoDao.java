package com.github.wkh131.hotelbooking.listing.model.visualasset.photo;

import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Default implementation of {@link PhotoDao}.
 */
@Repository
public class DefaultPhotoDao implements PhotoDao {
    @Override
    public Optional<Photo> findPhoto(String id) {
        return Optional.empty();
    }

    @Override
    public boolean createPhoto(Photo photo) {
        return false;
    }

    @Override
    public boolean updatePhoto(Photo photo) {
        return false;
    }

    @Override
    public boolean deletePhoto(String id) {
        return false;
    }
}
