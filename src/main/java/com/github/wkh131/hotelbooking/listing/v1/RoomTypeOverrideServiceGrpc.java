package com.github.wkh131.hotelbooking.listing.v1;

import com.github.wkh131.hotelbooking.listing.model.room.override.RoomTypeOverrideDao;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomTypeOverrideServiceGrpc {

    private final RoomTypeOverrideDao roomTypeOverrideDao;

    @Autowired
    public RoomTypeOverrideServiceGrpc(final RoomTypeOverrideDao roomTypeOverrideDao) {
        this.roomTypeOverrideDao = roomTypeOverrideDao;
    }

    public void getRoomTypeOverride(GetRoomTypeOverrideRequest request,
                                    StreamObserver<RoomTypeOverride> responseObserver) {
    }

    public void listRoomTypeOverrides(ListRoomTypeOverridesRequest request,
                                      StreamObserver<ListRoomTypeOverridesResponse> responseObserver) {
    }

    public void createRoomTypeOverride(CreateRoomTypeOverrideRequest request,
                                       StreamObserver<RoomTypeOverride> responseObserver) {
    }

    public void updateRoomTypeOverride(UpdateRoomTypeOverrideRequest request,
                                       StreamObserver<RoomTypeOverride> responseObserver) {
    }

    public void deleteRoomTypeOverride(DeleteRoomTypeOverrideRequest request,
                                       StreamObserver<com.google.protobuf.Empty> responseObserver) {
    }
}
