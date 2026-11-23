package com.github.wkh131.hotelbooking.listing.model.hotel;

public interface HotelDao {
    Hotel getHotel(String id);

    void createHotel(Hotel hotel);

    void updateHotel(Hotel hotel);

    void deleteHotel(Hotel hotel);
}
