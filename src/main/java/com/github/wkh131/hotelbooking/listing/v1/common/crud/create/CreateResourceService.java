package com.github.wkh131.hotelbooking.listing.v1.common.crud.create;

import com.github.wkh131.hotelbooking.listing.v1.common.Resource;
import io.grpc.stub.StreamObserver;

public interface CreateResourceService<R extends Resource, Req extends CreateRequest<R>> {
    void create(Req request, StreamObserver<R> responseObserver);
}
