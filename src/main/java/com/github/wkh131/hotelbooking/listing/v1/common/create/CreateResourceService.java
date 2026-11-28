package com.github.wkh131.hotelbooking.listing.v1.common.create;

import io.grpc.stub.StreamObserver;

public interface CreateResourceService<R> {
    void create(CreateRequest<R> request, StreamObserver<R> responseObserver);
}
