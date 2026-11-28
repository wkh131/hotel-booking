package com.github.wkh131.hotelbooking.listing.v1.common.get;

import io.grpc.stub.StreamObserver;

public interface GetResourceService<R> {
    void get(GetRequest request, StreamObserver<R> responseObserver);
}
