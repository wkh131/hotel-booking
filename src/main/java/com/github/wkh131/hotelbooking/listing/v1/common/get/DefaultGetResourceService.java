package com.github.wkh131.hotelbooking.listing.v1.common.get;

import com.github.wkh131.hotelbooking.listing.model.authz.Action;
import com.github.wkh131.hotelbooking.listing.model.authz.AuthorizationService;
import com.github.wkh131.hotelbooking.listing.v1.StringUtils;
import com.github.wkh131.hotelbooking.listing.v1.common.ModelDao;
import com.github.wkh131.hotelbooking.listing.v1.common.ProtoMapper;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import static com.github.wkh131.hotelbooking.listing.v1.GrpcServiceUtils.nameMaxLengthErrorMsg;
import static com.github.wkh131.hotelbooking.listing.v1.GrpcServiceUtils.topNameMissingErrorMsg;

public class DefaultGetResourceService<M, R> implements GetResourceService<R> {

    private final AuthorizationService authorizationService;

    private final ModelDao<M> resourceDao;

    private final ProtoMapper<M, R> protoMapper;

    public DefaultGetResourceService(final AuthorizationService authorizationService,
                                     final ModelDao<M> resourceDao,
                                     final ProtoMapper<M, R> protoMapper) {
        this.authorizationService = authorizationService;
        this.resourceDao = resourceDao;
        this.protoMapper = protoMapper;
    }

    @Override
    public void get(GetRequest request, StreamObserver<R> responseObserver) {
        final String resourceName = request.getName();
        if (resourceName.isEmpty()) {
            responseObserver.onError(
                    Status.INVALID_ARGUMENT.withDescription(topNameMissingErrorMsg()).asRuntimeException());
        } else if (resourceName.length() > StringUtils.MAX_LENGTH_RESOURCE_NAME) {
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription(nameMaxLengthErrorMsg("name"))
                                                            .asRuntimeException());
        } else if (!authorizationService.isAuthorized(Action.READ, resourceName)) {
            responseObserver.onError(Status.PERMISSION_DENIED.asRuntimeException());
        } else {
            resourceDao.get(resourceName)
                       .map(protoMapper::toProto)
                       .ifPresentOrElse(responseObserver::onNext,
                               () -> responseObserver.onError(Status.NOT_FOUND.asRuntimeException()));
            responseObserver.onCompleted();
        }
    }
}
