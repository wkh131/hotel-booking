package com.github.wkh131.hotelbooking.listing.v1.common.crud.get;

import io.grpc.stub.StreamObserver;

public interface GetResourceService<R, Req extends GetRequest> {
    void get(Req request, StreamObserver<R> responseObserver);
}
