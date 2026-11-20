package com.github.wkh131.hotelbooking.listing.v1;

import org.springframework.stereotype.Component;

// TODO: implement
@Component
public class HotelGrpcService {
    public Hotel getHotel(GetHotelRequest request) {
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
