package com.github.wkh131.hotelbooking.listing.v1.common.delete;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;

public interface DeleteResourceService {
    void delete(DeleteRequest request, StreamObserver<Empty> responseObserver);
}
