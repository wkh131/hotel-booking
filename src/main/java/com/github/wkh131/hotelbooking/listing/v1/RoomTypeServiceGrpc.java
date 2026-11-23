package com.github.wkh131.hotelbooking.listing.v1;

import com.github.wkh131.hotelbooking.listing.model.roomtype.RoomTypeDao;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// TODO: Implement
@Component
public class RoomTypeServiceGrpc {

    private final RoomTypeDao roomTypeDao;

    @Autowired
    public RoomTypeServiceGrpc(final RoomTypeDao roomTypeDao) {
        this.roomTypeDao = roomTypeDao;
    }

    public void listRoomTypes(ListRoomTypesRequest request,
                              StreamObserver<ListRoomTypesResponse> responseObserver) {

    }

    public void createRoomType(CreateRoomTypeRequest request,
                               StreamObserver<RoomType> responseObserver) {
    }

    public void updateRoomType(UpdateRoomTypeRequest request,
                               StreamObserver<RoomType> responseObserver) {
    }

    public void deleteRoomType(DeleteRoomTypeRequest request,
                               StreamObserver<com.google.protobuf.Empty> responseObserver) {
    }
}
