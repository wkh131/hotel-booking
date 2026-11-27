package com.github.wkh131.hotelbooking.listing.model.hotel;

/**
 * A rating of a property.
 * @param value the rating value.
 * @param outOf what the rating is out of.
 */
public record Rating(float value, float outOf) {
}
