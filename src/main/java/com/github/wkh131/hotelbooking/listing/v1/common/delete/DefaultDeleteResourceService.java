package com.github.wkh131.hotelbooking.listing.v1.common.delete;

import com.github.wkh131.hotelbooking.listing.model.authz.Action;
import com.github.wkh131.hotelbooking.listing.model.authz.AuthorizationService;
import com.github.wkh131.hotelbooking.listing.v1.StringUtils;
import com.github.wkh131.hotelbooking.listing.v1.common.ModelDao;
import com.google.protobuf.Empty;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import static com.github.wkh131.hotelbooking.listing.v1.GrpcServiceUtils.nameMaxLengthErrorMsg;
import static com.github.wkh131.hotelbooking.listing.v1.GrpcServiceUtils.topNameMissingErrorMsg;

public class DefaultDeleteResourceService<M> implements DeleteResourceService {

    private final AuthorizationService authorizationService;

    private final ModelDao<M> resourceDao;

    public DefaultDeleteResourceService(final AuthorizationService authorizationService,
                                        final ModelDao<M> resourceDao) {
        this.authorizationService = authorizationService;
        this.resourceDao = resourceDao;
    }

    @Override
    public void delete(DeleteRequest request, StreamObserver<Empty> responseObserver) {
        final String resourceName = request.getName();
        if (resourceName.isEmpty()) {
            responseObserver.onError(
                    Status.INVALID_ARGUMENT.withDescription(topNameMissingErrorMsg()).asRuntimeException());
        } else if (resourceName.length() > StringUtils.MAX_LENGTH_RESOURCE_NAME) {
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription(nameMaxLengthErrorMsg("name"))
                                                            .asRuntimeException());
        } else if (!authorizationService.isAuthorized(Action.MODIFY, resourceName)) {
            responseObserver.onError(Status.PERMISSION_DENIED.asRuntimeException());
        } else {
            if (!resourceDao.delete(resourceName)) {
                responseObserver.onError(Status.NOT_FOUND.asRuntimeException());
            } else {
                responseObserver.onNext(Empty.getDefaultInstance());
                responseObserver.onCompleted();
            }
        }
    }
}
