package com.github.wkh131.hotelbooking.listing.model.hotel;

import com.github.wkh131.hotelbooking.listing.model.contact.AddressLocation;
import com.github.wkh131.hotelbooking.listing.model.contact.Contact;
import com.github.wkh131.hotelbooking.listing.model.contact.GeoLocation;

/**
 * Container for hotel ID and the hotel metadata.
 *
 * @param resourceName the unique ID of the hotel.
 * @param partnerResourceName the unique ID for the partner this hotel is registered to.
 * @param title the official name of the hotel.
 * @param address the physical address of the hotel.
 * @param geoLocation the geocoordinates of the hotel.
 * @param contact the contact info of the hotel.
 * @param rating the star value of this hotel.
 */
public record Hotel(String resourceName,
                    String partnerResourceName,
                    String title,
                    AddressLocation address,
                    GeoLocation geoLocation,
                    Contact contact,
                    Rating rating) {
}
