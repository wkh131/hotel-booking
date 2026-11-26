package com.github.wkh131.hotelbooking.listing.v1;

import com.github.wkh131.hotelbooking.listing.model.hotel.HotelDao;
import io.grpc.stub.StreamObserver;
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

    public void getHotel(GetHotelRequest request, StreamObserver<Hotel> responseObserver) {
        String[] ids = request.getName().split("/");
//        com.github.wkh131.hotelbooking.listing.model.hotel.Hotel hotel = hotelDao.findHotel(
//                ids[ids.length - 1]);
        Hotel.newBuilder().build();
    }

    public void createHotel(CreateHotelRequest request, StreamObserver<Hotel> responseObserver) {

    }

    public void updateHotel(UpdateHotelRequest request, StreamObserver<Hotel> responseObserver) {
    }

    public void deleteHotel(DeleteHotelRequest request,
                            StreamObserver<com.google.protobuf.Empty> responseObserver) {
    }

    public void searchHotels(SearchHotelsRequest request,
                             StreamObserver<SearchHotelsResponse> responseObserver) {

    }
}
