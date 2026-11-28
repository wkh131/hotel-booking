package com.github.wkh131.hotelbooking.listing.v1.common.update;

import io.grpc.stub.StreamObserver;

public interface UpdateResourceService<R> {
    void update(UpdateRequest<R> request, StreamObserver<R> responseObserver);
}
