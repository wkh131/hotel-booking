package com.github.wkh131.hotelbooking.listing.v1;

import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Default implementation of ListingService.
 */
@Service
public class ListingServiceGrpcImpl extends ListingServiceGrpc.ListingServiceImplBase {

    private final PartnerServiceGrpc partnerService;

    private final HotelServiceGrpc hotelService;

    private final RoomTypeServiceGrpc roomTypeService;

    private final RoomTypeOverrideServiceGrpc roomTypeOverrideService;

    private final AmenityServiceGrpc amenityServiceGrpc;

    private final PhotoServiceGrpc photoServiceGrpc;

    private final PolicyTypeServiceGrpc policyTypeService;

    private final HotelPolicyServiceGrpc hotelPolicyService;


    @Autowired
    public ListingServiceGrpcImpl(final PartnerServiceGrpc partnerService,
                                  final HotelServiceGrpc hotelService,
                                  final RoomTypeServiceGrpc roomTypeService,
                                  final RoomTypeOverrideServiceGrpc roomTypeOverrideService,
                                  final AmenityServiceGrpc amenityServiceGrpc,
                                  final PhotoServiceGrpc photoServiceGrpc,
                                  final PolicyTypeServiceGrpc policyTypeService,
                                  final HotelPolicyServiceGrpc hotelPolicyService) {
        this.partnerService = partnerService;
        this.hotelService = hotelService;
        this.roomTypeService = roomTypeService;
        this.roomTypeOverrideService = roomTypeOverrideService;
        this.amenityServiceGrpc = amenityServiceGrpc;
        this.photoServiceGrpc = photoServiceGrpc;
        this.policyTypeService = policyTypeService;
        this.hotelPolicyService = hotelPolicyService;
    }

    @Override
    public void createPartner(CreatePartnerRequest request,
                              StreamObserver<Partner> responseObserver) {
        responseObserver.onNext(partnerService.createPartner(request));
        responseObserver.onCompleted();
    }

    @Override
    public void getHotel(GetHotelRequest request, StreamObserver<Hotel> responseObserver) {
        responseObserver.onNext(hotelService.getHotel(request));
        responseObserver.onCompleted();
    }

    @Override
    public void createHotel(CreateHotelRequest request, StreamObserver<Hotel> responseObserver) {
    }

    @Override
    public void updateHotel(UpdateHotelRequest request, StreamObserver<Hotel> responseObserver) {
    }

    @Override
    public void deleteHotel(DeleteHotelRequest request,
                            StreamObserver<com.google.protobuf.Empty> responseObserver) {
    }

    @Override
    public void searchHotels(SearchHotelsRequest request,
                             StreamObserver<SearchHotelsResponse> responseObserver) {

    }

    @Override
    public void getRoomType(GetRoomTypeRequest request, StreamObserver<RoomType> responseObserver) {
    }

    @Override
    public void listRoomTypes(ListRoomTypesRequest request,
                              StreamObserver<ListRoomTypesResponse> responseObserver) {
    }

    @Override
    public void createRoomType(CreateRoomTypeRequest request,
                               StreamObserver<RoomType> responseObserver) {
    }

    @Override
    public void updateRoomType(UpdateRoomTypeRequest request,
                               StreamObserver<RoomType> responseObserver) {
    }

    @Override
    public void deleteRoomType(DeleteRoomTypeRequest request,
                               StreamObserver<com.google.protobuf.Empty> responseObserver) {
    }

    @Override
    public void getRoomTypeOverride(GetRoomTypeOverrideRequest request,
                                    StreamObserver<RoomTypeOverride> responseObserver) {
    }

    @Override
    public void listRoomTypeOverrides(ListRoomTypeOverridesRequest request,
                                      StreamObserver<ListRoomTypeOverridesResponse> responseObserver) {
    }

    @Override
    public void createRoomTypeOverride(CreateRoomTypeOverrideRequest request,
                                       StreamObserver<RoomTypeOverride> responseObserver) {
    }

    @Override
    public void updateRoomTypeOverride(UpdateRoomTypeOverrideRequest request,
                                       StreamObserver<RoomTypeOverride> responseObserver) {
    }

    @Override
    public void deleteRoomTypeOverride(DeleteRoomTypeOverrideRequest request,
                                       StreamObserver<com.google.protobuf.Empty> responseObserver) {
    }

    @Override
    public void getAmenity(GetAmenityRequest request, StreamObserver<Amenity> responseObserver) {
    }

    @Override
    public void listAmenities(ListAmenitiesRequest request,
                              StreamObserver<ListAmenitiesResponse> responseObserver) {
    }

    @Override
    public void createAmenity(CreateAmenityRequest request,
                              StreamObserver<Amenity> responseObserver) {
    }

    @Override
    public void updateAmenity(UpdateAmenityRequest request,
                              StreamObserver<Amenity> responseObserver) {
    }

    @Override
    public void deleteAmenity(DeleteAmenityRequest request,
                              StreamObserver<com.google.protobuf.Empty> responseObserver) {
    }

    @Override
    public void getPhoto(GetPhotoRequest request, StreamObserver<Photo> responseObserver) {
    }

    @Override
    public void listPhotos(ListPhotosRequest request,
                           StreamObserver<ListPhotosResponse> responseObserver) {
    }

    @Override
    public void createPhoto(CreatePhotoRequest request, StreamObserver<Photo> responseObserver) {
    }

    @Override
    public void updatePhoto(UpdatePhotoRequest request, StreamObserver<Photo> responseObserver) {
    }

    @Override
    public void deletePhoto(DeletePhotoRequest request,
                            StreamObserver<com.google.protobuf.Empty> responseObserver) {
    }

    @Override
    public void getPolicyType(GetPolicyTypeRequest request,
                              StreamObserver<PolicyType> responseObserver) {
    }

    @Override
    public void listPolicyTypes(ListPolicyTypesRequest request,
                                StreamObserver<ListPolicyTypesResponse> responseObserver) {
    }

    @Override
    public void createPolicyType(CreatePolicyTypeRequest request,
                                 StreamObserver<PolicyType> responseObserver) {
    }

    @Override
    public void updatePolicyType(UpdatePolicyTypeRequest request,
                                 StreamObserver<PolicyType> responseObserver) {
    }

    @Override
    public void deletePolicyType(DeletePolicyTypeRequest request,
                                 StreamObserver<com.google.protobuf.Empty> responseObserver) {
    }

    @Override
    public void getHotelPolicy(GetHotelPolicyRequest request,
                               StreamObserver<HotelPolicy> responseObserver) {
    }

    @Override
    public void listHotelPolicies(ListHotelPoliciesRequest request,
                                  StreamObserver<ListHotelPoliciesResponse> responseObserver) {
    }

    @Override
    public void createHotelPolicy(CreateHotelPolicyRequest request,
                                  StreamObserver<HotelPolicy> responseObserver) {
    }

    @Override
    public void updateHotelPolicy(UpdateHotelPolicyRequest request,
                                  StreamObserver<HotelPolicy> responseObserver) {
    }

    @Override
    public void deleteHotelPolicy(DeleteHotelPolicyRequest request,
                                  StreamObserver<com.google.protobuf.Empty> responseObserver) {
    }
}
