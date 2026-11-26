package com.github.wkh131.hotelbooking.listing.model.partner;

import com.github.wkh131.hotelbooking.listing.model.contact.EmailAddress;
import com.github.wkh131.hotelbooking.listing.model.contact.Location;
import com.github.wkh131.hotelbooking.listing.model.contact.PhoneNumber;

public record Partner(String resourceName,
                      String title,
                      Location location,
                      PhoneNumber phoneNumber,
                      EmailAddress emailAddress) {
}
