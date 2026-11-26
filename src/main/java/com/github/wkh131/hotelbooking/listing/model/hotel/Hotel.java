package com.github.wkh131.hotelbooking.listing.model.hotel;

import com.github.wkh131.hotelbooking.listing.model.contact.EmailAddress;
import com.github.wkh131.hotelbooking.listing.model.contact.Location;
import com.github.wkh131.hotelbooking.listing.model.contact.PhoneNumber;

/**
 * TODO: document
 * A hotel consisting of stuff.
 */
public record Hotel(String id,
                    String partnerId,
                    String title,
                    Location address,
                    PhoneNumber phoneNumber,
                    EmailAddress emailAddress) {
}
