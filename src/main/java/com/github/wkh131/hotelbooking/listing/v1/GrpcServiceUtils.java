package com.github.wkh131.hotelbooking.listing.v1;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;

// TODO: DOCUMENT
public class GrpcServiceUtils {

    public static <T> void notifyInvalidNameError(StreamObserver<T> responseObserver) {
        responseObserver.onError(
                Status.INVALID_ARGUMENT.withDescription("invalid name").asRuntimeException());
    }
}
