package com.github.wkh131.hotelbooking.listing.v1;

import com.github.wkh131.hotelbooking.listing.model.hotel.HotelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// TODO: implement
@Component
public class HotelServiceGrpc {

    HotelDao hotelDao;

    @Autowired
    public HotelServiceGrpc(final HotelDao hotelDao) {
        this.hotelDao = hotelDao;
    }


    public Hotel getHotel(GetHotelRequest request) {
        String[] ids = request.getName().split("/");
        com.github.wkh131.hotelbooking.listing.model.hotel.Hotel hotel = hotelDao.getHotel(ids[ids.length - 1]);
        return Hotel.newBuilder().build();
    }

    public void createHotel(CreateHotelRequest request) {

    }

    public void updateHotel(UpdateHotelRequest request) {
    }

    public void deleteHotel(DeleteHotelRequest request) {
    }

    public void searchHotels(SearchHotelsRequest request) {

    }
}
