package com.github.wkh131.hotelbooking.listing.v1.common.crud.update;

import io.grpc.stub.StreamObserver;

public interface UpdateResourceService<R, Req extends UpdateRequest> {
    void update(Req request, StreamObserver<R> responseObserver);
}
