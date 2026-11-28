package com.github.wkh131.hotelbooking.listing.v1;

import com.github.wkh131.hotelbooking.listing.model.authz.AuthorizationService;
import com.github.wkh131.hotelbooking.listing.v1.common.ModelDao;
import com.github.wkh131.hotelbooking.listing.v1.common.ProtoMapper;
import com.github.wkh131.hotelbooking.listing.v1.common.ResourceStringsResolver;
import com.github.wkh131.hotelbooking.listing.v1.common.create.CreateRequest;
import com.github.wkh131.hotelbooking.listing.v1.common.create.CreateResourceService;
import com.github.wkh131.hotelbooking.listing.v1.common.create.DefaultCreateResourceService;
import com.github.wkh131.hotelbooking.listing.v1.common.delete.DefaultDeleteResourceService;
import com.github.wkh131.hotelbooking.listing.v1.common.delete.DeleteResourceService;
import com.github.wkh131.hotelbooking.listing.v1.common.get.DefaultGetResourceService;
import com.github.wkh131.hotelbooking.listing.v1.common.get.GetResourceService;
import com.github.wkh131.hotelbooking.listing.v1.common.update.DefaultUpdateResourceService;
import com.github.wkh131.hotelbooking.listing.v1.common.update.UpdateResourceService;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Hotel resource related implementation of methods in ListingService.
 */
@Component
public class HotelServiceGrpc {

    private static final String RESOURCE_FIELD_NAME = "hotel";

    private static final String RESOURCE_NAME_FORMAT = "hotels/%s";

    private final GetResourceService<Hotel> getResourceService;

    private final CreateResourceService<Hotel> createResourceService;

    private final UpdateResourceService<Hotel> updateResourceService;

    private final DeleteResourceService deleteResourceService;

    @Autowired
    public HotelServiceGrpc(final AuthorizationService authorizationService,
                            final ModelDao<com.github.wkh131.hotelbooking.listing.model.hotel.Hotel> hotelDao) {
        final HotelProtoMapper hotelProtoMapper = new HotelProtoMapper();
        final HotelResourceStringsResolver resourceStringsResolver = new HotelResourceStringsResolver();
        this.getResourceService = new DefaultGetResourceService<>(authorizationService, hotelDao,
                hotelProtoMapper);
        this.createResourceService = new DefaultCreateResourceService<>(authorizationService, hotelDao,
                hotelProtoMapper, resourceStringsResolver);
        this.updateResourceService = new DefaultUpdateResourceService<>(authorizationService, hotelDao,
                hotelProtoMapper, resourceStringsResolver);
        this.deleteResourceService = new DefaultDeleteResourceService<>(authorizationService, hotelDao);
    }

    public void getHotel(GetHotelRequest request, StreamObserver<Hotel> responseObserver) {
        getResourceService.get(request::getName, responseObserver);
    }

    public void createHotel(CreateHotelRequest request, StreamObserver<Hotel> responseObserver) {
        createResourceService.create(new CreateRequest<>() {
            @Override
            public String getParent() {
                // no parent node for partners
                return "";
            }

            @Override
            public String getId() {
                return request.getHotelId();
            }

            @Override
            public Hotel getProto() {
                return request.getHotel();
            }
        }, responseObserver);
    }

    public void updateHotel(UpdateHotelRequest request, StreamObserver<Hotel> responseObserver) {
        updateResourceService.update(request::getHotel, responseObserver);
    }

    public void deleteHotel(DeleteHotelRequest request,
                            StreamObserver<com.google.protobuf.Empty> responseObserver) {
        deleteResourceService.delete(request::getName, responseObserver);
    }

    // TODO: move to search service
    public void searchHotels(SearchHotelsRequest request,
                             StreamObserver<SearchHotelsResponse> responseObserver) {

    }

    private static class HotelProtoMapper implements ProtoMapper<com.github.wkh131.hotelbooking.listing.model.hotel.Hotel, Hotel> {

        @Override
        public com.github.wkh131.hotelbooking.listing.model.hotel.Hotel fromProto(Hotel proto) {
            String resourceName = proto.getName();
            return null;
        }

        @Override
        public Hotel toProto(com.github.wkh131.hotelbooking.listing.model.hotel.Hotel rModel) {
            String name = rModel.resourceName();
            return null;
        }
    }

    private static class HotelResourceStringsResolver implements ResourceStringsResolver<Hotel> {

        @Override
        public String getName(Hotel proto) {
            return proto.getName();
        }

        @Override
        public String getFieldName() {
            return RESOURCE_FIELD_NAME;
        }

        @Override
        public String makeName(String parentName, String resourceId) {
            return parentName + String.format(RESOURCE_NAME_FORMAT, resourceId);
        }
    }
}
