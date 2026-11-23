package com.github.wkh131.hotelbooking.listing.v1;

import com.github.wkh131.hotelbooking.listing.model.amenity.AmenityDao;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Component;

@Component
public class AmenityServiceGrpc {

    private final AmenityDao amenityDao;

    public AmenityServiceGrpc(final AmenityDao amenityDao) {
        this.amenityDao = amenityDao;
    }

    public void getAmenity(GetAmenityRequest request, StreamObserver<Amenity> responseObserver) {
    }

    public void listAmenities(ListAmenitiesRequest request,
                              StreamObserver<ListAmenitiesResponse> responseObserver) {
    }

    public void createAmenity(CreateAmenityRequest request,
                              StreamObserver<Amenity> responseObserver) {
    }

    public void updateAmenity(UpdateAmenityRequest request,
                              StreamObserver<Amenity> responseObserver) {
    }

    public void deleteAmenity(DeleteAmenityRequest request,
                              StreamObserver<com.google.protobuf.Empty> responseObserver) {
    }
}
