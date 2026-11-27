package com.github.wkh131.hotelbooking.listing.v1.common.crud.delete;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;

public class BaseDeleteResourceService<Req extends DeleteRequest> implements DeleteResourceService<Req> {

    public void delete(Req request, StreamObserver<Empty> responseObserver) {

    }
}
