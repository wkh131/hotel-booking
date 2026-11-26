package com.github.wkh131.hotelbooking.listing.v1;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

/**
 * Default implementation of ListingService.
 */
@GrpcService
public class ListingServiceGrpcImpl extends ListingServiceGrpc.ListingServiceImplBase {

    private final PartnerServiceGrpc partnerService;

    private final HotelServiceGrpc hotelService;

    private final RoomTypeServiceGrpc roomTypeService;

    private final RoomTypeOverrideServiceGrpc roomTypeOverrideService;

    private final AmenityServiceGrpc amenityService;

    private final PhotoServiceGrpc photoService;

    private final PolicyTypeServiceGrpc policyTypeService;

    private final HotelPolicyServiceGrpc hotelPolicyService;

    @Autowired
    public ListingServiceGrpcImpl(final PartnerServiceGrpc partnerService,
                                  final HotelServiceGrpc hotelService,
                                  final RoomTypeServiceGrpc roomTypeService,
                                  final RoomTypeOverrideServiceGrpc roomTypeOverrideService,
                                  final AmenityServiceGrpc amenityService,
                                  final PhotoServiceGrpc photoService,
                                  final PolicyTypeServiceGrpc policyTypeService,
                                  final HotelPolicyServiceGrpc hotelPolicyService) {
        this.partnerService = partnerService;
        this.hotelService = hotelService;
        this.roomTypeService = roomTypeService;
        this.roomTypeOverrideService = roomTypeOverrideService;
        this.amenityService = amenityService;
        this.photoService = photoService;
        this.policyTypeService = policyTypeService;
        this.hotelPolicyService = hotelPolicyService;
    }

    @Override
    public void getPartner(GetPartnerRequest request, StreamObserver<Partner> responseObserver) {
        // TODO: context? what
        // throw new RuntimeException("oh no");
        responseObserver.onError(
                Status.INVALID_ARGUMENT.withDescription("what happened").asRuntimeException());
        // Context.current().isCancelled();
        //        responseObserver.onNext(Partner.newBuilder().setName("myName").build());
        //        responseObserver.onCompleted();
        // partnerService.getPartner(request, responseObserver);
    }

    @Override
    public void createPartner(CreatePartnerRequest request,
                              StreamObserver<Partner> responseObserver) {
        partnerService.createPartner(request, responseObserver);
    }

    @Override
    public void updatePartner(UpdatePartnerRequest request,
                              StreamObserver<Partner> responseObserver) {
        partnerService.updatePartner(request, responseObserver);
    }

    @Override
    public void deletePartner(DeletePartnerRequest request,
                              StreamObserver<com.google.protobuf.Empty> responseObserver) {
        partnerService.deletePartner(request, responseObserver);
    }

    @Override
    public void getHotel(GetHotelRequest request, StreamObserver<Hotel> responseObserver) {
        hotelService.getHotel(request, responseObserver);
    }

    @Override
    public void createHotel(CreateHotelRequest request, StreamObserver<Hotel> responseObserver) {
        hotelService.createHotel(request, responseObserver);
    }

    @Override
    public void updateHotel(UpdateHotelRequest request, StreamObserver<Hotel> responseObserver) {
        hotelService.updateHotel(request, responseObserver);
    }

    @Override
    public void deleteHotel(DeleteHotelRequest request,
                            StreamObserver<com.google.protobuf.Empty> responseObserver) {
        hotelService.deleteHotel(request, responseObserver);
    }

    @Override
    public void searchHotels(SearchHotelsRequest request,
                             StreamObserver<SearchHotelsResponse> responseObserver) {
        hotelService.searchHotels(request, responseObserver);
    }

    @Override
    public void getRoomType(GetRoomTypeRequest request, StreamObserver<RoomType> responseObserver) {
        roomTypeService.getRoomType(request, responseObserver);
    }

    @Override
    public void listRoomTypes(ListRoomTypesRequest request,
                              StreamObserver<ListRoomTypesResponse> responseObserver) {
        roomTypeService.listRoomTypes(request, responseObserver);
    }

    @Override
    public void createRoomType(CreateRoomTypeRequest request,
                               StreamObserver<RoomType> responseObserver) {
        roomTypeService.createRoomType(request, responseObserver);
    }

    @Override
    public void updateRoomType(UpdateRoomTypeRequest request,
                               StreamObserver<RoomType> responseObserver) {
        roomTypeService.updateRoomType(request, responseObserver);
    }

    @Override
    public void deleteRoomType(DeleteRoomTypeRequest request,
                               StreamObserver<com.google.protobuf.Empty> responseObserver) {
        roomTypeService.deleteRoomType(request, responseObserver);
    }

    @Override
    public void getRoomTypeOverride(GetRoomTypeOverrideRequest request,
                                    StreamObserver<RoomTypeOverride> responseObserver) {
        roomTypeOverrideService.getRoomTypeOverride(request, responseObserver);
    }

    @Override
    public void listRoomTypeOverrides(ListRoomTypeOverridesRequest request,
                                      StreamObserver<ListRoomTypeOverridesResponse> responseObserver) {
        roomTypeOverrideService.listRoomTypeOverrides(request, responseObserver);
    }

    @Override
    public void createRoomTypeOverride(CreateRoomTypeOverrideRequest request,
                                       StreamObserver<RoomTypeOverride> responseObserver) {
        roomTypeOverrideService.createRoomTypeOverride(request, responseObserver);
    }

    @Override
    public void updateRoomTypeOverride(UpdateRoomTypeOverrideRequest request,
                                       StreamObserver<RoomTypeOverride> responseObserver) {
        roomTypeOverrideService.updateRoomTypeOverride(request, responseObserver);
    }

    @Override
    public void deleteRoomTypeOverride(DeleteRoomTypeOverrideRequest request,
                                       StreamObserver<com.google.protobuf.Empty> responseObserver) {
        roomTypeOverrideService.deleteRoomTypeOverride(request, responseObserver);
    }

    @Override
    public void getAmenity(GetAmenityRequest request, StreamObserver<Amenity> responseObserver) {
        amenityService.getAmenity(request, responseObserver);
    }

    @Override
    public void listAmenities(ListAmenitiesRequest request,
                              StreamObserver<ListAmenitiesResponse> responseObserver) {
        amenityService.listAmenities(request, responseObserver);
    }

    @Override
    public void createAmenity(CreateAmenityRequest request,
                              StreamObserver<Amenity> responseObserver) {
        amenityService.createAmenity(request, responseObserver);
    }

    @Override
    public void updateAmenity(UpdateAmenityRequest request,
                              StreamObserver<Amenity> responseObserver) {
        amenityService.updateAmenity(request, responseObserver);
    }

    @Override
    public void deleteAmenity(DeleteAmenityRequest request,
                              StreamObserver<com.google.protobuf.Empty> responseObserver) {
        amenityService.deleteAmenity(request, responseObserver);
    }

    @Override
    public void getPhoto(GetPhotoRequest request, StreamObserver<Photo> responseObserver) {
        photoService.getPhoto(request, responseObserver);
    }

    @Override
    public void listPhotos(ListPhotosRequest request,
                           StreamObserver<ListPhotosResponse> responseObserver) {
        photoService.listPhotos(request, responseObserver);
    }

    @Override
    public void createPhoto(CreatePhotoRequest request, StreamObserver<Photo> responseObserver) {
        photoService.createPhoto(request, responseObserver);
    }

    @Override
    public void updatePhoto(UpdatePhotoRequest request, StreamObserver<Photo> responseObserver) {
        photoService.updatePhoto(request, responseObserver);
    }

    @Override
    public void deletePhoto(DeletePhotoRequest request,
                            StreamObserver<com.google.protobuf.Empty> responseObserver) {
        photoService.deletePhoto(request, responseObserver);
    }

    @Override
    public void getPolicyType(GetPolicyTypeRequest request,
                              StreamObserver<PolicyType> responseObserver) {
        policyTypeService.getPolicyType(request, responseObserver);
    }

    @Override
    public void listPolicyTypes(ListPolicyTypesRequest request,
                                StreamObserver<ListPolicyTypesResponse> responseObserver) {
        policyTypeService.listPolicyTypes(request, responseObserver);
    }

    @Override
    public void createPolicyType(CreatePolicyTypeRequest request,
                                 StreamObserver<PolicyType> responseObserver) {
        policyTypeService.createPolicyType(request, responseObserver);
    }

    @Override
    public void updatePolicyType(UpdatePolicyTypeRequest request,
                                 StreamObserver<PolicyType> responseObserver) {
        policyTypeService.updatePolicyType(request, responseObserver);
    }

    @Override
    public void deletePolicyType(DeletePolicyTypeRequest request,
                                 StreamObserver<com.google.protobuf.Empty> responseObserver) {
        policyTypeService.deletePolicyType(request, responseObserver);
    }

    @Override
    public void getHotelPolicy(GetHotelPolicyRequest request,
                               StreamObserver<HotelPolicy> responseObserver) {
        hotelPolicyService.getHotelPolicy(request, responseObserver);
    }

    @Override
    public void listHotelPolicies(ListHotelPoliciesRequest request,
                                  StreamObserver<ListHotelPoliciesResponse> responseObserver) {
        hotelPolicyService.listHotelPolicies(request, responseObserver);
    }

    @Override
    public void createHotelPolicy(CreateHotelPolicyRequest request,
                                  StreamObserver<HotelPolicy> responseObserver) {
        hotelPolicyService.createHotelPolicy(request, responseObserver);
    }

    @Override
    public void updateHotelPolicy(UpdateHotelPolicyRequest request,
                                  StreamObserver<HotelPolicy> responseObserver) {
        hotelPolicyService.updateHotelPolicy(request, responseObserver);
    }

    @Override
    public void deleteHotelPolicy(DeleteHotelPolicyRequest request,
                                  StreamObserver<com.google.protobuf.Empty> responseObserver) {
        hotelPolicyService.deleteHotelPolicy(request, responseObserver);
    }
}
