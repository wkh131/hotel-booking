package com.github.wkh131.hotelbooking.listing.model.partner;

import com.github.wkh131.hotelbooking.listing.model.contact.AddressLocation;
import com.github.wkh131.hotelbooking.listing.model.contact.Contact;

public record Partner(String resourceName,
                      String title,
                      AddressLocation location,
                      Contact contact) {
}
