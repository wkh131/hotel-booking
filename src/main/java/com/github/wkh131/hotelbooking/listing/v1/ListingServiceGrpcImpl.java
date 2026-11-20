package com.github.wkh131.hotelbooking.listing.v1;

import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Default implementation of ListingService.
 */
@Service
public class ListingServiceGrpcImpl extends ListingServiceGrpc.ListingServiceImplBase {

    private final PartnerGrpcService partnerService;

    private final HotelGrpcService hotelGrpcService;

    @Autowired
    public ListingServiceGrpcImpl(final PartnerGrpcService partnerService, final HotelGrpcService hotelGrpcService) {
        this.partnerService = partnerService;
        this.hotelGrpcService = hotelGrpcService;
    }

    @Override
    public void createPartner(CreatePartnerRequest request,
                              StreamObserver<Partner> responseObserver) {
        responseObserver.onNext(partnerService.createPartner(request));
        responseObserver.onCompleted();
    }

    @Override
    public void getHotel(GetHotelRequest request,
                         StreamObserver<Hotel> responseObserver) {
        responseObserver.onNext(hotelGrpcService.getHotel(request));
        responseObserver.onCompleted();
    }

    @Override
    public void createHotel(com.github.wkh131.hotelbooking.listing.v1.CreateHotelRequest request,
                            StreamObserver<Hotel> responseObserver) {
    }

    @Override
    public void updateHotel(UpdateHotelRequest request,
                            StreamObserver<Hotel> responseObserver) {
    }

    @Override
    public void deleteHotel(DeleteHotelRequest request,
                            StreamObserver<com.google.protobuf.Empty> responseObserver) {
    }

    @Override
    public void searchHotels(SearchHotelsRequest request,
                             StreamObserver<SearchHotelsResponse> responseObserver) {

    }
}
