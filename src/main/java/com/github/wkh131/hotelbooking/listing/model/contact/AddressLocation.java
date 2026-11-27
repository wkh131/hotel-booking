package com.github.wkh131.hotelbooking.listing.model.contact;

public record AddressLocation(String addressLine,
                              String locality,
                              String stateProvince,
                              String countryCode,
                              String postalCode) {
}
