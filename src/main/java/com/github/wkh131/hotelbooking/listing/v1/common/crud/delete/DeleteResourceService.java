package com.github.wkh131.hotelbooking.listing.v1.common.crud.delete;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;

public interface DeleteResourceService<Req extends DeleteRequest> {
    void delete(Req request, StreamObserver<Empty> responseObserver);
}
